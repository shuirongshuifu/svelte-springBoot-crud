/*
这是一个实体类（Entity），代表数据库中的一张表
可以根据数据库表，自动生成实体类，也可以手动编写
是数据库表的Java语言描述，定义了数据结构和映射规则，是整个ORM系统的基石

类似前端的class User或TypeScript的interface定义（数据模型）
定义好了entity以后，就可以在mapper中使用，比如：
@Mapper
public interface PeopleMapper extends BaseMapper<People> {

}
BaseMapper是MyBatis Plus的基类，里面定义了CRUD操作的方法
就是说，有了entity以后，就不用手写sql了
就可以通过继承而后直接使用BaseMapper的方法来操作数据库（比如save、update、delete、select等）
*/ 

package com.people_sys.people.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@TableName("t_people")
public class People {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    private Integer age;

    private String home;
    private String remark;

    @TableLogic
    private Integer delFlag;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

}
