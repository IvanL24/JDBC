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
        
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            List<Role> roles = roleservice.getAll();
           
            request.setAttribute("roles", roles);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(action != null && action.equals("delete")){
            try {
                service.delete(email);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);

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
        RoleService roleService = new RoleService();
        
        String action = request.getParameter("action");
 
        if(action != null && action.equals("add")){
            try {
                String email = request.getParameter("newUserEmail");
                String newFname = request.getParameter("newUserFirstName");
                String newLname = request.getParameter("newUserLastName");
                String newpass = request.getParameter("newUserPassword");
                String roleName = request.getParameter("newRole");
            
                int roleId = roleService.getRoleId(roleName);
            
                service.insert(email, true, newFname, newLname, newpass, new Role(roleId, roleName));
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }
        
        if(action != null && action.equals("edit")){
            try {
                String email = request.getParameter("editUserEmail");
                String newFname = request.getParameter("editUserFirstName");
                String newLname = request.getParameter("editUserLastName");
                String newpass = request.getParameter("editUserPassword");
                String roleName = request.getParameter("editRole");
            
                int roleId = roleService.getRoleId(roleName);
            
                service.update(email, true, newFname, newLname, newpass, new Role(roleId, roleName));
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }
        
    }
}
