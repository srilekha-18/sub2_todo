<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
</head>
<body>

<h2>Login</h2>
<form method="post" action="./LoginServlet">
    Email: <input type="text" name="email"><br>
    Password: <input type="password" name="pass"><br>
    <input type="submit" value="Login">
</form>

<p>New User? <a href="Register.html">Sign Up</a></p>

<%
    Object error = request.getAttribute("loginError");
    if (error != null) {
%>
    <p style="color: red;"><%= error %></p>
<%
    }
%>

</body>
</html>
