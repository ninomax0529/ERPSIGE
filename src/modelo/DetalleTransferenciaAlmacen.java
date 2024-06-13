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
@Table(name = "detalle_transferencia_almacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleTransferenciaAlmacen.findAll", query = "SELECT d FROM DetalleTransferenciaAlmacen d")
    , @NamedQuery(name = "DetalleTransferenciaAlmacen.findByCodigo", query = "SELECT d FROM DetalleTransferenciaAlmacen d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleTransferenciaAlmacen.findByNombreArticulo", query = "SELECT d FROM DetalleTransferenciaAlmacen d WHERE d.nombreArticulo = :nombreArticulo")
    , @NamedQuery(name = "DetalleTransferenciaAlmacen.findByCantidad", query = "SELECT d FROM DetalleTransferenciaAlmacen d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleTransferenciaAlmacen.findByPrecio", query = "SELECT d FROM DetalleTransferenciaAlmacen d WHERE d.precio = :precio")
    , @NamedQuery(name = "DetalleTransferenciaAlmacen.findByTotal", query = "SELECT d FROM DetalleTransferenciaAlmacen d WHERE d.total = :total")
    , @NamedQuery(name = "DetalleTransferenciaAlmacen.findByNombreUnidad", query = "SELECT d FROM DetalleTransferenciaAlmacen d WHERE d.nombreUnidad = :nombreUnidad")})
public class DetalleTransferenciaAlmacen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre_articulo")
    private String nombreArticulo;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private double cantidad;
    @Basic(optional = false)
    @Column(name = "precio")
    private double precio;
    @Basic(optional = false)
    @Column(name = "total")
    private double total;
    @Basic(optional = false)
    @Column(name = "nombre_unidad")
    private String nombreUnidad;
    @JoinColumn(name = "transferencia_almacen", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TransferenciaAlmacen transferenciaAlmacen;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "almacen_destino", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Almacen almacenDestino;
    @JoinColumn(name = "almacen_origen", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Almacen almacenOrigen;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidad;

    public DetalleTransferenciaAlmacen() {
    }

    public DetalleTransferenciaAlmacen(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleTransferenciaAlmacen(Integer codigo, String nombreArticulo, double cantidad, double precio, double total, String nombreUnidad) {
        this.codigo = codigo;
        this.nombreArticulo = nombreArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
        this.nombreUnidad = nombreUnidad;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public TransferenciaAlmacen getTransferenciaAlmacen() {
        return transferenciaAlmacen;
    }

    public void setTransferenciaAlmacen(TransferenciaAlmacen transferenciaAlmacen) {
        this.transferenciaAlmacen = transferenciaAlmacen;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Almacen getAlmacenDestino() {
        return almacenDestino;
    }

    public void setAlmacenDestino(Almacen almacenDestino) {
        this.almacenDestino = almacenDestino;
    }

    public Almacen getAlmacenOrigen() {
        return almacenOrigen;
    }

    public void setAlmacenOrigen(Almacen almacenOrigen) {
        this.almacenOrigen = almacenOrigen;
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
        if (!(object instanceof DetalleTransferenciaAlmacen)) {
            return false;
        }
        DetalleTransferenciaAlmacen other = (DetalleTransferenciaAlmacen) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleTransferenciaAlmacen[ codigo=" + codigo + " ]";
    }
    
}
