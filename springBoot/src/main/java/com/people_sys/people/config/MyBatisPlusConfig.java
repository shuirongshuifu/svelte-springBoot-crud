
package com.people_sys.people.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 告诉Spring这是一个配置类，类似前端的配置文件（如vite.config.js）或Express的app.use()注册中间件
@Configuration 
public class MyBatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;

//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
////        paginationInterceptor.setReasonable(false); // 禁用合理化，页码超出范围返回空
//        interceptor.addInnerInterceptor(paginationInterceptor);
//        return interceptor;
    }
}
