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
@Table(name = "comprobante_de_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComprobanteDePago.findAll", query = "SELECT c FROM ComprobanteDePago c")
    , @NamedQuery(name = "ComprobanteDePago.findByCodigo", query = "SELECT c FROM ComprobanteDePago c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "ComprobanteDePago.findByFecha", query = "SELECT c FROM ComprobanteDePago c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "ComprobanteDePago.findByMonto", query = "SELECT c FROM ComprobanteDePago c WHERE c.monto = :monto")
    , @NamedQuery(name = "ComprobanteDePago.findByBalancePendiente", query = "SELECT c FROM ComprobanteDePago c WHERE c.balancePendiente = :balancePendiente")
    , @NamedQuery(name = "ComprobanteDePago.findByAnulado", query = "SELECT c FROM ComprobanteDePago c WHERE c.anulado = :anulado")
    , @NamedQuery(name = "ComprobanteDePago.findByCajaChica", query = "SELECT c FROM ComprobanteDePago c WHERE c.cajaChica = :cajaChica")
    , @NamedQuery(name = "ComprobanteDePago.findByNumero", query = "SELECT c FROM ComprobanteDePago c WHERE c.numero = :numero")
    , @NamedQuery(name = "ComprobanteDePago.findByFechaRegistro", query = "SELECT c FROM ComprobanteDePago c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "ComprobanteDePago.findByFechaAnulado", query = "SELECT c FROM ComprobanteDePago c WHERE c.fechaAnulado = :fechaAnulado")
    , @NamedQuery(name = "ComprobanteDePago.findByAnuladoPor", query = "SELECT c FROM ComprobanteDePago c WHERE c.anuladoPor = :anuladoPor")
    , @NamedQuery(name = "ComprobanteDePago.findByMontoEnLetra", query = "SELECT c FROM ComprobanteDePago c WHERE c.montoEnLetra = :montoEnLetra")})
public class ComprobanteDePago implements Serializable {

    @OneToMany(mappedBy = "comprobanteDePago")
    private Collection<DetalleAvanceASuplidor> detalleAvanceASuplidorCollection;

    @Column(name = "tipo_comprobante")
    private String tipoComprobante;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Lob
    @Column(name = "concepto")
    private String concepto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @Column(name = "balance_pendiente")
    private Double balancePendiente;
    @Column(name = "anulado")
    private Boolean anulado;
    @Column(name = "caja_chica")
    private Integer cajaChica;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fecha_anulado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulado;
    @Column(name = "anulado_por")
    private Integer anuladoPor;
    @Column(name = "monto_en_letra")
    private String montoEnLetra;
    @OneToMany(mappedBy = "comprobanteDePago",cascade = CascadeType.ALL)
    private Collection<DetalleComprobanteDePago> detalleComprobanteDePagoCollection;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "suplidor", referencedColumnName = "codigo")
    @ManyToOne
    private Suplidor suplidor;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

    public ComprobanteDePago() {
    }

    public ComprobanteDePago(Integer codigo) {
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

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getBalancePendiente() {
        return balancePendiente;
    }

    public void setBalancePendiente(Double balancePendiente) {
        this.balancePendiente = balancePendiente;
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public Integer getCajaChica() {
        return cajaChica;
    }

    public void setCajaChica(Integer cajaChica) {
        this.cajaChica = cajaChica;
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

    public Date getFechaAnulado() {
        return fechaAnulado;
    }

    public void setFechaAnulado(Date fechaAnulado) {
        this.fechaAnulado = fechaAnulado;
    }

    public Integer getAnuladoPor() {
        return anuladoPor;
    }

    public void setAnuladoPor(Integer anuladoPor) {
        this.anuladoPor = anuladoPor;
    }

    public String getMontoEnLetra() {
        return montoEnLetra;
    }

    public void setMontoEnLetra(String montoEnLetra) {
        this.montoEnLetra = montoEnLetra;
    }

    @XmlTransient
    public Collection<DetalleComprobanteDePago> getDetalleComprobanteDePagoCollection() {
        return detalleComprobanteDePagoCollection;
    }

    public void setDetalleComprobanteDePagoCollection(Collection<DetalleComprobanteDePago> detalleComprobanteDePagoCollection) {
        this.detalleComprobanteDePagoCollection = detalleComprobanteDePagoCollection;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public SecuenciaDocumento getSecuenciaDocumento() {
        return secuenciaDocumento;
    }

    public void setSecuenciaDocumento(SecuenciaDocumento secuenciaDocumento) {
        this.secuenciaDocumento = secuenciaDocumento;
    }

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
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
        if (!(object instanceof ComprobanteDePago)) {
            return false;
        }
        ComprobanteDePago other = (ComprobanteDePago) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ComprobanteDePago[ codigo=" + codigo + " ]";
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    @XmlTransient
    public Collection<DetalleAvanceASuplidor> getDetalleAvanceASuplidorCollection() {
        return detalleAvanceASuplidorCollection;
    }

    public void setDetalleAvanceASuplidorCollection(Collection<DetalleAvanceASuplidor> detalleAvanceASuplidorCollection) {
        this.detalleAvanceASuplidorCollection = detalleAvanceASuplidorCollection;
    }
    
}
