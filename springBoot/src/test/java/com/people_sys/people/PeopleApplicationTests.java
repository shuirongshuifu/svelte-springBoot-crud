// 测试类
// 用于验证Spring Boot应用的上下文能否正常加载，是项目基础配置的健康检查测试
// 使用@SpringBootTest注解标识这是一个Spring Boot测试类，会自动加载整个应用上下文
// 使用@Test注解标识这是一个Junit 5测试方法，执行时会触发上下文初始化

/**
 * 相当于前端项目中package.json里配置的"test": "jest"（运行测试），
 * 或者 Vue/React 项目中src/tests/unit/index.js（运行单元测试）
 * 
 * 中小项目团队后端一般写的不多，但能快速排查依赖冲突、配置文件错误等基础问题
 */

package com.people_sys.people;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PeopleApplicationTests {

	@Test
	void contextLoads() {
	}

}
