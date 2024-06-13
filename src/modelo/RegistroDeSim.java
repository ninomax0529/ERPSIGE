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
@Table(name = "registro_de_sim")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroDeSim.findAll", query = "SELECT r FROM RegistroDeSim r")
    , @NamedQuery(name = "RegistroDeSim.findByCodigo", query = "SELECT r FROM RegistroDeSim r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "RegistroDeSim.findByNumero", query = "SELECT r FROM RegistroDeSim r WHERE r.numero = :numero")
    , @NamedQuery(name = "RegistroDeSim.findByFechaDeCompra", query = "SELECT r FROM RegistroDeSim r WHERE r.fechaDeCompra = :fechaDeCompra")
    , @NamedQuery(name = "RegistroDeSim.findByFechaRegistro", query = "SELECT r FROM RegistroDeSim r WHERE r.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "RegistroDeSim.findByEmpresa", query = "SELECT r FROM RegistroDeSim r WHERE r.empresa = :empresa")
    , @NamedQuery(name = "RegistroDeSim.findByDisponible", query = "SELECT r FROM RegistroDeSim r WHERE r.disponible = :disponible")
    , @NamedQuery(name = "RegistroDeSim.findByUsuario", query = "SELECT r FROM RegistroDeSim r WHERE r.usuario = :usuario")
    , @NamedQuery(name = "RegistroDeSim.findByDuplicado", query = "SELECT r FROM RegistroDeSim r WHERE r.duplicado = :duplicado")
    , @NamedQuery(name = "RegistroDeSim.findByFechaParaDuplicado", query = "SELECT r FROM RegistroDeSim r WHERE r.fechaParaDuplicado = :fechaParaDuplicado")})
public class RegistroDeSim implements Serializable {

    @OneToMany(mappedBy = "sim")
    private Collection<RegistroDeImei> registroDeImeiCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @Column(name = "fecha_de_compra")
    @Temporal(TemporalType.DATE)
    private Date fechaDeCompra;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "empresa")
    private String empresa;
    @Basic(optional = false)
    @Column(name = "disponible")
    private boolean disponible;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "duplicado")
    private boolean duplicado;
    @Column(name = "fecha_para_duplicado")
    @Temporal(TemporalType.DATE)
    private Date fechaParaDuplicado;
    @OneToMany(mappedBy = "sim")
    private Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection;

    public RegistroDeSim() {
    }

    public RegistroDeSim(Integer codigo) {
        this.codigo = codigo;
    }

    public RegistroDeSim(Integer codigo, String numero, Date fechaDeCompra, Date fechaRegistro, String empresa, boolean disponible, int usuario, boolean duplicado) {
        this.codigo = codigo;
        this.numero = numero;
        this.fechaDeCompra = fechaDeCompra;
        this.fechaRegistro = fechaRegistro;
        this.empresa = empresa;
        this.disponible = disponible;
        this.usuario = usuario;
        this.duplicado = duplicado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaDeCompra() {
        return fechaDeCompra;
    }

    public void setFechaDeCompra(Date fechaDeCompra) {
        this.fechaDeCompra = fechaDeCompra;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public boolean getDuplicado() {
        return duplicado;
    }

    public void setDuplicado(boolean duplicado) {
        this.duplicado = duplicado;
    }

    public Date getFechaParaDuplicado() {
        return fechaParaDuplicado;
    }

    public void setFechaParaDuplicado(Date fechaParaDuplicado) {
        this.fechaParaDuplicado = fechaParaDuplicado;
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
        if (!(object instanceof RegistroDeSim)) {
            return false;
        }
        RegistroDeSim other = (RegistroDeSim) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RegistroDeSim[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<RegistroDeImei> getRegistroDeImeiCollection() {
        return registroDeImeiCollection;
    }

    public void setRegistroDeImeiCollection(Collection<RegistroDeImei> registroDeImeiCollection) {
        this.registroDeImeiCollection = registroDeImeiCollection;
    }
    
}
