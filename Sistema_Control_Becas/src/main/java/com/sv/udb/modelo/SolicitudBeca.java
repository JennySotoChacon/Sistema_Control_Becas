/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eduardo
 */
@Entity
@Table(name = "solicitud_beca", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudBeca.findAll", query = "SELECT s FROM SolicitudBeca s"),
    @NamedQuery(name = "SolicitudBeca.findByCodiSoliBeca", query = "SELECT s FROM SolicitudBeca s WHERE s.codiSoliBeca = :codiSoliBeca"),
    @NamedQuery(name = "SolicitudBeca.findByCarnAlum", query = "SELECT s FROM SolicitudBeca s WHERE s.carnAlum = :carnAlum"),
    @NamedQuery(name = "SolicitudBeca.findByNombAlum", query = "SELECT s FROM SolicitudBeca s WHERE s.nombAlum = :nombAlum"),
    @NamedQuery(name = "SolicitudBeca.findByFechSoliBeca", query = "SELECT s FROM SolicitudBeca s WHERE s.fechSoliBeca = :fechSoliBeca"),
    @NamedQuery(name = "SolicitudBeca.findByEstaSoliBeca", query = "SELECT s FROM SolicitudBeca s WHERE s.estaSoliBeca = :estaSoliBeca")})
public class SolicitudBeca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_soli_beca")
    private Integer codiSoliBeca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "carn_alum")
    private String carnAlum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nomb_alum")
    private String nombAlum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_soli_beca")
    @Temporal(TemporalType.DATE)
    private Date fechSoliBeca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_soli_beca")
    private int estaSoliBeca;
    @OneToMany(mappedBy = "codiSoliBeca", fetch = FetchType.LAZY)
    private List<Seguimiento> seguimientoList;
    @OneToMany(mappedBy = "codiSoliBeca", fetch = FetchType.LAZY)
    private List<Documento> documentoList;
    @JoinColumn(name = "codi_empr", referencedColumnName = "codi_empr")
    @ManyToOne(fetch = FetchType.LAZY)
    private Empresa codiEmpr;
    @JoinColumn(name = "codi_grad", referencedColumnName = "codi_grad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grado codiGrad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiSoliBeca", fetch = FetchType.LAZY)
    private List<Beca> becaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiSoliBeca", fetch = FetchType.LAZY)
    private List<Respuesta> respuestaList;

    public SolicitudBeca() {
    }

    public SolicitudBeca(Integer codiSoliBeca) {
        this.codiSoliBeca = codiSoliBeca;
    }

    public SolicitudBeca(Integer codiSoliBeca, String carnAlum, String nombAlum, Date fechSoliBeca, int estaSoliBeca) {
        this.codiSoliBeca = codiSoliBeca;
        this.carnAlum = carnAlum;
        this.nombAlum = nombAlum;
        this.fechSoliBeca = fechSoliBeca;
        this.estaSoliBeca = estaSoliBeca;
    }

    public Integer getCodiSoliBeca() {
        return codiSoliBeca;
    }

    public void setCodiSoliBeca(Integer codiSoliBeca) {
        this.codiSoliBeca = codiSoliBeca;
    }

    public String getCarnAlum() {
        return carnAlum;
    }

    public void setCarnAlum(String carnAlum) {
        this.carnAlum = carnAlum;
    }

    public String getNombAlum() {
        return nombAlum;
    }

    public void setNombAlum(String nombAlum) {
        this.nombAlum = nombAlum;
    }

    public Date getFechSoliBeca() {
        return fechSoliBeca;
    }

    public void setFechSoliBeca(Date fechSoliBeca) {
        this.fechSoliBeca = fechSoliBeca;
    }

    public int getEstaSoliBeca() {
        return estaSoliBeca;
    }

    public void setEstaSoliBeca(int estaSoliBeca) {
        this.estaSoliBeca = estaSoliBeca;
    }

    @XmlTransient
    public List<Seguimiento> getSeguimientoList() {
        return seguimientoList;
    }

    public void setSeguimientoList(List<Seguimiento> seguimientoList) {
        this.seguimientoList = seguimientoList;
    }

    @XmlTransient
    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public Empresa getCodiEmpr() {
        return codiEmpr;
    }

    public void setCodiEmpr(Empresa codiEmpr) {
        this.codiEmpr = codiEmpr;
    }

    public Grado getCodiGrad() {
        return codiGrad;
    }

    public void setCodiGrad(Grado codiGrad) {
        this.codiGrad = codiGrad;
    }

    @XmlTransient
    public List<Beca> getBecaList() {
        return becaList;
    }

    public void setBecaList(List<Beca> becaList) {
        this.becaList = becaList;
    }

    @XmlTransient
    public List<Respuesta> getRespuestaList() {
        return respuestaList;
    }

    public void setRespuestaList(List<Respuesta> respuestaList) {
        this.respuestaList = respuestaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiSoliBeca != null ? codiSoliBeca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudBeca)) {
            return false;
        }
        SolicitudBeca other = (SolicitudBeca) object;
        if ((this.codiSoliBeca == null && other.codiSoliBeca != null) || (this.codiSoliBeca != null && !this.codiSoliBeca.equals(other.codiSoliBeca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.SolicitudBeca[ codiSoliBeca=" + codiSoliBeca + " ]";
    }
    
}
