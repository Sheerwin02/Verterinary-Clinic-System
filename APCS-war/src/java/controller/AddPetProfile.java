/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Customers;
import Model.CustomersFacade;
import Model.Pets;
import Model.PetsFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
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
@WebServlet(name = "AddPetProfile", urlPatterns = {"/AddPetProfile"})
public class AddPetProfile extends HttpServlet {

    @EJB
    private CustomersFacade customersFacade;

    @EJB
    private PetsFacade petsFacade;
    

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
        
        try {
            String name = request.getParameter("name");
            String species = request.getParameter("species");
            Long customerId = Long.parseLong(request.getParameter("customerId"));

            Customers customer = customersFacade.find(customerId);
            if (customer == null) {
                // Handle case where customer is not found
                request.setAttribute("errorMessage", "Invalid customer.");
                request.getRequestDispatcher("/addPetProfile.jsp").forward(request, response);
                return;
            }

            Pets pet = new Pets();
            pet.setName(name);
            pet.setSpecies(species);
            pet.setCustomerId(customer);
            pet.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            pet.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            
            petsFacade.create(pet);
            
            response.sendRedirect("ViewPetProfiles"); // Redirect to a page where the pet profiles are listed
        } catch (Exception e) {
            e.printStackTrace();
            // Handle failure; redirect back to form with error message
            request.setAttribute("errorMessage", "Error adding pet profile.");
            request.getRequestDispatcher("/addPetProfile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Customers> customers = customersFacade.findAll(); // Assuming findAll() returns a list of all customers
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/addPetProfile.jsp").forward(request, response);
    }

}