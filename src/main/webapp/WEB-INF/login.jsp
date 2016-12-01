<%@ page import="utils.constants.UrlHolder" %>
<%@ page import="utils.constants.AttributesHolder" %>
<%@ page import="utils.constants.PagesHolder" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login</title>

    <link rel="stylesheet"type="text/css" href="<c:url value="/bootstrap.css"/>" >
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jquerymobile/1.4.5/jquery.mobile.min.css">
    <style>
        .authorization-section {
            width: 50%;
        }
        .center {
            float: none;
            margin-left: auto;
            margin-right: auto;
        }
        .alrt {
            margin-bottom: 5px;
            padding-left: 15px;
            border-radius: 3px;
            font-size: smaller;
        }
    </style>
</head>

<body>
<jsp:include page="navigationElements/upperPanel.jsp"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[AttributesHolder.LOCALE]}" />
<fmt:setBundle basename="user" var="msg"/>

<div class="container">
    <div class="center jumbotron authorization-section">
        <form action="${UrlHolder.LOGIN}" method="post">


            <h2 class="form-signin-heading"><fmt:message key="login.msg" bundle="${msg}"/><br/></h2>
            <br>
            <div class="alrt alert-danger">
                <c:if test="${errors != null && !errors.result}">
                    <fmt:message key="${errors.messages['email']}" bundle="${msg}"/>
                </c:if>
            </div>
            <input type="text" class="form-control" name="${AttributesHolder.EMAIL}" placeholder="<fmt:message key="email" bundle="${msg}"/>" required autofocus >
            <br>
            <div class="alrt alert-danger">
                <c:if test="${errors != null && errors.messages['password'] != null}">
                    <fmt:message key="${errors.messages['password']}" bundle="${msg}"/>
                </c:if>
            </div>
            <input type="password" class="form-control" name="${AttributesHolder.PASSWORD}" placeholder="<fmt:message key="password" bundle="${msg}"/>" required >
            <br>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login" bundle="${msg}"/></button>
            <h5><fmt:message key="not.register" bundle="${msg}"/> <a href="${UrlHolder.SIGNUP}"><fmt:message key="signup" bundle="${msg}"/>!</a>
            </h5>
        </form>
    </div>
</div>

</body>
</html>
