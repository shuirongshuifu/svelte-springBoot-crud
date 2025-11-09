/*
UserInfo.java - Excel导入时处理数据的对象
*/ 
package com.people_sys.people.dto;

import cn.hutool.core.annotation.Alias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {

    @Alias("姓名")
    private String name;

    @Alias("年龄")
    private Integer age;

    @Alias("家乡")
    private String home;

    @Alias("备注")
    private String remark;

}
