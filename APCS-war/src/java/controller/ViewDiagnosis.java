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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "ViewDiagnosis", urlPatterns = {"/ViewDiagnosis"})
public class ViewDiagnosis extends HttpServlet {

    @EJB
    private PrognosesFacade prognosesFacade;

    @EJB
    private DiagnosesFacade diagnosesFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Diagnoses> diagnosesList = diagnosesFacade.findAll();
        Map<Long, Boolean> prognosisExistsMap = new HashMap<>();

        for (Diagnoses diagnosis : diagnosesList) {
            Prognoses prognosis = prognosesFacade.findByDiagnosisId(diagnosis.getDiagnosisId());
            prognosisExistsMap.put(diagnosis.getDiagnosisId(), prognosis != null);
        }

        request.setAttribute("diagnoses", diagnosesList);
        request.setAttribute("prognosisExistsMap", prognosisExistsMap);
        request.getRequestDispatcher("/viewDiagnosis.jsp").forward(request, response);
    }
}
