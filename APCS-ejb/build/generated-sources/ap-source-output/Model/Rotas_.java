package Model;

import Model.Users;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-03-20T16:45:46")
@StaticMetamodel(Rotas.class)
public class Rotas_ { 

    public static volatile SingularAttribute<Rotas, Timestamp> dateTime;
    public static volatile SingularAttribute<Rotas, Timestamp> createdAt;
    public static volatile SingularAttribute<Rotas, Timestamp> deletedAt;
    public static volatile SingularAttribute<Rotas, String> shift;
    public static volatile SingularAttribute<Rotas, Users> vetId;
    public static volatile SingularAttribute<Rotas, Long> rotaId;
    public static volatile SingularAttribute<Rotas, Timestamp> updatedAt;

}