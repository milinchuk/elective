<%--
  Created by IntelliJ IDEA.
  User: click
  Date: 5/31/2016
  Time: 05:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="utils.constants.UrlHolder" %>
<%@ page import="utils.constants.AttributesHolder" %>
<%@ page import="utils.constants.PagesHolder" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<c:url value="/bootstrap.css"/>" >
</head>

<body>
    <jsp:include page="navigationElements/upperPanel.jsp"/>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope[AttributesHolder.LOCALE]}" />
    <fmt:setBundle basename="user" var="msg"/>

    <div class="container-fluid">
        <div class="row content">
            <jsp:include page="navigationElements/leftPanel.jsp" />
            <div class="col-sm-9 section">
                <h2><small><fmt:message key="my.profile" bundle="${msg}"/></small></h2>
                <hr>
                <form action="${UrlHolder.PROFILE}" method="post">
                    <c:if test="${errors != null && errors.messages['firstName'] != null}">
                        <div class="alrt alert-danger">
                            <fmt:message key="${errors.messages['firstName']}" bundle="${msg}" />
                        </div>
                    </c:if>
                    <div class="form-group">
                        <h3><small><fmt:message key="first.name" bundle="${msg}"/>: </small></h3>
                        <input type='text' class="form-control" name="${AttributesHolder.FIRST_NAME}" value="<c:out value="${user.firstName}"/>"/>
                    </div>

                    <c:if test="${errors != null && errors.messages['lastName'] != null}">
                        <div class="alrt alert-danger">
                            <fmt:message key="${errors.messages['lastName']}" bundle="${msg}" />
                        </div>
                    </c:if>
                    <div class="form-group">
                        <h3><small><fmt:message key="last.name" bundle="${msg}"/>: </small></h3>
                        <input type='text' class="form-control" name="${AttributesHolder.LAST_NAME}" value="<c:out value="${user.lastName}"/>"/>
                    </div>

                    <c:if test="${errors != null && errors.messages['email'] != null}">
                        <div class="alrt alert-danger">
                            <fmt:message key="${errors.messages['email']}" bundle="${msg}" />
                        </div>
                    </c:if>
                    <div class="form-group">
                        <h3><small><fmt:message key="email" bundle="${msg}"/>: </small></h3>
                        <input type='text' class="form-control" name="${AttributesHolder.EMAIL}" value="<c:out value="${user.email}"/>"/>
                    </div>

                    <button type="submit" name="${AttributesHolder.ID}" value="<c:out value="${user.id}"/>" class="btn btn-success">
                        <fmt:message key="save" bundle="${msg}"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
