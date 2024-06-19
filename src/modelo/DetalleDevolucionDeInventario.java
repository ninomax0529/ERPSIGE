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
@Table(name = "detalle_devolucion_de_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleDevolucionDeInventario.findAll", query = "SELECT d FROM DetalleDevolucionDeInventario d")
    , @NamedQuery(name = "DetalleDevolucionDeInventario.findByCodigo", query = "SELECT d FROM DetalleDevolucionDeInventario d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleDevolucionDeInventario.findByUnidadSalida", query = "SELECT d FROM DetalleDevolucionDeInventario d WHERE d.unidadSalida = :unidadSalida")
    , @NamedQuery(name = "DetalleDevolucionDeInventario.findByCantidad", query = "SELECT d FROM DetalleDevolucionDeInventario d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleDevolucionDeInventario.findByCostoUnitario", query = "SELECT d FROM DetalleDevolucionDeInventario d WHERE d.costoUnitario = :costoUnitario")
    , @NamedQuery(name = "DetalleDevolucionDeInventario.findByCosto", query = "SELECT d FROM DetalleDevolucionDeInventario d WHERE d.costo = :costo")
    , @NamedQuery(name = "DetalleDevolucionDeInventario.findByExistenciaActual", query = "SELECT d FROM DetalleDevolucionDeInventario d WHERE d.existenciaActual = :existenciaActual")
    , @NamedQuery(name = "DetalleDevolucionDeInventario.findByNuevaExistencia", query = "SELECT d FROM DetalleDevolucionDeInventario d WHERE d.nuevaExistencia = :nuevaExistencia")
    , @NamedQuery(name = "DetalleDevolucionDeInventario.findByDescripcionArticulo", query = "SELECT d FROM DetalleDevolucionDeInventario d WHERE d.descripcionArticulo = :descripcionArticulo")
    , @NamedQuery(name = "DetalleDevolucionDeInventario.findByDescripcionUnidadEntrada", query = "SELECT d FROM DetalleDevolucionDeInventario d WHERE d.descripcionUnidadEntrada = :descripcionUnidadEntrada")})
public class DetalleDevolucionDeInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "unidad_salida")
    private Integer unidadSalida;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "costo_unitario")
    private Double costoUnitario;
    @Column(name = "costo")
    private Double costo;
    @Column(name = "existencia_actual")
    private Double existenciaActual;
    @Column(name = "nueva_existencia")
    private Double nuevaExistencia;
    @Column(name = "descripcion_articulo")
    private String descripcionArticulo;
    @Column(name = "descripcion_unidad_entrada")
    private String descripcionUnidadEntrada;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo articulo;
    @JoinColumn(name = "devolucion_de_inventario", referencedColumnName = "codigo")
    @ManyToOne
    private DevolucionDeInventario devolucionDeInventario;
    @JoinColumn(name = "almacen", referencedColumnName = "codigo")
    @ManyToOne
    private Almacen almacen;
    @JoinColumn(name = "unidad_entrada", referencedColumnName = "codigo")
    @ManyToOne
    private Unidad unidadEntrada;

    public DetalleDevolucionDeInventario() {
    }

    public DetalleDevolucionDeInventario(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getUnidadSalida() {
        return unidadSalida;
    }

    public void setUnidadSalida(Integer unidadSalida) {
        this.unidadSalida = unidadSalida;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getExistenciaActual() {
        return existenciaActual;
    }

    public void setExistenciaActual(Double existenciaActual) {
        this.existenciaActual = existenciaActual;
    }

    public Double getNuevaExistencia() {
        return nuevaExistencia;
    }

    public void setNuevaExistencia(Double nuevaExistencia) {
        this.nuevaExistencia = nuevaExistencia;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public String getDescripcionUnidadEntrada() {
        return descripcionUnidadEntrada;
    }

    public void setDescripcionUnidadEntrada(String descripcionUnidadEntrada) {
        this.descripcionUnidadEntrada = descripcionUnidadEntrada;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public DevolucionDeInventario getDevolucionDeInventario() {
        return devolucionDeInventario;
    }

    public void setDevolucionDeInventario(DevolucionDeInventario devolucionDeInventario) {
        this.devolucionDeInventario = devolucionDeInventario;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public Unidad getUnidadEntrada() {
        return unidadEntrada;
    }

    public void setUnidadEntrada(Unidad unidadEntrada) {
        this.unidadEntrada = unidadEntrada;
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
        if (!(object instanceof DetalleDevolucionDeInventario)) {
            return false;
        }
        DetalleDevolucionDeInventario other = (DetalleDevolucionDeInventario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleDevolucionDeInventario[ codigo=" + codigo + " ]";
    }
    
}
