<%--
  Created by IntelliJ IDEA.
  User: xiao
  Date: 2020/2/17
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<body>
<h4>Login Page</h4>
<form action="/shiro/login" method="get">
    username: <input type="text" name="username">
    <br><br>
    password: <input type="password" name="password">
    <br><br>
    <input type="submit" value="submit">
</form>
</body>
</html>
