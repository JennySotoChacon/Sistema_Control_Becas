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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eduardo
 */
@Entity
@Table(name = "beca", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Beca.findAll", query = "SELECT b FROM Beca b"),
    @NamedQuery(name = "Beca.findByCodiBeca", query = "SELECT b FROM Beca b WHERE b.codiBeca = :codiBeca"),
    @NamedQuery(name = "Beca.findByMensAlum", query = "SELECT b FROM Beca b WHERE b.mensAlum = :mensAlum"),
    @NamedQuery(name = "Beca.findByFechInic", query = "SELECT b FROM Beca b WHERE b.fechInic = :fechInic"),
    @NamedQuery(name = "Beca.findByFechBaja", query = "SELECT b FROM Beca b WHERE b.fechBaja = :fechBaja")})
public class Beca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_beca")
    private Integer codiBeca;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "mens_alum")
    private BigDecimal mensAlum;
    @Column(name = "fech_inic")
    @Temporal(TemporalType.DATE)
    private Date fechInic;
    @Column(name = "fech_baja")
    @Temporal(TemporalType.DATE)
    private Date fechBaja;
    @JoinColumn(name = "codi_soli_beca", referencedColumnName = "codi_soli_beca")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SolicitudBeca codiSoliBeca;
    @JoinColumn(name = "codi_tipo_esta", referencedColumnName = "codi_tipo_esta")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoEstado codiTipoEsta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiBeca", fetch = FetchType.LAZY)
    private List<DetalleBeca> detalleBecaList;

    public Beca() {
    }

    public Beca(Integer codiBeca) {
        this.codiBeca = codiBeca;
    }

    public Beca(Integer codiBeca, BigDecimal mensAlum) {
        this.codiBeca = codiBeca;
        this.mensAlum = mensAlum;
    }

    public Integer getCodiBeca() {
        return codiBeca;
    }

    public void setCodiBeca(Integer codiBeca) {
        this.codiBeca = codiBeca;
    }

    public BigDecimal getMensAlum() {
        return mensAlum;
    }

    public void setMensAlum(BigDecimal mensAlum) {
        this.mensAlum = mensAlum;
    }

    public Date getFechInic() {
        return fechInic;
    }

    public void setFechInic(Date fechInic) {
        this.fechInic = fechInic;
    }

    public Date getFechBaja() {
        return fechBaja;
    }

    public void setFechBaja(Date fechBaja) {
        this.fechBaja = fechBaja;
    }

    public SolicitudBeca getCodiSoliBeca() {
        return codiSoliBeca;
    }

    public void setCodiSoliBeca(SolicitudBeca codiSoliBeca) {
        this.codiSoliBeca = codiSoliBeca;
    }

    public TipoEstado getCodiTipoEsta() {
        return codiTipoEsta;
    }

    public void setCodiTipoEsta(TipoEstado codiTipoEsta) {
        this.codiTipoEsta = codiTipoEsta;
    }

    @XmlTransient
    public List<DetalleBeca> getDetalleBecaList() {
        return detalleBecaList;
    }

    public void setDetalleBecaList(List<DetalleBeca> detalleBecaList) {
        this.detalleBecaList = detalleBecaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiBeca != null ? codiBeca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beca)) {
            return false;
        }
        Beca other = (Beca) object;
        if ((this.codiBeca == null && other.codiBeca != null) || (this.codiBeca != null && !this.codiBeca.equals(other.codiBeca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Beca[ codiBeca=" + codiBeca + " ]";
    }
    
}
