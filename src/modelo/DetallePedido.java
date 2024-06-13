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
@Table(name = "detalle_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallePedido.findAll", query = "SELECT d FROM DetallePedido d")
    , @NamedQuery(name = "DetallePedido.findByCodigo", query = "SELECT d FROM DetallePedido d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetallePedido.findByCantidad", query = "SELECT d FROM DetallePedido d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetallePedido.findByPrecio", query = "SELECT d FROM DetallePedido d WHERE d.precio = :precio")
    , @NamedQuery(name = "DetallePedido.findByCostoUnitario", query = "SELECT d FROM DetallePedido d WHERE d.costoUnitario = :costoUnitario")
    , @NamedQuery(name = "DetallePedido.findByDescuento", query = "SELECT d FROM DetallePedido d WHERE d.descuento = :descuento")
    , @NamedQuery(name = "DetallePedido.findByItbis", query = "SELECT d FROM DetallePedido d WHERE d.itbis = :itbis")
    , @NamedQuery(name = "DetallePedido.findByExistencia", query = "SELECT d FROM DetallePedido d WHERE d.existencia = :existencia")
    , @NamedQuery(name = "DetallePedido.findByNuevaExistencia", query = "SELECT d FROM DetallePedido d WHERE d.nuevaExistencia = :nuevaExistencia")
    , @NamedQuery(name = "DetallePedido.findByTotal", query = "SELECT d FROM DetallePedido d WHERE d.total = :total")
    , @NamedQuery(name = "DetallePedido.findBySubTotal", query = "SELECT d FROM DetallePedido d WHERE d.subTotal = :subTotal")
    , @NamedQuery(name = "DetallePedido.findByNombreArticulo", query = "SELECT d FROM DetallePedido d WHERE d.nombreArticulo = :nombreArticulo")
    , @NamedQuery(name = "DetallePedido.findByPrecioItbis", query = "SELECT d FROM DetallePedido d WHERE d.precioItbis = :precioItbis")
    , @NamedQuery(name = "DetallePedido.findByNumeroArticulo", query = "SELECT d FROM DetallePedido d WHERE d.numeroArticulo = :numeroArticulo")
    , @NamedQuery(name = "DetallePedido.findByUnidadInventario", query = "SELECT d FROM DetallePedido d WHERE d.unidadInventario = :unidadInventario")
    , @NamedQuery(name = "DetallePedido.findByCantidadInventario", query = "SELECT d FROM DetallePedido d WHERE d.cantidadInventario = :cantidadInventario")
    , @NamedQuery(name = "DetallePedido.findByFactorUnidadSalida", query = "SELECT d FROM DetallePedido d WHERE d.factorUnidadSalida = :factorUnidadSalida")
    , @NamedQuery(name = "DetallePedido.findByListaDePrecio", query = "SELECT d FROM DetallePedido d WHERE d.listaDePrecio = :listaDePrecio")
    , @NamedQuery(name = "DetallePedido.findByTasaItbis", query = "SELECT d FROM DetallePedido d WHERE d.tasaItbis = :tasaItbis")})
public class DetallePedido implements Serializable {

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
    @Column(name = "costo_unitario")
    private Double costoUnitario;
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
    @Column(name = "precio_itbis")
    private Double precioItbis;
    @Column(name = "numero_articulo")
    private Integer numeroArticulo;
    @Column(name = "unidad_inventario")
    private Integer unidadInventario;
    @Column(name = "cantidad_inventario")
    private Double cantidadInventario;
    @Column(name = "factor_unidad_salida")
    private Double factorUnidadSalida;
    @Column(name = "lista_de_precio")
    private Integer listaDePrecio;
    @Column(name = "tasa_itbis")
    private Double tasaItbis;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "pedido", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidad;

    public DetallePedido() {
    }

    public DetallePedido(Integer codigo) {
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

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
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

    public Double getPrecioItbis() {
        return precioItbis;
    }

    public void setPrecioItbis(Double precioItbis) {
        this.precioItbis = precioItbis;
    }

    public Integer getNumeroArticulo() {
        return numeroArticulo;
    }

    public void setNumeroArticulo(Integer numeroArticulo) {
        this.numeroArticulo = numeroArticulo;
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

    public Double getTasaItbis() {
        return tasaItbis;
    }

    public void setTasaItbis(Double tasaItbis) {
        this.tasaItbis = tasaItbis;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
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
        if (!(object instanceof DetallePedido)) {
            return false;
        }
        DetallePedido other = (DetallePedido) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetallePedido[ codigo=" + codigo + " ]";
    }
    
}
