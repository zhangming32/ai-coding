<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>课程列表</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>课程列表 <small>学号: ${studentInfo.stuId} | 学院: ${studentInfo.insName}</small></h1>
        </div>
        <% if (request.getAttribute("msg") != null) { %>
            <div class="alert alert-warning">${msg}</div>
        <% } %>
        <div class="row">
            <div class="col-md-4">
                <form action="searchCourse" method="get" class="form-inline">
                    <input type="number" name="courseid" placeholder="课程ID" class="form-control">
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </div>
            <div class="col-md-4">
                <form action="searchListByTeaId" method="get" class="form-inline">
                    <select name="teaid" class="form-control">
                        <option value="">选择教师</option>
                        <c:forEach items="${teaList}" var="tea">
                            <option value="${tea.teaId}">${tea.teaName}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="btn btn-default">筛选</button>
                </form>
            </div>
            <div class="col-md-4">
                <form action="searchListByInsId" method="get" class="form-inline">
                    <select name="insid" class="form-control">
                        <option value="">选择学院</option>
                        <c:forEach items="${insList}" var="ins">
                            <option value="${ins.insId}">${ins.insName}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="btn btn-default">筛选</button>
                </form>
            </div>
        </div>
        <hr>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>课程ID</th>
                    <th>课程名称</th>
                    <th>教师</th>
                    <th>容量</th>
                    <th>已选人数</th>
                    <th>学院限制</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${paging.list}" var="course">
                    <tr>
                        <td>${course.classId}</td>
                        <td>${course.className}</td>
                        <td>${course.teaName}</td>
                        <td>${course.classNum}</td>
                        <td>${course.classChooseNum}</td>
                        <td>
                            <c:forEach items="${course.classLimitInsName}" var="insName">
                                ${insName}
                            </c:forEach>
                        </td>
                        <td>
                            <c:if test="${course.isChoose == 1}">
                                <span class="label label-success">已选</span>
                            </c:if>
                            <c:if test="${course.isChoose == 0}">
                                <span class="label label-default">未选</span>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${course.isChoose == 0}">
                                <a href="courseDetail?classId=${course.classId}" class="btn btn-primary btn-sm">选课</a>
                            </c:if>
                            <c:if test="${course.isChoose == 1}">
                                <a href="deleteCourse?courseid=${course.classId}" class="btn btn-danger btn-sm">退课</a>
                            </c:if>
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
        <a href="studentIndex" class="btn btn-default">返回首页</a>
    </div>
</body>
</html>