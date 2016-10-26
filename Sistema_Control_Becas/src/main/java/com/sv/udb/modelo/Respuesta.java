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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eduardo
 */
@Entity
@Table(name = "respuesta", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Respuesta.findAll", query = "SELECT r FROM Respuesta r"),
    @NamedQuery(name = "Respuesta.findByCodiResp", query = "SELECT r FROM Respuesta r WHERE r.codiResp = :codiResp"),
    @NamedQuery(name = "Respuesta.findByDescOpci", query = "SELECT r FROM Respuesta r WHERE r.descOpci = :descOpci"),
    @NamedQuery(name = "Respuesta.findByEstaResp", query = "SELECT r FROM Respuesta r WHERE r.estaResp = :estaResp")})
public class Respuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_resp")
    private Integer codiResp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "desc_opci")
    private String descOpci;
    @Column(name = "esta_resp")
    private Integer estaResp;
    @JoinColumn(name = "codi_opci", referencedColumnName = "codi_opci")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Opcion codiOpci;
    @JoinColumn(name = "codi_soli_beca", referencedColumnName = "codi_soli_beca")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SolicitudBeca codiSoliBeca;

    public Respuesta() {
    }

    public Respuesta(Integer codiResp) {
        this.codiResp = codiResp;
    }

    public Respuesta(Integer codiResp, String descOpci) {
        this.codiResp = codiResp;
        this.descOpci = descOpci;
    }

    public Integer getCodiResp() {
        return codiResp;
    }

    public void setCodiResp(Integer codiResp) {
        this.codiResp = codiResp;
    }

    public String getDescOpci() {
        return descOpci;
    }

    public void setDescOpci(String descOpci) {
        this.descOpci = descOpci;
    }

    public Integer getEstaResp() {
        return estaResp;
    }

    public void setEstaResp(Integer estaResp) {
        this.estaResp = estaResp;
    }

    public Opcion getCodiOpci() {
        return codiOpci;
    }

    public void setCodiOpci(Opcion codiOpci) {
        this.codiOpci = codiOpci;
    }

    public SolicitudBeca getCodiSoliBeca() {
        return codiSoliBeca;
    }

    public void setCodiSoliBeca(SolicitudBeca codiSoliBeca) {
        this.codiSoliBeca = codiSoliBeca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiResp != null ? codiResp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuesta)) {
            return false;
        }
        Respuesta other = (Respuesta) object;
        if ((this.codiResp == null && other.codiResp != null) || (this.codiResp != null && !this.codiResp.equals(other.codiResp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Respuesta[ codiResp=" + codiResp + " ]";
    }
    
}
