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
@Table(name = "detalle_conduce")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleConduce.findAll", query = "SELECT d FROM DetalleConduce d")
    , @NamedQuery(name = "DetalleConduce.findByCodigo", query = "SELECT d FROM DetalleConduce d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleConduce.findByCantidad", query = "SELECT d FROM DetalleConduce d WHERE d.cantidad = :cantidad")})
public class DetalleConduce implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private double cantidad;
    @JoinColumn(name = "conduce", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Conduce conduce;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidad;

    public DetalleConduce() {
    }

    public DetalleConduce(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleConduce(Integer codigo, double cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Conduce getConduce() {
        return conduce;
    }

    public void setConduce(Conduce conduce) {
        this.conduce = conduce;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
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
        if (!(object instanceof DetalleConduce)) {
            return false;
        }
        DetalleConduce other = (DetalleConduce) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleConduce[ codigo=" + codigo + " ]";
    }
    
}
