package com.sense.controller;

import com.sense.entity.Device;
import com.sense.result.Result;
import com.sense.service.DeviceService;
import com.sense.vo.DeviceDataVO;
import com.sense.vo.DeviceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sense.constant.MessageConstant.DEVICE_LIST_GET_FAILED;

@RestController
@RequestMapping("/senser")
@Slf4j
public class DeviceController {
    @Autowired
    private DeviceService deviceService;


    @GetMapping("/deviceList")
    public Result<List<DeviceVO>> deviceList() {
        List<DeviceVO> devices = deviceService.getDevices();
        if (devices == null) {
            return Result.error(DEVICE_LIST_GET_FAILED);
        }
        return Result.success(devices);
    }

    @PostMapping("/deviceData")
    public Result<List<DeviceDataVO>> deviceData(@RequestBody List<Integer> deviceIds) {
        List<DeviceDataVO> deviceData = deviceService.getDeviceData(deviceIds);
        return Result.success(deviceData);
    }

    @GetMapping("/deviceInfo/{deviceId}")
    public Result<DeviceVO> deviceInfo(@PathVariable Integer deviceId) {
        DeviceVO deviceVO = deviceService.getDeviceInfo(deviceId);
        return Result.success(deviceVO);
    }

}
