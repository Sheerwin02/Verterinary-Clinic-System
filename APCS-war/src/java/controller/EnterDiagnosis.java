/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Appointments;
import Model.AppointmentsFacade;
import Model.Diagnoses;
import Model.DiagnosesFacade;
import Model.Users;
import static Model.Users_.userId;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpSession;

@WebServlet("/EnterDiagnosis")
public class EnterDiagnosis extends HttpServlet {

    @EJB
    private DiagnosesFacade diagnosesFacade;

    @EJB
    private AppointmentsFacade appointmentsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Users vet = (Users) session.getAttribute("user"); 
        
        if (vet != null) {
            List<Appointments> appointments = appointmentsFacade.findByVetId(vet.getUserId());
            request.setAttribute("appointments", appointments);
        } else {
            // Handle case where vetId is not found in session (e.g., redirect to login)
            response.sendRedirect("login.jsp"); // Redirect to login or appropriate page
            return;
        }

        request.getRequestDispatcher("/enterDiagnosis.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve form parameters
            Long appointmentId = Long.parseLong(request.getParameter("appointmentId"));
            String diagnosisText = request.getParameter("diagnosis");

            // Find the appointment by ID
            Appointments appointment = appointmentsFacade.find(appointmentId);
            
            if (appointment != null) {
                // Create a new diagnosis entity
                Diagnoses diagnosis = new Diagnoses();
                diagnosis.setAppointmentId(appointment); // Set the linked appointment
                diagnosis.setDiagnosis(diagnosisText); // Set the diagnosis text
                diagnosis.setCreatedAt(new Timestamp(System.currentTimeMillis())); // Optionally set creation and update timestamps
                diagnosis.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                
                // Persist the new diagnosis entity
                diagnosesFacade.create(diagnosis);

                // Redirect to a confirmation page or back to the dashboard with a success message
                request.getSession().setAttribute("successMessage", "Diagnosis successfully entered for appointment ID: " + appointmentId);
                response.sendRedirect("ViewDiagnosis"); // Adjust the redirect as needed
            } else {
                // Appointment not found
                request.getSession().setAttribute("errorMessage", "Appointment not found.");
                response.sendRedirect("enterDiagnosis.jsp"); // Adjust the redirect as needed
            }
        } catch (NumberFormatException e) {
            // Handle invalid appointment ID
            request.getSession().setAttribute("errorMessage", "Invalid appointment ID.");
            response.sendRedirect("enterDiagnosis.jsp"); // Adjust the redirect as needed
        } catch (Exception e) {
            // Handle other errors
            request.getSession().setAttribute("errorMessage", "Error saving diagnosis: " + e.getMessage());
            response.sendRedirect("enterDiagnosis.jsp"); // Adjust the redirect as needed
        }
    }
}

