<%-- 
    Document   : showRoles
    Created on : 13 ene 2022, 18:02:41
    Author     : False
--%>

<%@page import="java.util.List"%>
<%@page import="persistencia.Roles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <body>

        <h1>Roles</h1>
        <%
            List<Roles> lr = (List<Roles>) request.getAttribute("roles");
            if (lr.size() > 0) {
                out.println("<table border='1'>");
                for (Roles r : lr) {
                    out.println("<tr>");
                    out.println("<td>" + r.getNombre() + "</td>");
                    out.println("<td><a href='/javaWebApp2/Roles/delete?id=" + r.getId() + "'>Delete</a>");
                    out.println("<td><a href='/javaWebApp2/Roles/edit?id=" + r.getId() + "'>Edit</a>");
                    out.println("</td>");

                }
                out.println("</table>");
            } else {
                out.println("<p>No hay Roles</p>");
            }

        %>
        <hr />
        <nav> |<a href="/javaWebApp2/"> Inicio </a>
            |<a href="/javaWebApp2/Roles/altaRol"> Nuevo Rol </a> |
        </nav>    
    </body>
    </body>
</html>
