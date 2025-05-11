package com.sense.service.impl;

import com.sense.entity.Device;
import com.sense.entity.SysDeviceFactors;
import com.sense.entity.SysDeviceHistorydata;
import com.sense.mapper.DeviceMapper;
import com.sense.mapper.FactorsMapper;
import com.sense.service.DeviceService;
import com.sense.vo.DeviceDataItemVO;
import com.sense.vo.DeviceDataVO;
import com.sense.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private FactorsMapper factorsMapper;
    @Autowired
    RedisTemplate redisTemplate;

    public List<DeviceVO> getDevices() {
        List<Device> devices = deviceMapper.getDevices();
        if (devices == null) {
            return null;
        }
        List<DeviceVO> deviceVOS = new ArrayList<>();
        for (Device device : devices) {
            DeviceVO deviceVO = new DeviceVO();
            deviceVO.setId(device.getId());
            deviceVO.setDeviceName(device.getDeviceName());
            deviceVO.setStatus(device.getStatus());
/*            deviceVO.setAddress(device.getDeviceAddr());
            deviceVO.setAlarmRecord(device.getAlarmRecord());
            deviceVO.setAlarmSwitch(device.getAlarmSwitch());
            deviceVO.setUseMarkLocation(device.getUseMarkLocation());
            deviceVO.setOfflineInterval(device.getOfflineInterval());
            deviceVO.setSaveDataInterval(device.getSaveDataInterval());*/
            deviceVOS.add(deviceVO);
        }
        return deviceVOS;
    }

    @Override
    public List<DeviceDataVO> getDeviceData(List<Integer> deviceIds) {
        List<DeviceDataVO> deviceDataVOS = new ArrayList<>();

        // 遍历每个设备ID，逐个处理
        for (Integer deviceId : deviceIds) {
            String cacheKey = "deviceData::" + deviceId;

            // 先尝试从 Redis 缓存中获取数据
            DeviceDataVO cachedData = (DeviceDataVO) redisTemplate.opsForValue().get(cacheKey);
            if (cachedData != null) {
                deviceDataVOS.add(cachedData);
                continue; // 如果缓存命中，跳过数据库查询
            }

            // 从数据库查询设备因子信息
            List<SysDeviceFactors> sysDeviceFactors = factorsMapper.selectListByDeviceId(deviceId);

            // 构建返回结果的 DeviceDataVO 对象
            DeviceDataVO deviceDataVO = new DeviceDataVO();
            deviceDataVO.setDeviceId(deviceId);


            // 存储多个设备数据项
            List<DeviceDataItemVO> dataItems = new ArrayList<>();

            // 遍历所有设备因子
            for (SysDeviceFactors sysDeviceFactor : sysDeviceFactors) {
                deviceDataVO.setStatus(sysDeviceFactor.getStatus());
                if (sysDeviceFactor.getStatus() == 0) { // 只处理状态为0（正常）的因子
                    // 查询该设备最新的历史数据
                    SysDeviceHistorydata sysDeviceHistorydata = factorsMapper.selectLatestByDeviceIdAndNodeId(deviceId, sysDeviceFactor.getNodeId());
                    if (sysDeviceHistorydata != null) {
                        // 将查询到的数据封装成 DeviceDataItemVO
                        dataItems.add(new DeviceDataItemVO(sysDeviceFactor.getFactorName(), sysDeviceFactor.getUnit(), sysDeviceHistorydata.getValue()));
                    }
                } else {
                    dataItems.add(new DeviceDataItemVO(sysDeviceFactor.getFactorName(), sysDeviceFactor.getUnit(), null));
                }
            }

            if (!dataItems.isEmpty()) {
                // 设置封装后的数据项
                deviceDataVO.setDataItems(dataItems);

                // 写入 Redis 缓存，60秒后过期
                redisTemplate.opsForValue().set(cacheKey, deviceDataVO, 60, TimeUnit.SECONDS);

                // 添加到最终返回结果中
                deviceDataVOS.add(deviceDataVO);
            }
        }

        return deviceDataVOS;
    }

    @Override
    public DeviceVO getDeviceInfo(Integer deviceId) {
        String cacheKey = "deviceInfo::" + deviceId;
        // 先尝试从 Redis 获取数据
        DeviceVO cachedData = (DeviceVO) redisTemplate.opsForValue().get(cacheKey);
        if (cachedData != null) {
            return cachedData;
        }
        Device device = deviceMapper.getDeviceInfoById(deviceId);
        if (device == null) {
            redisTemplate.opsForValue().set(cacheKey, null, 5, TimeUnit.MINUTES);
            return new DeviceVO();
        }
        DeviceVO deviceVO = DeviceVO.builder()
                .id(deviceId)
                .deviceName(device.getDeviceName())
                .status(device.getStatus())
                .address(device.getDeviceAddr())
                .alarmRecord(device.getAlarmRecord())
                .alarmSwitch(device.getAlarmSwitch())
                .useMarkLocation(device.getUseMarkLocation())
                .offlineInterval(device.getOfflineInterval())
                .saveDataInterval(device.getSaveDataInterval()).build();
        // 将新构建的 DeviceVO 写入 Redis 缓存，5分钟后过期
        redisTemplate.opsForValue().set(cacheKey, deviceVO, 5, TimeUnit.MINUTES);
        return deviceVO;
    }
}
