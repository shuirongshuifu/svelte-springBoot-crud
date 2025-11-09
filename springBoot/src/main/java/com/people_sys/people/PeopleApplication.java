// 启动类
// 启动类是Spring Boot应用程序的入口点。它负责启动Spring容器，加载配置，并启动应用程序。
// 它使用@SpringBootApplication注解来标识这是一个Spring Boot应用程序
// （@SpringBootApplication已包含组件扫描功能，显式的@ComponentScan可以删除）
// 它使用SpringApplication.run方法来启动应用程序。

/**
 * 相当于前端项目中package.json里配置的"main": "index.js"（指定程序的入口文件），
 * 或者 Vue/React 项目中src/main.js（启动应用的入口）
 * */ 

package com.people_sys.people;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.people_sys.people")
public class PeopleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleApplication.class, args);
	}
}
