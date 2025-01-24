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
import javax.persistence.ManyToOne;

/**
 *
 * @author Sheerwin
 */
@Entity
public class Rotas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rotaId;
    @ManyToOne
    private Users vetId;
    private Timestamp dateTime;
    private String shift;
    private Timestamp createdAt;
    private Timestamp deletedAt;
    private Timestamp updatedAt;

    public Rotas() {
    }

    public Rotas(Long rotaId, Users vetId, Timestamp dateTime, String shift) {
        this.rotaId = rotaId;
        this.vetId = vetId;
        this.dateTime = dateTime;
        this.shift = shift;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public Long getRotaId() {
        return rotaId;
    }
    
    public void setRotaId(Long rotaId) {
        this.rotaId = rotaId;
    }

    public Users getVetId() {
        return vetId;
    }

    public void setVetId(Users vetId) {
        this.vetId = vetId;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rotaId != null ? rotaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rotas)) {
            return false;
        }
        Rotas other = (Rotas) object;
        if ((this.rotaId == null && other.rotaId != null) || (this.rotaId != null && !this.rotaId.equals(other.rotaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Rotas[ rotaId=" + rotaId +
               ", customerId='" + vetId + '\''+
               ", name='" + dateTime + '\''+
               ", shift='" + shift + '\''+
               ", createdAt='" + createdAt + '\''+
               ", updatedAt='" + updatedAt + '\''+
               ", deletedAt='" + deletedAt + '\''+
               '}';    
    }
    
}
