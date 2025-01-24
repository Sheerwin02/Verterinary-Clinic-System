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
import java.util.List;

@WebServlet("/ViewStaff")
public class ViewStaff extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        String roleFilter = request.getParameter("roleFilter");
        
        List<Users> staffList = usersFacade.findActiveUsersBySearchAndRole(searchQuery, roleFilter);
        List<Users> pendingUsers = usersFacade.findUsersByStatus("Pending"); // Fetch pending approval users
        
        String pageTitle = "Managing Staff List";
        if ("Vet".equals(roleFilter)) {
            pageTitle = "Manage Vets";
        } else if ("Receptionist".equals(roleFilter)) {
            pageTitle = "Manage Receptionists";
        } else if ("Managing Staff".equals(roleFilter)) {
            pageTitle = "Manage Managing Staff";
        }

        request.setAttribute("staffList", staffList);
        request.setAttribute("pendingUsers", pendingUsers); // Set pending users as a request attribute
        request.setAttribute("pageTitle", pageTitle);
        request.getRequestDispatcher("/viewStaff.jsp").forward(request, response);
    }
}
