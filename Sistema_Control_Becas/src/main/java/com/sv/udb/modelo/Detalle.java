/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eduardo
 */
@Entity
@Table(name = "detalle", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalle.findAll", query = "SELECT d FROM Detalle d"),
    @NamedQuery(name = "Detalle.findByCodiDeta", query = "SELECT d FROM Detalle d WHERE d.codiDeta = :codiDeta"),
    @NamedQuery(name = "Detalle.findByFechDeta", query = "SELECT d FROM Detalle d WHERE d.fechDeta = :fechDeta"),
    @NamedQuery(name = "Detalle.findByMontAlum", query = "SELECT d FROM Detalle d WHERE d.montAlum = :montAlum"),
    @NamedQuery(name = "Detalle.findByEstaDeta", query = "SELECT d FROM Detalle d WHERE d.estaDeta = :estaDeta")})
public class Detalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_deta")
    private Integer codiDeta;
    @Column(name = "fech_deta")
    @Temporal(TemporalType.DATE)
    private Date fechDeta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mont_alum")
    private BigDecimal montAlum;
    @Column(name = "esta_deta")
    private Integer estaDeta;
    @JoinColumn(name = "codi_tran", referencedColumnName = "codi_tran")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Transaccion codiTran;

    public Detalle() {
    }

    public Detalle(Integer codiDeta) {
        this.codiDeta = codiDeta;
    }

    public Integer getCodiDeta() {
        return codiDeta;
    }

    public void setCodiDeta(Integer codiDeta) {
        this.codiDeta = codiDeta;
    }

    public Date getFechDeta() {
        return fechDeta;
    }

    public void setFechDeta(Date fechDeta) {
        this.fechDeta = fechDeta;
    }

    public BigDecimal getMontAlum() {
        return montAlum;
    }

    public void setMontAlum(BigDecimal montAlum) {
        this.montAlum = montAlum;
    }

    public Integer getEstaDeta() {
        return estaDeta;
    }

    public void setEstaDeta(Integer estaDeta) {
        this.estaDeta = estaDeta;
    }

    public Transaccion getCodiTran() {
        return codiTran;
    }

    public void setCodiTran(Transaccion codiTran) {
        this.codiTran = codiTran;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiDeta != null ? codiDeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalle)) {
            return false;
        }
        Detalle other = (Detalle) object;
        if ((this.codiDeta == null && other.codiDeta != null) || (this.codiDeta != null && !this.codiDeta.equals(other.codiDeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Detalle[ codiDeta=" + codiDeta + " ]";
    }
    
}
