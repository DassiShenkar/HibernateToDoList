<%@ page import="il.ac.shenkar.hibernate.model.dao.HibernateToDoListDAO" %>
<%@ page import="il.ac.shenkar.hibernate.model.User" %>
<%@ page import="il.ac.shenkar.hibernate.controller.utils.Time" %>
<% Time t = new Time(0,0);
    t.startCount();
%>

<!DOCTYPE html>
<html>
    <head>
      <meta charset="utf-8">
      <title>ToDo Log in</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
    </head>
    <body class="index">
        <section class="details">
            <h1>ToDo list</h1>
            <%  Cookie[] cookies = request.getCookies();
                String logged_in = "";
                String id = "";
                if(cookies != null) {
                    for(Cookie cookie : cookies){
                        if (cookie.getName().equals("logged_in")){
                            logged_in = cookie.getValue();
                        }
                        else if (cookie.getName().equals("user_id")){
                            id = cookie.getValue();
                        }
                    }
                    if(logged_in.equals("true") && !id.equals("")){
                        User user = HibernateToDoListDAO.getInstance().getUserById(Integer.parseInt(id));
                        if(user.getUsername().equals("administrator"))
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
            <form class="register_form" method="post" action="ToDoServlet?action=login">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="text" class="form-control" id="email" placeholder="Enter your email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="text" class="form-control" id="password" placeholder="Enter your password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Log In</button>
            </form>
            <p>Don't have an account? &nbsp<a href="createUser.jsp">Sign Up</a></p>
            <h4 class="status" name="status">
                <%  String name = (String)request.getAttribute("status");  // if user not exist will display message
                    if (name == null) name = "";
                %>
                <%= name %>
            </h4>
        </section>
    </body>
</html>