<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>教师首页</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>教师首页</h1>
            <p>欢迎, ${sessionScope.username}</p>
        </div>
        <div class="row">
            <div class="col-md-3">
                <div class="list-group">
                    <a href="teacherInfo?teaid=${sessionScope.teaid}" class="list-group-item">个人信息</a>
                    <a href="courseList?page=1" class="list-group-item">我的课程</a>
                    <a href="insertCourse" class="list-group-item">新增课程</a>
                    <a href="editTeaPass" class="list-group-item">修改密码</a>
                    <a href="../exit" class="list-group-item">退出登录</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>