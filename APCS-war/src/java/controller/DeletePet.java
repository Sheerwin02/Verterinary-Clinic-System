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

@WebServlet(name = "DeletePet", urlPatterns = {"/DeletePet"})
public class DeletePet extends HttpServlet {

    @EJB
    private PetsFacade petsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String petIdParam = request.getParameter("petId");
        if(petIdParam != null && !petIdParam.isEmpty()) {
            try {
                Long petId = Long.parseLong(petIdParam);
                Pets pet = petsFacade.find(petId);
                if(pet != null) {
                    petsFacade.remove(pet);
                    response.sendRedirect("viewPetProfiles?successMessage=Pet profile deleted successfully.");
                } else {
                    response.sendRedirect("viewPetProfiles?errorMessage=Pet profile not found.");
                }
            } catch(NumberFormatException e) {
                response.sendRedirect("viewPetProfiles?errorMessage=Invalid pet ID.");
            }
        } else {
            response.sendRedirect("viewPetProfiles?errorMessage=Pet ID is required.");
        }
    }
}
