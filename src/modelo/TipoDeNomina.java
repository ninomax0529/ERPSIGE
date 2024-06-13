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
@Table(name = "tipo_de_nomina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDeNomina.findAll", query = "SELECT t FROM TipoDeNomina t")
    , @NamedQuery(name = "TipoDeNomina.findByCodigo", query = "SELECT t FROM TipoDeNomina t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoDeNomina.findByNombre", query = "SELECT t FROM TipoDeNomina t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoDeNomina.findByFechaRegistro", query = "SELECT t FROM TipoDeNomina t WHERE t.fechaRegistro = :fechaRegistro")})
public class TipoDeNomina implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoNomina")
    private Collection<Nomina> nominaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoNomina")
    private Collection<Empleado> empleadoCollection;

    public TipoDeNomina() {
    }

    public TipoDeNomina(Integer codigo) {
        this.codigo = codigo;
    }

    public TipoDeNomina(Integer codigo, String nombre, Date fechaRegistro) {
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

    @XmlTransient
    public Collection<Nomina> getNominaCollection() {
        return nominaCollection;
    }

    public void setNominaCollection(Collection<Nomina> nominaCollection) {
        this.nominaCollection = nominaCollection;
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
        if (!(object instanceof TipoDeNomina)) {
            return false;
        }
        TipoDeNomina other = (TipoDeNomina) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TipoDeNomina[ codigo=" + codigo + " ]";
    }
    
}
