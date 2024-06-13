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
import javax.persistence.Lob;
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
@Table(name = "tipo_ncf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoNcf.findAll", query = "SELECT t FROM TipoNcf t")
    , @NamedQuery(name = "TipoNcf.findByCodigo", query = "SELECT t FROM TipoNcf t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoNcf.findByNombre", query = "SELECT t FROM TipoNcf t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoNcf.findByHabilitado", query = "SELECT t FROM TipoNcf t WHERE t.habilitado = :habilitado")
    , @NamedQuery(name = "TipoNcf.findByPrefijo", query = "SELECT t FROM TipoNcf t WHERE t.prefijo = :prefijo")})
public class TipoNcf implements Serializable {

    @Column(name = "tipo")
    private String tipo;
    @Column(name = "serie")
    private String serie;
    @Column(name = "origen")
    private String origen;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "habilitado")
    private boolean habilitado;
    @Column(name = "prefijo")
    private String prefijo;
    @OneToMany(mappedBy = "tipoNcf")
    private Collection<Cliente> clienteCollection;
    @OneToMany(mappedBy = "tipoNcf")
    private Collection<FacturaTemporal> facturaTemporalCollection;
    @OneToMany(mappedBy = "tipoNcf")
    private Collection<Factura> facturaCollection;
    @OneToMany(mappedBy = "tipoNcf")
    private Collection<RelacionNcf> relacionNcfCollection;

    public TipoNcf() {
    }

    public TipoNcf(Integer codigo) {
        this.codigo = codigo;
    }

    public TipoNcf(Integer codigo, boolean habilitado) {
        this.codigo = codigo;
        this.habilitado = habilitado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @XmlTransient
    public Collection<FacturaTemporal> getFacturaTemporalCollection() {
        return facturaTemporalCollection;
    }

    public void setFacturaTemporalCollection(Collection<FacturaTemporal> facturaTemporalCollection) {
        this.facturaTemporalCollection = facturaTemporalCollection;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    @XmlTransient
    public Collection<RelacionNcf> getRelacionNcfCollection() {
        return relacionNcfCollection;
    }

    public void setRelacionNcfCollection(Collection<RelacionNcf> relacionNcfCollection) {
        this.relacionNcfCollection = relacionNcfCollection;
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
        if (!(object instanceof TipoNcf)) {
            return false;
        }
        TipoNcf other = (TipoNcf) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TipoNcf[ codigo=" + codigo + " ]";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }


}
