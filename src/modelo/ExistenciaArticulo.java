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
@Table(name = "existencia_articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExistenciaArticulo.findAll", query = "SELECT e FROM ExistenciaArticulo e")
    , @NamedQuery(name = "ExistenciaArticulo.findByCodigo", query = "SELECT e FROM ExistenciaArticulo e WHERE e.codigo = :codigo")
    , @NamedQuery(name = "ExistenciaArticulo.findByExistencia", query = "SELECT e FROM ExistenciaArticulo e WHERE e.existencia = :existencia")
    , @NamedQuery(name = "ExistenciaArticulo.findByFecha", query = "SELECT e FROM ExistenciaArticulo e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "ExistenciaArticulo.findByExistenciaAnterior", query = "SELECT e FROM ExistenciaArticulo e WHERE e.existenciaAnterior = :existenciaAnterior")
    , @NamedQuery(name = "ExistenciaArticulo.findByPrecioVentaAnterior", query = "SELECT e FROM ExistenciaArticulo e WHERE e.precioVentaAnterior = :precioVentaAnterior")
    , @NamedQuery(name = "ExistenciaArticulo.findByEstado", query = "SELECT e FROM ExistenciaArticulo e WHERE e.estado = :estado")
    , @NamedQuery(name = "ExistenciaArticulo.findByUbicacion", query = "SELECT e FROM ExistenciaArticulo e WHERE e.ubicacion = :ubicacion")
    , @NamedQuery(name = "ExistenciaArticulo.findByNombreUnidadBase", query = "SELECT e FROM ExistenciaArticulo e WHERE e.nombreUnidadBase = :nombreUnidadBase")
    , @NamedQuery(name = "ExistenciaArticulo.findByNombreAlmacen", query = "SELECT e FROM ExistenciaArticulo e WHERE e.nombreAlmacen = :nombreAlmacen")})
public class ExistenciaArticulo implements Serializable {

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
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "existencia_anterior")
    private Double existenciaAnterior;
    @Column(name = "precio_venta_anterior")
    private Double precioVentaAnterior;
    @Column(name = "estado")
    private String estado;
    @Column(name = "ubicacion")
    private String ubicacion;
    @Column(name = "nombre_unidad_base")
    private String nombreUnidadBase;
    @Column(name = "nombre_almacen")
    private String nombreAlmacen;
    @JoinColumn(name = "almacen", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Almacen almacen;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne
    private Unidad unidad;

    public ExistenciaArticulo() {
    }

    public ExistenciaArticulo(Integer codigo) {
        this.codigo = codigo;
    }

    public ExistenciaArticulo(Integer codigo, double existencia, Date fecha) {
        this.codigo = codigo;
        this.existencia = existencia;
        this.fecha = fecha;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getExistenciaAnterior() {
        return existenciaAnterior;
    }

    public void setExistenciaAnterior(Double existenciaAnterior) {
        this.existenciaAnterior = existenciaAnterior;
    }

    public Double getPrecioVentaAnterior() {
        return precioVentaAnterior;
    }

    public void setPrecioVentaAnterior(Double precioVentaAnterior) {
        this.precioVentaAnterior = precioVentaAnterior;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombreUnidadBase() {
        return nombreUnidadBase;
    }

    public void setNombreUnidadBase(String nombreUnidadBase) {
        this.nombreUnidadBase = nombreUnidadBase;
    }

    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
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

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
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
        if (!(object instanceof ExistenciaArticulo)) {
            return false;
        }
        ExistenciaArticulo other = (ExistenciaArticulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ExistenciaArticulo[ codigo=" + codigo + " ]";
    }
    
}
