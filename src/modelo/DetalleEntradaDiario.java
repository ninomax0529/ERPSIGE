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
import javax.persistence.Lob;
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
@Table(name = "detalle_entrada_diario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleEntradaDiario.findAll", query = "SELECT d FROM DetalleEntradaDiario d")
    , @NamedQuery(name = "DetalleEntradaDiario.findByCodigo", query = "SELECT d FROM DetalleEntradaDiario d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleEntradaDiario.findByCuenta", query = "SELECT d FROM DetalleEntradaDiario d WHERE d.cuenta = :cuenta")
    , @NamedQuery(name = "DetalleEntradaDiario.findByDebito", query = "SELECT d FROM DetalleEntradaDiario d WHERE d.debito = :debito")
    , @NamedQuery(name = "DetalleEntradaDiario.findByCredito", query = "SELECT d FROM DetalleEntradaDiario d WHERE d.credito = :credito")
    , @NamedQuery(name = "DetalleEntradaDiario.findByCuentaControl", query = "SELECT d FROM DetalleEntradaDiario d WHERE d.cuentaControl = :cuentaControl")})
public class DetalleEntradaDiario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "cuenta")
    private String cuenta;
    @Lob
    @Column(name = "descripcion_cuenta")
    private String descripcionCuenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "debito")
    private Double debito;
    @Column(name = "credito")
    private Double credito;
    @Column(name = "cuenta_control")
    private Integer cuentaControl;
    @JoinColumn(name = "catalogo", referencedColumnName = "codigo")
    @ManyToOne
    private Catalogo catalogo;
    @JoinColumn(name = "entrada_diario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private EntradaDiario entradaDiario;

    public DetalleEntradaDiario() {
    }

    public DetalleEntradaDiario(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getDescripcionCuenta() {
        return descripcionCuenta;
    }

    public void setDescripcionCuenta(String descripcionCuenta) {
        this.descripcionCuenta = descripcionCuenta;
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

    public Integer getCuentaControl() {
        return cuentaControl;
    }

    public void setCuentaControl(Integer cuentaControl) {
        this.cuentaControl = cuentaControl;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public EntradaDiario getEntradaDiario() {
        return entradaDiario;
    }

    public void setEntradaDiario(EntradaDiario entradaDiario) {
        this.entradaDiario = entradaDiario;
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
        if (!(object instanceof DetalleEntradaDiario)) {
            return false;
        }
        DetalleEntradaDiario other = (DetalleEntradaDiario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleEntradaDiario[ codigo=" + codigo + " ]";
    }
    
}
