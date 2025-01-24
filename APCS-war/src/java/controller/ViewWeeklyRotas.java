/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Rotas;
import Model.RotasFacade;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ViewWeeklyRotas", urlPatterns = {"/ViewWeeklyRotas"})
public class ViewWeeklyRotas extends HttpServlet {

    @EJB
    private RotasFacade rotasFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vetIdStr = request.getParameter("vetId");
        Long vetId = Long.parseLong(vetIdStr);

        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);
        
        List<Rotas> weeklyRotas = rotasFacade.findRotasByVetAndWeek(vetId, startOfWeek, endOfWeek);
        
        request.setAttribute("weeklyRotas", weeklyRotas);
        request.getRequestDispatcher("/viewWeeklyRotas.jsp").forward(request, response);
    }
}
