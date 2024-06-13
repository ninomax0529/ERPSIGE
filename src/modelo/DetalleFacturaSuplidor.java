/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "detalle_factura_suplidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleFacturaSuplidor.findAll", query = "SELECT d FROM DetalleFacturaSuplidor d")
    , @NamedQuery(name = "DetalleFacturaSuplidor.findByCodigo", query = "SELECT d FROM DetalleFacturaSuplidor d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleFacturaSuplidor.findByDescripcionArticulo", query = "SELECT d FROM DetalleFacturaSuplidor d WHERE d.descripcionArticulo = :descripcionArticulo")
    , @NamedQuery(name = "DetalleFacturaSuplidor.findByCantidad", query = "SELECT d FROM DetalleFacturaSuplidor d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleFacturaSuplidor.findByPrecioUnitario", query = "SELECT d FROM DetalleFacturaSuplidor d WHERE d.precioUnitario = :precioUnitario")
    , @NamedQuery(name = "DetalleFacturaSuplidor.findBySubTotal", query = "SELECT d FROM DetalleFacturaSuplidor d WHERE d.subTotal = :subTotal")
    , @NamedQuery(name = "DetalleFacturaSuplidor.findByDescuento", query = "SELECT d FROM DetalleFacturaSuplidor d WHERE d.descuento = :descuento")
    , @NamedQuery(name = "DetalleFacturaSuplidor.findBySubTotalConDescuento", query = "SELECT d FROM DetalleFacturaSuplidor d WHERE d.subTotalConDescuento = :subTotalConDescuento")
    , @NamedQuery(name = "DetalleFacturaSuplidor.findByIsr", query = "SELECT d FROM DetalleFacturaSuplidor d WHERE d.isr = :isr")
    , @NamedQuery(name = "DetalleFacturaSuplidor.findByItbis", query = "SELECT d FROM DetalleFacturaSuplidor d WHERE d.itbis = :itbis")
    , @NamedQuery(name = "DetalleFacturaSuplidor.findByTotal", query = "SELECT d FROM DetalleFacturaSuplidor d WHERE d.total = :total")
    , @NamedQuery(name = "DetalleFacturaSuplidor.findByTotalAPagar", query = "SELECT d FROM DetalleFacturaSuplidor d WHERE d.totalAPagar = :totalAPagar")
    , @NamedQuery(name = "DetalleFacturaSuplidor.findByPendiente", query = "SELECT d FROM DetalleFacturaSuplidor d WHERE d.pendiente = :pendiente")
    , @NamedQuery(name = "DetalleFacturaSuplidor.findByItbisRetenido", query = "SELECT d FROM DetalleFacturaSuplidor d WHERE d.itbisRetenido = :itbisRetenido")
    , @NamedQuery(name = "DetalleFacturaSuplidor.findByItbisExcento", query = "SELECT d FROM DetalleFacturaSuplidor d WHERE d.itbisExcento = :itbisExcento")})
public class DetalleFacturaSuplidor implements Serializable {

    @Column(name = "porciento_propina")
    private Double porcientoPropina;
    @Column(name = "monto_propina")
    private Double montoPropina;

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
    @Column(name = "descripcion_articulo")
    private String descripcionArticulo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "precio_unitario")
    private Double precioUnitario;
    @Column(name = "sub_total")
    private Double subTotal;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "sub_total_con_descuento")
    private Double subTotalConDescuento;
    @Column(name = "isr")
    private Double isr;
    @Column(name = "itbis")
    private Double itbis;
    @Column(name = "total")
    private Double total;
    @Column(name = "total_a_pagar")
    private Double totalAPagar;
    @Column(name = "pendiente")
    private Double pendiente;
    @Column(name = "itbis_retenido")
    private Double itbisRetenido;
    @Column(name = "itbis_excento")
    private Double itbisExcento;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo articulo;
    @JoinColumn(name = "factura_suplidor", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private FacturaSuplidor facturaSuplidor;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne
    private Unidad unidad;

    public DetalleFacturaSuplidor() {
    }

    public DetalleFacturaSuplidor(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getSubTotalConDescuento() {
        return subTotalConDescuento;
    }

    public void setSubTotalConDescuento(Double subTotalConDescuento) {
        this.subTotalConDescuento = subTotalConDescuento;
    }

    public Double getIsr() {
        return isr;
    }

    public void setIsr(Double isr) {
        this.isr = isr;
    }

    public Double getItbis() {
        return itbis;
    }

    public void setItbis(Double itbis) {
        this.itbis = itbis;
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

    public Double getPendiente() {
        return pendiente;
    }

    public void setPendiente(Double pendiente) {
        this.pendiente = pendiente;
    }

    public Double getItbisRetenido() {
        return itbisRetenido;
    }

    public void setItbisRetenido(Double itbisRetenido) {
        this.itbisRetenido = itbisRetenido;
    }

    public Double getItbisExcento() {
        return itbisExcento;
    }

    public void setItbisExcento(Double itbisExcento) {
        this.itbisExcento = itbisExcento;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public FacturaSuplidor getFacturaSuplidor() {
        return facturaSuplidor;
    }

    public void setFacturaSuplidor(FacturaSuplidor facturaSuplidor) {
        this.facturaSuplidor = facturaSuplidor;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
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
        if (!(object instanceof DetalleFacturaSuplidor)) {
            return false;
        }
        DetalleFacturaSuplidor other = (DetalleFacturaSuplidor) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleFacturaSuplidor[ codigo=" + codigo + " ]";
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

    public Double getPorcientoPropina() {
        return porcientoPropina;
    }

    public void setPorcientoPropina(Double porcientoPropina) {
        this.porcientoPropina = porcientoPropina;
    }

    public Double getMontoPropina() {
        return montoPropina;
    }

    public void setMontoPropina(Double montoPropina) {
        this.montoPropina = montoPropina;
    }
    
}
