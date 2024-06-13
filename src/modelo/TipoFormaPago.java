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
@Table(name = "tipo_forma_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoFormaPago.findAll", query = "SELECT t FROM TipoFormaPago t")
    , @NamedQuery(name = "TipoFormaPago.findByCodigo", query = "SELECT t FROM TipoFormaPago t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoFormaPago.findByDescripcion", query = "SELECT t FROM TipoFormaPago t WHERE t.descripcion = :descripcion")})
public class TipoFormaPago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "tipoFormaPago")
    private Collection<FormaPago> formaPagoCollection;

    public TipoFormaPago() {
    }

    public TipoFormaPago(Integer codigo) {
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
    public Collection<FormaPago> getFormaPagoCollection() {
        return formaPagoCollection;
    }

    public void setFormaPagoCollection(Collection<FormaPago> formaPagoCollection) {
        this.formaPagoCollection = formaPagoCollection;
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
        if (!(object instanceof TipoFormaPago)) {
            return false;
        }
        TipoFormaPago other = (TipoFormaPago) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TipoFormaPago[ codigo=" + codigo + " ]";
    }
    
}
