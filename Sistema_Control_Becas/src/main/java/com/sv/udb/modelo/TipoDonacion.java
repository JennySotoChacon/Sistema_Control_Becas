/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "tipo_donacion", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDonacion.findAll", query = "SELECT t FROM TipoDonacion t"),
    @NamedQuery(name = "TipoDonacion.findByCodiTipoDona", query = "SELECT t FROM TipoDonacion t WHERE t.codiTipoDona = :codiTipoDona"),
    @NamedQuery(name = "TipoDonacion.findByNombTipoDona", query = "SELECT t FROM TipoDonacion t WHERE t.nombTipoDona = :nombTipoDona"),
    @NamedQuery(name = "TipoDonacion.findByDescTipoDona", query = "SELECT t FROM TipoDonacion t WHERE t.descTipoDona = :descTipoDona"),
    @NamedQuery(name = "TipoDonacion.findByRecaTipoDona", query = "SELECT t FROM TipoDonacion t WHERE t.recaTipoDona = :recaTipoDona"),
    @NamedQuery(name = "TipoDonacion.findByEstaDona", query = "SELECT t FROM TipoDonacion t WHERE t.estaDona = :estaDona")})
public class TipoDonacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_tipo_dona")
    private Integer codiTipoDona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomb_tipo_dona")
    private String nombTipoDona;
    @Size(max = 500)
    @Column(name = "desc_tipo_dona")
    private String descTipoDona;
    @Column(name = "reca_tipo_dona")
    private Character recaTipoDona;
    @Column(name = "esta_dona")
    private Integer estaDona;
    @OneToMany(mappedBy = "codiTipoDona", fetch = FetchType.LAZY)
    private List<Donacion> donacionList;

    public TipoDonacion() {
    }

    public TipoDonacion(Integer codiTipoDona) {
        this.codiTipoDona = codiTipoDona;
    }

    public TipoDonacion(Integer codiTipoDona, String nombTipoDona) {
        this.codiTipoDona = codiTipoDona;
        this.nombTipoDona = nombTipoDona;
    }

    public Integer getCodiTipoDona() {
        return codiTipoDona;
    }

    public void setCodiTipoDona(Integer codiTipoDona) {
        this.codiTipoDona = codiTipoDona;
    }

    public String getNombTipoDona() {
        return nombTipoDona;
    }

    public void setNombTipoDona(String nombTipoDona) {
        this.nombTipoDona = nombTipoDona;
    }

    public String getDescTipoDona() {
        return descTipoDona;
    }

    public void setDescTipoDona(String descTipoDona) {
        this.descTipoDona = descTipoDona;
    }

    public Character getRecaTipoDona() {
        return recaTipoDona;
    }

    public void setRecaTipoDona(Character recaTipoDona) {
        this.recaTipoDona = recaTipoDona;
    }

    public Integer getEstaDona() {
        return estaDona;
    }

    public void setEstaDona(Integer estaDona) {
        this.estaDona = estaDona;
    }

    @XmlTransient
    public List<Donacion> getDonacionList() {
        return donacionList;
    }

    public void setDonacionList(List<Donacion> donacionList) {
        this.donacionList = donacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipoDona != null ? codiTipoDona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDonacion)) {
            return false;
        }
        TipoDonacion other = (TipoDonacion) object;
        if ((this.codiTipoDona == null && other.codiTipoDona != null) || (this.codiTipoDona != null && !this.codiTipoDona.equals(other.codiTipoDona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.TipoDonacion[ codiTipoDona=" + codiTipoDona + " ]";
    }
    
}
