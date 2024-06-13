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
@Table(name = "estado_contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoContrato.findAll", query = "SELECT e FROM EstadoContrato e")
    , @NamedQuery(name = "EstadoContrato.findByCodigo", query = "SELECT e FROM EstadoContrato e WHERE e.codigo = :codigo")
    , @NamedQuery(name = "EstadoContrato.findByNombre", query = "SELECT e FROM EstadoContrato e WHERE e.nombre = :nombre")})
public class EstadoContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private Collection<HistoricoEstadoDeContrato> historicoEstadoDeContratoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private Collection<ContratoDeServicio> contratoDeServicioCollection;

    public EstadoContrato() {
    }

    public EstadoContrato(Integer codigo) {
        this.codigo = codigo;
    }

    public EstadoContrato(Integer codigo, String nombre) {
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

    @XmlTransient
    public Collection<HistoricoEstadoDeContrato> getHistoricoEstadoDeContratoCollection() {
        return historicoEstadoDeContratoCollection;
    }

    public void setHistoricoEstadoDeContratoCollection(Collection<HistoricoEstadoDeContrato> historicoEstadoDeContratoCollection) {
        this.historicoEstadoDeContratoCollection = historicoEstadoDeContratoCollection;
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
        if (!(object instanceof EstadoContrato)) {
            return false;
        }
        EstadoContrato other = (EstadoContrato) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.EstadoContrato[ codigo=" + codigo + " ]";
    }
    
}
