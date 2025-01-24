/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Rotas;
import Model.RotasFacade;
import Model.Users;
import Model.UsersFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sheerwin
 */
@WebServlet(name = "ProcessWeeklyRota", urlPatterns = {"/ProcessWeeklyRota"})
public class ProcessWeeklyRota extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;

    @EJB
    private RotasFacade rotasFacade;

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
        try {
             String weekOfStr = request.getParameter("weekOf"); // e.g., "2024-W11"
            String[] parts = weekOfStr.split("-W");
            int year = Integer.parseInt(parts[0]);
            int weekOfYear = Integer.parseInt(parts[1]);
            
            // Find the LocalDate that corresponds to the first day of the given week of the year
            LocalDate startOfWeek = LocalDate.now()
                .withYear(year)
                .with(WeekFields.of(Locale.getDefault()).weekOfYear(), weekOfYear)
                .with(TemporalAdjusters.previousOrSame(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek()));
            
            // Assuming you have predefined arrays or lists for days and shifts
            List<String> daysOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
            List<String> shifts = Arrays.asList("Morning", "Afternoon");

            for (int dayIndex = 0; dayIndex < daysOfWeek.size(); dayIndex++) {
                 LocalDate rotaDate = startOfWeek.plusDays(dayIndex);
                for (int shiftIndex = 0; shiftIndex < shifts.size(); shiftIndex++) {
                    String vetIdParam = request.getParameter("vetFor" + dayIndex + shiftIndex);
                    if (vetIdParam != null && !vetIdParam.isEmpty()) {
                        Long vetId = Long.parseLong(vetIdParam);
                        // Assuming UsersFacade and RotasFacade have appropriate methods like find() and create()
                        Users vet = usersFacade.find(vetId);
                        if (vet != null) {
                            Rotas rota = new Rotas();
                            rota.setVetId(vet);
                            rota.setDateTime(Timestamp.valueOf(rotaDate.atStartOfDay()));
                            rota.setShift(shifts.get(shiftIndex));
                            Timestamp now = new Timestamp(System.currentTimeMillis());
                            rota.setCreatedAt(now);
                            rota.setUpdatedAt(now);
                            rotasFacade.create(rota);
                        }
                    }
                }
            }

            response.sendRedirect("ViewRotas?rotaSuccess=true");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error processing weekly rota.");
            request.getRequestDispatcher("/weekRota.jsp").forward(request, response);
        }
    }
}