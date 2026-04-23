<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>选课学生</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>选课学生</h1>
        </div>
        <div class="row">
            <div class="col-md-4">
                <form action="searchStu" method="get" class="form-inline">
                    <input type="number" name="stuid" placeholder="学生学号" class="form-control">
                    <input type="hidden" name="courseid" value="${param.courseid}">
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </div>
        </div>
        <hr>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>成绩</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${paging.list}" var="student">
                    <tr>
                        <td>${student.stuId}</td>
                        <td>${student.stuName}</td>
                        <td>${student.tempScore}</td>
                        <td>
                            <form action="updateScore" method="post" class="form-inline">
                                <input type="hidden" name="courseid" value="${param.courseid}">
                                <input type="hidden" name="stuId" value="${student.stuId}">
                                <input type="number" name="score" value="${student.tempScore}" class="form-control" style="width:80px">
                                <input type="hidden" name="page" value="${paging.currentPage}">
                                <button type="submit" class="btn btn-primary btn-sm">评分</button>
                            </form>
                            <a href="deleteStuCourse?stuid=${student.stuId}&courseid=${param.courseid}" class="btn btn-danger btn-sm">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="text-center">
            <c:if test="${paging.currentPage > 1}">
                <a href="detailCourse?courseid=${param.courseid}&page=${paging.currentPage - 1}" class="btn btn-default">上一页</a>
            </c:if>
            <span>第 ${paging.currentPage} 页 / 共 ${paging.totalPage} 页</span>
            <c:if test="${paging.currentPage < paging.totalPage}">
                <a href="detailCourse?courseid=${param.courseid}&page=${paging.currentPage + 1}" class="btn btn-default">下一页</a>
            </c:if>
        </div>
        <a href="courseList?page=1" class="btn btn-default">返回课程列表</a>
    </div>
</body>
</html>