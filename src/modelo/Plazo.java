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
@Table(name = "plazo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plazo.findAll", query = "SELECT p FROM Plazo p")
    , @NamedQuery(name = "Plazo.findByCodigo", query = "SELECT p FROM Plazo p WHERE p.codigo = :codigo")
    , @NamedQuery(name = "Plazo.findByDescripcion", query = "SELECT p FROM Plazo p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Plazo.findByDias", query = "SELECT p FROM Plazo p WHERE p.dias = :dias")})
public class Plazo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "dias")
    private int dias;
    @OneToMany(mappedBy = "plazo")
    private Collection<RecepcionArticulo> recepcionArticuloCollection;
    @OneToMany(mappedBy = "plazo")
    private Collection<FacturaTemporal> facturaTemporalCollection;
    @OneToMany(mappedBy = "plazo")
    private Collection<OrdenCompra> ordenCompraCollection;
    @OneToMany(mappedBy = "plazo")
    private Collection<Factura> facturaCollection;
    @OneToMany(mappedBy = "plazo")
    private Collection<Suplidor> suplidorCollection;

    public Plazo() {
    }

    public Plazo(Integer codigo) {
        this.codigo = codigo;
    }

    public Plazo(Integer codigo, int dias) {
        this.codigo = codigo;
        this.dias = dias;
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

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
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
    public Collection<OrdenCompra> getOrdenCompraCollection() {
        return ordenCompraCollection;
    }

    public void setOrdenCompraCollection(Collection<OrdenCompra> ordenCompraCollection) {
        this.ordenCompraCollection = ordenCompraCollection;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    @XmlTransient
    public Collection<Suplidor> getSuplidorCollection() {
        return suplidorCollection;
    }

    public void setSuplidorCollection(Collection<Suplidor> suplidorCollection) {
        this.suplidorCollection = suplidorCollection;
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
        if (!(object instanceof Plazo)) {
            return false;
        }
        Plazo other = (Plazo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Plazo[ codigo=" + codigo + " ]";
    }
    
}
