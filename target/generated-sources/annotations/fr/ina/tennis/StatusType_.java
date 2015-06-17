package fr.ina.tennis;

import fr.ina.tennis.Status;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-01T22:05:39")
@StaticMetamodel(StatusType.class)
public class StatusType_ { 

    public static volatile SingularAttribute<StatusType, Integer> idStatusType;
    public static volatile SingularAttribute<StatusType, String> color;
    public static volatile SingularAttribute<StatusType, String> description;
    public static volatile ListAttribute<StatusType, Status> statusList;
    public static volatile SingularAttribute<StatusType, String> label;

}