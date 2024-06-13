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
@Table(name = "solicitud_salida_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudSalidaInventario.findAll", query = "SELECT s FROM SolicitudSalidaInventario s")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByCodigo", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.codigo = :codigo")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByFecha", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.fecha = :fecha")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByFechaRegistro", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "SolicitudSalidaInventario.findBySolicitante", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.solicitante = :solicitante")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByAutorizada", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.autorizada = :autorizada")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByFechaAutorizacion", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.fechaAutorizacion = :fechaAutorizacion")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByRechazada", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.rechazada = :rechazada")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByFechaRechazada", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.fechaRechazada = :fechaRechazada")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByAutorizador", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.autorizador = :autorizador")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByNombreAutorizador", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.nombreAutorizador = :nombreAutorizador")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByNotaRechazo", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.notaRechazo = :notaRechazo")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByAnulada", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.anulada = :anulada")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByFechaAnulada", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.fechaAnulada = :fechaAnulada")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByRechazadoPor", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.rechazadoPor = :rechazadoPor")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByEstado", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.estado = :estado")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByFechaEstado", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.fechaEstado = :fechaEstado")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByTipoSolicitud", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.tipoSolicitud = :tipoSolicitud")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByNumeroSolicitud", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.numeroSolicitud = :numeroSolicitud")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByCondicion", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.condicion = :condicion")
    , @NamedQuery(name = "SolicitudSalidaInventario.findByNumero", query = "SELECT s FROM SolicitudSalidaInventario s WHERE s.numero = :numero")})
public class SolicitudSalidaInventario implements Serializable {

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
    @Column(name = "solicitante")
    private String solicitante;
    @Lob
    @Column(name = "comentario")
    private String comentario;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitudSalidaInventario",orphanRemoval = true)
    private Collection<DetalleSolicitudSalidaInventario> detalleSolicitudSalidaInventarioCollection;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "solicitudSalida")
    private Collection<SalidaInventario> salidaInventarioCollection;

    public SolicitudSalidaInventario() {
    }

    public SolicitudSalidaInventario(Integer codigo) {
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

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    @XmlTransient
    public Collection<DetalleSolicitudSalidaInventario> getDetalleSolicitudSalidaInventarioCollection() {
        return detalleSolicitudSalidaInventarioCollection;
    }

    public void setDetalleSolicitudSalidaInventarioCollection(Collection<DetalleSolicitudSalidaInventario> detalleSolicitudSalidaInventarioCollection) {
        this.detalleSolicitudSalidaInventarioCollection = detalleSolicitudSalidaInventarioCollection;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<SalidaInventario> getSalidaInventarioCollection() {
        return salidaInventarioCollection;
    }

    public void setSalidaInventarioCollection(Collection<SalidaInventario> salidaInventarioCollection) {
        this.salidaInventarioCollection = salidaInventarioCollection;
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
        if (!(object instanceof SolicitudSalidaInventario)) {
            return false;
        }
        SolicitudSalidaInventario other = (SolicitudSalidaInventario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.SolicitudSalidaInventario[ codigo=" + codigo + " ]";
    }
    
}
