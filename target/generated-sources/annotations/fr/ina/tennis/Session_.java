package fr.ina.tennis;

import fr.ina.tennis.Club;
import fr.ina.tennis.Registration;
import fr.ina.tennis.SessionType;
import fr.ina.tennis.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-01T22:05:39")
@StaticMetamodel(Session.class)
public class Session_ { 

    public static volatile SingularAttribute<Session, Date> plannedDate;
    public static volatile ListAttribute<Session, Registration> registrationList;
    public static volatile SingularAttribute<Session, Club> club;
    public static volatile SingularAttribute<Session, Integer> idsession;
    public static volatile SingularAttribute<Session, SessionType> sessionType;
    public static volatile SingularAttribute<Session, Short> mailSent;
    public static volatile SingularAttribute<Session, Boolean> validated;
    public static volatile SingularAttribute<Session, User> bookedBy;

}