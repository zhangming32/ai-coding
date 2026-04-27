# student Specification

## Purpose
学生功能模块，包括个人信息查看、选课、退课、课程查询和成绩查看。

## Requirements

### Requirement: 学生个人信息查看
系统 SHALL 允许学生查看个人信息。

#### Scenario: 查看学生信息
- GIVEN 学生已登录
- WHEN 学生请求个人信息页面
- THEN 显示学号、姓名、所属学院

### Requirement: 可选课程列表查看
系统 SHALL 展示所有可选课程列表并支持分页。

#### Scenario: 查看课程列表
- GIVEN 学生已登录
- WHEN 学生请求课程列表(page参数)
- THEN 显示所有课程信息
- AND 显示课程名称、教师、容量、已选人数
- AND 显示学院限制信息
- AND 已选课程标记为"已选"
- AND 支持分页显示

#### Scenario: 显示教师列表筛选
- GIVEN 学生请求课程列表
- WHEN 系统准备课程列表页面
- THEN 显示所有教师列表供筛选

#### Scenario: 显示学院列表筛选
- GIVEN 学生请求课程列表
- WHEN 系统准备课程列表页面
- THEN 显示所有学院列表供筛选

### Requirement: 课程详情查看
系统 SHALL 允许学生查看特定课程详情并检查学院限制。

#### Scenario: 学院限制匹配
- GIVEN 学生学院与课程限制匹配
- WHEN 学生请求课程详情(classId)
- THEN 显示课程完整信息
- AND 显示学院限制详情

#### Scenario: 学院限制不匹配
- GIVEN 学生学院与课程限制不匹配
- WHEN 学生请求课程详情(classId)
- THEN 显示警告"请注意课程的学院限制"
- AND 重定向到课程列表

### Requirement: 选课功能
系统 SHALL 允许符合条件的学生选择课程。

#### Scenario: 选课成功
- GIVEN 学生学院符合课程限制
- AND 课程未达到容量上限
- WHEN 学生确认选课
- THEN 创建选课记录
- AND 课程已选人数增加
- AND 课程标记为"已选"
- AND 显示更新后的课程列表

#### Scenario: 选课人数更新
- GIVEN 学生选课成功
- WHEN 系统处理选课
- THEN 课程classChooseNum字段增加1

### Requirement: 退课功能
系统 SHALL 允许学生退选已选课程。

#### Scenario: 退课成功
- GIVEN 学生已选择该课程
- WHEN 学生请求退课(courseid)
- THEN 删除选课记录
- AND 课程已选人数减少
- AND 显示更新后的课程列表

#### Scenario: 退课人数更新
- GIVEN 学生退课成功
- WHEN 系统处理退课
- THEN 课程classChooseNum字段减少1

### Requirement: 已选课程查看
系统 SHALL 允许学生查看已选课程列表。

#### Scenario: 查看已选课程
- GIVEN 学生已登录且有选课记录
- WHEN 学生请求已选课程列表
- THEN 显示已选课程列表
- AND 显示课程名称、教师、成绩

#### Scenario: 成绩显示
- GIVEN 学生有已选课程且已评分
- WHEN 学生查看已选课程列表
- THEN 显示课程对应成绩

#### Scenario: 无成绩显示
- GIVEN 学生有已选课程但未评分
- WHEN 学生查看已选课程列表
- THEN 成绩显示为默认值0

### Requirement: 课程搜索
系统 SHALL 支持多种方式搜索筛选课程。

#### Scenario: 按课程ID搜索
- GIVEN 学生输入课程ID
- WHEN 学生请求搜索(courseid)
- THEN 显示匹配的课程

#### Scenario: 按教师筛选
- GIVEN 学生选择教师
- WHEN 学生请求按教师筛选(teaid)
- THEN 显示该教师开设的所有课程

#### Scenario: 按学院筛选
- GIVEN 学生选择学院
- WHEN 学生请求按学院筛选(insid)
- THEN 显示限制该学院的课程列表

### Requirement: 学生首页访问
 系统 SHALL 提供学生功能入口页面。

#### Scenario: 学生首页
- GIVEN 学生登录成功
- WHEN 学生访问学生首页
- THEN 显示学生功能入口

### Requirement: 学生信息显示
 系统 SHALL 在课程列表和已选课程页面标题后显示当前登录学生的学号和学院信息。

#### Scenario: 课程列表显示学生信息
- GIVEN 学生已登录
- WHEN 学生访问课程列表页面
- THEN 页面标题后以小号字体显示学号和学院

#### Scenario: 已选课程显示学生信息
- GIVEN 学生已登录
- WHEN 学生访问已选课程页面
- THEN 页面标题后以小号字体显示学号和学院