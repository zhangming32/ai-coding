<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>课程选课系统 - 登录</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
    <style>
        body { background-color: #f5f5f5; }
        .login-container { max-width: 400px; margin: 100px auto; }
        .login-panel { padding: 30px; background: #fff; border-radius: 5px; }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="login-panel">
            <h3 class="text-center">课程选课系统</h3>
            <hr>
            <% if (request.getAttribute("msg") != null) { %>
                <div class="alert alert-danger">
                    ${msg}
                </div>
            <% } %>
            <form action="check" method="post">
                <div class="form-group">
                    <label for="userid">账号</label>
                    <input type="text" class="form-control" id="userid" name="userid" required>
                </div>
                <div class="form-group">
                    <label for="userpass">密码</label>
                    <input type="password" class="form-control" id="userpass" name="userpass" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">登录</button>
            </form>
        </div>
    </div>
</body>
</html>