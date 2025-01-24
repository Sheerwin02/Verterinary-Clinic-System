/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Users;
import Model.UsersFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
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
@WebServlet(name = "PrepareWeeklyRota", urlPatterns = {"/PrepareWeeklyRota"})
public class PrepareWeeklyRota extends HttpServlet {

    @EJB
    private UsersFacade usersFacade; // Assuming this EJB is implemented to manage User entities

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Users> vets = usersFacade.findByRole("Vet"); // Fetch only vets
        List<String> daysOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        List<String> shifts = Arrays.asList("Morning", "Afternoon"); // Assuming these are your shifts

        request.setAttribute("vets", vets);
        request.setAttribute("daysOfWeek", daysOfWeek);
        request.setAttribute("shifts", shifts);

        request.getRequestDispatcher("/weekRota.jsp").forward(request, response);
    }
}
