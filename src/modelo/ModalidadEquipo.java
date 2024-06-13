/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "modalidad_equipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModalidadEquipo.findAll", query = "SELECT m FROM ModalidadEquipo m")
    , @NamedQuery(name = "ModalidadEquipo.findByCodigo", query = "SELECT m FROM ModalidadEquipo m WHERE m.codigo = :codigo")
    , @NamedQuery(name = "ModalidadEquipo.findByNombre", query = "SELECT m FROM ModalidadEquipo m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "ModalidadEquipo.findByHabilitada", query = "SELECT m FROM ModalidadEquipo m WHERE m.habilitada = :habilitada")})
public class ModalidadEquipo implements Serializable {

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
    @Column(name = "habilitada")
    private boolean habilitada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modalidad")
    private Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection;

    public ModalidadEquipo() {
    }

    public ModalidadEquipo(Integer codigo) {
        this.codigo = codigo;
    }

    public ModalidadEquipo(Integer codigo, String nombre, boolean habilitada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.habilitada = habilitada;
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

    public boolean getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    @XmlTransient
    public Collection<DetalleContratoDeServicio> getDetalleContratoDeServicioCollection() {
        return detalleContratoDeServicioCollection;
    }

    public void setDetalleContratoDeServicioCollection(Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection) {
        this.detalleContratoDeServicioCollection = detalleContratoDeServicioCollection;
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
        if (!(object instanceof ModalidadEquipo)) {
            return false;
        }
        ModalidadEquipo other = (ModalidadEquipo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ModalidadEquipo[ codigo=" + codigo + " ]";
    }
    
}
