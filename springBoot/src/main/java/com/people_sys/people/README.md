## Java人员管理系统 - 架构分层详解

### 项目架构分层

| 层级 | 文件夹 | 核心作用 | 数据流向 |
|------|--------|----------|----------|
| 配置层 | config/ | 系统配置管理 | 启动时加载 |
| 接口层 | controller/ | HTTP请求处理 | 前端 → Controller → Service |
| 数据传输 | dto/ | 数据传输对象 | 前端数据校验转换 |
| 数据模型 | entity/ | 数据库映射 | Entity ↔ 数据库 |
| 数据访问 | mapper/ | SQL执行 | Service → Mapper → DB |
| 业务逻辑 | service/ | 业务处理 | Controller → Service → Mapper |

### 数据流完整图解

```bash
🌐 前端请求 (HTTP Request)
    ↓
📋 Controller层 - 请求入口
    ├── 接收HTTP请求 (@PostMapping, @GetMapping等)
    ├── 参数校验与绑定 (@Valid, @RequestBody)
    ├── 调用对应的Service方法
    ↓
🎯 Service层 - 业务逻辑处理
    ├── 参数校验 (业务规则验证)
    ├── 数据转换 (DTO → Entity)
    ├── 业务逻辑处理 (增删改查逻辑)
    ├── 异常处理 (try-catch, 自定义异常)
    ├── 调用Mapper层执行数据库操作
    ↓
💾 Mapper层 - 数据访问
    ├── MyBatis Plus自动生成SQL
    ├── 执行CRUD操作 (增删改查)
    ├── 处理分页、排序等查询
    ├── 返回查询结果或影响行数
    ↓
🗄️ 数据库层 - 数据持久化
    ├── MySQL数据库执行SQL——CRUD
    ├── 比如，逻辑删除处理 (del_flag字段)
    ├── 返回执行结果
    ↓
📤 响应封装与返回
    ├── Entity → DTO转换 (安全过滤)
    ├── 直接返回Entity（不优雅）
    ├── 统一响应格式封装 (ApiResponse)
    ├── 异常统一处理 (GlobalExceptionHandler)
    ├── 返回JSON数据给前端
    ↓
📱 前端接收展示 (HTTP Response)
```

### 关键设计原则——**可维护**、**可扩展**、**易测试**

- **分层隔离**：每层职责单一，互不干扰
- **依赖倒置**：高层不依赖低层，通过接口解耦
- **数据安全**：DTO隔离敏感字段，Entity专注数据映射
- **业务封装**：Service层统一处理业务规则和异常