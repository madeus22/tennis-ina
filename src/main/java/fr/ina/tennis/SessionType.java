/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ina.tennis;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author amadeus
 */
@Entity
@Table(name = "session_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SessionType.findAll", query = "SELECT s FROM SessionType s"),
    @NamedQuery(name = "SessionType.findByIdSessionType", query = "SELECT s FROM SessionType s WHERE s.idSessionType = :idSessionType"),
    @NamedQuery(name = "SessionType.findByLabel", query = "SELECT s FROM SessionType s WHERE s.label = :label")})
public class SessionType implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessionType")
    private List<Session> sessionList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_session_type")
    private Integer idSessionType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "label")
    private String label;

    public SessionType() {
    }

    public SessionType(Integer idSessionType) {
        this.idSessionType = idSessionType;
    }

    public SessionType(Integer idSessionType, String label) {
        this.idSessionType = idSessionType;
        this.label = label;
    }

    public Integer getIdSessionType() {
        return idSessionType;
    }

    public void setIdSessionType(Integer idSessionType) {
        this.idSessionType = idSessionType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSessionType != null ? idSessionType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SessionType)) {
            return false;
        }
        SessionType other = (SessionType) object;
        if ((this.idSessionType == null && other.idSessionType != null) || (this.idSessionType != null && !this.idSessionType.equals(other.idSessionType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.ina.tennis.SessionType[ idSessionType=" + idSessionType + " ]";
    }

    @XmlTransient
    public List<Session> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }
    
}
