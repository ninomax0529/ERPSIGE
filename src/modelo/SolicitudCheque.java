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
@Table(name = "solicitud_cheque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudCheque.findAll", query = "SELECT s FROM SolicitudCheque s")
    , @NamedQuery(name = "SolicitudCheque.findByCodigo", query = "SELECT s FROM SolicitudCheque s WHERE s.codigo = :codigo")
    , @NamedQuery(name = "SolicitudCheque.findByFecha", query = "SELECT s FROM SolicitudCheque s WHERE s.fecha = :fecha")
    , @NamedQuery(name = "SolicitudCheque.findByFechaRegistro", query = "SELECT s FROM SolicitudCheque s WHERE s.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "SolicitudCheque.findByBeneficiario", query = "SELECT s FROM SolicitudCheque s WHERE s.beneficiario = :beneficiario")
    , @NamedQuery(name = "SolicitudCheque.findByNombreBeneficiario", query = "SELECT s FROM SolicitudCheque s WHERE s.nombreBeneficiario = :nombreBeneficiario")
    , @NamedQuery(name = "SolicitudCheque.findBySolicitante", query = "SELECT s FROM SolicitudCheque s WHERE s.solicitante = :solicitante")
    , @NamedQuery(name = "SolicitudCheque.findByNombreSolicitante", query = "SELECT s FROM SolicitudCheque s WHERE s.nombreSolicitante = :nombreSolicitante")
    , @NamedQuery(name = "SolicitudCheque.findByAutorizada", query = "SELECT s FROM SolicitudCheque s WHERE s.autorizada = :autorizada")
    , @NamedQuery(name = "SolicitudCheque.findByFechaAutorizacion", query = "SELECT s FROM SolicitudCheque s WHERE s.fechaAutorizacion = :fechaAutorizacion")
    , @NamedQuery(name = "SolicitudCheque.findByRechazada", query = "SELECT s FROM SolicitudCheque s WHERE s.rechazada = :rechazada")
    , @NamedQuery(name = "SolicitudCheque.findByFechaRechazada", query = "SELECT s FROM SolicitudCheque s WHERE s.fechaRechazada = :fechaRechazada")
    , @NamedQuery(name = "SolicitudCheque.findByAutorizador", query = "SELECT s FROM SolicitudCheque s WHERE s.autorizador = :autorizador")
    , @NamedQuery(name = "SolicitudCheque.findByNombreAutorizador", query = "SELECT s FROM SolicitudCheque s WHERE s.nombreAutorizador = :nombreAutorizador")
    , @NamedQuery(name = "SolicitudCheque.findByNotaRechazo", query = "SELECT s FROM SolicitudCheque s WHERE s.notaRechazo = :notaRechazo")
    , @NamedQuery(name = "SolicitudCheque.findByAnulada", query = "SELECT s FROM SolicitudCheque s WHERE s.anulada = :anulada")
    , @NamedQuery(name = "SolicitudCheque.findByFechaAnulada", query = "SELECT s FROM SolicitudCheque s WHERE s.fechaAnulada = :fechaAnulada")
    , @NamedQuery(name = "SolicitudCheque.findByMonto", query = "SELECT s FROM SolicitudCheque s WHERE s.monto = :monto")
    , @NamedQuery(name = "SolicitudCheque.findByRechazadoPor", query = "SELECT s FROM SolicitudCheque s WHERE s.rechazadoPor = :rechazadoPor")
    , @NamedQuery(name = "SolicitudCheque.findByEstado", query = "SELECT s FROM SolicitudCheque s WHERE s.estado = :estado")
    , @NamedQuery(name = "SolicitudCheque.findByFechaEstado", query = "SELECT s FROM SolicitudCheque s WHERE s.fechaEstado = :fechaEstado")
    , @NamedQuery(name = "SolicitudCheque.findByTipoSolicitud", query = "SELECT s FROM SolicitudCheque s WHERE s.tipoSolicitud = :tipoSolicitud")
    , @NamedQuery(name = "SolicitudCheque.findByNumeroSolicitud", query = "SELECT s FROM SolicitudCheque s WHERE s.numeroSolicitud = :numeroSolicitud")
    , @NamedQuery(name = "SolicitudCheque.findByCondicion", query = "SELECT s FROM SolicitudCheque s WHERE s.condicion = :condicion")
    , @NamedQuery(name = "SolicitudCheque.findByNumero", query = "SELECT s FROM SolicitudCheque s WHERE s.numero = :numero")})
