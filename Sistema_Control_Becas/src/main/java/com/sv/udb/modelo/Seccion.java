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
@Table(name = "seccion", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s"),
    @NamedQuery(name = "Seccion.findByCodiSecc", query = "SELECT s FROM Seccion s WHERE s.codiSecc = :codiSecc"),
    @NamedQuery(name = "Seccion.findByNombSecc", query = "SELECT s FROM Seccion s WHERE s.nombSecc = :nombSecc"),
    @NamedQuery(name = "Seccion.findByDescSecc", query = "SELECT s FROM Seccion s WHERE s.descSecc = :descSecc"),
    @NamedQuery(name = "Seccion.findByIndiSecc", query = "SELECT s FROM Seccion s WHERE s.indiSecc = :indiSecc"),
    @NamedQuery(name = "Seccion.findByEstaSecc", query = "SELECT s FROM Seccion s WHERE s.estaSecc = :estaSecc")})
public class Seccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_secc")
    private Integer codiSecc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nomb_secc")
    private String nombSecc;
    @Size(max = 255)
    @Column(name = "desc_secc")
    private String descSecc;
    @Size(max = 255)
    @Column(name = "indi_secc")
    private String indiSecc;
    @Column(name = "esta_secc")
    private Integer estaSecc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiSecc", fetch = FetchType.LAZY)
    private List<Pregunta> preguntaList;

    public Seccion() {
    }

    public Seccion(Integer codiSecc) {
        this.codiSecc = codiSecc;
    }

    public Seccion(Integer codiSecc, String nombSecc) {
        this.codiSecc = codiSecc;
        this.nombSecc = nombSecc;
    }

    public Integer getCodiSecc() {
        return codiSecc;
    }

    public void setCodiSecc(Integer codiSecc) {
        this.codiSecc = codiSecc;
    }

    public String getNombSecc() {
        return nombSecc;
    }

    public void setNombSecc(String nombSecc) {
        this.nombSecc = nombSecc;
    }

    public String getDescSecc() {
        return descSecc;
    }

    public void setDescSecc(String descSecc) {
        this.descSecc = descSecc;
    }

    public String getIndiSecc() {
        return indiSecc;
    }

    public void setIndiSecc(String indiSecc) {
        this.indiSecc = indiSecc;
    }

    public Integer getEstaSecc() {
        return estaSecc;
    }

    public void setEstaSecc(Integer estaSecc) {
        this.estaSecc = estaSecc;
    }

    @XmlTransient
    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiSecc != null ? codiSecc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.codiSecc == null && other.codiSecc != null) || (this.codiSecc != null && !this.codiSecc.equals(other.codiSecc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Seccion[ codiSecc=" + codiSecc + " ]";
    }
    
}
