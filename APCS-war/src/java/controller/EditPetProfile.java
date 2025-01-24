/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Pets;
import Model.PetsFacade;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/EditPetProfile")
public class EditPetProfile extends HttpServlet {

    @EJB
    private PetsFacade petsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long petId = Long.parseLong(request.getParameter("petId"));
        Pets pet = petsFacade.find(petId);
        if (pet != null) {
            request.setAttribute("pet", pet);
            request.getRequestDispatcher("/editPetProfile.jsp").forward(request, response);
        } else {
            response.sendRedirect("viewPetProfiles?errorMessage=Pet not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long petId = Long.parseLong(request.getParameter("petId"));
            Pets pet = petsFacade.find(petId);

            if (pet != null) {
                pet.setName(request.getParameter("name"));
                pet.setSpecies(request.getParameter("species"));
                // Update other pet fields as necessary

                petsFacade.edit(pet);
                response.sendRedirect("ViewPetProfiles?successMessage=Pet profile updated successfully");
            } else {
                response.sendRedirect("ViewPetProfiles?errorMessage=Pet not found");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("viewPetProfiles?errorMessage=Invalid pet ID");
        }
    }
}
