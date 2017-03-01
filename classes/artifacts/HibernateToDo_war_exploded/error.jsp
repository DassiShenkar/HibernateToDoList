<%@ taglib uri="/WEB-INF/tlds/custom.tld" prefix="ex" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <title>Error page</title>
    </head>
    <body id="errorPage">
    <section class="details">
        <h1><ex:title/></h1>
        <p><%= (String) request.getAttribute("exception")%></p>
        </section>
    </body>
</html>