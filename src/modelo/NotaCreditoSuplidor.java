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
@Table(name = "nota_credito_suplidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotaCreditoSuplidor.findAll", query = "SELECT n FROM NotaCreditoSuplidor n")
    , @NamedQuery(name = "NotaCreditoSuplidor.findByCodigo", query = "SELECT n FROM NotaCreditoSuplidor n WHERE n.codigo = :codigo")
    , @NamedQuery(name = "NotaCreditoSuplidor.findByFecha", query = "SELECT n FROM NotaCreditoSuplidor n WHERE n.fecha = :fecha")
    , @NamedQuery(name = "NotaCreditoSuplidor.findByFactura", query = "SELECT n FROM NotaCreditoSuplidor n WHERE n.factura = :factura")
    , @NamedQuery(name = "NotaCreditoSuplidor.findByMonto", query = "SELECT n FROM NotaCreditoSuplidor n WHERE n.monto = :monto")
    , @NamedQuery(name = "NotaCreditoSuplidor.findByNcf", query = "SELECT n FROM NotaCreditoSuplidor n WHERE n.ncf = :ncf")
    , @NamedQuery(name = "NotaCreditoSuplidor.findByNcfAfectado", query = "SELECT n FROM NotaCreditoSuplidor n WHERE n.ncfAfectado = :ncfAfectado")
    , @NamedQuery(name = "NotaCreditoSuplidor.findByNumero", query = "SELECT n FROM NotaCreditoSuplidor n WHERE n.numero = :numero")
    , @NamedQuery(name = "NotaCreditoSuplidor.findByFechaRegistro", query = "SELECT n FROM NotaCreditoSuplidor n WHERE n.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "NotaCreditoSuplidor.findByAnulada", query = "SELECT n FROM NotaCreditoSuplidor n WHERE n.anulada = :anulada")
    , @NamedQuery(name = "NotaCreditoSuplidor.findByFechaAnulada", query = "SELECT n FROM NotaCreditoSuplidor n WHERE n.fechaAnulada = :fechaAnulada")
    , @NamedQuery(name = "NotaCreditoSuplidor.findByAnuladaPor", query = "SELECT n FROM NotaCreditoSuplidor n WHERE n.anuladaPor = :anuladaPor")
    , @NamedQuery(name = "NotaCreditoSuplidor.findByNombreUsuario", query = "SELECT n FROM NotaCreditoSuplidor n WHERE n.nombreUsuario = :nombreUsuario")})
public class NotaCreditoSuplidor implements Serializable {

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
    private String factura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "anulada")
    private boolean anulada;
    @Column(name = "fecha_anulada")
    @Temporal(TemporalType.DATE)
    private Date fechaAnulada;
    @Column(name = "anulada_por")
    private String anuladaPor;
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "suplidor", referencedColumnName = "codigo")
    @ManyToOne
    private Suplidor suplidor;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;

    public NotaCreditoSuplidor() {
    }

    public NotaCreditoSuplidor(Integer codigo) {
        this.codigo = codigo;
    }

    public NotaCreditoSuplidor(Integer codigo, boolean anulada) {
        this.codigo = codigo;
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

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    public String getAnuladaPor() {
        return anuladaPor;
    }

    public void setAnuladaPor(String anuladaPor) {
        this.anuladaPor = anuladaPor;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
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
        if (!(object instanceof NotaCreditoSuplidor)) {
            return false;
        }
        NotaCreditoSuplidor other = (NotaCreditoSuplidor) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.NotaCreditoSuplidor[ codigo=" + codigo + " ]";
    }
    
}
