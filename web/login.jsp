<%--
  Created by IntelliJ IDEA.
  User: steel
  Date: 2019-11-03
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form action="./login" method="post">
    <div class="form-group">
        <label for="username">Username</label>
        <input id="username" class="form-control" name="username"/>
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input id="password" type="password" name="password"/>
    </div>
    <div class="form-group">
        <input class="btn btn-success" type="submit" value="Login">
    </div>
    <c:if test="${not empty message}">
        <small style="color:red;">${message}</small>
    </c:if>
</form>
</body>
</html>
