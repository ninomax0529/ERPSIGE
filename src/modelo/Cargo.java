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
import javax.persistence.Lob;
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
@Table(name = "cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c")
    , @NamedQuery(name = "Cargo.findByCodigo", query = "SELECT c FROM Cargo c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Cargo.findByNombre", query = "SELECT c FROM Cargo c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cargo.findByFechaRegistro", query = "SELECT c FROM Cargo c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Cargo.findByUsuario", query = "SELECT c FROM Cargo c WHERE c.usuario = :usuario")
    , @NamedQuery(name = "Cargo.findBySueldo", query = "SELECT c FROM Cargo c WHERE c.sueldo = :sueldo")})
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "sueldo")
    private double sueldo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargo")
    private Collection<EjecutivoDeVenta> ejecutivoDeVentaCollection;
    @OneToMany(mappedBy = "cargo")
    private Collection<Empleado> empleadoCollection;

    public Cargo() {
    }

    public Cargo(Integer codigo) {
        this.codigo = codigo;
    }

    public Cargo(Integer codigo, String nombre, Date fechaRegistro, int usuario, double sueldo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
        this.usuario = usuario;
        this.sueldo = sueldo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @XmlTransient
    public Collection<EjecutivoDeVenta> getEjecutivoDeVentaCollection() {
        return ejecutivoDeVentaCollection;
    }

    public void setEjecutivoDeVentaCollection(Collection<EjecutivoDeVenta> ejecutivoDeVentaCollection) {
        this.ejecutivoDeVentaCollection = ejecutivoDeVentaCollection;
    }

    @XmlTransient
    public Collection<Empleado> getEmpleadoCollection() {
        return empleadoCollection;
    }

    public void setEmpleadoCollection(Collection<Empleado> empleadoCollection) {
        this.empleadoCollection = empleadoCollection;
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
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cargo[ codigo=" + codigo + " ]";
    }
    
}
