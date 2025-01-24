/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Prognoses;
import Model.PrognosesFacade;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditPrognosis")
public class EditPrognosis extends HttpServlet {

    @EJB
    private PrognosesFacade prognosesFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String prognosisIdStr = request.getParameter("prognosisId");
        if (prognosisIdStr != null && !prognosisIdStr.isEmpty()) {
            Long prognosisId = Long.parseLong(prognosisIdStr);
            Prognoses prognosis = prognosesFacade.find(prognosisId);
            if (prognosis != null) {
                request.setAttribute("prognosis", prognosis);
                request.getRequestDispatcher("/editPrognosis.jsp").forward(request, response);
            } else {
                response.sendRedirect("viewDiagnosis.jsp?errorMessage=Prognosis not found");
            }
        } else {
            response.sendRedirect("viewDiagnosis.jsp?errorMessage=Invalid request");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long prognosisId = Long.parseLong(request.getParameter("prognosisId"));
            String prognosisText = request.getParameter("prognosis");
            Boolean followedUpRequired = "on".equals(request.getParameter("followedUpRequired"));

            Prognoses prognosis = prognosesFacade.find(prognosisId);
            if (prognosis != null) {
                prognosis.setPrognosis(prognosisText);
                prognosis.setFollowedUpRequired(followedUpRequired);
                prognosesFacade.edit(prognosis);
                response.sendRedirect("ViewDiagnosis?successMessage=Prognosis updated successfully");
            } else {
                response.sendRedirect("editPrognosis.jsp?prognosisId=" + prognosisId + "&errorMessage=Prognosis not found");
            }
        } catch (Exception e) {
            response.sendRedirect("editPrognosis.jsp?errorMessage=Error updating prognosis");
        }
    }
}
