<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <title>Error page</title>
    </head>
    <body id="errorPage">
    <section class="details">
        <h1>ToDo list</h1>
        <p><%= (String) request.getAttribute("exception")%></p>
        </section>
    </body>
</html>