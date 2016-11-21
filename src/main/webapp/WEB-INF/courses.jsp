<%@ page import="model.entity.Course" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: click
  Date: 5/31/2016
  Time: 05:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("courses", (List<Course>)request.getAttribute("courses"));
%>
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
            <h2><small>FIND COURSES</small></h2>
            <hr>
            <c:forEach items="${courses}" var="course">
                <div class="course-mini">
                    <h2><a href="/course/<c:out value="${course.id}"/>" ><c:out value="${course.name}"/></a></h2>
                        <%--<h5><span class="glyphicon glyphicon-time"></span>--%>
                    <h5>  Tutor <a href="#"><c:out value="${course.tutor.lastName}"/></a>
                            <%--<span class="glyphicon glyphicon-thumbs-up"></span> 5--%>
                    </h5>
                    <p><c:out value="${course.about}"/></p>
                    <a href="/course/<c:out value="${course.id}"/>"><button type="button" class="btn btn-primary">Read more</button></a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
