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
@Table(name = "costos_y_gastos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CostosYGastos.findAll", query = "SELECT c FROM CostosYGastos c")
    , @NamedQuery(name = "CostosYGastos.findByCodigo", query = "SELECT c FROM CostosYGastos c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "CostosYGastos.findByNombre", query = "SELECT c FROM CostosYGastos c WHERE c.nombre = :nombre")})
public class CostosYGastos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "costosYGastos")
    private Collection<FacturaSuplidor> facturaSuplidorCollection;
    @OneToMany(mappedBy = "tipoBienesYServicios")
    private Collection<DetalleFormato606> detalleFormato606Collection;

    public CostosYGastos() {
    }

    public CostosYGastos(Integer codigo) {
        this.codigo = codigo;
    }

    public CostosYGastos(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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

    @XmlTransient
    public Collection<FacturaSuplidor> getFacturaSuplidorCollection() {
        return facturaSuplidorCollection;
    }

    public void setFacturaSuplidorCollection(Collection<FacturaSuplidor> facturaSuplidorCollection) {
        this.facturaSuplidorCollection = facturaSuplidorCollection;
    }

    @XmlTransient
    public Collection<DetalleFormato606> getDetalleFormato606Collection() {
        return detalleFormato606Collection;
    }

    public void setDetalleFormato606Collection(Collection<DetalleFormato606> detalleFormato606Collection) {
        this.detalleFormato606Collection = detalleFormato606Collection;
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
        if (!(object instanceof CostosYGastos)) {
            return false;
        }
        CostosYGastos other = (CostosYGastos) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CostosYGastos[ codigo=" + codigo + " ]";
    }
    
}
