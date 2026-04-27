# auth Specification

## Purpose
用户认证和会话管理，包括登录、注销和密码修改功能。

## Requirements

### Requirement: 用户登录认证
系统 SHALL 支持学生和教师通过账号密码登录。

#### Scenario: 学生登录成功
- GIVEN 学生账号存在且密码正确
- WHEN 学生提交登录表单(userid, userpass)
- THEN 系统验证身份返回 1
- AND Session 存储 stuid 和 username
- AND 重定向到学生首页

#### Scenario: 教师登录成功
- GIVEN 教师账号存在且密码正确
- WHEN 教师提交登录表单(userid, userpass)
- THEN 系统验证身份返回 2
- AND Session 存储 teaid 和 username
- AND 重定向到教师首页

#### Scenario: 登录失败
- GIVEN 账号不存在或密码错误
- WHEN 用户提交登录表单
- THEN 系统返回 0
- AND 显示"密码错误"提示
- AND 返回登录页面

#### Scenario: 用户身份识别
- GIVEN 用户提交登录请求
- WHEN 系统检查用户ID格式
- THEN ID第5位为'1'识别为教师
- AND ID第5位不为'1'识别为学生

### Requirement: 用户会话管理
系统 MUST 管理用户会话状态。

#### Scenario: 会话创建
- GIVEN 用户登录成功
- WHEN 系统创建会话
- THEN Session 包含用户身份标识
- AND Session 包含用户名称

#### Scenario: 会话销毁
- GIVEN 用户已登录
- WHEN 用户点击注销
- THEN Session 被销毁(invalidate)
- AND 重定向到登录页面

### Requirement: 密码修改
系统 SHALL 支持用户修改密码。

#### Scenario: 学生修改密码成功
- GIVEN 学生已登录且原密码正确
- WHEN 学生提交原密码和新密码
- THEN 系统验证原密码通过
- AND 更新学生密码
- AND 显示个人信息页面

#### Scenario: 教师修改密码成功
- GIVEN 教师已登录且原密码正确
- WHEN 教师提交原密码和新密码
- THEN 系统验证原密码通过
- AND 更新教师密码
- AND 显示个人信息页面

#### Scenario: 原密码错误
- GIVEN 用户已登录但原密码错误
- WHEN 用户提交修改密码请求
- THEN 系统验证失败
- AND 显示"原始密码输入错误!"提示
- AND 返回修改密码页面

### Requirement: 登录页面访问
系统 SHALL 提供登录页面。

#### Scenario: 访问登录页面
- GIVEN 用户未登录或已注销
- WHEN 用户请求 /login
- THEN 返回登录页面(login.jsp)

### Requirement: 用户实体数据模型
系统 SHALL 维护用户完整数据属性。

#### Scenario: 用户完整属性
- GIVEN 系统需要存储用户信息
- WHEN 用户数据模型定义
- THEN 包含 id(用户ID)
- AND 包含 email(邮箱)
- AND 包含 password(密码)
- AND 包含 username(用户姓名)
- AND 包含 role(角色：student/teacher)
- AND 包含 status(状态：0启用/1禁用)
- AND 包含 regTime(注册时间)
- AND 包含 regIp(注册IP)