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
@Table(name = "cheque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cheque.findAll", query = "SELECT c FROM Cheque c")
    , @NamedQuery(name = "Cheque.findByCodigo", query = "SELECT c FROM Cheque c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Cheque.findByNumero", query = "SELECT c FROM Cheque c WHERE c.numero = :numero")
    , @NamedQuery(name = "Cheque.findByMonto", query = "SELECT c FROM Cheque c WHERE c.monto = :monto")
    , @NamedQuery(name = "Cheque.findByFecha", query = "SELECT c FROM Cheque c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "Cheque.findByFechaRegistro", query = "SELECT c FROM Cheque c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Cheque.findByBeneficiario", query = "SELECT c FROM Cheque c WHERE c.beneficiario = :beneficiario")
    , @NamedQuery(name = "Cheque.findByAnulado", query = "SELECT c FROM Cheque c WHERE c.anulado = :anulado")
    , @NamedQuery(name = "Cheque.findByNombreUsuario", query = "SELECT c FROM Cheque c WHERE c.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "Cheque.findByConfirmado", query = "SELECT c FROM Cheque c WHERE c.confirmado = :confirmado")
    , @NamedQuery(name = "Cheque.findByFechaAnulado", query = "SELECT c FROM Cheque c WHERE c.fechaAnulado = :fechaAnulado")
    , @NamedQuery(name = "Cheque.findByMoneda", query = "SELECT c FROM Cheque c WHERE c.moneda = :moneda")
    , @NamedQuery(name = "Cheque.findByNumeroCuenta", query = "SELECT c FROM Cheque c WHERE c.numeroCuenta = :numeroCuenta")
    , @NamedQuery(name = "Cheque.findByFechaCorfirmado", query = "SELECT c FROM Cheque c WHERE c.fechaCorfirmado = :fechaCorfirmado")
    , @NamedQuery(name = "Cheque.findByTipoConcepto", query = "SELECT c FROM Cheque c WHERE c.tipoConcepto = :tipoConcepto")})
public class Cheque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @Column(name = "monto")
    private double monto;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "beneficiario")
    private String beneficiario;
    @Basic(optional = false)
    @Column(name = "anulado")
    private boolean anulado;
    @Basic(optional = false)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "confirmado")
    private boolean confirmado;
    @Column(name = "fecha_anulado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulado;
    @Basic(optional = false)
    @Column(name = "moneda")
    private String moneda;
    @Basic(optional = false)
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    @Column(name = "fecha_corfirmado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCorfirmado;
    @Column(name = "tipo_concepto")
    private Integer tipoConcepto;
    @JoinColumn(name = "secuencia", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaCheque secuencia;
    @JoinColumn(name = "banco", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Banco banco;
    @JoinColumn(name = "cuenta", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private CuentaBanco cuenta;
    @JoinColumn(name = "solicitud", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private SolicitudCheque solicitud;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

    public Cheque() {
    }

    public Cheque(Integer codigo) {
        this.codigo = codigo;
    }

    public Cheque(Integer codigo, String numero, double monto, Date fecha, Date fechaRegistro, String beneficiario, boolean anulado, String nombreUsuario, boolean confirmado, String moneda, String numeroCuenta) {
        this.codigo = codigo;
        this.numero = numero;
        this.monto = monto;
        this.fecha = fecha;
        this.fechaRegistro = fechaRegistro;
        this.beneficiario = beneficiario;
        this.anulado = anulado;
        this.nombreUsuario = nombreUsuario;
        this.confirmado = confirmado;
        this.moneda = moneda;
        this.numeroCuenta = numeroCuenta;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
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

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public Date getFechaAnulado() {
        return fechaAnulado;
    }

    public void setFechaAnulado(Date fechaAnulado) {
        this.fechaAnulado = fechaAnulado;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Date getFechaCorfirmado() {
        return fechaCorfirmado;
    }

    public void setFechaCorfirmado(Date fechaCorfirmado) {
        this.fechaCorfirmado = fechaCorfirmado;
    }

    public Integer getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(Integer tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }

    public SecuenciaCheque getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(SecuenciaCheque secuencia) {
        this.secuencia = secuencia;
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

    public SolicitudCheque getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudCheque solicitud) {
        this.solicitud = solicitud;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
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
        if (!(object instanceof Cheque)) {
            return false;
        }
        Cheque other = (Cheque) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cheque[ codigo=" + codigo + " ]";
    }
    
}
