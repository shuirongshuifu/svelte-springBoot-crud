/*
dto/
├── PeopleDTO.java      # 新增人员的数据传输对象
├── PeoplePageDTO.java  # 分页查询的参数传输对象
├── PeopleUpdateDTO.java # 更新人员的数据传输对象
└── UserInfo.java       # Excel导入的数据传输对象

DTO (Data Transfer Object) 数据传输对象
作用：
1. PeopleDTO.java       - 新增人员时接收前端数据（无id）
2. PeopleUpdateDTO.java - 更新人员时接收前端数据（包含id）
3. PeoplePageDTO.java   - 分页查询时接收查询参数
4. UserInfo.java        - Excel导入时处理文件数据

为什么需要DTO：
- 安全：控制前端能访问的字段范围
- 灵活：不同接口可以使用不同的数据结构
- 隔离：业务逻辑与数据传输分离
- 兼容：API版本升级时保持向后兼容

数据流向：
Controller接收DTO → Service转换Entity → Mapper操作数据库
*/


package com.people_sys.people.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PeopleDTO {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    private Integer age;

    private String home;
    private String remark;

}
