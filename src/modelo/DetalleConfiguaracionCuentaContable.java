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
@Table(name = "detalle_configuaracion_cuenta_contable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleConfiguaracionCuentaContable.findAll", query = "SELECT d FROM DetalleConfiguaracionCuentaContable d")
    , @NamedQuery(name = "DetalleConfiguaracionCuentaContable.findByCodigo", query = "SELECT d FROM DetalleConfiguaracionCuentaContable d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleConfiguaracionCuentaContable.findByCuenta", query = "SELECT d FROM DetalleConfiguaracionCuentaContable d WHERE d.cuenta = :cuenta")
    , @NamedQuery(name = "DetalleConfiguaracionCuentaContable.findByDebitar", query = "SELECT d FROM DetalleConfiguaracionCuentaContable d WHERE d.debitar = :debitar")
    , @NamedQuery(name = "DetalleConfiguaracionCuentaContable.findByAcreditar", query = "SELECT d FROM DetalleConfiguaracionCuentaContable d WHERE d.acreditar = :acreditar")})
public class DetalleConfiguaracionCuentaContable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "cuenta")
    private String cuenta;
    @Basic(optional = false)
    @Lob
    @Column(name = "nombre_cuenta")
    private String nombreCuenta;
    @Basic(optional = false)
    @Column(name = "debitar")
    private boolean debitar;
    @Basic(optional = false)
    @Column(name = "acreditar")
    private boolean acreditar;
    @JoinColumn(name = "configuracion_cuenta_contable", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private ConfiguracionCuentaContable configuracionCuentaContable;
    @JoinColumn(name = "catalogo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Catalogo catalogo;

    public DetalleConfiguaracionCuentaContable() {
    }

    public DetalleConfiguaracionCuentaContable(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleConfiguaracionCuentaContable(Integer codigo, String cuenta, String nombreCuenta, boolean debitar, boolean acreditar) {
        this.codigo = codigo;
        this.cuenta = cuenta;
        this.nombreCuenta = nombreCuenta;
        this.debitar = debitar;
        this.acreditar = acreditar;
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

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public boolean getDebitar() {
        return debitar;
    }

    public void setDebitar(boolean debitar) {
        this.debitar = debitar;
    }

    public boolean getAcreditar() {
        return acreditar;
    }

    public void setAcreditar(boolean acreditar) {
        this.acreditar = acreditar;
    }

    public ConfiguracionCuentaContable getConfiguracionCuentaContable() {
        return configuracionCuentaContable;
    }

    public void setConfiguracionCuentaContable(ConfiguracionCuentaContable configuracionCuentaContable) {
        this.configuracionCuentaContable = configuracionCuentaContable;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
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
        if (!(object instanceof DetalleConfiguaracionCuentaContable)) {
            return false;
        }
        DetalleConfiguaracionCuentaContable other = (DetalleConfiguaracionCuentaContable) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleConfiguaracionCuentaContable[ codigo=" + codigo + " ]";
    }
    
}
