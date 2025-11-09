/*
PeopleServiceImpl.java - 具体业务实现类
*/ 

package com.people_sys.people.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
// import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.people_sys.people.config.BusinessException;
import com.people_sys.people.dto.PeopleDTO;
import com.people_sys.people.dto.PeoplePageDTO;
import com.people_sys.people.dto.UserInfo;
import com.people_sys.people.entity.People;
import com.people_sys.people.mapper.PeopleMapper;
import com.people_sys.people.service.PeopleService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
// import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People> implements PeopleService {

    @Override
    public IPage<People> selectPage(PeoplePageDTO dto) {
        Page<People> page = new Page<>(dto.getCurrentPage(), dto.getPageSize());
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 0);
        queryWrapper.like(ObjectUtil.isNotEmpty(dto.getName()), "name", dto.getName());
        queryWrapper.like(ObjectUtil.isNotEmpty(dto.getHome()), "home", dto.getHome());
        queryWrapper.eq(ObjectUtil.isNotEmpty(dto.getAge()), "age", dto.getAge());
        queryWrapper.like(ObjectUtil.isNotEmpty(dto.getRemark()), "remark", dto.getRemark());

        if (ObjectUtil.isNotEmpty(dto.getOrderType()) && ObjectUtil.isNotEmpty(dto.getOrderColumn())) {
            if (dto.getOrderType().equals(1)) {
                queryWrapper.orderByDesc(dto.getOrderColumn());
            } else {
                queryWrapper.orderByAsc(dto.getOrderColumn());
            }
        } else {
            queryWrapper.orderByDesc("create_time");
        }

        return this.page(page, queryWrapper);
    }

    @Override
    // 新增人员的校验：新增的人名字不能和数据库中已经有的人名字重复
    public boolean create(PeopleDTO dto) throws Exception {
        // 校验名字是否重复
        if (lambdaQuery()
                .eq(People::getName, dto.getName())
                .eq(People::getDelFlag, 0)
                .count() > 0) {
            throw new BusinessException(400, "人员姓名已存在，请使用其他姓名");
        }

        People entity = BeanUtil.copyProperties(dto, People.class);
        return save(entity);
    }

    @Override
    // 导入Excel数据，并保存到数据库
    public String importData(MultipartFile file) {
        try {
            List<People> userInfoList = importExcel(file);

            // 处理导入的数据，例如保存到数据库
            saveBatch(userInfoList);
            System.out.println("导入数据成功，共" + userInfoList.size() + "条");
            return "导入成功";
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(500, "导入失败：" + e.getMessage());
        }
    }

    public List<People> importExcel(MultipartFile file) throws Exception {
        try (InputStream inputStream = file.getInputStream()) {
            // 获取ExcelReader对象，指定表头所在行（默认0行，即第一行）
            ExcelReader reader = ExcelUtil.getReader(inputStream);
            // 读取数据并转换为实体类列表
            List<UserInfo> userInfoList = reader.readAll(UserInfo.class);
            List<People> peopleList = BeanUtil.copyToList(userInfoList, People.class);
            return peopleList;
        }
    }
}
