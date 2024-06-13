/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "secuencia_cheque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SecuenciaCheque.findAll", query = "SELECT s FROM SecuenciaCheque s")
    , @NamedQuery(name = "SecuenciaCheque.findByCodigo", query = "SELECT s FROM SecuenciaCheque s WHERE s.codigo = :codigo")
    , @NamedQuery(name = "SecuenciaCheque.findByFecha", query = "SELECT s FROM SecuenciaCheque s WHERE s.fecha = :fecha")
    , @NamedQuery(name = "SecuenciaCheque.findByCantidad", query = "SELECT s FROM SecuenciaCheque s WHERE s.cantidad = :cantidad")
    , @NamedQuery(name = "SecuenciaCheque.findByNumeroInicial", query = "SELECT s FROM SecuenciaCheque s WHERE s.numeroInicial = :numeroInicial")
    , @NamedQuery(name = "SecuenciaCheque.findByNumeroFinal", query = "SELECT s FROM SecuenciaCheque s WHERE s.numeroFinal = :numeroFinal")
    , @NamedQuery(name = "SecuenciaCheque.findByUsuario", query = "SELECT s FROM SecuenciaCheque s WHERE s.usuario = :usuario")
    , @NamedQuery(name = "SecuenciaCheque.findByNumeroActual", query = "SELECT s FROM SecuenciaCheque s WHERE s.numeroActual = :numeroActual")
    , @NamedQuery(name = "SecuenciaCheque.findByFechaRegistro", query = "SELECT s FROM SecuenciaCheque s WHERE s.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "SecuenciaCheque.findByEstado", query = "SELECT s FROM SecuenciaCheque s WHERE s.estado = :estado")
    , @NamedQuery(name = "SecuenciaCheque.findByNumeroCuenta", query = "SELECT s FROM SecuenciaCheque s WHERE s.numeroCuenta = :numeroCuenta")})
public class SecuenciaCheque implements Serializable {

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
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "numero_inicial")
    private int numeroInicial;
    @Basic(optional = false)
    @Column(name = "numero_final")
    private int numeroFinal;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "numero_actual")
    private int numeroActual;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    @OneToMany(mappedBy = "secuencia")
    private Collection<Cheque> chequeCollection;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "banco", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Banco banco;
    @JoinColumn(name = "cuenta", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private CuentaBanco cuenta;

    public SecuenciaCheque() {
    }

    public SecuenciaCheque(Integer codigo) {
        this.codigo = codigo;
    }

    public SecuenciaCheque(Integer codigo, Date fecha, int cantidad, int numeroInicial, int numeroFinal, int usuario, int numeroActual, Date fechaRegistro, String numeroCuenta) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.numeroInicial = numeroInicial;
        this.numeroFinal = numeroFinal;
        this.usuario = usuario;
        this.numeroActual = numeroActual;
        this.fechaRegistro = fechaRegistro;
        this.numeroCuenta = numeroCuenta;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getNumeroInicial() {
        return numeroInicial;
    }

    public void setNumeroInicial(int numeroInicial) {
        this.numeroInicial = numeroInicial;
    }

    public int getNumeroFinal() {
        return numeroFinal;
    }

    public void setNumeroFinal(int numeroFinal) {
        this.numeroFinal = numeroFinal;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getNumeroActual() {
        return numeroActual;
    }

    public void setNumeroActual(int numeroActual) {
        this.numeroActual = numeroActual;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    @XmlTransient
    public Collection<Cheque> getChequeCollection() {
        return chequeCollection;
    }

    public void setChequeCollection(Collection<Cheque> chequeCollection) {
        this.chequeCollection = chequeCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SecuenciaCheque)) {
            return false;
        }
        SecuenciaCheque other = (SecuenciaCheque) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.SecuenciaCheque[ codigo=" + codigo + " ]";
    }
    
}
