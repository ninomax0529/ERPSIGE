/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "sub_tipo_articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubTipoArticulo.findAll", query = "SELECT s FROM SubTipoArticulo s")
    , @NamedQuery(name = "SubTipoArticulo.findByCodigo", query = "SELECT s FROM SubTipoArticulo s WHERE s.codigo = :codigo")
    , @NamedQuery(name = "SubTipoArticulo.findByNombre", query = "SELECT s FROM SubTipoArticulo s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SubTipoArticulo.findByFecha", query = "SELECT s FROM SubTipoArticulo s WHERE s.fecha = :fecha")})
public class SubTipoArticulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "tipo_articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoArticulo tipoArticulo;

    public SubTipoArticulo() {
    }

    public SubTipoArticulo(Integer codigo) {
        this.codigo = codigo;
    }

    public SubTipoArticulo(Integer codigo, String nombre) {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoArticulo getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(TipoArticulo tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
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
        if (!(object instanceof SubTipoArticulo)) {
            return false;
        }
        SubTipoArticulo other = (SubTipoArticulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.SubTipoArticulo[ codigo=" + codigo + " ]";
    }
    
}