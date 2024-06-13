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
@Table(name = "descuento_articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescuentoArticulo.findAll", query = "SELECT d FROM DescuentoArticulo d")
    , @NamedQuery(name = "DescuentoArticulo.findByCodigo", query = "SELECT d FROM DescuentoArticulo d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DescuentoArticulo.findByArticulo", query = "SELECT d FROM DescuentoArticulo d WHERE d.articulo = :articulo")
    , @NamedQuery(name = "DescuentoArticulo.findByPorciento", query = "SELECT d FROM DescuentoArticulo d WHERE d.porciento = :porciento")
    , @NamedQuery(name = "DescuentoArticulo.findByFecha", query = "SELECT d FROM DescuentoArticulo d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "DescuentoArticulo.findByUsuario", query = "SELECT d FROM DescuentoArticulo d WHERE d.usuario = :usuario")})
public class DescuentoArticulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "articulo")
    private int articulo;
    @Basic(optional = false)
    @Column(name = "porciento")
    private double porciento;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;

    public DescuentoArticulo() {
    }

    public DescuentoArticulo(Integer codigo) {
        this.codigo = codigo;
    }

    public DescuentoArticulo(Integer codigo, int articulo, double porciento, Date fecha, int usuario) {
        this.codigo = codigo;
        this.articulo = articulo;
        this.porciento = porciento;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getArticulo() {
        return articulo;
    }

    public void setArticulo(int articulo) {
        this.articulo = articulo;
    }

    public double getPorciento() {
        return porciento;
    }

    public void setPorciento(double porciento) {
        this.porciento = porciento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof DescuentoArticulo)) {
            return false;
        }
        DescuentoArticulo other = (DescuentoArticulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DescuentoArticulo[ codigo=" + codigo + " ]";
    }
    
}
