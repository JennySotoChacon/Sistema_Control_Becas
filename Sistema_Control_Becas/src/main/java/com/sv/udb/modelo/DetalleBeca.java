/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Owner
 */
@Entity
@Table(name = "detalle_beca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleBeca.findAll", query = "SELECT d FROM DetalleBeca d"),
    @NamedQuery(name = "DetalleBeca.findByCodiDetaBeca", query = "SELECT d FROM DetalleBeca d WHERE d.codiDetaBeca = :codiDetaBeca"),
    @NamedQuery(name = "DetalleBeca.findByCantMese", query = "SELECT d FROM DetalleBeca d WHERE d.cantMese = :cantMese"),
    @NamedQuery(name = "DetalleBeca.findByEstaDetaBeca", query = "SELECT d FROM DetalleBeca d WHERE d.estaDetaBeca = :estaDetaBeca")})
public class DetalleBeca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_deta_beca")
    private Integer codiDetaBeca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cant_mese")
    private int cantMese;
    @Column(name = "esta_deta_beca")
    private Integer estaDetaBeca;
    @JoinColumn(name = "codi_tipo_beca", referencedColumnName = "codi_tipo_beca")
    @ManyToOne(optional = false)
    private TipoBeca codiTipoBeca;
    @JoinColumn(name = "codi_beca", referencedColumnName = "codi_beca")
    @ManyToOne(optional = false)
    private Beca codiBeca;

    public DetalleBeca() {
    }

    public DetalleBeca(Integer codiDetaBeca) {
        this.codiDetaBeca = codiDetaBeca;
    }

    public DetalleBeca(Integer codiDetaBeca, int cantMese) {
        this.codiDetaBeca = codiDetaBeca;
        this.cantMese = cantMese;
    }

    public Integer getCodiDetaBeca() {
        return codiDetaBeca;
    }

    public void setCodiDetaBeca(Integer codiDetaBeca) {
        this.codiDetaBeca = codiDetaBeca;
    }

    public int getCantMese() {
        return cantMese;
    }

    public void setCantMese(int cantMese) {
        this.cantMese = cantMese;
    }

    public Integer getEstaDetaBeca() {
        return estaDetaBeca;
    }

    public void setEstaDetaBeca(Integer estaDetaBeca) {
        this.estaDetaBeca = estaDetaBeca;
    }

    public TipoBeca getCodiTipoBeca() {
        return codiTipoBeca;
    }

    public void setCodiTipoBeca(TipoBeca codiTipoBeca) {
        this.codiTipoBeca = codiTipoBeca;
    }

    public Beca getCodiBeca() {
        return codiBeca;
    }

    public void setCodiBeca(Beca codiBeca) {
        this.codiBeca = codiBeca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiDetaBeca != null ? codiDetaBeca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleBeca)) {
            return false;
        }
        DetalleBeca other = (DetalleBeca) object;
        if ((this.codiDetaBeca == null && other.codiDetaBeca != null) || (this.codiDetaBeca != null && !this.codiDetaBeca.equals(other.codiDetaBeca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.DetalleBeca[ codiDetaBeca=" + codiDetaBeca + " ]";
    }
    
}
