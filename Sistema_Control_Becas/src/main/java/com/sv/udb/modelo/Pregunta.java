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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eduardo
 */
@Entity
@Table(name = "pregunta", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
    @NamedQuery(name = "Pregunta.findByCodiPreg", query = "SELECT p FROM Pregunta p WHERE p.codiPreg = :codiPreg"),
    @NamedQuery(name = "Pregunta.findByCodiPregSupe", query = "SELECT p FROM Pregunta p WHERE p.codiPregSupe = :codiPregSupe"),
    @NamedQuery(name = "Pregunta.findByDescPreg", query = "SELECT p FROM Pregunta p WHERE p.descPreg = :descPreg"),
    @NamedQuery(name = "Pregunta.findByEstaPreg", query = "SELECT p FROM Pregunta p WHERE p.estaPreg = :estaPreg")})
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_preg")
    private Integer codiPreg;
    @Column(name = "codi_preg_supe")
    private Integer codiPregSupe;
    @Size(max = 255)
    @Column(name = "desc_preg")
    private String descPreg;
    @Column(name = "esta_preg")
    private Integer estaPreg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiPreg", fetch = FetchType.LAZY)
    private List<Opcion> opcionList;
    @JoinColumn(name = "codi_secc", referencedColumnName = "codi_secc")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Seccion codiSecc;

    public Pregunta() {
    }

    public Pregunta(Integer codiPreg) {
        this.codiPreg = codiPreg;
    }

    public Integer getCodiPreg() {
        return codiPreg;
    }

    public void setCodiPreg(Integer codiPreg) {
        this.codiPreg = codiPreg;
    }

    public Integer getCodiPregSupe() {
        return codiPregSupe;
    }

    public void setCodiPregSupe(Integer codiPregSupe) {
        this.codiPregSupe = codiPregSupe;
    }

    public String getDescPreg() {
        return descPreg;
    }

    public void setDescPreg(String descPreg) {
        this.descPreg = descPreg;
    }

    public Integer getEstaPreg() {
        return estaPreg;
    }

    public void setEstaPreg(Integer estaPreg) {
        this.estaPreg = estaPreg;
    }

    @XmlTransient
    public List<Opcion> getOpcionList() {
        return opcionList;
    }

    public void setOpcionList(List<Opcion> opcionList) {
        this.opcionList = opcionList;
    }

    public Seccion getCodiSecc() {
        return codiSecc;
    }

    public void setCodiSecc(Seccion codiSecc) {
        this.codiSecc = codiSecc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPreg != null ? codiPreg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.codiPreg == null && other.codiPreg != null) || (this.codiPreg != null && !this.codiPreg.equals(other.codiPreg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Pregunta[ codiPreg=" + codiPreg + " ]";
    }
    
}
