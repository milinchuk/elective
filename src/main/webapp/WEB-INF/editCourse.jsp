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
    <link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap.css"/>" >
    <style>
    </style>
    <script type="text/javascript">
        $(function () {
            $('#datetimepicker1').datetimepicker({
                defaultDate: "11/1/2013",
                disabledDates: [
                    moment("12/25/2013"),
                    new Date(2013, 11 - 1, 21),
                    "11/22/2013 00:53"
                ]
            });
            $('#datetimepicker2').datetimepicker({
                defaultDate: "11/1/2013",
                disabledDates: [
                    moment("12/25/2013"),
                    new Date(2013, 11 - 1, 21),
                    "11/22/2013 00:53"
                ]
            });
        });
    </script>
</head>

<body>
<jsp:include page="navigationElements/upperPanel.jsp"/>
<div class="container-fluid">
    <div class="row content">
        <jsp:include page="navigationElements/leftPanel.jsp" />
        <div class="col-sm-9 section">
            <h2><small>EDIT COURSE</small></h2>
            <hr>
            <form action="/user/course/edit" method="post">
                <label>ID:</label>
                <input name="id" style="border: none" readonly value="<c:out value="${course.id}"/>"/>
                <br>
                <label for="name">Name:</label>
                <input name="name" id="name" class="form-control" value="<c:out value="${course.name}"/>"/>
                <br>
                <label>About:</label>
                <textarea name="about" style="resize: none;" class="form-control" rows="3"><c:out value="${course.about}"/></textarea>
                <label for="name">Start date:</label>
                <div class='input-group date col-xs-3' id='datetimepicker1'>
                    <input name="startDate" type='text' class="form-control"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                </div>
                <label for="name">End date:</label>
                        <div class='input-group date col-xs-3' id='datetimepicker2'>
                            <input name="endDate" type='text' class="form-control"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                        </div>

                <br>
                <button class="btn btn-success" type="submit">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
