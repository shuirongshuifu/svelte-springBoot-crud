### 🎨 技术栈类比

| Spring Boot 生态 | Node.js 对应技术 | 说明 |
|------------------|-----------------|------|
| **JDK 8** | Node.js | ⚙️ 运行环境，学Java装JDK，就像学JS装Node |
| **Spring && Spring MVC** | Express/Koa/Fastify | 🚀 后端基础框架，快速搭建应用服务 |
| **Spring Boot 2.7.18** | Egg.js/Nest.js 或 Express/Koa/Fastify + 一堆插件 | 🚀 后端进阶完善的框架，可开箱即用 |
| **MyBatis-Plus 3.5.3.1** | Sequelize/Prisma | 🗄️ ORM框架，简化数据库操作，不用手搓sql了 |
| **Swagger 3.0.0 + Knife4j 3.0.3** | swagger-ui-express | 📖 API文档自动生成 |
| **Hutool 5.8.22** | lodash/day.js | 🛠️ 工具库，提供各种实用函数 |
| **Apache POI 4.1.2** | node-xlsx / xlsx | 📊 Excel文件处理，导入导出解析excel的数据 |
| **数据库驱动（JDBC Driver）** | mysql或者mysql2 | 🔌 数据库连接 |
| **HikariCP** | mysql或者mysql2内置的连接池 | 🔌 数据库连接池，管理数据库连接 |

> - Maven 就像 npm，`pom.xml` 就是 `package.json`，依赖管理方式几乎一样！
> - Java 的包管理比 npm 更严格，但概念相同
> - Spring Boot 的注解就像 Vue的自定义指令

## 主要功能

### 1. 人员信息管理
- ✅ 新增人员信息
- ✅ 修改人员信息
- ✅ 删除人员信息（支持批量删除）
- ✅ 根据ID查询人员详情
- ✅ 分页查询人员列表

### 2. 高级查询功能
- 🔍 姓名模糊搜索
- 📊 分页显示（支持自定义页码和每页条数）
- 🔄 排序功能（支持升序/降序，按年龄、更新时间等字段排序）

### 3. 数据导入导出
- 📥 Excel文件数据导入
- 🔄 自动数据验证和错误处理

### 4. 系统特性
- 🗑️ 逻辑删除（软删除）
- ⏰ 自动记录创建时间和更新时间
- 📝 完整的API文档——Swagger文档

## 项目结构

### 📁 分层架构详解（前端开发者视角）

```yml
src/main/java/com/people_sys/people/
├── config/                 # 配置类（相当于前端的 config/ 或 utils/config.js）
│   ├── ApiResponse.java    # API响应封装（统一返回{ "code": 200, "message": "success", "data": ... } —— 类似前端的 response interceptor）
│   ├── BusinessException.java  # 业务异常（类似返回{ "code": 4001, "message": "用户不存在", "data": null } —— 自定义错误类，类似前端的 Error 类）
│   ├── GlobalExceptionHandler.java  # 全局异常处理（类似Vue的全局报错捕获钩子 app.config.errorHandler = (err, instance, info) => { ... } ）
│   ├── MyBatisPlusConfig.java  # MyBatis-Plus配置（类似app.use(xxx)注册中间件/插件以便于在项目中使用 —— 相当于前端的 plugin 系统）
│   ├── MyMetaObjectHandler.java  # 自动填充处理器（自动添加创建时间、更新时间的填充值 —— 类似前端的 mixin 或 hooks）
│   └── SwaggerConfig.java   # Swagger配置（类似swagger-ui-express和swagger-jsdoc —— 前端API文档生成）
├── controller/             # 控制器层（重点关注：类似Express的路由 app.get/app.post，定义前端请求入口）
│   └── PeopleController.java # 路由控制器（相当于前端的 router.js + API service 合体）
├── dto/                   # 数据传输对象（DTO：Data Transfer Object —— 相当于前端的 interface 或 TypeScript type 定义）
│   ├── PeopleDTO.java     # 新增人员DTO（请求体类型定义，类似前端API请求的参数类型）
│   ├── PeopleUpdateDTO.java # 编辑人员DTO（多了一个id）
│   ├── PeoplePageDTO.java # 分页查询DTO（查询参数类型定义，类似前端分页组件的props）
│   └── UserInfo.java      # Excel导入DTO（Excel数据导入时的数据传输对象）
├── entity/                # 实体类（数据库表结构映射 —— 相当于前端的 Model 或数据库 Schema 定义）
│   └── People.java        # 人员实体（一条记录的类型定义，类似前端的数据库模型——Sequelize）
├── mapper/                # 数据访问层（DAO层 —— 相当于前端的 database query functions 或 API 调用函数）
│   └── PeopleMapper.java  # 数据访问接口（定义数据库操作方法，类似前端的 db.query() 函数声明）
├── service/               # 业务逻辑层（核心业务处理 —— 相当于前端的 service/ 或 utils/ 业务逻辑）
│   ├── PeopleService.java # 业务接口（定义业务方法，就像前端的 service 接口或业务逻辑抽象）
│   └── impl/              # 实现类（相当于前端的 service 实现）
│       └── PeopleServiceImpl.java # 业务实现（具体的业务逻辑实现，类似前端的 service 方法体）
└── PeopleApplication.java # 启动类（相当于前端的 main.js 或 app.js，应用的入口文件）
```

