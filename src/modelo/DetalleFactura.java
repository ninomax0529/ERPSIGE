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
import javax.persistence.Lob;
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
@Table(name = "detalle_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleFactura.findAll", query = "SELECT d FROM DetalleFactura d")
    , @NamedQuery(name = "DetalleFactura.findByCodigo", query = "SELECT d FROM DetalleFactura d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleFactura.findByCantidad", query = "SELECT d FROM DetalleFactura d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleFactura.findByPrecio", query = "SELECT d FROM DetalleFactura d WHERE d.precio = :precio")
    , @NamedQuery(name = "DetalleFactura.findByDescuento", query = "SELECT d FROM DetalleFactura d WHERE d.descuento = :descuento")
    , @NamedQuery(name = "DetalleFactura.findByItbis", query = "SELECT d FROM DetalleFactura d WHERE d.itbis = :itbis")
    , @NamedQuery(name = "DetalleFactura.findByExistencia", query = "SELECT d FROM DetalleFactura d WHERE d.existencia = :existencia")
    , @NamedQuery(name = "DetalleFactura.findByNuevaExistencia", query = "SELECT d FROM DetalleFactura d WHERE d.nuevaExistencia = :nuevaExistencia")
    , @NamedQuery(name = "DetalleFactura.findByTotal", query = "SELECT d FROM DetalleFactura d WHERE d.total = :total")
    , @NamedQuery(name = "DetalleFactura.findBySubTotal", query = "SELECT d FROM DetalleFactura d WHERE d.subTotal = :subTotal")
    , @NamedQuery(name = "DetalleFactura.findByTasaItbis", query = "SELECT d FROM DetalleFactura d WHERE d.tasaItbis = :tasaItbis")
    , @NamedQuery(name = "DetalleFactura.findByPrecioItbis", query = "SELECT d FROM DetalleFactura d WHERE d.precioItbis = :precioItbis")
    , @NamedQuery(name = "DetalleFactura.findByCostoUnitario", query = "SELECT d FROM DetalleFactura d WHERE d.costoUnitario = :costoUnitario")
    , @NamedQuery(name = "DetalleFactura.findByUnidadInventario", query = "SELECT d FROM DetalleFactura d WHERE d.unidadInventario = :unidadInventario")
    , @NamedQuery(name = "DetalleFactura.findByCantidadInventario", query = "SELECT d FROM DetalleFactura d WHERE d.cantidadInventario = :cantidadInventario")
    , @NamedQuery(name = "DetalleFactura.findByFactorUnidadSalida", query = "SELECT d FROM DetalleFactura d WHERE d.factorUnidadSalida = :factorUnidadSalida")
    , @NamedQuery(name = "DetalleFactura.findByListaDePrecio", query = "SELECT d FROM DetalleFactura d WHERE d.listaDePrecio = :listaDePrecio")
    , @NamedQuery(name = "DetalleFactura.findByComponente", query = "SELECT d FROM DetalleFactura d WHERE d.componente = :componente")
    , @NamedQuery(name = "DetalleFactura.findByArticuloCompuesto", query = "SELECT d FROM DetalleFactura d WHERE d.articuloCompuesto = :articuloCompuesto")
    , @NamedQuery(name = "DetalleFactura.findByPorcientoDescuento", query = "SELECT d FROM DetalleFactura d WHERE d.porcientoDescuento = :porcientoDescuento")
    , @NamedQuery(name = "DetalleFactura.findByIsrRetenido", query = "SELECT d FROM DetalleFactura d WHERE d.isrRetenido = :isrRetenido")
    , @NamedQuery(name = "DetalleFactura.findByItbisRetenido", query = "SELECT d FROM DetalleFactura d WHERE d.itbisRetenido = :itbisRetenido")
    , @NamedQuery(name = "DetalleFactura.findByTotalACobrar", query = "SELECT d FROM DetalleFactura d WHERE d.totalACobrar = :totalACobrar")})
public class DetalleFactura implements Serializable {

    @Column(name = "detalle_contrato")
    private Integer detalleContrato;


    @Column(name = "monto_itbis_excento")
    private Double montoItbisExcento;

