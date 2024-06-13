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
@Table(name = "unidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unidad.findAll", query = "SELECT u FROM Unidad u")
    , @NamedQuery(name = "Unidad.findByCodigo", query = "SELECT u FROM Unidad u WHERE u.codigo = :codigo")
    , @NamedQuery(name = "Unidad.findByDescripcion", query = "SELECT u FROM Unidad u WHERE u.descripcion = :descripcion")
    , @NamedQuery(name = "Unidad.findByAbreviatura", query = "SELECT u FROM Unidad u WHERE u.abreviatura = :abreviatura")})
public class Unidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "abreviatura")
    private String abreviatura;
    @OneToMany(mappedBy = "unidad")
    private Collection<DetalleFacturaSuplidor> detalleFacturaSuplidorCollection;
    @OneToMany(mappedBy = "unidadEntrada")
    private Collection<Articulo> articuloCollection;
    @OneToMany(mappedBy = "unidadSalida")
    private Collection<Articulo> articuloCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad")
    private Collection<DetalleAjusteInventario> detalleAjusteInventarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadEntrada")
    private Collection<DetalleAjusteInventario> detalleAjusteInventarioCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadSalida")
    private Collection<DetalleAjusteInventario> detalleAjusteInventarioCollection2;
    @OneToMany(mappedBy = "unidad")
    private Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad")
    private Collection<DetalleReceta> detalleRecetaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad")
    private Collection<DetalleInventario> detalleInventarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad")
    private Collection<DetallePedido> detallePedidoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad")
    private Collection<RegistroLote> registroLoteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad")
    private Collection<ArticuloUnidad> articuloUnidadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad")
    private Collection<DetalleSalidaInventario> detalleSalidaInventarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadSalida")
    private Collection<DetalleListaDePrecio> detalleListaDePrecioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad")
    private Collection<DetalleInventarioFinal> detalleInventarioFinalCollection;
    @OneToMany(mappedBy = "unidad")
    private Collection<ExistenciaArticulo> existenciaArticuloCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad")
    private Collection<Receta> recetaCollection;
    @OneToMany(mappedBy = "unidad")
    private Collection<DetalleRecepcionArticulo> detalleRecepcionArticuloCollection;
    @OneToMany(mappedBy = "unidad")
    private Collection<DetalleFactura> detalleFacturaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad")
    private Collection<DetalleTransferenciaAlmacen> detalleTransferenciaAlmacenCollection;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad")
    private Collection<DetalleEntradaInventario> detalleEntradaInventarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad")
    private Collection<DetalleSolicitudSalidaInventario> detalleSolicitudSalidaInventarioCollection;
    @OneToMany(mappedBy = "unidad")
    private Collection<DetalleFacturaTemporal> detalleFacturaTemporalCollection;
    @OneToMany(mappedBy = "unidad")
    private Collection<DetalleCotizacionDeVenta> detalleCotizacionDeVentaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad")
    private Collection<DetalleConduce> detalleConduceCollection;
    @OneToMany(mappedBy = "unidad")
    private Collection<DetalleOrdenCompra> detalleOrdenCompraCollection;

    public Unidad() {
    }

    public Unidad(Integer codigo) {
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

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    @XmlTransient
    public Collection<DetalleFacturaSuplidor> getDetalleFacturaSuplidorCollection() {
        return detalleFacturaSuplidorCollection;
    }

    public void setDetalleFacturaSuplidorCollection(Collection<DetalleFacturaSuplidor> detalleFacturaSuplidorCollection) {
        this.detalleFacturaSuplidorCollection = detalleFacturaSuplidorCollection;
    }

    @XmlTransient
    public Collection<Articulo> getArticuloCollection() {
        return articuloCollection;
    }

    public void setArticuloCollection(Collection<Articulo> articuloCollection) {
        this.articuloCollection = articuloCollection;
    }

    @XmlTransient
    public Collection<Articulo> getArticuloCollection1() {
        return articuloCollection1;
    }

    public void setArticuloCollection1(Collection<Articulo> articuloCollection1) {
        this.articuloCollection1 = articuloCollection1;
    }

    @XmlTransient
    public Collection<DetalleAjusteInventario> getDetalleAjusteInventarioCollection() {
        return detalleAjusteInventarioCollection;
    }

    public void setDetalleAjusteInventarioCollection(Collection<DetalleAjusteInventario> detalleAjusteInventarioCollection) {
        this.detalleAjusteInventarioCollection = detalleAjusteInventarioCollection;
    }

    @XmlTransient
    public Collection<DetalleAjusteInventario> getDetalleAjusteInventarioCollection1() {
        return detalleAjusteInventarioCollection1;
    }

    public void setDetalleAjusteInventarioCollection1(Collection<DetalleAjusteInventario> detalleAjusteInventarioCollection1) {
        this.detalleAjusteInventarioCollection1 = detalleAjusteInventarioCollection1;
    }

    @XmlTransient
    public Collection<DetalleAjusteInventario> getDetalleAjusteInventarioCollection2() {
        return detalleAjusteInventarioCollection2;
    }

    public void setDetalleAjusteInventarioCollection2(Collection<DetalleAjusteInventario> detalleAjusteInventarioCollection2) {
        this.detalleAjusteInventarioCollection2 = detalleAjusteInventarioCollection2;
    }

    @XmlTransient
    public Collection<DetalleContratoDeServicio> getDetalleContratoDeServicioCollection() {
        return detalleContratoDeServicioCollection;
    }

    public void setDetalleContratoDeServicioCollection(Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection) {
        this.detalleContratoDeServicioCollection = detalleContratoDeServicioCollection;
    }

    @XmlTransient
    public Collection<DetalleReceta> getDetalleRecetaCollection() {
        return detalleRecetaCollection;
    }

    public void setDetalleRecetaCollection(Collection<DetalleReceta> detalleRecetaCollection) {
        this.detalleRecetaCollection = detalleRecetaCollection;
    }

    @XmlTransient
    public Collection<DetalleInventario> getDetalleInventarioCollection() {
        return detalleInventarioCollection;
    }

    public void setDetalleInventarioCollection(Collection<DetalleInventario> detalleInventarioCollection) {
        this.detalleInventarioCollection = detalleInventarioCollection;
    }

    @XmlTransient
    public Collection<DetallePedido> getDetallePedidoCollection() {
        return detallePedidoCollection;
    }

    public void setDetallePedidoCollection(Collection<DetallePedido> detallePedidoCollection) {
        this.detallePedidoCollection = detallePedidoCollection;
    }

    @XmlTransient
    public Collection<RegistroLote> getRegistroLoteCollection() {
        return registroLoteCollection;
    }

    public void setRegistroLoteCollection(Collection<RegistroLote> registroLoteCollection) {
        this.registroLoteCollection = registroLoteCollection;
    }

    @XmlTransient
    public Collection<ArticuloUnidad> getArticuloUnidadCollection() {
        return articuloUnidadCollection;
    }

    public void setArticuloUnidadCollection(Collection<ArticuloUnidad> articuloUnidadCollection) {
        this.articuloUnidadCollection = articuloUnidadCollection;
    }

    @XmlTransient
    public Collection<DetalleSalidaInventario> getDetalleSalidaInventarioCollection() {
        return detalleSalidaInventarioCollection;
    }

    public void setDetalleSalidaInventarioCollection(Collection<DetalleSalidaInventario> detalleSalidaInventarioCollection) {
        this.detalleSalidaInventarioCollection = detalleSalidaInventarioCollection;
    }

    @XmlTransient
    public Collection<DetalleListaDePrecio> getDetalleListaDePrecioCollection() {
        return detalleListaDePrecioCollection;
    }

    public void setDetalleListaDePrecioCollection(Collection<DetalleListaDePrecio> detalleListaDePrecioCollection) {
        this.detalleListaDePrecioCollection = detalleListaDePrecioCollection;
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

    @XmlTransient
    public Collection<Receta> getRecetaCollection() {
        return recetaCollection;
    }

    public void setRecetaCollection(Collection<Receta> recetaCollection) {
        this.recetaCollection = recetaCollection;
    }

    @XmlTransient
    public Collection<DetalleRecepcionArticulo> getDetalleRecepcionArticuloCollection() {
        return detalleRecepcionArticuloCollection;
    }

    public void setDetalleRecepcionArticuloCollection(Collection<DetalleRecepcionArticulo> detalleRecepcionArticuloCollection) {
        this.detalleRecepcionArticuloCollection = detalleRecepcionArticuloCollection;
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

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
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

    @XmlTransient
    public Collection<DetalleConduce> getDetalleConduceCollection() {
        return detalleConduceCollection;
    }

    public void setDetalleConduceCollection(Collection<DetalleConduce> detalleConduceCollection) {
        this.detalleConduceCollection = detalleConduceCollection;
    }

    @XmlTransient
    public Collection<DetalleOrdenCompra> getDetalleOrdenCompraCollection() {
        return detalleOrdenCompraCollection;
    }

    public void setDetalleOrdenCompraCollection(Collection<DetalleOrdenCompra> detalleOrdenCompraCollection) {
        this.detalleOrdenCompraCollection = detalleOrdenCompraCollection;
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
        if (!(object instanceof Unidad)) {
            return false;
        }
        Unidad other = (Unidad) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Unidad[ codigo=" + codigo + " ]";
    }
    
}
