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
import java.util.regex.Pattern;

@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;
    @EJB
    private VetExpertisesFacade vetExpertisesFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String role = request.getParameter("role");
        String areaOfExpertise = request.getParameter("areaOfExpertise");

        boolean isValid = true;
        if (!validateUsername(username)) {
            request.setAttribute("usernameError", "Username already exists or is invalid.");
            isValid = false;
        }
        if (!validatePassword(password)) {
            request.setAttribute("passwordError", "Password must include at least one number, one letter, and be at least 8 characters long.");
            isValid = false;
        }
        if (!validateEmail(email)) {
            request.setAttribute("emailError", "Invalid email format.");
            isValid = false;
        }
        
        // Validate phone number
        if (!validatePhoneNumber(phoneNumber)) {
            request.setAttribute("phoneNumberError", "Invalid phone number format.");
            isValid = false;
        }
        
        
        if (name.isEmpty() || role.isEmpty()) {
            request.setAttribute("generalError", "Please ensure all required fields are filled out.");
            isValid = false;
        }

        if (!isValid) {
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        // Indicate that form submission was attempted
        request.setAttribute("formSubmitted", true);

        String status = "Pending";
        Users user = new Users(username, name, password, phoneNumber, email, address, role, status);
        
        try {
            usersFacade.create(user);
            if ("Vet".equals(role) && areaOfExpertise != null && !areaOfExpertise.trim().isEmpty()) {
                VetExpertises vetExpertise = new VetExpertises(user, areaOfExpertise);
                vetExpertisesFacade.create(vetExpertise);
            }
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    private boolean validateUsername(String username) {
        // Check if the username already exists in the system
        Users existingUser = usersFacade.findUsersByUsername(username);
        return existingUser == null; // Returns true if username does not exist, ensuring uniqueness
    }

    private boolean validatePassword(String password) {
        // Password must include at least one number and one character, and be at least 8 characters long
        boolean hasLetter = false;
        boolean hasDigit = false;
        if (password != null && password.length() >= 8) {
            for (char c : password.toCharArray()) {
                if (Character.isLetter(c)) {
                    hasLetter = true;
                } else if (Character.isDigit(c)) {
                    hasDigit = true;
                }
                if (hasLetter && hasDigit) {
                    return true; // Password is valid
                }
            }
        }
        return false; // Password does not meet the criteria
    }
    
    private boolean validatePhoneNumber(String phoneNumber) {
        // checks if the phone number consists of 10 digits.
        String phoneRegex = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return phoneNumber != null && pattern.matcher(phoneNumber).matches();
    }


    private boolean validateEmail(String email) {
        // Basic email format validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return email != null && pattern.matcher(email).matches();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }
}
