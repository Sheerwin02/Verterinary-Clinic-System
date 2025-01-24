package controller;

import Model.Users;
import Model.UsersFacade;
import Model.VetExpertises;
import Model.VetExpertisesFacade;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;
    @EJB
    private VetExpertisesFacade vetExpertisesFacade;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long userId = (Long) request.getSession().getAttribute("userId"); // Example, adjust as needed
            if (userId == null) {
                throw new Exception("No user ID found in session.");
            }
            Users user = usersFacade.find(userId);
            if (user == null) {
                throw new Exception("User not found.");
            }
            request.setAttribute("user", user);

            if ("Vet".equals(user.getRole())) {
                VetExpertises vetExpertise = vetExpertisesFacade.findVetExpertiseByVetId(userId);
                if (vetExpertise != null) {
                    request.setAttribute("vetExpertise", vetExpertise);
                }
            }

            request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Log the stack trace for debugging
            request.setAttribute("errorMessage", "Error preparing profile edit: " + e.getMessage());
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response); // Consider redirecting to a generic error page
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String newPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String areaOfExpertise = request.getParameter("areaOfExpertise");

        // Assume validations are initially passed
        boolean validationsPassed = true;

        // Validate required fields
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || phoneNumber == null || phoneNumber.isEmpty()) {
            request.setAttribute("errorMessage", "Name, email, and phone number are required.");
            validationsPassed = false;
        }

        // Validate email format
        if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            request.setAttribute("errorMessage", "Invalid email format.");
            validationsPassed = false;
        }

        // Validate phone number format
        if (!phoneNumber.matches("^[0-9]{10}$")) { // Adjust regex according to your needs
            request.setAttribute("errorMessage", "Invalid phone number format.");
            validationsPassed = false;
        }

        // Validate new password if provided
        if (newPassword != null && !newPassword.isEmpty()) {
            if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("errorMessage", "Passwords do not match.");
                validationsPassed = false;
            } else if (!newPassword.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$")) { // Ensure at least one letter, one number, and at least 8 characters
                request.setAttribute("errorMessage", "Password must include at least one number, one letter, and be at least 8 characters long.");
                validationsPassed = false;
            }
        }

        // If validations fail, forward back to the edit page with error messages
        if (!validationsPassed) {
            request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
            return;
        }

        
        try {
            Long userId = Long.parseLong(request.getParameter("userId"));
            Users user = usersFacade.find(userId);

            // Update password if not empty
            //String newPassword = request.getParameter("password");
            if (newPassword != null && !newPassword.isEmpty()) {
                user.setPassword(newPassword);
            }

            // Update other fields
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPhoneNumber(request.getParameter("phoneNumber"));
            user.setAddress(request.getParameter("address"));
            user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            // Handle vet-specific area of expertise
            if ("Vet".equals(user.getRole())) {
                //String areaOfExpertise = request.getParameter("areaOfExpertise");
                VetExpertises vetExpertise = vetExpertisesFacade.findByVetId(userId);
                if (vetExpertise == null) {
                    vetExpertise = new VetExpertises();
                    vetExpertise.setVetId(user);
                }
                vetExpertise.setAreaOfExperties(areaOfExpertise);
                vetExpertisesFacade.edit(vetExpertise);
            }

            usersFacade.edit(user);

            // Redirect to the correct dashboard based on the role
            String redirectTo = "receiptionistDashboard.jsp"; // default
            if ("Vet".equals(user.getRole())) {
                redirectTo = "vetDashboard.jsp";
            } else if ("Managing Staff".equals(user.getRole())) {
                redirectTo = "managingStaffDashboard.jsp";
            }
            response.sendRedirect(redirectTo);
        } catch (Exception e) {
            throw new ServletException("Error updating profile", e);
        }
    }
}
