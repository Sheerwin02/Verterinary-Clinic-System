package Model;

import Model.Appointments;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-03-20T16:45:46")
@StaticMetamodel(Diagnoses.class)
public class Diagnoses_ { 

    public static volatile SingularAttribute<Diagnoses, Timestamp> createdAt;
    public static volatile SingularAttribute<Diagnoses, Timestamp> deletedAt;
    public static volatile SingularAttribute<Diagnoses, Long> diagnosisId;
    public static volatile SingularAttribute<Diagnoses, Appointments> appointmentId;
    public static volatile SingularAttribute<Diagnoses, String> diagnosis;
    public static volatile SingularAttribute<Diagnoses, Timestamp> updatedAt;

}