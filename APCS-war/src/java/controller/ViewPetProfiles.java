/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Pets;
import Model.PetsFacade;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ViewPetProfiles", urlPatterns = {"/ViewPetProfiles"})
public class ViewPetProfiles extends HttpServlet {

    @EJB
    private PetsFacade petsFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch all pets from the database
        List<Pets> pets = petsFacade.findAll();

        // Set the pets list as an attribute to the request
        request.setAttribute("pets", pets);

        // Forward the request to the JSP page
        request.getRequestDispatcher("/viewPetProfiles.jsp").forward(request, response);
    }
}