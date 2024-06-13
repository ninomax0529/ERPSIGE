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
@Table(name = "chofer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chofer.findAll", query = "SELECT c FROM Chofer c")
    , @NamedQuery(name = "Chofer.findByCodigo", query = "SELECT c FROM Chofer c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Chofer.findByNombre", query = "SELECT c FROM Chofer c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Chofer.findByFechaRegistro", query = "SELECT c FROM Chofer c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Chofer.findByEmpleado", query = "SELECT c FROM Chofer c WHERE c.empleado = :empleado")})
public class Chofer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "empleado")
    private Integer empleado;
    @OneToMany(mappedBy = "chofer")
    private Collection<Conduce> conduceCollection;

    public Chofer() {
    }

    public Chofer(Integer codigo) {
        this.codigo = codigo;
    }

    public Chofer(Integer codigo, String nombre, Date fechaRegistro) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Integer empleado) {
        this.empleado = empleado;
    }

    @XmlTransient
    public Collection<Conduce> getConduceCollection() {
        return conduceCollection;
    }

    public void setConduceCollection(Collection<Conduce> conduceCollection) {
        this.conduceCollection = conduceCollection;
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
        if (!(object instanceof Chofer)) {
            return false;
        }
        Chofer other = (Chofer) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Chofer[ codigo=" + codigo + " ]";
    }
    
}
