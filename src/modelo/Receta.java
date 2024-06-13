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
import javax.persistence.CascadeType;
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
@Table(name = "receta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receta.findAll", query = "SELECT r FROM Receta r")
    , @NamedQuery(name = "Receta.findByCodigo", query = "SELECT r FROM Receta r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "Receta.findByDescripcion", query = "SELECT r FROM Receta r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "Receta.findByCantidad", query = "SELECT r FROM Receta r WHERE r.cantidad = :cantidad")
    , @NamedQuery(name = "Receta.findByCosto", query = "SELECT r FROM Receta r WHERE r.costo = :costo")
    , @NamedQuery(name = "Receta.findByCostoConItbis", query = "SELECT r FROM Receta r WHERE r.costoConItbis = :costoConItbis")
    , @NamedQuery(name = "Receta.findByCostoUnitario", query = "SELECT r FROM Receta r WHERE r.costoUnitario = :costoUnitario")
    , @NamedQuery(name = "Receta.findByCostoUnitarioConItbis", query = "SELECT r FROM Receta r WHERE r.costoUnitarioConItbis = :costoUnitarioConItbis")
    , @NamedQuery(name = "Receta.findByFecha", query = "SELECT r FROM Receta r WHERE r.fecha = :fecha")})
public class Receta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private double cantidad;
    @Basic(optional = false)
    @Column(name = "costo")
    private double costo;
    @Basic(optional = false)
    @Column(name = "costo_con_itbis")
    private double costoConItbis;
    @Basic(optional = false)
    @Column(name = "costo_unitario")
    private double costoUnitario;
    @Basic(optional = false)
    @Column(name = "costo_unitario_con_itbis")
    private double costoUnitarioConItbis;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receta")
    private Collection<DetalleReceta> detalleRecetaCollection;
    @JoinColumn(name = "producto", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo producto;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidad;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Receta() {
    }

    public Receta(Integer codigo) {
        this.codigo = codigo;
    }

    public Receta(Integer codigo, String descripcion, double cantidad, double costo, double costoConItbis, double costoUnitario, double costoUnitarioConItbis, Date fecha) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.costo = costo;
        this.costoConItbis = costoConItbis;
        this.costoUnitario = costoUnitario;
        this.costoUnitarioConItbis = costoUnitarioConItbis;
        this.fecha = fecha;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getCostoConItbis() {
        return costoConItbis;
    }

    public void setCostoConItbis(double costoConItbis) {
        this.costoConItbis = costoConItbis;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public double getCostoUnitarioConItbis() {
        return costoUnitarioConItbis;
    }

    public void setCostoUnitarioConItbis(double costoUnitarioConItbis) {
        this.costoUnitarioConItbis = costoUnitarioConItbis;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public Collection<DetalleReceta> getDetalleRecetaCollection() {
        return detalleRecetaCollection;
    }

    public void setDetalleRecetaCollection(Collection<DetalleReceta> detalleRecetaCollection) {
        this.detalleRecetaCollection = detalleRecetaCollection;
    }

    public Articulo getProducto() {
        return producto;
    }

    public void setProducto(Articulo producto) {
        this.producto = producto;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof Receta)) {
            return false;
        }
        Receta other = (Receta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Receta[ codigo=" + codigo + " ]";
    }
    
}
