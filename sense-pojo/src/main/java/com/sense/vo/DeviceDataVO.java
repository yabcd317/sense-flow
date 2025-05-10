package com.sense.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDataVO implements Serializable {
    private Integer deviceId;
    private Integer status;
    private List<DeviceDataItemVO> dataItems;
}
