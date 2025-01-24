/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "APCS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public Optional<Users> findByUsername(String username) {
        TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.username = :username", Users.class);
        query.setParameter("username", username);
        return query.getResultList().stream().findFirst();
    }
    
    public Users findUsersByUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM Users u WHERE u.username = :username", Users.class)
                     .setParameter("username", username)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null; // No user found with the specified username
        }
    }

    public List<Users> findByRole(String role) {
        TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.role = :role", Users.class);
        query.setParameter("role", role);
        return query.getResultList();
    }
    
    public void updateStatusByUserName(String userName, String status) {
        Users user = em.find(Users.class, userName);
        if (user != null) {
            user.setStatus(status);
            em.merge(user);
        }
    }
    
    public List<Users> findUsersByStatus(String status) {
        return em.createQuery("SELECT u FROM Users u WHERE u.status = :status", Users.class)
                 .setParameter("status", status)
                 .getResultList();
    }
    
    public void updateRoleByUserName(String userName, String role) {
        Users user = em.find(Users.class, userName);
        if (user != null) {
            user.setRole(role);
            em.merge(user);
        }
    }
    
    public Optional<Users> login(String username, String password) {
        TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.username = :username AND u.password = :password", Users.class);
        query.setParameter("username", username);
        query.setParameter("password", password); // Note: For demonstration. Use hashed passwords in production.
        try {
            Users user = query.getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    
    public List<Users> findByNameOrUsername(String searchQuery) {
        TypedQuery<Users> query = em.createQuery(
                "SELECT u FROM Users u WHERE LOWER(u.name) LIKE LOWER(:searchQuery) OR LOWER(u.username) LIKE LOWER(:searchQuery)", 
                Users.class);
        query.setParameter("searchQuery", "%" + searchQuery + "%");
        return query.getResultList();
    }
    
    public List<Users> findActiveUsersBySearchAndRole(String searchQuery, String roleFilter) {
        String queryStr = "SELECT u FROM Users u WHERE u.deletedAt IS NULL";

        if (roleFilter != null && !roleFilter.isEmpty() && !roleFilter.equals("All Roles")) {
            queryStr += " AND u.role = :roleFilter";
        }
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            queryStr += " AND (LOWER(u.name) LIKE LOWER(:searchQuery) OR LOWER(u.username) LIKE LOWER(:searchQuery))";
        }

        TypedQuery<Users> query = em.createQuery(queryStr, Users.class);

        if (roleFilter != null && !roleFilter.isEmpty() && !roleFilter.equals("All Roles")) {
            query.setParameter("roleFilter", roleFilter);
        }
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            query.setParameter("searchQuery", "%" + searchQuery + "%");
        }

        return query.getResultList();
    }
    
    public List<Users> findAllVets() {
        return em.createQuery("SELECT u FROM Users u WHERE u.role = :role", Users.class)
                .setParameter("role", "VET")
                .getResultList();
    }


}
