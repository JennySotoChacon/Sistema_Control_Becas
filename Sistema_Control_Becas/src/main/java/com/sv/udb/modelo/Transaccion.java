/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @NamedQuery(name = "Transaccion.findByFechTran", query = "SELECT t FROM Transaccion t WHERE t.fechTran = :fechTran"),
    @NamedQuery(name = "Transaccion.findByMontTota", query = "SELECT t FROM Transaccion t WHERE t.montTota = :montTota"),
    @NamedQuery(name = "Transaccion.findByTipoTran", query = "SELECT t FROM Transaccion t WHERE t.tipoTran = :tipoTran"),
    @NamedQuery(name = "Transaccion.findByEstaTran", query = "SELECT t FROM Transaccion t WHERE t.estaTran = :estaTran")})

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
    @Column(name = "fech_tran")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechTran;
    @Column(name = "mont_tota")
    private BigDecimal montTota;
    @Column(name = "tipo_tran")
    private Integer tipoTran;
    @Column(name = "esta_tran")
    private Integer estaTran;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiTran", fetch = FetchType.LAZY)
    private List<Detalle> detalleList;
    @JoinColumn(name = "codi_dona", referencedColumnName = "codi_dona")
    @ManyToOne(fetch = FetchType.LAZY)
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

    public Date getFechTran() {
        return fechTran;
    }

    public void setFechTran(Date fechTran) {
        this.fechTran = fechTran;
    }

    public BigDecimal getMontTota() {
        return montTota;
    }

    public void setMontTota(BigDecimal montTota) {
        this.montTota = montTota;
    }

    public Integer getTipoTran() {
        return tipoTran;
    }

    public void setTipoTran(Integer tipoTran) {
        this.tipoTran = tipoTran;
    }

    public Integer getEstaTran() {
        return estaTran;
    }

    public void setEstaTran(Integer estaTran) {
        this.estaTran = estaTran;
    }

    @XmlTransient
    public List<Detalle> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<Detalle> detalleList) {
        this.detalleList = detalleList;
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
