/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "detalle_cotizacion_de_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCotizacionDeVenta.findAll", query = "SELECT d FROM DetalleCotizacionDeVenta d")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findByCodigo", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findByCantidad", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findByPrecio", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.precio = :precio")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findByDescuento", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.descuento = :descuento")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findByItbis", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.itbis = :itbis")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findByTotal", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.total = :total")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findBySubTotal", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.subTotal = :subTotal")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findByNombreArticulo", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.nombreArticulo = :nombreArticulo")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findByTasaItbis", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.tasaItbis = :tasaItbis")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findByPrecioItbis", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.precioItbis = :precioItbis")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findByCostoUnitario", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.costoUnitario = :costoUnitario")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findByUnidadInventario", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.unidadInventario = :unidadInventario")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findByCantidadInventario", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.cantidadInventario = :cantidadInventario")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findByFactorUnidadSalida", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.factorUnidadSalida = :factorUnidadSalida")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findByListaDePrecio", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.listaDePrecio = :listaDePrecio")
    , @NamedQuery(name = "DetalleCotizacionDeVenta.findByPorcientoDescuento", query = "SELECT d FROM DetalleCotizacionDeVenta d WHERE d.porcientoDescuento = :porcientoDescuento")})
public class DetalleCotizacionDeVenta implements Serializable {

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
    @Column(name = "total")
    private Double total;
    @Column(name = "sub_total")
    private Double subTotal;
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
    @Column(name = "porciento_descuento")
    private Double porcientoDescuento;
    @JoinColumn(name = "almacen", referencedColumnName = "codigo")
    @ManyToOne
    private Almacen almacen;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne
    private Unidad unidad;
    @JoinColumn(name = "cotizacion_de_venta", referencedColumnName = "codigo")
    @ManyToOne
    private CotizacionDeVenta cotizacionDeVenta;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo articulo;

    public DetalleCotizacionDeVenta() {
    }

    public DetalleCotizacionDeVenta(Integer codigo) {
        this.codigo = codigo;
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

    public Double getPorcientoDescuento() {
        return porcientoDescuento;
    }

    public void setPorcientoDescuento(Double porcientoDescuento) {
        this.porcientoDescuento = porcientoDescuento;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public CotizacionDeVenta getCotizacionDeVenta() {
        return cotizacionDeVenta;
    }

    public void setCotizacionDeVenta(CotizacionDeVenta cotizacionDeVenta) {
        this.cotizacionDeVenta = cotizacionDeVenta;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
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
        if (!(object instanceof DetalleCotizacionDeVenta)) {
            return false;
        }
        DetalleCotizacionDeVenta other = (DetalleCotizacionDeVenta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleCotizacionDeVenta[ codigo=" + codigo + " ]";
    }
    
}
