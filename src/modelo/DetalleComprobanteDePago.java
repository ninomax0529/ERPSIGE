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
@Table(name = "detalle_comprobante_de_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleComprobanteDePago.findAll", query = "SELECT d FROM DetalleComprobanteDePago d")
    , @NamedQuery(name = "DetalleComprobanteDePago.findByCodigo", query = "SELECT d FROM DetalleComprobanteDePago d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleComprobanteDePago.findByTotal", query = "SELECT d FROM DetalleComprobanteDePago d WHERE d.total = :total")
    , @NamedQuery(name = "DetalleComprobanteDePago.findByPendiente", query = "SELECT d FROM DetalleComprobanteDePago d WHERE d.pendiente = :pendiente")})
public class DetalleComprobanteDePago implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto_factura")
    private Double montoFactura;

    @Column(name = "pago_con_avance")
    private Double pagoConAvance;
    @Column(name = "monto_avance")
    private Double montoAvance;
    @Column(name = "monto_pagado")
    private Double montoPagado;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @Column(name = "pendiente")
    private Double pendiente;
    @JoinColumn(name = "factura", referencedColumnName = "codigo")
    @ManyToOne
    private FacturaSuplidor factura;
    @JoinColumn(name = "comprobante_de_pago", referencedColumnName = "codigo")
    @ManyToOne
    private ComprobanteDePago comprobanteDePago;

    public DetalleComprobanteDePago() {
    }

    public DetalleComprobanteDePago(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPendiente() {
        return pendiente;
    }

    public void setPendiente(Double pendiente) {
        this.pendiente = pendiente;
    }

    public FacturaSuplidor getFactura() {
        return factura;
    }

    public void setFactura(FacturaSuplidor factura) {
        this.factura = factura;
    }

    public ComprobanteDePago getComprobanteDePago() {
        return comprobanteDePago;
    }

    public void setComprobanteDePago(ComprobanteDePago comprobanteDePago) {
        this.comprobanteDePago = comprobanteDePago;
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
        if (!(object instanceof DetalleComprobanteDePago)) {
            return false;
        }
        DetalleComprobanteDePago other = (DetalleComprobanteDePago) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleComprobanteDePago[ codigo=" + codigo + " ]";
    }

    public Double getPagoConAvance() {
        return pagoConAvance;
    }

    public void setPagoConAvance(Double pagoConAvance) {
        this.pagoConAvance = pagoConAvance;
    }

    public Double getMontoAvance() {
        return montoAvance;
    }

    public void setMontoAvance(Double montoAvance) {
        this.montoAvance = montoAvance;
    }

    public Double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(Double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public Double getMontoFactura() {
        return montoFactura;
    }

    public void setMontoFactura(Double montoFactura) {
        this.montoFactura = montoFactura;
    }
    
}
