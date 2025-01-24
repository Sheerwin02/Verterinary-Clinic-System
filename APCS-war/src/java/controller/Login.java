/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Users;
import Model.UsersFacade;
import java.io.IOException;
import static java.lang.System.out;
import java.util.Optional;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password");

        Optional<Users> userOptional = usersFacade.login(username, password);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            // Check if the user status is not "Active"
            if (!"Active".equals(user.getStatus())) {
                if ("Pending".equals(user.getStatus())) {
                    request.setAttribute("errorMessage", "Your account is pending approval. Please contact the managing staff.");
                } else {
                    request.setAttribute("errorMessage", "Your account is not active. Please contact the administrator.");
                }
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            HttpSession session = request.getSession(true); // Create a new session if one doesn't exist
            session.setAttribute("user", user); // Store user object in session

            switch (user.getRole()) {
                case "Managing Staff":
                    response.sendRedirect("managingStaffDashboard.jsp"); // Redirect to Managing Staff dashboard
                    break;
                case "Vet":
                    response.sendRedirect("ViewVetRotas"); // Redirect to Vet dashboard
                    break;
                case "Receptionist":
                    response.sendRedirect("receiptionistDashboard.jsp"); // Corrected the spelling for Receptionist dashboard
                    break;
                default:
                    session.invalidate(); // Invalidate session for unrecognized roles
                    request.setAttribute("errorMessage", "Unauthorized access. Please contact the administrator.");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    break;
            }
        } else {
            // For added security, consider not specifying whether the username or password was incorrect
            request.setAttribute("errorMessage", "Invalid credentials. Please try again.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // It's a good practice to redirect GET requests to avoid direct access
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Login servlet handles user authentication and session management.";
    }
}