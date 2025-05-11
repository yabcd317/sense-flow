package com.sense.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysDeviceFactors {
    private Long id;

    private String factorId;

    private Integer deviceId;

    private Integer nodeId;

    private Integer registerId;

    private String factorName;

    private String factorIcon;

    private Double coefficient;

    private Double offset;

    private Integer alarmDelay;

    private Integer alarmRate;

    private Integer backToNormalDelay;

    private Integer digits;

    private String unit;

    private Boolean enabled;

    private Integer sort;

    private Integer maxVoiceAlarmTimes;

    private Integer maxSmsAlarmTimes;

    private LocalDateTime createTime;

    private Integer createId;

    private LocalDateTime updateTime;

    private Integer updateId;

    private Integer status;
}