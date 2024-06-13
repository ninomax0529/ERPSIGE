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
@Table(name = "transferencia_banco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransferenciaBanco.findAll", query = "SELECT t FROM TransferenciaBanco t")
    , @NamedQuery(name = "TransferenciaBanco.findByCodigo", query = "SELECT t FROM TransferenciaBanco t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TransferenciaBanco.findByFecha", query = "SELECT t FROM TransferenciaBanco t WHERE t.fecha = :fecha")
    , @NamedQuery(name = "TransferenciaBanco.findByFechaRegistro", query = "SELECT t FROM TransferenciaBanco t WHERE t.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "TransferenciaBanco.findByNumeroTransferencia", query = "SELECT t FROM TransferenciaBanco t WHERE t.numeroTransferencia = :numeroTransferencia")
    , @NamedQuery(name = "TransferenciaBanco.findByMonto", query = "SELECT t FROM TransferenciaBanco t WHERE t.monto = :monto")
    , @NamedQuery(name = "TransferenciaBanco.findByBalanceActualCuentaOrigen", query = "SELECT t FROM TransferenciaBanco t WHERE t.balanceActualCuentaOrigen = :balanceActualCuentaOrigen")
    , @NamedQuery(name = "TransferenciaBanco.findByBalanceActualCuentaDestino", query = "SELECT t FROM TransferenciaBanco t WHERE t.balanceActualCuentaDestino = :balanceActualCuentaDestino")
    , @NamedQuery(name = "TransferenciaBanco.findByBalanceAnteriorCuentaOrigen", query = "SELECT t FROM TransferenciaBanco t WHERE t.balanceAnteriorCuentaOrigen = :balanceAnteriorCuentaOrigen")
    , @NamedQuery(name = "TransferenciaBanco.findByBalanceAnteriorCuentaDestino", query = "SELECT t FROM TransferenciaBanco t WHERE t.balanceAnteriorCuentaDestino = :balanceAnteriorCuentaDestino")
    , @NamedQuery(name = "TransferenciaBanco.findByNumero", query = "SELECT t FROM TransferenciaBanco t WHERE t.numero = :numero")})
public class TransferenciaBanco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "numero_transferencia")
    private String numeroTransferencia;
    @Basic(optional = false)
    @Column(name = "monto")
    private double monto;
    @Basic(optional = false)
    @Column(name = "balance_actual_cuenta_origen")
    private double balanceActualCuentaOrigen;
    @Basic(optional = false)
    @Column(name = "balance_actual_cuenta_destino")
    private double balanceActualCuentaDestino;
    @Basic(optional = false)
    @Column(name = "balance_anterior_cuenta_origen")
    private double balanceAnteriorCuentaOrigen;
    @Basic(optional = false)
    @Column(name = "balance_anterior_cuenta_destino")
    private double balanceAnteriorCuentaDestino;
    @Column(name = "numero")
    private Integer numero;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "banco_destino", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Banco bancoDestino;
    @JoinColumn(name = "banco_origen", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Banco bancoOrigen;
    @JoinColumn(name = "cuenta_destino", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private CuentaBanco cuentaDestino;
    @JoinColumn(name = "cuenta_origen", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private CuentaBanco cuentaOrigen;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public TransferenciaBanco() {
    }

    public TransferenciaBanco(Integer codigo) {
        this.codigo = codigo;
    }

    public TransferenciaBanco(Integer codigo, Date fecha, Date fechaRegistro, String numeroTransferencia, double monto, double balanceActualCuentaOrigen, double balanceActualCuentaDestino, double balanceAnteriorCuentaOrigen, double balanceAnteriorCuentaDestino) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.fechaRegistro = fechaRegistro;
        this.numeroTransferencia = numeroTransferencia;
        this.monto = monto;
        this.balanceActualCuentaOrigen = balanceActualCuentaOrigen;
        this.balanceActualCuentaDestino = balanceActualCuentaDestino;
        this.balanceAnteriorCuentaOrigen = balanceAnteriorCuentaOrigen;
        this.balanceAnteriorCuentaDestino = balanceAnteriorCuentaDestino;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNumeroTransferencia() {
        return numeroTransferencia;
    }

    public void setNumeroTransferencia(String numeroTransferencia) {
        this.numeroTransferencia = numeroTransferencia;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getBalanceActualCuentaOrigen() {
        return balanceActualCuentaOrigen;
    }

    public void setBalanceActualCuentaOrigen(double balanceActualCuentaOrigen) {
        this.balanceActualCuentaOrigen = balanceActualCuentaOrigen;
    }

    public double getBalanceActualCuentaDestino() {
        return balanceActualCuentaDestino;
    }

    public void setBalanceActualCuentaDestino(double balanceActualCuentaDestino) {
        this.balanceActualCuentaDestino = balanceActualCuentaDestino;
    }

    public double getBalanceAnteriorCuentaOrigen() {
        return balanceAnteriorCuentaOrigen;
    }

    public void setBalanceAnteriorCuentaOrigen(double balanceAnteriorCuentaOrigen) {
        this.balanceAnteriorCuentaOrigen = balanceAnteriorCuentaOrigen;
    }

    public double getBalanceAnteriorCuentaDestino() {
        return balanceAnteriorCuentaDestino;
    }

    public void setBalanceAnteriorCuentaDestino(double balanceAnteriorCuentaDestino) {
        this.balanceAnteriorCuentaDestino = balanceAnteriorCuentaDestino;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
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

    public Banco getBancoDestino() {
        return bancoDestino;
    }

    public void setBancoDestino(Banco bancoDestino) {
        this.bancoDestino = bancoDestino;
    }

    public Banco getBancoOrigen() {
        return bancoOrigen;
    }

    public void setBancoOrigen(Banco bancoOrigen) {
        this.bancoOrigen = bancoOrigen;
    }

    public CuentaBanco getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(CuentaBanco cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public CuentaBanco getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(CuentaBanco cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof TransferenciaBanco)) {
            return false;
        }
        TransferenciaBanco other = (TransferenciaBanco) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TransferenciaBanco[ codigo=" + codigo + " ]";
    }
    
}
