/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controladores;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.jboss.weld.servlet.SessionHolder;
import persistencia.Comentario;
import persistencia.Items;
import persistencia.Usuarios;

/**
 *
 * @author False
 */
@MultipartConfig
@WebServlet(name = "ItemsControler", urlPatterns = {"/ItemsControler/*"})
public class ItemsControler extends HttpServlet {

    @PersistenceContext(unitName = "TrabajoFinalPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accion = request.getPathInfo();
        System.out.println("accion: " + accion);
        String vista = "/index.jsp";
        TypedQuery<Items> query;
        TypedQuery<Comentario> query_comentario;
        Items items;
        Usuarios user;
        List<Items> coleccionItems;
        switch (accion) {

            case "/ensamblar":
                
                try {

                query = em.createNamedQuery("Items.findAll", Items.class);
                List<Items> item_querry = query.getResultList();
                
                for(int i = 0; i < item_querry.size();i++){
                
                    item_querry.get(i).setImagen(new String (item_querry.get(i).getImage(),StandardCharsets.UTF_8));
                }

                request.setAttribute("listaItems", item_querry);

            } catch (Exception ex) {
                System.out.println("salta exception");
            }

            try {
                user = (Usuarios) session.getAttribute("usuario");
                query = em.createNamedQuery("Items.buscarItemPorCliente", Items.class).setParameter("name", user.getNombre());
                List<Items> itemsPorCliente = query.getResultList();

                if (itemsPorCliente != null) {

                    System.out.println(itemsPorCliente.get(0));

                    request.setAttribute("tusItems", itemsPorCliente);
                }

            } catch (Exception ex) {
            }

            vista = "/Items.jsp";
            break;

            case "/borrarItemCesta":

                String item_index = (request.getParameter("var"));

                List<Items> lista_items2 = (List<Items>) session.getAttribute("listaDeseados");
                lista_items2.remove(Integer.parseInt(item_index));

                vista = "/cestaCompra.jsp";
                break;

            case "/mirarCesta":

                List<Items> lista_compra = (List<Items>) session.getAttribute("listaDeseados");

                if (lista_compra != null) {

                    //vamos a comprobar si los items en la cesta están en la base de datos.
                    for (int i = 0; i < lista_compra.size(); i++) {

                        query = em.createNamedQuery("Items.findByName", Items.class).setParameter("name", lista_compra.get(i).getNombre());

                        List<Items> item_querry = query.getResultList();

                        if (item_querry.isEmpty()) {
                            lista_compra.get(i).setNombre("Lo sentimos, articulo no disponible");
                            lista_compra.get(i).setImagen("Error");
                        }

                    }

                }

                vista = "/cestaCompra.jsp";
                break;

            case "/elegirItem":

                String n_Item = (request.getParameter("var"));
                query = em.createNamedQuery("Items.findByName", Items.class).setParameter("name", n_Item);
                List<Items> iquerry = query.getResultList();

                Items itemElegido = iquerry.get(0);
                itemElegido.setImagen(new String (itemElegido.getImage(),StandardCharsets.UTF_8));
                
                request.setAttribute("itemElegido", itemElegido);
                
                
                //cargamos comentarios.
                
                query_comentario = em.createNamedQuery("Comentario.buscarComentario", Comentario.class).setParameter("objeto", itemElegido.getNombre());
                List<Comentario> comentarios_querry = query_comentario.getResultList();
                
                request.setAttribute("comentarios", comentarios_querry);

                vista = "/Articulo.jsp";
                break;

            case "/annadirCesta":

                String nombreItem = (request.getParameter("var"));
                System.out.println("entra aqui " + nombreItem);
                query = em.createNamedQuery("Items.findByName", Items.class).setParameter("name", nombreItem);

                List<Items> item_querry = query.getResultList();
                System.out.println("item_querry da: " + item_querry.size());
                //if(!item_querry.isEmpty()){
                /**
                 * Items prueba = new Items(); prueba.setNombre("Unicapa");
                 * prueba.setPrecio(50); item_querry.add(prueba);
                *
                 */

                List<Items> lista_items = (List<Items>) session.getAttribute("listaDeseados");

                if (lista_items == null) {
                    //No existe la lista, creamos lista nueva y agragamos el objeto
                    List<Items> nueva_lista = new ArrayList<Items>();
                    nueva_lista.add(item_querry.get(0));
                    session.setAttribute("listaDeseados", nueva_lista);
                    System.out.println("se crea lista Deseados");
                } else {
                    //agregamos sencillamente el objeto.
                    lista_items.add(item_querry.get(0));
                    session.setAttribute("listaDeseados", lista_items);

                }

                //}
                vista = "/ItemsControler/ensamblar";

                break;

            case "/altaItem":

                System.out.println("entro en alta Items.");

                items = new Items();
                items.setNombre(request.getParameter("nombre"));
                items.setPrecio(Double.parseDouble(request.getParameter("precio")));
                items.setDescripcion(request.getParameter("descripcion"));

                user = (Usuarios) session.getAttribute("usuario");

                try {
                    items.setUser(user);

                    final Part filePart = request.getPart("file");
                    

                    if (filePart != null) {
                        
                            String b64 = getBase64(filePart);
                            items.setImage(b64.getBytes(StandardCharsets.UTF_8));
                            //item_querry.get(i).setImagen(new String (item_querry.get(i).getImage(),StandardCharsets.UTF_8));
                            

                        /**
                         * String relativePathFolder = "img"; String
                         * absolutePathFolder =
                         * getServletContext().getRealPath(relativePathFolder);
                         *
                         * File folder = new File(absolutePathFolder); if
                         * (folder.exists()) { //System.err.println("Error : " +
                         * absolutePathFolder + " existe"); } else {
                         * folder.mkdir(); }                          *
                         * System.out.println(absolutePathFolder+
                         * File.separator+items.getNombre()+".jpg");
                         * items.setImagen(absolutePathFolder+
                         * File.separator+items.getNombre()+".jpg"); File f =
                         * new File(absolutePathFolder+ File.separator +
                         * items.getNombre()+".jpg");
                         *
                         * OutputStream p = new FileOutputStream(f); InputStream
                         * filecontent; filecontent = filePart.getInputStream();
                         * System.out.println("Tam: " + filePart.getSize());
                         *
                         * int read = 0; final byte[] bytes = new byte[1024];
                         * while ((read = filecontent.read(bytes)) != -1) {
                         * p.write(bytes, 0, read); }
                         *
                         * p.close(); filecontent.close(); *
                         */
                    }

                    query = em.createNamedQuery("Items.findByName", Items.class).setParameter("name", items.getNombre());

                    List<Items> i = query.getResultList();

                    if (i.isEmpty()) {
                        persist(items);
                    }

                } catch (Exception ex) {
                    System.out.println("imposible persistir el item " + ex);
                }

                System.out.println("la vista quedaria: " + request.getContextPath() + "/ItemsControler/Items.jsp");
                vista = "/ItemsControler/ensamblar";

                break;

            case "/borrarItem":

                String nombre = (request.getParameter("var"));

                query = em.createNamedQuery("Items.findByName", Items.class).setParameter("name", nombre);
                List<Items> it = query.getResultList();

                
                
                
                
                if (it != null) {

                    delete(it.get(0));
                }

                vista = "/ItemsControler/ensamblar";
                break;

            case "/showItems":

                query = em.createNamedQuery("Items.findAll", Items.class);
                coleccionItems = query.getResultList();

                request.setAttribute("listaItems", coleccionItems);
                vista = "/Items.jsp";

                break;

            default:

                vista = "/index.jsp";

                break;

        }
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    private void delete(Object object) {
        try {
            utx.begin();
            object = (Object) em.merge(object);
            em.remove(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    private String getBase64(Part filePart) {
        try {
            InputStream fileContent = filePart.getInputStream();
            byte[] byteArray = getByteArray(fileContent);
            return Base64.getEncoder().encodeToString(byteArray);
        } catch (Exception e) {
            return null;
        }
    }

    private static byte[] getByteArray(InputStream is) throws Exception {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        BufferedOutputStream os = new BufferedOutputStream(b);
        while (true) {
            int i = is.read();
            if (i == -1) {
                break;
            }
            os.write(i);
        }
        os.flush();
        os.close();
        return b.toByteArray();
    }

}
