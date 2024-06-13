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
@Table(name = "detalle_salida_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleSalidaInventario.findAll", query = "SELECT d FROM DetalleSalidaInventario d")
    , @NamedQuery(name = "DetalleSalidaInventario.findByCodigo", query = "SELECT d FROM DetalleSalidaInventario d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleSalidaInventario.findByDescripcionArticulo", query = "SELECT d FROM DetalleSalidaInventario d WHERE d.descripcionArticulo = :descripcionArticulo")
    , @NamedQuery(name = "DetalleSalidaInventario.findByCantidad", query = "SELECT d FROM DetalleSalidaInventario d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleSalidaInventario.findByExistencia", query = "SELECT d FROM DetalleSalidaInventario d WHERE d.existencia = :existencia")
    , @NamedQuery(name = "DetalleSalidaInventario.findByPrecio", query = "SELECT d FROM DetalleSalidaInventario d WHERE d.precio = :precio")
    , @NamedQuery(name = "DetalleSalidaInventario.findByValor", query = "SELECT d FROM DetalleSalidaInventario d WHERE d.valor = :valor")
    , @NamedQuery(name = "DetalleSalidaInventario.findByExistenciaAnterior", query = "SELECT d FROM DetalleSalidaInventario d WHERE d.existenciaAnterior = :existenciaAnterior")
    , @NamedQuery(name = "DetalleSalidaInventario.findByCantidadSolicitada", query = "SELECT d FROM DetalleSalidaInventario d WHERE d.cantidadSolicitada = :cantidadSolicitada")})
public class DetalleSalidaInventario implements Serializable {

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
    @Column(name = "cantidad")
    private double cantidad;
    @Basic(optional = false)
    @Column(name = "existencia")
    private double existencia;
    @Basic(optional = false)
    @Column(name = "precio")
    private double precio;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @Column(name = "existencia_anterior")
    private double existenciaAnterior;
    @Basic(optional = false)
    @Column(name = "cantidad_solicitada")
    private double cantidadSolicitada;
    @JoinColumn(name = "salida_inventario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private SalidaInventario salidaInventario;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidad;
    @JoinColumn(name = "almacen", referencedColumnName = "codigo")
    @ManyToOne
    private Almacen almacen;

    public DetalleSalidaInventario() {
    }

    public DetalleSalidaInventario(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleSalidaInventario(Integer codigo, String descripcionArticulo, double cantidad, double existencia, double precio, double valor, double existenciaAnterior, double cantidadSolicitada) {
        this.codigo = codigo;
        this.descripcionArticulo = descripcionArticulo;
        this.cantidad = cantidad;
        this.existencia = existencia;
        this.precio = precio;
        this.valor = valor;
        this.existenciaAnterior = existenciaAnterior;
        this.cantidadSolicitada = cantidadSolicitada;
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

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getExistencia() {
        return existencia;
    }

    public void setExistencia(double existencia) {
        this.existencia = existencia;
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

    public double getExistenciaAnterior() {
        return existenciaAnterior;
    }

    public void setExistenciaAnterior(double existenciaAnterior) {
        this.existenciaAnterior = existenciaAnterior;
    }

    public double getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(double cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public SalidaInventario getSalidaInventario() {
        return salidaInventario;
    }

    public void setSalidaInventario(SalidaInventario salidaInventario) {
        this.salidaInventario = salidaInventario;
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
        if (!(object instanceof DetalleSalidaInventario)) {
            return false;
        }
        DetalleSalidaInventario other = (DetalleSalidaInventario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleSalidaInventario[ codigo=" + codigo + " ]";
    }
    
}
