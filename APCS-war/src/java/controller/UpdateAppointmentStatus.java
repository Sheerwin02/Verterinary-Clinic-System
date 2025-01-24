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
@WebServlet(name = "UpdateAppointmentStatus", urlPatterns = {"/UpdateAppointmentStatus"})
public class UpdateAppointmentStatus extends HttpServlet {

    @EJB
    private AppointmentsFacade appointmentsFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appointmentIdStr = request.getParameter("appointmentId");
        String newStatus = request.getParameter("newStatus");

        if (appointmentIdStr != null && newStatus != null) {
            try {
                Long appointmentId = Long.parseLong(appointmentIdStr);
                Appointments appointment = appointmentsFacade.find(appointmentId);
                if (appointment != null) {
                    appointment.setStatus(newStatus);
                    appointmentsFacade.edit(appointment);
                    response.sendRedirect("VetAppointments?success=Appointment marked as completed successfully");
                } else {
                    response.sendRedirect("VetAppointments?error=Appointment not found");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("VetAppointments?error=Invalid appointment ID");
            }
        } else {
            response.sendRedirect("VetAppointments?error=Missing parameters");
        }
    }
}