/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "corte_de_facturacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CorteDeFacturacion.findAll", query = "SELECT c FROM CorteDeFacturacion c")
    , @NamedQuery(name = "CorteDeFacturacion.findByCodigo", query = "SELECT c FROM CorteDeFacturacion c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "CorteDeFacturacion.findByFecha", query = "SELECT c FROM CorteDeFacturacion c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "CorteDeFacturacion.findByFechaRegistro", query = "SELECT c FROM CorteDeFacturacion c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "CorteDeFacturacion.findByCantidadFactura", query = "SELECT c FROM CorteDeFacturacion c WHERE c.cantidadFactura = :cantidadFactura")})
public class CorteDeFacturacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "cantidad_factura")
    private int cantidadFactura;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "corteDeFacturacion")
    private Collection<DetalleCorteDeFacturacion> detalleCorteDeFacturacionCollection;

    public CorteDeFacturacion() {
    }

    public CorteDeFacturacion(Integer codigo) {
        this.codigo = codigo;
    }

    public CorteDeFacturacion(Integer codigo, Date fecha, Date fechaRegistro, int cantidadFactura) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.fechaRegistro = fechaRegistro;
        this.cantidadFactura = cantidadFactura;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getCantidadFactura() {
        return cantidadFactura;
    }

    public void setCantidadFactura(int cantidadFactura) {
        this.cantidadFactura = cantidadFactura;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<DetalleCorteDeFacturacion> getDetalleCorteDeFacturacionCollection() {
        return detalleCorteDeFacturacionCollection;
    }

    public void setDetalleCorteDeFacturacionCollection(Collection<DetalleCorteDeFacturacion> detalleCorteDeFacturacionCollection) {
        this.detalleCorteDeFacturacionCollection = detalleCorteDeFacturacionCollection;
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
        if (!(object instanceof CorteDeFacturacion)) {
            return false;
        }
        CorteDeFacturacion other = (CorteDeFacturacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CorteDeFacturacion[ codigo=" + codigo + " ]";
    }
    
}
