/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Pets;
import Model.PetsFacade;
import Model.Users;
import Model.UsersFacade;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PrepareAddAppointment", urlPatterns = {"/PrepareAddAppointment"})
public class PrepareAddAppointment extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;
    @EJB
    private PetsFacade petsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Users> vets = usersFacade.findByRole("Vet");
        List<Pets> pets = petsFacade.findAll();

        request.setAttribute("vets", vets);
        request.setAttribute("pets", pets);

        request.getRequestDispatcher("/addAppointments.jsp").forward(request, response);
    }
}