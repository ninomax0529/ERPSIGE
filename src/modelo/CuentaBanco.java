/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "cuenta_banco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaBanco.findAll", query = "SELECT c FROM CuentaBanco c")
    , @NamedQuery(name = "CuentaBanco.findByCodigo", query = "SELECT c FROM CuentaBanco c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "CuentaBanco.findByNumeroCuenta", query = "SELECT c FROM CuentaBanco c WHERE c.numeroCuenta = :numeroCuenta")
    , @NamedQuery(name = "CuentaBanco.findByCuentaContable", query = "SELECT c FROM CuentaBanco c WHERE c.cuentaContable = :cuentaContable")})
public class CuentaBanco implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "debito")
    private Double debito;
    @Column(name = "credito")
    private Double credito;
    @Column(name = "saldo")
    private Double saldo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta")
    private Collection<MovimientoBanco> movimientoBancoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    @Column(name = "cuenta_contable")
    private String cuentaContable;
    @JoinColumn(name = "catalogo", referencedColumnName = "codigo")
    @ManyToOne
    private Catalogo catalogo;
    @JoinColumn(name = "banco", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Banco banco;
    @JoinColumn(name = "moneda", referencedColumnName = "codigo")
    @ManyToOne
    private Moneda moneda;
    @JoinColumn(name = "tipo_cuenta", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoCuentaBanco tipoCuenta;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

    public CuentaBanco() {
    }

    public CuentaBanco(Integer codigo) {
        this.codigo = codigo;
    }

    public CuentaBanco(Integer codigo, String numeroCuenta) {
        this.codigo = codigo;
        this.numeroCuenta = numeroCuenta;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public TipoCuentaBanco getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuentaBanco tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
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
        if (!(object instanceof CuentaBanco)) {
            return false;
        }
        CuentaBanco other = (CuentaBanco) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CuentaBanco[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<MovimientoBanco> getMovimientoBancoCollection() {
        return movimientoBancoCollection;
    }

    public void setMovimientoBancoCollection(Collection<MovimientoBanco> movimientoBancoCollection) {
        this.movimientoBancoCollection = movimientoBancoCollection;
    }

    public Double getDebito() {
        return debito;
    }

    public void setDebito(Double debito) {
        this.debito = debito;
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    
}
