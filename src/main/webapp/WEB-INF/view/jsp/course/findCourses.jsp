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
            float: left;
            border-bottom: 1px gray;
        }
    </style>
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
            <h2><small><fmt:message key="find.courses" bundle="${msg}"/></small></h2>
            <hr>
            <c:if test="${addMessage}">
                <div class="alert alert-success">
                    <fmt:message key="add" bundle="${msg}"/>
                </div>
            </c:if>
            <c:forEach items="${courses}" var="course">
                <div class="course-mini">
                    <h2><a href="#"><c:out value="${course.name}"/></a></h2>
                    <h5><fmt:message key="tutor" bundle="${msg}"/>:
                        <c:out value="${course.tutor.lastName}"/>
                    </h5>
                    <p><fmt:message key="about" bundle="${msg}"/>:
                        <c:out value="${course.about}"/>
                    </p>
                    <p><fmt:message key="start.date" bundle="${msg}"/>:
                        <c:out value="${course.startDate}"/>
                    </p>
                    <p><fmt:message key="end.date" bundle="${msg}"/>:
                        <c:out value="${course.endDate}"/>
                    </p>
                    <%--here must be form--%>
                    <c:if test="${course.isActive}">
                    <form action="${UrlHolder.FOLLOW}" method="post">
                        <button type="submit" name="${AttributesHolder.COURSE}" class="btn btn-primary"
                                value="<c:out value="${course.id}"/>">
                            <fmt:message key="follow" bundle="${msg}"/>
                        </button>
                    </form>
                    </c:if>
                    <c:if test="${!course.isActive}">
                        <button type="button" name="${AttributesHolder.COURSE}" class="btn btn-primary disabled"
                                value="<c:out value="${course.id}"/>">
                            <fmt:message key="follow" bundle="${msg}"/>
                        </button>
                    </c:if>
                    <br>
                </div>
                <br>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
