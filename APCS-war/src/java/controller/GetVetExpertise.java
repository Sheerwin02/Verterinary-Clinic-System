package controller;

import Model.VetExpertises;
import Model.VetExpertisesFacade;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/GetVetExpertise")
public class GetVetExpertise extends HttpServlet {

    @EJB
    private VetExpertisesFacade vetExpertisesFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        Long vetId = Long.parseLong(request.getParameter("vetId"));
        
        // Fetch the list of expertise for the given vet ID
        List<VetExpertises> vetExpertisesList = vetExpertisesFacade.findVetByVetId(vetId);
        System.out.println(vetExpertisesList);

        // Check if the list is not null and not empty
        if (vetExpertisesList != null && !vetExpertisesList.isEmpty()) {
            // Iterate over the list of expertise and print each
            for (VetExpertises expertise : vetExpertisesList) {
                out.println(expertise.getAreaOfExperties());
            }
        } else {
            // If the list is null or empty, print a default message
            out.print("Expertise not available");
        }
    }
}
