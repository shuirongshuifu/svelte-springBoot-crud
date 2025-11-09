/*
PeopleService.java - 业务接口
*/ 

/**
 * 这里的PeopleService接口和前端的interface作用完全一致：
 * 只定义方法名、参数类型、返回值类型，不写具体逻辑，具体实现交给PeopleServiceImpl
 * */ 

package com.people_sys.people.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.people_sys.people.dto.PeopleDTO;
import com.people_sys.people.dto.PeoplePageDTO;
import com.people_sys.people.entity.People;
import org.springframework.web.multipart.MultipartFile;

public interface PeopleService extends IService<People> {

    IPage<People> selectPage(PeoplePageDTO pageDTO);

    boolean create(PeopleDTO people) throws Exception;

    String importData(MultipartFile file);
}
