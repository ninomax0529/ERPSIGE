/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "campo_cheque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CampoCheque.findAll", query = "SELECT c FROM CampoCheque c")
    , @NamedQuery(name = "CampoCheque.findByCodigo", query = "SELECT c FROM CampoCheque c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "CampoCheque.findByNombre", query = "SELECT c FROM CampoCheque c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "CampoCheque.findByActivo", query = "SELECT c FROM CampoCheque c WHERE c.activo = :activo")})
public class CampoCheque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "activo")
    private boolean activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campoCheque")
    private Collection<ConfiguracionCheque> configuracionChequeCollection;

    public CampoCheque() {
    }

    public CampoCheque(Integer codigo) {
        this.codigo = codigo;
    }

    public CampoCheque(Integer codigo, String nombre, boolean activo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.activo = activo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public Collection<ConfiguracionCheque> getConfiguracionChequeCollection() {
        return configuracionChequeCollection;
    }

    public void setConfiguracionChequeCollection(Collection<ConfiguracionCheque> configuracionChequeCollection) {
        this.configuracionChequeCollection = configuracionChequeCollection;
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
        if (!(object instanceof CampoCheque)) {
            return false;
        }
        CampoCheque other = (CampoCheque) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CampoCheque[ codigo=" + codigo + " ]";
    }
    
}
