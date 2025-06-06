# 🧠 Text-to-UI 智能页面生成系统

本系统是一个基于Spring Boot的智能UI页面生成引擎，支持用户通过自然语言描述生成结构化的HTML页面，并提供完整的任务追踪和用户评价反馈功能。

## 🚀 功能特性

- ✨ **文本生成页面**: 通过自然语言描述自动生成HTML页面结构
- 🧠 **智能组件生成**: 支持地址选择器等复杂UI组件的自动生成
- 📊 **执行链追踪**: 完整记录页面生成过程中的每步操作（模型调用、耗时、状态等）
- ⭐ **用户评价系统**: 支持用户对生成结果进行评分和反馈建议
- 🌐 **RESTful API**: 提供完整的后端API服务
- 📖 **Swagger文档**: 自动生成API接口文档
- 🖥️ **Web界面**: 简洁直观的前端操作界面
- 🗄️ **数据持久化**: 基于H2内存数据库的任务和评价数据存储

## 🧱 技术栈

### 后端技术
- **Java 21** - 核心开发语言
- **Spring Boot 3.x** - 应用框架
- **Spring Web** - Web服务框架
- **Spring Data JPA** - 数据访问层
- **H2 Database** - 内存数据库
- **Springdoc OpenAPI** - API文档生成

### 前端技术
- **HTML5 + JavaScript** - 基础前端技术
- **原生Ajax** - 异步数据交互

### 构建工具
- **Maven** - 项目构建和依赖管理

## 📦 快速开始

### 环境要求
- Java 21 或更高版本
- Maven 3.6+ 

### 安装和启动

1. **克隆项目**
```bash
git clone <your-repository-url>
cd ai_project_demo
```

2. **构建项目**
```bash
mvn clean install
```

3. **启动应用**
```bash
mvn spring-boot:run
```

4. **访问应用**
- 🌐 **主页面**: http://localhost:8080/index.html
- 📖 **API文档**: http://localhost:8080/swagger-ui.html
- 🗄️ **数据库控制台**: http://localhost:8080/h2-console

### H2数据库连接信息
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **用户名**: `sa`
- **密码**: （留空）

## 🔗 核心API接口

### 1. 页面生成接口
```http
POST /api/page/render
Content-Type: application/json

{
  "prompt": "生成一个包含四级地址选择器的页面"
}
```

**响应示例**:
```json
{
  "result": "<div><label>地址选择：</label><select><option>北京</option></select></div>",
  "metadata": {
    "model": "mock-mcp-ui",
    "latency": 123
  }
}
```

### 2. 任务查询接口
```http
GET /api/job/{sessionId}
```
获取指定会话的所有执行任务记录

### 3. 用户评价接口
```http
POST /api/eval/submit
Content-Type: application/json

{
  "pageId": 1,
  "sessionId": "session-123",
  "score": 5,
  "comment": "生成的页面很好用"
}
```

## 📌 项目结构

```
src/main/java/com/example/uiagent/
├── Application.java              # Spring Boot 启动类
├── controller/                   # 控制器层
│   ├── PageController.java       # 页面生成控制器
│   ├── JobController.java        # 任务查询控制器
│   └── EvalController.java       # 评价提交控制器
├── model/                        # 数据模型
│   ├── JobEntity.java            # 任务实体类
│   ├── EvalEntity.java           # 评价实体类
│   ├── MCPRequest.java           # MCP请求模型
│   └── MCPResponse.java          # MCP响应模型
├── repository/                   # 数据访问层
│   ├── JobRepository.java        # 任务数据访问
│   └── EvalRepository.java       # 评价数据访问
└── config/                       # 配置类
    └── SwaggerConfig.java        # Swagger配置

src/main/resources/
├── static/                       # 静态资源
│   └── index.html               # 主页面
├── templates/                    # 页面模板
│   └── result.html              # 结果展示模板
└── application.yml              # 应用配置文件
```

## 🎯 使用示例

### 基本页面生成
1. 访问 http://localhost:8080/index.html
2. 在输入框中输入页面描述，如："生成一个用户注册表单"
3. 点击"生成页面"按钮
4. 查看生成的HTML页面结构

### API调用示例
```javascript
// 生成页面
const response = await fetch('/api/page/render', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ 
    prompt: "生成一个包含姓名、邮箱、电话的联系表单" 
  })
});
const result = await response.json();
console.log(result.result); // 生成的HTML代码
```

## 📊 数据模型说明

### JobEntity (任务实体)
- `sessionId`: 会话ID
- `stepName`: 执行步骤名称
- `modelName`: 使用的模型名称
- `inputPrompt`: 输入提示
- `outputResult`: 输出结果
- `latencyMs`: 执行耗时(毫秒)
- `status`: 执行状态

### EvalEntity (评价实体)
- `pageId`: 页面ID
- `sessionId`: 会话ID
- `score`: 评分(1-5)
- `comment`: 评价建议
- `timestamp`: 评价时间

## 🔧 配置说明

### 应用配置 (application.yml)
- **服务端口**: 8080
- **数据库**: H2内存数据库
- **LLM配置**: 支持阿里云大模型API配置

### 自定义配置
如需接入真实的大语言模型，请在 `application.yml` 中配置：
```yaml
llm:
  ali:
    api-key: YOUR_ALI_API_KEY
    endpoint: https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation
```

## 🚧 开发计划

### 已实现功能
- ✅ 基础页面生成框架
- ✅ 任务执行链追踪
- ✅ 用户评价反馈系统
- ✅ RESTful API设计
- ✅ Swagger API文档

### 后续扩展计划
- 🔄 集成真实大语言模型API
- 🎨 React前端重构
- 💾 Redis缓存和会话管理
- 🔍 向量数据库和RAG检索
- 📈 更丰富的UI组件库
- 🔐 用户认证和权限管理

## 🤝 贡献指南

1. Fork 本项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 📞 联系方式

如有问题或建议，请提交 Issue 或联系项目维护者。
