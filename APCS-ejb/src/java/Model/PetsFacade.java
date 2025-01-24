/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sheerwin
 */
@Stateless
public class PetsFacade extends AbstractFacade<Pets> {

    @PersistenceContext(unitName = "APCS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PetsFacade() {
        super(Pets.class);
    }
    
    public Pets findPetById(Long id) {
        return em.find(Pets.class, id);
    }
    
    public String findSpeciesByPetId(Long petId) {
        TypedQuery<String> query = em.createQuery(
                "SELECT p.species FROM Pets p WHERE p.id = :petId", String.class);
        query.setParameter("petId", petId);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            // Handle no result or any other exception as needed
            return null;
        }
    }
}
