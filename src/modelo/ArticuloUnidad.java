/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "articulo_unidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArticuloUnidad.findAll", query = "SELECT a FROM ArticuloUnidad a")
    , @NamedQuery(name = "ArticuloUnidad.findByCodigo", query = "SELECT a FROM ArticuloUnidad a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "ArticuloUnidad.findByCantidadUnidades", query = "SELECT a FROM ArticuloUnidad a WHERE a.cantidadUnidades = :cantidadUnidades")
    , @NamedQuery(name = "ArticuloUnidad.findByCostoUnitario", query = "SELECT a FROM ArticuloUnidad a WHERE a.costoUnitario = :costoUnitario")
    , @NamedQuery(name = "ArticuloUnidad.findByUnidadInventario", query = "SELECT a FROM ArticuloUnidad a WHERE a.unidadInventario = :unidadInventario")
    , @NamedQuery(name = "ArticuloUnidad.findByFatorVenta", query = "SELECT a FROM ArticuloUnidad a WHERE a.fatorVenta = :fatorVenta")
    , @NamedQuery(name = "ArticuloUnidad.findByUnidadEntrada", query = "SELECT a FROM ArticuloUnidad a WHERE a.unidadEntrada = :unidadEntrada")
    , @NamedQuery(name = "ArticuloUnidad.findByNombreUe", query = "SELECT a FROM ArticuloUnidad a WHERE a.nombreUe = :nombreUe")
    , @NamedQuery(name = "ArticuloUnidad.findByPrecioVenta", query = "SELECT a FROM ArticuloUnidad a WHERE a.precioVenta = :precioVenta")})
public class ArticuloUnidad implements Serializable {

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "cantidad_unidades")
    private double cantidadUnidades;
    @Basic(optional = false)
    @Column(name = "costo_unitario")
    private double costoUnitario;
    @Basic(optional = false)
    @Column(name = "unidad_inventario")
    private boolean unidadInventario;
    @Basic(optional = false)
    @Column(name = "fator_venta")
    private double fatorVenta;
    @Basic(optional = false)
    @Column(name = "unidad_entrada")
    private int unidadEntrada;
    @Basic(optional = false)
    @Column(name = "nombre_ue")
    private String nombreUe;
    @Basic(optional = false)
    @Column(name = "precio_venta")
    private double precioVenta;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidad;
    @OneToMany(mappedBy = "unidadEntrada")
    private Collection<DetalleDevolucionDeInventario> detalleDevolucionDeInventarioCollection;

    public ArticuloUnidad() {
    }

    public ArticuloUnidad(Integer codigo) {
        this.codigo = codigo;
    }

    public ArticuloUnidad(Integer codigo, double cantidadUnidades, double costoUnitario, boolean unidadInventario, double fatorVenta, int unidadEntrada, String nombreUe, double precioVenta) {
        this.codigo = codigo;
        this.cantidadUnidades = cantidadUnidades;
        this.costoUnitario = costoUnitario;
        this.unidadInventario = unidadInventario;
        this.fatorVenta = fatorVenta;
        this.unidadEntrada = unidadEntrada;
        this.nombreUe = nombreUe;
        this.precioVenta = precioVenta;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public double getCantidadUnidades() {
        return cantidadUnidades;
    }

    public void setCantidadUnidades(double cantidadUnidades) {
        this.cantidadUnidades = cantidadUnidades;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public boolean getUnidadInventario() {
        return unidadInventario;
    }

    public void setUnidadInventario(boolean unidadInventario) {
        this.unidadInventario = unidadInventario;
    }

    public double getFatorVenta() {
        return fatorVenta;
    }

    public void setFatorVenta(double fatorVenta) {
        this.fatorVenta = fatorVenta;
    }

    public int getUnidadEntrada() {
        return unidadEntrada;
    }

    public void setUnidadEntrada(int unidadEntrada) {
        this.unidadEntrada = unidadEntrada;
    }

    public String getNombreUe() {
        return nombreUe;
    }

    public void setNombreUe(String nombreUe) {
        this.nombreUe = nombreUe;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
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

    @XmlTransient
    public Collection<DetalleDevolucionDeInventario> getDetalleDevolucionDeInventarioCollection() {
        return detalleDevolucionDeInventarioCollection;
    }

    public void setDetalleDevolucionDeInventarioCollection(Collection<DetalleDevolucionDeInventario> detalleDevolucionDeInventarioCollection) {
        this.detalleDevolucionDeInventarioCollection = detalleDevolucionDeInventarioCollection;
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
        if (!(object instanceof ArticuloUnidad)) {
            return false;
        }
        ArticuloUnidad other = (ArticuloUnidad) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ArticuloUnidad[ codigo=" + codigo + " ]";
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}
