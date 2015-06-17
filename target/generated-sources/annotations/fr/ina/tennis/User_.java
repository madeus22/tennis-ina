package fr.ina.tennis;

import fr.ina.tennis.Registration;
import fr.ina.tennis.Session;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-01T22:05:39")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Boolean> presta;
    public static volatile ListAttribute<User, Registration> registrationList;
    public static volatile SingularAttribute<User, String> email;
    public static volatile ListAttribute<User, Session> sessionList;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> login;

}