<%@ page import="utils.constants.UrlHolder" %>
<%@ page import="utils.constants.AttributesHolder" %>
<%@ page import="utils.constants.PagesHolder" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sign Up</title>
    <link rel="stylesheet" href="<c:url value="/bootstrap.css"/>" >
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
    <jsp:include page="../navigationElements/upperPanel.jsp"/>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setLocale value="${sessionScope[AttributesHolder.LOCALE]}" />
    <fmt:setBundle basename="user" var="msg"/>

    <div class="container">
        <div class="center jumbotron authorization-section">
            <form action="" method="post">
                <h2 class="form-signin-heading"><fmt:message key="signup" bundle="${msg}"/></h2>
                <br>
                <c:if test="${errors != null && errors.messages['firstName'] != null}">
                    <div class="alrt alert-danger">
                        <fmt:message key="${errors.messages['firstName']}" bundle="${msg}"/>
                    </div>
                </c:if>
                <input type="text" class="form-control" name="${AttributesHolder.FIRST_NAME}" value="<c:out value="${user.firstName}"/>"
                       placeholder="<fmt:message key="first.name" bundle="${msg}"/>" required>

                <br>
                <c:if test="${errors != null && errors.messages['lastName'] != null}">
                    <div class="alrt alert-danger">
                        <fmt:message key="${errors.messages['lastName']}" bundle="${msg}" />
                    </div>
                </c:if>
                <input type="text" class="form-control" name="${AttributesHolder.LAST_NAME}"
                       value="<c:out value="${user.lastName}"/>"
                       placeholder="<fmt:message key="last.name" bundle="${msg}"/>" required >

                <br>
                <c:if test="${errors != null && errors.messages['role'] != null}">
                    <div class="alrt alert-danger">
                        <fmt:message key="${errors.messages['role']}" bundle="${msg}" />
                    </div>
                </c:if>
                <div class="form-group">
                    <select class="form-control" name="role" id="sel1">
                        <option><fmt:message key="choose.role" bundle="${msg}"/></option>
                        <option><c:out value="${AttributesHolder.STUDENT}"/></option>
                        <option><c:out value="${AttributesHolder.TUTOR}"/></option>
                    </select>
                </div>

                <c:if test="${errors != null && errors.messages['email'] != null}">
                    <div class="alrt alert-danger">
                        <fmt:message key="${errors.messages['email']}" bundle="${msg}" />
                    </div>
                </c:if>
                <input type="text" class="form-control" name="${AttributesHolder.EMAIL}" value="<c:out value="${user.email}"/>"
                       placeholder="<fmt:message key="email" bundle="${msg}" />" required >
                <br>
                <c:if test="${errors != null && errors.messages['password'] != null}">
                    <div class="alrt alert-danger">
                        <fmt:message key="${errors.messages['password']}" bundle="${msg}"/>
                    </div>
                </c:if>
                <input type="password" class="form-control" name="${AttributesHolder.PASSWORD}" placeholder="<fmt:message key="password" bundle="${msg}" />" required >
                <br>
                <input type="password" class="form-control" name="${AttributesHolder.CONFIRM_PASSWORD}" placeholder="<fmt:message key="confirm.password" bundle="${msg}"/>" required >
                <br>
                <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="signup" bundle="${msg}"/></button>
                <h5><fmt:message key="have.account" bundle="${msg}"/> <a href="${UrlHolder.LOGIN}"><fmt:message key="login.here" bundle="${msg}"/></a>
                </h5>
            </form>
        </div>
    </div>
</body>
</html>
