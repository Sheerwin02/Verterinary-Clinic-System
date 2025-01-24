/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Appointments;
import Model.AppointmentsFacade;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ViewAppointments", urlPatterns = {"/ViewAppointments"})
public class ViewAppointments extends HttpServlet {

    @EJB
    private AppointmentsFacade appointmentsFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all appointments from the database
        List<Appointments> appointmentsList = appointmentsFacade.findAll();
        // Set the appointments list as a request attribute to be accessed by the JSP
        request.setAttribute("appointments", appointmentsList);
        // Forward the request to the JSP
        request.getRequestDispatcher("/viewAppointments.jsp").forward(request, response);
    }
}