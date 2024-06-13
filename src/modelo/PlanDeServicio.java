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
@Table(name = "plan_de_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanDeServicio.findAll", query = "SELECT p FROM PlanDeServicio p")
    , @NamedQuery(name = "PlanDeServicio.findByCodigo", query = "SELECT p FROM PlanDeServicio p WHERE p.codigo = :codigo")
    , @NamedQuery(name = "PlanDeServicio.findByNombre", query = "SELECT p FROM PlanDeServicio p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "PlanDeServicio.findByPrecio", query = "SELECT p FROM PlanDeServicio p WHERE p.precio = :precio")
    , @NamedQuery(name = "PlanDeServicio.findByFecha", query = "SELECT p FROM PlanDeServicio p WHERE p.fecha = :fecha")})
public class PlanDeServicio implements Serializable {

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
    @Column(name = "precio")
    private double precio;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planDeServicio")
    private Collection<ContratoDeServicio> contratoDeServicioCollection;

    public PlanDeServicio() {
    }

    public PlanDeServicio(Integer codigo) {
        this.codigo = codigo;
    }

    public PlanDeServicio(Integer codigo, String nombre, double precio, Date fecha) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.fecha = fecha;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public Collection<ContratoDeServicio> getContratoDeServicioCollection() {
        return contratoDeServicioCollection;
    }

    public void setContratoDeServicioCollection(Collection<ContratoDeServicio> contratoDeServicioCollection) {
        this.contratoDeServicioCollection = contratoDeServicioCollection;
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
        if (!(object instanceof PlanDeServicio)) {
            return false;
        }
        PlanDeServicio other = (PlanDeServicio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.PlanDeServicio[ codigo=" + codigo + " ]";
    }
    
}
