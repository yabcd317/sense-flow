package com.sense.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class device {
    private Integer id;
    //  报警数据
    private Integer alarmRecord;
    //设备地址
    private Integer deviceAddr;
    //设备名称
    private String deviceName;
    //经度
    private Double lat;
    //纬度
    private Double lng;
    //离线间隔
    private Short offlineInterval;
    //保存数据间隔
    private Short saveDataInterval;
    //设备编号
    private String deviceCode;
    //使用标记位置
    private Boolean useMarkLocation;
}
