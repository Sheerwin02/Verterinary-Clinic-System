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
@WebServlet(name = "CancelAppointment", urlPatterns = {"/CancelAppointment"})
public class CancelAppointment extends HttpServlet {

    @EJB
    private AppointmentsFacade appointmentFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long appointmentId = Long.parseLong(request.getParameter("appointmentId"));
            Appointments appointment = appointmentFacade.find(appointmentId);
            appointment.setStatus("Canceled");
            appointmentFacade.edit(appointment);
            response.sendRedirect("ViewAppointments");
        } catch (Exception e) {
            // Handle exceptions
            response.sendRedirect("viewAppointments.jsp?error=Unable to cancel appointment");
        }
    }
}
