<%@ page import="model.entity.Course" %>
<%--
  Created by IntelliJ IDEA.
  User: click
  Date: 11/15/2016
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
//    Course course = (Course)request.getAttribute("course");
//    request.setAttribute("course", course);
%>
<html>
<head>
    <title></title>
</head>
<body>
<c:out value="${course.id}"/>
<c:out value="${course.name}"/>
<c:out value="${course.about}"/>
<c:out value="${course.startDate}"/>
<c:out value="${course.endDate}"/>
<c:out value="${course.tutor.lastName}"/>
</body>
</html>
