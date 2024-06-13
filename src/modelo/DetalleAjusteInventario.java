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
@Table(name = "detalle_ajuste_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleAjusteInventario.findAll", query = "SELECT d FROM DetalleAjusteInventario d")
    , @NamedQuery(name = "DetalleAjusteInventario.findByCodigo", query = "SELECT d FROM DetalleAjusteInventario d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleAjusteInventario.findByExistencia", query = "SELECT d FROM DetalleAjusteInventario d WHERE d.existencia = :existencia")
    , @NamedQuery(name = "DetalleAjusteInventario.findByCantidadAjustada", query = "SELECT d FROM DetalleAjusteInventario d WHERE d.cantidadAjustada = :cantidadAjustada")
    , @NamedQuery(name = "DetalleAjusteInventario.findByNuevaExistencia", query = "SELECT d FROM DetalleAjusteInventario d WHERE d.nuevaExistencia = :nuevaExistencia")
    , @NamedQuery(name = "DetalleAjusteInventario.findByCostoUnitario", query = "SELECT d FROM DetalleAjusteInventario d WHERE d.costoUnitario = :costoUnitario")
    , @NamedQuery(name = "DetalleAjusteInventario.findByCosto", query = "SELECT d FROM DetalleAjusteInventario d WHERE d.costo = :costo")
    , @NamedQuery(name = "DetalleAjusteInventario.findByDecripcionArticulo", query = "SELECT d FROM DetalleAjusteInventario d WHERE d.decripcionArticulo = :decripcionArticulo")})
public class DetalleAjusteInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "existencia")
    private double existencia;
    @Basic(optional = false)
    @Column(name = "cantidad_ajustada")
    private double cantidadAjustada;
    @Basic(optional = false)
    @Column(name = "nueva_existencia")
    private double nuevaExistencia;
    @Basic(optional = false)
    @Column(name = "costo_unitario")
    private double costoUnitario;
    @Basic(optional = false)
    @Column(name = "costo")
    private double costo;
    @Basic(optional = false)
    @Column(name = "decripcion_articulo")
    private String decripcionArticulo;
    @JoinColumn(name = "ajuste_inventario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private AjusteInventario ajusteInventario;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidad;
    @JoinColumn(name = "almacen", referencedColumnName = "codigo")
    @ManyToOne
    private Almacen almacen;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "unidad_entrada", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidadEntrada;
    @JoinColumn(name = "unidad_salida", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidadSalida;

    public DetalleAjusteInventario() {
    }

    public DetalleAjusteInventario(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleAjusteInventario(Integer codigo, double existencia, double cantidadAjustada, double nuevaExistencia, double costoUnitario, double costo, String decripcionArticulo) {
        this.codigo = codigo;
        this.existencia = existencia;
        this.cantidadAjustada = cantidadAjustada;
        this.nuevaExistencia = nuevaExistencia;
        this.costoUnitario = costoUnitario;
        this.costo = costo;
        this.decripcionArticulo = decripcionArticulo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public double getExistencia() {
        return existencia;
    }

    public void setExistencia(double existencia) {
        this.existencia = existencia;
    }

    public double getCantidadAjustada() {
        return cantidadAjustada;
    }

    public void setCantidadAjustada(double cantidadAjustada) {
        this.cantidadAjustada = cantidadAjustada;
    }

    public double getNuevaExistencia() {
        return nuevaExistencia;
    }

    public void setNuevaExistencia(double nuevaExistencia) {
        this.nuevaExistencia = nuevaExistencia;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getDecripcionArticulo() {
        return decripcionArticulo;
    }

    public void setDecripcionArticulo(String decripcionArticulo) {
        this.decripcionArticulo = decripcionArticulo;
    }

    public AjusteInventario getAjusteInventario() {
        return ajusteInventario;
    }

    public void setAjusteInventario(AjusteInventario ajusteInventario) {
        this.ajusteInventario = ajusteInventario;
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

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Unidad getUnidadEntrada() {
        return unidadEntrada;
    }

    public void setUnidadEntrada(Unidad unidadEntrada) {
        this.unidadEntrada = unidadEntrada;
    }

    public Unidad getUnidadSalida() {
        return unidadSalida;
    }

    public void setUnidadSalida(Unidad unidadSalida) {
        this.unidadSalida = unidadSalida;
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
        if (!(object instanceof DetalleAjusteInventario)) {
            return false;
        }
        DetalleAjusteInventario other = (DetalleAjusteInventario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleAjusteInventario[ codigo=" + codigo + " ]";
    }
    
}
