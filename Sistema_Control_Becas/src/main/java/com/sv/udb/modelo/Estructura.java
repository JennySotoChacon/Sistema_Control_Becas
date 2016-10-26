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
@Table(name = "estructura", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estructura.findAll", query = "SELECT e FROM Estructura e"),
    @NamedQuery(name = "Estructura.findByCodiEstr", query = "SELECT e FROM Estructura e WHERE e.codiEstr = :codiEstr"),
    @NamedQuery(name = "Estructura.findByTipoEstr", query = "SELECT e FROM Estructura e WHERE e.tipoEstr = :tipoEstr"),
    @NamedQuery(name = "Estructura.findByEstaEstr", query = "SELECT e FROM Estructura e WHERE e.estaEstr = :estaEstr")})
public class Estructura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_estr")
    private Integer codiEstr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "tipo_estr")
    private String tipoEstr;
    @Column(name = "esta_estr")
    private Integer estaEstr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiEstr", fetch = FetchType.LAZY)
    private List<Opcion> opcionList;

    public Estructura() {
    }

    public Estructura(Integer codiEstr) {
        this.codiEstr = codiEstr;
    }

    public Estructura(Integer codiEstr, String tipoEstr) {
        this.codiEstr = codiEstr;
        this.tipoEstr = tipoEstr;
    }

    public Integer getCodiEstr() {
        return codiEstr;
    }

    public void setCodiEstr(Integer codiEstr) {
        this.codiEstr = codiEstr;
    }

    public String getTipoEstr() {
        return tipoEstr;
    }

    public void setTipoEstr(String tipoEstr) {
        this.tipoEstr = tipoEstr;
    }

    public Integer getEstaEstr() {
        return estaEstr;
    }

    public void setEstaEstr(Integer estaEstr) {
        this.estaEstr = estaEstr;
    }

    @XmlTransient
    public List<Opcion> getOpcionList() {
        return opcionList;
    }

    public void setOpcionList(List<Opcion> opcionList) {
        this.opcionList = opcionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiEstr != null ? codiEstr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estructura)) {
            return false;
        }
        Estructura other = (Estructura) object;
        if ((this.codiEstr == null && other.codiEstr != null) || (this.codiEstr != null && !this.codiEstr.equals(other.codiEstr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Estructura[ codiEstr=" + codiEstr + " ]";
    }
    
}
