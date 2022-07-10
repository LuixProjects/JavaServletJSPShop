/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import persistencia.Roles;

/**
 *
 * @author False
 */
@WebServlet(name = "RolesControler", urlPatterns = {"/RolesControler/*"})
public class RolesControler extends HttpServlet {

    @PersistenceContext(unitName = "TrabajoFinalPU")
    private EntityManager em ;
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
        System.out.println("accion: " + accion);
        String vista;
        Roles r;
        TypedQuery<Roles> query;
        List<Roles> ir;
        switch(accion){
        
            case "/show":
                    query = em.createNamedQuery("Roles.findAll",Roles.class);
                    ir = query.getResultList();
                    
                    request.setAttribute("roles", ir);
                    vista = "/showRoles.jsp";
                break;
            
            case "/altaRol":
                
                r = new Roles();
                r.setNombre(request.getParameter("nombre"));
                r.setPermisos(request.getParameter("permisos"));
                query = em.createNamedQuery("Roles.findByName",Roles.class).setParameter("name", request.getParameter("nombre"));
                List<Roles> resultList = query.getResultList();
                
                if(resultList.isEmpty()){
                    persist(r);
                }
                
                
                vista = "/altaRoles.jsp";
                break;
               
            case "/deleteRol":
                 r = new Roles();
                r.setNombre(request.getParameter("nombre"));
                r.setPermisos(request.getParameter("permisos"));
                
                query = em.createNamedQuery("Roles.findByName",Roles.class).setParameter("name", r.getNombre());
                List<Roles> roles = query.getResultList();
                
                if(!roles.isEmpty()){
                    //Si ha encontrado roles los elimina
                    delete(r);
                }
                
                vista = "index.html";
                
                
                break;
                
                
            default:
                vista = "index.html";
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

}