> 🔍 **前端学习重点**: 从上到下看代码结构，就像前端的页面请求流程：
> 1. **Controller**（路由层）← 类似前端的 `router.js` 或 API service
> 2. **Service**（业务逻辑）← 类似前端的业务处理函数
> 3. **Mapper**（数据访问）← 类似前端的数据库查询函数
> 4. **Entity**（数据模型）← 类似前端的 TypeScript interface

## 数据库设计

### t_people 表结构

| 字段名 | 类型 | 说明 | 约束 |
|--------|------|------|------|
| id | INT | 主键ID | AUTO_INCREMENT 自增 |
| name | VARCHAR(50) | 姓名 | NOT NULL |
| age | INT | 年龄 | - |
| home | VARCHAR(255) | 家庭地址 | - |
| remark | VARCHAR(255) | 备注 | - |
| del_flag | TINYINT | 逻辑删除标记 | DEFAULT 0 |
| create_time | DATETIME | 创建时间 | - |
| update_time | DATETIME | 更新时间 | - |

## API接口

### 基础CRUD接口

| 接口路径 | 请求方法 | 功能描述 |
|----------|----------|----------|
| `/people` | POST | 新增人员 |
| `/people` | PUT | 修改人员 |
| `/people?ids=1,2,3` | DELETE | 删除人员（批量） |
| `/people/{id}` | GET | 根据ID查询人员详情 |
| `/people/page` | GET | 分页查询人员列表 |
| `/people/import` | POST | Excel数据导入 |

> ⚠️ **JSON格式注意事项**: 发送JSON数据时，最后一个字段后不要加逗号。JavaScript对象允许尾随逗号，但JSON标准不允许。

### 分页查询参数

```json
{
  "name": "张三",           // 姓名筛选（可选）
  "currentPage": 1,       // 当前页码
  "pageSize": 10,         // 每页条数
  "orderColumn": "update_time",  // 排序字段
  "orderType": 1          // 排序类型：0升序，1降序
}
```

## 环境要求

- JDK 8+
- Maven 3.6+
- MySQL 5.7+

## 快速开始

### 🚀 前端开发者特别提醒
- **Maven = npm**: `pom.xml` 就是 `package.json`，依赖管理完全一样
- **JDK = Node.js**: 都需要安装运行环境
- **端口默认8080**: 和前端 dev server 一样，可以设置服务端口
- **包名**: 就像前端的目录结构，`com.people_sys.people` 类似 `@/utils/api`
- **注解**: `@RestController` —— 框架自带的约定的语法，只要写框架就做对应处理

### 1. 克隆项目
```bash
git clone .../java-people-manage.git
cd java-people-manage
```

### 2. 数据库配置
在MySQL中创建名为person_manage的数据库：
```sql
CREATE DATABASE person_manage CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

在person_manage数据库中，使用sql语句创建一张名为t_people的表
```sql
-- person_manage.t_people definition

CREATE TABLE `t_people` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '一条数据的唯一标识',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名字字段',
  `age` int DEFAULT NULL COMMENT '年龄字段',
  `home` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '家乡字段',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注字段',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '逻辑删除字段，0代表为未删除，1代表已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=295 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT;
```

### 3. 修改配置文件
编辑 `src/main/resources/application.yml` 中的数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/person_manage
    username: username
    password: password
```

### 4. 运行项目

> 💻 **前端开发者命令对照表**:
> - ``mvn spring-boot:run` = `npm run dev` （开发模式启动）
> - `mvn package` = `npm run build` （编译打jar包 / 打dist 包 ）

**控制台启动成功标志：**

```cmd
2025-11-07 ...... Tomcat started on port(s): 8080 (http) with context path ''
2025-11-07 ...... Started PeopleApplication in 6.423 seconds (JVM running for 8.692)
```

### 5. 访问API文档
启动成功后，访问API文档：
- Swagger UI: http://localhost:8080/doc.html
- 或者 http://localhost:8080/swagger-ui/index.html#/people-controller

## 开发说明

### 🎓 前端开发者学习路径建议

**学习顺序推荐**（按前端思维理解）：
1. **第一步**: 先看 `PeopleController.java` （🎯 重点！路由接口，类似前端的 API service 或 axios 配置）
2. **第二步**: 看 `PeopleService.java` 和实现类 （业务逻辑，类似前端的业务处理函数）
3. **第三步**: 看 `PeopleMapper.java` （数据访问，类似前端调用数据库API）
4. **第四步**: 看 `People.java` （数据模型，类似前端的 TypeScript interface）
5. **第五步**: 研究配置类 （了解Spring Boot的配置方式，类似前端的配置文件）

**核心概念类比**（前端视角）：

- `@RestController` = Express 的 `app.get/post` （路由定义）
- `@Autowired` = `import` + 自动依赖注入 （Spring自动创建实例）
- `@RequestBody` = `req.body` （POST请求体参数）
- `@PathVariable` = 路由参数，如 `/user/:id` （URL路径中的参数）
- `@RequestParam` = 查询参数，如 `?name=张三` （URL查询字符串）
- `@RequestMapping` = 路由分组，类似 Express 的 `app.use('/api')`

**学习技巧**：

- 🔍 先跑通项目，看API文档，理解接口调用
- 📝 多看代码注释，里面有依赖前端类比说明
- 🧪 修改接口试试，理解前后端联调
- 📚 遇到不懂的，搜索一下相关知识点

### 扩展需求
- 🔐 用户权限管理（Spring Security，类似前端的路由守卫）
- ⚡ Redis缓存集成（提升性能，类似前端的状态管理）
- 等...
