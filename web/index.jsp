<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.hibernate.cfg.AnnotationConfiguration" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="org.hibernate.Hibernate" %>
<%@ page import="javax.xml.transform.sax.SAXSource" %>
<%@ page import="il.ac.shenkar.hibernate.HibernateToDoListDAO" %>
<%@ page import="il.ac.shenkar.hibernate.ToDoServlet" %>
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
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("logged_in")){
                logged_in = cookies[i].getValue();
            }
            if (cookies[i].getName().equals("user_id")){
                id = cookies[i].getValue();
            }
        }
        if(logged_in.equals("true") && id != ""){
            request.setAttribute("tasksList", HibernateToDoListDAO.getInstance().getAllItemsByUserId(Integer.parseInt(id)));//TODO: fix getUserByUserName and add a call to dao
            request.getRequestDispatcher("/tasks.jsp").forward(request, response);
        }
    }

    %>


</h1>
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