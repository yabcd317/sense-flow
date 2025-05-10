package com.sense.controller;

import com.sense.result.Result;
import com.sense.service.DeviceService;
import com.sense.vo.DeviceVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.sense.constant.MessageConstant.DEVICE_LIST_GET_FAILED;

@RestController
@RequestMapping("/senser")
@Slf4j
public class DeviceListController {
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
}
