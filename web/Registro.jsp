<%-- 
    Document   : Registro
    Created on : 16 ene 2022, 19:19:39
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
        <script src="${pageContext.request.contextPath}/js/funciones.js" type="text/javascript"></script>
        <link href="https://bootswatch.com/5/lumen/bootstrap.min.css" rel="stylesheet" >
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </head>
    <body>
        <div>
            
            
            <img src="${pageContext.request.contextPath}/img/usuario.png" alt="Logo inicio sesion"/>
            
        <form name="registro" action="/TrabajoFinal/UsuariosController/registroUsuario" method="post" onsubmit="return validarRegistro()" >

            <ul>
                
                <li>
                    <label for="nombre"> Usuario : </label>
                    <input type="text" id ="nombre" name="nombre">
                </li>
                
                
                <li>
                    <label for="correoE"> Correo electronico : </label>
                    <input type="text" id ="correoE" name="correoE" onchange="peticionSincrona()">
                </li>
                <%
                    String aux = (String)request.getAttribute("mensaje");
                    
                    if(aux != null){
                        
                        out.println("<h2>"+aux+"</h2>");
                    }
                %>
                <li>
                    
                    <label for="contrasenna"> Contraseña : </label>
                    <input type="text" id ="contrasenna" name="contrasenna">
                    
                </li>
                
                
            </ul>
            
            <input type="submit" value="Registrar usuario"/>  
            
            
            
        </form>
            
        </div>
    </body>
</html>
