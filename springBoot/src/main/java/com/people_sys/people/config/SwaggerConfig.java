/*
SwaggerConfig.java 用于配置Swagger文档，生成API文档
*/ 
package com.people_sys.people.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi // Swagger 3.x
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .enable(true) // 确保启用Swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.people_sys.people.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
