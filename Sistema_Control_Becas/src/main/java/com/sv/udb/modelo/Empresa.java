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
@Table(name = "empresa", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByCodiEmpr", query = "SELECT e FROM Empresa e WHERE e.codiEmpr = :codiEmpr"),
    @NamedQuery(name = "Empresa.findByNombEmpr", query = "SELECT e FROM Empresa e WHERE e.nombEmpr = :nombEmpr"),
    @NamedQuery(name = "Empresa.findByDireEmpr", query = "SELECT e FROM Empresa e WHERE e.direEmpr = :direEmpr"),
    @NamedQuery(name = "Empresa.findByEmaiEmpr", query = "SELECT e FROM Empresa e WHERE e.emaiEmpr = :emaiEmpr"),
    @NamedQuery(name = "Empresa.findByEncaEmpr", query = "SELECT e FROM Empresa e WHERE e.encaEmpr = :encaEmpr"),
    @NamedQuery(name = "Empresa.findByFechEmpr", query = "SELECT e FROM Empresa e WHERE e.fechEmpr = :fechEmpr"),
    @NamedQuery(name = "Empresa.findByEstaEmpr", query = "SELECT e FROM Empresa e WHERE e.estaEmpr = :estaEmpr")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_empr")
    private Integer codiEmpr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nomb_empr")
    private String nombEmpr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "dire_empr")
    private String direEmpr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "emai_empr")
    private String emaiEmpr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "enca_empr")
    private String encaEmpr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_empr")
    @Temporal(TemporalType.DATE)
    private Date fechEmpr;
    @Column(name = "esta_empr")
    private Integer estaEmpr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiEmpr", fetch = FetchType.LAZY)
    private List<Donacion> donacionList;
    @OneToMany(mappedBy = "codiEmpr", fetch = FetchType.LAZY)
    private List<Seguimiento> seguimientoList;
    @OneToMany(mappedBy = "codiEmpr", fetch = FetchType.LAZY)
    private List<Documento> documentoList;
    @OneToMany(mappedBy = "codiEmpr", fetch = FetchType.LAZY)
    private List<SolicitudBeca> solicitudBecaList;

    public Empresa() {
    }

    public Empresa(Integer codiEmpr) {
        this.codiEmpr = codiEmpr;
    }

    public Empresa(Integer codiEmpr, String nombEmpr, String direEmpr, String emaiEmpr, String encaEmpr, Date fechEmpr) {
        this.codiEmpr = codiEmpr;
        this.nombEmpr = nombEmpr;
        this.direEmpr = direEmpr;
        this.emaiEmpr = emaiEmpr;
        this.encaEmpr = encaEmpr;
        this.fechEmpr = fechEmpr;
    }

    public Integer getCodiEmpr() {
        return codiEmpr;
    }

    public void setCodiEmpr(Integer codiEmpr) {
        this.codiEmpr = codiEmpr;
    }

    public String getNombEmpr() {
        return nombEmpr;
    }

    public void setNombEmpr(String nombEmpr) {
        this.nombEmpr = nombEmpr;
    }

    public String getDireEmpr() {
        return direEmpr;
    }

    public void setDireEmpr(String direEmpr) {
        this.direEmpr = direEmpr;
    }

    public String getEmaiEmpr() {
        return emaiEmpr;
    }

    public void setEmaiEmpr(String emaiEmpr) {
        this.emaiEmpr = emaiEmpr;
    }

    public String getEncaEmpr() {
        return encaEmpr;
    }

    public void setEncaEmpr(String encaEmpr) {
        this.encaEmpr = encaEmpr;
    }

    public Date getFechEmpr() {
        return fechEmpr;
    }

    public void setFechEmpr(Date fechEmpr) {
        this.fechEmpr = fechEmpr;
    }

    public Integer getEstaEmpr() {
        return estaEmpr;
    }

    public void setEstaEmpr(Integer estaEmpr) {
        this.estaEmpr = estaEmpr;
    }

    @XmlTransient
    public List<Donacion> getDonacionList() {
        return donacionList;
    }

    public void setDonacionList(List<Donacion> donacionList) {
        this.donacionList = donacionList;
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

    @XmlTransient
    public List<SolicitudBeca> getSolicitudBecaList() {
        return solicitudBecaList;
    }

    public void setSolicitudBecaList(List<SolicitudBeca> solicitudBecaList) {
        this.solicitudBecaList = solicitudBecaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiEmpr != null ? codiEmpr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.codiEmpr == null && other.codiEmpr != null) || (this.codiEmpr != null && !this.codiEmpr.equals(other.codiEmpr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Empresa[ codiEmpr=" + codiEmpr + " ]";
    }
    
}
