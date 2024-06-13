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
@Table(name = "tipo_de_ingreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDeIngreso.findAll", query = "SELECT t FROM TipoDeIngreso t")
    , @NamedQuery(name = "TipoDeIngreso.findByCodigo", query = "SELECT t FROM TipoDeIngreso t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoDeIngreso.findByNombre", query = "SELECT t FROM TipoDeIngreso t WHERE t.nombre = :nombre")})
public class TipoDeIngreso implements Serializable {

    @OneToMany(mappedBy = "tipoDeIngreso")
    private Collection<DetalleFormato607> detalleFormato607Collection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "tipoDeIngreso")
    private Collection<Factura> facturaCollection;

    public TipoDeIngreso() {
    }

    public TipoDeIngreso(Integer codigo) {
        this.codigo = codigo;
    }

    public TipoDeIngreso(Integer codigo, String nombre) {
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
        if (!(object instanceof TipoDeIngreso)) {
            return false;
        }
        TipoDeIngreso other = (TipoDeIngreso) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TipoDeIngreso[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<DetalleFormato607> getDetalleFormato607Collection() {
        return detalleFormato607Collection;
    }

    public void setDetalleFormato607Collection(Collection<DetalleFormato607> detalleFormato607Collection) {
        this.detalleFormato607Collection = detalleFormato607Collection;
    }
    
}
