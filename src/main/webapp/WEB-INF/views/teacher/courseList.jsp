<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的课程</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>我的课程</h1>
        </div>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>课程ID</th>
                    <th>课程名称</th>
                    <th>容量</th>
                    <th>已选人数</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${paging.list}" var="course">
                    <tr>
                        <td>${course.classId}</td>
                        <td>${course.className}</td>
                        <td>${course.classNum}</td>
                        <td>${course.classChooseNum}</td>
                        <td>
                            <a href="editCourse?courseid=${course.classId}" class="btn btn-primary btn-sm">编辑</a>
                            <a href="detailCourse?courseid=${course.classId}&page=1" class="btn btn-info btn-sm">查看学生</a>
                            <a href="deleteCourse?courseid=${course.classId}" class="btn btn-danger btn-sm">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="text-center">
            <c:if test="${paging.currentPage > 1}">
                <a href="courseList?page=${paging.currentPage - 1}" class="btn btn-default">上一页</a>
            </c:if>
            <span>第 ${paging.currentPage} 页 / 共 ${paging.totalPage} 页</span>
            <c:if test="${paging.currentPage < paging.totalPage}">
                <a href="courseList?page=${paging.currentPage + 1}" class="btn btn-default">下一页</a>
            </c:if>
        </div>
        <a href="insertCourse" class="btn btn-success">新增课程</a>
        <a href="teacherIndex" class="btn btn-default">返回首页</a>
    </div>
</body>
</html>