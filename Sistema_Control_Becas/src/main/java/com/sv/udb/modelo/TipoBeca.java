/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "tipo_beca", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoBeca.findAll", query = "SELECT t FROM TipoBeca t"),
    @NamedQuery(name = "TipoBeca.findByCodiTipoBeca", query = "SELECT t FROM TipoBeca t WHERE t.codiTipoBeca = :codiTipoBeca"),
    @NamedQuery(name = "TipoBeca.findByNombTipoBeca", query = "SELECT t FROM TipoBeca t WHERE t.nombTipoBeca = :nombTipoBeca"),
    @NamedQuery(name = "TipoBeca.findByDescTipoBeca", query = "SELECT t FROM TipoBeca t WHERE t.descTipoBeca = :descTipoBeca"),
    @NamedQuery(name = "TipoBeca.findByEstaTipoBeca", query = "SELECT t FROM TipoBeca t WHERE t.estaTipoBeca = :estaTipoBeca"),
    @NamedQuery(name = "TipoBeca.findByTipoTipoBeca", query = "SELECT t FROM TipoBeca t WHERE t.tipoTipoBeca = :tipoTipoBeca")})
public class TipoBeca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_tipo_beca")
    private Integer codiTipoBeca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomb_tipo_beca")
    private String nombTipoBeca;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "desc_tipo_beca")
    private BigDecimal descTipoBeca;
    @Column(name = "esta_tipo_beca")
    private Integer estaTipoBeca;
    @Column(name = "tipo_tipo_beca")
    private Integer tipoTipoBeca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiTipoBeca", fetch = FetchType.LAZY)
    private List<DetalleBeca> detalleBecaList;

    public TipoBeca() {
    }

    public TipoBeca(Integer codiTipoBeca) {
        this.codiTipoBeca = codiTipoBeca;
    }

    public TipoBeca(Integer codiTipoBeca, String nombTipoBeca) {
        this.codiTipoBeca = codiTipoBeca;
        this.nombTipoBeca = nombTipoBeca;
    }

    public Integer getCodiTipoBeca() {
        return codiTipoBeca;
    }

    public void setCodiTipoBeca(Integer codiTipoBeca) {
        this.codiTipoBeca = codiTipoBeca;
    }

    public String getNombTipoBeca() {
        return nombTipoBeca;
    }

    public void setNombTipoBeca(String nombTipoBeca) {
        this.nombTipoBeca = nombTipoBeca;
    }

    public BigDecimal getDescTipoBeca() {
        return descTipoBeca;
    }

    public void setDescTipoBeca(BigDecimal descTipoBeca) {
        this.descTipoBeca = descTipoBeca;
    }

    public Integer getEstaTipoBeca() {
        return estaTipoBeca;
    }

    public void setEstaTipoBeca(Integer estaTipoBeca) {
        this.estaTipoBeca = estaTipoBeca;
    }

    public Integer getTipoTipoBeca() {
        return tipoTipoBeca;
    }

    public void setTipoTipoBeca(Integer tipoTipoBeca) {
        this.tipoTipoBeca = tipoTipoBeca;
    }

    @XmlTransient
    public List<DetalleBeca> getDetalleBecaList() {
        return detalleBecaList;
    }

    public void setDetalleBecaList(List<DetalleBeca> detalleBecaList) {
        this.detalleBecaList = detalleBecaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipoBeca != null ? codiTipoBeca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoBeca)) {
            return false;
        }
        TipoBeca other = (TipoBeca) object;
        if ((this.codiTipoBeca == null && other.codiTipoBeca != null) || (this.codiTipoBeca != null && !this.codiTipoBeca.equals(other.codiTipoBeca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.TipoBeca[ codiTipoBeca=" + codiTipoBeca + " ]";
    }
    
}
