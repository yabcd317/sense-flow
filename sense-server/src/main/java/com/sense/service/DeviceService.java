package com.sense.service;

import com.sense.vo.DeviceDataVO;
import com.sense.vo.DeviceVO;
import java.util.List;

public interface DeviceService {
    List<DeviceVO> getDevices();
    List<DeviceDataVO> getDeviceData(List<Integer> deviceIds);
    DeviceVO getDeviceInfo(Integer deviceId);
}