public class SolicitudCheque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "beneficiario")
    private Integer beneficiario;
    @Column(name = "nombre_beneficiario")
    private String nombreBeneficiario;
    @Column(name = "solicitante")
    private Integer solicitante;
    @Column(name = "nombre_solicitante")
    private String nombreSolicitante;
    @Lob
    @Column(name = "concepto")
    private String concepto;
    @Column(name = "autorizada")
    private Boolean autorizada;
    @Column(name = "fecha_autorizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAutorizacion;
    @Column(name = "rechazada")
    private Boolean rechazada;
    @Column(name = "fecha_rechazada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRechazada;
    @Column(name = "autorizador")
    private Integer autorizador;
    @Column(name = "nombre_autorizador")
    private String nombreAutorizador;
    @Column(name = "nota_rechazo")
    private String notaRechazo;
    @Column(name = "anulada")
    private Boolean anulada;
    @Column(name = "fecha_anulada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulada;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @Column(name = "rechazado_por")
    private Integer rechazadoPor;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_estado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    @Column(name = "tipo_solicitud")
    private Integer tipoSolicitud;
    @Column(name = "numero_solicitud")
    private Integer numeroSolicitud;
    @Column(name = "condicion")
    private String condicion;
    @Column(name = "numero")
    private Integer numero;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitudCheque")
    private Collection<DetalleSolicitudCheque> detalleSolicitudChequeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitud")
    private Collection<Cheque> chequeCollection;

    public SolicitudCheque() {
    }

    public SolicitudCheque(Integer codigo) {
        this.codigo = codigo;
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

    public Integer getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Integer beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public void setNombreBeneficiario(String nombreBeneficiario) {
        this.nombreBeneficiario = nombreBeneficiario;
    }

    public Integer getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Integer solicitante) {
        this.solicitante = solicitante;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Boolean getAutorizada() {
        return autorizada;
    }

    public void setAutorizada(Boolean autorizada) {
        this.autorizada = autorizada;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public Boolean getRechazada() {
        return rechazada;
    }

    public void setRechazada(Boolean rechazada) {
        this.rechazada = rechazada;
    }

    public Date getFechaRechazada() {
        return fechaRechazada;
    }

    public void setFechaRechazada(Date fechaRechazada) {
        this.fechaRechazada = fechaRechazada;
    }

    public Integer getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(Integer autorizador) {
        this.autorizador = autorizador;
    }

    public String getNombreAutorizador() {
        return nombreAutorizador;
    }

    public void setNombreAutorizador(String nombreAutorizador) {
        this.nombreAutorizador = nombreAutorizador;
    }

    public String getNotaRechazo() {
        return notaRechazo;
    }

    public void setNotaRechazo(String notaRechazo) {
        this.notaRechazo = notaRechazo;
    }

    public Boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(Boolean anulada) {
        this.anulada = anulada;
    }

    public Date getFechaAnulada() {
        return fechaAnulada;
    }

    public void setFechaAnulada(Date fechaAnulada) {
        this.fechaAnulada = fechaAnulada;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getRechazadoPor() {
        return rechazadoPor;
    }

    public void setRechazadoPor(Integer rechazadoPor) {
        this.rechazadoPor = rechazadoPor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public Integer getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(Integer tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public Integer getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(Integer numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public SecuenciaDocumento getSecuenciaDocumento() {
        return secuenciaDocumento;
    }

    public void setSecuenciaDocumento(SecuenciaDocumento secuenciaDocumento) {
        this.secuenciaDocumento = secuenciaDocumento;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    @XmlTransient
    public Collection<DetalleSolicitudCheque> getDetalleSolicitudChequeCollection() {
        return detalleSolicitudChequeCollection;
    }

    public void setDetalleSolicitudChequeCollection(Collection<DetalleSolicitudCheque> detalleSolicitudChequeCollection) {
        this.detalleSolicitudChequeCollection = detalleSolicitudChequeCollection;
    }

    @XmlTransient
    public Collection<Cheque> getChequeCollection() {
        return chequeCollection;
    }

    public void setChequeCollection(Collection<Cheque> chequeCollection) {
        this.chequeCollection = chequeCollection;
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
        if (!(object instanceof SolicitudCheque)) {
            return false;
        }
        SolicitudCheque other = (SolicitudCheque) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.SolicitudCheque[ codigo=" + codigo + " ]";
    }
    
}
