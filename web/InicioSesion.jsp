<%-- 
    Document   : InicioSesion
    Created on : 8 ene 2022, 12:04:40
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
        <link href="https://bootswatch.com/5/lumen/bootstrap.min.css" rel="stylesheet" >
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </head>
    
    <body>
        
        <div>
            
            
            <img src="${pageContext.request.contextPath}/img/usuario.png" alt="Logo inicio sesion"/>
            
        <form action="/TrabajoFinal/UsuariosController/loginUsuario" method="post" >

            <ul>
                
                <li>
                    <label for="nombre"> Usuario : </label>
                    <input type="text" id ="nombre" name="nombre">
                </li>
                
                <li>
                    
                    <label for="contrasenna"> Contraseña : </label>
                    <input type="text" id ="contrasenna" name="contrasenna">
                    
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
            <a href ="">¿Has olvidado tu contraseña?</a>
            <a href=" ${pageContext.request.contextPath}/navegacionController/Registro">Registrarse</a>
        </div>
        
        
        
        
    
        
    </body>
</html>
