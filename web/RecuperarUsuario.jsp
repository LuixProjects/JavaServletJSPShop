<%-- 
    Document   : RecuperarUsuario
    Created on : 19 ene 2022, 20:41:46
    Author     : False
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="
              <%
                  out.println(request.getContextPath());
              %>/css/InicioSesion.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
       
        <div>
            
            
            <img src="${pageContext.request.contextPath}/img/usuario.png" alt="Logo inicio sesion"/>
            
        <form action="/TrabajoFinal/UsuariosController/loginUsuario" method="post" >

            <ul>
                
                <li>
                    <label for="nombre"> Correo Electronico : </label>
                    <input type="text" id ="nombre" name="nombre">
                </li>

                
            </ul>
            
            <input type="submit" value="Login"/>  
            
            <h2>
                <%
                    
                String mensaje = (String)request.getAttribute("mensaje");
                System.out.println("el mensaje es : " + mensaje);
                if (mensaje == null)
                    mensaje = "";
                out.println(mensaje);
                %>
                
                
            </h2>
            
        </form>
            
        </div>
        
        
        
    </body>
</html>
