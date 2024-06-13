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
@Table(name = "detalle_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleInventario.findAll", query = "SELECT d FROM DetalleInventario d")
    , @NamedQuery(name = "DetalleInventario.findByCodigo", query = "SELECT d FROM DetalleInventario d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleInventario.findByExistencia", query = "SELECT d FROM DetalleInventario d WHERE d.existencia = :existencia")
    , @NamedQuery(name = "DetalleInventario.findByAlmacen", query = "SELECT d FROM DetalleInventario d WHERE d.almacen = :almacen")})
public class DetalleInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "existencia")
    private double existencia;
    @Basic(optional = false)
    @Column(name = "almacen")
    private int almacen;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "inventario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Inventario inventario;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidad;

    public DetalleInventario() {
    }

    public DetalleInventario(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleInventario(Integer codigo, double existencia, int almacen) {
        this.codigo = codigo;
        this.existencia = existencia;
        this.almacen = almacen;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public double getExistencia() {
        return existencia;
    }

    public void setExistencia(double existencia) {
        this.existencia = existencia;
    }

    public int getAlmacen() {
        return almacen;
    }

    public void setAlmacen(int almacen) {
        this.almacen = almacen;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
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
        if (!(object instanceof DetalleInventario)) {
            return false;
        }
        DetalleInventario other = (DetalleInventario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleInventario[ codigo=" + codigo + " ]";
    }
    
}
