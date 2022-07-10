/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.Comentario;
import persistencia.Items;
import persistencia.Usuarios;

/**
 *
 * @author False
 */
@WebServlet(name = "comentariosController", urlPatterns = {"/comentariosController/*"})
public class comentariosController extends HttpServlet {

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
        System.out.println("accion2: " + accion);
        String vista = "/index.jsp";
        TypedQuery<Items> query;
        Items items;
        Usuarios user;
        Comentario coment = null;
        List<Items> coleccionItems;
        
        switch (accion){
        
            
            case "/publicarComentario":
                System.out.println("entro en publicar comentario");
                //recibo el nombre del articulo por url var="nombre"
                String comentario = request.getParameter("comentar");
                Usuarios usuario_acual = (Usuarios)session.getAttribute("usuario");
                String nombreItem = request.getParameter("var");
                
                try{
                query = em.createNamedQuery("Items.findByName", Items.class).setParameter("name", nombreItem);

                List<Items> item_querry = query.getResultList();
                
                items = item_querry.get(0);
                
                System.out.println("el usuario es :" + usuario_acual.getNombre() + " el item es: " + items.getNombre()+ " y el comentario es : " + comentario);
                
                
                    
                coment = new Comentario();
                coment.setUsuarioComentador(usuario_acual);
                coment.setTexto(comentario);
                coment.setObjeto(items);
                    System.out.println("hago los sets del objeto");
                
                
                persist(coment);
                    System.out.println("he petado?");
                }catch(Exception ex){};
                vista = "/ItemsControler/elegirItem?var="+nombreItem;
                break;
            
            
            
            default:
                vista="/Articulo.jsp";
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

}
