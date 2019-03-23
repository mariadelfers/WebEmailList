/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataAccess.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import dataAccess.UserDAO;

/**
 *
 * @author Fer Enriquez'
 */
@WebServlet(name = "EmailListServlet", urlPatterns = {"/EmailListServlet"})
public class EmailListServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String url = "/index.jsp";
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // default action
        }
        // perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/index.jsp"; // the "join" page
        } else if (action.equals("add")) {
            // get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            //store data in User object and save User object in database
            User user = new User(firstName, lastName, email, password);

            DBConnection dbCon = new DBConnection();
            UserDAO userDAO = new UserDAO(dbCon.getConnection());
            userDAO.addUser(user);

            // set User object in request object and set URL
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            url = "/jsp/thanks.jsp"; // the "thanks" page
        } else if (action.equals("mostrar")) {
            DBConnection dbCon = new DBConnection();
            UserDAO userDAO = new UserDAO(dbCon.getConnection());
            ArrayList<model.User> usuarios;
            usuarios = userDAO.allUsers();
            HttpSession session = request.getSession();
            session.setAttribute("usuarios", usuarios);

            url = "/jsp/mostrarTodos.jsp";
        }

        // forward request and response objects to specified URL
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
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

    private void insert(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
