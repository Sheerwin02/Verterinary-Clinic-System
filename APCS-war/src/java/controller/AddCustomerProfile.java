/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Customers;
import Model.CustomersFacade;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AddCustomerProfile", urlPatterns = {"/AddCustomerProfile"})
public class AddCustomerProfile extends HttpServlet {

    @EJB
    private CustomersFacade customersFacade;

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
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            
            Customers customer = new Customers();
            customer.setName(name);
            customer.setEmail(email);
            customer.setPhoneNumber(phoneNumber);
            customer.setAddress(address);
            
            customersFacade.create(customer);
            
            response.sendRedirect("receiptionistDashboard.jsp"); // Redirect or display a success message
        } catch (Exception e) {
            // Handle exceptions and errors
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error adding customer profile.");
            request.getRequestDispatcher("/addCustomerProfile.jsp").forward(request, response);
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
        // Redirect to prevent direct access
        response.sendRedirect("addCustomerProfile.jsp");
    }
}
