/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tipo_retiro", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRetiro.findAll", query = "SELECT t FROM TipoRetiro t"),
    @NamedQuery(name = "TipoRetiro.findByCodiReti", query = "SELECT t FROM TipoRetiro t WHERE t.codiReti = :codiReti"),
    @NamedQuery(name = "TipoRetiro.findByNombReti", query = "SELECT t FROM TipoRetiro t WHERE t.nombReti = :nombReti"),
    @NamedQuery(name = "TipoRetiro.findByDescReti", query = "SELECT t FROM TipoRetiro t WHERE t.descReti = :descReti"),
    @NamedQuery(name = "TipoRetiro.findByEstaReti", query = "SELECT t FROM TipoRetiro t WHERE t.estaReti = :estaReti")})
public class TipoRetiro implements Serializable {

    @OneToMany(mappedBy = "codiReti")
    private Collection<Beca> becaCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_reti")
    private Integer codiReti;
    @Size(max = 50)
    @Column(name = "nomb_reti")
    private String nombReti;
    @Size(max = 500)
    @Column(name = "desc_reti")
    private String descReti;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_reti")
    private int estaReti;

    public TipoRetiro() {
    }

    public TipoRetiro(Integer codiReti) {
        this.codiReti = codiReti;
    }

    public TipoRetiro(Integer codiReti, int estaReti) {
        this.codiReti = codiReti;
        this.estaReti = estaReti;
    }

    public Integer getCodiReti() {
        return codiReti;
    }

    public void setCodiReti(Integer codiReti) {
        this.codiReti = codiReti;
    }

    public String getNombReti() {
        return nombReti;
    }

    public void setNombReti(String nombReti) {
        this.nombReti = nombReti;
    }

    public String getDescReti() {
        return descReti;
    }

    public void setDescReti(String descReti) {
        this.descReti = descReti;
    }

    public int getEstaReti() {
        return estaReti;
    }

    public void setEstaReti(int estaReti) {
        this.estaReti = estaReti;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiReti != null ? codiReti.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRetiro)) {
            return false;
        }
        TipoRetiro other = (TipoRetiro) object;
        if ((this.codiReti == null && other.codiReti != null) || (this.codiReti != null && !this.codiReti.equals(other.codiReti))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.TipoRetiro[ codiReti=" + codiReti + " ]";
    }

    @XmlTransient
    public Collection<Beca> getBecaCollection() {
        return becaCollection;
    }

    public void setBecaCollection(Collection<Beca> becaCollection) {
        this.becaCollection = becaCollection;
    }
    
}
