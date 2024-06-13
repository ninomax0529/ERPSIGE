/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "deduciones_tss")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeducionesTss.findAll", query = "SELECT d FROM DeducionesTss d")
    , @NamedQuery(name = "DeducionesTss.findByCodigo", query = "SELECT d FROM DeducionesTss d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DeducionesTss.findByAfp", query = "SELECT d FROM DeducionesTss d WHERE d.afp = :afp")
    , @NamedQuery(name = "DeducionesTss.findBySfs", query = "SELECT d FROM DeducionesTss d WHERE d.sfs = :sfs")
    , @NamedQuery(name = "DeducionesTss.findByArl", query = "SELECT d FROM DeducionesTss d WHERE d.arl = :arl")
    , @NamedQuery(name = "DeducionesTss.findByInfotep", query = "SELECT d FROM DeducionesTss d WHERE d.infotep = :infotep")})
public class DeducionesTss implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "afp")
    private float afp;
    @Basic(optional = false)
    @Column(name = "sfs")
    private float sfs;
    @Basic(optional = false)
    @Column(name = "arl")
    private float arl;
    @Basic(optional = false)
    @Column(name = "infotep")
    private float infotep;
    @JoinColumn(name = "constribuyente", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private ConstribuyenteTss constribuyente;

    public DeducionesTss() {
    }

    public DeducionesTss(Integer codigo) {
        this.codigo = codigo;
    }

    public DeducionesTss(Integer codigo, float afp, float sfs, float arl, float infotep) {
        this.codigo = codigo;
        this.afp = afp;
        this.sfs = sfs;
        this.arl = arl;
        this.infotep = infotep;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public float getAfp() {
        return afp;
    }

    public void setAfp(float afp) {
        this.afp = afp;
    }

    public float getSfs() {
        return sfs;
    }

    public void setSfs(float sfs) {
        this.sfs = sfs;
    }

    public float getArl() {
        return arl;
    }

    public void setArl(float arl) {
        this.arl = arl;
    }

    public float getInfotep() {
        return infotep;
    }

    public void setInfotep(float infotep) {
        this.infotep = infotep;
    }

    public ConstribuyenteTss getConstribuyente() {
        return constribuyente;
    }

    public void setConstribuyente(ConstribuyenteTss constribuyente) {
        this.constribuyente = constribuyente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeducionesTss)) {
            return false;
        }
        DeducionesTss other = (DeducionesTss) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DeducionesTss[ codigo=" + codigo + " ]";
    }
    
}
