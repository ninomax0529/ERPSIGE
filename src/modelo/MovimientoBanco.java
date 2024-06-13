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
@Table(name = "movimiento_banco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovimientoBanco.findAll", query = "SELECT m FROM MovimientoBanco m")
    , @NamedQuery(name = "MovimientoBanco.findByCodigo", query = "SELECT m FROM MovimientoBanco m WHERE m.codigo = :codigo")
    , @NamedQuery(name = "MovimientoBanco.findByFechaRegistro", query = "SELECT m FROM MovimientoBanco m WHERE m.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "MovimientoBanco.findByMovimiento", query = "SELECT m FROM MovimientoBanco m WHERE m.movimiento = :movimiento")
    , @NamedQuery(name = "MovimientoBanco.findByConcepto", query = "SELECT m FROM MovimientoBanco m WHERE m.concepto = :concepto")
    , @NamedQuery(name = "MovimientoBanco.findByNumeroOperacion", query = "SELECT m FROM MovimientoBanco m WHERE m.numeroOperacion = :numeroOperacion")
    , @NamedQuery(name = "MovimientoBanco.findByNumeroReferencia", query = "SELECT m FROM MovimientoBanco m WHERE m.numeroReferencia = :numeroReferencia")
    , @NamedQuery(name = "MovimientoBanco.findByFechaOperacion", query = "SELECT m FROM MovimientoBanco m WHERE m.fechaOperacion = :fechaOperacion")
    , @NamedQuery(name = "MovimientoBanco.findByNumeroCuenta", query = "SELECT m FROM MovimientoBanco m WHERE m.numeroCuenta = :numeroCuenta")
    , @NamedQuery(name = "MovimientoBanco.findByDebito", query = "SELECT m FROM MovimientoBanco m WHERE m.debito = :debito")
    , @NamedQuery(name = "MovimientoBanco.findByCredito", query = "SELECT m FROM MovimientoBanco m WHERE m.credito = :credito")
    , @NamedQuery(name = "MovimientoBanco.findByCodigoOperacion", query = "SELECT m FROM MovimientoBanco m WHERE m.codigoOperacion = :codigoOperacion")
    , @NamedQuery(name = "MovimientoBanco.findByBalanceAnterior", query = "SELECT m FROM MovimientoBanco m WHERE m.balanceAnterior = :balanceAnterior")
    , @NamedQuery(name = "MovimientoBanco.findByBalanceActual", query = "SELECT m FROM MovimientoBanco m WHERE m.balanceActual = :balanceActual")})
public class MovimientoBanco implements Serializable {

    @Basic(optional = false)
    @Column(name = "anulado")
    private boolean anulado;

    @Column(name = "codigo_documento")
    private Integer codigoDocumento;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "movimiento")
    private String movimiento;
    @Basic(optional = false)
    @Column(name = "concepto")
    private String concepto;
    @Basic(optional = false)
    @Column(name = "numero_operacion")
    private String numeroOperacion;
    @Basic(optional = false)
    @Column(name = "numero_referencia")
    private String numeroReferencia;
    @Basic(optional = false)
    @Column(name = "fecha_operacion")
    @Temporal(TemporalType.DATE)
    private Date fechaOperacion;
    @Basic(optional = false)
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    @Basic(optional = false)
    @Column(name = "debito")
    private double debito;
    @Basic(optional = false)
    @Column(name = "credito")
    private double credito;
    @Basic(optional = false)
    @Column(name = "codigo_operacion")
    private int codigoOperacion;
    @Basic(optional = false)
    @Column(name = "balance_anterior")
    private double balanceAnterior;
    @Basic(optional = false)
    @Column(name = "balance_actual")
    private double balanceActual;
    @JoinColumn(name = "tipo_concepto", referencedColumnName = "codigo")
    @ManyToOne
    private TipoConcepto tipoConcepto;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "banco", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Banco banco;
    @JoinColumn(name = "cuenta", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private CuentaBanco cuenta;
    @JoinColumn(name = "tipo_movimiento", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoMovimiento tipoMovimiento;
    @JoinColumn(name = "tipo_operacion", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoOperacion tipoOperacion;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public MovimientoBanco() {
    }

    public MovimientoBanco(Integer codigo) {
        this.codigo = codigo;
    }

    public MovimientoBanco(Integer codigo, Date fechaRegistro, String movimiento, String concepto, String numeroOperacion, String numeroReferencia, Date fechaOperacion, String numeroCuenta, double debito, double credito, int codigoOperacion, double balanceAnterior, double balanceActual) {
        this.codigo = codigo;
        this.fechaRegistro = fechaRegistro;
        this.movimiento = movimiento;
        this.concepto = concepto;
        this.numeroOperacion = numeroOperacion;
        this.numeroReferencia = numeroReferencia;
        this.fechaOperacion = fechaOperacion;
        this.numeroCuenta = numeroCuenta;
        this.debito = debito;
        this.credito = credito;
        this.codigoOperacion = codigoOperacion;
        this.balanceAnterior = balanceAnterior;
        this.balanceActual = balanceActual;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(String numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public int getCodigoOperacion() {
        return codigoOperacion;
    }

    public void setCodigoOperacion(int codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    public double getBalanceAnterior() {
        return balanceAnterior;
    }

    public void setBalanceAnterior(double balanceAnterior) {
        this.balanceAnterior = balanceAnterior;
    }

    public double getBalanceActual() {
        return balanceActual;
    }

    public void setBalanceActual(double balanceActual) {
        this.balanceActual = balanceActual;
    }

    public TipoConcepto getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(TipoConcepto tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public CuentaBanco getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaBanco cuenta) {
        this.cuenta = cuenta;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
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
        if (!(object instanceof MovimientoBanco)) {
            return false;
        }
        MovimientoBanco other = (MovimientoBanco) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.MovimientoBanco[ codigo=" + codigo + " ]";
    }

    public Integer getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(Integer codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }
    
}
