<%@ page import="utils.constants.UrlHolder" %>
<%@ page import="utils.constants.AttributesHolder" %>
<%@ page import="model.entity.User" %>
<%@ page import="i18n.LocaleHolder" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: click
  Date: 5/18/2016
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[AttributesHolder.LOCALE]}" />
<fmt:setBundle basename="system" var="msg"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><fmt:message key="logo" bundle="${msg}"/></a>
        </div>
        <ul class="nav navbar-nav">
            <c:if test="${sessionScope[AttributesHolder.ROLE] eq User.STUDENT}">
                <li><a href="${UrlHolder.FIND}"><fmt:message key="find.courses" bundle="${msg}"/></a></li>
                <li><a href="${UrlHolder.MY_COURSES}"><fmt:message key="my.courses" bundle="${msg}"/></a></li>
                <li><a href="${UrlHolder.PROFILE}"><fmt:message key="profile" bundle="${msg}"/></a></li>
                <li>
                    <a href="">
                    <form action="${UrlHolder.LOGOUT}" method="post">
                    <input style="border: none; background-color: transparent" type="submit" value="<fmt:message key="logout" bundle="${msg}"/>"/>
                    </form>
                    </a>
                </li>
            </c:if>
            <c:if test="${sessionScope[AttributesHolder.ROLE] eq User.TUTOR}">
                <li><a href="${UrlHolder.MY_COURSES}"><fmt:message key="my.courses" bundle="${msg}"/></a></li>
                <li><a href="${UrlHolder.PROFILE}"><fmt:message key="profile" bundle="${msg}"/></a></li>
                <li>
                    <a href="">
                        <form action="${UrlHolder.LOGOUT}" method="post">
                            <input style="border: none; background-color: transparent" type="submit" value="<fmt:message key="logout" bundle="${msg}"/>"/>
                        </form>
                    </a>
                </li>
            </c:if>
            <c:if test="${empty sessionScope[AttributesHolder.ID]}"><li><a href="${UrlHolder.LOGIN}">
                <fmt:message key="login" bundle="${msg}"/></a></li>
            </c:if>

            <c:forEach items="${LocaleHolder.SUPPORTED}" var="locale"> |
                <a href="?${AttributesHolder.LANG}=${locale.language}">${locale.language}</a>
            </c:forEach>
        </ul>
    </div>
</nav>