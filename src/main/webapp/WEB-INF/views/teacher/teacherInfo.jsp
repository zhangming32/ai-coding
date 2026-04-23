<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>教师信息</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>个人信息</h1>
        </div>
        <div class="panel panel-default">
            <div class="panel-body">
                <table class="table">
                    <tr><td>工号</td><td>${teacher.teaId}</td></tr>
                    <tr><td>姓名</td><td>${teacher.teaName}</td></tr>
                </table>
            </div>
        </div>
        <a href="teacherIndex" class="btn btn-default">返回首页</a>
    </div>
</body>
</html>