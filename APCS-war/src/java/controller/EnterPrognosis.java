/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Diagnoses;
import Model.DiagnosesFacade;
import Model.Prognoses;
import Model.PrognosesFacade;
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
@WebServlet(name = "EnterPrognosis", urlPatterns = {"/EnterPrognosis"})
public class EnterPrognosis extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private DiagnosesFacade diagnosesFacade;
    @EJB
    PrognosesFacade prognosesFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long diagnosisId = Long.parseLong(request.getParameter("diagnosisId"));
        String prognosis = request.getParameter("prognosis");
        Boolean followedUpRequired = request.getParameter("followedUpRequired") != null;

        Diagnoses diagnosis = diagnosesFacade.find(diagnosisId);

        if (diagnosis != null) {
            Prognoses newPrognosis = new Prognoses();
            newPrognosis.setDiagnosisId(diagnosis);
            newPrognosis.setPrognosis(prognosis);
            newPrognosis.setFollowedUpRequired(followedUpRequired);
            newPrognosis.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            newPrognosis.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            prognosesFacade.create(newPrognosis);
            
            response.sendRedirect("ViewDiagnosis"); // Redirect to the list of diagnoses after entering the prognosis
        } else {
            // Handle the case where the diagnosis is not found
            request.setAttribute("errorMessage", "Diagnosis not found.");
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Fetch all diagnoses to populate the dropdown.
        List<Diagnoses> allDiagnoses = diagnosesFacade.findAll();
        request.setAttribute("allDiagnoses", allDiagnoses);

        // Attempt to get a diagnosis ID from query parameters.
        String diagnosisIdStr = request.getParameter("diagnosisId");
        if (diagnosisIdStr != null && !diagnosisIdStr.isEmpty()) {
            try {
                Long diagnosisId = Long.parseLong(diagnosisIdStr);
                Diagnoses diagnosis = diagnosesFacade.find(diagnosisId);
                if (diagnosis != null) {
                    // Put the specific diagnosis in the request scope to pre-select it in the dropdown.
                    request.setAttribute("selectedDiagnosis", diagnosis);
                } else {
                    request.setAttribute("errorMessage", "No diagnosis found with ID: " + diagnosisId);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid diagnosis ID format.");
            }
        }

        // Forward to JSP
        request.getRequestDispatcher("/enterPrognosis.jsp").forward(request, response);
    }

}