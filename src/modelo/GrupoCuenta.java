/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "grupo_cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoCuenta.findAll", query = "SELECT g FROM GrupoCuenta g")
    , @NamedQuery(name = "GrupoCuenta.findByCodigo", query = "SELECT g FROM GrupoCuenta g WHERE g.codigo = :codigo")
    , @NamedQuery(name = "GrupoCuenta.findByNombre", query = "SELECT g FROM GrupoCuenta g WHERE g.nombre = :nombre")})
public class GrupoCuenta implements Serializable {

    /**
     * @return the tipoCuenta
     */
    public TipoCuentaBanco getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * @param tipoCuenta the tipoCuenta to set
     */
    public void setTipoCuenta(TipoCuentaBanco tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    @JoinColumn(name = "tipo_cuenta", referencedColumnName = "codigo")
    @ManyToOne
    private TipoCuentaBanco tipoCuenta;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "grupo")
    private Collection<Catalogo> catalogoCollection;
    @OneToMany(mappedBy = "grupo")
    private Collection<CatalogoCopy> catalogoCopyCollection;

    public GrupoCuenta() {
    }

    public GrupoCuenta(Integer codigo) {
        this.codigo = codigo;
    }

    public GrupoCuenta(Integer codigo, String nombre) {
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

    @XmlTransient
    public Collection<Catalogo> getCatalogoCollection() {
        return catalogoCollection;
    }

    public void setCatalogoCollection(Collection<Catalogo> catalogoCollection) {
        this.catalogoCollection = catalogoCollection;
    }

    @XmlTransient
    public Collection<CatalogoCopy> getCatalogoCopyCollection() {
        return catalogoCopyCollection;
    }

    public void setCatalogoCopyCollection(Collection<CatalogoCopy> catalogoCopyCollection) {
        this.catalogoCopyCollection = catalogoCopyCollection;
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
        if (!(object instanceof GrupoCuenta)) {
            return false;
        }
        GrupoCuenta other = (GrupoCuenta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.GrupoCuenta[ codigo=" + codigo + " ]";
    }



    
}
