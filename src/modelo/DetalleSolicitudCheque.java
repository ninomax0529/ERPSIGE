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
@Table(name = "detalle_solicitud_cheque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleSolicitudCheque.findAll", query = "SELECT d FROM DetalleSolicitudCheque d")
    , @NamedQuery(name = "DetalleSolicitudCheque.findByCodigo", query = "SELECT d FROM DetalleSolicitudCheque d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleSolicitudCheque.findByFactura", query = "SELECT d FROM DetalleSolicitudCheque d WHERE d.factura = :factura")
    , @NamedQuery(name = "DetalleSolicitudCheque.findByFecha", query = "SELECT d FROM DetalleSolicitudCheque d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "DetalleSolicitudCheque.findByMonto", query = "SELECT d FROM DetalleSolicitudCheque d WHERE d.monto = :monto")
    , @NamedQuery(name = "DetalleSolicitudCheque.findByFechaVencimiento", query = "SELECT d FROM DetalleSolicitudCheque d WHERE d.fechaVencimiento = :fechaVencimiento")
    , @NamedQuery(name = "DetalleSolicitudCheque.findByAbono", query = "SELECT d FROM DetalleSolicitudCheque d WHERE d.abono = :abono")
    , @NamedQuery(name = "DetalleSolicitudCheque.findByPendiente", query = "SELECT d FROM DetalleSolicitudCheque d WHERE d.pendiente = :pendiente")
    , @NamedQuery(name = "DetalleSolicitudCheque.findByPagado", query = "SELECT d FROM DetalleSolicitudCheque d WHERE d.pagado = :pagado")
    , @NamedQuery(name = "DetalleSolicitudCheque.findByMontoNeto", query = "SELECT d FROM DetalleSolicitudCheque d WHERE d.montoNeto = :montoNeto")})
public class DetalleSolicitudCheque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "factura")
    private String factura;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Column(name = "abono")
    private Double abono;
    @Column(name = "pendiente")
    private Double pendiente;
    @Column(name = "pagado")
    private Double pagado;
    @Column(name = "monto_neto")
    private Double montoNeto;
    @JoinColumn(name = "solicitud_cheque", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private SolicitudCheque solicitudCheque;
    @JoinColumn(name = "factura_suplidor", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private FacturaSuplidor facturaSuplidor;

    public DetalleSolicitudCheque() {
    }

    public DetalleSolicitudCheque(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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

    public Double getPagado() {
        return pagado;
    }

    public void setPagado(Double pagado) {
        this.pagado = pagado;
    }

    public Double getMontoNeto() {
        return montoNeto;
    }

    public void setMontoNeto(Double montoNeto) {
        this.montoNeto = montoNeto;
    }

    public SolicitudCheque getSolicitudCheque() {
        return solicitudCheque;
    }

    public void setSolicitudCheque(SolicitudCheque solicitudCheque) {
        this.solicitudCheque = solicitudCheque;
    }

    public FacturaSuplidor getFacturaSuplidor() {
        return facturaSuplidor;
    }

    public void setFacturaSuplidor(FacturaSuplidor facturaSuplidor) {
        this.facturaSuplidor = facturaSuplidor;
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
        if (!(object instanceof DetalleSolicitudCheque)) {
            return false;
        }
        DetalleSolicitudCheque other = (DetalleSolicitudCheque) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleSolicitudCheque[ codigo=" + codigo + " ]";
    }
    
}
