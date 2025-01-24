package controller;

import Model.Appointments;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import Model.AppointmentsFacade;
import java.util.List;

@WebServlet(name = "AppointmentsReport", value = "/AppointmentsReport")
public class AppointmentsReport extends HttpServlet {

    @EJB
    private AppointmentsFacade appointmentsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date today = new Date(); // Today's date
        List<Appointments> dailyAppointments = appointmentsFacade.findDailyAppointments(today);
        request.setAttribute("dailyAppointments", dailyAppointments);

        List<Object[]> monthlyData = appointmentsFacade.findMonthlyOverview();
        request.setAttribute("monthlyData", monthlyData);

        request.getRequestDispatcher("/appointmentReports.jsp").forward(request, response);
    }
}
