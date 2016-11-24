<%--
  Created by IntelliJ IDEA.
  User: click
  Date: 5/31/2016
  Time: 05:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<c:url value="/bootstrap.css"/>" >
</head>

<body>
    <jsp:include page="navigationElements/upperPanel.jsp"/>

    <div class="container-fluid">
        <div class="row content">
            <jsp:include page="navigationElements/leftPanel.jsp" />
            <div class="col-sm-9 section">
                <h2><small>MY PROFILE</small></h2>
                <hr>
                <form action="/user/profile" method="post">
                    <div class="form-group">
                        <h3><small>First name: </small></h3>
                        <input type='text' class="form-control" name="firstName" value="<c:out value="${user.firstName}"/>"/>
                    </div>

                    <div class="form-group">
                        <h3><small>Last name: </small></h3>
                        <input type='text' class="form-control" name="lastName" value="<c:out value="${user.lastName}"/>"/>
                    </div>

                    <div class="form-group">
                        <h3><small>Email: </small></h3>
                        <input type='text' class="form-control" name="email" value="<c:out value="${user.email}"/>"/>
                    </div>

                    <button type="submit" name="id" value="<c:out value="${user.id}"/>" class="btn btn-success">Save</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
