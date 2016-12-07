<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="model.entity.Course" %>
<%@ page import="utils.constants.AttributesHolder" %>
<%@ page import="utils.constants.UrlHolder" %>
<%--
  Created by IntelliJ IDEA.
  User: click
  Date: 11/15/2016
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[AttributesHolder.LOCALE]}" />
<fmt:setBundle basename="course" var="msg"/>
<div class="container-fluid">
    <div class="row content">
        <jsp:include page="navigationElements/leftPanel.jsp" />
        <div class="col-sm-9 section">
            <h2><small><fmt:message key="add.course" bundle="${msg}"/></small></h2>
            <hr>
            <form action="${UrlHolder.COURSE_ADD}" method="post">
                <c:if test="${errors != null && errors.messages['name'] != null}">
                <div class="alrt alert-danger">
                    <fmt:message key="${errors.messages['name']}" bundle="${msg}" />
                </div>
                </c:if>
                <label for="name"><fmt:message key="name" bundle="${msg}"/></label>
                <input id="name" class="form-control" name="${AttributesHolder.NAME}" />

                <c:if test="${errors != null && errors.messages['about'] != null}">
                    <div class="alrt alert-danger">
                        <fmt:message key="${errors.messages['about']}" bundle="${msg}" />
                    </div>
                </c:if>
                <label for="name"><fmt:message key="about" bundle="${msg}"/></label>
                <textarea name="${AttributesHolder.ABOUT}" style="resize: none;" class="form-control" rows="3"></textarea>

                <c:if test="${errors != null && errors.messages['startDate'] != null}">
                    <div class="alrt alert-danger">
                        <fmt:message key="${errors.messages['startDate']}" bundle="${msg}" />
                    </div>
                </c:if>
                <label for="name"><fmt:message key="start.date" bundle="${msg}"/></label>
                <div class='input-group date col-xs-3' id='datetimepicker1'>
                    <input type='text' class="form-control"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                </div>

                <c:if test="${errors != null && errors.messages['endDate'] != null}">
                    <div class="alrt alert-danger">
                        <fmt:message key="${errors.messages['endDate']}" bundle="${msg}" />
                    </div>
                </c:if>
                <label for="name"><fmt:message key="end.date" bundle="${msg}"/></label>
                <div class='input-group date col-xs-3' id='datetimepicker2'>
                    <input type='text' class="form-control"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                </div>

                <br>
                <button class="btn btn-success" type="submit"><fmt:message key="save" bundle="${msg}"/></button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
