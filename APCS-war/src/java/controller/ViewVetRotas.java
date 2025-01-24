/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Rotas;
import Model.RotasFacade;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sheerwin
 */
@WebServlet("/ViewVetRotas")
public class ViewVetRotas extends HttpServlet {
    @EJB
    private RotasFacade rotasFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users vet = (Users) session.getAttribute("user");
        if (vet == null || !vet.getRole().equals("Vet")) {
            response.sendRedirect("login.jsp"); // or any error page
            return;
        }

        System.out.println("Vet ID: " + vet.getUserId()); // Debugging

        LocalDate today = LocalDate.now();
        List<Rotas> todayRotas = rotasFacade.findRotasByVetId(vet.getUserId(), today);

        System.out.println("Today's Rotas: " + todayRotas.size()); // Debugging

        request.setAttribute("todayRotas", todayRotas);
        request.getRequestDispatcher("/vetDashboard.jsp").forward(request, response);
    }
}
