<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="model.entity.Course" %>
<%@ page import="utils.constants.AttributesHolder" %>
<%@ page import="utils.constants.UrlHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="course" var="msg"/>
<div class="container-fluid">
        <div class="row content">
            <jsp:include page="navigationElements/leftPanel.jsp" />
        <div class="col-sm-9 section">
            <h2><small><fmt:message key="find.courses" bundle="${msg}"/></small></h2>
            <hr>
            <c:forEach items="${courses}" var="course">
                <div class="course-mini">
                    <h2><a href="#"><c:out value="${course.name}"/></a></h2>
                    <h5><fmt:message key="tutor" bundle="${msg}"/>:
                        <c:out value="${course.tutor.lastName}"/>
                    </h5>
                    <p><fmt:message key="about" bundle="${msg}"/>:
                        <c:out value="${course.about}"/>
                    </p>
                    <%--here must be form--%>
                    <form action="${UrlHolder.FOLLOW}" method="post">
                        <button type="submit" name="${AttributesHolder.COURSE}" class="btn btn-primary"
                                value="<c:out value="${course.id}"/>">
                            <fmt:message key="follow" bundle="${msg}"/>
                        </button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
