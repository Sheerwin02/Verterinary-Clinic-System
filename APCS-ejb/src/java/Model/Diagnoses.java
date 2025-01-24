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
public class Diagnoses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long diagnosisId;
    @OneToOne
    private Appointments appointmentId;
    private String diagnosis;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    public Diagnoses() {
    }

    public Diagnoses(Long diagnosisId, Appointments appointmentId, String diagnosis) {
        this.diagnosisId = diagnosisId;
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
    
    public Appointments getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Appointments appointmentId) {
        this.appointmentId = appointmentId;
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


    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Long getDiagnosisId() {
        return diagnosisId;
    }

    public void setId(Long diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diagnosisId != null ? diagnosisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diagnoses)) {
            return false;
        }
        Diagnoses other = (Diagnoses) object;
        if ((this.diagnosisId == null && other.diagnosisId != null) || (this.diagnosisId != null && !this.diagnosisId.equals(other.diagnosisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Diagnoses[ diagnosisId=" + diagnosisId +
               ", appointmentId='" + appointmentId + '\''+
               ", diagnosis='" + diagnosis + '\''+
               ", createdAt='" + createdAt + '\''+
               ", updatedAt='" + updatedAt + '\''+
               ", deletedAt='" + deletedAt + '\''+
               '}';    
    }
    
}
