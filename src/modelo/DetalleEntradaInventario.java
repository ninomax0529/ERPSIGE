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
@Table(name = "detalle_entrada_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleEntradaInventario.findAll", query = "SELECT d FROM DetalleEntradaInventario d")
    , @NamedQuery(name = "DetalleEntradaInventario.findByCodigo", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleEntradaInventario.findByDescripcionArticulo", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.descripcionArticulo = :descripcionArticulo")
    , @NamedQuery(name = "DetalleEntradaInventario.findByCantidadPedida", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.cantidadPedida = :cantidadPedida")
    , @NamedQuery(name = "DetalleEntradaInventario.findByCantidadRecibida", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.cantidadRecibida = :cantidadRecibida")
    , @NamedQuery(name = "DetalleEntradaInventario.findByPrecio", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.precio = :precio")
    , @NamedQuery(name = "DetalleEntradaInventario.findByValor", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.valor = :valor")
    , @NamedQuery(name = "DetalleEntradaInventario.findBySuplidor", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.suplidor = :suplidor")
    , @NamedQuery(name = "DetalleEntradaInventario.findByFactura", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.factura = :factura")
    , @NamedQuery(name = "DetalleEntradaInventario.findByPendiente", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.pendiente = :pendiente")
    , @NamedQuery(name = "DetalleEntradaInventario.findByCerrado", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.cerrado = :cerrado")
    , @NamedQuery(name = "DetalleEntradaInventario.findByNumeroOrden", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.numeroOrden = :numeroOrden")
    , @NamedQuery(name = "DetalleEntradaInventario.findByNombreSuplidor", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.nombreSuplidor = :nombreSuplidor")
    , @NamedQuery(name = "DetalleEntradaInventario.findByValorImpuesto", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.valorImpuesto = :valorImpuesto")
    , @NamedQuery(name = "DetalleEntradaInventario.findByExistencia", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.existencia = :existencia")
    , @NamedQuery(name = "DetalleEntradaInventario.findByExistenciaAnterior", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.existenciaAnterior = :existenciaAnterior")
    , @NamedQuery(name = "DetalleEntradaInventario.findByPrecioAnterior", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.precioAnterior = :precioAnterior")
    , @NamedQuery(name = "DetalleEntradaInventario.findByPrecioVentaAnterior", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.precioVentaAnterior = :precioVentaAnterior")
    , @NamedQuery(name = "DetalleEntradaInventario.findByPrecioVenta", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.precioVenta = :precioVenta")
    , @NamedQuery(name = "DetalleEntradaInventario.findByCostoUnitario", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.costoUnitario = :costoUnitario")
    , @NamedQuery(name = "DetalleEntradaInventario.findByUnidadSalida", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.unidadSalida = :unidadSalida")
    , @NamedQuery(name = "DetalleEntradaInventario.findByCantidadInventario", query = "SELECT d FROM DetalleEntradaInventario d WHERE d.cantidadInventario = :cantidadInventario")})
public class DetalleEntradaInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "descripcion_articulo")
    private String descripcionArticulo;
    @Basic(optional = false)
    @Column(name = "cantidad_pedida")
    private double cantidadPedida;
    @Basic(optional = false)
    @Column(name = "cantidad_recibida")
    private double cantidadRecibida;
    @Basic(optional = false)
    @Column(name = "precio")
    private double precio;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @Column(name = "suplidor")
    private int suplidor;
    @Basic(optional = false)
    @Column(name = "factura")
    private String factura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pendiente")
    private Double pendiente;
    @Column(name = "cerrado")
    private String cerrado;
    @Column(name = "numero_orden")
    private String numeroOrden;
    @Column(name = "nombre_suplidor")
    private String nombreSuplidor;
    @Column(name = "valor_impuesto")
    private Double valorImpuesto;
    @Basic(optional = false)
    @Column(name = "existencia")
    private double existencia;
    @Basic(optional = false)
    @Column(name = "existencia_anterior")
    private double existenciaAnterior;
    @Basic(optional = false)
    @Column(name = "precio_anterior")
    private double precioAnterior;
    @Basic(optional = false)
    @Column(name = "precio_venta_anterior")
    private double precioVentaAnterior;
    @Basic(optional = false)
    @Column(name = "precio_venta")
    private double precioVenta;
    @Basic(optional = false)
    @Column(name = "costo_unitario")
    private double costoUnitario;
    @Basic(optional = false)
    @Column(name = "unidad_salida")
    private double unidadSalida;
    @Column(name = "cantidad_inventario")
    private Double cantidadInventario;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "entrada_inventario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private EntradaInventario entradaInventario;
    @JoinColumn(name = "orden_compra", referencedColumnName = "codigo")
    @ManyToOne
    private OrdenCompra ordenCompra;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidad;
    @JoinColumn(name = "almacen", referencedColumnName = "codigo")
    @ManyToOne
    private Almacen almacen;

    public DetalleEntradaInventario() {
    }

    public DetalleEntradaInventario(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleEntradaInventario(Integer codigo, String descripcionArticulo, double cantidadPedida, double cantidadRecibida, double precio, double valor, int suplidor, String factura, double existencia, double existenciaAnterior, double precioAnterior, double precioVentaAnterior, double precioVenta, double costoUnitario, double unidadSalida) {
        this.codigo = codigo;
        this.descripcionArticulo = descripcionArticulo;
        this.cantidadPedida = cantidadPedida;
        this.cantidadRecibida = cantidadRecibida;
        this.precio = precio;
        this.valor = valor;
        this.suplidor = suplidor;
        this.factura = factura;
        this.existencia = existencia;
        this.existenciaAnterior = existenciaAnterior;
        this.precioAnterior = precioAnterior;
        this.precioVentaAnterior = precioVentaAnterior;
        this.precioVenta = precioVenta;
        this.costoUnitario = costoUnitario;
        this.unidadSalida = unidadSalida;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public double getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(double cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public double getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(double cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(int suplidor) {
        this.suplidor = suplidor;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public Double getPendiente() {
        return pendiente;
    }

    public void setPendiente(Double pendiente) {
        this.pendiente = pendiente;
    }

    public String getCerrado() {
        return cerrado;
    }

    public void setCerrado(String cerrado) {
        this.cerrado = cerrado;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getNombreSuplidor() {
        return nombreSuplidor;
    }

    public void setNombreSuplidor(String nombreSuplidor) {
        this.nombreSuplidor = nombreSuplidor;
    }

    public Double getValorImpuesto() {
        return valorImpuesto;
    }

    public void setValorImpuesto(Double valorImpuesto) {
        this.valorImpuesto = valorImpuesto;
    }

    public double getExistencia() {
        return existencia;
    }

    public void setExistencia(double existencia) {
        this.existencia = existencia;
    }

    public double getExistenciaAnterior() {
        return existenciaAnterior;
    }

    public void setExistenciaAnterior(double existenciaAnterior) {
        this.existenciaAnterior = existenciaAnterior;
    }

    public double getPrecioAnterior() {
        return precioAnterior;
    }

    public void setPrecioAnterior(double precioAnterior) {
        this.precioAnterior = precioAnterior;
    }

    public double getPrecioVentaAnterior() {
        return precioVentaAnterior;
    }

    public void setPrecioVentaAnterior(double precioVentaAnterior) {
        this.precioVentaAnterior = precioVentaAnterior;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public double getUnidadSalida() {
        return unidadSalida;
    }

    public void setUnidadSalida(double unidadSalida) {
        this.unidadSalida = unidadSalida;
    }

    public Double getCantidadInventario() {
        return cantidadInventario;
    }

    public void setCantidadInventario(Double cantidadInventario) {
        this.cantidadInventario = cantidadInventario;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public EntradaInventario getEntradaInventario() {
        return entradaInventario;
    }

    public void setEntradaInventario(EntradaInventario entradaInventario) {
        this.entradaInventario = entradaInventario;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
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
        if (!(object instanceof DetalleEntradaInventario)) {
            return false;
        }
        DetalleEntradaInventario other = (DetalleEntradaInventario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleEntradaInventario[ codigo=" + codigo + " ]";
    }
    
}
