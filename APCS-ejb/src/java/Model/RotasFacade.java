/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Sheerwin
 */
@Stateless
public class RotasFacade extends AbstractFacade<Rotas> {

    @PersistenceContext(unitName = "APCS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RotasFacade() {
        super(Rotas.class);
    }
        
    public List<Rotas> findByWeekVetNameAndShift(Timestamp startOfWeek, Timestamp endOfWeek, String vetName, String shift) {
        String queryString = "SELECT r FROM Rotas r WHERE r.dateTime BETWEEN :startOfWeek AND :endOfWeek" +
                (vetName != null && !vetName.isEmpty() ? " AND LOWER(r.vetId.name) LIKE LOWER(:vetName)" : "") +
                (shift != null && !shift.isEmpty() ? " AND r.shift = :shift" : "");

        TypedQuery<Rotas> query = em.createQuery(queryString, Rotas.class);
        query.setParameter("startOfWeek", startOfWeek);
        query.setParameter("endOfWeek", endOfWeek);

        if (vetName != null && !vetName.isEmpty()) {
            query.setParameter("vetName", "%" + vetName + "%");
        }
        if (shift != null && !shift.isEmpty()) {
            query.setParameter("shift", shift);
        }

        return query.getResultList();
    }
    
    public List<Rotas> findByWeekAndFilters(Timestamp weekStart, Timestamp weekEnd, String vetName, String shift) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Rotas> cq = cb.createQuery(Rotas.class);
        Root<Rotas> rotas = cq.from(Rotas.class);
        List<Predicate> predicates = new ArrayList<>();

        // Ensure dateTime is within the specified week range
        predicates.add(cb.greaterThanOrEqualTo(rotas.<Timestamp>get("dateTime"), weekStart));
        predicates.add(cb.lessThanOrEqualTo(rotas.<Timestamp>get("dateTime"), weekEnd));

        // Optionally filter by vet name
        if (vetName != null && !vetName.isEmpty()) {
            predicates.add(cb.like(rotas.get("vetId").get("name"), "%" + vetName + "%"));
        }

        // Optionally filter by shift
        if (shift != null && !shift.isEmpty()) {
            predicates.add(cb.equal(rotas.get("shift"), shift));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        return em.createQuery(cq).getResultList();
    }
    
    public List<Rotas> findRotasByVetId(Long vetId, LocalDate date) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Rotas> cq = cb.createQuery(Rotas.class);
        Root<Rotas> rotas = cq.from(Rotas.class);

        // Convert LocalDate to Timestamp
        Timestamp startOfDay = Timestamp.valueOf(date.atStartOfDay());
        Timestamp endOfDay = Timestamp.valueOf(date.plusDays(1).atStartOfDay());

        cq.where(
            cb.and(
                cb.equal(rotas.get("vetId").get("userId"), vetId),
                cb.greaterThanOrEqualTo(rotas.<Timestamp>get("dateTime"), startOfDay),
                cb.lessThan(rotas.<Timestamp>get("dateTime"), endOfDay)
            )
        );

        return em.createQuery(cq).getResultList();
    }
    
    public List<Rotas> findRotasByVetAndWeek(Long vetId, LocalDate startOfWeek, LocalDate endOfWeek) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Rotas> cq = cb.createQuery(Rotas.class);
        Root<Rotas> rotas = cq.from(Rotas.class);

        Predicate vetPredicate = cb.equal(rotas.get("vetId").get("userId"), vetId);

        Timestamp startTimestamp = Timestamp.valueOf(startOfWeek.atStartOfDay());
        Timestamp endTimestamp = Timestamp.valueOf(endOfWeek.atTime(23, 59, 59));

        Predicate datePredicate = cb.between(rotas.<Timestamp>get("dateTime"), startTimestamp, endTimestamp);

        cq.where(cb.and(vetPredicate, datePredicate));
        return em.createQuery(cq).getResultList();
    }

}
    

