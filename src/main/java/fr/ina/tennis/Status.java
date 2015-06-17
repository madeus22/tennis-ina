/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ina.tennis;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s"),
    @NamedQuery(name = "Status.findByIdstatus", query = "SELECT s FROM Status s WHERE s.idstatus = :idstatus"),
    @NamedQuery(name = "Status.findByStatusDate", query = "SELECT s FROM Status s WHERE s.statusDate = :statusDate")})
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idstatus")
    private Integer idstatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusDate;
    @JoinColumn(name = "id_status_type", referencedColumnName = "id_status_type")
    @ManyToOne(optional = false)
    private StatusType idStatusType;
    @JoinColumn(name = "id_registration", referencedColumnName = "idregistration")
    @ManyToOne(optional = false)
    private Registration idRegistration;

    public Status() {
    }

    public Status(Integer idstatus) {
        this.idstatus = idstatus;
    }

    public Status(Integer idstatus, Date statusDate) {
        this.idstatus = idstatus;
        this.statusDate = statusDate;
    }

    public Status(StatusType statusType) {
        this.idStatusType = statusType;
    }
    
    public Integer getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(Integer idstatus) {
        this.idstatus = idstatus;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public StatusType getIdStatusType() {
        return idStatusType;
    }

    public void setIdStatusType(StatusType idStatusType) {
        this.idStatusType = idStatusType;
    }

    public Registration getIdRegistration() {
        return idRegistration;
    }

    public void setIdRegistration(Registration idRegistration) {
        this.idRegistration = idRegistration;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstatus != null ? idstatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Status)) {
            return false;
        }
        Status other = (Status) object;
        if ((this.idstatus == null && other.idstatus != null) || (this.idstatus != null && !this.idstatus.equals(other.idstatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.ina.tennis.Status[ idstatus=" + idstatus + " ]";
    }
    
}
