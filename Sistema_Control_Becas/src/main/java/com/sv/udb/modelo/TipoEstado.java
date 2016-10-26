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
@Table(name = "tipo_estado", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEstado.findAll", query = "SELECT t FROM TipoEstado t"),
    @NamedQuery(name = "TipoEstado.findByCodiTipoEsta", query = "SELECT t FROM TipoEstado t WHERE t.codiTipoEsta = :codiTipoEsta"),
    @NamedQuery(name = "TipoEstado.findByNombTipoEsta", query = "SELECT t FROM TipoEstado t WHERE t.nombTipoEsta = :nombTipoEsta"),
    @NamedQuery(name = "TipoEstado.findByDescTipoEsta", query = "SELECT t FROM TipoEstado t WHERE t.descTipoEsta = :descTipoEsta"),
    @NamedQuery(name = "TipoEstado.findByEstaTipoEsta", query = "SELECT t FROM TipoEstado t WHERE t.estaTipoEsta = :estaTipoEsta")})
public class TipoEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_tipo_esta")
    private Integer codiTipoEsta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomb_tipo_esta")
    private String nombTipoEsta;
    @Size(max = 100)
    @Column(name = "desc_tipo_esta")
    private String descTipoEsta;
    @Column(name = "esta_tipo_esta")
    private Integer estaTipoEsta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiTipoEsta", fetch = FetchType.LAZY)
    private List<Beca> becaList;

    public TipoEstado() {
    }

    public TipoEstado(Integer codiTipoEsta) {
        this.codiTipoEsta = codiTipoEsta;
    }

    public TipoEstado(Integer codiTipoEsta, String nombTipoEsta) {
        this.codiTipoEsta = codiTipoEsta;
        this.nombTipoEsta = nombTipoEsta;
    }

    public Integer getCodiTipoEsta() {
        return codiTipoEsta;
    }

    public void setCodiTipoEsta(Integer codiTipoEsta) {
        this.codiTipoEsta = codiTipoEsta;
    }

    public String getNombTipoEsta() {
        return nombTipoEsta;
    }

    public void setNombTipoEsta(String nombTipoEsta) {
        this.nombTipoEsta = nombTipoEsta;
    }

    public String getDescTipoEsta() {
        return descTipoEsta;
    }

    public void setDescTipoEsta(String descTipoEsta) {
        this.descTipoEsta = descTipoEsta;
    }

    public Integer getEstaTipoEsta() {
        return estaTipoEsta;
    }

    public void setEstaTipoEsta(Integer estaTipoEsta) {
        this.estaTipoEsta = estaTipoEsta;
    }

    @XmlTransient
    public List<Beca> getBecaList() {
        return becaList;
    }

    public void setBecaList(List<Beca> becaList) {
        this.becaList = becaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipoEsta != null ? codiTipoEsta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEstado)) {
            return false;
        }
        TipoEstado other = (TipoEstado) object;
        if ((this.codiTipoEsta == null && other.codiTipoEsta != null) || (this.codiTipoEsta != null && !this.codiTipoEsta.equals(other.codiTipoEsta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.TipoEstado[ codiTipoEsta=" + codiTipoEsta + " ]";
    }
    
}
