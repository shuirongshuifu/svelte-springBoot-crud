/*
PeopleUpdateDTO.java - 更新人员的数据传输对象
虽然有了PeopleDTO.java，但是更新操作需要包含id，所以需要单独定义一个类——单一职责原则

作用：专门用于更新操作，包含id和可更新的字段
- 包含id：用于标识要更新的记录
- 只包含业务字段：name, age, home, remark
- 不包含系统字段：delFlag, createTime, updateTime（这些由系统自动管理）

前端调用示例：
PUT /people
{
  "id": 1,
  "name": "孙悟空-edit",
  "age": 501,
  "home": "花果山-edit",
  "remark": "齐天大圣-edit"
}

为什么不直接用People实体：
1. 安全：避免前端误传系统字段
2. 清晰：明确哪些字段可以更新
3. 维护：系统字段变更不影响API
*/

package com.people_sys.people.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PeopleUpdateDTO {

    @NotNull(message = "ID不能为空")
    private Integer id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    private Integer age;

    private String home;
    private String remark;

}
