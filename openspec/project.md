# Course Selecting System Project

## Project Overview

This is a web-based course selecting system for higher education institutions, built with SSM (Spring + SpringMVC + MyBatis) framework.

## Project Goals

- Provide online course selection for students
- Enable teachers to manage courses and grades
- Support institution-based course restrictions
- Deliver paginated data display for better UX

## Target Users

| User Role | Primary Functions |
|-----------|-------------------|
| Student | Browse courses, select/drop courses, view grades |
| Teacher | Create/manage courses, grade students, manage enrollments |

## Technology Stack

| Layer | Technology |
|-------|------------|
| Frontend | JSP, Bootstrap, Layui, jQuery |
| Web | Spring MVC 5.0.3 |
| Business | Spring 5.3.5 |
| Persistence | MyBatis 3.4.1 |
| Database | MySQL 5.7 |
| Connection Pool | C3P0 |

## Project Structure

```
src/main/java/com/zxc/
├── controller/
│   ├── common/LoginController.java
│   ├── student/StudentController.java
│   └── teacher/TeacherController.java
├── service/
│   ├── UserService.java
│   ├── CourseService.java
│   ├── PageService.java
│   └── impl/
├── dao/
│   ├── UserDao.java
│   └── CourseDao.java
└── model/
    ├── Student.java
    ├── Teacher.java
    ├── Course.java
    ├── Course_choose.java
    ├── Course_limit.java
    ├── Institution.java
    └── Page.java
```

## Database Entities

| Entity | Description |
|--------|-------------|
| Student | Student information with institution affiliation |
| Teacher | Teacher information |
| Course | Course with capacity and teacher |
| Course_choose | Student enrollment record with score |
| Course_limit | Institution restrictions for courses |
| Institution | Academic institution/college |

## Key Business Rules

1. User role is determined by ID format (5th digit: '1' = teacher)
2. Students can only select courses matching their institution restrictions
3. Teachers can only manage courses they created
4. Course deletion cascades to enrollments and restrictions
5. Enrollment count is maintained automatically

## API Endpoints

| Role | Base Path | Key Endpoints |
|------|-----------|---------------|
| Auth | / | /login, /check, /exit |
| Student | /student/* | /courseList, /chooseSuccess, /deleteCourse |
| Teacher | /teacher/* | /courseList, /insertCourseSuccess, /updateScore |

## Documentation

- SDD: docs/SDD_Software_Design_Specification.md
- PRD: docs/PRD_Product_Requirements_Document.md
- Architecture: docs/Architecture_Design_Document.md
- User Stories: docs/User_Stories.md
- API Docs: docs/API_Documentation.md
- UML: docs/UML_Diagrams.md