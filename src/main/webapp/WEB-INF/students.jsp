<%--
  Created by IntelliJ IDEA.
  User: click
  Date: 5/31/2016
  Time: 05:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<c:url value="/bootstrap.css"/>" >
    <style>
        .course-mini{
            width: 28%;
            height: 30%;
            float: left;
            border-bottom: 1px gray;
        }
    </style>
</head>

<body>
<jsp:include page="navigationElements/upperPanel.jsp"/>

<div class="container-fluid">
    <div class="row content">
        <jsp:include page="navigationElements/leftPanel.jsp" />
        <div class="col-sm-9 section">
            <h2><small>Students</small></h2>
            <hr>
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Full name</th>
                        <th>Mark</th>
                        <th>Note</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${students}" var="progress">
                        <tr>
                            <form action="/user/student/edit" method="post">
                                <td><input readonly style="border: none; width: 15px;" name="id" value="<c:out value="${progress.id}"/>"/></td>
                                <td><c:out value="${progress.student.firstName}"/> <c:out value="${progress.student.lastName}"/></td>
                                <td><input name="mark" class="form-control" value="<c:out value="${progress.mark}"/>"/></td>
                                <td><input name="note" class="form-control" value="<c:out value="${progress.note}"/>"/></td>
                                <td><button class="btn btn-success" name="course" type="submit" value="<c:out value="${progress.course.id}"/>">Save</button></td>
                            </form>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
