<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Insert title here</title>
</head>
<body>
<p>if you re not  signed up user <a href="createUser.jsp">click here</a></p>

<form method="post" action="ToDoServlet?action=login">
  <label>Email<input type="text" name="email"></label>
  <label>Password<input type="password" name="password"></label>
  <input type="submit" value="LogIn">
</form>
<h1 name="status">
  <%  String name = (String)request.getAttribute("status");
    if (name == null) name = "";
  %>
  <%= name %></h1>
</body>
</html>