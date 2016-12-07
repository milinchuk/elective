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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
            <h2><small><fmt:message key="my.courses" bundle="${msg}"/></small></h2>
            <hr>
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th><fmt:message key="course.name" bundle="${msg}"/></th>
                        <th><fmt:message key="tutor" bundle="${msg}"/></th>
                        <th><fmt:message key="start.date" bundle="${msg}"/></th>
                        <th><fmt:message key="end.date" bundle="${msg}"/></th>
                        <th><fmt:message key="mark" bundle="${msg}"/></th>
                        <th><fmt:message key="note" bundle="${msg}"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${progresses}" var="progress">
                        <tr>
                                <td><c:out value="${progress.course.name}"/></td>
                                <td><c:out value="${progress.course.tutor.lastName}"/></td>
                                <td><c:out value="${progress.course.startDate}"/></td>
                                <td><c:out value="${progress.course.endDate}"/></td>
                                <td><c:out value="${progress.mark}"/></td>
                                <td><c:out value="${progress.note}"/></td>
                                <td>
                                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#<c:out value="${progress.id}"/>">Unfollow</button>
                                        <!-- Modal -->
                                        <div class="modal fade" id="<c:out value="${progress.id}"/>" role="dialog">
                                            <div class="modal-dialog">
                                                <!-- Modal content-->
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        <h4 class="modal-title"><fmt:message key="unfollow" bundle="${msg}"/></h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p><fmt:message key="msg.unfollow" bundle="${msg}"/></p>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <form action="${UrlHolder.UNFOLLOW}" method="post">
                                                            <button type="submit" class="btn btn-danger" name="${AttributesHolder.PROGRESS}" value="<c:out value="${progress.id}"/>">
                                                                <fmt:message key="unfollow" bundle="${msg}"/>
                                                            </button>
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                                                <fmt:message key="cancel" bundle="${msg}"/>
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
