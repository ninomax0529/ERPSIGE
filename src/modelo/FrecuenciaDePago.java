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
@Table(name = "frecuencia_de_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrecuenciaDePago.findAll", query = "SELECT f FROM FrecuenciaDePago f")
    , @NamedQuery(name = "FrecuenciaDePago.findByCodigo", query = "SELECT f FROM FrecuenciaDePago f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "FrecuenciaDePago.findByFrecuencia", query = "SELECT f FROM FrecuenciaDePago f WHERE f.frecuencia = :frecuencia")
    , @NamedQuery(name = "FrecuenciaDePago.findByHabilitada", query = "SELECT f FROM FrecuenciaDePago f WHERE f.habilitada = :habilitada")
    , @NamedQuery(name = "FrecuenciaDePago.findByDescripcion", query = "SELECT f FROM FrecuenciaDePago f WHERE f.descripcion = :descripcion")})
public class FrecuenciaDePago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "frecuencia")
    private String frecuencia;
    @Basic(optional = false)
    @Column(name = "habilitada")
    private boolean habilitada;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "frecuenciaDePago")
    private Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "frecuenciaDePago")
    private Collection<ContratoDeServicio> contratoDeServicioCollection;

    public FrecuenciaDePago() {
    }

    public FrecuenciaDePago(Integer codigo) {
        this.codigo = codigo;
    }

    public FrecuenciaDePago(Integer codigo, String frecuencia, boolean habilitada) {
        this.codigo = codigo;
        this.frecuencia = frecuencia;
        this.habilitada = habilitada;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public boolean getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<DetalleContratoDeServicio> getDetalleContratoDeServicioCollection() {
        return detalleContratoDeServicioCollection;
    }

    public void setDetalleContratoDeServicioCollection(Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection) {
        this.detalleContratoDeServicioCollection = detalleContratoDeServicioCollection;
    }

    @XmlTransient
    public Collection<ContratoDeServicio> getContratoDeServicioCollection() {
        return contratoDeServicioCollection;
    }

    public void setContratoDeServicioCollection(Collection<ContratoDeServicio> contratoDeServicioCollection) {
        this.contratoDeServicioCollection = contratoDeServicioCollection;
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
        if (!(object instanceof FrecuenciaDePago)) {
            return false;
        }
        FrecuenciaDePago other = (FrecuenciaDePago) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.FrecuenciaDePago[ codigo=" + codigo + " ]";
    }
    
}
