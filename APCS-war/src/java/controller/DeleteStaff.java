package controller;

import Model.Users;
import Model.UsersFacade;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "DeleteStaff", urlPatterns = {"/DeleteStaff"})
public class DeleteStaff extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");
        if (userIdStr != null) {
            try {
                Long userId = Long.parseLong(userIdStr);
                Users user = usersFacade.find(userId);
                if (user != null) {
                    // Set the user as inactive and mark the deletion time
                    user.setStatus("Inactive");
                    user.setDeletedAt(new Timestamp(System.currentTimeMillis()));
                    usersFacade.edit(user); 
                    
                    // Redirect with a success message parameter
                    response.sendRedirect("ViewStaff?deleteSuccess=true");
                } else {
                    // If the user does not exist, redirect without making changes
                    response.sendRedirect("ViewStaff");
                }
            } catch (NumberFormatException e) {
                // Handle exception if userID is not a valid long
                response.sendRedirect("ViewStaff");
            }
        } else {
            // Redirect back if userIdStr is null
            response.sendRedirect("ViewStaff");
        }
    }
}
