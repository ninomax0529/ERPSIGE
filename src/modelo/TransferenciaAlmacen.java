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
import javax.persistence.Lob;
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
@Table(name = "transferencia_almacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransferenciaAlmacen.findAll", query = "SELECT t FROM TransferenciaAlmacen t")
    , @NamedQuery(name = "TransferenciaAlmacen.findByCodigo", query = "SELECT t FROM TransferenciaAlmacen t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TransferenciaAlmacen.findByFecha", query = "SELECT t FROM TransferenciaAlmacen t WHERE t.fecha = :fecha")
    , @NamedQuery(name = "TransferenciaAlmacen.findByFechaRegistro", query = "SELECT t FROM TransferenciaAlmacen t WHERE t.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "TransferenciaAlmacen.findBySolicitante", query = "SELECT t FROM TransferenciaAlmacen t WHERE t.solicitante = :solicitante")
    , @NamedQuery(name = "TransferenciaAlmacen.findByDepartamentoSolicitante", query = "SELECT t FROM TransferenciaAlmacen t WHERE t.departamentoSolicitante = :departamentoSolicitante")
    , @NamedQuery(name = "TransferenciaAlmacen.findByAnulada", query = "SELECT t FROM TransferenciaAlmacen t WHERE t.anulada = :anulada")
    , @NamedQuery(name = "TransferenciaAlmacen.findByFechaAnulada", query = "SELECT t FROM TransferenciaAlmacen t WHERE t.fechaAnulada = :fechaAnulada")
    , @NamedQuery(name = "TransferenciaAlmacen.findByAutorizada", query = "SELECT t FROM TransferenciaAlmacen t WHERE t.autorizada = :autorizada")
    , @NamedQuery(name = "TransferenciaAlmacen.findByFechaAutorizada", query = "SELECT t FROM TransferenciaAlmacen t WHERE t.fechaAutorizada = :fechaAutorizada")
    , @NamedQuery(name = "TransferenciaAlmacen.findByAutorizador", query = "SELECT t FROM TransferenciaAlmacen t WHERE t.autorizador = :autorizador")
    , @NamedQuery(name = "TransferenciaAlmacen.findByNumero", query = "SELECT t FROM TransferenciaAlmacen t WHERE t.numero = :numero")})
public class TransferenciaAlmacen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "solicitante")
    private Integer solicitante;
    @Column(name = "departamento_solicitante")
    private Integer departamentoSolicitante;
    @Basic(optional = false)
    @Column(name = "anulada")
    private boolean anulada;
    @Column(name = "fecha_anulada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulada;
    @Basic(optional = false)
    @Column(name = "autorizada")
    private boolean autorizada;
    @Column(name = "fecha_autorizada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAutorizada;
    @Column(name = "autorizador")
    private Integer autorizador;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "numero")
    private Integer numero;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transferenciaAlmacen")
    private Collection<DetalleTransferenciaAlmacen> detalleTransferenciaAlmacenCollection;

    public TransferenciaAlmacen() {
    }

    public TransferenciaAlmacen(Integer codigo) {
        this.codigo = codigo;
    }

    public TransferenciaAlmacen(Integer codigo, Date fecha, Date fechaRegistro, boolean anulada, boolean autorizada) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.fechaRegistro = fechaRegistro;
        this.anulada = anulada;
        this.autorizada = autorizada;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Integer solicitante) {
        this.solicitante = solicitante;
    }

    public Integer getDepartamentoSolicitante() {
        return departamentoSolicitante;
    }

    public void setDepartamentoSolicitante(Integer departamentoSolicitante) {
        this.departamentoSolicitante = departamentoSolicitante;
    }

    public boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public Date getFechaAnulada() {
        return fechaAnulada;
    }

    public void setFechaAnulada(Date fechaAnulada) {
        this.fechaAnulada = fechaAnulada;
    }

    public boolean getAutorizada() {
        return autorizada;
    }

    public void setAutorizada(boolean autorizada) {
        this.autorizada = autorizada;
    }

    public Date getFechaAutorizada() {
        return fechaAutorizada;
    }

    public void setFechaAutorizada(Date fechaAutorizada) {
        this.fechaAutorizada = fechaAutorizada;
    }

    public Integer getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(Integer autorizador) {
        this.autorizador = autorizador;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public SecuenciaDocumento getSecuenciaDocumento() {
        return secuenciaDocumento;
    }

    public void setSecuenciaDocumento(SecuenciaDocumento secuenciaDocumento) {
        this.secuenciaDocumento = secuenciaDocumento;
    }

    @XmlTransient
    public Collection<DetalleTransferenciaAlmacen> getDetalleTransferenciaAlmacenCollection() {
        return detalleTransferenciaAlmacenCollection;
    }

    public void setDetalleTransferenciaAlmacenCollection(Collection<DetalleTransferenciaAlmacen> detalleTransferenciaAlmacenCollection) {
        this.detalleTransferenciaAlmacenCollection = detalleTransferenciaAlmacenCollection;
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
        if (!(object instanceof TransferenciaAlmacen)) {
            return false;
        }
        TransferenciaAlmacen other = (TransferenciaAlmacen) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TransferenciaAlmacen[ codigo=" + codigo + " ]";
    }
    
}
