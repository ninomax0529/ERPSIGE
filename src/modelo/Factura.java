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
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
    , @NamedQuery(name = "Factura.findByCodigo", query = "SELECT f FROM Factura f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "Factura.findByNumero", query = "SELECT f FROM Factura f WHERE f.numero = :numero")
    , @NamedQuery(name = "Factura.findByFecha", query = "SELECT f FROM Factura f WHERE f.fecha = :fecha")
    , @NamedQuery(name = "Factura.findByTipoVenta", query = "SELECT f FROM Factura f WHERE f.tipoVenta = :tipoVenta")
    , @NamedQuery(name = "Factura.findByNombreCliente", query = "SELECT f FROM Factura f WHERE f.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "Factura.findByFechaVencimiento", query = "SELECT f FROM Factura f WHERE f.fechaVencimiento = :fechaVencimiento")
    , @NamedQuery(name = "Factura.findByMonto", query = "SELECT f FROM Factura f WHERE f.monto = :monto")
    , @NamedQuery(name = "Factura.findByAbono", query = "SELECT f FROM Factura f WHERE f.abono = :abono")
    , @NamedQuery(name = "Factura.findByPendiente", query = "SELECT f FROM Factura f WHERE f.pendiente = :pendiente")
    , @NamedQuery(name = "Factura.findByPagada", query = "SELECT f FROM Factura f WHERE f.pagada = :pagada")
    , @NamedQuery(name = "Factura.findByNcf", query = "SELECT f FROM Factura f WHERE f.ncf = :ncf")
    , @NamedQuery(name = "Factura.findByItbis", query = "SELECT f FROM Factura f WHERE f.itbis = :itbis")
    , @NamedQuery(name = "Factura.findByDescuento", query = "SELECT f FROM Factura f WHERE f.descuento = :descuento")
    , @NamedQuery(name = "Factura.findByPorcientoDescuento", query = "SELECT f FROM Factura f WHERE f.porcientoDescuento = :porcientoDescuento")
    , @NamedQuery(name = "Factura.findByAnulada", query = "SELECT f FROM Factura f WHERE f.anulada = :anulada")
    , @NamedQuery(name = "Factura.findBySecSinComp", query = "SELECT f FROM Factura f WHERE f.secSinComp = :secSinComp")
    , @NamedQuery(name = "Factura.findBySecConComp", query = "SELECT f FROM Factura f WHERE f.secConComp = :secConComp")
    , @NamedQuery(name = "Factura.findByTotal", query = "SELECT f FROM Factura f WHERE f.total = :total")
    , @NamedQuery(name = "Factura.findBySubTotal", query = "SELECT f FROM Factura f WHERE f.subTotal = :subTotal")
    , @NamedQuery(name = "Factura.findByFechaCreacion", query = "SELECT f FROM Factura f WHERE f.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Factura.findByCajaChica", query = "SELECT f FROM Factura f WHERE f.cajaChica = :cajaChica")
    , @NamedQuery(name = "Factura.findByFechaAnulada", query = "SELECT f FROM Factura f WHERE f.fechaAnulada = :fechaAnulada")
    , @NamedQuery(name = "Factura.findByNumeroContrato", query = "SELECT f FROM Factura f WHERE f.numeroContrato = :numeroContrato")
    , @NamedQuery(name = "Factura.findByAutorizadorDescuento", query = "SELECT f FROM Factura f WHERE f.autorizadorDescuento = :autorizadorDescuento")
    , @NamedQuery(name = "Factura.findByDespachada", query = "SELECT f FROM Factura f WHERE f.despachada = :despachada")
    , @NamedQuery(name = "Factura.findByEnviadaPorCorreo", query = "SELECT f FROM Factura f WHERE f.enviadaPorCorreo = :enviadaPorCorreo")
    , @NamedQuery(name = "Factura.findByNcfFechaValidoHasta", query = "SELECT f FROM Factura f WHERE f.ncfFechaValidoHasta = :ncfFechaValidoHasta")
    , @NamedQuery(name = "Factura.findByImprimir", query = "SELECT f FROM Factura f WHERE f.imprimir = :imprimir")
    , @NamedQuery(name = "Factura.findByNombreTipoNcf", query = "SELECT f FROM Factura f WHERE f.nombreTipoNcf = :nombreTipoNcf")
    , @NamedQuery(name = "Factura.findByFechaRetencion", query = "SELECT f FROM Factura f WHERE f.fechaRetencion = :fechaRetencion")
    , @NamedQuery(name = "Factura.findByIsrRetenido", query = "SELECT f FROM Factura f WHERE f.isrRetenido = :isrRetenido")
    , @NamedQuery(name = "Factura.findByItbisRetenido", query = "SELECT f FROM Factura f WHERE f.itbisRetenido = :itbisRetenido")
    , @NamedQuery(name = "Factura.findByIsrPercibido", query = "SELECT f FROM Factura f WHERE f.isrPercibido = :isrPercibido")
    , @NamedQuery(name = "Factura.findByItbisPercibido", query = "SELECT f FROM Factura f WHERE f.itbisPercibido = :itbisPercibido")
    , @NamedQuery(name = "Factura.findByImpuestoSelectivoAlComsumo", query = "SELECT f FROM Factura f WHERE f.impuestoSelectivoAlComsumo = :impuestoSelectivoAlComsumo")
    , @NamedQuery(name = "Factura.findByOtrosImpuestoTasa", query = "SELECT f FROM Factura f WHERE f.otrosImpuestoTasa = :otrosImpuestoTasa")
    , @NamedQuery(name = "Factura.findByMontoPropinaLegal", query = "SELECT f FROM Factura f WHERE f.montoPropinaLegal = :montoPropinaLegal")
    , @NamedQuery(name = "Factura.findByEfectivo", query = "SELECT f FROM Factura f WHERE f.efectivo = :efectivo")
    , @NamedQuery(name = "Factura.findByChequeTransferenciaDeposito", query = "SELECT f FROM Factura f WHERE f.chequeTransferenciaDeposito = :chequeTransferenciaDeposito")
    , @NamedQuery(name = "Factura.findByTarjetaDebitoCredito", query = "SELECT f FROM Factura f WHERE f.tarjetaDebitoCredito = :tarjetaDebitoCredito")
    , @NamedQuery(name = "Factura.findByVentaACredito", query = "SELECT f FROM Factura f WHERE f.ventaACredito = :ventaACredito")
    , @NamedQuery(name = "Factura.findByBonosOCertificado", query = "SELECT f FROM Factura f WHERE f.bonosOCertificado = :bonosOCertificado")
    , @NamedQuery(name = "Factura.findByPermuta", query = "SELECT f FROM Factura f WHERE f.permuta = :permuta")
    , @NamedQuery(name = "Factura.findByOtrasFormaDeVenta", query = "SELECT f FROM Factura f WHERE f.otrasFormaDeVenta = :otrasFormaDeVenta")
    , @NamedQuery(name = "Factura.findByTotalACobrar", query = "SELECT f FROM Factura f WHERE f.totalACobrar = :totalACobrar")})
