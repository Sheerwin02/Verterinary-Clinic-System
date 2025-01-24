package controller;

import Model.Pets;
import Model.PetsFacade;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetPetSpecies", urlPatterns = {"/GetPetSpecies"})
public class GetPetSpecies extends HttpServlet {

    @EJB
    private PetsFacade petsFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Long petId = Long.parseLong(request.getParameter("petId"));

        Pets pet = petsFacade.find(petId);
        String species = pet != null ? pet.getSpecies() : "Not available";
        
        String json = "{\"species\": \"" + species + "\"}";

        out.print(json);
        out.flush();
    }
}
