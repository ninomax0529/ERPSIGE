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
@Table(name = "descuento_por_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescuentoPorUsuario.findAll", query = "SELECT d FROM DescuentoPorUsuario d")
    , @NamedQuery(name = "DescuentoPorUsuario.findByCodigo", query = "SELECT d FROM DescuentoPorUsuario d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DescuentoPorUsuario.findByFechaDesabilitado", query = "SELECT d FROM DescuentoPorUsuario d WHERE d.fechaDesabilitado = :fechaDesabilitado")
    , @NamedQuery(name = "DescuentoPorUsuario.findByFecha", query = "SELECT d FROM DescuentoPorUsuario d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "DescuentoPorUsuario.findByMinimo", query = "SELECT d FROM DescuentoPorUsuario d WHERE d.minimo = :minimo")
    , @NamedQuery(name = "DescuentoPorUsuario.findByMaximo", query = "SELECT d FROM DescuentoPorUsuario d WHERE d.maximo = :maximo")
    , @NamedQuery(name = "DescuentoPorUsuario.findByHabilitado", query = "SELECT d FROM DescuentoPorUsuario d WHERE d.habilitado = :habilitado")
    , @NamedQuery(name = "DescuentoPorUsuario.findByClave", query = "SELECT d FROM DescuentoPorUsuario d WHERE d.clave = :clave")
    , @NamedQuery(name = "DescuentoPorUsuario.findByNombreUsuario", query = "SELECT d FROM DescuentoPorUsuario d WHERE d.nombreUsuario = :nombreUsuario")})
public class DescuentoPorUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "fecha_desabilitado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesabilitado;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "minimo")
    private double minimo;
    @Basic(optional = false)
    @Column(name = "maximo")
    private double maximo;
    @Basic(optional = false)
    @Column(name = "habilitado")
    private boolean habilitado;
    @Basic(optional = false)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public DescuentoPorUsuario() {
    }

    public DescuentoPorUsuario(Integer codigo) {
        this.codigo = codigo;
    }

    public DescuentoPorUsuario(Integer codigo, Date fecha, double minimo, double maximo, boolean habilitado, String clave, String nombreUsuario) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.minimo = minimo;
        this.maximo = maximo;
        this.habilitado = habilitado;
        this.clave = clave;
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaDesabilitado() {
        return fechaDesabilitado;
    }

    public void setFechaDesabilitado(Date fechaDesabilitado) {
        this.fechaDesabilitado = fechaDesabilitado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMinimo() {
        return minimo;
    }

    public void setMinimo(double minimo) {
        this.minimo = minimo;
    }

    public double getMaximo() {
        return maximo;
    }

    public void setMaximo(double maximo) {
        this.maximo = maximo;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
        if (!(object instanceof DescuentoPorUsuario)) {
            return false;
        }
        DescuentoPorUsuario other = (DescuentoPorUsuario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DescuentoPorUsuario[ codigo=" + codigo + " ]";
    }
    
}
