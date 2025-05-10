package com.sense.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeePageQueryDTO implements Serializable {

    //用户姓名
    private String name;

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;

}
