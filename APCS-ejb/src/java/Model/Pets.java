/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Sheerwin
 */
@Entity
public class Pets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long petId;
    @OneToOne
    private Customers customerId;
    private String name;
    private String species;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    public Pets() {
    }
    
    public Pets(Long petId, Customers customerId, String name, String species) {
        this.petId = petId;
        this.customerId = customerId;
        this.name = name;
        this.species = species;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
    
    public Customers getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customers customerId) {
        this.customerId = customerId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (petId != null ? petId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pets)) {
            return false;
        }
        Pets other = (Pets) object;
        if ((this.petId == null && other.petId != null) || (this.petId != null && !this.petId.equals(other.petId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Pets[ petId=" + petId + 
               ", customerId='" + customerId + '\''+
               ", name='" + name + '\''+
               ", species='" + species + '\''+
               ", createdAt='" + createdAt + '\''+
               ", updatedAt='" + updatedAt + '\''+
               ", deletedAt='" + deletedAt + '\''+
               '}';    
    }
    
}
