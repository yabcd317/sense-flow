package com.sense.service.impl;

import com.sense.entity.Device;
import com.sense.mapper.DeviceMapper;
import com.sense.service.DeviceService;
import com.sense.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    public List<DeviceVO> getDevices() {
        List<Device> devices = deviceMapper.getDevices();
        if (devices == null) {
            return null;
        }
        List<DeviceVO> deviceVOS = new ArrayList<>();
        for (Device device : devices) {
            DeviceVO deviceVO = new DeviceVO();
            deviceVO.setDeviceName(device.getDeviceName());
            deviceVO.setStatus(device.getStatus());
            deviceVOS.add(deviceVO);
        }
        return deviceVOS;
    }
}
