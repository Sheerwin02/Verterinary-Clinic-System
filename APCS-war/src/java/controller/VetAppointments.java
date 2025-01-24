/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Appointments;
import Model.AppointmentsFacade;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sheerwin
 */
@WebServlet(name = "VetAppointments", urlPatterns = {"/VetAppointments"})
public class VetAppointments extends HttpServlet {

    @EJB
    private AppointmentsFacade appointmentsFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users vet = (Users) session.getAttribute("user"); // Assuming "user" session attribute holds the vet object

        if (vet != null) {
            List<Appointments> appointments = appointmentsFacade.findByVetId(vet.getUserId()); // Assuming this method exists to find appointments by vetId
            request.setAttribute("appointments", appointments);
        } else {
            request.setAttribute("errorMessage", "Unable to retrieve vet information. Please login again.");
        }

        request.getRequestDispatcher("/vetAppointments.jsp").forward(request, response);
    }
}