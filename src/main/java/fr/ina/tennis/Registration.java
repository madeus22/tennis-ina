/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ina.tennis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author amadeus
 */
@Entity
@Table(name = "registration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registration.findAll", query = "SELECT r FROM Registration r"),
    @NamedQuery(name = "Registration.findByRegistrationDate", query = "SELECT r FROM Registration r WHERE r.registrationDate = :registrationDate"),
    @NamedQuery(name = "Registration.findByIdregistration", query = "SELECT r FROM Registration r WHERE r.idregistration = :idregistration")})
public class Registration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idregistration")
    private Integer idregistration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRegistration")
    @OrderBy("statusDate DESC")
    private List<Status> statusList = new ArrayList();
    @JoinColumn(name = "login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private User login;
    @JoinColumn(name = "idsession", referencedColumnName = "idsession")
    @ManyToOne(optional = false)
    private Session idsession;

    public Registration() {
    }

    public Registration(Integer idregistration) {
        this.idregistration = idregistration;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getIdregistration() {
        return idregistration;
    }

    public void setIdregistration(Integer idregistration) {
        this.idregistration = idregistration;
    }

    @XmlTransient
    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

    public User getLogin() {
        return login;
    }

    public void setLogin(User login) {
        this.login = login;
    }

    public Session getIdsession() {
        return idsession;
    }

    public void setIdsession(Session idsession) {
        this.idsession = idsession;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idregistration != null ? idregistration.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registration)) {
            return false;
        }
        Registration other = (Registration) object;
        if ((this.idregistration == null && other.idregistration != null) || (this.idregistration != null && !this.idregistration.equals(other.idregistration))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.ina.tennis.Registration[ idregistration=" + idregistration + " ]";
    }
    
}
