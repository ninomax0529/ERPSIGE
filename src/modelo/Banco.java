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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "banco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banco.findAll", query = "SELECT b FROM Banco b")
    , @NamedQuery(name = "Banco.findByCodigo", query = "SELECT b FROM Banco b WHERE b.codigo = :codigo")
    , @NamedQuery(name = "Banco.findByNombre", query = "SELECT b FROM Banco b WHERE b.nombre = :nombre")
    , @NamedQuery(name = "Banco.findByEstado", query = "SELECT b FROM Banco b WHERE b.estado = :estado")
    , @NamedQuery(name = "Banco.findBySucursal", query = "SELECT b FROM Banco b WHERE b.sucursal = :sucursal")
    , @NamedQuery(name = "Banco.findByContacto", query = "SELECT b FROM Banco b WHERE b.contacto = :contacto")
    , @NamedQuery(name = "Banco.findByTelefonoContacto", query = "SELECT b FROM Banco b WHERE b.telefonoContacto = :telefonoContacto")
    , @NamedQuery(name = "Banco.findByDireccion", query = "SELECT b FROM Banco b WHERE b.direccion = :direccion")})
public class Banco implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "banco")
    private Collection<ConciliacionBancaria> conciliacionBancariaCollection;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "banco")
    private Collection<MovimientoBanco> movimientoBancoCollection;

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
    @Column(name = "estado")
    private boolean estado;
    @Column(name = "sucursal")
    private String sucursal;
    @Column(name = "contacto")
    private String contacto;
    @Column(name = "telefono_contacto")
    private String telefonoContacto;
    @Column(name = "direccion")
    private String direccion;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "banco")
    private Collection<CuentaBanco> cuentaBancoCollection;

    public Banco() {
    }

    public Banco(Integer codigo) {
        this.codigo = codigo;
    }

    public Banco(Integer codigo, String nombre, boolean estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.estado = estado;
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

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    @XmlTransient
    public Collection<CuentaBanco> getCuentaBancoCollection() {
        return cuentaBancoCollection;
    }

    public void setCuentaBancoCollection(Collection<CuentaBanco> cuentaBancoCollection) {
        this.cuentaBancoCollection = cuentaBancoCollection;
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
        if (!(object instanceof Banco)) {
            return false;
        }
        Banco other = (Banco) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Banco[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<MovimientoBanco> getMovimientoBancoCollection() {
        return movimientoBancoCollection;
    }

    public void setMovimientoBancoCollection(Collection<MovimientoBanco> movimientoBancoCollection) {
        this.movimientoBancoCollection = movimientoBancoCollection;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public Collection<ConciliacionBancaria> getConciliacionBancariaCollection() {
        return conciliacionBancariaCollection;
    }

    public void setConciliacionBancariaCollection(Collection<ConciliacionBancaria> conciliacionBancariaCollection) {
        this.conciliacionBancariaCollection = conciliacionBancariaCollection;
    }
    
}
