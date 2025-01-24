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
import javax.persistence.OneToOne;

/**
 *
 * @author Sheerwin
 */
@Entity
public class Appointments implements Serializable {

    public Appointments() {
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointmentId;
    @ManyToOne
    private Users vetId;
    @OneToOne
    private Pets petId;
    private Timestamp dateTime;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    
    public Appointments(Long appointmentId, Users vetId, Pets petId, Timestamp dateTime, String status) {
        this.appointmentId = appointmentId;
        this.vetId = vetId;
        this.petId = petId;
        this.dateTime = dateTime;
        this.status = status;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
    
    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }
    
    public Users getVetId() {
        return vetId;
    }

    public void setVetId(Users vetId) {
        this.vetId = vetId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pets getPetId() {
        return petId;
    }

    public void setPetId(Pets petId) {
        this.petId = petId;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointmentId != null ? appointmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointments)) {
            return false;
        }
        Appointments other = (Appointments) object;
        if ((this.appointmentId == null && other.appointmentId != null) || (this.appointmentId != null && !this.appointmentId.equals(other.appointmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Appointments[ appointmentId=" + appointmentId + 
               ", vetId='" + vetId + '\''+
               ", petId='" + petId + '\''+
               ", dateTime='" + dateTime + '\''+
               ", status='" + status + '\''+
               ", createdAt='" + createdAt + '\''+
               ", updatedAt='" + updatedAt + '\''+
               ", deletedAt='" + deletedAt + '\''+
        '}';
    }
    
}
