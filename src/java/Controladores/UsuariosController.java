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
import javax.ws.rs.DELETE;
import persistencia.Usuarios;

/**
 *
 * @author False
 */
@WebServlet(name = "UsuariosController", urlPatterns = {"/UsuariosController/*"})
public class UsuariosController extends HttpServlet {

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
        
        String vista = "/index.jsp";

        TypedQuery<Usuarios> query;
        List<Usuarios> ir;
        Usuarios user;
        
        HttpSession session = request.getSession();

        switch (accion) {

            
            
            
            
            case "/registroUsuario":

                user = new Usuarios();
                user.setNombre(request.getParameter("nombre"));
                user.setCorreo(request.getParameter("correoE"));
                user.setContrasenna(request.getParameter("contrasenna"));

                try {

                    
                    //Antes de persistir tengo que comprobar que no existe un usuario igual en la BD.
                    query = em.createNamedQuery("Usuarios.registrarUsuario", Usuarios.class).setParameter("name", user.getNombre()).setParameter("corr", user.getCorreo());
                    
                    List<Usuarios> resultList = query.getResultList();

                    if (resultList.isEmpty() && user.getNombre().length() > 3 && user.getNombre().length()<20 && user.getContrasenna().length() > 3 && user.getContrasenna().length() < 21) //Como no existe en la base de datos, persisto el usuario
                    {
                        persist(user);
                        session.setAttribute("usuario", user);
                        
                        vista = "/index.jsp"; //De vuelta al main tras ser creado
                    } else {
                        request.setAttribute("mensaje", "error al crear el usuario, nombre de usuario ya en uso");
                        vista = "/Registro.jsp";

                    }

                } catch (Exception ex) {
                    System.out.println("imposible persistir cliente");
                }

                break;

            case "/ensamblar":
                vista= "/Items.jsp";
                break;
            
            case "/loginUsuario":

                user = new Usuarios();
                user.setNombre(request.getParameter("nombre"));
                user.setContrasenna(request.getParameter("contrasenna"));

                query = em.createNamedQuery("Usuarios.comprobarLogin", Usuarios.class).setParameter("name", user.getNombre()).setParameter("contra", user.getContrasenna());

                List<Usuarios> user_consulta = query.getResultList();

                if (user_consulta.isEmpty()) {
                    System.out.println("aqui estoy ahora");
                    String mensaje = "Usuario o contrasenna inválidos";
                    request.setAttribute("mensaje", mensaje);
                    vista = "/InicioSesion.jsp";
                } else {
                    session.setAttribute("usuario", user_consulta.get(0));
                    vista = "/index.jsp";
                }

                break;

            case "/borrarUsuario":
                user = new Usuarios();
                user.setNombre(request.getParameter("nombre"));
                user.setContrasenna(request.getParameter("contrasenna"));
                query = em.createNamedQuery("Usuarios.comprobarLogin", Usuarios.class).setParameter("name", user.getNombre()).setParameter("contra", user.getContrasenna());
                List<Usuarios> resultList = query.getResultList();

                if (!resultList.isEmpty()) {
                    //Se encuentra al usuario.

                    delete(resultList.get(0));

                }

                vista = "/index.html";

                break;

            default:
                vista = "/index.html";
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
