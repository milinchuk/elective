<%@ page import="utils.UrlHolder" %>
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
            <li><a href="/user/myCourses"> My Courses</a></li>
            <li><a href="/user/courses">Find Courses</a></li>
            <li><a href="/user/profile">Profile</a></li>
                <li><a href="/login">Login</a></li>
                <li><a href="/user/logout">Logout</a></li>
        </ul>
    </div>
</nav>