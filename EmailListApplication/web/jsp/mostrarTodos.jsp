<%-- 
    Document   : mostrarTodos
    Created on : 19/10/2018, 10:52:38 AM
    Author     : Fer Enriquez'
--%>
<%@ page isELIgnored="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
 <title>Mostrar todos los usuarios</title>
 <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
    <body>
        <jsp:include page="/header.html" flush="true"/>
        <h1>Users</h1>
        
        <table>
            <tr>
                <th>Email</th>
                <th>Name</th>
                <th>Last Name</th>
                <th>Password</th>
            </tr>
            <c:forEach var="usuario" items="${usuarios}">
                <tr>
                    <td>${usuario.email}</td>
                    <td>${usuario.firstName}</td>
                    <td>${usuario.lastName}</td>
                    <td>${usuairo.password}</td>
                </tr>
            </c:forEach>
        </table>

        <form action="" method="get">
            <input type="hidden" name="action" value="join">
            <input type="submit" value="Return">
        </form>
        <jsp:include page="/jsp/footer.jsp" flush="flus"></jsp:include>
    </body>
</html>
