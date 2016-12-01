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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
            <h2><small><fmt:message key="my.courses" bundle="${msg}"/></small></h2>
            <hr>
            <div>
                <a href="${UrlHolder.COURSE_ADD}">
                    <button type="submit" class="btn btn-success"><fmt:message key="add.course" bundle="${msg}"/></button>
                </a>
                <table class="table">
                    <thead>
                    <tr>
                        <th></th>
                        <th></th>
                        <th><fmt:message key="course.name" bundle="${msg}"/></th>
                        <th><fmt:message key="about" bundle="${msg}"/></th>
                        <th><fmt:message key="start.date" bundle="${msg}"/></th>
                        <th><fmt:message key="end.date" bundle="${msg}"/></th>
                        <th>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${courses}" var="course">
                        <tr>
                            <td>
                                <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#<c:out value="${course.id}"/>">X</button>
                                <!-- Modal -->
                                <div class="modal fade" id="<c:out value="${course.id}"/>" role="dialog">
                                    <div class="modal-dialog">
                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title"><fmt:message key="delete" bundle="${msg}"/></h4>
                                            </div>
                                            <div class="modal-body">
                                                <p><fmt:message key="msg.delete" bundle="${msg}"/></p>
                                            </div>
                                            <div class="modal-footer">
                                                <form action="${UrlHolder.COURSE_DELETE}" method="post">
                                                    <button type="submit" class="btn btn-danger" name="${AttributesHolder.ID}" value="<c:out value="${course.id}"/>">
                                                        <fmt:message key="delete" bundle="${msg}"/>
                                                    </button>
                                                    <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="cancel" bundle="${msg}"/></button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <a href="${UrlHolder.COURSE}?${AttributesHolder.ID}=<c:out value="${course.id}"/>">
                                    <button type="button" class="btn btn-info btn-sm"><fmt:message key="edit" bundle="${msg}"/></button>
                                </a>
                            </td>
                            <td><c:out value="${course.name}"/></td>
                            <td><c:out value="${course.about}"/></td>
                            <td><c:out value="${course.startDate}"/></td>
                            <td><c:out value="${course.endDate}"/></td>
                            <td>
                                <a href="${UrlHolder.STUDENTS}/?${AttributesHolder.COURSE}=<c:out value="${course.id}"/>">
                                    <button type="button" class="btn btn-primary">
                                    <fmt:message key="show.students" bundle="${msg}"/>
                                </button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
