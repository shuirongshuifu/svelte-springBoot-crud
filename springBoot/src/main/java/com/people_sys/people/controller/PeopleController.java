/*
定义接口路由，类似前端的router.js + API service的组合
同时做参数校验，返回数据格式统一
*/ 
package com.people_sys.people.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.people_sys.people.config.ApiResponse;
import com.people_sys.people.config.BusinessException;
import com.people_sys.people.dto.PeopleDTO;
import com.people_sys.people.dto.PeoplePageDTO;
import com.people_sys.people.dto.PeopleUpdateDTO;
import com.people_sys.people.entity.People;
import com.people_sys.people.service.PeopleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController // REST风格的接口
@RequestMapping("/people") // 接口请求url 统一请求前缀/people
@CrossOrigin // 允许跨域请求
public class PeopleController {

    private final PeopleService peopleService;

    // 构造器注入
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    // 新增人员 - POST /people
    @PostMapping
    public ApiResponse<Boolean> create(@Valid @RequestBody PeopleDTO people) throws Exception {
        boolean result = peopleService.create(people);
        return ApiResponse.success(result);
    }

    // 修改人员 - PUT /people
    @PutMapping
    public ApiResponse<Boolean> update(@Valid @RequestBody PeopleUpdateDTO dto) {
        People people = BeanUtil.copyProperties(dto, People.class);
        boolean result = peopleService.updateById(people);
        if (!result) {
            throw new BusinessException(404, "更新失败，人员不存在或数据未变更");
        }
        return ApiResponse.success(true);
    }

    // 删除人员 - DELETE /people?ids=1,2,3
    @DeleteMapping
    public ApiResponse<Boolean> delete(@RequestParam("ids") List<Integer> ids) {
        boolean result = peopleService.removeBatchByIds(ids);
        if (!result) {
            throw new BusinessException(500, "删除失败");
        }
        return ApiResponse.success(true);
    }

    // 根据id查询（返回entity所有数据方式） - GET /people/{id}
    @GetMapping("/{id}")
    public ApiResponse<People> getById(@PathVariable Integer id) {
        People people = peopleService.getById(id);
        if (people == null) {
            throw new BusinessException(404, "人员不存在");
        }
        // 直接返回entity方式
        return ApiResponse.success(people);
    }

    // // 根据id查询（返回dto部分数据方式） - GET /people/{id}
    // @GetMapping("/{id}")
    // public ApiResponse<PeopleDTO> getById(@PathVariable Integer id) {
    //     People people = peopleService.getById(id);
    //     if (people == null) {
    //         throw new BusinessException(404, "人员不存在");
    //     }
    //     // Entity → DTO转换，避免直接返回Entity，因为Entity可能包含一些敏感信息，比如密码
    //     PeopleDTO dto = new PeopleDTO();
    //     BeanUtil.copyProperties(people, dto);
    //     return ApiResponse.success(dto);
    // }

    // 分页查询 - GET /people/page
    @GetMapping("/page")
    public ApiResponse<IPage<People>> getPage(@Valid PeoplePageDTO dto) {
        IPage<People> page = peopleService.selectPage(dto);
        return ApiResponse.success(page);
    }

    // 导入Excel - POST /people/import
    @PostMapping("/import")
    public ApiResponse<String> importData(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new BusinessException(400, "请选择要上传的文件");
        }
        String result = peopleService.importData(file);
        return ApiResponse.success(result);
    }
}
