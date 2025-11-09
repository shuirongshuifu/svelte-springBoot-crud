# 人员管理系统 - 前端

基于 Svelte 5 构建的现代化人员管理系统前端应用。

## 功能特性

- ✅ **人员列表展示** - 分页显示人员信息
- ✅ **条件搜索** - 支持按姓名、年龄、住址、备注进行多条件搜索
- ✅ **新增人员** - 添加新的人员记录，带表单验证
- ✅ **编辑人员** - 修改现有人员信息
- ✅ **批量删除** - 支持单个或批量删除人员记录
- ✅ **复选框选择** - 支持全选/单选，实时显示已选数量
- ✅ **响应式设计** - 适配桌面和移动设备
- ✅ **组件化架构** - 分页组件独立封装，易于复用

## 技术栈

- **框架**: Svelte 5
- **构建工具**: Vite 6
- **API**: Fetch API (调用后端 REST 接口)
- **状态管理**: Svelte 响应式系统

## 快速开始

### 环境要求

- Node.js 18+
- npm 或 yarn

### 安装依赖

```bash
npm install
```

### 启动开发服务器

```bash
npm run dev
```

应用将在 `http://localhost:5173` 启动

### 构建生产版本

```bash
npm run build
```

### 预览生产版本

```bash
npm run preview
```

## 项目结构

```
src/
├── pages/
│   ├── components/
│   │   ├── Page.svelte       # 分页组件
│   │   └── Page.css          # 分页组件样式
│   ├── api.js                # API 服务封装
│   ├── PeopleManagement.svelte  # 人员管理主组件
│   └── PeopleManagement.css     # 主组件样式
├── App.svelte                # 应用主组件
├── app.css                   # 全局样式（CSS 变量定义）
└── main.js                   # 应用入口
```

## API 接口

前端调用后端提供的 REST API 接口：

- `GET /people/page` - 分页查询人员
- `POST /people` - 创建人员
- `PUT /people` - 更新人员
- `DELETE /people?ids=1,2,3` - 删除人员
- `GET /people/{id}` - 根据ID查询人员详情

## 核心特性

### 1. 组件化设计
- **Page 组件**: 独立的分页组件，可在其他项目中复用
- **统一字段配置**: 搜索表单和编辑表单共享同一配置源
- **工厂函数**: 使用工厂函数统一管理初始状态

### 2. 状态管理
- 使用 Svelte 响应式系统，无需额外状态库
- 通过对象属性直接赋值触发更新（比 React 更简洁）
- `obj.prop = value` vs `setState({...obj, prop: value})`
- 辅助函数统一处理 Set 等复杂数据结构的更新

> 编译为原生 JavaScript，无虚拟 DOM 开销