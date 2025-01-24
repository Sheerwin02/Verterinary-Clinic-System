package Model;

import Model.Customers;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-03-20T16:45:46")
@StaticMetamodel(Pets.class)
public class Pets_ { 

    public static volatile SingularAttribute<Pets, Timestamp> createdAt;
    public static volatile SingularAttribute<Pets, Timestamp> deletedAt;
    public static volatile SingularAttribute<Pets, Long> petId;
    public static volatile SingularAttribute<Pets, String> species;
    public static volatile SingularAttribute<Pets, Customers> customerId;
    public static volatile SingularAttribute<Pets, String> name;
    public static volatile SingularAttribute<Pets, Timestamp> updatedAt;

}