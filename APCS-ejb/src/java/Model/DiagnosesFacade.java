/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sheerwin
 */
@Stateless
public class DiagnosesFacade extends AbstractFacade<Diagnoses> {

    @PersistenceContext(unitName = "APCS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiagnosesFacade() {
        super(Diagnoses.class);
    }
    
}
