<%@ page import="utils.constants.UrlHolder" %>
<%@ page import="utils.constants.AttributesHolder" %>
<%@ page import="model.entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: click
  Date: 5/18/2016
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Elective</a>
        </div>
        <ul class="nav navbar-nav">
            <c:if test="<%=((String.valueOf(request.getSession().getAttribute(AttributesHolder.ROLE)).equals(String.valueOf(User.STUDENT))))%>">
                <li><a href="<%=UrlHolder.FIND%>">Find Courses</a></li>
                <li><a href="<%=UrlHolder.MY_COURSES%>"> My Courses</a></li>
                <li><a href="<%=UrlHolder.PROFILE%>">Profile</a></li>
                <li><a href="<%=UrlHolder.LOGOUT%>">Logout</a></li>
            </c:if>
            <c:if test="<%=(String.valueOf(request.getSession().getAttribute(AttributesHolder.ROLE)).equals(String.valueOf(User.TUTOR)))%>">
                <li><a href="<%=UrlHolder.MY_COURSES%>"> My Courses</a></li>
                <li><a href="<%=UrlHolder.PROFILE%>">Profile</a></li>
                <li><a href="<%=UrlHolder.LOGOUT%>">Logout</a></li>
            </c:if>
            <c:if test="<%=request.getSession().getAttribute(AttributesHolder.ID) == null %>"><li><a href="<%=UrlHolder.LOGIN%>">Login</a></li></c:if>
        </ul>
    </div>
</nav>