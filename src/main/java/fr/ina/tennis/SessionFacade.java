/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ina.tennis;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author amadeus
 */
@Stateless
public class SessionFacade extends AbstractFacade<Session> {
    @PersistenceContext(unitName = "fr.ina_tennis-ina_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    private static Date maxDate;
    private static Date minDate;
    private static Integer currentMonth = 0;

    static {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        if(cal.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY){
            cal.add(Calendar.DATE, Calendar.MONDAY - cal.get(Calendar.DAY_OF_WEEK));
        } else {
            cal.add(Calendar.DATE, cal.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY);
        }
        
        minDate = cal.getTime();
        
        cal.add(Calendar.DATE, 41);
        
        maxDate = cal.getTime();

    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SessionFacade() {
        super(Session.class);
    }
    
    @Deprecated
    public List<Session> getRollingWeekSessions(){
        return em.createNamedQuery("Session.findByRollingWeek").setParameter("maxDate", maxDate).setParameter("minDate", minDate).getResultList();
    }
    
    public List<Session> getSessionsInInterval(Date start, Date end){
        return em.createNamedQuery("Session.findByRollingWeek").setParameter("maxDate", end).setParameter("minDate", start).setHint("eclipselink.refresh", "true").getResultList();
    }
    
    @Deprecated
    public List<Session> getCurrentMonthSessions(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        ///calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date frist = calendar.getTime();
        
        return em.createNamedQuery("Session.findAllInMonth").setParameter("month", currentMonth).getResultList();
    }
    
}
