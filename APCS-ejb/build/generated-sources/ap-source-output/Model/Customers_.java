package Model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-03-20T16:45:46")
@StaticMetamodel(Customers.class)
public class Customers_ { 

    public static volatile SingularAttribute<Customers, Timestamp> createdAt;
    public static volatile SingularAttribute<Customers, Timestamp> deletedAt;
    public static volatile SingularAttribute<Customers, String> phoneNumber;
    public static volatile SingularAttribute<Customers, String> address;
    public static volatile SingularAttribute<Customers, Long> customerId;
    public static volatile SingularAttribute<Customers, String> name;
    public static volatile SingularAttribute<Customers, String> email;
    public static volatile SingularAttribute<Customers, Timestamp> updatedAt;

}