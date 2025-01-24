package Model;

import Model.Pets;
import Model.Users;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-03-20T16:45:46")
@StaticMetamodel(Appointments.class)
public class Appointments_ { 

    public static volatile SingularAttribute<Appointments, Timestamp> dateTime;
    public static volatile SingularAttribute<Appointments, Timestamp> createdAt;
    public static volatile SingularAttribute<Appointments, Timestamp> deletedAt;
    public static volatile SingularAttribute<Appointments, Pets> petId;
    public static volatile SingularAttribute<Appointments, Long> appointmentId;
    public static volatile SingularAttribute<Appointments, Users> vetId;
    public static volatile SingularAttribute<Appointments, String> status;
    public static volatile SingularAttribute<Appointments, Timestamp> updatedAt;

}