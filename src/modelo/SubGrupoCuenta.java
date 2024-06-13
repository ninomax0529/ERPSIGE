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
@Table(name = "sub_grupo_cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubGrupoCuenta.findAll", query = "SELECT s FROM SubGrupoCuenta s")
    , @NamedQuery(name = "SubGrupoCuenta.findByCodigo", query = "SELECT s FROM SubGrupoCuenta s WHERE s.codigo = :codigo")
    , @NamedQuery(name = "SubGrupoCuenta.findByNombre", query = "SELECT s FROM SubGrupoCuenta s WHERE s.nombre = :nombre")})
public class SubGrupoCuenta implements Serializable {

    @Column(name = "grupo")
    private Integer grupo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "subGrupo")
    private Collection<Catalogo> catalogoCollection;
    @OneToMany(mappedBy = "subGrupo")
    private Collection<CatalogoCopy> catalogoCopyCollection;

    public SubGrupoCuenta() {
    }

    public SubGrupoCuenta(Integer codigo) {
        this.codigo = codigo;
    }

    public SubGrupoCuenta(Integer codigo, String nombre) {
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
        if (!(object instanceof SubGrupoCuenta)) {
            return false;
        }
        SubGrupoCuenta other = (SubGrupoCuenta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.SubGrupoCuenta[ codigo=" + codigo + " ]";
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }
    
}
