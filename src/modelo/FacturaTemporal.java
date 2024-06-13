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
import javax.persistence.FetchType;
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
@Table(name = "factura_temporal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacturaTemporal.findAll", query = "SELECT f FROM FacturaTemporal f")
    , @NamedQuery(name = "FacturaTemporal.findByCodigo", query = "SELECT f FROM FacturaTemporal f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "FacturaTemporal.findByNumero", query = "SELECT f FROM FacturaTemporal f WHERE f.numero = :numero")
    , @NamedQuery(name = "FacturaTemporal.findByFecha", query = "SELECT f FROM FacturaTemporal f WHERE f.fecha = :fecha")
    , @NamedQuery(name = "FacturaTemporal.findByTipoVenta", query = "SELECT f FROM FacturaTemporal f WHERE f.tipoVenta = :tipoVenta")
    , @NamedQuery(name = "FacturaTemporal.findByNombreCliente", query = "SELECT f FROM FacturaTemporal f WHERE f.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "FacturaTemporal.findByFechaVencimiento", query = "SELECT f FROM FacturaTemporal f WHERE f.fechaVencimiento = :fechaVencimiento")
    , @NamedQuery(name = "FacturaTemporal.findByMonto", query = "SELECT f FROM FacturaTemporal f WHERE f.monto = :monto")
    , @NamedQuery(name = "FacturaTemporal.findByAbono", query = "SELECT f FROM FacturaTemporal f WHERE f.abono = :abono")
    , @NamedQuery(name = "FacturaTemporal.findByPendiente", query = "SELECT f FROM FacturaTemporal f WHERE f.pendiente = :pendiente")
    , @NamedQuery(name = "FacturaTemporal.findByPagada", query = "SELECT f FROM FacturaTemporal f WHERE f.pagada = :pagada")
    , @NamedQuery(name = "FacturaTemporal.findByNcf", query = "SELECT f FROM FacturaTemporal f WHERE f.ncf = :ncf")
    , @NamedQuery(name = "FacturaTemporal.findByItbis", query = "SELECT f FROM FacturaTemporal f WHERE f.itbis = :itbis")
    , @NamedQuery(name = "FacturaTemporal.findByDescuento", query = "SELECT f FROM FacturaTemporal f WHERE f.descuento = :descuento")
    , @NamedQuery(name = "FacturaTemporal.findByPorcientoDescuento", query = "SELECT f FROM FacturaTemporal f WHERE f.porcientoDescuento = :porcientoDescuento")
    , @NamedQuery(name = "FacturaTemporal.findByAnulada", query = "SELECT f FROM FacturaTemporal f WHERE f.anulada = :anulada")
    , @NamedQuery(name = "FacturaTemporal.findBySecSinComp", query = "SELECT f FROM FacturaTemporal f WHERE f.secSinComp = :secSinComp")
    , @NamedQuery(name = "FacturaTemporal.findBySecConComp", query = "SELECT f FROM FacturaTemporal f WHERE f.secConComp = :secConComp")
    , @NamedQuery(name = "FacturaTemporal.findByTotal", query = "SELECT f FROM FacturaTemporal f WHERE f.total = :total")
    , @NamedQuery(name = "FacturaTemporal.findBySubTotal", query = "SELECT f FROM FacturaTemporal f WHERE f.subTotal = :subTotal")
    , @NamedQuery(name = "FacturaTemporal.findByFechaCreacion", query = "SELECT f FROM FacturaTemporal f WHERE f.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "FacturaTemporal.findByCajaChica", query = "SELECT f FROM FacturaTemporal f WHERE f.cajaChica = :cajaChica")
    , @NamedQuery(name = "FacturaTemporal.findByFechaAnulada", query = "SELECT f FROM FacturaTemporal f WHERE f.fechaAnulada = :fechaAnulada")
    , @NamedQuery(name = "FacturaTemporal.findByNumeroContrato", query = "SELECT f FROM FacturaTemporal f WHERE f.numeroContrato = :numeroContrato")
    , @NamedQuery(name = "FacturaTemporal.findByAutorizadorDescuento", query = "SELECT f FROM FacturaTemporal f WHERE f.autorizadorDescuento = :autorizadorDescuento")
    , @NamedQuery(name = "FacturaTemporal.findByDespachada", query = "SELECT f FROM FacturaTemporal f WHERE f.despachada = :despachada")
    , @NamedQuery(name = "FacturaTemporal.findByEnviadaPorCorreo", query = "SELECT f FROM FacturaTemporal f WHERE f.enviadaPorCorreo = :enviadaPorCorreo")
    , @NamedQuery(name = "FacturaTemporal.findByNcfFechaValidoHasta", query = "SELECT f FROM FacturaTemporal f WHERE f.ncfFechaValidoHasta = :ncfFechaValidoHasta")
    , @NamedQuery(name = "FacturaTemporal.findByNombreTipoNcf", query = "SELECT f FROM FacturaTemporal f WHERE f.nombreTipoNcf = :nombreTipoNcf")})
public class FacturaTemporal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "tipo_venta")
    private Integer tipoVenta;
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @Column(name = "abono")
    private Double abono;
    @Column(name = "pendiente")
    private Double pendiente;
    @Column(name = "pagada")
    private Boolean pagada;
    @Column(name = "ncf")
    private String ncf;
    @Column(name = "itbis")
    private Double itbis;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "porciento_descuento")
    private Double porcientoDescuento;
    @Column(name = "anulada")
    private Boolean anulada;
    @Column(name = "sec_sin_comp")
    private Integer secSinComp;
    @Column(name = "sec_con_comp")
    private Integer secConComp;
    @Column(name = "total")
    private Double total;
    @Column(name = "sub_total")
    private Double subTotal;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "caja_chica")
    private Integer cajaChica;
    @Column(name = "fecha_anulada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulada;
    @Column(name = "numero_contrato")
    private String numeroContrato;
    @Column(name = "autorizador_descuento")
    private Integer autorizadorDescuento;
    @Basic(optional = false)
    @Column(name = "despachada")
    private boolean despachada;
    @Basic(optional = false)
    @Column(name = "enviada_por_correo")
    private boolean enviadaPorCorreo;
    @Column(name = "ncf_fecha_valido_hasta")
    @Temporal(TemporalType.DATE)
    private Date ncfFechaValidoHasta;
    @Column(name = "nombre_tipo_ncf")
    private String nombreTipoNcf;
    @JoinColumn(name = "cliente", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "pedido", referencedColumnName = "codigo")
    @ManyToOne
    private Pedido pedido;
    @JoinColumn(name = "vendedor", referencedColumnName = "codigo")
    @ManyToOne
    private EjecutivoDeVenta vendedor;
    @JoinColumn(name = "condicion_pago", referencedColumnName = "codigo")
    @ManyToOne
    private CondicionPago condicionPago;
    @JoinColumn(name = "plazo", referencedColumnName = "codigo")
    @ManyToOne
    private Plazo plazo;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "tipo_ncf", referencedColumnName = "codigo")
    @ManyToOne
    private TipoNcf tipoNcf;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "cotizacion_de_venta", referencedColumnName = "codigo")
    @ManyToOne
    private CotizacionDeVenta cotizacionDeVenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura", orphanRemoval = true, fetch = FetchType.EAGER)
    private Collection<DetalleFacturaTemporal> detalleFacturaTemporalCollection;

    public FacturaTemporal() {
    }

    public FacturaTemporal(Integer codigo) {
        this.codigo = codigo;
    }

    public FacturaTemporal(Integer codigo, boolean despachada, boolean enviadaPorCorreo) {
        this.codigo = codigo;
        this.despachada = despachada;
        this.enviadaPorCorreo = enviadaPorCorreo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(Integer tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getAbono() {
        return abono;
    }

    public void setAbono(Double abono) {
        this.abono = abono;
    }

    public Double getPendiente() {
        return pendiente;
    }

    public void setPendiente(Double pendiente) {
        this.pendiente = pendiente;
    }

    public Boolean getPagada() {
        return pagada;
    }

    public void setPagada(Boolean pagada) {
        this.pagada = pagada;
    }

    public String getNcf() {
        return ncf;
    }

    public void setNcf(String ncf) {
        this.ncf = ncf;
    }

    public Double getItbis() {
        return itbis;
    }

    public void setItbis(Double itbis) {
        this.itbis = itbis;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getPorcientoDescuento() {
        return porcientoDescuento;
    }

    public void setPorcientoDescuento(Double porcientoDescuento) {
        this.porcientoDescuento = porcientoDescuento;
    }

    public Boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(Boolean anulada) {
        this.anulada = anulada;
    }

    public Integer getSecSinComp() {
        return secSinComp;
    }

    public void setSecSinComp(Integer secSinComp) {
        this.secSinComp = secSinComp;
    }

    public Integer getSecConComp() {
        return secConComp;
    }

    public void setSecConComp(Integer secConComp) {
        this.secConComp = secConComp;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCajaChica() {
        return cajaChica;
    }

    public void setCajaChica(Integer cajaChica) {
        this.cajaChica = cajaChica;
    }

    public Date getFechaAnulada() {
        return fechaAnulada;
    }

    public void setFechaAnulada(Date fechaAnulada) {
        this.fechaAnulada = fechaAnulada;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Integer getAutorizadorDescuento() {
        return autorizadorDescuento;
    }

    public void setAutorizadorDescuento(Integer autorizadorDescuento) {
        this.autorizadorDescuento = autorizadorDescuento;
    }

    public boolean getDespachada() {
        return despachada;
    }

    public void setDespachada(boolean despachada) {
        this.despachada = despachada;
    }

    public boolean getEnviadaPorCorreo() {
        return enviadaPorCorreo;
    }

    public void setEnviadaPorCorreo(boolean enviadaPorCorreo) {
        this.enviadaPorCorreo = enviadaPorCorreo;
    }

    public Date getNcfFechaValidoHasta() {
        return ncfFechaValidoHasta;
    }

    public void setNcfFechaValidoHasta(Date ncfFechaValidoHasta) {
        this.ncfFechaValidoHasta = ncfFechaValidoHasta;
    }

    public String getNombreTipoNcf() {
        return nombreTipoNcf;
    }

    public void setNombreTipoNcf(String nombreTipoNcf) {
        this.nombreTipoNcf = nombreTipoNcf;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public EjecutivoDeVenta getVendedor() {
        return vendedor;
    }

    public void setVendedor(EjecutivoDeVenta vendedor) {
        this.vendedor = vendedor;
    }

    public CondicionPago getCondicionPago() {
        return condicionPago;
    }

    public void setCondicionPago(CondicionPago condicionPago) {
        this.condicionPago = condicionPago;
    }

    public Plazo getPlazo() {
        return plazo;
    }

    public void setPlazo(Plazo plazo) {
        this.plazo = plazo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoNcf getTipoNcf() {
        return tipoNcf;
    }

    public void setTipoNcf(TipoNcf tipoNcf) {
        this.tipoNcf = tipoNcf;
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

    public CotizacionDeVenta getCotizacionDeVenta() {
        return cotizacionDeVenta;
    }

    public void setCotizacionDeVenta(CotizacionDeVenta cotizacionDeVenta) {
        this.cotizacionDeVenta = cotizacionDeVenta;
    }

    @XmlTransient
    public Collection<DetalleFacturaTemporal> getDetalleFacturaTemporalCollection() {
        return detalleFacturaTemporalCollection;
    }

    public void setDetalleFacturaTemporalCollection(Collection<DetalleFacturaTemporal> detalleFacturaTemporalCollection) {
        this.detalleFacturaTemporalCollection = detalleFacturaTemporalCollection;
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
        if (!(object instanceof FacturaTemporal)) {
            return false;
        }
        FacturaTemporal other = (FacturaTemporal) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.FacturaTemporal[ codigo=" + codigo + " ]";
    }

}
