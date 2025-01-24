/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sheerwin
 */
@Stateless
public class PrognosesFacade extends AbstractFacade<Prognoses> {

    @PersistenceContext(unitName = "APCS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrognosesFacade() {
        super(Prognoses.class);
    }
    
    public Prognoses findByDiagnosisId(Long diagnosisId) {
        try {
            return em.createQuery("SELECT p FROM Prognoses p WHERE p.diagnosisId.diagnosisId = :diagnosisId", Prognoses.class)
                     .setParameter("diagnosisId", diagnosisId)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    
}
