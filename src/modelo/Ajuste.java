/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "ajuste")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ajuste.findAll", query = "SELECT a FROM Ajuste a")
    , @NamedQuery(name = "Ajuste.findByCodigo", query = "SELECT a FROM Ajuste a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "Ajuste.findByCantidad", query = "SELECT a FROM Ajuste a WHERE a.cantidad = :cantidad")
    , @NamedQuery(name = "Ajuste.findByExistencia", query = "SELECT a FROM Ajuste a WHERE a.existencia = :existencia")
    , @NamedQuery(name = "Ajuste.findByNuevaExistencia", query = "SELECT a FROM Ajuste a WHERE a.nuevaExistencia = :nuevaExistencia")
    , @NamedQuery(name = "Ajuste.findByPrecio", query = "SELECT a FROM Ajuste a WHERE a.precio = :precio")
    , @NamedQuery(name = "Ajuste.findByPrecioActual", query = "SELECT a FROM Ajuste a WHERE a.precioActual = :precioActual")
    , @NamedQuery(name = "Ajuste.findByNuevoPrecio", query = "SELECT a FROM Ajuste a WHERE a.nuevoPrecio = :nuevoPrecio")})
public class Ajuste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Lob
    @Column(name = "motivo")
    private String motivo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "existencia")
    private Double existencia;
    @Column(name = "nueva_existencia")
    private Double nuevaExistencia;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "precio_actual")
    private Double precioActual;
    @Column(name = "nuevo_precio")
    private Double nuevoPrecio;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo articulo;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Ajuste> ajusteCollection;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private Ajuste unidadDeNegocio;

    public Ajuste() {
    }

    public Ajuste(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getExistencia() {
        return existencia;
    }

    public void setExistencia(Double existencia) {
        this.existencia = existencia;
    }

    public Double getNuevaExistencia() {
        return nuevaExistencia;
    }

    public void setNuevaExistencia(Double nuevaExistencia) {
        this.nuevaExistencia = nuevaExistencia;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(Double precioActual) {
        this.precioActual = precioActual;
    }

    public Double getNuevoPrecio() {
        return nuevoPrecio;
    }

    public void setNuevoPrecio(Double nuevoPrecio) {
        this.nuevoPrecio = nuevoPrecio;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<Ajuste> getAjusteCollection() {
        return ajusteCollection;
    }

    public void setAjusteCollection(Collection<Ajuste> ajusteCollection) {
        this.ajusteCollection = ajusteCollection;
    }

    public Ajuste getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(Ajuste unidadDeNegocio) {
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
        if (!(object instanceof Ajuste)) {
            return false;
        }
        Ajuste other = (Ajuste) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Ajuste[ codigo=" + codigo + " ]";
    }
    
}
