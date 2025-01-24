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
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ViewPrognosis")
public class ViewPrognosis extends HttpServlet {

    @EJB
    private PrognosesFacade prognosesFacade;

    @EJB
    private DiagnosesFacade diagnosesFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String diagnosisIdStr = request.getParameter("diagnosisId");
        if (diagnosisIdStr != null && !diagnosisIdStr.isEmpty()) {
            Long diagnosisId = Long.parseLong(diagnosisIdStr);
            Prognoses prognosis = prognosesFacade.findByDiagnosisId(diagnosisId);
            if (prognosis != null) {
                request.setAttribute("prognosis", prognosis);
            } else {
                request.setAttribute("errorMessage", "Prognosis not found for Diagnosis ID: " + diagnosisId);
            }
        } else {
            request.setAttribute("errorMessage", "Invalid Diagnosis ID.");
        }
        
        request.getRequestDispatcher("/viewPrognosis.jsp").forward(request, response);
    }
}
