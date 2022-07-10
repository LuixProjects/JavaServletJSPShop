<%-- 
    Document   : index
    Created on : 18 ene 2022, 19:50:10
    Author     : False
--%>

<%@page import="persistencia.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <link href="
              <%
                  out.println(request.getContextPath());
              %>/css/styles.css" rel="stylesheet" type="text/css" />
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
            <section>

                <h2>Sobre nosotros</h2>

            </section>

            <p id="text">
                Lorem ipsum dolor sit amet consectetur adipiscing elit euismod sed in, donec proin justo orci molestie praesent sem conubia platea erat neque, lacinia dapibus commodo per pretium vestibulum ultricies massa mus. Semper bibendum felis varius condimentum cum aenean quisque dis commodo, sollicitudin gravida cubilia per velit dictum dictumst hac ad laoreet, mauris suscipit elementum pellentesque consequat tellus fames montes. Morbi neque ullamcorper vulputate cum aliquet euismod donec eleifend sapien metus, platea volutpat habitant ut sociis lacus ad sagittis porttitor, varius urna bibendum facilisi massa praesent diam tortor ornare Donec ultrices tellus ultricies in posuere vestibulum libero egestas lacus, mollis cum dictum condimentum scelerisque mus sagittis nulla nisl interdum, ornare quisque auctor suspendisse feugiat cubilia dis netus. Cum massa diam facilisi parturient porttitor curae eget dictumst dapibus cras feugiat accumsan, pulvinar himenaeos tellus augue vel velit sollicitudin dictum porta aptent. Interdum laoreet cras ultrices ridiculus vulputate imperdiet arcu, quisque tristique sodales fusce cum aliquet eu consequat, mi convallis curabitur dui etiam ornare.
                <br>
                <br>
                Lorem ipsum dolor sit amet consectetur adipiscing elit euismod sed in, donec proin justo orci molestie praesent sem conubia platea erat neque, lacinia dapibus commodo per pretium vestibulum ultricies massa mus. Semper bibendum felis varius condimentum cum aenean quisque dis commodo, sollicitudin gravida cubilia per velit dictum dictumst hac ad laoreet, mauris suscipit elementum pellentesque consequat tellus fames montes. Morbi neque ullamcorper vulputate cum aliquet euismod donec eleifend sapien metus, platea volutpat habitant ut sociis lacus ad sagittis porttitor, varius urna bibendum facilisi massa praesent diam tortor ornare Donec ultrices tellus ultricies in posuere vestibulum libero egestas lacus, mollis cum dictum condimentum scelerisque mus sagittis nulla nisl interdum, ornare quisque auctor suspendisse feugiat cubilia dis netus. Cum massa diam facilisi parturient porttitor curae eget dictumst dapibus cras feugiat accumsan, pulvinar himenaeos tellus augue vel velit sollicitudin dictum porta aptent. Interdum laoreet cras ultrices ridiculus vulputate imperdiet arcu, quisque tristique sodales fusce cum aliquet eu consequat, mi convallis curabitur dui etiam ornare.
                <br>
                <br>
                Lorem ipsum dolor sit amet consectetur adipiscing elit euismod sed in, donec proin justo orci molestie praesent sem conubia platea erat neque, lacinia dapibus commodo per pretium vestibulum ultricies massa mus. Semper bibendum felis varius condimentum cum aenean quisque dis commodo, sollicitudin gravida cubilia per velit dictum dictumst hac ad laoreet, mauris suscipit elementum pellentesque consequat tellus fames montes. Morbi neque ullamcorper vulputate cum aliquet euismod donec eleifend sapien metus, platea volutpat habitant ut sociis lacus ad sagittis porttitor, varius urna bibendum facilisi massa praesent diam tortor ornare Donec ultrices tellus ultricies in posuere vestibulum libero egestas lacus, mollis cum dictum condimentum scelerisque mus sagittis nulla nisl interdum, ornare quisque auctor suspendisse feugiat cubilia dis netus. Cum massa diam facilisi parturient porttitor curae eget dictumst dapibus cras feugiat accumsan, pulvinar himenaeos tellus augue vel velit sollicitudin dictum porta aptent. Interdum laoreet cras ultrices ridiculus vulputate imperdiet arcu, quisque tristique sodales fusce cum aliquet eu consequat, mi convallis curabitur dui etiam ornare.
                <br>
                <br>
                Lorem ipsum dolor sit amet consectetur adipiscing elit euismod sed in, donec proin justo orci molestie praesent sem conubia platea erat neque, lacinia dapibus commodo per pretium vestibulum ultricies massa mus. Semper bibendum felis varius condimentum cum aenean quisque dis commodo, sollicitudin gravida cubilia per velit dictum dictumst hac ad laoreet, mauris suscipit elementum pellentesque consequat tellus fames montes. Morbi neque ullamcorper vulputate cum aliquet euismod donec eleifend sapien metus, platea volutpat habitant ut sociis lacus ad sagittis porttitor, varius urna bibendum facilisi massa praesent diam tortor ornare Donec ultrices tellus ultricies in posuere vestibulum libero egestas lacus, mollis cum dictum condimentum scelerisque mus sagittis nulla nisl interdum, ornare quisque auctor suspendisse feugiat cubilia dis netus. Cum massa diam facilisi parturient porttitor curae eget dictumst dapibus cras feugiat accumsan, pulvinar himenaeos tellus augue vel velit sollicitudin dictum porta aptent. Interdum laoreet cras ultrices ridiculus vulputate imperdiet arcu, quisque tristique sodales fusce cum aliquet eu consequat, mi convallis curabitur dui etiam ornare.
            </p><!-- comment -->

            <footer style="width:100%; margin-left: 0px;">


                <p>Copyright © 2021</p>
                <p>Developed and Maintained by Luis Gutiérrez Jerez</p>

            </footer>

        </div>
    </body>
</html>
