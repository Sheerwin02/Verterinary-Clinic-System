/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sheerwin
 */
@Stateless
public class VetExpertisesFacade extends AbstractFacade<VetExpertises> {

    @PersistenceContext(unitName = "APCS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VetExpertisesFacade() {
        super(VetExpertises.class);
    }
    
    public VetExpertises findByVetId(Long vetId) {
        try {
            Query query = em.createNativeQuery(
                "SELECT * FROM VetExpertises v WHERE v.VET_ID_USERID = ?", 
                VetExpertises.class);
            query.setParameter(1, vetId);
            return (VetExpertises) query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Or handle it in another way that suits your application
        }
    }
    
    public List<VetExpertises> findVetByVetId(Long vetId) {
        Query query = em.createNativeQuery(
                "SELECT * FROM VetExpertises v WHERE v.VET_ID_USERID = ?", VetExpertises.class);
        query.setParameter(1, vetId);
        return query.getResultList();
    }
    
    public VetExpertises findVetExpertiseByVetId(Long vetId) {
        try {
            TypedQuery<VetExpertises> query = em.createQuery(
                "SELECT ve FROM VetExpertises ve WHERE ve.vetId = :vetId", VetExpertises.class);
            query.setParameter("vetId", vetId);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public String findAreaOfExpertiseByVetId(Long vetId) {
        try {
            String jpql = "SELECT v.areaOfExperties FROM VetExpertises v WHERE v.vetId.userId = :vetId";
            return em.createQuery(jpql, String.class)
                     .setParameter("vetId", vetId)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null; // Return null if no expertise found
        } catch (Exception e) {
            // Log error or handle exception as needed
            return null;
        }
    }
    
    public String findExpertiseByVetId(Long vetId) {
        TypedQuery<String> query = em.createQuery("SELECT ve.areaOfExperties FROM VetExpertises ve WHERE ve.vetId.userId = :vetId", String.class);
        query.setParameter("vetId", vetId);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            // Handle no result or any exception as needed
            return null;
        }
    }
}

