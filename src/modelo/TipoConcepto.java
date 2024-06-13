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
@Table(name = "tipo_concepto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoConcepto.findAll", query = "SELECT t FROM TipoConcepto t")
    , @NamedQuery(name = "TipoConcepto.findByCodigo", query = "SELECT t FROM TipoConcepto t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoConcepto.findByNombre", query = "SELECT t FROM TipoConcepto t WHERE t.nombre = :nombre")})
public class TipoConcepto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "modulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Modulo modulo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoConcepto")
    private Collection<MovimientoBanco> movimientoBancoCollection;
    @OneToMany(mappedBy = "tipoConcepto")
    private Collection<ConfiguracionCuentaContable> configuracionCuentaContableCollection;

    public TipoConcepto() {
    }

    public TipoConcepto(Integer codigo) {
        this.codigo = codigo;
    }

    public TipoConcepto(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    @XmlTransient
    public Collection<MovimientoBanco> getMovimientoBancoCollection() {
        return movimientoBancoCollection;
    }

    public void setMovimientoBancoCollection(Collection<MovimientoBanco> movimientoBancoCollection) {
        this.movimientoBancoCollection = movimientoBancoCollection;
    }

    @XmlTransient
    public Collection<ConfiguracionCuentaContable> getConfiguracionCuentaContableCollection() {
        return configuracionCuentaContableCollection;
    }

    public void setConfiguracionCuentaContableCollection(Collection<ConfiguracionCuentaContable> configuracionCuentaContableCollection) {
        this.configuracionCuentaContableCollection = configuracionCuentaContableCollection;
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
        if (!(object instanceof TipoConcepto)) {
            return false;
        }
        TipoConcepto other = (TipoConcepto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TipoConcepto[ codigo=" + codigo + " ]";
    }
    
}