/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "condicion_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CondicionPago.findAll", query = "SELECT c FROM CondicionPago c")
    , @NamedQuery(name = "CondicionPago.findByCodigo", query = "SELECT c FROM CondicionPago c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "CondicionPago.findByDescripcion", query = "SELECT c FROM CondicionPago c WHERE c.descripcion = :descripcion")})
public class CondicionPago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "codicionPago")
    private Collection<RecepcionArticulo> recepcionArticuloCollection;
    @OneToMany(mappedBy = "condicionPago")
    private Collection<FacturaTemporal> facturaTemporalCollection;
    @OneToMany(mappedBy = "condicionPago")
    private Collection<Factura> facturaCollection;

    public CondicionPago() {
    }

    public CondicionPago(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<RecepcionArticulo> getRecepcionArticuloCollection() {
        return recepcionArticuloCollection;
    }

    public void setRecepcionArticuloCollection(Collection<RecepcionArticulo> recepcionArticuloCollection) {
        this.recepcionArticuloCollection = recepcionArticuloCollection;
    }

    @XmlTransient
    public Collection<FacturaTemporal> getFacturaTemporalCollection() {
        return facturaTemporalCollection;
    }

    public void setFacturaTemporalCollection(Collection<FacturaTemporal> facturaTemporalCollection) {
        this.facturaTemporalCollection = facturaTemporalCollection;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
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
        if (!(object instanceof CondicionPago)) {
            return false;
        }
        CondicionPago other = (CondicionPago) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CondicionPago[ codigo=" + codigo + " ]";
    }
    
}
