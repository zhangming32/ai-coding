<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑课程</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>编辑课程</h1>
        </div>
        <form id="courseForm">
            <div class="form-group">
                <label for="className">课程名称</label>
                <input type="text" class="form-control" id="className" name="className" value="${courseInfo.className}" required>
            </div>
            <div class="form-group">
                <label for="classNum">课程容量</label>
                <input type="number" class="form-control" id="classNum" name="classNum" value="${courseInfo.classNum}" required>
            </div>
            <div class="form-group">
                <label>学院限制</label>
                <c:forEach items="${insList}" var="ins">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="insIds" value="${ins.insId}" 
                                <c:forEach items="${checkIns}" var="cIns">
                                    <c:if test="${cIns == ins.insId}">checked</c:if>
                                </c:forEach>
                            > ${ins.insName}
                        </label>
                    </div>
                </c:forEach>
            </div>
            <button type="button" onclick="submitForm()" class="btn btn-primary">提交</button>
            <a href="courseList?page=1" class="btn btn-default">返回</a>
        </form>
    </div>
    <script>
        function submitForm() {
            var className = document.getElementById('className').value;
            var classNum = document.getElementById('classNum').value;
            var insIds = [];
            var checkboxes = document.getElementsByName('insIds');
            for (var i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].checked) {
                    insIds.push(checkboxes[i].value);
                }
            }
            var content = className + '|' + classNum + '|' + insIds.join(',');
            content = encodeURIComponent(encodeURIComponent(content));
            window.location.href = 'updateCourseSuccess?content=' + content + '&page=1';
        }
    </script>
</body>
</html>