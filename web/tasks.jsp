<%@ page language="java" contentType="text/html; charset=windows-1255"
         pageEncoding="windows-1255" import="java.util.*,il.ac.shenkar.hibernate.Item,il.ac.shenkar.hibernate.HibernateToDoListDAO"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
        <title>Todo - My Tasks</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
    </head>
    <body class="tasks">
        <h1>ToDo list</h1>
        <button type="button" class="logOut btn btn-info" onclick="location.href='/ToDoServlet?action=log_out'">Log out</button>
        <table class="table table-striped table-bordered">
            <tr>
                <th>userName</th>
                <th>ID</th>
                <th>Name</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            <%
                String userName = "";
                Boolean adminFlag = false;
                int userID=0;
                double time = 0;
                Cookie[] cookies = request.getCookies();
                if(cookies != null) {
                    for(Cookie cookie : cookies){
                        if (cookie.getName().equals("user_id")) {
                            userID = Integer.parseInt(cookie.getValue());
                        }
                        else if(cookie.getName().equals("name")){
                            userName = cookie.getValue();
                            %>
                                <jsp:useBean id="user" class="il.ac.shenkar.hibernate.User" />
                                <jsp:setProperty name ="user" property="username" value='<%= userName%>' />
                            <%
                        }
                        else if(cookie.getName().equals("time_elapsed")){
                            time = Double.parseDouble(cookie.getValue());
                        }
                    }
                }

                List<Item> list = (ArrayList<Item>)request.getAttribute("tasksList");
                if(list != null && list.size() != 0){
                    out.print("<h2 class='name'>Hello ");%>
                    <jsp:getProperty name='user' property='username'/>
            <%
                    if(userName.equals("administrator")){
                        adminFlag = true;
                        out.println(", There is "+list.size()+" tasks</h2>");
                    }
                    else{
                        out.println(", You have "+list.size()+" tasks</h2>");
                    }
                    for(Item item : list){
            %>
            <tr>
                <form method="post" action="ToDoServlet?action=editTask&id=<%= item.getId()%>&userID=<%= item.getUserId()%>&admin=<%= adminFlag %>">
                    <td> <%= HibernateToDoListDAO.getInstance().getNameById(item.getUserId()) %></td>
                    <td> <%= item.getId()%></td>
                    <td><input name="title" value="<%=item.getTitle()%>"/> </td>
                    <td><input name="status" value="<%=item.getStatus()%>"/> </td>
                    <td>
                        <button type="submit" id="update_button" class="btn btn-success">Update</button>
                        <button type="button" id="delete_button" value="Delete" class="btn btn-danger" onclick="location.href='/ToDoServlet?action=delete&id=<%= item.getId()%>&userID=<%= item.getUserId()%>&admin=<%= adminFlag %>';">Delete</button>
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
                        <th><%= userName%></th>
                        <th>id</th>
                        <th><input name="title" placeholder="Add task name" required/> </th>
                        <th><input name="status" placeholder="Add task status" required/> </th>
                        <th>
                            <button type="submit" id="add_task" class="btn btn-primary">Add task</button>
                        </th>
                    </tr>
                </form>
            </tr>
        </table>
        <% if(time > 0 ) out.println("<h4>This response took " + time + " seconds</h4>"); %>
    </body>
</html>