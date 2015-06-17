/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ina.tennis;

/**
 *
 * @author amadeus
 */
 
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
 
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
 
@ManagedBean
@ViewScoped
@Stateful
@DeclareRoles({"player","admin"})
public class ScheduleView implements Serializable {
 
    @EJB
    private fr.ina.tennis.SessionTypeFacade sessionTypeFacade;
    
    @EJB
    private ClubFacade clubFacade;
    
    @EJB
    private UserFacade userFacade;
    
    @EJB
    private SessionFacade sessionFacade;
    
    @EJB
    private SessionController sessionController;
    
    @EJB
    private StatusTypeFacade statusTypeFacade;
    
    private ScheduleModel eventModel;
     
    private ScheduleModel lazyEventModel;
 
    private ScheduleEvent event = new DefaultScheduleEvent();
    
    private Session session;
    private List<Session> currentMonthSessions;
    private List<User> selectedUsers;
    private List<SessionType> sessionTypes;
    private List<Club> clubs;
    private List<User> users;
    private User current;
 
    @PostConstruct
    public void init() {
        
        currentMonthSessions = sessionFacade.getRollingWeekSessions();
        selectedUsers = new ArrayList<User>();
        session = new Session();
        session.setPlannedDate(new Date());
        sessionTypes = sessionTypeFacade.findAll();
        clubs = clubFacade.findAll();
        users = userFacade.findAll();
        
        current = userFacade.find("achaieb");
        
        eventModel = new DefaultScheduleModel();
        
        for(Session tempSession: currentMonthSessions) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(tempSession.getPlannedDate());
            calendar.add(Calendar.HOUR, 1);
            
            DefaultScheduleEvent tempEvent = new DefaultScheduleEvent(tempSession.getLabel(), tempSession.getPlannedDate(), calendar.getTime());
            tempEvent.setStyleClass("fc-event-" + tempSession.getSessionType().getLabel().toLowerCase());
            tempEvent.setData(tempSession);
            eventModel.addEvent(tempEvent);
        }
        
        lazyEventModel = new LazyScheduleModel() {
             
            @Override
            public void loadEvents(Date start, Date end) {
               
                for(Session session : sessionFacade.getSessionsInInterval(start, end)) {
                    
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(session.getPlannedDate());
                    calendar.add(Calendar.HOUR, 1);
            
                    DefaultScheduleEvent tempEvent = new DefaultScheduleEvent(session.getLabel(), session.getPlannedDate(), calendar.getTime());
                    tempEvent.setStyleClass("fc-event-" + session.getSessionType().getLabel().toLowerCase());
                    tempEvent.setData(session);
            
                    addEvent(tempEvent);
                }
            }   
        };
    }
     
    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month
         
        return date.getTime();
    }
     
    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
         
        return calendar.getTime();
    }
     
    public ScheduleModel getEventModel() {
        return eventModel;
    }
     
    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }
 
    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
 
        return calendar;
    }
     
    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);
         
        return t.getTime();
    }
     
    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);
         
        return t.getTime();
    }
     
    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);
         
        return t.getTime();
    }
     
    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);     
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);
         
        return t.getTime();
    }
 
    private Date today6Pm() {
        Calendar t = (Calendar) today().clone(); 
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);
         
        return t.getTime();
    }
     
    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);
         
        return t.getTime();
    }
     
    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);
         
        return t.getTime();
    }
     
    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone(); 
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);
         
        return t.getTime();
    }
     
    public ScheduleEvent getEvent() {
        return event;
    }
 
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
     
    public void deleteEvent(){
        session.setValidated(Boolean.FALSE);
        sessionController.update(session);
        
    }
    
    public void addEvent(ActionEvent actionEvent) {
        
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(session.getPlannedDate());
        calendar.add(Calendar.HOUR, 1);
        
        session.setBookedBy(current);
        
        // selected users
        List<Registration> registrations = new ArrayList();
        
        StatusType present = statusTypeFacade.find(1);
        
        for(User user : selectedUsers) {
            
            Registration registration = new Registration();
            registration.setLogin(user);
            registration.setRegistrationDate(new Date());
            
            Status status = new Status(present);
            status.setStatusDate(new Date());
            registration.getStatusList().add(status);
            
            registrations.add(registration);
            
        }
        
        session.getRegistrationList().clear();
            session.getRegistrationList().addAll(registrations);
        
        sessionFacade.create(session);
        session.getIdsession();
        ((DefaultScheduleEvent)event).setTitle(session.getLabel());
        ((DefaultScheduleEvent)event).setStartDate(session.getPlannedDate());
        ((DefaultScheduleEvent)event).setEndDate(calendar.getTime());
        
        if(event.getId() == null)
            eventModel.addEvent(event);
        else
            eventModel.updateEvent(event);
         
        event = new DefaultScheduleEvent();
    }
     
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
        session = (Session) event.getData();
        selectedUsers.clear();
        for(Registration registration : session.getRegistrationList()) {
            selectedUsers.add(registration.getLogin());
        }
    }
     
    public void onViewChange(SelectEvent selectEvent) {
        
        event = (ScheduleEvent) selectEvent.getObject();
        
    }
    
    
    public void onDateSelect(SelectEvent selectEvent) {
        session.setPlannedDate(((Date) selectEvent.getObject()));
        session.getPlannedDate().setHours(12);
        
        //event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
        //session.getPlannedDate();
    }
     
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<SessionType> getSessionTypes() {
        return sessionTypes;
    }

    public void setSessionTypes(List<SessionType> sessionTypes) {
        this.sessionTypes = sessionTypes;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List<User> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }
      
}