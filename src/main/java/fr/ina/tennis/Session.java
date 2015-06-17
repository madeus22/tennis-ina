/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ina.tennis;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amadeus
 */
@Entity
@Table(name = "session")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Session.findAll", query = "SELECT s FROM Session s"),
    @NamedQuery(name = "Session.findByIdsession", query = "SELECT s FROM Session s WHERE s.idsession = :idsession"),
    @NamedQuery(name = "Session.findByPlannedDate", query = "SELECT s FROM Session s WHERE s.plannedDate = :plannedDate"),
    @NamedQuery(name = "Session.findByMailSent", query = "SELECT s FROM Session s WHERE s.mailSent = :mailSent"),
    @NamedQuery(name = "Session.findByRollingWeek", query = "SELECT s FROM Session s WHERE s.plannedDate < :maxDate and s.plannedDate >= :minDate"),
    @NamedQuery(name = "Session.findAllInMonth", query = "SELECT s FROM Session s WHERE FUNC('MONTH', s.plannedDate) = :month")
    })
    
public class Session implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsession")
    private Integer idsession;
    @Basic(optional = false)
    @NotNull
    @Column(name = "planned_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date plannedDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mail_sent")
    private short mailSent;
    @JoinColumn(name = "session_type", referencedColumnName = "id_session_type")
    @ManyToOne(optional = false)
    private SessionType sessionType;
    @JoinColumn(name = "booked_by", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private User bookedBy;
    @JoinColumn(name = "club", referencedColumnName = "idclub")
    @ManyToOne
    private Club club;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsession", fetch = FetchType.EAGER)
    private List<Registration> registrationList = new ArrayList();
    @Column(name = "validated")
    private Boolean validated;
    
    public Session() {
    }

    public Session(Integer idsession) {
        this.idsession = idsession;
    }

    public Session(Integer idsession, Date plannedDate, short mailSent) {
        this.idsession = idsession;
        this.plannedDate = plannedDate;
        this.mailSent = mailSent;
    }

    public Integer getIdsession() {
        return idsession;
    }

    public void setIdsession(Integer idsession) {
        this.idsession = idsession;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public short getMailSent() {
        return mailSent;
    }

    public void setMailSent(short mailSent) {
        this.mailSent = mailSent;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public User getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(User bookedBy) {
        this.bookedBy = bookedBy;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
    
    public String getLabel() {
        DateFormat format = new SimpleDateFormat("HH:mm");
        return ((getSessionType() != null)?getSessionType().getLabel():"") + " - " + ((getPlannedDate() != null)?format.format(getPlannedDate()):"");
    }
    
    public void setLabel(String label) {}

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsession != null ? idsession.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Session)) {
            return false;
        }
        Session other = (Session) object;
        if ((this.idsession == null && other.idsession != null) || (this.idsession != null && !this.idsession.equals(other.idsession))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.ina.tennis.Session[ idsession=" + idsession + " ]";
    }

    public List<Registration> getRegistrationList() {
        return registrationList;
    }

    public void setRegistrationList(List<Registration> registrationList) {
        this.registrationList = registrationList;
    }
    
    
}
