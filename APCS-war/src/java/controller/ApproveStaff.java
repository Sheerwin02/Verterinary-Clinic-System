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

@WebServlet("/ApproveStaff")
public class ApproveStaff extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");
        if (userIdStr != null && !userIdStr.isEmpty()) {
            try {
                Long userId = Long.parseLong(userIdStr);
                Users user = usersFacade.find(userId);
                if (user != null && "Pending".equals(user.getStatus())) {
                    user.setStatus("Active");
                    usersFacade.edit(user); 
                    // Redirect back or show success message
                }
            } catch (NumberFormatException e) {
                // Handle parsing error
            }
        }
        response.sendRedirect("ViewStaff"); // Redirect back to the staff list
    }
}
