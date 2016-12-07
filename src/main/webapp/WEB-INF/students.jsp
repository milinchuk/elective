<%--
  Created by IntelliJ IDEA.
  User: click
  Date: 5/31/2016
  Time: 05:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="model.entity.Course" %>
<%@ page import="utils.constants.AttributesHolder" %>
<%@ page import="utils.constants.UrlHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[AttributesHolder.LOCALE]}" />
<fmt:setBundle basename="course" var="msg"/>

<div class="container-fluid">
    <div class="row content">
        <jsp:include page="navigationElements/leftPanel.jsp" />
        <div class="col-sm-9 section">
            <h2><small><fmt:message key="students" bundle="${msg}"/></small></h2>
            <hr>
            <table class="table">
                <thead>
                    <tr>
                        <th><fmt:message key="id" bundle="${msg}"/></th>
                        <th><fmt:message key="full.name" bundle="${msg}"/></th>
                        <th><fmt:message key="mark" bundle="${msg}"/></th>
                        <th><fmt:message key="note" bundle="${msg}"/></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${students}" var="progress">
                        <tr>
                            <form action="${UrlHolder.STUDENT_EDIT}" method="post">
                                <td><input hidden style="border: none; width: 15px;" name="${AttributesHolder.ID}" value="<c:out value="${progress.id}"/>"/></td>
                                <td><c:out value="${progress.student.firstName}"/> <c:out value="${progress.student.lastName}"/></td>

                                <td>
                                    <c:if test="${student != null && student == progress.id && errors != null && errors.messages['mark'] != null}">
                                        <div class="alrt alert-danger">
                                            <fmt:message key="${errors.messages['mark']}" bundle="${msg}" />
                                        </div>
                                    </c:if>
                                    <input name="mark" class="form-control" value="<c:out value="${progress.mark}"/>"/>
                                </td>

                                <td>
                                    <c:if test="${student!= null && student == progress.id && errors != null && errors.messages['note'] != null}">
                                        <div class="alrt alert-danger">
                                            <fmt:message key="${errors.messages['note']}" bundle="${msg}" />
                                        </div>
                                    </c:if>
                                    <input name="note" class="form-control" value="<c:out value="${progress.note}"/>"/>
                                </td>
                                <td><button class="btn btn-success" name="${AttributesHolder.COURSE}" type="submit" value="<c:out value="${progress.course.id}"/>">Save</button></td>
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
