# course Specification

## Purpose
课程管理核心功能，定义课程数据结构、选课规则和学院限制机制。

## Requirements

### Requirement: 课程数据结构
系统 SHALL 维护课程核心数据属性。

#### Scenario: 课程基本信息
- GIVEN 课程存在
- WHEN 系统查询课程
- THEN 课程包含classId(课程ID)
- AND 课程包含className(课程名称)
- AND 课程包含classNum(课程容量)
- AND 课程包含teaId(教师ID)
- AND 课程包含classChooseNum(已选人数)

#### Scenario: 课程扩展信息
- GIVEN 课程查询请求
- WHEN 系统返回课程信息
- THEN 课程包含teaName(教师姓名)
- AND 课程包含classLimitInsName(学院限制名称列表)
- AND 课程包含score(成绩)
- AND 课程包含isChoose(是否已选标记)

### Requirement: 课程容量管理
系统 MUST 确保课程已选人数不超过容量。

#### Scenario: 已选人数上限
- GIVEN 课程容量为N
- WHEN 学生选课
- THEN classChooseNum不得超过N

#### Scenario: 已选人数更新
- GIVEN 学生选课或退课
- WHEN 操作成功
- THEN classChooseNum相应增减

### Requirement: 学院限制机制
系统 SHALL 支持课程学院限制设置。

#### Scenario: 设置学院限制
- GIVEN 教师创建或编辑课程
- WHEN 设置学院限制
- THEN 创建Course_limit记录
- AND 每条记录包含limitId(自增主键)
- AND 每条记录关联classId和insId

#### Scenario: 多学院限制
- GIVEN 课程设置多个学院限制
- WHEN 系统存储限制信息
- THEN 创建多条Course_limit记录
- AND 学生属于任一限制学院即可选课

#### Scenario: 学院限制验证
- GIVEN 课程有学院限制
- AND 学生请求选课
- WHEN 系统验证学生学院
- THEN 学生学院必须在限制列表中
- AND 否则禁止选课

#### Scenario: 无学院限制
- GIVEN 课程未设置学院限制(limitList为空)
- WHEN 系统验证学生学院
- THEN 返回false(禁止选课)

### Requirement: 选课记录管理
系统 SHALL 维护选课记录数据。

#### Scenario: 选课记录结构
- GIVEN 学生选课成功
- WHEN 系统创建选课记录
-THEN 记录包含chooseId(记录ID)
- AND 记录包含stuId(学生ID)
- AND 记录包含classId(课程ID)
- AND 记录包含score(成绩，默认0)

#### Scenario: 选课记录唯一性
- GIVEN 学生已选某课程
- WHEN 学生再次选择同一课程
- THEN 课程标记为已选(isChoose=1)

### Requirement: 课程查询功能
系统 SHALL 支持多种课程查询方式。

#### Scenario: 查询教师课程
- GIVEN 教师ID
- WHEN 系统查询课程
- THEN 返回该教师开设的所有课程

#### Scenario: 查询所有课程
- GIVEN 学生请求课程列表
- WHEN 系统查询课程
- THEN 返回所有课程
- AND 标记学生已选课程(isChoose)

#### Scenario: 查询课程详情
- GIVEN 课程ID
- WHEN 系统查询课程详情
- THEN 返回课程完整信息
- AND 包含学院限制名称列表

#### Scenario: 查询学院限制课程
- GIVEN 学院ID
- WHEN 系统按学院筛选课程
- THEN 返回限制该学院的课程列表

### Requirement: 课程学院关系
系统 SHALL 维护课程与学院的关系映射。

#### Scenario: 查询课程学院限制ID
- GIVEN 课程ID
- WHEN 系统查询学院限制
- THEN 返回限制学院ID列表

#### Scenario: 查询课程学院限制名称
- GIVEN 课程ID
- WHEN 系统查询学院限制名称
- THEN 返回限制学院名称列表

### Requirement: 课程数据一致性
系统 MUST 保证课程相关数据的完整性。

#### Scenario: 删除课程时清理数据
- GIVEN 课程被删除
- WHEN 系统执行删除操作
- THEN 删除Course表中记录
- AND 删除Course_choose表中关联记录
- AND 删除Course_limit表中关联记录

#### Scenario: 更新课程已选人数
- GIVEN 选课记录变更
- WHEN 操作完成
- THEN Course表classChooseNum同步更新

### Requirement: 数据字段映射
系统 SHALL 明确数据字段的来源和设置方式。

#### Scenario: 学生成绩字段映射
- GIVEN 教师查看选课学生列表
- WHEN 系统查询选课记录
- THEN Student对象需设置tempScore属性
- AND tempScore值来源于Course_choose表的score字段

#### Scenario: 学生已选课程成绩
- GIVEN 学生查看已选课程列表
- WHEN 系统查询选课记录
- THEN Course对象需设置score属性
- AND score值来源于Course_choose表的score字段

### Requirement: DAO接口定义
系统 SHALL 定义完整的数据访问接口。

#### CourseDao 接口方法

| 方法签名 | 返回类型 | 说明 |
|---------|---------|------|
| queryCourseById(int teaId) | List<Course> | 查询教师开设的课程 |
| queryInsIdByCourseId(int classId) | List<Integer> | 查询课程学院限制ID列表 |
| selectNameByInsId(int insId) | String | 根据学院ID查询学院名称 |
| queryCourseInfoById(int classId) | Course | 查询课程详细信息 |
| selectTeaNameByTeaId(int teaId) | String | 根据教师ID查询教师姓名 |
| selectCourseByClassId(int classId) | Course | 根据课程ID查询课程 |
| selectScore(Course_choose cc) | int | 查询学生某课程的成绩 |
| deleteStuByClassId(int classId) | void | 删除课程的所有选课记录 |
| deleteLimitByClassId(int classId) | void | 删除课程的所有学院限制记录 |
| queryCourseIdByStuId(int stuId) | List<Integer> | 查询学生已选课程ID列表 |
| queryAllCourse() | List<Course> | 查询所有课程 |
| selectInsIdByClassId(int classId) | List<Integer> | 查询课程学院限制ID列表 |