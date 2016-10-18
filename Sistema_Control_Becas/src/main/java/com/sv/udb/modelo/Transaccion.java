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
@Table(name = "transaccion", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaccion.findAll", query = "SELECT t FROM Transaccion t"),
    @NamedQuery(name = "Transaccion.findByCodiTran", query = "SELECT t FROM Transaccion t WHERE t.codiTran = :codiTran"),
    @NamedQuery(name = "Transaccion.findByMontTran", query = "SELECT t FROM Transaccion t WHERE t.montTran = :montTran"),
    @NamedQuery(name = "Transaccion.findByFechEntrTran", query = "SELECT t FROM Transaccion t WHERE t.fechEntrTran = :fechEntrTran"),
    @NamedQuery(name = "Transaccion.findByFechConfTran", query = "SELECT t FROM Transaccion t WHERE t.fechConfTran = :fechConfTran"),
    @NamedQuery(name = "Transaccion.findByFechSaliTran", query = "SELECT t FROM Transaccion t WHERE t.fechSaliTran = :fechSaliTran"),
    @NamedQuery(name = "Transaccion.findByTipoTran", query = "SELECT t FROM Transaccion t WHERE t.tipoTran = :tipoTran")})
public class Transaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_tran")
    private Integer codiTran;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mont_tran")
    private BigDecimal montTran;
    @Column(name = "fech_entr_tran")
    @Temporal(TemporalType.DATE)
    private Date fechEntrTran;
    @Column(name = "fech_conf_tran")
    @Temporal(TemporalType.DATE)
    private Date fechConfTran;
    @Column(name = "fech_sali_tran")
    @Temporal(TemporalType.DATE)
    private Date fechSaliTran;
    @Column(name = "tipo_tran")
    private Integer tipoTran;
    @JoinColumn(name = "codi_dona", referencedColumnName = "codi_dona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Donacion codiDona;
    @JoinColumn(name = "codi_deta_beca", referencedColumnName = "codi_deta_beca")
    @ManyToOne(fetch = FetchType.LAZY)
    private DetalleBeca codiDetaBeca;

    public Transaccion() {
    }

    public Transaccion(Integer codiTran) {
        this.codiTran = codiTran;
    }

    public Integer getCodiTran() {
        return codiTran;
    }

    public void setCodiTran(Integer codiTran) {
        this.codiTran = codiTran;
    }

    public BigDecimal getMontTran() {
        return montTran;
    }

    public void setMontTran(BigDecimal montTran) {
        this.montTran = montTran;
    }

    public Date getFechEntrTran() {
        return fechEntrTran;
    }

    public void setFechEntrTran(Date fechEntrTran) {
        this.fechEntrTran = fechEntrTran;
    }

    public Date getFechConfTran() {
        return fechConfTran;
    }

    public void setFechConfTran(Date fechConfTran) {
        this.fechConfTran = fechConfTran;
    }

    public Date getFechSaliTran() {
        return fechSaliTran;
    }

    public void setFechSaliTran(Date fechSaliTran) {
        this.fechSaliTran = fechSaliTran;
    }

    public Integer getTipoTran() {
        return tipoTran;
    }

    public void setTipoTran(Integer tipoTran) {
        this.tipoTran = tipoTran;
    }

    public Donacion getCodiDona() {
        return codiDona;
    }

    public void setCodiDona(Donacion codiDona) {
        this.codiDona = codiDona;
    }

    public DetalleBeca getCodiDetaBeca() {
        return codiDetaBeca;
    }

    public void setCodiDetaBeca(DetalleBeca codiDetaBeca) {
        this.codiDetaBeca = codiDetaBeca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTran != null ? codiTran.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaccion)) {
            return false;
        }
        Transaccion other = (Transaccion) object;
        if ((this.codiTran == null && other.codiTran != null) || (this.codiTran != null && !this.codiTran.equals(other.codiTran))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Transaccion[ codiTran=" + codiTran + " ]";
    }
    
}
