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
@Table(name = "factura_suplidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacturaSuplidor.findAll", query = "SELECT f FROM FacturaSuplidor f")
    , @NamedQuery(name = "FacturaSuplidor.findByCodigo", query = "SELECT f FROM FacturaSuplidor f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "FacturaSuplidor.findByFecha", query = "SELECT f FROM FacturaSuplidor f WHERE f.fecha = :fecha")
    , @NamedQuery(name = "FacturaSuplidor.findByNoFactura", query = "SELECT f FROM FacturaSuplidor f WHERE f.noFactura = :noFactura")
    , @NamedQuery(name = "FacturaSuplidor.findByNcf", query = "SELECT f FROM FacturaSuplidor f WHERE f.ncf = :ncf")
    , @NamedQuery(name = "FacturaSuplidor.findByOrdenCompra", query = "SELECT f FROM FacturaSuplidor f WHERE f.ordenCompra = :ordenCompra")
    , @NamedQuery(name = "FacturaSuplidor.findByNombreSuplidor", query = "SELECT f FROM FacturaSuplidor f WHERE f.nombreSuplidor = :nombreSuplidor")
    , @NamedQuery(name = "FacturaSuplidor.findBySubTotal", query = "SELECT f FROM FacturaSuplidor f WHERE f.subTotal = :subTotal")
    , @NamedQuery(name = "FacturaSuplidor.findByTotalDescuento", query = "SELECT f FROM FacturaSuplidor f WHERE f.totalDescuento = :totalDescuento")
    , @NamedQuery(name = "FacturaSuplidor.findBySubTotalConDescuento", query = "SELECT f FROM FacturaSuplidor f WHERE f.subTotalConDescuento = :subTotalConDescuento")
    , @NamedQuery(name = "FacturaSuplidor.findByItbis", query = "SELECT f FROM FacturaSuplidor f WHERE f.itbis = :itbis")
    , @NamedQuery(name = "FacturaSuplidor.findByIsr", query = "SELECT f FROM FacturaSuplidor f WHERE f.isr = :isr")
    , @NamedQuery(name = "FacturaSuplidor.findByTotal", query = "SELECT f FROM FacturaSuplidor f WHERE f.total = :total")
    , @NamedQuery(name = "FacturaSuplidor.findByTotalAPagar", query = "SELECT f FROM FacturaSuplidor f WHERE f.totalAPagar = :totalAPagar")
    , @NamedQuery(name = "FacturaSuplidor.findByTotalPagado", query = "SELECT f FROM FacturaSuplidor f WHERE f.totalPagado = :totalPagado")
    , @NamedQuery(name = "FacturaSuplidor.findByTotalPendiente", query = "SELECT f FROM FacturaSuplidor f WHERE f.totalPendiente = :totalPendiente")
    , @NamedQuery(name = "FacturaSuplidor.findByFechaVencimiento", query = "SELECT f FROM FacturaSuplidor f WHERE f.fechaVencimiento = :fechaVencimiento")
    , @NamedQuery(name = "FacturaSuplidor.findByAnulada", query = "SELECT f FROM FacturaSuplidor f WHERE f.anulada = :anulada")
    , @NamedQuery(name = "FacturaSuplidor.findBySolicitud", query = "SELECT f FROM FacturaSuplidor f WHERE f.solicitud = :solicitud")
    , @NamedQuery(name = "FacturaSuplidor.findByTipoConcepto", query = "SELECT f FROM FacturaSuplidor f WHERE f.tipoConcepto = :tipoConcepto")
    , @NamedQuery(name = "FacturaSuplidor.findByNumeroSolicitud", query = "SELECT f FROM FacturaSuplidor f WHERE f.numeroSolicitud = :numeroSolicitud")
    , @NamedQuery(name = "FacturaSuplidor.findByFechaRegistro", query = "SELECT f FROM FacturaSuplidor f WHERE f.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "FacturaSuplidor.findByFechaAnulada", query = "SELECT f FROM FacturaSuplidor f WHERE f.fechaAnulada = :fechaAnulada")
    , @NamedQuery(name = "FacturaSuplidor.findByPagada", query = "SELECT f FROM FacturaSuplidor f WHERE f.pagada = :pagada")
    , @NamedQuery(name = "FacturaSuplidor.findByPendiente", query = "SELECT f FROM FacturaSuplidor f WHERE f.pendiente = :pendiente")
    , @NamedQuery(name = "FacturaSuplidor.findByAbono", query = "SELECT f FROM FacturaSuplidor f WHERE f.abono = :abono")
    , @NamedQuery(name = "FacturaSuplidor.findByNumero", query = "SELECT f FROM FacturaSuplidor f WHERE f.numero = :numero")
    , @NamedQuery(name = "FacturaSuplidor.findByTotalItbisRetenido", query = "SELECT f FROM FacturaSuplidor f WHERE f.totalItbisRetenido = :totalItbisRetenido")
    , @NamedQuery(name = "FacturaSuplidor.findBySuplidorCxp", query = "SELECT f FROM FacturaSuplidor f WHERE f.suplidorCxp = :suplidorCxp")
    , @NamedQuery(name = "FacturaSuplidor.findByNombreFormaDePago", query = "SELECT f FROM FacturaSuplidor f WHERE f.nombreFormaDePago = :nombreFormaDePago")
    , @NamedQuery(name = "FacturaSuplidor.findByItbisSujetoAProporcionalidad", query = "SELECT f FROM FacturaSuplidor f WHERE f.itbisSujetoAProporcionalidad = :itbisSujetoAProporcionalidad")
    , @NamedQuery(name = "FacturaSuplidor.findByItbisLlevadoAlCosto", query = "SELECT f FROM FacturaSuplidor f WHERE f.itbisLlevadoAlCosto = :itbisLlevadoAlCosto")
    , @NamedQuery(name = "FacturaSuplidor.findByItbisPorAdelantar", query = "SELECT f FROM FacturaSuplidor f WHERE f.itbisPorAdelantar = :itbisPorAdelantar")
    , @NamedQuery(name = "FacturaSuplidor.findByFechaPago", query = "SELECT f FROM FacturaSuplidor f WHERE f.fechaPago = :fechaPago")
    , @NamedQuery(name = "FacturaSuplidor.findByFechaActualizacion", query = "SELECT f FROM FacturaSuplidor f WHERE f.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "FacturaSuplidor.findByPropinaLegal", query = "SELECT f FROM FacturaSuplidor f WHERE f.propinaLegal = :propinaLegal")
    , @NamedQuery(name = "FacturaSuplidor.findByImpuestoSelectivoAlConsumo", query = "SELECT f FROM FacturaSuplidor f WHERE f.impuestoSelectivoAlConsumo = :impuestoSelectivoAlConsumo")
    , @NamedQuery(name = "FacturaSuplidor.findByOtrosImpuestoYOTasa", query = "SELECT f FROM FacturaSuplidor f WHERE f.otrosImpuestoYOTasa = :otrosImpuestoYOTasa")
    , @NamedQuery(name = "FacturaSuplidor.findByMontoItbisExcento", query = "SELECT f FROM FacturaSuplidor f WHERE f.montoItbisExcento = :montoItbisExcento")})
public class FacturaSuplidor implements Serializable {

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
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "no_factura")
    private String noFactura;
    @Column(name = "ncf")
    private String ncf;
    @Column(name = "orden_compra")
    private Integer ordenCompra;
    @Column(name = "nombre_suplidor")
    private String nombreSuplidor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sub_total")
    private Double subTotal;
    @Column(name = "total_descuento")
    private Double totalDescuento;
    @Column(name = "sub_total_con_descuento")
    private Double subTotalConDescuento;
    @Basic(optional = false)
    @Column(name = "itbis")
    private double itbis;
    @Basic(optional = false)
    @Column(name = "isr")
    private double isr;
    @Column(name = "total")
    private Double total;
    @Column(name = "total_a_pagar")
    private Double totalAPagar;
    @Column(name = "total_pagado")
    private Double totalPagado;
    @Column(name = "total_pendiente")
    private Double totalPendiente;
    @Lob
    @Column(name = "concepto")
    private String concepto;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Column(name = "anulada")
    private Boolean anulada;
    @Basic(optional = false)
    @Column(name = "solicitud")
    private boolean solicitud;
    @Column(name = "tipo_concepto")
    private Integer tipoConcepto;
    @Column(name = "numero_solicitud")
    private Integer numeroSolicitud;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fecha_anulada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulada;
    @Basic(optional = false)
    @Column(name = "pagada")
    private boolean pagada;
    @Basic(optional = false)
    @Column(name = "pendiente")
    private double pendiente;
    @Basic(optional = false)
    @Column(name = "abono")
    private double abono;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "total_itbis_retenido")
    private Double totalItbisRetenido;
    @Column(name = "suplidor_cxp")
    private Integer suplidorCxp;
    @Column(name = "nombre_forma_de_pago")
    private String nombreFormaDePago;
    @Column(name = "itbis_sujeto_a_proporcionalidad")
    private Double itbisSujetoAProporcionalidad;
    @Column(name = "itbis_llevado_al_costo")
    private Double itbisLlevadoAlCosto;
    @Column(name = "itbis_por_adelantar")
    private Double itbisPorAdelantar;
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name = "propina_legal")
    private Double propinaLegal;
    @Column(name = "impuesto_selectivo_al_consumo")
    private Double impuestoSelectivoAlConsumo;
    @Column(name = "otros_impuesto_y_o_tasa")
    private Double otrosImpuestoYOTasa;
    @Column(name = "monto_itbis_excento")
    private Double montoItbisExcento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturaSuplidor",orphanRemoval = true)
    private Collection<DetalleFacturaSuplidor> detalleFacturaSuplidorCollection;
    @JoinColumn(name = "suplidor", referencedColumnName = "codigo")
    @ManyToOne
    private Suplidor suplidor;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "proyecto", referencedColumnName = "codigo")
    @ManyToOne
    private Proyecto proyecto;
    @JoinColumn(name = "costos_y_gastos", referencedColumnName = "codigo")
    @ManyToOne
    private CostosYGastos costosYGastos;
    @JoinColumn(name = "forma_de_pago", referencedColumnName = "codigo")
    @ManyToOne
    private FormaPagoDgii formaDePago;
    @JoinColumn(name = "tipo_isr", referencedColumnName = "codigo")
    @ManyToOne
    private TipoDeRetencionIsr tipoIsr;
    @OneToMany(mappedBy = "factura")
    private Collection<DetalleComprobanteDePago> detalleComprobanteDePagoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturaSuplidor")
    private Collection<DetalleSolicitudCheque> detalleSolicitudChequeCollection;

    public FacturaSuplidor() {
    }

    public FacturaSuplidor(Integer codigo) {
        this.codigo = codigo;
    }

    public FacturaSuplidor(Integer codigo, double itbis, double isr, boolean solicitud, boolean pagada, double pendiente, double abono) {
        this.codigo = codigo;
        this.itbis = itbis;
        this.isr = isr;
        this.solicitud = solicitud;
        this.pagada = pagada;
        this.pendiente = pendiente;
        this.abono = abono;
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

    public String getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(String noFactura) {
        this.noFactura = noFactura;
    }

    public String getNcf() {
        return ncf;
    }

    public void setNcf(String ncf) {
        this.ncf = ncf;
    }

    public Integer getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(Integer ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public String getNombreSuplidor() {
        return nombreSuplidor;
    }

    public void setNombreSuplidor(String nombreSuplidor) {
        this.nombreSuplidor = nombreSuplidor;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(Double totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public Double getSubTotalConDescuento() {
        return subTotalConDescuento;
    }

    public void setSubTotalConDescuento(Double subTotalConDescuento) {
        this.subTotalConDescuento = subTotalConDescuento;
    }

    public double getItbis() {
        return itbis;
    }

    public void setItbis(double itbis) {
        this.itbis = itbis;
    }

    public double getIsr() {
        return isr;
    }

    public void setIsr(double isr) {
        this.isr = isr;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(Double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public Double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(Double totalPagado) {
        this.totalPagado = totalPagado;
    }

    public Double getTotalPendiente() {
        return totalPendiente;
    }

    public void setTotalPendiente(Double totalPendiente) {
        this.totalPendiente = totalPendiente;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(Boolean anulada) {
        this.anulada = anulada;
    }

    public boolean getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(boolean solicitud) {
        this.solicitud = solicitud;
    }

    public Integer getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(Integer tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }

    public Integer getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(Integer numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaAnulada() {
        return fechaAnulada;
    }

    public void setFechaAnulada(Date fechaAnulada) {
        this.fechaAnulada = fechaAnulada;
    }

    public boolean getPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public double getPendiente() {
        return pendiente;
    }

    public void setPendiente(double pendiente) {
        this.pendiente = pendiente;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Double getTotalItbisRetenido() {
        return totalItbisRetenido;
    }

    public void setTotalItbisRetenido(Double totalItbisRetenido) {
        this.totalItbisRetenido = totalItbisRetenido;
    }

    public Integer getSuplidorCxp() {
        return suplidorCxp;
    }

    public void setSuplidorCxp(Integer suplidorCxp) {
        this.suplidorCxp = suplidorCxp;
    }

    public String getNombreFormaDePago() {
        return nombreFormaDePago;
    }

    public void setNombreFormaDePago(String nombreFormaDePago) {
        this.nombreFormaDePago = nombreFormaDePago;
    }

    public Double getItbisSujetoAProporcionalidad() {
        return itbisSujetoAProporcionalidad;
    }

    public void setItbisSujetoAProporcionalidad(Double itbisSujetoAProporcionalidad) {
        this.itbisSujetoAProporcionalidad = itbisSujetoAProporcionalidad;
    }

    public Double getItbisLlevadoAlCosto() {
        return itbisLlevadoAlCosto;
    }

    public void setItbisLlevadoAlCosto(Double itbisLlevadoAlCosto) {
        this.itbisLlevadoAlCosto = itbisLlevadoAlCosto;
    }

    public Double getItbisPorAdelantar() {
        return itbisPorAdelantar;
    }

    public void setItbisPorAdelantar(Double itbisPorAdelantar) {
        this.itbisPorAdelantar = itbisPorAdelantar;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Double getPropinaLegal() {
        return propinaLegal;
    }

    public void setPropinaLegal(Double propinaLegal) {
        this.propinaLegal = propinaLegal;
    }

    public Double getImpuestoSelectivoAlConsumo() {
        return impuestoSelectivoAlConsumo;
    }

    public void setImpuestoSelectivoAlConsumo(Double impuestoSelectivoAlConsumo) {
        this.impuestoSelectivoAlConsumo = impuestoSelectivoAlConsumo;
    }

    public Double getOtrosImpuestoYOTasa() {
        return otrosImpuestoYOTasa;
    }

    public void setOtrosImpuestoYOTasa(Double otrosImpuestoYOTasa) {
        this.otrosImpuestoYOTasa = otrosImpuestoYOTasa;
    }

    public Double getMontoItbisExcento() {
        return montoItbisExcento;
    }

    public void setMontoItbisExcento(Double montoItbisExcento) {
        this.montoItbisExcento = montoItbisExcento;
    }

    @XmlTransient
    public Collection<DetalleFacturaSuplidor> getDetalleFacturaSuplidorCollection() {
        return detalleFacturaSuplidorCollection;
    }

    public void setDetalleFacturaSuplidorCollection(Collection<DetalleFacturaSuplidor> detalleFacturaSuplidorCollection) {
        this.detalleFacturaSuplidorCollection = detalleFacturaSuplidorCollection;
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

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public CostosYGastos getCostosYGastos() {
        return costosYGastos;
    }

    public void setCostosYGastos(CostosYGastos costosYGastos) {
        this.costosYGastos = costosYGastos;
    }

    public FormaPagoDgii getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(FormaPagoDgii formaDePago) {
        this.formaDePago = formaDePago;
    }

    public TipoDeRetencionIsr getTipoIsr() {
        return tipoIsr;
    }

    public void setTipoIsr(TipoDeRetencionIsr tipoIsr) {
        this.tipoIsr = tipoIsr;
    }

    @XmlTransient
    public Collection<DetalleComprobanteDePago> getDetalleComprobanteDePagoCollection() {
        return detalleComprobanteDePagoCollection;
    }

    public void setDetalleComprobanteDePagoCollection(Collection<DetalleComprobanteDePago> detalleComprobanteDePagoCollection) {
        this.detalleComprobanteDePagoCollection = detalleComprobanteDePagoCollection;
    }

    @XmlTransient
    public Collection<DetalleSolicitudCheque> getDetalleSolicitudChequeCollection() {
        return detalleSolicitudChequeCollection;
    }

    public void setDetalleSolicitudChequeCollection(Collection<DetalleSolicitudCheque> detalleSolicitudChequeCollection) {
        this.detalleSolicitudChequeCollection = detalleSolicitudChequeCollection;
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
        if (!(object instanceof FacturaSuplidor)) {
            return false;
        }
        FacturaSuplidor other = (FacturaSuplidor) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.FacturaSuplidor[ codigo=" + codigo + " ]";
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
    
}
