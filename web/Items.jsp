<%-- 
    Document   : Items
    Created on : 18 ene 2022, 18:54:43
    Author     : False
--%>

<%@page import="persistencia.Usuarios"%>
<%@page import="persistencia.Items"%>
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
        <script src="${pageContext.request.contextPath}/js/funciones.js" type="text/javascript"></script>
        <link href="https://bootswatch.com/5/lumen/bootstrap.min.css" rel="stylesheet" >
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </head>
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

    
    <div id="scroll" style="max-width: 80%;">
        <table id="tablaProductos" >

            <tr>
                <th>Imagen</th>
                <th >Tipo</th>
                <th >Precio</th>
                <%
                    
                    List<Items> Tusitems = (List<Items>)request.getAttribute("tusItems");
                    if(Tusitems != null){
                        out.println("<th> Opciones </th>");
                    }

                
                %>
            </tr>


            <%
                try {
                    List<Items> items = (List<Items>) request.getAttribute("listaItems");

                    if (!items.isEmpty()) {

                        for (int i = 0; i < items.size(); i++) {

                            out.println("<tr onclick=\"annadirLista(this)\">");

                            
                            
                            
                            
                            out.println("<td> <img src=\"data:image/png;base64," + items.get(i).getImagen() + "\" width = \"50\" height = \"50\" /> </td>");
                            out.println("<td>" + items.get(i).getNombre() + "</td>");
                            out.println("<td> " + items.get(i).getPrecio() + "</td>");
                           
                            if (Tusitems != null){
                            
                            for (int x = 0; x < Tusitems.size();x++){
                            System.out.println("nombre del itemM " + items.get(i).getNombre() + " itemTUS: " + Tusitems.get(x).getNombre());
                                
                                if(items.get(i).getNombre().equals(Tusitems.get(x).getNombre())){
                                    out.println("<td> <a href=\"" + request.getContextPath()+ "/ItemsControler/borrarItem?var=" +Tusitems.get(x).getNombre()+ "\">Eliminar</a> </td>");
                                }
                
                            }}
                            
                            
                            
                            out.println("</tr>");
                        }

                    }else{
                
                    out.println("<h2>Ahora mismo no hay ningun Item en el marketplace</h2>");
                }
                } catch (Exception ex) {
                }

            %>

            


        </table>

        <%                    try {

                

                if (user != null) {
                    /**
                    out.println("<div>");

                    out.println("<h2 id=\"boton\">Subir modelo</h2>");
                    out.println("<input type=\"file\" >");
                    
                    out.println("<input type=\"button\" value=\"Subir modelo\">");
                    out.println("</div>");**/
                    
                    out.println("<form action=\"/TrabajoFinal/ItemsControler/altaItem\" method=\"post\" enctype=\"multipart/form-data\" >");
                    
                        out.println("<ul>");
                            out.println("<li>");
                            out.println("<label for=\"nombre\">Nombre:</label>");
                            out.println("<input type=\"text\"  name=\"nombre\" />");
                            out.println("</li>");
                            
                            out.println("<li>");
                            out.println("<label for=\"coste\">Precio</label>");
                            out.println("<input type=\"text\"  name=\"precio\" />");
                            out.println("</li>");
                            
                            out.println("<li>");
                            out.println("<label for=\"Descripcion\">Descripcion del producto</label>");
                            out.println("<input type=\"text\"  name=\"descripcion\" />");
                            out.println("</li>");
                            
                            
                            
                            out.println("<li>");
                            out.println("<label for =\"imagen\">Imagen del producto(PNG) </label> ");
                            out.println("<input type=\"file\" name=\"file\" value = \"\"");
                            
                            out.println("</li>");
                            
                            
                        out.println("</ul>");
                        out.println("<input type=\"submit\" value=\"Crear Artículo\" class=\"btn btn-success\"/>");
                        out.println("</form>");
                    
                }

            } catch (Exception ex) {
                System.out.println("excepcion recibida");
            }
            


        %>



    </div>


    <footer style="width:100%; margin-left: 0px;">


        <p>Copyright © 2021</p>
        <p>Developed and Maintained by Luis Gutiérrez Jerez</p>

    </footer>
</body>
</html>
