<%@ page import="utils.constants.UrlHolder" %>
<%@ page import="utils.constants.AttributesHolder" %>
<%@ page import="utils.constants.PagesHolder" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        }
    </style>
</head>

<body>
<jsp:include page="navigationElements/upperPanel.jsp"/>

<div class="container">
    <div class="center jumbotron authorization-section">
        <form action="<%=UrlHolder.LOGIN%>" method="post">
            <h2 class="form-signin-heading">Please log in</h2>
            <br>
            <div class="alert-danger">
                <c:out value="${errors.messages['email']}" />
            </div>
            <input type="text" class="form-control" name="<%=AttributesHolder.EMAIL%>" placeholder="Email address" required autofocus >
            <br>
            <div class="alert-danger">
                <c:out value="${errors.messages['password']}" />
            </div>
            <input type="password" class="form-control" name="<%=AttributesHolder.PASSWORD%>" placeholder="Password" required >
            <br>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
            <h5>Not register yet? <a href="<%=UrlHolder.SIGNUP%>">Sign Up!</a>
            </h5>
        </form>
    </div>
</div>

</body>
</html>
