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
@Table(name = "detalle_formato_606")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleFormato606.findAll", query = "SELECT d FROM DetalleFormato606 d")
    , @NamedQuery(name = "DetalleFormato606.findByCodigo", query = "SELECT d FROM DetalleFormato606 d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleFormato606.findByRncOCedula", query = "SELECT d FROM DetalleFormato606 d WHERE d.rncOCedula = :rncOCedula")
    , @NamedQuery(name = "DetalleFormato606.findByTipoId", query = "SELECT d FROM DetalleFormato606 d WHERE d.tipoId = :tipoId")
    , @NamedQuery(name = "DetalleFormato606.findByNcf", query = "SELECT d FROM DetalleFormato606 d WHERE d.ncf = :ncf")
    , @NamedQuery(name = "DetalleFormato606.findByNcfModificado", query = "SELECT d FROM DetalleFormato606 d WHERE d.ncfModificado = :ncfModificado")
    , @NamedQuery(name = "DetalleFormato606.findByFechaComprobante", query = "SELECT d FROM DetalleFormato606 d WHERE d.fechaComprobante = :fechaComprobante")
    , @NamedQuery(name = "DetalleFormato606.findByFechaPago", query = "SELECT d FROM DetalleFormato606 d WHERE d.fechaPago = :fechaPago")
    , @NamedQuery(name = "DetalleFormato606.findByMontoEnServicio", query = "SELECT d FROM DetalleFormato606 d WHERE d.montoEnServicio = :montoEnServicio")
    , @NamedQuery(name = "DetalleFormato606.findByMontoEnBienes", query = "SELECT d FROM DetalleFormato606 d WHERE d.montoEnBienes = :montoEnBienes")
    , @NamedQuery(name = "DetalleFormato606.findByTotalFacturado", query = "SELECT d FROM DetalleFormato606 d WHERE d.totalFacturado = :totalFacturado")
    , @NamedQuery(name = "DetalleFormato606.findByItbisFacturado", query = "SELECT d FROM DetalleFormato606 d WHERE d.itbisFacturado = :itbisFacturado")
    , @NamedQuery(name = "DetalleFormato606.findByItbisRetenido", query = "SELECT d FROM DetalleFormato606 d WHERE d.itbisRetenido = :itbisRetenido")
    , @NamedQuery(name = "DetalleFormato606.findByItbisSujetoAProporcionalidad", query = "SELECT d FROM DetalleFormato606 d WHERE d.itbisSujetoAProporcionalidad = :itbisSujetoAProporcionalidad")
    , @NamedQuery(name = "DetalleFormato606.findByItbisLlevadoAlCosto", query = "SELECT d FROM DetalleFormato606 d WHERE d.itbisLlevadoAlCosto = :itbisLlevadoAlCosto")
    , @NamedQuery(name = "DetalleFormato606.findByItbisPorAdelantar", query = "SELECT d FROM DetalleFormato606 d WHERE d.itbisPorAdelantar = :itbisPorAdelantar")
    , @NamedQuery(name = "DetalleFormato606.findByItbisPercibidoEnCompra", query = "SELECT d FROM DetalleFormato606 d WHERE d.itbisPercibidoEnCompra = :itbisPercibidoEnCompra")
    , @NamedQuery(name = "DetalleFormato606.findByIsrRetenido", query = "SELECT d FROM DetalleFormato606 d WHERE d.isrRetenido = :isrRetenido")
    , @NamedQuery(name = "DetalleFormato606.findByNombreFormaDePago", query = "SELECT d FROM DetalleFormato606 d WHERE d.nombreFormaDePago = :nombreFormaDePago")
    , @NamedQuery(name = "DetalleFormato606.findByIsrPercibidoEnCompra", query = "SELECT d FROM DetalleFormato606 d WHERE d.isrPercibidoEnCompra = :isrPercibidoEnCompra")
    , @NamedQuery(name = "DetalleFormato606.findByImpuestoSelectivoAlComsumo", query = "SELECT d FROM DetalleFormato606 d WHERE d.impuestoSelectivoAlComsumo = :impuestoSelectivoAlComsumo")
    , @NamedQuery(name = "DetalleFormato606.findByOtrosImpuestoTasa", query = "SELECT d FROM DetalleFormato606 d WHERE d.otrosImpuestoTasa = :otrosImpuestoTasa")
    , @NamedQuery(name = "DetalleFormato606.findByMontoPropinaLegal", query = "SELECT d FROM DetalleFormato606 d WHERE d.montoPropinaLegal = :montoPropinaLegal")
    , @NamedQuery(name = "DetalleFormato606.findByNombreSuplidor", query = "SELECT d FROM DetalleFormato606 d WHERE d.nombreSuplidor = :nombreSuplidor")
    , @NamedQuery(name = "DetalleFormato606.findBySuplidor", query = "SELECT d FROM DetalleFormato606 d WHERE d.suplidor = :suplidor")
    , @NamedQuery(name = "DetalleFormato606.findByFactura", query = "SELECT d FROM DetalleFormato606 d WHERE d.factura = :factura")})
