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
@Table(name = "detalle_cierre_mensual_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCierreMensualInventario.findAll", query = "SELECT d FROM DetalleCierreMensualInventario d")
    , @NamedQuery(name = "DetalleCierreMensualInventario.findByCodigo", query = "SELECT d FROM DetalleCierreMensualInventario d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleCierreMensualInventario.findByInventarioInicial", query = "SELECT d FROM DetalleCierreMensualInventario d WHERE d.inventarioInicial = :inventarioInicial")
    , @NamedQuery(name = "DetalleCierreMensualInventario.findByCantidadEntrada", query = "SELECT d FROM DetalleCierreMensualInventario d WHERE d.cantidadEntrada = :cantidadEntrada")
    , @NamedQuery(name = "DetalleCierreMensualInventario.findByInventarioFisico", query = "SELECT d FROM DetalleCierreMensualInventario d WHERE d.inventarioFisico = :inventarioFisico")
    , @NamedQuery(name = "DetalleCierreMensualInventario.findByCantidadAjustada", query = "SELECT d FROM DetalleCierreMensualInventario d WHERE d.cantidadAjustada = :cantidadAjustada")
    , @NamedQuery(name = "DetalleCierreMensualInventario.findByDiferencia", query = "SELECT d FROM DetalleCierreMensualInventario d WHERE d.diferencia = :diferencia")
    , @NamedQuery(name = "DetalleCierreMensualInventario.findByUbicacion", query = "SELECT d FROM DetalleCierreMensualInventario d WHERE d.ubicacion = :ubicacion")
    , @NamedQuery(name = "DetalleCierreMensualInventario.findByCantidadSalida", query = "SELECT d FROM DetalleCierreMensualInventario d WHERE d.cantidadSalida = :cantidadSalida")
    , @NamedQuery(name = "DetalleCierreMensualInventario.findByInventarioFinal", query = "SELECT d FROM DetalleCierreMensualInventario d WHERE d.inventarioFinal = :inventarioFinal")
    , @NamedQuery(name = "DetalleCierreMensualInventario.findByUnidadSalida", query = "SELECT d FROM DetalleCierreMensualInventario d WHERE d.unidadSalida = :unidadSalida")
    , @NamedQuery(name = "DetalleCierreMensualInventario.findByDescripcionArticulo", query = "SELECT d FROM DetalleCierreMensualInventario d WHERE d.descripcionArticulo = :descripcionArticulo")
    , @NamedQuery(name = "DetalleCierreMensualInventario.findByCostoUnitario", query = "SELECT d FROM DetalleCierreMensualInventario d WHERE d.costoUnitario = :costoUnitario")
    , @NamedQuery(name = "DetalleCierreMensualInventario.findByCosto", query = "SELECT d FROM DetalleCierreMensualInventario d WHERE d.costo = :costo")
    , @NamedQuery(name = "DetalleCierreMensualInventario.findByPrecioVenta", query = "SELECT d FROM DetalleCierreMensualInventario d WHERE d.precioVenta = :precioVenta")})
public class DetalleCierreMensualInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "inventario_inicial")
    private double inventarioInicial;
    @Basic(optional = false)
    @Column(name = "cantidad_entrada")
    private double cantidadEntrada;
    @Basic(optional = false)
    @Column(name = "inventario_fisico")
    private double inventarioFisico;
    @Basic(optional = false)
    @Column(name = "cantidad_ajustada")
    private double cantidadAjustada;
    @Basic(optional = false)
    @Column(name = "diferencia")
    private double diferencia;
    @Basic(optional = false)
    @Column(name = "ubicacion")
    private int ubicacion;
    @Basic(optional = false)
    @Column(name = "cantidad_salida")
    private double cantidadSalida;
    @Basic(optional = false)
    @Column(name = "inventario_final")
    private double inventarioFinal;
    @Basic(optional = false)
    @Column(name = "unidad_salida")
    private int unidadSalida;
    @Basic(optional = false)
    @Column(name = "descripcion_articulo")
    private String descripcionArticulo;
    @Basic(optional = false)
    @Column(name = "costo_unitario")
    private double costoUnitario;
    @Basic(optional = false)
    @Column(name = "costo")
    private double costo;
    @Basic(optional = false)
    @Column(name = "precio_venta")
    private double precioVenta;
    @JoinColumn(name = "cierre_mensual", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private CierreMensualInventario cierreMensual;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "almacen", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Almacen almacen;

    public DetalleCierreMensualInventario() {
    }

    public DetalleCierreMensualInventario(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleCierreMensualInventario(Integer codigo, double inventarioInicial, double cantidadEntrada, double inventarioFisico, double cantidadAjustada, double diferencia, int ubicacion, double cantidadSalida, double inventarioFinal, int unidadSalida, String descripcionArticulo, double costoUnitario, double costo, double precioVenta) {
        this.codigo = codigo;
        this.inventarioInicial = inventarioInicial;
        this.cantidadEntrada = cantidadEntrada;
        this.inventarioFisico = inventarioFisico;
        this.cantidadAjustada = cantidadAjustada;
        this.diferencia = diferencia;
        this.ubicacion = ubicacion;
        this.cantidadSalida = cantidadSalida;
        this.inventarioFinal = inventarioFinal;
        this.unidadSalida = unidadSalida;
        this.descripcionArticulo = descripcionArticulo;
        this.costoUnitario = costoUnitario;
        this.costo = costo;
        this.precioVenta = precioVenta;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public double getInventarioInicial() {
        return inventarioInicial;
    }

    public void setInventarioInicial(double inventarioInicial) {
        this.inventarioInicial = inventarioInicial;
    }

    public double getCantidadEntrada() {
        return cantidadEntrada;
    }

    public void setCantidadEntrada(double cantidadEntrada) {
        this.cantidadEntrada = cantidadEntrada;
    }

    public double getInventarioFisico() {
        return inventarioFisico;
    }

    public void setInventarioFisico(double inventarioFisico) {
        this.inventarioFisico = inventarioFisico;
    }

    public double getCantidadAjustada() {
        return cantidadAjustada;
    }

    public void setCantidadAjustada(double cantidadAjustada) {
        this.cantidadAjustada = cantidadAjustada;
    }

    public double getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(double diferencia) {
        this.diferencia = diferencia;
    }

    public int getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getCantidadSalida() {
        return cantidadSalida;
    }

    public void setCantidadSalida(double cantidadSalida) {
        this.cantidadSalida = cantidadSalida;
    }

    public double getInventarioFinal() {
        return inventarioFinal;
    }

    public void setInventarioFinal(double inventarioFinal) {
        this.inventarioFinal = inventarioFinal;
    }

    public int getUnidadSalida() {
        return unidadSalida;
    }

    public void setUnidadSalida(int unidadSalida) {
        this.unidadSalida = unidadSalida;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
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

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public CierreMensualInventario getCierreMensual() {
        return cierreMensual;
    }

    public void setCierreMensual(CierreMensualInventario cierreMensual) {
        this.cierreMensual = cierreMensual;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
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
        if (!(object instanceof DetalleCierreMensualInventario)) {
            return false;
        }
        DetalleCierreMensualInventario other = (DetalleCierreMensualInventario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleCierreMensualInventario[ codigo=" + codigo + " ]";
    }
    
}
