<%--
  Created by IntelliJ IDEA.
  User: Arel
  Date: 05/01/2017
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
         pageEncoding="windows-1255" import="java.util.*,il.ac.shenkar.hibernate.Item,il.ac.shenkar.hibernate.HibernateToDoListDAO"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
        <title>Insert title here</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="style.css">

    </head>
    <body>
        <button type="button" class="btn btn-primary" onclick="location.href='/ToDoServlet?action=log_out'">Log out</button>
        <table class="table table-striped table-bordered">
            <tr>
                <th>Username</th>
                <th>ID</th>
                <th>Name</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            <%
                String user_email = "";
                int userID=0;
                double time = 0;
                Cookie[] cookies = request.getCookies();
                if(cookies != null) {
                    for(Cookie cookie : cookies){
                        if (cookie.getName().equals("user_id")) {
                            userID = Integer.parseInt(cookie.getValue());
                        }
                        else if(cookie.getName().equals("name")){
                            user_email = cookie.getValue();
                            out.println("<h1 class='name'>Hello "+user_email+"</h1>");
                        }
                        else if(cookie.getName().equals("time_elapsed")){
                            time = Double.parseDouble(cookie.getValue());
                        }
                    }
                }

                List<Item> list = (ArrayList<Item>)request.getAttribute("tasksList");
                if(list != null && list.size() != 0){
                    if(user_email.equals("administrator")){
                        out.println("<h3>There is "+list.size()+" tasks</h3>");
                    }
                    else{
                        out.println("<h3>You have "+list.size()+" tasks</h3>");
                    }
                    for(Item item : list){
            %>
            <tr>
                <form method="post" action="ToDoServlet?action=editTask&id=<%= item.getId()%>&userID=<%= item.getUserId()%>">
                    <th> <%= HibernateToDoListDAO.getInstance().getNameById(item.getUserId()) %></th>
                    <td> <%= item.getId()%></td>
                    <td><input name="title" value="<%=item.getTitle()%>"/> </td>
                    <td><input name="status" value="<%=item.getStatus()%>"/> </td>
                    <td>
                        <input type="submit" id="save_button" value="Save" class="save">
                        <input type="button" id="delete_button" value="Delete" class="delete" onclick="location.href='/ToDoServlet?action=delete&id=<%= item.getId()%>&userID=<%= item.getUserId()%>';">
                    </td>
                </form>
            </tr>
            <%
                    }
                }
            %>
            <tr>
                <form method="post" action="ToDoServlet?action=addItem&userID=<%= userID%>">
                    <tr>
                        <td><%= user_email%></td>
                        <td>id</td>
                        <td><input name="title" placeholder="Add task name" required/> </td>
                        <td><input name="status" placeholder="Add task status" required/> </td>
                        <td>
                            <input type="submit" id="add_task" value="Add task">
                        </td>
                    </tr>
                </form>
            </tr>
        </table>
        <% if(time > 0 ) out.println("<h2>This response took " + time + " seconds</h2>");%>
    </body>
</html>