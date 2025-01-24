/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Appointments;
import Model.Users;
import Model.Pets;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

import Model.AppointmentsFacade;
import Model.PetsFacade;
import Model.UsersFacade;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/EditAppointment")
public class EditAppointment extends HttpServlet {
    @EJB
    private AppointmentsFacade appointmentsFacade;
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private PetsFacade petsFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long appointmentId = Long.parseLong(request.getParameter("appointmentId"));
            Long vetId = Long.parseLong(request.getParameter("vetId"));
            Long petId = Long.parseLong(request.getParameter("petId"));

            // Get the dateTime string from the request
            String dateTimeStr = request.getParameter("dateTime");
            // Replace 'T' with a space to match the expected format
            dateTimeStr = dateTimeStr.replace('T', ' ');
            // Append seconds (":00") to match the expected format
            dateTimeStr += ":00";
            // Now you can convert it to a Timestamp
            Timestamp dateTime = Timestamp.valueOf(dateTimeStr);

            String status = request.getParameter("status");

            Appointments appointment = appointmentsFacade.find(appointmentId);
            Users vet = usersFacade.find(vetId);
            Pets pet = petsFacade.find(petId);

            if (vet != null && "Vet".equals(vet.getRole()) && appointment != null && pet != null) {
                appointment.setVetId(vet);
                appointment.setPetId(pet);
                appointment.setDateTime(dateTime);
                appointment.setStatus(status);

                appointmentsFacade.edit(appointment);
                response.sendRedirect("ViewAppointments");
            } else {
                // Handle error scenarios
                request.setAttribute("errorMessage", "Validation error: missing or incorrect data.");
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Error updating appointment: " + e.getMessage(), e);
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appointmentIdStr = request.getParameter("appointmentId");
        if (appointmentIdStr != null) {
            try {
                Long appointmentId = Long.parseLong(appointmentIdStr);
                Appointments appointment = appointmentsFacade.find(appointmentId);
                if (appointment != null) {
                    request.setAttribute("appointment", appointment);
                } else {
                    // Handle case where appointment is not found
                    request.setAttribute("errorMessage", "Appointment not found.");
                }
            } catch (NumberFormatException e) {
                // Handle case where appointment ID is invalid
                request.setAttribute("errorMessage", "Invalid appointment ID.");
            }
        }

        // Fetching all vets and pets as before
        List<Users> vets = usersFacade.findAll().stream().filter(user -> "Vet".equals(user.getRole())).collect(Collectors.toList());
        List<Pets> pets = petsFacade.findAll();
        request.setAttribute("vets", vets);
        request.setAttribute("pets", pets);

        // Forward to JSP
        request.getRequestDispatcher("/editAppointment.jsp").forward(request, response);
}

}
