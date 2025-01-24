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
public class Prognoses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prognosisId;
    @OneToOne
    private Diagnoses diagnosisId;
    private String prognosis;
    private Boolean followedUpRequired;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    public Prognoses() {
    }
    
    public Prognoses(Long prognosisId, Diagnoses diagnosisId, String prognosis, Boolean followedUpRequired) {
        this.prognosisId = prognosisId;
        this.diagnosisId = diagnosisId;
        this.prognosis = prognosis;
        this.followedUpRequired = followedUpRequired;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public Diagnoses getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Diagnoses diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public String getPrognosis() {
        return prognosis;
    }

    public void setPrognosis(String prognosis) {
        this.prognosis = prognosis;
    }

    public Boolean getFollowedUpRequired() {
        return followedUpRequired;
    }

    public void setFollowedUpRequired(Boolean followedUpRequired) {
        this.followedUpRequired = followedUpRequired;
    }

    public Long getPrognosisId() {
        return prognosisId;
    }

    public void setPrognosisId(Long prognosisId) {
        this.prognosisId = prognosisId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prognosisId != null ? prognosisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prognoses)) {
            return false;
        }
        Prognoses other = (Prognoses) object;
        if ((this.prognosisId == null && other.prognosisId != null) || (this.prognosisId != null && !this.prognosisId.equals(other.prognosisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Prognoses[ prognosisId=" + prognosisId +
               ", diagnosisId='" + diagnosisId + '\''+
               ", prognosis='" + prognosis + '\''+
               ", createdAt='" + createdAt + '\''+
               ", updatedAt='" + updatedAt + '\''+
               ", deletedAt='" + deletedAt + '\'';
    }
    
}
