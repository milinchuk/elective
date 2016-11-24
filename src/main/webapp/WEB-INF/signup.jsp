<%@ page import="utils.constants.AttributesHolder" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sign Up</title>
    <link rel="stylesheet" href="<c:url value="/bootstrap.css"/>" >
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jquerymobile/1.4.5/jquery.mobile.min.css">
    <%--<link href="<c:url value="/css/bootstrap.css.map"/>" type="text/css" rel="stylesheet">--%>
    <%--<link href="<c:url value="/css/bootstrap.min.css"/>" type="text/css" rel="stylesheet">--%>
    <%--<link href="<c:url value="/css/bootstrap.min.css.map"/>" type="text/css" rel="stylesheet">--%>
    <%--<link href="<c:url value="/css/bootstrap-theme.css.map"/>" type="text/css" rel="stylesheet">--%>
    <%--<link href="<c:url value="/css/bootstrap-theme.min.css"/>" type="text/css" rel="stylesheet">--%>
    <%--<link href="<c:url value="/css/bootstrap-theme.min.css.map"/>" type="text/css" rel="stylesheet">--%>
    <%--<link href="<c:url value="/css/bootstrap-theme.css" />" type="text/css" rel="stylesheet">--%>
    <%--<link href="<c:url value="/css/style.css" />" type="text/css" rel="stylesheet">--%>
    <%--<link rel="stylesheet" type="text/css" href="<c:url value="css/bootstrap-wysihtml5.css"/>" />--%>

    <%--<jsp:include page="navigationElements/styles.jsp"/>--%>
    <style>
        .authorization-section {
            width: 50%;
        }
        .center {
            float: none;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>

<body>
    <jsp:include page="navigationElements/upperPanel.jsp"/>

    <div class="container">
        <div class="center jumbotron authorization-section">
            <form action="" method="post">
                <h2 class="form-signin-heading">Please sign up</h2>
                <c:forEach items="${errors.messages}" var="msg">
                    <p>${msg}</p>
                </c:forEach>
                <br>
                <input type="text" class="form-control" name="<%=AttributesHolder.FIRST_NAME%>" value="<c:out value="${user.firstName}"/>" placeholder="First name" required>
                <br>
                <input type="text" class="form-control" name="<%=AttributesHolder.LAST_NAME%>" value="<c:out value="${user.lastName}"/>" placeholder="Last name" required >
                <br>
                <div class="form-group">
                    <select class="form-control" name="role" id="sel1">
                        <option>Choose your role ...</option>
                        <option>student</option>
                        <option>tutor</option>
                    </select>
                </div>
                <input type="text" class="form-control" name="<%=AttributesHolder.EMAIL%>" value="<c:out value="${user.email}"/>" placeholder="Email address" required >
                <br>
                <input type="password" class="form-control" name="<%=AttributesHolder.PASSWORD%>" placeholder="Password" required >
                <br>
                <input type="password" class="form-control" name="<%=AttributesHolder.CONFIRM_PASSWORD%>" placeholder="Confirm Password" required >
                <br>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
                <h5>Have an account? <a href="/login">Login here!</a>
                </h5>
            </form>
        </div>
    </div>
</body>
</html>
