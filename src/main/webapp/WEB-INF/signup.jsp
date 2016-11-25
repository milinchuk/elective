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
            <form action="" method="post">
                <h2 class="form-signin-heading">Please sign up</h2>
                <br>
                <div class="alrt alert-danger">
                    <strong>!</strong> <c:out value="${errors.messages['firstName']}"/>
                </div>
                <input type="text" class="form-control" name="<%=AttributesHolder.FIRST_NAME%>" value="<c:out value="${user.firstName}"/>" placeholder="First name" required>

                <br>
                <div class="alert-danger">
                    <strong>!</strong> <c:out value="${errors.messages['lastName']}" />
                </div>
                <input type="text" class="form-control" name="<%=AttributesHolder.LAST_NAME%>" value="<c:out value="${user.lastName}"/>" placeholder="Last name" required >

                <br>
                <div class="alert-danger">
                    <strong>!</strong>  <c:out value="${errors.messages['role']}" />
                </div>
                <div class="form-group">
                    <select class="form-control" name="role" id="sel1">
                        <option>Choose your role ...</option>
                        <option>student</option>
                        <option>tutor</option>
                    </select>
                </div>
                <div class="alert-danger">
                    <strong>!</strong> <c:out value="${errors.messages['email']}" />
                </div>
                <input type="text" class="form-control" name="<%=AttributesHolder.EMAIL%>" value="<c:out value="${user.email}"/>" placeholder="Email address" required >
                <br>
                <div class="alert-danger">
                    <strong>!</strong> <c:out value="${errors.messages['password']}" />
                </div>
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