public class Factura implements Serializable {

    @Column(name = "formato")
    private Integer formato;

    @Column(name = "suplidor_de_venta")
    private Integer suplidorDeVenta;

    /**
     * @return the compatibilidad
     */
    public Boolean getCompatibilidad() {
        return compatibilidad;
    }

    /**
     * @param compatibilidad the compatibilidad to set
     */
    public void setCompatibilidad(Boolean compatibilidad) {
        this.compatibilidad = compatibilidad;
    }

    @Column(name = "contrato")
    private Integer contrato;
    
    @Column(name = "compatibilidad")
    private Boolean compatibilidad;

    @Column(name = "monto_itbis_excento")
    private Double montoItbisExcento;

    @Lob
    @Column(name = "concepto")
    private String concepto;
    @Column(name = "monto_excento")
    private Double montoExcento;
    @Column(name = "monto_gravado")
    private Double montoGravado;

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
    @Basic(optional = false)
    @Column(name = "imprimir")
    private boolean imprimir;
    @Column(name = "nombre_tipo_ncf")
    private String nombreTipoNcf;
    @Column(name = "fecha_retencion")
    @Temporal(TemporalType.DATE)
    private Date fechaRetencion;
    @Column(name = "isr_retenido")
    private Double isrRetenido;
    @Column(name = "itbis_retenido")
    private Double itbisRetenido;
    @Column(name = "isr_percibido")
    private Double isrPercibido;
    @Column(name = "itbis_percibido")
    private Double itbisPercibido;
    @Column(name = "impuesto_selectivo_al_comsumo")
    private Double impuestoSelectivoAlComsumo;
    @Column(name = "otros_impuesto_tasa")
    private Double otrosImpuestoTasa;
    @Column(name = "monto_propina_legal")
    private Double montoPropinaLegal;
    @Column(name = "efectivo")
    private Double efectivo;
    @Column(name = "cheque_transferencia_deposito")
    private Double chequeTransferenciaDeposito;
    @Column(name = "tarjeta_debito_credito")
    private Double tarjetaDebitoCredito;
    @Column(name = "venta_a_credito")
    private Double ventaACredito;
    @Column(name = "bonos_o_certificado")
    private Double bonosOCertificado;
    @Column(name = "permuta")
    private Double permuta;
    @Column(name = "otras_forma_de_venta")
    private Double otrasFormaDeVenta;
    @Column(name = "total_a_cobrar")
    private Double totalACobrar;
    @OneToMany(mappedBy = "factura")
    private Collection<DetalleReciboIngreso> detalleReciboIngresoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    private Collection<GestionDeCobro> gestionDeCobroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    private Collection<FormaPagoFactura> formaPagoFacturaCollection;
    @OneToMany(mappedBy = "factura",cascade = CascadeType.ALL)
    private Collection<DetalleFactura> detalleFacturaCollection;
    @OneToMany(mappedBy = "factura")
    private Collection<Conduce> conduceCollection;
    @JoinColumn(name = "condicion_pago", referencedColumnName = "codigo")
    @ManyToOne
    private CondicionPago condicionPago;
    @JoinColumn(name = "cliente", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "plazo", referencedColumnName = "codigo")
    @ManyToOne
    private Plazo plazo;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "tipo_ncf", referencedColumnName = "codigo")
    @ManyToOne
    private TipoNcf tipoNcf;
    @JoinColumn(name = "origen_factura", referencedColumnName = "codigo")
    @ManyToOne
    private OrigenFactura origenFactura;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "vendedor", referencedColumnName = "codigo")
    @ManyToOne
    private EjecutivoDeVenta vendedor;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "cotizacion_de_venta", referencedColumnName = "codigo")
    @ManyToOne
    private CotizacionDeVenta cotizacionDeVenta;
    @JoinColumn(name = "pedido", referencedColumnName = "codigo")
    @ManyToOne
    private Pedido pedido;
    @JoinColumn(name = "tipo_de_ingreso", referencedColumnName = "codigo")
    @ManyToOne
    private TipoDeIngreso tipoDeIngreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    private Collection<DetalleCorteDeFacturacion> detalleCorteDeFacturacionCollection;
    @OneToMany(mappedBy = "factura")
    private Collection<DevolucionDeInventario> devolucionDeInventarioCollection;

    public Factura() {
    }

    public Factura(Integer codigo) {
        this.codigo = codigo;
    }

    public Factura(Integer codigo, boolean despachada, boolean enviadaPorCorreo, boolean imprimir) {
        this.codigo = codigo;
        this.despachada = despachada;
        this.enviadaPorCorreo = enviadaPorCorreo;
        this.imprimir = imprimir;
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

    public boolean getImprimir() {
        return imprimir;
    }

    public void setImprimir(boolean imprimir) {
        this.imprimir = imprimir;
    }

    public String getNombreTipoNcf() {
        return nombreTipoNcf;
    }

    public void setNombreTipoNcf(String nombreTipoNcf) {
        this.nombreTipoNcf = nombreTipoNcf;
    }

    public Date getFechaRetencion() {
        return fechaRetencion;
    }

    public void setFechaRetencion(Date fechaRetencion) {
        this.fechaRetencion = fechaRetencion;
    }

    public Double getIsrRetenido() {
        return isrRetenido;
    }

    public void setIsrRetenido(Double isrRetenido) {
        this.isrRetenido = isrRetenido;
    }

    public Double getItbisRetenido() {
        return itbisRetenido;
    }

    public void setItbisRetenido(Double itbisRetenido) {
        this.itbisRetenido = itbisRetenido;
    }

    public Double getIsrPercibido() {
        return isrPercibido;
    }

    public void setIsrPercibido(Double isrPercibido) {
        this.isrPercibido = isrPercibido;
    }

    public Double getItbisPercibido() {
        return itbisPercibido;
    }

    public void setItbisPercibido(Double itbisPercibido) {
        this.itbisPercibido = itbisPercibido;
    }

    public Double getImpuestoSelectivoAlComsumo() {
        return impuestoSelectivoAlComsumo;
    }

    public void setImpuestoSelectivoAlComsumo(Double impuestoSelectivoAlComsumo) {
        this.impuestoSelectivoAlComsumo = impuestoSelectivoAlComsumo;
    }

    public Double getOtrosImpuestoTasa() {
        return otrosImpuestoTasa;
    }

    public void setOtrosImpuestoTasa(Double otrosImpuestoTasa) {
        this.otrosImpuestoTasa = otrosImpuestoTasa;
    }

    public Double getMontoPropinaLegal() {
        return montoPropinaLegal;
    }

    public void setMontoPropinaLegal(Double montoPropinaLegal) {
        this.montoPropinaLegal = montoPropinaLegal;
    }

    public Double getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(Double efectivo) {
        this.efectivo = efectivo;
    }

    public Double getChequeTransferenciaDeposito() {
        return chequeTransferenciaDeposito;
    }

    public void setChequeTransferenciaDeposito(Double chequeTransferenciaDeposito) {
        this.chequeTransferenciaDeposito = chequeTransferenciaDeposito;
    }

    public Double getTarjetaDebitoCredito() {
        return tarjetaDebitoCredito;
    }

    public void setTarjetaDebitoCredito(Double tarjetaDebitoCredito) {
        this.tarjetaDebitoCredito = tarjetaDebitoCredito;
    }

    public Double getVentaACredito() {
        return ventaACredito;
    }

    public void setVentaACredito(Double ventaACredito) {
        this.ventaACredito = ventaACredito;
    }

    public Double getBonosOCertificado() {
        return bonosOCertificado;
    }

    public void setBonosOCertificado(Double bonosOCertificado) {
        this.bonosOCertificado = bonosOCertificado;
    }

    public Double getPermuta() {
        return permuta;
    }

    public void setPermuta(Double permuta) {
        this.permuta = permuta;
    }

    public Double getOtrasFormaDeVenta() {
        return otrasFormaDeVenta;
    }

    public void setOtrasFormaDeVenta(Double otrasFormaDeVenta) {
        this.otrasFormaDeVenta = otrasFormaDeVenta;
    }

    public Double getTotalACobrar() {
        return totalACobrar;
    }

    public void setTotalACobrar(Double totalACobrar) {
        this.totalACobrar = totalACobrar;
    }

    @XmlTransient
    public Collection<DetalleReciboIngreso> getDetalleReciboIngresoCollection() {
        return detalleReciboIngresoCollection;
    }

    public void setDetalleReciboIngresoCollection(Collection<DetalleReciboIngreso> detalleReciboIngresoCollection) {
        this.detalleReciboIngresoCollection = detalleReciboIngresoCollection;
    }

    @XmlTransient
    public Collection<GestionDeCobro> getGestionDeCobroCollection() {
        return gestionDeCobroCollection;
    }

    public void setGestionDeCobroCollection(Collection<GestionDeCobro> gestionDeCobroCollection) {
        this.gestionDeCobroCollection = gestionDeCobroCollection;
    }

    @XmlTransient
    public Collection<FormaPagoFactura> getFormaPagoFacturaCollection() {
        return formaPagoFacturaCollection;
    }

    public void setFormaPagoFacturaCollection(Collection<FormaPagoFactura> formaPagoFacturaCollection) {
        this.formaPagoFacturaCollection = formaPagoFacturaCollection;
    }

    @XmlTransient
    public Collection<DetalleFactura> getDetalleFacturaCollection() {
        return detalleFacturaCollection;
    }

    public void setDetalleFacturaCollection(Collection<DetalleFactura> detalleFacturaCollection) {
        this.detalleFacturaCollection = detalleFacturaCollection;
    }

    @XmlTransient
    public Collection<Conduce> getConduceCollection() {
        return conduceCollection;
    }

    public void setConduceCollection(Collection<Conduce> conduceCollection) {
        this.conduceCollection = conduceCollection;
    }

    public CondicionPago getCondicionPago() {
        return condicionPago;
    }

    public void setCondicionPago(CondicionPago condicionPago) {
        this.condicionPago = condicionPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public OrigenFactura getOrigenFactura() {
        return origenFactura;
    }

    public void setOrigenFactura(OrigenFactura origenFactura) {
        this.origenFactura = origenFactura;
    }

    public SecuenciaDocumento getSecuenciaDocumento() {
        return secuenciaDocumento;
    }

    public void setSecuenciaDocumento(SecuenciaDocumento secuenciaDocumento) {
        this.secuenciaDocumento = secuenciaDocumento;
    }

    public EjecutivoDeVenta getVendedor() {
        return vendedor;
    }

    public void setVendedor(EjecutivoDeVenta vendedor) {
        this.vendedor = vendedor;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public TipoDeIngreso getTipoDeIngreso() {
        return tipoDeIngreso;
    }

    public void setTipoDeIngreso(TipoDeIngreso tipoDeIngreso) {
        this.tipoDeIngreso = tipoDeIngreso;
    }

    @XmlTransient
    public Collection<DetalleCorteDeFacturacion> getDetalleCorteDeFacturacionCollection() {
        return detalleCorteDeFacturacionCollection;
    }

    public void setDetalleCorteDeFacturacionCollection(Collection<DetalleCorteDeFacturacion> detalleCorteDeFacturacionCollection) {
        this.detalleCorteDeFacturacionCollection = detalleCorteDeFacturacionCollection;
    }

    @XmlTransient
    public Collection<DevolucionDeInventario> getDevolucionDeInventarioCollection() {
        return devolucionDeInventarioCollection;
    }

    public void setDevolucionDeInventarioCollection(Collection<DevolucionDeInventario> devolucionDeInventarioCollection) {
        this.devolucionDeInventarioCollection = devolucionDeInventarioCollection;
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
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Factura[ codigo=" + codigo + " ]";
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getMontoExcento() {
        return montoExcento;
    }

    public void setMontoExcento(Double montoExcento) {
        this.montoExcento = montoExcento;
    }

    public Double getMontoGravado() {
        return montoGravado;
    }

    public void setMontoGravado(Double montoGravado) {
        this.montoGravado = montoGravado;
    }

    public Double getMontoItbisExcento() {
        return montoItbisExcento;
    }

    public void setMontoItbisExcento(Double montoItbisExcento) {
        this.montoItbisExcento = montoItbisExcento;
    }

    public Integer getContrato() {
        return contrato;
    }

    public void setContrato(Integer contrato) {
        this.contrato = contrato;
    }

    public Integer getSuplidorDeVenta() {
        return suplidorDeVenta;
    }

    public void setSuplidorDeVenta(Integer suplidorDeVenta) {
        this.suplidorDeVenta = suplidorDeVenta;
    }

    public Integer getFormato() {
        return formato;
    }

    public void setFormato(Integer formato) {
        this.formato = formato;
    }

    
}
