/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import jdk.internal.org.jline.reader.impl.DefaultParser;

/**
 *
 * @author False
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginCliente extends HttpServlet {

    @Resource(name = "TrabajoFinalDB")
    private DataSource trabajoFinalDB;

    //Variables para conexion y sentencias.
    private Connection conn;
    private PreparedStatement ps;
    private Usuario user;

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
            throws ServletException, IOException, NamingException, SQLException {
        
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            //Creamos el objeto usuario.
            String nombre = request.getParameter("nombre");
            String contrasenna = request.getParameter("contrasenna");
             System.out.println("estoy aqui: " + nombre + " " + contrasenna);
            //Comprobaciones en lado de servidor.
            
//            Pattern p = Pattern.compile("[^a-zA-Z0-9 ]", Pattern.CASE_INSENSITIVE);
//            Matcher name = p.matcher(nombre);
//            Matcher pass = p.matcher(contrasenna);
//            System.out.println("estoy aqui3");
//            if (!name.find() || !pass.find()) {
//                throw new RuntimeException("Caracteres invalidos detectados.");
//            }

//            //Conexion
            Context c = new InitialContext();
            trabajoFinalDB = (DataSource) c.lookup("jdbc/TrabajoFinal");
            conn = trabajoFinalDB.getConnection();
           
            ps = conn.prepareStatement("select * from Usuarios where NOMBRE = ? AND CONTRASENNA = ?");
            ps.setString(1, nombre);
            ps.setString(2, contrasenna);
////            
           ResultSet rs = ps.executeQuery();
            boolean usuarioCorrecto;
            if (rs.next()) {
                usuarioCorrecto = true;
            } else {
                usuarioCorrecto = false;
                ps.close();
            }
            conn.close();

            /* TODO output your page here. You may use following sample code. */
            if(usuarioCorrecto == false){
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginCliente</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginCliente at " + usuarioCorrecto + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
              
                out.println("<meta http-equiv=\"refresh\" content=\"0; URL= http://localhost:8080/TrabajoFinal/InicioSesion.html\" />");
            
            }
               
        }
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(LoginCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("aqui estoy");
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(LoginCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("aqui estoy 2");
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

}