    @Column(name = "monto_excento")
    private Double montoExcento;
    @Column(name = "monto_gravado")
    private Double montoGravado;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "itbis")
    private Double itbis;
    @Column(name = "existencia")
    private Double existencia;
    @Column(name = "nueva_existencia")
    private Double nuevaExistencia;
    @Column(name = "total")
    private Double total;
    @Column(name = "sub_total")
    private Double subTotal;
    @Lob
    @Column(name = "nombre_articulo")
    private String nombreArticulo;
    @Column(name = "tasa_itbis")
    private Double tasaItbis;
    @Column(name = "precio_itbis")
    private Double precioItbis;
    @Column(name = "costo_unitario")
    private Double costoUnitario;
    @Column(name = "unidad_inventario")
    private Integer unidadInventario;
    @Column(name = "cantidad_inventario")
    private Double cantidadInventario;
    @Column(name = "factor_unidad_salida")
    private Double factorUnidadSalida;
    @Column(name = "lista_de_precio")
    private Integer listaDePrecio;
    @Basic(optional = false)
    @Column(name = "componente")
    private boolean componente;
    @Basic(optional = false)
    @Column(name = "articulo_compuesto")
    private boolean articuloCompuesto;
    @Column(name = "porciento_descuento")
    private Double porcientoDescuento;
    @Lob
    @Column(name = "descripcion_pago")
    private String descripcionPago;
    @Column(name = "isr_retenido")
    private Double isrRetenido;
    @Column(name = "itbis_retenido")
    private Double itbisRetenido;
    @Column(name = "total_a_cobrar")
    private Double totalACobrar;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo articulo;
    @JoinColumn(name = "factura", referencedColumnName = "codigo")
    @ManyToOne
    private Factura factura;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne
    private Unidad unidad;
    @JoinColumn(name = "almacen", referencedColumnName = "codigo")
    @ManyToOne
    private Almacen almacen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    private Collection<FacturaDatosDeVehiculo> facturaDatosDeVehiculoCollection;

    public DetalleFactura() {
    }

    public DetalleFactura(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleFactura(Integer codigo, boolean componente, boolean articuloCompuesto) {
        this.codigo = codigo;
        this.componente = componente;
        this.articuloCompuesto = articuloCompuesto;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getItbis() {
        return itbis;
    }

    public void setItbis(Double itbis) {
        this.itbis = itbis;
    }

    public Double getExistencia() {
        return existencia;
    }

    public void setExistencia(Double existencia) {
        this.existencia = existencia;
    }

    public Double getNuevaExistencia() {
        return nuevaExistencia;
    }

    public void setNuevaExistencia(Double nuevaExistencia) {
        this.nuevaExistencia = nuevaExistencia;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public Double getTasaItbis() {
        return tasaItbis;
    }

    public void setTasaItbis(Double tasaItbis) {
        this.tasaItbis = tasaItbis;
    }

    public Double getPrecioItbis() {
        return precioItbis;
    }

    public void setPrecioItbis(Double precioItbis) {
        this.precioItbis = precioItbis;
    }

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public Integer getUnidadInventario() {
        return unidadInventario;
    }

    public void setUnidadInventario(Integer unidadInventario) {
        this.unidadInventario = unidadInventario;
    }

    public Double getCantidadInventario() {
        return cantidadInventario;
    }

    public void setCantidadInventario(Double cantidadInventario) {
        this.cantidadInventario = cantidadInventario;
    }

    public Double getFactorUnidadSalida() {
        return factorUnidadSalida;
    }

    public void setFactorUnidadSalida(Double factorUnidadSalida) {
        this.factorUnidadSalida = factorUnidadSalida;
    }

    public Integer getListaDePrecio() {
        return listaDePrecio;
    }

    public void setListaDePrecio(Integer listaDePrecio) {
        this.listaDePrecio = listaDePrecio;
    }

    public boolean getComponente() {
        return componente;
    }

    public void setComponente(boolean componente) {
        this.componente = componente;
    }

    public boolean getArticuloCompuesto() {
        return articuloCompuesto;
    }

    public void setArticuloCompuesto(boolean articuloCompuesto) {
        this.articuloCompuesto = articuloCompuesto;
    }

    public Double getPorcientoDescuento() {
        return porcientoDescuento;
    }

    public void setPorcientoDescuento(Double porcientoDescuento) {
        this.porcientoDescuento = porcientoDescuento;
    }

    public String getDescripcionPago() {
        return descripcionPago;
    }

    public void setDescripcionPago(String descripcionPago) {
        this.descripcionPago = descripcionPago;
    }

    public Double getIsrRetenido() {
        return isrRetenido;
    }

    public void setIsrRetenido(Double isrRetenido) {
        this.isrRetenido = isrRetenido;
    }

    public Double getItbisRetenido() {
        return itbisRetenido;
    }

    public void setItbisRetenido(Double itbisRetenido) {
        this.itbisRetenido = itbisRetenido;
    }

    public Double getTotalACobrar() {
        return totalACobrar;
    }

    public void setTotalACobrar(Double totalACobrar) {
        this.totalACobrar = totalACobrar;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
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
        if (!(object instanceof DetalleFactura)) {
            return false;
        }
        DetalleFactura other = (DetalleFactura) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleFactura[ codigo=" + codigo + " ]";
    }

    public Double getMontoExcento() {
        return montoExcento;
    }

    public void setMontoExcento(Double montoExcento) {
        this.montoExcento = montoExcento;
    }

    public Double getMontoGravado() {
        return montoGravado;
    }

    public void setMontoGravado(Double montoGravado) {
        this.montoGravado = montoGravado;
    }

    public Double getMontoItbisExcento() {
        return montoItbisExcento;
    }

    public void setMontoItbisExcento(Double montoItbisExcento) {
        this.montoItbisExcento = montoItbisExcento;
    }

    public Integer getDetalleContrato() {
        return detalleContrato;
    }

    public void setDetalleContrato(Integer detalleContrato) {
        this.detalleContrato = detalleContrato;
    }

}
