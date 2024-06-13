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
@Table(name = "tipo_vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoVehiculo.findAll", query = "SELECT t FROM TipoVehiculo t")
    , @NamedQuery(name = "TipoVehiculo.findByCodigo", query = "SELECT t FROM TipoVehiculo t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoVehiculo.findByNombre", query = "SELECT t FROM TipoVehiculo t WHERE t.nombre = :nombre")})
public class TipoVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoVehiculo")
    private Collection<DatosDeVehiculo> datosDeVehiculoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoVehiculo")
    private Collection<FacturaDatosDeVehiculo> facturaDatosDeVehiculoCollection;

    public TipoVehiculo() {
    }

    public TipoVehiculo(Integer codigo) {
        this.codigo = codigo;
    }

    public TipoVehiculo(Integer codigo, String nombre) {
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
    public Collection<DatosDeVehiculo> getDatosDeVehiculoCollection() {
        return datosDeVehiculoCollection;
    }

    public void setDatosDeVehiculoCollection(Collection<DatosDeVehiculo> datosDeVehiculoCollection) {
        this.datosDeVehiculoCollection = datosDeVehiculoCollection;
    }

    @XmlTransient
    public Collection<FacturaDatosDeVehiculo> getFacturaDatosDeVehiculoCollection() {
        return facturaDatosDeVehiculoCollection;
    }

    public void setFacturaDatosDeVehiculoCollection(Collection<FacturaDatosDeVehiculo> facturaDatosDeVehiculoCollection) {
        this.facturaDatosDeVehiculoCollection = facturaDatosDeVehiculoCollection;
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
        if (!(object instanceof TipoVehiculo)) {
            return false;
        }
        TipoVehiculo other = (TipoVehiculo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TipoVehiculo[ codigo=" + codigo + " ]";
    }
    
}
