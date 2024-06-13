/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "nota_debito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotaDebito.findAll", query = "SELECT n FROM NotaDebito n")
    , @NamedQuery(name = "NotaDebito.findByCodigo", query = "SELECT n FROM NotaDebito n WHERE n.codigo = :codigo")
    , @NamedQuery(name = "NotaDebito.findByFecha", query = "SELECT n FROM NotaDebito n WHERE n.fecha = :fecha")
    , @NamedQuery(name = "NotaDebito.findByFactura", query = "SELECT n FROM NotaDebito n WHERE n.factura = :factura")
    , @NamedQuery(name = "NotaDebito.findByMontoFactura", query = "SELECT n FROM NotaDebito n WHERE n.montoFactura = :montoFactura")
    , @NamedQuery(name = "NotaDebito.findByClienteoOSuplidor", query = "SELECT n FROM NotaDebito n WHERE n.clienteoOSuplidor = :clienteoOSuplidor")
    , @NamedQuery(name = "NotaDebito.findByNombreSocioNegocio", query = "SELECT n FROM NotaDebito n WHERE n.nombreSocioNegocio = :nombreSocioNegocio")
    , @NamedQuery(name = "NotaDebito.findByMonto", query = "SELECT n FROM NotaDebito n WHERE n.monto = :monto")
    , @NamedQuery(name = "NotaDebito.findByNcf", query = "SELECT n FROM NotaDebito n WHERE n.ncf = :ncf")
    , @NamedQuery(name = "NotaDebito.findByNcfAfectado", query = "SELECT n FROM NotaDebito n WHERE n.ncfAfectado = :ncfAfectado")
    , @NamedQuery(name = "NotaDebito.findByNumero", query = "SELECT n FROM NotaDebito n WHERE n.numero = :numero")
    , @NamedQuery(name = "NotaDebito.findByAnulada", query = "SELECT n FROM NotaDebito n WHERE n.anulada = :anulada")
    , @NamedQuery(name = "NotaDebito.findByFechaAnulada", query = "SELECT n FROM NotaDebito n WHERE n.fechaAnulada = :fechaAnulada")
    , @NamedQuery(name = "NotaDebito.findByFechaRegistro", query = "SELECT n FROM NotaDebito n WHERE n.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "NotaDebito.findByNcfFechaValidoHasta", query = "SELECT n FROM NotaDebito n WHERE n.ncfFechaValidoHasta = :ncfFechaValidoHasta")
    , @NamedQuery(name = "NotaDebito.findByNumeroFactura", query = "SELECT n FROM NotaDebito n WHERE n.numeroFactura = :numeroFactura")})
public class NotaDebito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "factura")
    private Integer factura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto_factura")
    private Double montoFactura;
    @Column(name = "clienteo_o_suplidor")
    private Integer clienteoOSuplidor;
    @Column(name = "nombre_socio_negocio")
    private String nombreSocioNegocio;
    @Column(name = "monto")
    private Double monto;
    @Lob
    @Column(name = "concepto")
    private String concepto;
    @Column(name = "ncf")
    private String ncf;
    @Column(name = "ncf_afectado")
    private String ncfAfectado;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "anulada")
    private Boolean anulada;
    @Column(name = "fecha_anulada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulada;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "ncf_fecha_valido_hasta")
    @Temporal(TemporalType.DATE)
    private Date ncfFechaValidoHasta;
    @Column(name = "numero_factura")
    private Integer numeroFactura;
    @JoinColumn(name = "tipo_nd", referencedColumnName = "codigo")
    @ManyToOne
    private TipoNotaDebito tipoNd;
    @JoinColumn(name = "razon_nota_debito", referencedColumnName = "codigo")
    @ManyToOne
    private RazonNotaDebito razonNotaDebito;
    @JoinColumn(name = "tipo_documento", referencedColumnName = "codigo")
    @ManyToOne
    private TipoDocumento tipoDocumento;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;

    public NotaDebito() {
    }

    public NotaDebito(Integer codigo) {
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

    public Integer getFactura() {
        return factura;
    }

    public void setFactura(Integer factura) {
        this.factura = factura;
    }

    public Double getMontoFactura() {
        return montoFactura;
    }

    public void setMontoFactura(Double montoFactura) {
        this.montoFactura = montoFactura;
    }

    public Integer getClienteoOSuplidor() {
        return clienteoOSuplidor;
    }

    public void setClienteoOSuplidor(Integer clienteoOSuplidor) {
        this.clienteoOSuplidor = clienteoOSuplidor;
    }

    public String getNombreSocioNegocio() {
        return nombreSocioNegocio;
    }

    public void setNombreSocioNegocio(String nombreSocioNegocio) {
        this.nombreSocioNegocio = nombreSocioNegocio;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getNcf() {
        return ncf;
    }

    public void setNcf(String ncf) {
        this.ncf = ncf;
    }

    public String getNcfAfectado() {
        return ncfAfectado;
    }

    public void setNcfAfectado(String ncfAfectado) {
        this.ncfAfectado = ncfAfectado;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getNcfFechaValidoHasta() {
        return ncfFechaValidoHasta;
    }

    public void setNcfFechaValidoHasta(Date ncfFechaValidoHasta) {
        this.ncfFechaValidoHasta = ncfFechaValidoHasta;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Integer numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public TipoNotaDebito getTipoNd() {
        return tipoNd;
    }

    public void setTipoNd(TipoNotaDebito tipoNd) {
        this.tipoNd = tipoNd;
    }

    public RazonNotaDebito getRazonNotaDebito() {
        return razonNotaDebito;
    }

    public void setRazonNotaDebito(RazonNotaDebito razonNotaDebito) {
        this.razonNotaDebito = razonNotaDebito;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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
        if (!(object instanceof NotaDebito)) {
            return false;
        }
        NotaDebito other = (NotaDebito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.NotaDebito[ codigo=" + codigo + " ]";
    }
    
}
