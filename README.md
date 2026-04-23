# AI-Coding 项目文档

## 项目概述

本项目是基于 Course-Selecting-System 的规范文档（openspec/specs）反向生成的代码实现。

## 生成依据

代码严格按照以下规范文档生成：
- `openspec/specs/auth/spec.md` - 用户认证规范
- `openspec/specs/student/spec.md` - 学生功能规范
- `openspec/specs/teacher/spec.md` - 教师功能规范
- `openspec/specs/course/spec.md` - 课程管理规范
- `openspec/specs/system/spec.md` - 系统规范

## 技术栈

| 层级 | 技术 |
|------|------|
| 表示层 | Spring MVC + JSP |
| 业务层 | Spring Framework |
| 数据层 | MyBatis |
| 数据库 | MySQL |
| 连接池 | C3P0 |

## 项目结构

```
ai-coding/
├── pom.xml                          Maven配置
├── docs/
│   ├── DECISIONS.md                 开发决策记录
│   └── database_init.sql            数据库初始化脚本
├── src/main/java/com/zxc/
│   ├── controller/
│   │   ├── common/LoginController.java
│   │   ├── student/StudentController.java
│   │   └── teacher/TeacherController.java
│   ├── service/
│   │   ├── UserService.java
│   │   ├── CourseService.java
│   │   ├── PageService.java
│   │   └── impl/
│   │       ├── UserServiceImpl.java
│   │       ├── CourseServiceImpl.java
│   │       └── PageServiceImpl.java
│   ├── dao/
│   │   ├── UserDao.java
│   │   └── CourseDao.java
│   └── model/
│       ├── Student.java
│       ├── Teacher.java
│       ├── Course.java
│       ├── Course_choose.java
│       ├── Course_limit.java
│       ├── Institution.java
│       ├── Page.java
│       └── User.java
├── src/main/resources/
│   ├── jdbc.properties
│   ├── spring-mybatis.xml
│   ├── spring-mvc.xml
│   └── mapper/
│       ├── UserDao.xml
│       └── CourseDao.xml
└── src/main/webapp/WEB-INF/
    ├── web.xml
    └── views/
        ├── login.jsp
        ├── student/
        │   ├── studentIndex.jsp
        │   ├── studentInfo.jsp
        │   ├── editStuPass.jsp
        │   ├── courseList.jsp
        │   ├── courseDetail.jsp
        │   └── checkedCourseList.jsp
        └── teacher/
            ├── teacherIndex.jsp
            ├── teacherInfo.jsp
            ├── editTeaPass.jsp
            ├── courseList.jsp
            ├── insertCourse.jsp
            ├── editCourse.jsp
            └── courseDetail.jsp
```

## 运行步骤

1. 创建数据库并执行 `docs/database_init.sql`
2. 修改 `jdbc.properties` 配置数据库连接
3. 使用 Maven 构建：`mvn clean package`
4. 部署到 Tomcat 服务器
5. 访问 `http://localhost:8080/course-selecting-system/login`

## 测试账号

- 学生：2018000001-2018000011（密码=账号）
- 教师：2018100001-2018100004（密码=账号）

## 关键决策

详见 `docs/DECISIONS.md`，记录了15个关键技术决策项。