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
@Table(name = "recibo_ingreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReciboIngreso.findAll", query = "SELECT r FROM ReciboIngreso r")
    , @NamedQuery(name = "ReciboIngreso.findByCodigo", query = "SELECT r FROM ReciboIngreso r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "ReciboIngreso.findByFecha", query = "SELECT r FROM ReciboIngreso r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "ReciboIngreso.findByMonto", query = "SELECT r FROM ReciboIngreso r WHERE r.monto = :monto")
    , @NamedQuery(name = "ReciboIngreso.findByBalancePendiente", query = "SELECT r FROM ReciboIngreso r WHERE r.balancePendiente = :balancePendiente")
    , @NamedQuery(name = "ReciboIngreso.findByAnulado", query = "SELECT r FROM ReciboIngreso r WHERE r.anulado = :anulado")
    , @NamedQuery(name = "ReciboIngreso.findByCajaChica", query = "SELECT r FROM ReciboIngreso r WHERE r.cajaChica = :cajaChica")
    , @NamedQuery(name = "ReciboIngreso.findByNumero", query = "SELECT r FROM ReciboIngreso r WHERE r.numero = :numero")
    , @NamedQuery(name = "ReciboIngreso.findByImprimir", query = "SELECT r FROM ReciboIngreso r WHERE r.imprimir = :imprimir")
    , @NamedQuery(name = "ReciboIngreso.findByFechaRegistro", query = "SELECT r FROM ReciboIngreso r WHERE r.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "ReciboIngreso.findByTipoRecibo", query = "SELECT r FROM ReciboIngreso r WHERE r.tipoRecibo = :tipoRecibo")})
public class ReciboIngreso implements Serializable {

    @Column(name = "avance_pendiente")
    private Double avancePendiente;
    @OneToMany(mappedBy = "reciboIngreso")
    private Collection<DetalleAvanceDeCliente> detalleAvanceDeClienteCollection;
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
    @Basic(optional = false)
    @Column(name = "imprimir")
    private boolean imprimir;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "tipo_recibo")
    private String tipoRecibo;
    @OneToMany(mappedBy = "recibo",cascade = CascadeType.ALL)
    private Collection<DetalleReciboIngreso> detalleReciboIngresoCollection;
    @JoinColumn(name = "concepto_de_cobro", referencedColumnName = "codigo")
    @ManyToOne
    private ConceptoPorCobro conceptoDeCobro;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "cliente", referencedColumnName = "codigo")
    @ManyToOne
    private Cliente cliente;

    public ReciboIngreso() {
    }

    public ReciboIngreso(Integer codigo) {
        this.codigo = codigo;
    }

    public ReciboIngreso(Integer codigo, boolean imprimir, String tipoRecibo) {
        this.codigo = codigo;
        this.imprimir = imprimir;
        this.tipoRecibo = tipoRecibo;
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

    public boolean getImprimir() {
        return imprimir;
    }

    public void setImprimir(boolean imprimir) {
        this.imprimir = imprimir;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipoRecibo() {
        return tipoRecibo;
    }

    public void setTipoRecibo(String tipoRecibo) {
        this.tipoRecibo = tipoRecibo;
    }

    @XmlTransient
    public Collection<DetalleReciboIngreso> getDetalleReciboIngresoCollection() {
        return detalleReciboIngresoCollection;
    }

    public void setDetalleReciboIngresoCollection(Collection<DetalleReciboIngreso> detalleReciboIngresoCollection) {
        this.detalleReciboIngresoCollection = detalleReciboIngresoCollection;
    }

    public ConceptoPorCobro getConceptoDeCobro() {
        return conceptoDeCobro;
    }

    public void setConceptoDeCobro(ConceptoPorCobro conceptoDeCobro) {
        this.conceptoDeCobro = conceptoDeCobro;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        if (!(object instanceof ReciboIngreso)) {
            return false;
        }
        ReciboIngreso other = (ReciboIngreso) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ReciboIngreso[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<DetalleAvanceDeCliente> getDetalleAvanceDeClienteCollection() {
        return detalleAvanceDeClienteCollection;
    }

    public void setDetalleAvanceDeClienteCollection(Collection<DetalleAvanceDeCliente> detalleAvanceDeClienteCollection) {
        this.detalleAvanceDeClienteCollection = detalleAvanceDeClienteCollection;
    }


    public Double getAvancePendiente() {
        return avancePendiente;
    }

    public void setAvancePendiente(Double avancePendiente) {
        this.avancePendiente = avancePendiente;
    }
    
}
