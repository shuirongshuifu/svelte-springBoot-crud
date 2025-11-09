/*
Controller (PeopleController) 
    ↓ 调用
Service (PeopleServiceImpl)
    ↓ 调用  
Mapper (PeopleMapper) ← 这个文件
    ↓ 操作
数据库 (MySQL: t_people表)

这个文件就是数据库操作的"大门"，Service层通过它来执行所有数据库操作，
而不需要写任何SQL代码！这也是MyBatis Plus最强大的地方之一。
*/ 

package com.people_sys.people.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.people_sys.people.entity.People;
import org.apache.ibatis.annotations.Mapper;

// 定义一个接口PeopleMapper，继承BaseMapper，这样就有了CRUD操作的方法
@Mapper
public interface PeopleMapper extends BaseMapper<People> {
}
