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
@Table(name = "detalle_orden_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleOrdenCompra.findAll", query = "SELECT d FROM DetalleOrdenCompra d")
    , @NamedQuery(name = "DetalleOrdenCompra.findByCodigo", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleOrdenCompra.findByDescripcionArticulo", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.descripcionArticulo = :descripcionArticulo")
    , @NamedQuery(name = "DetalleOrdenCompra.findByPrecioUnitario", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.precioUnitario = :precioUnitario")
    , @NamedQuery(name = "DetalleOrdenCompra.findByCantidad", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleOrdenCompra.findByCantidadRecibida", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.cantidadRecibida = :cantidadRecibida")
    , @NamedQuery(name = "DetalleOrdenCompra.findBySubTotal", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.subTotal = :subTotal")
    , @NamedQuery(name = "DetalleOrdenCompra.findByDescuento", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.descuento = :descuento")
    , @NamedQuery(name = "DetalleOrdenCompra.findBySubTotalConDescuento", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.subTotalConDescuento = :subTotalConDescuento")
    , @NamedQuery(name = "DetalleOrdenCompra.findByItbis", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.itbis = :itbis")
    , @NamedQuery(name = "DetalleOrdenCompra.findByIsr", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.isr = :isr")
    , @NamedQuery(name = "DetalleOrdenCompra.findByTotal", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.total = :total")
    , @NamedQuery(name = "DetalleOrdenCompra.findByTotalAPagar", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.totalAPagar = :totalAPagar")
    , @NamedQuery(name = "DetalleOrdenCompra.findByPendiente", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.pendiente = :pendiente")})
public class DetalleOrdenCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "descripcion_articulo")
    private String descripcionArticulo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_unitario")
    private Double precioUnitario;
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "cantidad_recibida")
    private Double cantidadRecibida;
    @Column(name = "sub_total")
    private Double subTotal;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "sub_total_con_descuento")
    private Double subTotalConDescuento;
    @Column(name = "itbis")
    private Double itbis;
    @Column(name = "isr")
    private Double isr;
    @Column(name = "total")
    private Double total;
    @Column(name = "total_a_pagar")
    private Double totalAPagar;
    @Column(name = "pendiente")
    private Double pendiente;
    @JoinColumn(name = "orden_compra", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private OrdenCompra ordenCompra;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo articulo;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne
    private Unidad unidad;

    public DetalleOrdenCompra() {
    }

    public DetalleOrdenCompra(Integer codigo) {
        this.codigo = codigo;
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

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(Double cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getSubTotalConDescuento() {
        return subTotalConDescuento;
    }

    public void setSubTotalConDescuento(Double subTotalConDescuento) {
        this.subTotalConDescuento = subTotalConDescuento;
    }

    public Double getItbis() {
        return itbis;
    }

    public void setItbis(Double itbis) {
        this.itbis = itbis;
    }

    public Double getIsr() {
        return isr;
    }

    public void setIsr(Double isr) {
        this.isr = isr;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(Double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public Double getPendiente() {
        return pendiente;
    }

    public void setPendiente(Double pendiente) {
        this.pendiente = pendiente;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
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
        if (!(object instanceof DetalleOrdenCompra)) {
            return false;
        }
        DetalleOrdenCompra other = (DetalleOrdenCompra) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleOrdenCompra[ codigo=" + codigo + " ]";
    }
    
}
