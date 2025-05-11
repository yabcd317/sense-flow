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
public class SysDeviceHistorydata {
    private Long id;

    private Integer deviceId;

    private Long recordTime;

    private String recordId;

    private String recordTimeStr;

    private Integer nodeId;

    private Integer registerId;

    private String registerName;

    private String text;

    private Double value;

    private Integer alarmLevel;

    private Integer status;

    private LocalDateTime createTime;

    private Integer createId;

    private LocalDateTime updateTime;

    private Integer updateId;

    private Integer jobId;
}