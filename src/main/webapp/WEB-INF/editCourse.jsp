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
    <link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap.css"/>" >
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript">
        function addYears(id) {
            var div = document.getElementById(id);
            var val;
            for (i = 2010; i <= 2020; i++) {
                val += ("<option>" + i + "</option>");
            }
            div.innerHTML = val;
        }
        function addDays(id) {
            var div = document.getElementById(id);
            var val;
            for (i = 1; i <= 31; i++) {
                val += ("<option>" + i + "</option>");
            }
            div.innerHTML = val;
        }

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
            <h2><small><fmt:message key="msg.edit.course" bundle="${msg}"/></small></h2>
            <hr>
            <form action="${UrlHolder.COURSE_EDIT}" method="post">
                <br>
                <input name="${AttributesHolder.ID}" value="<c:out value="${course.id}"/>" hidden/>
                <c:if test="${errors != null && errors.messages['name'] != null}">
                    <div class="alrt alert-danger">
                        <fmt:message key="${errors.messages['name']}" bundle="${msg}" />
                    </div>
                </c:if>
                <label for="name"><fmt:message key="name" bundle="${msg}"/></label>
                <input name="${AttributesHolder.NAME}" id="name" class="form-control" value="<c:out value="${course.name}"/>"/>

                <br>
                <c:if test="${errors != null && errors.messages['about'] != null}">
                    <div class="alrt alert-danger">
                        <fmt:message key="${errors.messages['about']}" bundle="${msg}" />
                    </div>
                </c:if>
                <label><fmt:message key="about" bundle="${msg}"/></label>
                <textarea name="${AttributesHolder.ABOUT}" style="resize: none;" class="form-control" rows="3"><c:out value="${course.about}"/>
                </textarea>

                <%--<c:if test="${errors != null && errors.messages['startDate'] != null}">--%>
                    <%--<div class="alrt alert-danger">--%>
                        <%--<fmt:message key="${errors.messages['startDate']}" bundle="${msg}" />--%>
                    <%--</div>--%>
                <%--</c:if>--%>
                <%--<label for="name"><fmt:message key="start.date" bundle="${msg}"/></label>--%>
                <%--<div class="form-group">--%>
                    <%--<label for="day">Day:</label>--%>
                    <%--<select class="form-control" onclick="addDays('startDay')" name="${AttributesHolder.START_DAY}"--%>
                            <%--id="startDay" >--%>
                        <%--<option>1</option>--%>
                    <%--</select>--%>

                    <%--<label for="month">Month:</label>--%>
                    <%--<select class="form-control" name="${AttributesHolder.START_MONTH}" id="startMonth" >--%>
                        <%--<c:forEach items="${DateHolder.MONTHS}" var="month">--%>
                            <%--<option>--%>
                                <%--<fmt:message key="${month}" bundle="${msg}" />--%>
                            <%--</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>

                    <%--<label for="year">Year:</label>--%>
                    <%--<select class="form-control" onclick="addYears('startYear')" name="${AttributesHolder.START_YEAR}"--%>
                            <%--id="startYear" >--%>
                        <%--<option>2016</option>--%>
                    <%--</select>--%>
                <%--</div>--%>

                <%--<c:if test="${errors != null && errors.messages['endDate'] != null}">--%>
                    <%--<div class="alrt alert-danger">--%>
                        <%--<fmt:message key="${errors.messages['endDate']}" bundle="${msg}" />--%>
                    <%--</div>--%>
                <%--</c:if>--%>
                <%--<label for="name"><fmt:message key="end.date" bundle="${msg}"/></label>--%>
                <%--<div class="form-group">--%>
                    <%--<label for="day">Day:</label>--%>
                    <%--<select class="form-control" onclick="addDays('day')" name="${AttributesHolder.END_DAY}" id="day">--%>
                        <%--<option>1</option>--%>
                    <%--</select>--%>

                    <%--<label for="month">Month:</label>--%>
                    <%--<select class="form-control" name="${AttributesHolder.END_MONTH}" id="month">--%>
                        <%--<c:forEach items="${DateHolder.MONTHS}" var="month">--%>
                            <%--<option>--%>
                                <%--<fmt:message key="${month}" bundle="${msg}" />--%>
                            <%--</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>

                    <%--<label for="year">Year:</label>--%>
                    <%--<select class="form-control" onclick="addYears('year')" name="${AttributesHolder.END_YEAR}" id="year">--%>
                        <%--<option>2016</option>--%>
                    <%--</select>--%>
                <%--</div>--%>

                <button class="btn btn-success" type="submit"><fmt:message key="save" bundle="${msg}"/></button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
