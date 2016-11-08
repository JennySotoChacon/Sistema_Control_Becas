/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author Ariel
 */
@Entity
@Table(name = "seguimiento", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seguimiento.findAll", query = "SELECT s FROM Seguimiento s"),
    @NamedQuery(name = "Seguimiento.findByCodiSegu", query = "SELECT s FROM Seguimiento s WHERE s.codiSegu = :codiSegu"),
    @NamedQuery(name = "Seguimiento.findByFechSegu", query = "SELECT s FROM Seguimiento s WHERE s.fechSegu = :fechSegu"),
    @NamedQuery(name = "Seguimiento.findByNombSegu", query = "SELECT s FROM Seguimiento s WHERE s.nombSegu = :nombSegu"),
    @NamedQuery(name = "Seguimiento.findByDescSegu", query = "SELECT s FROM Seguimiento s WHERE s.descSegu = :descSegu"),
    @NamedQuery(name = "Seguimiento.findByEstaSegu", query = "SELECT s FROM Seguimiento s WHERE s.estaSegu = :estaSegu")})
public class Seguimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_segu")
    private Integer codiSegu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_segu")
    @Temporal(TemporalType.DATE)
    private Date fechSegu;
    @Column(name = "fech_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechInicio;
    @Column(name = "fech_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomb_segu")
    private String nombSegu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "desc_segu")
    private String descSegu;
    @Column(name = "esta_segu")
    private Integer estaSegu;
    @JoinColumn(name = "codi_empr", referencedColumnName = "codi_empr")
    @ManyToOne
    private Empresa codiEmpr;
    @JoinColumn(name = "codi_soli_beca", referencedColumnName = "codi_soli_beca")
    @ManyToOne
    private SolicitudBeca codiSoliBeca;
    @JoinColumn(name = "codi_tipo_segui", referencedColumnName = "codi_tipo_segui")
    @ManyToOne
    private TipoSeguimiento codiTipoSegui;
    @OneToMany(mappedBy = "padrSegu")
    private Collection<Seguimiento> seguimientoCollection;
    @JoinColumn(name = "padr_segu", referencedColumnName = "codi_segu")
    @ManyToOne
    private Seguimiento padrSegu;

    public Seguimiento() {
    }

    public Seguimiento(Integer codiSegu) {
        this.codiSegu = codiSegu;
    }

    public Seguimiento(Integer codiSegu, Date fechSegu, String nombSegu, String descSegu) {
        this.codiSegu = codiSegu;
        this.fechSegu = fechSegu;
        this.nombSegu = nombSegu;
        this.descSegu = descSegu;
    }

    public Integer getCodiSegu() {
        return codiSegu;
    }

    public void setCodiSegu(Integer codiSegu) {
        this.codiSegu = codiSegu;
    }

    public Date getFechSegu() {
        return fechSegu;
    }

    public void setFechSegu(Date fechSegu) {
        this.fechSegu = fechSegu;
    }

    public Date getFechInicio() {
        return fechInicio;
    }

    public void setFechInicio(Date fechInic) {
        this.fechInicio = fechInic;
    }

    public Date getFechFin() {
        return fechFin;
    }

    public void setFechFin(Date fechFin) {
        this.fechFin = fechFin;
    }

    

    public String getNombSegu() {
        return nombSegu;
    }

    public void setNombSegu(String nombSegu) {
        this.nombSegu = nombSegu;
    }

    public String getDescSegu() {
        return descSegu;
    }

    public void setDescSegu(String descSegu) {
        this.descSegu = descSegu;
    }

    public Integer getEstaSegu() {
        return estaSegu;
    }

    public void setEstaSegu(Integer estaSegu) {
        this.estaSegu = estaSegu;
    }

    public Empresa getCodiEmpr() {
        return codiEmpr;
    }

    public void setCodiEmpr(Empresa codiEmpr) {
        this.codiEmpr = codiEmpr;
    }

    public SolicitudBeca getCodiSoliBeca() {
        return codiSoliBeca;
    }

    public void setCodiSoliBeca(SolicitudBeca codiSoliBeca) {
        this.codiSoliBeca = codiSoliBeca;
    }

    public TipoSeguimiento getCodiTipoSegui() {
        return codiTipoSegui;
    }

    public void setCodiTipoSegui(TipoSeguimiento codiTipoSegui) {
        this.codiTipoSegui = codiTipoSegui;
    }

    @XmlTransient
    public Collection<Seguimiento> getSeguimientoCollection() {
        return seguimientoCollection;
    }

    public void setSeguimientoCollection(Collection<Seguimiento> seguimientoCollection) {
        this.seguimientoCollection = seguimientoCollection;
    }

    public Seguimiento getPadrSegu() {
        return padrSegu;
    }

    public void setPadrSegu(Seguimiento padrSegu) {
        this.padrSegu = padrSegu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiSegu != null ? codiSegu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seguimiento)) {
            return false;
        }
        Seguimiento other = (Seguimiento) object;
        if ((this.codiSegu == null && other.codiSegu != null) || (this.codiSegu != null && !this.codiSegu.equals(other.codiSegu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Seguimiento[ codiSegu=" + codiSegu + " ]";
    }
    
}
