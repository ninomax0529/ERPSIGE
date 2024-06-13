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
@Table(name = "detalle_corte_de_facturacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCorteDeFacturacion.findAll", query = "SELECT d FROM DetalleCorteDeFacturacion d")
    , @NamedQuery(name = "DetalleCorteDeFacturacion.findByCodigo", query = "SELECT d FROM DetalleCorteDeFacturacion d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleCorteDeFacturacion.findByNumeroContrato", query = "SELECT d FROM DetalleCorteDeFacturacion d WHERE d.numeroContrato = :numeroContrato")
    , @NamedQuery(name = "DetalleCorteDeFacturacion.findByNumeroFactura", query = "SELECT d FROM DetalleCorteDeFacturacion d WHERE d.numeroFactura = :numeroFactura")
    , @NamedQuery(name = "DetalleCorteDeFacturacion.findByTotalFactura", query = "SELECT d FROM DetalleCorteDeFacturacion d WHERE d.totalFactura = :totalFactura")})
public class DetalleCorteDeFacturacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "numero_contrato")
    private String numeroContrato;
    @Basic(optional = false)
    @Column(name = "numero_factura")
    private int numeroFactura;
    @Basic(optional = false)
    @Column(name = "total_factura")
    private double totalFactura;
    @JoinColumn(name = "contrato", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private ContratoDeServicio contrato;
    @JoinColumn(name = "corte_de_facturacion", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private CorteDeFacturacion corteDeFacturacion;
    @JoinColumn(name = "factura", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Factura factura;

    public DetalleCorteDeFacturacion() {
    }

    public DetalleCorteDeFacturacion(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleCorteDeFacturacion(Integer codigo, String numeroContrato, int numeroFactura, double totalFactura) {
        this.codigo = codigo;
        this.numeroContrato = numeroContrato;
        this.numeroFactura = numeroFactura;
        this.totalFactura = totalFactura;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public ContratoDeServicio getContrato() {
        return contrato;
    }

    public void setContrato(ContratoDeServicio contrato) {
        this.contrato = contrato;
    }

    public CorteDeFacturacion getCorteDeFacturacion() {
        return corteDeFacturacion;
    }

    public void setCorteDeFacturacion(CorteDeFacturacion corteDeFacturacion) {
        this.corteDeFacturacion = corteDeFacturacion;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
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
        if (!(object instanceof DetalleCorteDeFacturacion)) {
            return false;
        }
        DetalleCorteDeFacturacion other = (DetalleCorteDeFacturacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleCorteDeFacturacion[ codigo=" + codigo + " ]";
    }
    
}
