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
@Table(name = "registro_lote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroLote.findAll", query = "SELECT r FROM RegistroLote r")
    , @NamedQuery(name = "RegistroLote.findByCodigo", query = "SELECT r FROM RegistroLote r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "RegistroLote.findByFechaRegistro", query = "SELECT r FROM RegistroLote r WHERE r.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "RegistroLote.findByFecha", query = "SELECT r FROM RegistroLote r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "RegistroLote.findByNumeroDeLote", query = "SELECT r FROM RegistroLote r WHERE r.numeroDeLote = :numeroDeLote")
    , @NamedQuery(name = "RegistroLote.findByProduccion", query = "SELECT r FROM RegistroLote r WHERE r.produccion = :produccion")
    , @NamedQuery(name = "RegistroLote.findByCapacidad", query = "SELECT r FROM RegistroLote r WHERE r.capacidad = :capacidad")})
public class RegistroLote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "numero_de_lote")
    private int numeroDeLote;
    @Basic(optional = false)
    @Column(name = "produccion")
    private double produccion;
    @Column(name = "capacidad")
    private String capacidad;
    @JoinColumn(name = "producto", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo producto;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidad;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public RegistroLote() {
    }

    public RegistroLote(Integer codigo) {
        this.codigo = codigo;
    }

    public RegistroLote(Integer codigo, Date fechaRegistro, Date fecha, int numeroDeLote, double produccion) {
        this.codigo = codigo;
        this.fechaRegistro = fechaRegistro;
        this.fecha = fecha;
        this.numeroDeLote = numeroDeLote;
        this.produccion = produccion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumeroDeLote() {
        return numeroDeLote;
    }

    public void setNumeroDeLote(int numeroDeLote) {
        this.numeroDeLote = numeroDeLote;
    }

    public double getProduccion() {
        return produccion;
    }

    public void setProduccion(double produccion) {
        this.produccion = produccion;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public Articulo getProducto() {
        return producto;
    }

    public void setProducto(Articulo producto) {
        this.producto = producto;
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
        if (!(object instanceof RegistroLote)) {
            return false;
        }
        RegistroLote other = (RegistroLote) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RegistroLote[ codigo=" + codigo + " ]";
    }
    
}
