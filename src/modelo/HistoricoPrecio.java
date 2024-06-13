/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "historico_precio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoricoPrecio.findAll", query = "SELECT h FROM HistoricoPrecio h")
    , @NamedQuery(name = "HistoricoPrecio.findByCodigo", query = "SELECT h FROM HistoricoPrecio h WHERE h.codigo = :codigo")
    , @NamedQuery(name = "HistoricoPrecio.findByDocumento", query = "SELECT h FROM HistoricoPrecio h WHERE h.documento = :documento")
    , @NamedQuery(name = "HistoricoPrecio.findByTipoDocumento", query = "SELECT h FROM HistoricoPrecio h WHERE h.tipoDocumento = :tipoDocumento")
    , @NamedQuery(name = "HistoricoPrecio.findByFecha", query = "SELECT h FROM HistoricoPrecio h WHERE h.fecha = :fecha")
    , @NamedQuery(name = "HistoricoPrecio.findByArticulo", query = "SELECT h FROM HistoricoPrecio h WHERE h.articulo = :articulo")
    , @NamedQuery(name = "HistoricoPrecio.findByPrecioVenta", query = "SELECT h FROM HistoricoPrecio h WHERE h.precioVenta = :precioVenta")
    , @NamedQuery(name = "HistoricoPrecio.findByPrecioCompra", query = "SELECT h FROM HistoricoPrecio h WHERE h.precioCompra = :precioCompra")
    , @NamedQuery(name = "HistoricoPrecio.findByPorcientoUtilidad", query = "SELECT h FROM HistoricoPrecio h WHERE h.porcientoUtilidad = :porcientoUtilidad")
    , @NamedQuery(name = "HistoricoPrecio.findByMargenUtilidad", query = "SELECT h FROM HistoricoPrecio h WHERE h.margenUtilidad = :margenUtilidad")
    , @NamedQuery(name = "HistoricoPrecio.findByPrecioVentaAnterior", query = "SELECT h FROM HistoricoPrecio h WHERE h.precioVentaAnterior = :precioVentaAnterior")
    , @NamedQuery(name = "HistoricoPrecio.findByPrecioCompraAnterior", query = "SELECT h FROM HistoricoPrecio h WHERE h.precioCompraAnterior = :precioCompraAnterior")
    , @NamedQuery(name = "HistoricoPrecio.findByPorcientoUtilidadAnterior", query = "SELECT h FROM HistoricoPrecio h WHERE h.porcientoUtilidadAnterior = :porcientoUtilidadAnterior")
    , @NamedQuery(name = "HistoricoPrecio.findByMargenUtilidadAnterior", query = "SELECT h FROM HistoricoPrecio h WHERE h.margenUtilidadAnterior = :margenUtilidadAnterior")
    , @NamedQuery(name = "HistoricoPrecio.findByUsuario", query = "SELECT h FROM HistoricoPrecio h WHERE h.usuario = :usuario")})
public class HistoricoPrecio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "documento")
    private int documento;
    @Basic(optional = false)
    @Column(name = "tipo_documento")
    private int tipoDocumento;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "articulo")
    private int articulo;
    @Basic(optional = false)
    @Column(name = "precio_venta")
    private double precioVenta;
    @Basic(optional = false)
    @Column(name = "precio_compra")
    private double precioCompra;
    @Basic(optional = false)
    @Column(name = "porciento_utilidad")
    private double porcientoUtilidad;
    @Basic(optional = false)
    @Column(name = "margen_utilidad")
    private double margenUtilidad;
    @Basic(optional = false)
    @Column(name = "precio_venta_anterior")
    private double precioVentaAnterior;
    @Basic(optional = false)
    @Column(name = "precio_compra_anterior")
    private double precioCompraAnterior;
    @Basic(optional = false)
    @Column(name = "porciento_utilidad_anterior")
    private double porcientoUtilidadAnterior;
    @Basic(optional = false)
    @Column(name = "margen_utilidad_anterior")
    private double margenUtilidadAnterior;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

    public HistoricoPrecio() {
    }

    public HistoricoPrecio(Integer codigo) {
        this.codigo = codigo;
    }

    public HistoricoPrecio(Integer codigo, int documento, int tipoDocumento, Date fecha, int articulo, double precioVenta, double precioCompra, double porcientoUtilidad, double margenUtilidad, double precioVentaAnterior, double precioCompraAnterior, double porcientoUtilidadAnterior, double margenUtilidadAnterior, int usuario) {
        this.codigo = codigo;
        this.documento = documento;
        this.tipoDocumento = tipoDocumento;
        this.fecha = fecha;
        this.articulo = articulo;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.porcientoUtilidad = porcientoUtilidad;
        this.margenUtilidad = margenUtilidad;
        this.precioVentaAnterior = precioVentaAnterior;
        this.precioCompraAnterior = precioCompraAnterior;
        this.porcientoUtilidadAnterior = porcientoUtilidadAnterior;
        this.margenUtilidadAnterior = margenUtilidadAnterior;
        this.usuario = usuario;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getArticulo() {
        return articulo;
    }

    public void setArticulo(int articulo) {
        this.articulo = articulo;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPorcientoUtilidad() {
        return porcientoUtilidad;
    }

    public void setPorcientoUtilidad(double porcientoUtilidad) {
        this.porcientoUtilidad = porcientoUtilidad;
    }

    public double getMargenUtilidad() {
        return margenUtilidad;
    }

    public void setMargenUtilidad(double margenUtilidad) {
        this.margenUtilidad = margenUtilidad;
    }

    public double getPrecioVentaAnterior() {
        return precioVentaAnterior;
    }

    public void setPrecioVentaAnterior(double precioVentaAnterior) {
        this.precioVentaAnterior = precioVentaAnterior;
    }

    public double getPrecioCompraAnterior() {
        return precioCompraAnterior;
    }

    public void setPrecioCompraAnterior(double precioCompraAnterior) {
        this.precioCompraAnterior = precioCompraAnterior;
    }

    public double getPorcientoUtilidadAnterior() {
        return porcientoUtilidadAnterior;
    }

    public void setPorcientoUtilidadAnterior(double porcientoUtilidadAnterior) {
        this.porcientoUtilidadAnterior = porcientoUtilidadAnterior;
    }

    public double getMargenUtilidadAnterior() {
        return margenUtilidadAnterior;
    }

    public void setMargenUtilidadAnterior(double margenUtilidadAnterior) {
        this.margenUtilidadAnterior = margenUtilidadAnterior;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
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
        if (!(object instanceof HistoricoPrecio)) {
            return false;
        }
        HistoricoPrecio other = (HistoricoPrecio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.HistoricoPrecio[ codigo=" + codigo + " ]";
    }
    
}
