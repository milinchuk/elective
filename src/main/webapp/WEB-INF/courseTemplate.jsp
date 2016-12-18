<%--
  Created by IntelliJ IDEA.
  User: click
  Date: 12/10/2016
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="model.entity.Course" %>
<%@ page import="utils.constants.AttributesHolder" %>
<%@ page import="utils.constants.UrlHolder" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[AttributesHolder.LOCALE]}" />
<fmt:setBundle basename="course" var="msg"/>

<input name="${AttributesHolder.ID}" value="<c:out value="${course.id}"/>" hidden/>
<label for="name"><fmt:message key="name" bundle="${msg}"/></label>
<c:if test="${errors != null && errors.messages['name'] != null}">
    <div class="alrt alert-danger">
        <fmt:message key="${errors.messages['name']}" bundle="${msg}" />
    </div>
</c:if>
<input id="name" class="form-control" name="${AttributesHolder.NAME}" value="<c:out value="${course.name}"/>" />
<br>
<label for="name"><fmt:message key="about" bundle="${msg}"/></label>
<c:if test="${errors != null && errors.messages['about'] != null}">
    <div class="alrt alert-danger">
        <fmt:message key="${errors.messages['about']}" bundle="${msg}" />
    </div>
</c:if>
<textarea name="${AttributesHolder.ABOUT}" style="resize: none;" class="form-control" rows="3"><c:out value="${course.about}"/></textarea>

<br>
<label for="name"><fmt:message key="start.date" bundle="${msg}"/></label>
<c:if test="${errors != null && errors.messages['startDate'] != null}">
    <div class="alrt alert-danger">
        <fmt:message key="${errors.messages['startDate']}" bundle="${msg}" />
    </div>
</c:if>
<div class="row">
    <div class='col-sm-6'>
        <div class="form-group">
            <div class='input-group date'  id='startDate'>
                <input type="text" name="startDate" class="form-control" value="<c:out value="${course.startDateToString}"/>"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
            </div>
        </div>
    </div>
</div>
<label for="name"><fmt:message key="end.date" bundle="${msg}"/></label>
<c:if test="${errors != null && errors.messages['endDate'] != null}">
    <div class="alrt alert-danger">
        <fmt:message key="${errors.messages['endDate']}" bundle="${msg}" />
    </div>
</c:if>
<div class="row">
    <div class='col-sm-6'>
        <div class="form-group">
            <div class='input-group date' id='endDate'>
                <input type="text" name="endDate" class="form-control" value="<c:out value="${course.endDateToString}"/>" />
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
</div>
<br>
<button class="btn btn-success" type="submit"><fmt:message key="save" bundle="${msg}"/></button>