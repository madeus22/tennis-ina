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
@Table(name = "status_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusType.findAll", query = "SELECT s FROM StatusType s"),
    @NamedQuery(name = "StatusType.findByIdStatusType", query = "SELECT s FROM StatusType s WHERE s.idStatusType = :idStatusType"),
    @NamedQuery(name = "StatusType.findByLabel", query = "SELECT s FROM StatusType s WHERE s.label = :label"),
    @NamedQuery(name = "StatusType.findByDescription", query = "SELECT s FROM StatusType s WHERE s.description = :description"),
    @NamedQuery(name = "StatusType.findByColor", query = "SELECT s FROM StatusType s WHERE s.color = :color")})
public class StatusType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_status_type")
    private Integer idStatusType;
    @Size(max = 50)
    @Column(name = "label")
    private String label;
    @Size(max = 100)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "color")
    private String color;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStatusType")
    private List<Status> statusList;

    public StatusType() {
    }

    public StatusType(Integer idStatusType) {
        this.idStatusType = idStatusType;
    }

    public Integer getIdStatusType() {
        return idStatusType;
    }

    public void setIdStatusType(Integer idStatusType) {
        this.idStatusType = idStatusType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @XmlTransient
    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStatusType != null ? idStatusType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusType)) {
            return false;
        }
        StatusType other = (StatusType) object;
        if ((this.idStatusType == null && other.idStatusType != null) || (this.idStatusType != null && !this.idStatusType.equals(other.idStatusType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.ina.tennis.StatusType[ idStatusType=" + idStatusType + " ]";
    }
    
}
