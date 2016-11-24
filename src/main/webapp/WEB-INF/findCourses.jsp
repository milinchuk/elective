<%@ page import="model.entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="utils.constants.UrlHolder" %>
<%@ page import="utils.constants.AttributesHolder" %>
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
    <link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap.css"/>" >
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
            <h2><small>FIND COURSES</small></h2>
            <hr>
            <c:forEach items="${courses}" var="course">
                <div class="course-mini">
                    <h2><a href="#"><c:out value="${course.name}"/></a></h2>
                    <h5>  Tutor: <c:out value="${course.tutor.lastName}"/> </h5>
                    <p><c:out value="${course.about}"/></p>
                    <%--here must be form--%>
                    <form action="<%=UrlHolder.FOLLOW%>" method="post">
                        <button type="submit" name="course" class="btn btn-primary" value="<c:out value="${course.id}"/>">Follow</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
