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

<div class="container-fluid">
    <div class="row content">
        <jsp:include page="navigationElements/leftPanel.jsp" />
        <div class="col-sm-9 section">
            <h2><small>My courses</small></h2>
            <hr>
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Course name</th>
                        <th>Tutor</th>
                        <th>Start date</th>
                        <th>End date</th>
                        <th>Mark</th>
                        <th>Note</th>
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
                                                        <h4 class="modal-title">Delete</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p>Are you sure to unfollow course?</p>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <form action="/user/unfollow" method="post">
                                                            <button type="submit" class="btn btn-danger" name="progress" value="<c:out value="${progress.id}"/>">Unfollow</button>
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
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
