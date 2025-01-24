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
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sheerwin
 */
@WebServlet(name = "EditStaff", urlPatterns = {"/EditStaff"})
public class EditStaff extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;
    @EJB
    private VetExpertisesFacade vetExpertisesFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");
        if (userIdStr == null || userIdStr.trim().isEmpty()) {
            // Redirect or show error
            response.sendRedirect("ViewStaff"); // or any error page
            return;
        }

        Long userId = Long.parseLong(userIdStr);
        Users user = usersFacade.find(userId);
        request.setAttribute("user", user);

        if ("Vet".equals(user.getRole())) {
            VetExpertises vetExpertise = vetExpertisesFacade.findByVetId(userId);
            if (vetExpertise != null) {
                request.setAttribute("areaOfExpertise", vetExpertise.getAreaOfExperties());
            } else {
                request.setAttribute("areaOfExpertise", ""); // No expertise found, so set an empty string
            }
        }

        request.getRequestDispatcher("/editStaff.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");
        Long userId = Long.parseLong(userIdStr);
        Users user = usersFacade.find(userId);

        // Update user with form data
        user.setUsername(request.getParameter("username"));
        String password = request.getParameter("password");
        if (password != null && !password.trim().isEmpty()) {
            user.setPassword(password); 
        }
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPhoneNumber(request.getParameter("phoneNumber"));
        user.setAddress(request.getParameter("address"));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        
        if ("Vet".equals(user.getRole())) {
            String areaOfExpertise = request.getParameter("areaOfExpertise");
            VetExpertises vetExpertise = vetExpertisesFacade.findByVetId(userId);
            if (vetExpertise == null && !areaOfExpertise.trim().isEmpty()) {
                vetExpertise = new VetExpertises(user, areaOfExpertise);
                vetExpertisesFacade.create(vetExpertise);
            } else if (vetExpertise != null) {
                vetExpertise.setAreaOfExperties(areaOfExpertise);
                vetExpertisesFacade.edit(vetExpertise);
            }
        }

        usersFacade.edit(user);
        response.sendRedirect("ViewStaff?editSuccess=true");
    }
}
