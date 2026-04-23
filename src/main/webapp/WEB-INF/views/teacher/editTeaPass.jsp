<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改密码</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>修改密码</h1>
        </div>
        <% if (request.getAttribute("msg") != null) { %>
            <div class="alert alert-danger">${msg}</div>
        <% } %>
        <form action="changeTeaPass" method="post">
            <div class="form-group">
                <label for="prepass">原密码</label>
                <input type="password" class="form-control" id="prepass" name="prepass" required>
            </div>
            <div class="form-group">
                <label for="nowpass">新密码</label>
                <input type="password" class="form-control" id="nowpass" name="nowpass" required>
            </div>
            <button type="submit" class="btn btn-primary">提交</button>
            <a href="teacherIndex" class="btn btn-default">返回</a>
        </form>
    </div>
</body>
</html>