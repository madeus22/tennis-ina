/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ina.tennis.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import fr.ina.tennis.User;
import fr.ina.tennis.UserFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;
/**
 *
 * @author amadeus
 */
@FacesConverter("usersConverter")
public class UsersConverter implements Converter {

    @EJB
    UserFacade userFacade;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //List<User> users = (List<User>) context.getApplication().evaluateExpressionGet(context.getELContext(), "#{scheduleView.selectedUsers}", List.class);
       
        return userFacade.find(value);
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((User)value).getLogin();
    }
    
    
}
