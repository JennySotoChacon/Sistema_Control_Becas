/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ariel
 */
@Entity
@Table(name = "seguimiento", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seguimiento.findAll", query = "SELECT s FROM Seguimiento s"),
    @NamedQuery(name = "Seguimiento.findByCodiSegu", query = "SELECT s FROM Seguimiento s WHERE s.codiSegu = :codiSegu"),
    @NamedQuery(name = "Seguimiento.findByFechSegu", query = "SELECT s FROM Seguimiento s WHERE s.fechSegu = :fechSegu"),
    @NamedQuery(name = "Seguimiento.findByFechReco", query = "SELECT s FROM Seguimiento s WHERE s.fechReco = :fechReco"),
    @NamedQuery(name = "Seguimiento.findByDescSegu", query = "SELECT s FROM Seguimiento s WHERE s.descSegu = :descSegu")})
public class Seguimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_segu")
    private Integer codiSegu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_segu")
    @Temporal(TemporalType.DATE)
    private Date fechSegu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_reco")
    @Temporal(TemporalType.DATE)
    private Date fechReco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "desc_segu")
    private String descSegu;
    @JoinColumn(name = "codi_empr", referencedColumnName = "codi_empr")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Empresa codiEmpr;

    public Seguimiento() {
    }

    public Seguimiento(Integer codiSegu) {
        this.codiSegu = codiSegu;
    }

    public Seguimiento(Integer codiSegu, Date fechSegu, Date fechReco, String descSegu) {
        this.codiSegu = codiSegu;
        this.fechSegu = fechSegu;
        this.fechReco = fechReco;
        this.descSegu = descSegu;
    }

    public Integer getCodiSegu() {
        return codiSegu;
    }

    public void setCodiSegu(Integer codiSegu) {
        this.codiSegu = codiSegu;
    }

    public Date getFechSegu() {
        return fechSegu;
    }

    public void setFechSegu(Date fechSegu) {
        this.fechSegu = fechSegu;
    }

    public Date getFechReco() {
        return fechReco;
    }

    public void setFechReco(Date fechReco) {
        this.fechReco = fechReco;
    }

    public String getDescSegu() {
        return descSegu;
    }

    public void setDescSegu(String descSegu) {
        this.descSegu = descSegu;
    }

    public Empresa getCodiEmpr() {
        return codiEmpr;
    }

    public void setCodiEmpr(Empresa codiEmpr) {
        this.codiEmpr = codiEmpr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiSegu != null ? codiSegu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seguimiento)) {
            return false;
        }
        Seguimiento other = (Seguimiento) object;
        if ((this.codiSegu == null && other.codiSegu != null) || (this.codiSegu != null && !this.codiSegu.equals(other.codiSegu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Seguimiento[ codiSegu=" + codiSegu + " ]";
    }
    
}
