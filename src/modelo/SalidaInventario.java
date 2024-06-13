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
@Table(name = "salida_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalidaInventario.findAll", query = "SELECT s FROM SalidaInventario s")
    , @NamedQuery(name = "SalidaInventario.findByCodigo", query = "SELECT s FROM SalidaInventario s WHERE s.codigo = :codigo")
    , @NamedQuery(name = "SalidaInventario.findByFecha", query = "SELECT s FROM SalidaInventario s WHERE s.fecha = :fecha")
    , @NamedQuery(name = "SalidaInventario.findByFechaRegistro", query = "SELECT s FROM SalidaInventario s WHERE s.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "SalidaInventario.findByComentario", query = "SELECT s FROM SalidaInventario s WHERE s.comentario = :comentario")
    , @NamedQuery(name = "SalidaInventario.findByFechaContabilizacion", query = "SELECT s FROM SalidaInventario s WHERE s.fechaContabilizacion = :fechaContabilizacion")
    , @NamedQuery(name = "SalidaInventario.findByMoneda", query = "SELECT s FROM SalidaInventario s WHERE s.moneda = :moneda")
    , @NamedQuery(name = "SalidaInventario.findBySolicitante", query = "SELECT s FROM SalidaInventario s WHERE s.solicitante = :solicitante")
    , @NamedQuery(name = "SalidaInventario.findByTipoSalida", query = "SELECT s FROM SalidaInventario s WHERE s.tipoSalida = :tipoSalida")
    , @NamedQuery(name = "SalidaInventario.findByProyecto", query = "SELECT s FROM SalidaInventario s WHERE s.proyecto = :proyecto")
    , @NamedQuery(name = "SalidaInventario.findByAnulada", query = "SELECT s FROM SalidaInventario s WHERE s.anulada = :anulada")
    , @NamedQuery(name = "SalidaInventario.findByFechaAnulada", query = "SELECT s FROM SalidaInventario s WHERE s.fechaAnulada = :fechaAnulada")
    , @NamedQuery(name = "SalidaInventario.findByAnuladaPor", query = "SELECT s FROM SalidaInventario s WHERE s.anuladaPor = :anuladaPor")
    , @NamedQuery(name = "SalidaInventario.findByAlmacen", query = "SELECT s FROM SalidaInventario s WHERE s.almacen = :almacen")
    , @NamedQuery(name = "SalidaInventario.findByNumero", query = "SELECT s FROM SalidaInventario s WHERE s.numero = :numero")})
public class SalidaInventario implements Serializable {

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
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "fecha_contabilizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaContabilizacion;
    @Column(name = "moneda")
    private Integer moneda;
    @Column(name = "solicitante")
    private String solicitante;
    @Column(name = "tipo_salida")
    private Integer tipoSalida;
    @Basic(optional = false)
    @Column(name = "proyecto")
    private String proyecto;
    @Basic(optional = false)
    @Column(name = "anulada")
    private boolean anulada;
    @Column(name = "fecha_anulada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulada;
    @Column(name = "anulada_por")
    private Integer anuladaPor;
    @Column(name = "almacen")
    private Integer almacen;
    @Column(name = "numero")
    private Integer numero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salidaInventario")
    private Collection<DetalleSalidaInventario> detalleSalidaInventarioCollection;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "solicitud_salida", referencedColumnName = "codigo")
    @ManyToOne
    private SolicitudSalidaInventario solicitudSalida;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;

    public SalidaInventario() {
    }

    public SalidaInventario(Integer codigo) {
        this.codigo = codigo;
    }

    public SalidaInventario(Integer codigo, String proyecto, boolean anulada) {
        this.codigo = codigo;
        this.proyecto = proyecto;
        this.anulada = anulada;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaContabilizacion() {
        return fechaContabilizacion;
    }

    public void setFechaContabilizacion(Date fechaContabilizacion) {
        this.fechaContabilizacion = fechaContabilizacion;
    }

    public Integer getMoneda() {
        return moneda;
    }

    public void setMoneda(Integer moneda) {
        this.moneda = moneda;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public Integer getTipoSalida() {
        return tipoSalida;
    }

    public void setTipoSalida(Integer tipoSalida) {
        this.tipoSalida = tipoSalida;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
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

    public Integer getAnuladaPor() {
        return anuladaPor;
    }

    public void setAnuladaPor(Integer anuladaPor) {
        this.anuladaPor = anuladaPor;
    }

    public Integer getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Integer almacen) {
        this.almacen = almacen;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @XmlTransient
    public Collection<DetalleSalidaInventario> getDetalleSalidaInventarioCollection() {
        return detalleSalidaInventarioCollection;
    }

    public void setDetalleSalidaInventarioCollection(Collection<DetalleSalidaInventario> detalleSalidaInventarioCollection) {
        this.detalleSalidaInventarioCollection = detalleSalidaInventarioCollection;
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

    public SolicitudSalidaInventario getSolicitudSalida() {
        return solicitudSalida;
    }

    public void setSolicitudSalida(SolicitudSalidaInventario solicitudSalida) {
        this.solicitudSalida = solicitudSalida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof SalidaInventario)) {
            return false;
        }
        SalidaInventario other = (SalidaInventario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.SalidaInventario[ codigo=" + codigo + " ]";
    }
    
}
