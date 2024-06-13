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
@Table(name = "detalle_recepcion_articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleRecepcionArticulo.findAll", query = "SELECT d FROM DetalleRecepcionArticulo d")
    , @NamedQuery(name = "DetalleRecepcionArticulo.findByCodigo", query = "SELECT d FROM DetalleRecepcionArticulo d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleRecepcionArticulo.findByCantidad", query = "SELECT d FROM DetalleRecepcionArticulo d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleRecepcionArticulo.findByPrecioCompra", query = "SELECT d FROM DetalleRecepcionArticulo d WHERE d.precioCompra = :precioCompra")
    , @NamedQuery(name = "DetalleRecepcionArticulo.findByDescuento", query = "SELECT d FROM DetalleRecepcionArticulo d WHERE d.descuento = :descuento")
    , @NamedQuery(name = "DetalleRecepcionArticulo.findByItbis", query = "SELECT d FROM DetalleRecepcionArticulo d WHERE d.itbis = :itbis")
    , @NamedQuery(name = "DetalleRecepcionArticulo.findByTotal", query = "SELECT d FROM DetalleRecepcionArticulo d WHERE d.total = :total")})
public class DetalleRecepcionArticulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "precio_compra")
    private Double precioCompra;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "itbis")
    private Double itbis;
    @Column(name = "total")
    private Double total;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo articulo;
    @JoinColumn(name = "recepcion_articulo", referencedColumnName = "codigo")
    @ManyToOne
    private RecepcionArticulo recepcionArticulo;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne
    private Unidad unidad;

    public DetalleRecepcionArticulo() {
    }

    public DetalleRecepcionArticulo(Integer codigo) {
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

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
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

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public RecepcionArticulo getRecepcionArticulo() {
        return recepcionArticulo;
    }

    public void setRecepcionArticulo(RecepcionArticulo recepcionArticulo) {
        this.recepcionArticulo = recepcionArticulo;
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
        if (!(object instanceof DetalleRecepcionArticulo)) {
            return false;
        }
        DetalleRecepcionArticulo other = (DetalleRecepcionArticulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleRecepcionArticulo[ codigo=" + codigo + " ]";
    }
    
}
