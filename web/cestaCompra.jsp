<%-- 
    Document   : cestaCompra
    Created on : 19 ene 2022, 17:16:48
    Author     : False
--%>

<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="persistencia.Usuarios"%>
<%@page import="persistencia.Items"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="
              <%
                  out.println(request.getContextPath());
              %>/css/styles.css" rel="stylesheet" type="text/css" />
        <link href="https://bootswatch.com/5/lumen/bootstrap.min.css" rel="stylesheet" >
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </head>
    <header>
            <script src="js/funciones.js" type="text/javascript"></script>
            <div id="contenedorImagen">
                <img src="${pageContext.request.contextPath}/img/imagen_cabecera.jpg"   alt="Restaurant pic" id="logo"/>
            </div>
           
            
            <%
                
                Usuarios user =(Usuarios) session.getAttribute("usuario");
                
                if (user == null){
                
                    out.println("<div id=\"InicioSesion\">");
                    out.println("<a href=\""+this.getServletContext().getContextPath()+"/navegacionController/InicioSesion\">");
                    out.println("<img src =\""+this.getServletContext().getContextPath()+"/img/usuario.png\" alt=\"Inicio de sesion\" id=\"usuario\"/>");
                    out.println("</a>");
                    out.println("</div>");
                    
                    

                }

                else{
                    out.println("<div id=\"InicioSesion\">");
                    out.println("<a href=\""+this.getServletContext().getContextPath()+"/ItemsControler/mirarCesta\">");
                    out.println("<img src =\""+this.getServletContext().getContextPath()+"/img/carrito_compra.png\" alt=\"Inicio de sesion\" id=\"usuario\"/>");
                    out.println("</a>");
                    out.println("</div>");
                    
                    
                    out.println("<div id=\"CerrarSesion\">");
                    out.println("<a href=\""+this.getServletContext().getContextPath()+"/navegacionController/borrarSesion\">");
                    out.println("<img src =\""+this.getServletContext().getContextPath()+"/img/cerrar_sesion.png\" alt=\"Cerrar sesion\" id=\"usuario\"/>");
                    out.println("</a>");
                    out.println("</div>");
                
                }
            %>
           
            <nav>
                
                <ul id="menu">
                  <li><a href="${pageContext.request.contextPath}/navegacionController/home">Inicio</a></li>
                    
                    <li><a href="${pageContext.request.contextPath}/ItemsControler/ensamblar">Ensamblar modelo</a></li>
                    
                    <li><a href="${pageContext.request.contextPath}/navegacionController/Opinion">Dejar una opinion</a></li> 
                </ul>
                

            </nav>


        </header>
    <div id="scroll">   
            <h1>Carrito de compra</h1>
            
            <%
            try{
            List<Items> lista_compra = (List<Items>)session.getAttribute("listaDeseados");
            
            
            if(lista_compra == null || session.getAttribute("usuario") == null){
                    out.println("<h2> Aun no tiene objetos en la lista de la compra</h2>");
                }
            
                else{
                
                    out.println("<table>");
                
                    out.println("<tr>");
                    out.println("<th> Icono </th>");
                    out.println("<th> Producto </th>");
                    out.println("<th> Opciones </th>");
                    out.println("</tr>");
                    
                    for (int i = 0; i < lista_compra.size();i++){
                        lista_compra.get(i).setImagen(new String (lista_compra.get(i).getImage(),StandardCharsets.UTF_8));
                        out.println("<tr>");
                        out.println("<td> <img src=\"data:image/png;base64," + lista_compra.get(i).getImagen() + "\" width = \"50\" height = \"50\" /> </td>");
                        out.println("<td>"+ lista_compra.get(i).getNombre() + " </td>");
                        out.println("<td> <a href=\"/TrabajoFinal/ItemsControler/borrarItemCesta?var="+ i +"\">Eliminar</a> </td>");
                        out.println("</tr>");
                    }
                    
                    out.println("</table>");
                }
            
            }catch(Exception ex){
                
                }
            %>
            
            
            
        </div>
    
    </body>
</html>
