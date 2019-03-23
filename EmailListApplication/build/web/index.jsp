<%-- 
    Document   : index
    Created on : 23/10/2018, 04:50:00 PM
    Author     : Fer Enriquez'
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Murach's Java Servlets and JSP</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
    <jsp:include page ="/header.html" flush="true"/>
    <h1>Join our email list</h1>
    <p>To join our email list, enter your name and
        email address below.</p>
    <form action="EmailListServlet" method="post">
        <input type="hidden" name="action" value="add">

        <label>Email:</label>
        <input type="email" name="email" required><br>

        <label>First Name:</label>
        <input type="text" name="firstName" required><br>

        <label>Last Name:</label>
        <input type="text" name="lastName" required><br>

        <label>Password:</label>
        <input type="password" name="password" required><br>

        <label>&nbsp;</label>
        <input type="submit" value="Join Now" id="submit">
    </form>
    <br>
    <form action="EmailListServlet" method="post">
        <input type="hidden" name="action" value="mostrar">
        <input type="submit" value="Mostrar todos los Usuarios" id="submit">
    </form>

    <jsp:include page="/jsp/footer.jsp" flush="true"></jsp:include>
</body>
</html>