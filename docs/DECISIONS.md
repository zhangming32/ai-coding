# 开发决策记录 (Development Decisions)

## 项目概述

本项目基于 Course-Selecting-System 的规范文档（openspec/specs）和设计文档（docs）反向生成代码，遵循 SDD 开发规范。

---

## 决策1: 项目框架选择

**决策时间**: 2026-04-23

**决策内容**: 选择 SSM (Spring + SpringMVC + MyBatis) 作为开发框架

**决策依据**: 
- 规范文档明确要求使用 Spring 5.3.5 + SpringMVC 5.0.3 + MyBatis 3.4.1
- 三层架构设计要求表示层、业务层、数据层分离
- MyBatis 适合 SQL 映射和灵活的数据操作

**决策结果**: 
- 后端框架: Spring Framework 5.3.5 + SpringMVC + MyBatis 3.4.1
- 连接池: C3P0 0.9.5.2
- 前端: JSP + Bootstrap + Layui + jQuery

---

## 册策2: 包命名选择

**决策时间**: 2026-04-23

**决策内容**: 选择 `com.zxc` 作为根包名

**决策依据**:
- 规范文档中的包结构示例使用 `com.zxc`
- 保持与原项目命名一致性
- 简短且易于记忆

**决策结果**:
```
com.zxc.controller      - 控制层
com.zxc.service         - 业务接口
com.zxc.service.impl    - 业务实现
com.zxc.dao             - 数据访问接口
com.zxc.model           - 实体模型
```

---

## 决策3: 用户角色识别方式

**决策时间**: 2026-04-23

**决策内容**: 通过用户ID第5位字符识别角色

**决策依据**:
- auth spec 明确规定: ID第5位为'1'识别为教师，否则为学生
- 这是核心业务规则，影响登录流程和权限控制

**决策结果**:
```java
// 角色识别逻辑
if (Integer.toString(id).charAt(4) == '1') {
    return 2; // 教师
} else {
    return 1; // 学生
}
```

---

## 决策4: Session管理方式

**决策时间**: 2026-04-23

**决策内容**: 使用 Spring MVC 的 @SessionAttributes 注解管理会话

**决策依据**:
- system spec 规定使用 Session 存储用户状态
- AGENTS.md 明确使用 @SessionAttributes 注解
- 学生会话存储: username, stuid
- 教师会话存储: username, teaid

**决策结果**:
```java
@Controller
@SessionAttributes({"username", "stuid", "teaid"})
public class LoginController { ... }
```

---

## 决策5: 数据库表设计

**决策时间**: 2026-04-23

**决策内容**: 采用规范文档定义的6张核心表

**决策依据**:
- project.md 和 course spec 定义了数据库实体
- 表关系: Student-Institution (N:1), Teacher-Course (1:N), Course-Course_choose (1:N)

**决策结果**:
| 表名 | 主键 | 主要字段 |
|------|------|----------|
| Student | stuId | stuName, stuPass, insId |
| Teacher | teaId | teaName, teaPass |
| Course | classId | className, classNum, teaId, classChooseNum |
| Course_choose | chooseId | stuId, classId, score |
| Course_limit | limitId | classId, insId |
| Institution | insId | insName |

---

## 决策6: 分页实现方式

**决策时间**: 2026-04-23

**决策内容**: 创建通用 PageService 和 Page 实体类

**决策依据**:
- system spec 规定需要分页功能
- 分页元数据包含: currentPage, totalPage, pageSize, totalNum
- 支持任意类型数据的分页

**决策结果**:
- PageService 接口定义 subList 方法
- Page 类封装分页数据和元数据
- 每页大小由前端或配置决定

---

## 册策7: 依赖注入注解选择

**决策时间**: 2026-04-23

**决策内容**: Controller 使用 @Resource，Service 实现类使用 @Autowired

**决策依据**:
- AGENTS.md 明确 Controller 使用 @Resource
- Spring 推荐使用 @Autowired 进行自动装配
- @Resource 是 JSR-250 标准，兼容性更好

**决策结果**:
```java
// Controller层
@Resource
private UserService userService;

// Service实现层
@Autowired
private UserDao userDao;
```

---

## 册策8: RESTful API设计

**决策时间**: 2026-04-23

