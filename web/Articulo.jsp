<%-- 
    Document   : Articulo
    Created on : 25 ene 2022, 21:31:40
    Author     : False
--%>

<%@page import="persistencia.Comentario"%>
<%@page import="java.util.List"%>
<%@page import="persistencia.Items"%>
<%@page import="persistencia.Usuarios"%>
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
        
        <link href="
              <%
                  out.println(request.getContextPath());
              %>/css/articulos.css" rel="stylesheet" type="text/css" />
        <link href="https://bootswatch.com/5/lumen/bootstrap.min.css" rel="stylesheet" >
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </head>
    <body>
        <header>
            <script src="js/funciones.js" type="text/javascript"></script>
            <div id="contenedorImagen">
                <img src="${pageContext.request.contextPath}/img/imagen_cabecera.jpg"   alt="Restaurant pic" id="logo"/>
            </div>


            <%
                Usuarios user = (Usuarios) session.getAttribute("usuario");

                if (user == null) {

                    out.println("<div id=\"InicioSesion\">");
                    out.println("<a href=\"" + this.getServletContext().getContextPath() + "/navegacionController/InicioSesion\">");
                    out.println("<img src =\"" + this.getServletContext().getContextPath() + "/img/usuario.png\" alt=\"Inicio de sesion\" id=\"usuario\"/>");
                    out.println("</a>");
                    out.println("</div>");

                } else {
                    out.println("<div id=\"InicioSesion\">");
                    out.println("<a href=\"" + this.getServletContext().getContextPath() + "/ItemsControler/mirarCesta\">");
                    out.println("<img src =\"" + this.getServletContext().getContextPath() + "/img/carrito_compra.png\" alt=\"Inicio de sesion\" id=\"usuario\"/>");
                    out.println("</a>");
                    out.println("</div>");

                    out.println("<div id=\"CerrarSesion\">");
                    out.println("<a href=\"" + this.getServletContext().getContextPath() + "/navegacionController/borrarSesion\">");
                    out.println("<img src =\"" + this.getServletContext().getContextPath() + "/img/cerrar_sesion.png\" alt=\"Cerrar sesion\" id=\"usuario\"/>");
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
            
            <div id="box">
                
                <%
                
                    Items itemElegido = (Items)request.getAttribute("itemElegido");
                    
                    out.println("<img src=\"data:image/png;base64," + itemElegido.getImagen() +"\"   alt=\"Item\" id=\"imagen\"/>");
                    out.println("<h1>" + itemElegido.getNombre()+"</h1>");
                    out.println("<h2>"+itemElegido.getDescripcion()+"</h2>");
                    out.println("<a href=\"/TrabajoFinal/ItemsControler/annadirCesta?var="+ itemElegido.getNombre()+"\">");
                    if (user != null)
                    out.println("<input type=\"button\"  value=\"añadir al carrito\">");
                    out.println("</a>");
                %>    
            
            
            
            
            </div>
            
            <h2>Comentarios</h2>
            
            <%
            
                try{
                
                    List<Comentario> listaComentarios = (List<Comentario>)request.getAttribute("comentarios");
                    
                    if(listaComentarios != null){
                    
                    for(int i =0 ; i < listaComentarios.size();i++){
                
                    out.println("<h3>" + listaComentarios.get(i).getUsuarioComentador().getNombre()+ "</h3> ");
                    out.println("<h4>" + listaComentarios.get(i).getTexto()+"</h4>");
                }
                    
                    
                
                }else{
                    out.println("<h2>Aún no hay comentarios, ¡Se el primero!</h2>");
                }
                
                
                }catch(Exception ex){}
            
            
            %>
            
            
            
            
            
            
            
            <%
            try {

               

                if (user != null) {
                    /**
                    out.println("<div>");

                    out.println("<h2 id=\"boton\">Subir modelo</h2>");
                    out.println("<input type=\"file\" >");
                    
                    out.println("<input type=\"button\" value=\"Subir modelo\">");
                    out.println("</div>");**/
                    
                    out.println("<form action=\""+ request.getContextPath()+"/comentariosController/publicarComentario?var="+ itemElegido.getNombre()+"\" method=\"post\">");
                    
                        out.println("<ul>");
                            out.println("<li>");
                            out.println("<label for=\"comentar\">Comentar:</label>");
                            out.println("<input type=\"text\"  name=\"comentar\" />");
                            out.println("</li>");
                        out.println("</ul>");
                        out.println("<input type=\"submit\" value=\"Comentar\" class=\"btn btn-success\"/>");
                        out.println("</form>");
                    
                }

            } catch (Exception ex) {
                System.out.println("excepcion recibida");
            }
            


        %>
            
            
            
        </div>

    </body>
</html>
