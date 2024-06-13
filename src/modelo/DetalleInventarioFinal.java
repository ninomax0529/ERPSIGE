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
@Table(name = "detalle_inventario_final")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleInventarioFinal.findAll", query = "SELECT d FROM DetalleInventarioFinal d")
    , @NamedQuery(name = "DetalleInventarioFinal.findByCodigo", query = "SELECT d FROM DetalleInventarioFinal d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleInventarioFinal.findByExistencia", query = "SELECT d FROM DetalleInventarioFinal d WHERE d.existencia = :existencia")})
public class DetalleInventarioFinal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "existencia")
    private double existencia;
    @JoinColumn(name = "almacen", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Almacen almacen;
    @JoinColumn(name = "inventario_final", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private InventarioFinal inventarioFinal;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidad;

    public DetalleInventarioFinal() {
    }

    public DetalleInventarioFinal(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleInventarioFinal(Integer codigo, double existencia) {
        this.codigo = codigo;
        this.existencia = existencia;
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

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public InventarioFinal getInventarioFinal() {
        return inventarioFinal;
    }

    public void setInventarioFinal(InventarioFinal inventarioFinal) {
        this.inventarioFinal = inventarioFinal;
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
        if (!(object instanceof DetalleInventarioFinal)) {
            return false;
        }
        DetalleInventarioFinal other = (DetalleInventarioFinal) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleInventarioFinal[ codigo=" + codigo + " ]";
    }
    
}
