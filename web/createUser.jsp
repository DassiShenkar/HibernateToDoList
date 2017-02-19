<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Cookie[] cookies = request.getCookies();
    double time = 0;
    if(cookies != null) {
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("time_elapsed")){
                time = Double.parseDouble(cookie.getValue());
            }
            }
        }
        %>
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
<% if(time > 0 ) out.println("<h2>This response took " + time + " seconds</h2>"); %>
</body>
</html>