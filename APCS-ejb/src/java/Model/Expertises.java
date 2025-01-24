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

/**
 *
 * @author Sheerwin
 */
@Entity
public class Expertises implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long expertiseId;
    private String expertiseName;

    public Expertises() {
    }
    
    public Expertises(Long expertiseId, String expertiseName) {
        this.expertiseId = expertiseId;
        this.expertiseName = expertiseName;
    }

    public Long getExpertiseId() {
        return expertiseId;
    }

    public void setExpertiseId(Long expertiseId) {
        this.expertiseId = expertiseId;
    }

    public String getExpertiseName() {
        return expertiseName;
    }

    public void setExpertiseName(String expertiseName) {
        this.expertiseName = expertiseName;
    }

    public Long getId() {
        return expertiseId;
    }

    public void setId(Long expertiseId) {
        this.expertiseId = expertiseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (expertiseId != null ? expertiseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expertises)) {
            return false;
        }
        Expertises other = (Expertises) object;
        if ((this.expertiseId == null && other.expertiseId != null) || (this.expertiseId != null && !this.expertiseId.equals(other.expertiseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Expertises[ expertiseId=" + expertiseId +
               ", expertiesName='" + expertiseName + '\''+
               '}';    
    }
    
}
