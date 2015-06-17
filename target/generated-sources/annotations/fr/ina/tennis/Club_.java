package fr.ina.tennis;

import fr.ina.tennis.Session;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-01T22:05:39")
@StaticMetamodel(Club.class)
public class Club_ { 

    public static volatile SingularAttribute<Club, String> phone;
    public static volatile SingularAttribute<Club, Integer> idclub;
    public static volatile SingularAttribute<Club, String> address;
    public static volatile ListAttribute<Club, Session> sessionList;
    public static volatile SingularAttribute<Club, String> name;

}