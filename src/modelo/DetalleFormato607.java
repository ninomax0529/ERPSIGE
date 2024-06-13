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
@Table(name = "detalle_formato_607")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleFormato607.findAll", query = "SELECT d FROM DetalleFormato607 d")
    , @NamedQuery(name = "DetalleFormato607.findByCodigo", query = "SELECT d FROM DetalleFormato607 d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleFormato607.findByRncOCedula", query = "SELECT d FROM DetalleFormato607 d WHERE d.rncOCedula = :rncOCedula")
    , @NamedQuery(name = "DetalleFormato607.findByTipoId", query = "SELECT d FROM DetalleFormato607 d WHERE d.tipoId = :tipoId")
    , @NamedQuery(name = "DetalleFormato607.findByNcf", query = "SELECT d FROM DetalleFormato607 d WHERE d.ncf = :ncf")
    , @NamedQuery(name = "DetalleFormato607.findByNcfModificado", query = "SELECT d FROM DetalleFormato607 d WHERE d.ncfModificado = :ncfModificado")
    , @NamedQuery(name = "DetalleFormato607.findByTipoDeIngreso", query = "SELECT d FROM DetalleFormato607 d WHERE d.tipoDeIngreso = :tipoDeIngreso")
    , @NamedQuery(name = "DetalleFormato607.findByFechaComprobante", query = "SELECT d FROM DetalleFormato607 d WHERE d.fechaComprobante = :fechaComprobante")
    , @NamedQuery(name = "DetalleFormato607.findByFechaDeRetension", query = "SELECT d FROM DetalleFormato607 d WHERE d.fechaDeRetension = :fechaDeRetension")
    , @NamedQuery(name = "DetalleFormato607.findByTotalFacturado", query = "SELECT d FROM DetalleFormato607 d WHERE d.totalFacturado = :totalFacturado")
    , @NamedQuery(name = "DetalleFormato607.findByItbisFacturado", query = "SELECT d FROM DetalleFormato607 d WHERE d.itbisFacturado = :itbisFacturado")
    , @NamedQuery(name = "DetalleFormato607.findByItbisRetenidoPorTercero", query = "SELECT d FROM DetalleFormato607 d WHERE d.itbisRetenidoPorTercero = :itbisRetenidoPorTercero")
    , @NamedQuery(name = "DetalleFormato607.findByItbisPercibido", query = "SELECT d FROM DetalleFormato607 d WHERE d.itbisPercibido = :itbisPercibido")
    , @NamedQuery(name = "DetalleFormato607.findByIsrRetenidoPorTercero", query = "SELECT d FROM DetalleFormato607 d WHERE d.isrRetenidoPorTercero = :isrRetenidoPorTercero")
    , @NamedQuery(name = "DetalleFormato607.findByIsrPercibido", query = "SELECT d FROM DetalleFormato607 d WHERE d.isrPercibido = :isrPercibido")
    , @NamedQuery(name = "DetalleFormato607.findByImpuestoSelectivoAlComsumo", query = "SELECT d FROM DetalleFormato607 d WHERE d.impuestoSelectivoAlComsumo = :impuestoSelectivoAlComsumo")
    , @NamedQuery(name = "DetalleFormato607.findByOtrosImpuestoTasa", query = "SELECT d FROM DetalleFormato607 d WHERE d.otrosImpuestoTasa = :otrosImpuestoTasa")
    , @NamedQuery(name = "DetalleFormato607.findByMontoPropinaLegal", query = "SELECT d FROM DetalleFormato607 d WHERE d.montoPropinaLegal = :montoPropinaLegal")
    , @NamedQuery(name = "DetalleFormato607.findByEfectivo", query = "SELECT d FROM DetalleFormato607 d WHERE d.efectivo = :efectivo")
    , @NamedQuery(name = "DetalleFormato607.findByChqTransfDeposito", query = "SELECT d FROM DetalleFormato607 d WHERE d.chqTransfDeposito = :chqTransfDeposito")
    , @NamedQuery(name = "DetalleFormato607.findByTarjetaDebitoCredito", query = "SELECT d FROM DetalleFormato607 d WHERE d.tarjetaDebitoCredito = :tarjetaDebitoCredito")
    , @NamedQuery(name = "DetalleFormato607.findByVentaACredito", query = "SELECT d FROM DetalleFormato607 d WHERE d.ventaACredito = :ventaACredito")
    , @NamedQuery(name = "DetalleFormato607.findByBonosOCertificado", query = "SELECT d FROM DetalleFormato607 d WHERE d.bonosOCertificado = :bonosOCertificado")
    , @NamedQuery(name = "DetalleFormato607.findByPermuta", query = "SELECT d FROM DetalleFormato607 d WHERE d.permuta = :permuta")
    , @NamedQuery(name = "DetalleFormato607.findByOtrasFormaDeVenta", query = "SELECT d FROM DetalleFormato607 d WHERE d.otrasFormaDeVenta = :otrasFormaDeVenta")
    , @NamedQuery(name = "DetalleFormato607.findByNombreCliente", query = "SELECT d FROM DetalleFormato607 d WHERE d.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "DetalleFormato607.findByCliente", query = "SELECT d FROM DetalleFormato607 d WHERE d.cliente = :cliente")
    , @NamedQuery(name = "DetalleFormato607.findByFactura", query = "SELECT d FROM DetalleFormato607 d WHERE d.factura = :factura")
    , @NamedQuery(name = "DetalleFormato607.findByNombreTipoDeIngreso", query = "SELECT d FROM DetalleFormato607 d WHERE d.nombreTipoDeIngreso = :nombreTipoDeIngreso")})
