/*
PeoplePageDTO.java - 分页查询的参数传输对象
*/ 
package com.people_sys.people.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PeoplePageDTO {

    private String name;
    private Integer age;
    private String home;
    private String remark;

    @ApiModelProperty("当前第几页")
    @NotNull(message = "当前页不能为空")
    @Min(value = 1, message = "当前页必须大于0")
    private Integer currentPage;

    @ApiModelProperty("每页显示多少行")
    @NotNull(message = "每页显示行数不能为空")
    @Min(value = 1, message = "每页显示行数必须大于0")
    private Integer pageSize;

    @ApiModelProperty("排序字段：age,update_time")
    private String orderColumn;

    @ApiModelProperty("排序顺序：0 升序 1 降序")
    private Integer orderType;

}

