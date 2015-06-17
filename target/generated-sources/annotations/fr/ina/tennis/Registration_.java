package fr.ina.tennis;

import fr.ina.tennis.Session;
import fr.ina.tennis.Status;
import fr.ina.tennis.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-01T22:05:39")
@StaticMetamodel(Registration.class)
public class Registration_ { 

    public static volatile SingularAttribute<Registration, Date> registrationDate;
    public static volatile SingularAttribute<Registration, Session> idsession;
    public static volatile ListAttribute<Registration, Status> statusList;
    public static volatile SingularAttribute<Registration, User> login;
    public static volatile SingularAttribute<Registration, Integer> idregistration;

}