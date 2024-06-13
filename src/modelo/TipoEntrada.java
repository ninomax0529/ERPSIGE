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
@Table(name = "tipo_entrada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEntrada.findAll", query = "SELECT t FROM TipoEntrada t")
    , @NamedQuery(name = "TipoEntrada.findByCodigo", query = "SELECT t FROM TipoEntrada t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoEntrada.findByDescripcion", query = "SELECT t FROM TipoEntrada t WHERE t.descripcion = :descripcion")})
public class TipoEntrada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "tipoEntrada")
    private Collection<EntradaDiario> entradaDiarioCollection;
    @OneToMany(mappedBy = "tipoEntrada")
    private Collection<EntradaInventario> entradaInventarioCollection;

    public TipoEntrada() {
    }

    public TipoEntrada(Integer codigo) {
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
    public Collection<EntradaDiario> getEntradaDiarioCollection() {
        return entradaDiarioCollection;
    }

    public void setEntradaDiarioCollection(Collection<EntradaDiario> entradaDiarioCollection) {
        this.entradaDiarioCollection = entradaDiarioCollection;
    }

    @XmlTransient
    public Collection<EntradaInventario> getEntradaInventarioCollection() {
        return entradaInventarioCollection;
    }

    public void setEntradaInventarioCollection(Collection<EntradaInventario> entradaInventarioCollection) {
        this.entradaInventarioCollection = entradaInventarioCollection;
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
        if (!(object instanceof TipoEntrada)) {
            return false;
        }
        TipoEntrada other = (TipoEntrada) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TipoEntrada[ codigo=" + codigo + " ]";
    }
    
}
