package fr.ina.tennis;

import fr.ina.tennis.Registration;
import fr.ina.tennis.StatusType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-01T22:05:39")
@StaticMetamodel(Status.class)
public class Status_ { 

    public static volatile SingularAttribute<Status, Registration> idRegistration;
    public static volatile SingularAttribute<Status, StatusType> idStatusType;
    public static volatile SingularAttribute<Status, Integer> idstatus;
    public static volatile SingularAttribute<Status, Date> statusDate;

}