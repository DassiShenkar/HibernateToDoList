<%--
  Created by IntelliJ IDEA.
  User: Arel
  Date: 05/01/2017
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
         pageEncoding="windows-1255" import="java.util.*,il.ac.shenkar.hibernate.Item"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
    <title>Insert title here</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<table class="table table-striped table-bordered">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    <%
        List<Item> list = (ArrayList<Item>)request.getAttribute("tasksList");
        for(Item item : list){
    %>
    <tr>
        <td> <%= item.getId()%></td>
        <td><%=item.getTitle()%> </td>
        <td> <%=item.getStatus()%></td>
        <td>
            <input type="button" id="edit_button1" value="Edit" class="edit" onclick="form.action='ToDoServlet?action=edit&id=<%= item.getId()%>';">
            <input type="button" id="save_button1" value="Save" class="save" onclick="form.action='ToDoServlet?action=save&id=<%= item.getId()%>';">
            <input type="button" id="delete_button" value="Delete" class="delete" onclick="form.action='ToDoServlet?action=delete&id=<%= item.getId()%>';">
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>