public class DetalleFormato606 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "rnc_o_cedula")
    private String rncOCedula;
    @Column(name = "tipo_id")
    private Integer tipoId;
    @Column(name = "ncf")
    private String ncf;
    @Column(name = "ncf_modificado")
    private String ncfModificado;
    @Column(name = "fecha_comprobante")
    @Temporal(TemporalType.DATE)
    private Date fechaComprobante;
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto_en_servicio")
    private Double montoEnServicio;
    @Column(name = "monto_en_bienes")
    private Double montoEnBienes;
    @Column(name = "total_facturado")
    private Double totalFacturado;
    @Basic(optional = false)
    @Column(name = "itbis_facturado")
    private double itbisFacturado;
    @Column(name = "itbis_retenido")
    private Double itbisRetenido;
    @Column(name = "itbis_sujeto_a_proporcionalidad")
    private Double itbisSujetoAProporcionalidad;
    @Column(name = "itbis_llevado_al_costo")
    private Double itbisLlevadoAlCosto;
    @Column(name = "itbis_por_adelantar")
    private Double itbisPorAdelantar;
    @Column(name = "itbis_percibido_en_compra")
    private Double itbisPercibidoEnCompra;
    @Column(name = "isr_retenido")
    private Double isrRetenido;
    @Column(name = "nombre_forma_de_pago")
    private String nombreFormaDePago;
    @Column(name = "isr_percibido_en_compra")
    private Double isrPercibidoEnCompra;
    @Column(name = "impuesto_selectivo_al_comsumo")
    private Double impuestoSelectivoAlComsumo;
    @Column(name = "otros_impuesto_tasa")
    private Double otrosImpuestoTasa;
    @Column(name = "monto_propina_legal")
    private Double montoPropinaLegal;
    @Column(name = "nombre_suplidor")
    private String nombreSuplidor;
    @Column(name = "suplidor")
    private Integer suplidor;
    @Column(name = "factura")
    private Integer factura;
    @JoinColumn(name = "tipo_retencion_isr", referencedColumnName = "codigo")
    @ManyToOne
    private TipoDeRetencionIsr tipoRetencionIsr;
    @JoinColumn(name = "tipo_bienes_y_servicios", referencedColumnName = "codigo")
    @ManyToOne
    private CostosYGastos tipoBienesYServicios;
    @JoinColumn(name = "forma_de_pago", referencedColumnName = "codigo")
    @ManyToOne
    private FormaPagoDgii formaDePago;
    @JoinColumn(name = "formato_606", referencedColumnName = "codigo")
    @ManyToOne
    private Formato606 formato606;

    public DetalleFormato606() {
    }

    public DetalleFormato606(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleFormato606(Integer codigo, double itbisFacturado) {
        this.codigo = codigo;
        this.itbisFacturado = itbisFacturado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getRncOCedula() {
        return rncOCedula;
    }

    public void setRncOCedula(String rncOCedula) {
        this.rncOCedula = rncOCedula;
    }

    public Integer getTipoId() {
        return tipoId;
    }

    public void setTipoId(Integer tipoId) {
        this.tipoId = tipoId;
    }

    public String getNcf() {
        return ncf;
    }

    public void setNcf(String ncf) {
        this.ncf = ncf;
    }

    public String getNcfModificado() {
        return ncfModificado;
    }

    public void setNcfModificado(String ncfModificado) {
        this.ncfModificado = ncfModificado;
    }

    public Date getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(Date fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getMontoEnServicio() {
        return montoEnServicio;
    }

    public void setMontoEnServicio(Double montoEnServicio) {
        this.montoEnServicio = montoEnServicio;
    }

    public Double getMontoEnBienes() {
        return montoEnBienes;
    }

    public void setMontoEnBienes(Double montoEnBienes) {
        this.montoEnBienes = montoEnBienes;
    }

    public Double getTotalFacturado() {
        return totalFacturado;
    }

    public void setTotalFacturado(Double totalFacturado) {
        this.totalFacturado = totalFacturado;
    }

    public double getItbisFacturado() {
        return itbisFacturado;
    }

    public void setItbisFacturado(double itbisFacturado) {
        this.itbisFacturado = itbisFacturado;
    }

    public Double getItbisRetenido() {
        return itbisRetenido;
    }

    public void setItbisRetenido(Double itbisRetenido) {
        this.itbisRetenido = itbisRetenido;
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

    public Double getItbisPercibidoEnCompra() {
        return itbisPercibidoEnCompra;
    }

    public void setItbisPercibidoEnCompra(Double itbisPercibidoEnCompra) {
        this.itbisPercibidoEnCompra = itbisPercibidoEnCompra;
    }

    public Double getIsrRetenido() {
        return isrRetenido;
    }

    public void setIsrRetenido(Double isrRetenido) {
        this.isrRetenido = isrRetenido;
    }

    public String getNombreFormaDePago() {
        return nombreFormaDePago;
    }

    public void setNombreFormaDePago(String nombreFormaDePago) {
        this.nombreFormaDePago = nombreFormaDePago;
    }

    public Double getIsrPercibidoEnCompra() {
        return isrPercibidoEnCompra;
    }

    public void setIsrPercibidoEnCompra(Double isrPercibidoEnCompra) {
        this.isrPercibidoEnCompra = isrPercibidoEnCompra;
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

    public String getNombreSuplidor() {
        return nombreSuplidor;
    }

    public void setNombreSuplidor(String nombreSuplidor) {
        this.nombreSuplidor = nombreSuplidor;
    }

    public Integer getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Integer suplidor) {
        this.suplidor = suplidor;
    }

    public Integer getFactura() {
        return factura;
    }

    public void setFactura(Integer factura) {
        this.factura = factura;
    }

    public TipoDeRetencionIsr getTipoRetencionIsr() {
        return tipoRetencionIsr;
    }

    public void setTipoRetencionIsr(TipoDeRetencionIsr tipoRetencionIsr) {
        this.tipoRetencionIsr = tipoRetencionIsr;
    }

    public CostosYGastos getTipoBienesYServicios() {
        return tipoBienesYServicios;
    }

    public void setTipoBienesYServicios(CostosYGastos tipoBienesYServicios) {
        this.tipoBienesYServicios = tipoBienesYServicios;
    }

    public FormaPagoDgii getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(FormaPagoDgii formaDePago) {
        this.formaDePago = formaDePago;
    }

    public Formato606 getFormato606() {
        return formato606;
    }

    public void setFormato606(Formato606 formato606) {
        this.formato606 = formato606;
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
        if (!(object instanceof DetalleFormato606)) {
            return false;
        }
        DetalleFormato606 other = (DetalleFormato606) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleFormato606[ codigo=" + codigo + " ]";
    }
    
}
