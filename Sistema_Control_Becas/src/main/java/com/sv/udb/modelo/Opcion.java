/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eduardo
 */
@Entity
@Table(name = "opcion", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opcion.findAll", query = "SELECT o FROM Opcion o"),
    @NamedQuery(name = "Opcion.findByCodiOpci", query = "SELECT o FROM Opcion o WHERE o.codiOpci = :codiOpci"),
    @NamedQuery(name = "Opcion.findByTituOpci", query = "SELECT o FROM Opcion o WHERE o.tituOpci = :tituOpci"),
    @NamedQuery(name = "Opcion.findByDescOpci", query = "SELECT o FROM Opcion o WHERE o.descOpci = :descOpci"),
    @NamedQuery(name = "Opcion.findByEstaOpci", query = "SELECT o FROM Opcion o WHERE o.estaOpci = :estaOpci")})
public class Opcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_opci")
    private Integer codiOpci;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "titu_opci")
    private String tituOpci;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "desc_opci")
    private String descOpci;
    @Column(name = "esta_opci")
    private Integer estaOpci;
    @JoinColumn(name = "codi_preg", referencedColumnName = "codi_preg")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pregunta codiPreg;
    @JoinColumn(name = "codi_estr", referencedColumnName = "codi_estr")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estructura codiEstr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiOpci", fetch = FetchType.LAZY)
    private List<Respuesta> respuestaList;

    public Opcion() {
    }

    public Opcion(Integer codiOpci) {
        this.codiOpci = codiOpci;
    }

    public Opcion(Integer codiOpci, String tituOpci, String descOpci) {
        this.codiOpci = codiOpci;
        this.tituOpci = tituOpci;
        this.descOpci = descOpci;
    }

    public Integer getCodiOpci() {
        return codiOpci;
    }

    public void setCodiOpci(Integer codiOpci) {
        this.codiOpci = codiOpci;
    }

    public String getTituOpci() {
        return tituOpci;
    }

    public void setTituOpci(String tituOpci) {
        this.tituOpci = tituOpci;
    }

    public String getDescOpci() {
        return descOpci;
    }

    public void setDescOpci(String descOpci) {
        this.descOpci = descOpci;
    }

    public Integer getEstaOpci() {
        return estaOpci;
    }

    public void setEstaOpci(Integer estaOpci) {
        this.estaOpci = estaOpci;
    }

    public Pregunta getCodiPreg() {
        return codiPreg;
    }

    public void setCodiPreg(Pregunta codiPreg) {
        this.codiPreg = codiPreg;
    }

    public Estructura getCodiEstr() {
        return codiEstr;
    }

    public void setCodiEstr(Estructura codiEstr) {
        this.codiEstr = codiEstr;
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
        hash += (codiOpci != null ? codiOpci.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opcion)) {
            return false;
        }
        Opcion other = (Opcion) object;
        if ((this.codiOpci == null && other.codiOpci != null) || (this.codiOpci != null && !this.codiOpci.equals(other.codiOpci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Opcion[ codiOpci=" + codiOpci + " ]";
    }
    
}
