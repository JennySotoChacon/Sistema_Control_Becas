/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Owner
 */
@Entity
@Table(name = "tipo_documento", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t"),
    @NamedQuery(name = "TipoDocumento.findByCodiTipoDocu", query = "SELECT t FROM TipoDocumento t WHERE t.codiTipoDocu = :codiTipoDocu"),
    @NamedQuery(name = "TipoDocumento.findByNombTipoDocu", query = "SELECT t FROM TipoDocumento t WHERE t.nombTipoDocu = :nombTipoDocu"),
    @NamedQuery(name = "TipoDocumento.findByDescTipoDocu", query = "SELECT t FROM TipoDocumento t WHERE t.descTipoDocu = :descTipoDocu"),
    @NamedQuery(name = "TipoDocumento.findByEstaTipoDocu", query = "SELECT t FROM TipoDocumento t WHERE t.estaTipoDocu = :estaTipoDocu")})
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_tipo_docu")
    private Integer codiTipoDocu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomb_tipo_docu")
    private String nombTipoDocu;
    @Size(max = 100)
    @Column(name = "desc_tipo_docu")
    private String descTipoDocu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_tipo_docu")
    private int estaTipoDocu;

    public TipoDocumento() {
    }

    public TipoDocumento(Integer codiTipoDocu) {
        this.codiTipoDocu = codiTipoDocu;
    }

    public TipoDocumento(Integer codiTipoDocu, String nombTipoDocu, int estaTipoDocu) {
        this.codiTipoDocu = codiTipoDocu;
        this.nombTipoDocu = nombTipoDocu;
        this.estaTipoDocu = estaTipoDocu;
    }

    public Integer getCodiTipoDocu() {
        return codiTipoDocu;
    }

    public void setCodiTipoDocu(Integer codiTipoDocu) {
        this.codiTipoDocu = codiTipoDocu;
    }

    public String getNombTipoDocu() {
        return nombTipoDocu;
    }

    public void setNombTipoDocu(String nombTipoDocu) {
        this.nombTipoDocu = nombTipoDocu;
    }

    public String getDescTipoDocu() {
        return descTipoDocu;
    }

    public void setDescTipoDocu(String descTipoDocu) {
        this.descTipoDocu = descTipoDocu;
    }

    public int getEstaTipoDocu() {
        return estaTipoDocu;
    }

    public void setEstaTipoDocu(int estaTipoDocu) {
        this.estaTipoDocu = estaTipoDocu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipoDocu != null ? codiTipoDocu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.codiTipoDocu == null && other.codiTipoDocu != null) || (this.codiTipoDocu != null && !this.codiTipoDocu.equals(other.codiTipoDocu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.TipoDocumento[ codiTipoDocu=" + codiTipoDocu + " ]";
    }
    
}
