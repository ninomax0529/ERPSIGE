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
@Table(name = "historico_balance_pendiente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoricoBalancePendiente.findAll", query = "SELECT h FROM HistoricoBalancePendiente h")
    , @NamedQuery(name = "HistoricoBalancePendiente.findByCodigo", query = "SELECT h FROM HistoricoBalancePendiente h WHERE h.codigo = :codigo")
    , @NamedQuery(name = "HistoricoBalancePendiente.findByCliente", query = "SELECT h FROM HistoricoBalancePendiente h WHERE h.cliente = :cliente")
    , @NamedQuery(name = "HistoricoBalancePendiente.findByFactura", query = "SELECT h FROM HistoricoBalancePendiente h WHERE h.factura = :factura")
    , @NamedQuery(name = "HistoricoBalancePendiente.findByTotal", query = "SELECT h FROM HistoricoBalancePendiente h WHERE h.total = :total")
    , @NamedQuery(name = "HistoricoBalancePendiente.findByFechaVencimiento", query = "SELECT h FROM HistoricoBalancePendiente h WHERE h.fechaVencimiento = :fechaVencimiento")})
public class HistoricoBalancePendiente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "cliente")
    private int cliente;
    @Basic(optional = false)
    @Column(name = "factura")
    private int factura;
    @Basic(optional = false)
    @Column(name = "total")
    private double total;
    @Basic(optional = false)
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;

    public HistoricoBalancePendiente() {
    }

    public HistoricoBalancePendiente(Integer codigo) {
        this.codigo = codigo;
    }

    public HistoricoBalancePendiente(Integer codigo, int cliente, int factura, double total, Date fechaVencimiento) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.factura = factura;
        this.total = total;
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getFactura() {
        return factura;
    }

    public void setFactura(int factura) {
        this.factura = factura;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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
        if (!(object instanceof HistoricoBalancePendiente)) {
            return false;
        }
        HistoricoBalancePendiente other = (HistoricoBalancePendiente) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.HistoricoBalancePendiente[ codigo=" + codigo + " ]";
    }
    
}
