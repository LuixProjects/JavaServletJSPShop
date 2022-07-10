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
import persistencia.Usuarios;

/**
 *
 * @author False
 */
@WebServlet(name = "navegacionController", urlPatterns = {"/navegacionController/*"})
public class navegacionController extends HttpServlet {

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
        
        String accion = request.getPathInfo();
        System.out.println("el path es : " + accion);
        String vista ;
        TypedQuery<Usuarios> query;
        HttpSession session = request.getSession();
        
        switch(accion){
        
            case "/prueba":
                
                String correo = ("entro aqui." + request.getParameter("correoE")); 
                System.out.println("correo : " + correo);
                
                try{

                query = em.createNamedQuery("Usuarios.existirCorreo",Usuarios.class).setParameter("corr", correo);
                  List<Usuarios> resultList = query.getResultList();

                  if (resultList.isEmpty()){
                  //El email no existe en la base de datos
                    request.setAttribute("mensaje", "Correo valido");
                      System.out.println("entro en el if");
                  }else{
                      //El email existe en la base de datos.
                      request.setAttribute("mensaje", "Correo ya en uso por otro usuario, por favor, elija otro.");

                  }


                }catch(Exception ex){}


               vista = "/Registro.jsp";
                break;
            
            case "/home":
                vista = "/index.jsp";
            break;
            
            case "/InicioSesion":
                vista = "/InicioSesion.jsp";
            break;
            
            case "/Registro":
                vista = "/Registro.jsp";
                break;
                
            case "/Opinion":
                vista = "";
                break;
                
            
                
            case "/cestaCompra":
                vista = "/cestaCompra.jsp";
                break;
            
            case "/borrarSesion":
                session.invalidate();
                vista = "/index.jsp";
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

}
