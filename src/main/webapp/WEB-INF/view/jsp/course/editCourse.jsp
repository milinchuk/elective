<%--
  Created by IntelliJ IDEA.
  User: click
  Date: 11/15/2016
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="model.entity.Course" %>
<%@ page import="utils.constants.AttributesHolder" %>
<%@ page import="utils.constants.DateHolder" %>
<%@ page import="utils.constants.UrlHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title></title>
    <jsp:include page="../including.elements/styles.jsp"/>
</head>

<body>
<jsp:include page="../navigationElements/upperPanel.jsp"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[AttributesHolder.LOCALE]}" />
<fmt:setBundle basename="course" var="msg"/>
<div class="container-fluid">
    <div class="row content">
        <jsp:include page="../navigationElements/leftPanel.jsp" />
        <div class="col-sm-9 section">
            <h2><small><fmt:message key="msg.edit.course" bundle="${msg}"/></small></h2>
            <hr>
            <form action="${UrlHolder.COURSE_EDIT}" method="post">
                <jsp:include page="courseTemplate.jsp"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>
