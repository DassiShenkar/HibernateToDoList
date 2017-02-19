
<%@ page import="il.ac.shenkar.hibernate.HibernateToDoListDAO" %>
<%@ page import="il.ac.shenkar.hibernate.User" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Insert title here</title>
</head>
<body>
<p>if you re not  signed up user <a href="createUser.jsp">click here</a></p>
<h1>
    <% Cookie[] cookies = request.getCookies();
    String logged_in = "";
    String id = "";
    if(cookies != null) {
        for(Cookie cookie : cookies){
            if (cookie.getName().equals("logged_in")){
                logged_in = cookie.getValue();
            }
            if (cookie.getName().equals("user_id")){
                id = cookie.getValue();
            }
        }
        if(logged_in.equals("true") && id != ""){
            User user = HibernateToDoListDAO.getInstance().getUserById(Integer.parseInt(id));
            if(user.getUserName().equals("administrator"))
                request.setAttribute("tasksList", HibernateToDoListDAO.getInstance().getAllTasks());
            else
                request.setAttribute("tasksList", HibernateToDoListDAO.getInstance().getAllItemsByUserId(Integer.parseInt(id)));
            request.getRequestDispatcher("/tasks.jsp").forward(request, response);
        }
    }
    %>


</h1>
<form method="post" action="ToDoServlet?action=login">
  <label>Email<input type="text" name="email" required></label>
  <label>Password<input type="password" name="password" required></label>
  <input type="submit" value="LogIn">
</form>
<h1 name="status">
  <%  String name = (String)request.getAttribute("status");
    if (name == null) name = "";
  %>
  <%= name %></h1>
</body>
</html>