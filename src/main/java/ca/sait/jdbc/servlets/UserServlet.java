package ca.sait.jdbc.servlets;

import ca.sait.jdbc.models.Role;
import ca.sait.jdbc.models.User;
import ca.sait.jdbc.services.RoleService;
import ca.sait.jdbc.services.UserService;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ivanl
 */
public class UserServlet extends HttpServlet {
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
        
        UserService service = new UserService();
        RoleService roleservice = new RoleService();
        String email = request.getParameter("email");
        String action = request.getParameter("action");

        
        try {
            List<User> users = service.getAll();
           
            request.setAttribute("users", users);
            this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);

        
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        HttpSession session = request.getSession();
        UserService service = new UserService();
        
        
        String action = request.getParameter("action");
 
        if(action != null && action.equals("add")){
            String email = request.getParameter("newUserEmail");
            String newFname = request.getParameter("newUserFirstName");
            String newLname = request.getParameter("newUserLastName");
            String newpass = request.getParameter("newUserPassword");
            String role = request.getParameter("newRole");
            
            service.insert(email, newFname, newLname, newpass, role);
            
            session.setAttribute("items", items);
             
        }
    }
}
