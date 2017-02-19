
<%@ page import="il.ac.shenkar.hibernate.HibernateToDoListDAO" %>
<%@ page import="il.ac.shenkar.hibernate.User" %>
<%@ page import="il.ac.shenkar.hibernate.Time" %>
<% Time t = Time.getInstance();
    t.startCount(); %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" media="screen">

</head>
<body>
<p>if you are not signed up user <a href="createUser.jsp">click here</a></p>
<h1>
    <% Cookie[] cookies = request.getCookies();
    String logged_in = "";
    String id = "";
    double time = 0;
    if(cookies != null) {
        for(Cookie cookie : cookies){
            if (cookie.getName().equals("logged_in")){
                logged_in = cookie.getValue();
            }
            else if (cookie.getName().equals("user_id")){
                id = cookie.getValue();
            }
            else if(cookie.getName().equals("time_elapsed")){
                time = Double.parseDouble(cookie.getValue());
            }
        }
        if(logged_in.equals("true") && id != ""){
            User user = HibernateToDoListDAO.getInstance().getUserById(Integer.parseInt(id));
            if(user.getUserName().equals("administrator"))
                request.setAttribute("tasksList", HibernateToDoListDAO.getInstance().getAllTasks());
            else
                request.setAttribute("tasksList", HibernateToDoListDAO.getInstance().getAllItemsByUserId(Integer.parseInt(id)));
            Cookie seconds_cookie = new Cookie("time_elapsed",Double.toString(t.computeTime()));
            seconds_cookie.setMaxAge(60*60*24);   // 24 hours
            response.addCookie(seconds_cookie);
            RequestDispatcher view = request.getRequestDispatcher("tasks.jsp");
            view.forward(request, response);
            request.getRequestDispatcher("/tasks.jsp").forward(request, response);
        }
    }
    %>


</h1>
<form method="post" action="ToDoServlet?action=login">
  <label>Email<input type="text" name="email" required></label>
  <label>Password<input type="text" name="password" required></label>
  <input type="submit" value="LogIn">
</form>
<h1 name="status">
  <%  String name = (String)request.getAttribute("status");
    if (name == null) name = "";
  %>
  <%= name %></h1>
<% if(time > 0 ) out.println("<h2>This response took " + time + " seconds</h2>"); %>
</body>
</html>
