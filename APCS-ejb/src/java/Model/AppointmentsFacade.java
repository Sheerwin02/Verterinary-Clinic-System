/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 *
 * @author Sheerwin
 */
@Stateless
public class AppointmentsFacade extends AbstractFacade<Appointments> {

    @PersistenceContext(unitName = "APCS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppointmentsFacade() {
        super(Appointments.class);
    }
    
    // Method to find appointments by Vet ID
    public List<Appointments> findByVetId(Long vetId) {
        return em.createQuery("SELECT a FROM Appointments a WHERE a.vetId.userId = :vetId", Appointments.class)
                 .setParameter("vetId", vetId)
                 .getResultList();
    }
    
    public List<Appointments> findDailyAppointments(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date todayStart = calendar.getTime();
        
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrowStart = calendar.getTime();

        return em.createQuery("SELECT a FROM Appointments a WHERE a.dateTime >= :todayStart AND a.dateTime < :tomorrowStart ORDER BY a.dateTime ASC", Appointments.class)
                .setParameter("todayStart", todayStart, TemporalType.TIMESTAMP)
                .setParameter("tomorrowStart", tomorrowStart, TemporalType.TIMESTAMP)
                .getResultList();
    }

    public List<Object[]> findMonthlyOverview() {
        return em.createNativeQuery("SELECT DATE(a.dateTime), COUNT(*), SUM(CASE WHEN a.status = 'Completed' THEN 1 ELSE 0 END), SUM(CASE WHEN a.status = 'Cancelled' THEN 1 ELSE 0 END) FROM Appointments a WHERE MONTH(a.dateTime) = MONTH(CURRENT_DATE) AND YEAR(a.dateTime) = YEAR(CURRENT_DATE) GROUP BY DATE(a.dateTime)")
                .getResultList();
    }
}
    
