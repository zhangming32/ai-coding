<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>已选课程</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>已选课程</h1>
        </div>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>课程ID</th>
                    <th>课程名称</th>
                    <th>教师</th>
                    <th>成绩</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${courseList}" var="course">
                    <tr>
                        <td>${course.classId}</td>
                        <td>${course.className}</td>
                        <td>${course.teaName}</td>
                        <td>${course.score}</td>
                        <td>
                            <a href="deleteCourse?courseid=${course.classId}" class="btn btn-danger btn-sm">退课</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="studentIndex" class="btn btn-default">返回首页</a>
    </div>
</body>
</html>