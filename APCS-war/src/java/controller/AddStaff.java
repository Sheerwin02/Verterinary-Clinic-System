/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Users;
import Model.UsersFacade;
import Model.VetExpertises;
import Model.VetExpertisesFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddStaff", urlPatterns = {"/AddStaff"})
public class AddStaff extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;
    @EJB
    private VetExpertisesFacade vetExpertiseFacade;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String role = request.getParameter("role");
        String areaOfExpertise = request.getParameter("areaOfExpertise");
        
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("/addStaff.jsp").forward(request, response);
            return;
        }
        
        Users newUser = new Users(username, name, password, phoneNumber, email, address, role, "Active");
        usersFacade.create(newUser);

        if ("Vet".equals(role) && !areaOfExpertise.isEmpty()) {
            VetExpertises vetExpertise = new VetExpertises(newUser, areaOfExpertise);
            vetExpertiseFacade.create(vetExpertise);
        }
        
        response.sendRedirect("managingStaffDashboard.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("addStaff.jsp");
    }
}
