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
@Table(name = "tipo_articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoArticulo.findAll", query = "SELECT t FROM TipoArticulo t")
    , @NamedQuery(name = "TipoArticulo.findByCodigo", query = "SELECT t FROM TipoArticulo t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoArticulo.findByDescripcion", query = "SELECT t FROM TipoArticulo t WHERE t.descripcion = :descripcion")})
public class TipoArticulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "tipoArticulo")
    private Collection<Articulo> articuloCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoArticulo")
    private Collection<SubTipoArticulo> subTipoArticuloCollection;

    public TipoArticulo() {
    }

    public TipoArticulo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Articulo> getArticuloCollection() {
        return articuloCollection;
    }

    public void setArticuloCollection(Collection<Articulo> articuloCollection) {
        this.articuloCollection = articuloCollection;
    }

    @XmlTransient
    public Collection<SubTipoArticulo> getSubTipoArticuloCollection() {
        return subTipoArticuloCollection;
    }

    public void setSubTipoArticuloCollection(Collection<SubTipoArticulo> subTipoArticuloCollection) {
        this.subTipoArticuloCollection = subTipoArticuloCollection;
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
        if (!(object instanceof TipoArticulo)) {
            return false;
        }
        TipoArticulo other = (TipoArticulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TipoArticulo[ codigo=" + codigo + " ]";
    }
    
}
