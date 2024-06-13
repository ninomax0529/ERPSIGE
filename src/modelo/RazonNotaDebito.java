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
@Table(name = "razon_nota_debito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RazonNotaDebito.findAll", query = "SELECT r FROM RazonNotaDebito r")
    , @NamedQuery(name = "RazonNotaDebito.findByCodigo", query = "SELECT r FROM RazonNotaDebito r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "RazonNotaDebito.findByNombre", query = "SELECT r FROM RazonNotaDebito r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RazonNotaDebito.findByFechaRegistro", query = "SELECT r FROM RazonNotaDebito r WHERE r.fechaRegistro = :fechaRegistro")})
public class RazonNotaDebito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @OneToMany(mappedBy = "razonNotaDebito")
    private Collection<NotaDebito> notaDebitoCollection;

    public RazonNotaDebito() {
    }

    public RazonNotaDebito(Integer codigo) {
        this.codigo = codigo;
    }

    public RazonNotaDebito(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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
    public Collection<NotaDebito> getNotaDebitoCollection() {
        return notaDebitoCollection;
    }

    public void setNotaDebitoCollection(Collection<NotaDebito> notaDebitoCollection) {
        this.notaDebitoCollection = notaDebitoCollection;
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
        if (!(object instanceof RazonNotaDebito)) {
            return false;
        }
        RazonNotaDebito other = (RazonNotaDebito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RazonNotaDebito[ codigo=" + codigo + " ]";
    }
    
}
