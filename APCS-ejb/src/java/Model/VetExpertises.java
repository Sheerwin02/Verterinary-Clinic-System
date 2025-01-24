/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Sheerwin
 */
@Entity
public class VetExpertises implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vetExpertiseId;
    @ManyToOne
    @JoinColumn(name = "VET_ID_USERID", referencedColumnName = "USERID")
    private Users vetId;
    private String areaOfExperties;
    
    
    public VetExpertises() {
    }

    public VetExpertises(Users vetId, String areaOfExperties) {
        this.vetId = vetId;
        this.areaOfExperties = areaOfExperties;
    }

    public Users getVetId() {
        return vetId;
    }

    public void setVetId(Users vetId) {
        this.vetId = vetId;
    }

    public String getAreaOfExperties() {
        return areaOfExperties;
    }

    public void setAreaOfExperties(String areaOfExperties) {
        this.areaOfExperties = areaOfExperties;
    }


    public Long getVetExpertiseId() {
        return vetExpertiseId;
    }

    public void setId(Long vetExpertiseId) {
        this.vetExpertiseId = vetExpertiseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vetExpertiseId != null ? vetExpertiseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VetExpertises)) {
            return false;
        }
        VetExpertises other = (VetExpertises) object;
        if ((this.vetExpertiseId == null && other.vetExpertiseId != null) || (this.vetExpertiseId != null && !this.vetExpertiseId.equals(other.vetExpertiseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.VetExpertises[ vetExpertiseId=" + vetExpertiseId +
               ", vetId='" + vetId + '\''+
               '}';
    }
    
}
