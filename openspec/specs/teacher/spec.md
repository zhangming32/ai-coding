# teacher Specification

## Purpose
教师功能模块，包括个人信息查看、课程管理、学生管理和评分功能。

## Requirements

### Requirement: 教师个人信息查看
系统 SHALL 允许教师查看个人信息。

#### Scenario: 查看教师信息
- GIVEN 教师已登录
- WHEN 教师请求个人信息页面
- THEN 显示工号、姓名

### Requirement: 教师课程列表查看
系统 SHALL 展示教师自己开设的课程列表并支持分页。

#### Scenario: 查看我的课程列表
- GIVEN 教师已登录
- WHEN 教师请求课程列表(page参数)
- THEN 只显示该教师开设的课程
- AND 显示课程名称、容量、已选人数
- AND 支持分页显示

### Requirement: 课程新增
系统 SHALL 允许教师新增课程并设置学院限制。

#### Scenario: 新增课程页面
- GIVEN 教师请求新增课程
- WHEN 系统返回新增课程页面
- THEN 显示所有学院列表供选择

#### Scenario: 新增课程成功
- GIVEN 教师输入课程名称、容量
- AND 选择学院限制
- WHEN 教师提交新增课程请求
- THEN 创建新课程记录
- AND 设置课程教师为当前教师
- AND 初始已选人数为0
- AND 创建学院限制记录
- AND 显示更新后的课程列表

#### Scenario: 设置学院限制
- GIVEN 教师新增或编辑课程
- WHEN 教师选择学院限制
- THEN 为课程创建多条Course_limit记录
- AND 每条记录包含classId和insId

### Requirement: 课程编辑
系统 SHALL 允许教师编辑已有课程信息。

#### Scenario: 编辑课程页面
- GIVEN 教师请求编辑课程(courseid)
- WHEN 系统返回编辑页面
- THEN 显示课程当前信息
- AND 显示已选学院限制
- AND 显示所有学院列表

#### Scenario: 编辑课程成功
- GIVEN 教师修改课程信息
- WHEN 教师提交更新请求
- THEN 更新课程信息
- AND 更新学院限制
- AND 显示更新后的课程列表

#### Scenario: 更新学院限制
- GIVEN 教师编辑课程学院限制
- WHEN 系统处理更新
- THEN 先删除原有学院限制
- AND 再插入新的学院限制记录

### Requirement: 课程删除
系统 SHALL 允许教师删除课程及相关数据。

#### Scenario: 删除课程
- GIVEN 教师确认删除课程
- WHEN 教师提交删除请求(courseid)
- THEN 删除课程记录
- AND 删除所有相关选课记录
- AND 删除所有学院限制记录
- AND 显示更新后的课程列表

#### Scenario: 关联数据清理
- GIVEN 课程被删除
- WHEN 系统处理删除
- THEN Course_choose表中该课程记录被删除
- AND Course_limit表中该课程记录被删除

### Requirement: 选课学生管理
系统 SHALL 允许教师查看和管理选课学生。

#### Scenario: 查看选课学生列表
- GIVEN 课程有学生选课
- WHEN 教师请求课程详情(courseid, page)
- THEN 显示选课学生列表
- AND 显示学生学号、姓名、成绩
- AND 支持分页显示

#### Scenario: 搜索选课学生
- GIVEN 教师输入学生学号
- WHEN 教师请求搜索学生(stuid, courseid)
- THEN 显示匹配的学生信息

#### Scenario: 删除学生选课记录
- GIVEN 教师确认删除学生选课
- WHEN 教师提交删除请求(stuid, courseid)
- THEN 删除选课记录
- AND 课程已选人数减少
- AND 显示更新后的学生列表

### Requirement: 学生评分
系统 SHALL 允许教师为选课学生评分。

#### Scenario: 评分成功
- GIVEN 学生已选择该课程
- WHEN 教师提交评分(courseid, stuId, score)
- THEN 更新学生成绩
- AND 显示更新后的学生列表(含新成绩)

#### Scenario: 成绩范围
- GIVEN 教师评分
- WHEN 系统接收成绩值
- THEN 成绩为整数类型
- AND 默认成绩为0

### Requirement: 教师首页访问
系统 SHALL 提供教师功能入口页面。

#### Scenario: 教师首页
- GIVEN 教师登录成功
- WHEN 教师访问教师首页
- THEN 显示教师功能入口