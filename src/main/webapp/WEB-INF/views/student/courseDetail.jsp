<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>课程详情</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>课程详情</h1>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">${course.className}</div>
            <div class="panel-body">
                <table class="table">
                    <tr><td>课程ID</td><td>${course.classId}</td></tr>
                    <tr><td>教师</td><td>${course.teaName}</td></tr>
                    <tr><td>容量</td><td>${course.classNum}</td></tr>
                    <tr><td>已选人数</td><td>${course.classChooseNum}</td></tr>
                    <tr><td>学院限制</td><td>
                        <c:forEach items="${course.classLimitInsName}" var="insName">
                            ${insName} 
                        </c:forEach>
                    </td></tr>
                </table>
            </div>
        </div>
        <form action="chooseSuccess" method="post">
            <input type="hidden" name="stuid" value="${sessionScope.stuid}">
            <input type="hidden" name="courseid" value="${course.classId}">
            <button type="submit" class="btn btn-primary">确认选课</button>
            <a href="courseList?page=1" class="btn btn-default">返回列表</a>
        </form>
    </div>
</body>
</html>