<%--
  Created by IntelliJ IDEA.
  User: click
  Date: 5/31/2016
  Time: 05:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="utils.constants.UrlHolder" %>
<%@ page import="utils.constants.AttributesHolder" %>
<%@ page import="utils.constants.PagesHolder" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<c:url value="/bootstrap.css"/>" >
    <style>
        .authorization-section {
            width: 50%;
        }
        .center {
            float: none;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>

<body>
<jsp:include page="../navigationElements/upperPanel.jsp"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[AttributesHolder.LOCALE]}" />
<fmt:setBundle basename="system" var="msg"/>
<div class="container-fluid">
    <div class="row content">
        <jsp:include page="../navigationElements/leftPanel.jsp" />
        <div class="col-sm-9 section">
            <h2><small><fmt:message key="error" bundle="${msg}"/></small></h2>
            <hr>
            <div class="center jumbotron authorization-section">
                <div class="center">
                    <h2><small><fmt:message key="page.not.found" bundle="${msg}"/></small></h2>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

