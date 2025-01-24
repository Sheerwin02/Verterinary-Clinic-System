/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Appointments;
import Model.AppointmentsFacade;
import Model.Pets;
import Model.PetsFacade;
import Model.Users;
import Model.UsersFacade;
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
@WebServlet(name = "AddAppointment", urlPatterns = {"/AddAppointment"})
public class AddAppointment extends HttpServlet {

    @EJB
    private PetsFacade petsFacade;

    @EJB
    private UsersFacade usersFacade;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long vetId = Long.parseLong(request.getParameter("vetId"));
        Long petId = Long.parseLong(request.getParameter("petId"));
        Timestamp dateTime = Timestamp.valueOf(request.getParameter("dateTime").replace("T", " ") + ":00");
        String status = request.getParameter("status");

        Users vet = usersFacade.find(vetId);
        Pets pet = petsFacade.find(petId);

        Appointments appointment = new Appointments();
        appointment.setVetId(vet);
        appointment.setPetId(pet);
        appointment.setDateTime(dateTime);
        appointment.setStatus(status);

        appointmentsFacade.create(appointment);

        response.sendRedirect("ViewAppointments");
    }
}