**决策内容**: 采用传统 MVC URL 路径风格而非 RESTful

**决策依据**:
- API Documentation 使用传统路径如 /student/courseList
- 操作通过不同的 URL 路径区分而非 HTTP 方法语义
- 适合现有前端 JSP 页面结构

**决策结果**:
| 功能 | 路径 | 方法 |
|------|------|------|
| 登录验证 | /check | POST |
| 选课 | /student/chooseSuccess | POST |
| 退课 | /student/deleteCourse | POST |
| 课程列表 | /student/courseList | GET |

---

## 决策9: 学院限制检查实现

**决策时间**: 2026-04-23

**决策内容**: 在 CourseService 中实现 checkStuIns 方法

**决策依据**:
- course spec 规定需要验证学生学院是否在限制列表
- 学院限制存储在 Course_limit 表
- 无限制的课程允许所有学生选课

**决策结果**:
```java
public boolean checkStuIns(int classId, int stuId) {
    // 1. 获取课程学院限制列表
    // 2. 获取学生所属学院
    // 3. 检查学生学院是否在限制列表中
    // 4. 无限制返回 true
}
```

---

## 册策10: 课程删除级联处理

**决策时间**: 2026-04-23

**决策内容**: CourseService.deleteCourse 方法按顺序删除关联数据

**决策依据**:
- course spec 规定删除课程需要清理 Course_choose 和 Course_limit
- 先删除选课记录，再删除限制记录，最后删除课程
- 确保数据完整性

**决策结果**:
```java
public void deleteCourse(int classId) {
    // 1. 删除 Course_choose 记录
    // 2. 删除 Course_limit 记录  
    // 3. 删除 Course 记录
}
```

---

## 册策11: URL编码处理

**决策时间**: 2026-04-23

**决策内容**: 课程内容参数使用双重 URL 解码

**决策依据**:
- TeacherController 的 insertCourseSuccess 和 updateCourseSuccess 需要处理 URL 编码
- 内容格式: 课程名称|容量|学院IDs
- 双重解码确保中文正确传输

**决策结果**:
```java
String[] det = URLDecoder.decode(
    URLDecoder.decode(content, "utf-8"), "utf-8"
).split("\\|");
```

---

## 册策12: 视图解析配置

**决策时间**: 2026-04-23

**决策内容**: 配置 InternalResourceViewResolver 解析 JSP

**决策依据**:
- system spec 规定视图解析器添加前缀后缀
- JSP 文件位于 /WEB-INF/views/
- 视图名格式: student/studentIndex

**决策结果**:
```xml
<bean class="...InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/views/"/>
    <property name="suffix" value=".jsp"/>
</bean>
```

---

## 册策13: 错误消息传递方式

**决策时间**: 2026-04-23

**决策内容**: 使用 Model 的 msg 属性传递错误消息

**决策依据**:
- system spec 规定错误提示通过 msg 属性显示
- 登录失败: msg="密码错误"
- 密码修改失败: msg="原始密码输入错误!"

**决策结果**:
```java
model.addAttribute("msg", "密码错误");
return "login"; // 不使用 redirect
```

---

## 册策14: 成绩默认值设定

**决策时间**: 2026-04-23

**决策内容**: 选课记录成绩默认为 0

**决策依据**:
- course spec 规定 score 默认为 0
- 教师评分时更新该值
- 成绩类型为 int

**决策结果**:
```sql
INSERT INTO Course_choose (stuId, classId, score) 
VALUES (#{stuId}, #{classId}, 0)
```

---

## 册策15: 测试账号格式

**决策时间**: 2026-04-23

**决策内容**: 使用特定的账号格式便于测试

**决策依据**:
- AGENTS.md 提供测试账号
- 学生账号第5位不为'1': 2018000001-2018000011
- 教师账号第5位为'1': 2018100001-2018100004
- 密码与账号相同便于记忆

**决策结果**:
- 学生账号: 2018000001 - 2018000011
- 教师账号: 2018100001 - 2018100004
- 默认密码: 与账号相同

---

## 总结

以上15个决策项覆盖了项目的主要技术选型、架构设计、业务逻辑实现等方面。所有决策基于规范文档的要求，确保生成的代码符合 SDD 开发规范。

文档版本: v1.0
创建日期: 2026-04-23