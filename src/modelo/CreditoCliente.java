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
@Table(name = "credito_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CreditoCliente.findAll", query = "SELECT c FROM CreditoCliente c")
    , @NamedQuery(name = "CreditoCliente.findByCodigo", query = "SELECT c FROM CreditoCliente c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "CreditoCliente.findByGarantia", query = "SELECT c FROM CreditoCliente c WHERE c.garantia = :garantia")
    , @NamedQuery(name = "CreditoCliente.findByFecha", query = "SELECT c FROM CreditoCliente c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "CreditoCliente.findByFechaRegistro", query = "SELECT c FROM CreditoCliente c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "CreditoCliente.findByMonto", query = "SELECT c FROM CreditoCliente c WHERE c.monto = :monto")
    , @NamedQuery(name = "CreditoCliente.findByFechaVencimiento", query = "SELECT c FROM CreditoCliente c WHERE c.fechaVencimiento = :fechaVencimiento")
    , @NamedQuery(name = "CreditoCliente.findByMontoDisponible", query = "SELECT c FROM CreditoCliente c WHERE c.montoDisponible = :montoDisponible")
    , @NamedQuery(name = "CreditoCliente.findByMontoPendiente", query = "SELECT c FROM CreditoCliente c WHERE c.montoPendiente = :montoPendiente")
    , @NamedQuery(name = "CreditoCliente.findByHabilitado", query = "SELECT c FROM CreditoCliente c WHERE c.habilitado = :habilitado")})
public class CreditoCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "garantia")
    private String garantia;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "monto")
    private double monto;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto_disponible")
    private Double montoDisponible;
    @Column(name = "monto_pendiente")
    private Double montoPendiente;
    @Basic(optional = false)
    @Column(name = "habilitado")
    private boolean habilitado;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "cliente", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "tipo_credito", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoCredito tipoCredito;
    @JoinColumn(name = "tipo_garantia", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoGarantia tipoGarantia;

    public CreditoCliente() {
    }

    public CreditoCliente(Integer codigo) {
        this.codigo = codigo;
    }

    public CreditoCliente(Integer codigo, String garantia, Date fecha, Date fechaRegistro, double monto, boolean habilitado) {
        this.codigo = codigo;
        this.garantia = garantia;
        this.fecha = fecha;
        this.fechaRegistro = fechaRegistro;
        this.monto = monto;
        this.habilitado = habilitado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Double getMontoDisponible() {
        return montoDisponible;
    }

    public void setMontoDisponible(Double montoDisponible) {
        this.montoDisponible = montoDisponible;
    }

    public Double getMontoPendiente() {
        return montoPendiente;
    }

    public void setMontoPendiente(Double montoPendiente) {
        this.montoPendiente = montoPendiente;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoCredito getTipoCredito() {
        return tipoCredito;
    }

    public void setTipoCredito(TipoCredito tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public TipoGarantia getTipoGarantia() {
        return tipoGarantia;
    }

    public void setTipoGarantia(TipoGarantia tipoGarantia) {
        this.tipoGarantia = tipoGarantia;
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
        if (!(object instanceof CreditoCliente)) {
            return false;
        }
        CreditoCliente other = (CreditoCliente) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CreditoCliente[ codigo=" + codigo + " ]";
    }
    
}
