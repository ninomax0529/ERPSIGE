/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "retencion_de_impuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RetencionDeImpuesto.findAll", query = "SELECT r FROM RetencionDeImpuesto r")
    , @NamedQuery(name = "RetencionDeImpuesto.findByCodigo", query = "SELECT r FROM RetencionDeImpuesto r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "RetencionDeImpuesto.findByValor", query = "SELECT r FROM RetencionDeImpuesto r WHERE r.valor = :valor")
    , @NamedQuery(name = "RetencionDeImpuesto.findByDescripcion", query = "SELECT r FROM RetencionDeImpuesto r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "RetencionDeImpuesto.findByNombreImpuesto", query = "SELECT r FROM RetencionDeImpuesto r WHERE r.nombreImpuesto = :nombreImpuesto")})
public class RetencionDeImpuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "nombre_impuesto")
    private String nombreImpuesto;
    @JoinColumn(name = "tipo_impuesto", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoImpuesto tipoImpuesto;

    public RetencionDeImpuesto() {
    }

    public RetencionDeImpuesto(Integer codigo) {
        this.codigo = codigo;
    }

    public RetencionDeImpuesto(Integer codigo, BigDecimal valor) {
        this.codigo = codigo;
        this.valor = valor;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreImpuesto() {
        return nombreImpuesto;
    }

    public void setNombreImpuesto(String nombreImpuesto) {
        this.nombreImpuesto = nombreImpuesto;
    }

    public TipoImpuesto getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
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
        if (!(object instanceof RetencionDeImpuesto)) {
            return false;
        }
        RetencionDeImpuesto other = (RetencionDeImpuesto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RetencionDeImpuesto[ codigo=" + codigo + " ]";
    }
    
}
