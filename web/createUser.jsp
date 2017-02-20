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
    <title>ToDo Sign up</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>
<section class="details">
    <h1>ToDo list</h1>
    <h4>Please enter your details:</h4>
    <form method="post" action="ToDoServlet?action=create">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" placeholder="Enter your username" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="text" class="form-control" id="password" placeholder="Enter your password" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary">Sign Up</button>
    </form>
    <% if(time > 0 ) out.println("<h4>This response took " + time + " seconds</h4>"); %>
</section>
</body>
</html>