public class DetalleFormato607 implements Serializable {

    @JoinColumn(name = "tipo__de_ingreso", referencedColumnName = "codigo")
    @ManyToOne
    private TipoDeIngreso tipoDeIngreso;

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
    @Column(name = "fecha_de_retension")
    @Temporal(TemporalType.DATE)
    private Date fechaDeRetension;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_facturado")
    private Double totalFacturado;
    @Basic(optional = false)
    @Column(name = "itbis_facturado")
    private double itbisFacturado;
    @Column(name = "itbis_retenido_por_tercero")
    private Double itbisRetenidoPorTercero;
    @Column(name = "itbis_percibido")
    private Double itbisPercibido;
    @Column(name = "isr_retenido_por_tercero")
    private Double isrRetenidoPorTercero;
    @Column(name = "isr_percibido")
    private Double isrPercibido;
    @Column(name = "impuesto_selectivo_al_comsumo")
    private Double impuestoSelectivoAlComsumo;
    @Column(name = "otros_impuesto_tasa")
    private Double otrosImpuestoTasa;
    @Column(name = "monto_propina_legal")
    private Double montoPropinaLegal;
    @Column(name = "efectivo")
    private Double efectivo;
    @Column(name = "chq_transf_deposito")
    private Double chqTransfDeposito;
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
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Column(name = "cliente")
    private Integer cliente;
    @Column(name = "factura")
    private Integer factura;
    @Column(name = "nombre_tipo__de_ingreso")
    private String nombreTipoDeIngreso;
    @JoinColumn(name = "formato_607", referencedColumnName = "codigo")
    @ManyToOne
    private Formato607 formato607;

    public DetalleFormato607() {
    }

    public DetalleFormato607(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleFormato607(Integer codigo, double itbisFacturado) {
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

    public Date getFechaDeRetension() {
        return fechaDeRetension;
    }

    public void setFechaDeRetension(Date fechaDeRetension) {
        this.fechaDeRetension = fechaDeRetension;
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

    public Double getItbisRetenidoPorTercero() {
        return itbisRetenidoPorTercero;
    }

    public void setItbisRetenidoPorTercero(Double itbisRetenidoPorTercero) {
        this.itbisRetenidoPorTercero = itbisRetenidoPorTercero;
    }

    public Double getItbisPercibido() {
        return itbisPercibido;
    }

    public void setItbisPercibido(Double itbisPercibido) {
        this.itbisPercibido = itbisPercibido;
    }

    public Double getIsrRetenidoPorTercero() {
        return isrRetenidoPorTercero;
    }

    public void setIsrRetenidoPorTercero(Double isrRetenidoPorTercero) {
        this.isrRetenidoPorTercero = isrRetenidoPorTercero;
    }

    public Double getIsrPercibido() {
        return isrPercibido;
    }

    public void setIsrPercibido(Double isrPercibido) {
        this.isrPercibido = isrPercibido;
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

    public Double getChqTransfDeposito() {
        return chqTransfDeposito;
    }

    public void setChqTransfDeposito(Double chqTransfDeposito) {
        this.chqTransfDeposito = chqTransfDeposito;
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Integer getFactura() {
        return factura;
    }

    public void setFactura(Integer factura) {
        this.factura = factura;
    }

    public String getNombreTipoDeIngreso() {
        return nombreTipoDeIngreso;
    }

    public void setNombreTipoDeIngreso(String nombreTipoDeIngreso) {
        this.nombreTipoDeIngreso = nombreTipoDeIngreso;
    }

    public Formato607 getFormato607() {
        return formato607;
    }

    public void setFormato607(Formato607 formato607) {
        this.formato607 = formato607;
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
        if (!(object instanceof DetalleFormato607)) {
            return false;
        }
        DetalleFormato607 other = (DetalleFormato607) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleFormato607[ codigo=" + codigo + " ]";
    }

    public TipoDeIngreso getTipoDeIngreso() {
        return tipoDeIngreso;
    }

    public void setTipoDeIngreso(TipoDeIngreso tipoDeIngreso) {
        this.tipoDeIngreso = tipoDeIngreso;
    }
    
}
