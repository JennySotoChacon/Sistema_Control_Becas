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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Owner
 */
@Entity
@Table(name = "donacion", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Donacion.findAll", query = "SELECT d FROM Donacion d"),
    @NamedQuery(name = "Donacion.findByCodiDona", query = "SELECT d FROM Donacion d WHERE d.codiDona = :codiDona"),
    @NamedQuery(name = "Donacion.findByNombDona", query = "SELECT d FROM Donacion d WHERE d.nombDona = :nombDona"),
    @NamedQuery(name = "Donacion.findByPlazDona", query = "SELECT d FROM Donacion d WHERE d.plazDona = :plazDona"),
    @NamedQuery(name = "Donacion.findByCantCuot", query = "SELECT d FROM Donacion d WHERE d.cantCuot = :cantCuot"),
    @NamedQuery(name = "Donacion.findByMontTot", query = "SELECT d FROM Donacion d WHERE d.montTot = :montTot"),
    @NamedQuery(name = "Donacion.findByMontPend", query = "SELECT d FROM Donacion d WHERE d.montPend = :montPend"),
    @NamedQuery(name = "Donacion.findByFechDona", query = "SELECT d FROM Donacion d WHERE d.fechDona = :fechDona"),
    @NamedQuery(name = "Donacion.findByEstaDona", query = "SELECT d FROM Donacion d WHERE d.estaDona = :estaDona")})
public class Donacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_dona")
    private Integer codiDona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomb_dona")
    private String nombDona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plaz_dona")
    private int plazDona;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cant_cuot")
    private BigDecimal cantCuot;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mont_tot")
    private BigDecimal montTot;
    @Column(name = "mont_pend")
    private BigDecimal montPend;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_dona")
    @Temporal(TemporalType.DATE)
    private Date fechDona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_dona")
    private int estaDona;
    @JoinColumn(name = "codi_tipo_dona", referencedColumnName = "codi_tipo_dona")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoDonacion codiTipoDona;
    @JoinColumn(name = "codi_empr", referencedColumnName = "codi_empr")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empresa codiEmpr;

    public Donacion() {
    }

    public Donacion(Integer codiDona) {
        this.codiDona = codiDona;
    }

    public Donacion(Integer codiDona, String nombDona, int plazDona, BigDecimal cantCuot, BigDecimal montTot, Date fechDona, int estaDona) {
        this.codiDona = codiDona;
        this.nombDona = nombDona;
        this.plazDona = plazDona;
        this.cantCuot = cantCuot;
        this.montTot = montTot;
        this.fechDona = fechDona;
        this.estaDona = estaDona;
    }

    public Integer getCodiDona() {
        return codiDona;
    }

    public void setCodiDona(Integer codiDona) {
        this.codiDona = codiDona;
    }

    public String getNombDona() {
        return nombDona;
    }

    public void setNombDona(String nombDona) {
        this.nombDona = nombDona;
    }

    public int getPlazDona() {
        return plazDona;
    }

    public void setPlazDona(int plazDona) {
        this.plazDona = plazDona;
    }

    public BigDecimal getCantCuot() {
        return cantCuot;
    }

    public void setCantCuot(BigDecimal cantCuot) {
        this.cantCuot = cantCuot;
    }

    public BigDecimal getMontTot() {
        return montTot;
    }

    public void setMontTot(BigDecimal montTot) {
        this.montTot = montTot;
    }

    public BigDecimal getMontPend() {
        return montPend;
    }

    public void setMontPend(BigDecimal montPend) {
        this.montPend = montPend;
    }

    public Date getFechDona() {
        return fechDona;
    }

    public void setFechDona(Date fechDona) {
        this.fechDona = fechDona;
    }

    public int getEstaDona() {
        return estaDona;
    }

    public void setEstaDona(int estaDona) {
        this.estaDona = estaDona;
    }

    public TipoDonacion getCodiTipoDona() {
        return codiTipoDona;
    }

    public void setCodiTipoDona(TipoDonacion codiTipoDona) {
        this.codiTipoDona = codiTipoDona;
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
        hash += (codiDona != null ? codiDona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Donacion)) {
            return false;
        }
        Donacion other = (Donacion) object;
        if ((this.codiDona == null && other.codiDona != null) || (this.codiDona != null && !this.codiDona.equals(other.codiDona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Donacion[ codiDona=" + codiDona + " ]";
    }
    
}
