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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "almacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Almacen.findAll", query = "SELECT a FROM Almacen a")
    , @NamedQuery(name = "Almacen.findByCodigo", query = "SELECT a FROM Almacen a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "Almacen.findByDescripcion", query = "SELECT a FROM Almacen a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Almacen.findByUbicacion", query = "SELECT a FROM Almacen a WHERE a.ubicacion = :ubicacion")
    , @NamedQuery(name = "Almacen.findByNombre", query = "SELECT a FROM Almacen a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Almacen.findByDespacho", query = "SELECT a FROM Almacen a WHERE a.despacho = :despacho")})
public class Almacen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "ubicacion")
    private String ubicacion;
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "despacho")
    private boolean despacho;
    @OneToMany(mappedBy = "almacen")
    private Collection<DetalleAjusteInventario> detalleAjusteInventarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "almacen")
    private Collection<DetalleCierreMensualInventario> detalleCierreMensualInventarioCollection;
    @OneToMany(mappedBy = "almacen")
    private Collection<DetalleSalidaInventario> detalleSalidaInventarioCollection;
    @OneToMany(mappedBy = "almacen")
    private Collection<DetalleDevolucionDeInventario> detalleDevolucionDeInventarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "almacen")
    private Collection<DetalleInventarioFinal> detalleInventarioFinalCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "almacen")
    private Collection<ExistenciaArticulo> existenciaArticuloCollection;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @OneToMany(mappedBy = "almacen")
    private Collection<DetalleFactura> detalleFacturaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "almacenDestino")
    private Collection<DetalleTransferenciaAlmacen> detalleTransferenciaAlmacenCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "almacenOrigen")
    private Collection<DetalleTransferenciaAlmacen> detalleTransferenciaAlmacenCollection1;
    @OneToMany(mappedBy = "almacen")
    private Collection<DetalleEntradaInventario> detalleEntradaInventarioCollection;
    @OneToMany(mappedBy = "almacen")
    private Collection<DetalleSolicitudSalidaInventario> detalleSolicitudSalidaInventarioCollection;
    @OneToMany(mappedBy = "almacen")
    private Collection<DetalleFacturaTemporal> detalleFacturaTemporalCollection;
    @OneToMany(mappedBy = "almacen")
    private Collection<DetalleCotizacionDeVenta> detalleCotizacionDeVentaCollection;

    public Almacen() {
    }

    public Almacen(Integer codigo) {
        this.codigo = codigo;
    }

    public Almacen(Integer codigo, boolean despacho) {
        this.codigo = codigo;
        this.despacho = despacho;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getDespacho() {
        return despacho;
    }

    public void setDespacho(boolean despacho) {
        this.despacho = despacho;
    }

    @XmlTransient
    public Collection<DetalleAjusteInventario> getDetalleAjusteInventarioCollection() {
        return detalleAjusteInventarioCollection;
    }

    public void setDetalleAjusteInventarioCollection(Collection<DetalleAjusteInventario> detalleAjusteInventarioCollection) {
        this.detalleAjusteInventarioCollection = detalleAjusteInventarioCollection;
    }

    @XmlTransient
    public Collection<DetalleCierreMensualInventario> getDetalleCierreMensualInventarioCollection() {
        return detalleCierreMensualInventarioCollection;
    }

    public void setDetalleCierreMensualInventarioCollection(Collection<DetalleCierreMensualInventario> detalleCierreMensualInventarioCollection) {
        this.detalleCierreMensualInventarioCollection = detalleCierreMensualInventarioCollection;
    }

    @XmlTransient
    public Collection<DetalleSalidaInventario> getDetalleSalidaInventarioCollection() {
        return detalleSalidaInventarioCollection;
    }

    public void setDetalleSalidaInventarioCollection(Collection<DetalleSalidaInventario> detalleSalidaInventarioCollection) {
        this.detalleSalidaInventarioCollection = detalleSalidaInventarioCollection;
    }

    @XmlTransient
    public Collection<DetalleDevolucionDeInventario> getDetalleDevolucionDeInventarioCollection() {
        return detalleDevolucionDeInventarioCollection;
    }

    public void setDetalleDevolucionDeInventarioCollection(Collection<DetalleDevolucionDeInventario> detalleDevolucionDeInventarioCollection) {
        this.detalleDevolucionDeInventarioCollection = detalleDevolucionDeInventarioCollection;
    }

    @XmlTransient
    public Collection<DetalleInventarioFinal> getDetalleInventarioFinalCollection() {
        return detalleInventarioFinalCollection;
    }

    public void setDetalleInventarioFinalCollection(Collection<DetalleInventarioFinal> detalleInventarioFinalCollection) {
        this.detalleInventarioFinalCollection = detalleInventarioFinalCollection;
    }

    @XmlTransient
    public Collection<ExistenciaArticulo> getExistenciaArticuloCollection() {
        return existenciaArticuloCollection;
    }

    public void setExistenciaArticuloCollection(Collection<ExistenciaArticulo> existenciaArticuloCollection) {
        this.existenciaArticuloCollection = existenciaArticuloCollection;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    @XmlTransient
    public Collection<DetalleFactura> getDetalleFacturaCollection() {
        return detalleFacturaCollection;
    }

    public void setDetalleFacturaCollection(Collection<DetalleFactura> detalleFacturaCollection) {
        this.detalleFacturaCollection = detalleFacturaCollection;
    }

    @XmlTransient
    public Collection<DetalleTransferenciaAlmacen> getDetalleTransferenciaAlmacenCollection() {
        return detalleTransferenciaAlmacenCollection;
    }

    public void setDetalleTransferenciaAlmacenCollection(Collection<DetalleTransferenciaAlmacen> detalleTransferenciaAlmacenCollection) {
        this.detalleTransferenciaAlmacenCollection = detalleTransferenciaAlmacenCollection;
    }

    @XmlTransient
    public Collection<DetalleTransferenciaAlmacen> getDetalleTransferenciaAlmacenCollection1() {
        return detalleTransferenciaAlmacenCollection1;
    }

    public void setDetalleTransferenciaAlmacenCollection1(Collection<DetalleTransferenciaAlmacen> detalleTransferenciaAlmacenCollection1) {
        this.detalleTransferenciaAlmacenCollection1 = detalleTransferenciaAlmacenCollection1;
    }

    @XmlTransient
    public Collection<DetalleEntradaInventario> getDetalleEntradaInventarioCollection() {
        return detalleEntradaInventarioCollection;
    }

    public void setDetalleEntradaInventarioCollection(Collection<DetalleEntradaInventario> detalleEntradaInventarioCollection) {
        this.detalleEntradaInventarioCollection = detalleEntradaInventarioCollection;
    }

    @XmlTransient
    public Collection<DetalleSolicitudSalidaInventario> getDetalleSolicitudSalidaInventarioCollection() {
        return detalleSolicitudSalidaInventarioCollection;
    }

    public void setDetalleSolicitudSalidaInventarioCollection(Collection<DetalleSolicitudSalidaInventario> detalleSolicitudSalidaInventarioCollection) {
        this.detalleSolicitudSalidaInventarioCollection = detalleSolicitudSalidaInventarioCollection;
    }

    @XmlTransient
    public Collection<DetalleFacturaTemporal> getDetalleFacturaTemporalCollection() {
        return detalleFacturaTemporalCollection;
    }

    public void setDetalleFacturaTemporalCollection(Collection<DetalleFacturaTemporal> detalleFacturaTemporalCollection) {
        this.detalleFacturaTemporalCollection = detalleFacturaTemporalCollection;
    }

    @XmlTransient
    public Collection<DetalleCotizacionDeVenta> getDetalleCotizacionDeVentaCollection() {
        return detalleCotizacionDeVentaCollection;
    }

    public void setDetalleCotizacionDeVentaCollection(Collection<DetalleCotizacionDeVenta> detalleCotizacionDeVentaCollection) {
        this.detalleCotizacionDeVentaCollection = detalleCotizacionDeVentaCollection;
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
        if (!(object instanceof Almacen)) {
            return false;
        }
        Almacen other = (Almacen) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Almacen[ codigo=" + codigo + " ]";
    }
    
}
