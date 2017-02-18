<%--
  Created by IntelliJ IDEA.
  User: Arel
  Date: 05/01/2017
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
</head>
<body>
<p>enter your personal information for sign up:</p>
<form method="post" action="ToDoServlet?action=create">
    <label>Username<input type="text" name="email"></label>
    <label>Password<input type="password" name="password"></label>
    <input type="submit" value="Sign Up">
</form>
</body>
</html>