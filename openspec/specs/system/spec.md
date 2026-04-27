# system Specification

## Purpose
系统级规范，包括架构设计、数据管理、安全控制和分页功能。

## Requirements

### Requirement: 三层架构设计
系统 SHALL 采用三层架构模式。

#### Scenario: 表示层职责
- GIVEN HTTP请求到达
- WHEN Controller处理请求
- THEN 返回视图响应
- AND 调用Service层方法

#### Scenario: 业务逻辑层职责
- GIVEN Service接收请求
- WHEN 处理业务逻辑
- THEN 执行业务规则验证
- AND 调用DAO层数据访问
- AND 返回处理结果

#### Scenario: 数据访问层职责
- GIVEN DAO接收数据请求
- WHEN 执行数据库操作
- THEN 通过MyBatis执行SQL
- AND 返回数据结果

### Requirement: 会话管理
系统 MUST 使用Session管理用户状态。

#### Scenario: Session属性存储
- GIVEN 用户登录成功
- WHEN 系统创建Session
- THEN 存储 username(用户名称)
- AND 存储身份标识(teaid或stuid)

#### Scenario: Session属性读取
- GIVEN 用户已登录
- WHEN Controller处理请求
- THEN 从Session获取用户身份
- AND 验证用户权限

#### Scenario: Session销毁
- GIVEN 用户注销
- WHEN 调用invalidate()
- THEN Session所有属性被清除
- AND 用户状态变为未登录

### Requirement: 用户记录

系统SHALL通过同一个用户表记录所有角色信息。

#### Scenario：教师注册

- GIVEN User模型、教师识别
- WHEN ID第5位为'1'
- THEN 注册信息记录到用户信息、教师信息

#### Scenario：学生记录

- GIVEN User模型、学生识别
- WHEN ID第5位为'0'
- THEN 注册信息记录到用户信息、学生信息

### Requirement: 角色识别
系统 SHALL 通过用户ID格式识别角色。

#### Scenario: 教师识别
- GIVEN 用户ID格式
- WHEN ID第5位为'1'
- THEN 用户角色为教师

#### Scenario: 学生记录
- GIVEN 用户ID格式
- WHEN ID第5位不为'1'
- THEN 用户角色为学生

### Requirement: 分页功能
系统 SHALL 提供数据分页展示功能。

#### Scenario: 分页参数处理
- GIVEN 请求包含page参数
- WHEN PageService处理分页
- THEN 返回Page对象
- AND Page包含当前页数据列表
- AND Page包含分页元数据

#### Scenario: 分页元数据
- GIVEN Page对象
- WHEN 系统返回分页结果
- THEN 包含currentPage(当前页)
- AND 包含totalPage(总页数)
- AND 包含pageSize(每页大小，默认6)
- AND 包含totalNum(总记录数)

#### Scenario: 分页默认值配置
- GIVEN 系统配置
- WHEN Page对象初始化
- THEN pageSize默认值为6
- AND 支持泛型Page<T>

#### Scenario: 分页数据切片
- GIVEN 数据列表和页码
- WHEN 系统执行分页
- THEN 返回指定页的数据切片

### Requirement: 数据库连接池
系统 MUST 使用连接池管理数据库连接。

#### Scenario: C3P0连接池配置
- GIVEN 数据库连接配置
- WHEN 系统初始化连接池
- THEN 配置最大连接数(maxPoolSize)
- AND 配置最小连接数(minPoolSize)
- AND 配置连接增量(acquireIncrement)

#### Scenario: 连接获取与释放
- GIVEN 数据库操作请求
- WHEN DAO执行SQL
- THEN 从连接池获取连接
- AND 操作完成后释放连接

### Requirement: 权限控制
系统 SHALL 实施基本权限控制。

#### Scenario: 学生数据隔离
- GIVEN 学生用户
- WHEN 学生操作数据
- THEN 只能操作自己的选课记录
- AND 只能查看自己的信息

#### Scenario: 教师数据隔离
- GIVEN 教师用户
- WHEN 教师操作数据
- THEN 只能管理自己的课程
- AND 只能给选自己课程的学生评分

### Requirement: 视图解析
系统 SHALL 配置视图解析器。

#### Scenario: JSP视图解析
- GIVEN Controller返回视图名
- WHEN 视图解析器处理
- THEN 添加前缀 /WEB-INF/views/
- AND 添加后缀 .jsp
- AND 返回完整视图路径

### Requirement: 依赖注入
系统 SHALL 使用Spring IoC管理依赖。

#### Scenario: Controller依赖注入
- GIVEN Controller类
- WHEN Spring初始化
- THEN 注入Service实例
- AND 使用@Resource注解标记

#### Scenario: Service依赖注入
- GIVEN Service实现类
- WHEN Spring初始化
- THEN 注入DAO实例
- AND 使用@Autowired注解标记

### Requirement: 数据实体映射
系统 SHALL 使用MyBatis映射数据实体。

#### Scenario: 实体类与表映射
- GIVEN Java实体类
- WHEN MyBatis配置映射
- THEN 属性映射到数据库字段
- AND 支持基本类型转换

#### Scenario: SQL映射配置
- GIVEN DAO接口方法
- WHEN MyBatis XML配置
- THEN 定义SQL语句
- AND 定义参数映射
- AND 定义结果映射

### Requirement: 错误处理
系统 SHOULD 提供基本错误提示。

#### Scenario: 登录错误提示
- GIVEN 登录验证失败
- WHEN 返回登录页面
- THEN 显示错误消息(msg属性)

#### Scenario: 操作错误提示
- GIVEN 操作验证失败
- WHEN 返回原页面
- THEN 显示错误消息
- AND 保持页面状态