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
@Table(name = "documento", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d"),
    @NamedQuery(name = "Documento.findByCodiDocu", query = "SELECT d FROM Documento d WHERE d.codiDocu = :codiDocu"),
    @NamedQuery(name = "Documento.findByRutaDocu", query = "SELECT d FROM Documento d WHERE d.rutaDocu = :rutaDocu"),
    @NamedQuery(name = "Documento.findByFechDocu", query = "SELECT d FROM Documento d WHERE d.fechDocu = :fechDocu"),
    @NamedQuery(name = "Documento.findByEstaDocu", query = "SELECT d FROM Documento d WHERE d.estaDocu = :estaDocu")})
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_docu")
    private Integer codiDocu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ruta_docu")
    private String rutaDocu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_docu")
    @Temporal(TemporalType.DATE)
    private Date fechDocu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_docu")
    private int estaDocu;
    @JoinColumn(name = "codi_tipo_docu", referencedColumnName = "codi_tipo_docu")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TipoDocumento codiTipoDocu;
    @JoinColumn(name = "codi_soli_beca", referencedColumnName = "codi_soli_beca")
    @ManyToOne(fetch = FetchType.EAGER)
    private SolicitudBeca codiSoliBeca;
    @JoinColumn(name = "codi_empr", referencedColumnName = "codi_empr")
    @ManyToOne(fetch = FetchType.EAGER)
    private Empresa codiEmpr;

    public Documento() {
    }

    public Documento(Integer codiDocu) {
        this.codiDocu = codiDocu;
    }

    public Documento(Integer codiDocu, String rutaDocu, Date fechDocu, int estaDocu) {
        this.codiDocu = codiDocu;
        this.rutaDocu = rutaDocu;
        this.fechDocu = fechDocu;
        this.estaDocu = estaDocu;
    }

    public Integer getCodiDocu() {
        return codiDocu;
    }

    public void setCodiDocu(Integer codiDocu) {
        this.codiDocu = codiDocu;
    }

    public String getRutaDocu() {
        return rutaDocu;
    }

    public void setRutaDocu(String rutaDocu) {
        this.rutaDocu = rutaDocu;
    }

    public Date getFechDocu() {
        return fechDocu;
    }

    public void setFechDocu(Date fechDocu) {
        this.fechDocu = fechDocu;
    }

    public int getEstaDocu() {
        return estaDocu;
    }

    public void setEstaDocu(int estaDocu) {
        this.estaDocu = estaDocu;
    }

    public TipoDocumento getCodiTipoDocu() {
        return codiTipoDocu;
    }

    public void setCodiTipoDocu(TipoDocumento codiTipoDocu) {
        this.codiTipoDocu = codiTipoDocu;
    }

    public SolicitudBeca getCodiSoliBeca() {
        return codiSoliBeca;
    }

    public void setCodiSoliBeca(SolicitudBeca codiSoliBeca) {
        this.codiSoliBeca = codiSoliBeca;
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
        hash += (codiDocu != null ? codiDocu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.codiDocu == null && other.codiDocu != null) || (this.codiDocu != null && !this.codiDocu.equals(other.codiDocu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Documento[ codiDocu=" + codiDocu + " ]";
    }
    
}
