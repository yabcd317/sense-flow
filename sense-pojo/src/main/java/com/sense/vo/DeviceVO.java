package com.sense.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceVO implements Serializable {
    private Integer id;
    private String deviceName;
    private Integer status;
    private Integer address;
    //离线间隔
    private Short offlineInterval;
    //保存数据间隔
    private Short saveDataInterval;
    private Integer alarmRecord;
    private Integer alarmSwitch;
    private Integer useMarkLocation;


}
