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
@WebServlet(name = "AddVet", urlPatterns = {"/AddVet"})
public class AddVet extends HttpServlet {

    @EJB
    private VetExpertisesFacade vetExpertisesFacade;

    @EJB
    private UsersFacade usersFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");

            // Server-side password validation
            if (!password.equals(confirmPassword)) {
                request.setAttribute("errorMessage", "Passwords do not match.");
                request.getRequestDispatcher("/addVet.jsp").forward(request, response);
                return;
            }

            Users vet = new Users();
            vet.setUsername(username);
            vet.setName(name);
            vet.setPassword(password); // Consider hashing the password
            vet.setPhoneNumber(phoneNumber);
            vet.setEmail(email);
            vet.setAddress(address);
            vet.setRole("Vet");
            vet.setStatus("Active");
            vet.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            vet.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            
            String areaOfExpertise = request.getParameter("areaOfExpertise");
            
            usersFacade.create(vet);
            
            if (areaOfExpertise != null && !areaOfExpertise.isEmpty()) {
                VetExpertises vetExpertise = new VetExpertises();
                vetExpertise.setVetId(vet); // `vet` is the Users entity you just created
                vetExpertise.setAreaOfExperties(areaOfExpertise);
                vetExpertisesFacade.create(vetExpertise); 
            }
            

            response.sendRedirect("managingStaffDashboard.jsp"); // Redirect after successful addition
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            // Optionally set an error message and forward back to the JSP
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/addVet.jsp").forward(request, response);
        }
    }
}