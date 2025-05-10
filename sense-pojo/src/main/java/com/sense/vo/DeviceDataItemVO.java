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
public class DeviceDataItemVO implements Serializable {
    private String functionName;
    //单位
    private String unit;
    private Double value;
}
