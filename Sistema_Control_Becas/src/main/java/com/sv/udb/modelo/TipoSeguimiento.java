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
@Table(name = "tipo_seguimiento", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoSeguimiento.findAll", query = "SELECT t FROM TipoSeguimiento t"),
    @NamedQuery(name = "TipoSeguimiento.findByCodiTipoSegui", query = "SELECT t FROM TipoSeguimiento t WHERE t.codiTipoSegui = :codiTipoSegui"),
    @NamedQuery(name = "TipoSeguimiento.findByNombTipoSegui", query = "SELECT t FROM TipoSeguimiento t WHERE t.nombTipoSegui = :nombTipoSegui"),
    @NamedQuery(name = "TipoSeguimiento.findByDescTipoSegui", query = "SELECT t FROM TipoSeguimiento t WHERE t.descTipoSegui = :descTipoSegui"),
    @NamedQuery(name = "TipoSeguimiento.findByEstaTipoSegui", query = "SELECT t FROM TipoSeguimiento t WHERE t.estaTipoSegui = :estaTipoSegui")})
public class TipoSeguimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_tipo_segui")
    private Integer codiTipoSegui;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomb_tipo_segui")
    private String nombTipoSegui;
    @Size(max = 100)
    @Column(name = "desc_tipo_segui")
    private String descTipoSegui;
    @Column(name = "esta_tipo_segui")
    private Integer estaTipoSegui;
    @OneToMany(mappedBy = "codiTipoSegui", fetch = FetchType.LAZY)
    private List<Seguimiento> seguimientoList;

    public TipoSeguimiento() {
    }

    public TipoSeguimiento(Integer codiTipoSegui) {
        this.codiTipoSegui = codiTipoSegui;
    }

    public TipoSeguimiento(Integer codiTipoSegui, String nombTipoSegui) {
        this.codiTipoSegui = codiTipoSegui;
        this.nombTipoSegui = nombTipoSegui;
    }

    public Integer getCodiTipoSegui() {
        return codiTipoSegui;
    }

    public void setCodiTipoSegui(Integer codiTipoSegui) {
        this.codiTipoSegui = codiTipoSegui;
    }

    public String getNombTipoSegui() {
        return nombTipoSegui;
    }

    public void setNombTipoSegui(String nombTipoSegui) {
        this.nombTipoSegui = nombTipoSegui;
    }

    public String getDescTipoSegui() {
        return descTipoSegui;
    }

    public void setDescTipoSegui(String descTipoSegui) {
        this.descTipoSegui = descTipoSegui;
    }

    public Integer getEstaTipoSegui() {
        return estaTipoSegui;
    }

    public void setEstaTipoSegui(Integer estaTipoSegui) {
        this.estaTipoSegui = estaTipoSegui;
    }

    @XmlTransient
    public List<Seguimiento> getSeguimientoList() {
        return seguimientoList;
    }

    public void setSeguimientoList(List<Seguimiento> seguimientoList) {
        this.seguimientoList = seguimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipoSegui != null ? codiTipoSegui.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSeguimiento)) {
            return false;
        }
        TipoSeguimiento other = (TipoSeguimiento) object;
        if ((this.codiTipoSegui == null && other.codiTipoSegui != null) || (this.codiTipoSegui != null && !this.codiTipoSegui.equals(other.codiTipoSegui))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.TipoSeguimiento[ codiTipoSegui=" + codiTipoSegui + " ]";
    }
    
}
