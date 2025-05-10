package com.sense.mapper;

import com.sense.entity.SysDeviceFactors;
import com.sense.entity.SysDeviceHistorydata;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FactorsMapper {

    @Select("select * from sys_device_factors where deviceId=#{deviceId}")
    List<SysDeviceFactors> selectListByDeviceId(Integer deviceId);


    SysDeviceHistorydata selectLatestByDeviceIdAndNodeId(Integer deviceId, Integer nodeId);
}
