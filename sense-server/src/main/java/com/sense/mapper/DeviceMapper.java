package com.sense.mapper;

import com.sense.entity.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeviceMapper {

    @Select("select * from sys_device")
    List<Device> getDevices();

    @Select("select * from sys_device where id = #{id}")
    Device getDeviceInfoById(Integer id);
}
