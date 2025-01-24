/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Rotas;
import Model.RotasFacade;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewRotas")
public class ViewRotas extends HttpServlet {

    @EJB
    private RotasFacade rotasFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String weekOfStr = request.getParameter("weekOf");
        String searchTerm = request.getParameter("searchTerm");
        String shiftFilter = request.getParameter("shiftFilter");

        if (weekOfStr != null && !weekOfStr.isEmpty()) {
            // Convert weekOfStr to LocalDate for the start of the week
            LocalDate weekOf = LocalDate.parse(weekOfStr + "-1", DateTimeFormatter.ISO_WEEK_DATE);
            request.setAttribute("weekStartDate", weekOf); // Pass this start date to the JSP

            Timestamp startOfWeek = Timestamp.valueOf(weekOf.atStartOfDay());
            Timestamp endOfWeek = Timestamp.valueOf(weekOf.plusWeeks(1).atStartOfDay());

            List<Rotas> filteredRotas = rotasFacade.findByWeekVetNameAndShift(startOfWeek, endOfWeek, searchTerm, shiftFilter);
            request.setAttribute("rotas", filteredRotas);
        } else {
            request.setAttribute("rotas", rotasFacade.findAll());
        }

        request.getRequestDispatcher("/viewRotas.jsp").forward(request, response);
    }
}

