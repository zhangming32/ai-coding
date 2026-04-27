# AI Agent Instructions

## Working with This Project

This document provides guidance for AI assistants working with the Course Selecting System project.

## Project Context

- Framework: SSM (Spring + SpringMVC + MyBatis)
- Language: Java 8
- Build: Maven
- Database: MySQL 5.7

## Code Conventions

### Package Structure
- Controllers in `com.zxc.controller.*`
- Services in `com.zxc.service.*` (interfaces) and `com.zxc.service.impl.*` (implementations)
- DAOs in `com.zxc.dao.*`
- Models in `com.zxc.model.*`

### Naming Patterns
- Controller: `XXXController.java`
- Service Interface: `XXXService.java`
- Service Impl: `XXXServiceImpl.java`
- DAO: `XXXDao.java`
- Entity: Entity name (e.g., `Student.java`)
- MyBatis Mapper: `XXXDao.xml`

### Controller Mapping
- Auth: `/login`, `/check`, `/exit`
- Student: `/student/*`
- Teacher: `/teacher/*`

## Key Patterns

### Session Management
Uses `@SessionAttributes` annotation:
- Student session: `username`, `stuid`
- Teacher session: `username`, `teaid`

### Service Injection
Uses `@Resource` annotation for dependency injection.

### Request Handling
Uses `@RequestMapping` with path and method specification.

### Parameter Binding
Uses `@RequestParam` for named parameters, `@Param` for MyBatis.

## Database Schema

| Table | Key Fields |
|-------|------------|
| Student | stuId (PK), stuName, stuPass, insId (FK) |
| Teacher | teaId (PK), teaName, teaPass |
| Course | classId (PK), className, classNum, teaId (FK), classChooseNum |
| Course_choose | chooseId (PK), stuId (FK), classId (FK), score |
| Course_limit | limitId (PK), classId (FK), insId (FK) |
| Institution | insId (PK), insName |

## Business Logic

### Role Identification
```java
// Teacher: 5th digit of ID is '1'
if(Integer.toString(id).charAt(4)=='1') {
    // return 2 (teacher)
} else {
    // return 1 (student)
}
```

### Institution Restriction Check
- Course may have Course_limit records
- Student must belong to one of listed institutions to enroll

### Enrollment Count Management
- `addChooseNum`: increment when student enrolls
- `downChooseNum`: decrement when student drops

## When Making Changes

1. Follow existing patterns and naming conventions
2. Keep controllers focused on request handling
3. Business logic belongs in Service layer
4. Database operations in DAO layer with MyBatis mappers
5. Update corresponding spec.md in openspec/specs/
6. Add test cases for new functionality

## Testing Accounts

- Students: 2018000001 - 2018000011 (password = ID)
- Teachers: 2018100001 - 2018100004 (password = ID)

## Configuration Files

- `spring-mybatis.xml`: Spring + MyBatis integration
- `spring-mvc.xml`: Spring MVC configuration
- `jdbc.properties`: Database connection settings