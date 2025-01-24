package Model;

import Model.Diagnoses;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-03-20T16:45:46")
@StaticMetamodel(Prognoses.class)
public class Prognoses_ { 

    public static volatile SingularAttribute<Prognoses, Timestamp> createdAt;
    public static volatile SingularAttribute<Prognoses, Timestamp> deletedAt;
    public static volatile SingularAttribute<Prognoses, Boolean> followedUpRequired;
    public static volatile SingularAttribute<Prognoses, Diagnoses> diagnosisId;
    public static volatile SingularAttribute<Prognoses, Long> prognosisId;
    public static volatile SingularAttribute<Prognoses, String> prognosis;
    public static volatile SingularAttribute<Prognoses, Timestamp> updatedAt;

}