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
import javax.persistence.Lob;
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
@Table(name = "detalle_factura_temporal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleFacturaTemporal.findAll", query = "SELECT d FROM DetalleFacturaTemporal d")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByCodigo", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByCantidad", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByPrecio", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.precio = :precio")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByDescuento", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.descuento = :descuento")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByItbis", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.itbis = :itbis")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByExistencia", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.existencia = :existencia")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByNuevaExistencia", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.nuevaExistencia = :nuevaExistencia")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByTotal", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.total = :total")
    , @NamedQuery(name = "DetalleFacturaTemporal.findBySubTotal", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.subTotal = :subTotal")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByNombreArticulo", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.nombreArticulo = :nombreArticulo")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByTasaItbis", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.tasaItbis = :tasaItbis")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByPrecioItbis", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.precioItbis = :precioItbis")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByCostoUnitario", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.costoUnitario = :costoUnitario")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByUnidadInventario", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.unidadInventario = :unidadInventario")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByCantidadInventario", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.cantidadInventario = :cantidadInventario")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByFactorUnidadSalida", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.factorUnidadSalida = :factorUnidadSalida")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByListaDePrecio", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.listaDePrecio = :listaDePrecio")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByComponente", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.componente = :componente")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByArticuloCompuesto", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.articuloCompuesto = :articuloCompuesto")
    , @NamedQuery(name = "DetalleFacturaTemporal.findByPorcientoDescuento", query = "SELECT d FROM DetalleFacturaTemporal d WHERE d.porcientoDescuento = :porcientoDescuento")})
public class DetalleFacturaTemporal implements Serializable {

    @Column(name = "detalle_contrato")
    private Integer detalleContrato;


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
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo articulo;
    @JoinColumn(name = "factura", referencedColumnName = "codigo")
    @ManyToOne
    private FacturaTemporal factura;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne
    private Unidad unidad;
    @JoinColumn(name = "almacen", referencedColumnName = "codigo")
    @ManyToOne
    private Almacen almacen;
  
    public DetalleFacturaTemporal() {
    }

    public DetalleFacturaTemporal(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleFacturaTemporal(Integer codigo, boolean componente, boolean articuloCompuesto) {
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

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public FacturaTemporal getFactura() {
        return factura;
    }

    public void setFactura(FacturaTemporal factura) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFacturaTemporal)) {
            return false;
        }
        DetalleFacturaTemporal other = (DetalleFacturaTemporal) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleFacturaTemporal[ codigo=" + codigo + " ]";
    }

    public Integer getDetalleContrato() {
        return detalleContrato;
    }

    public void setDetalleContrato(Integer detalleContrato) {
        this.detalleContrato = detalleContrato;
    }

}
