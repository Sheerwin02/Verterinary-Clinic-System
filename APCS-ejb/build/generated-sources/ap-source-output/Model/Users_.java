package Model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-03-20T16:45:46")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, Timestamp> createdAt;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, Timestamp> deletedAt;
    public static volatile SingularAttribute<Users, String> address;
    public static volatile SingularAttribute<Users, String> role;
    public static volatile SingularAttribute<Users, String> phoneNumber;
    public static volatile SingularAttribute<Users, String> name;
    public static volatile SingularAttribute<Users, Long> userId;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, String> username;
    public static volatile SingularAttribute<Users, String> status;
    public static volatile SingularAttribute<Users, Timestamp> updatedAt;

}