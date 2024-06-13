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
@Table(name = "detalle_receta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleReceta.findAll", query = "SELECT d FROM DetalleReceta d")
    , @NamedQuery(name = "DetalleReceta.findByCodigo", query = "SELECT d FROM DetalleReceta d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleReceta.findByCantidad", query = "SELECT d FROM DetalleReceta d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleReceta.findByCostoUnitario", query = "SELECT d FROM DetalleReceta d WHERE d.costoUnitario = :costoUnitario")
    , @NamedQuery(name = "DetalleReceta.findByCosto", query = "SELECT d FROM DetalleReceta d WHERE d.costo = :costo")
    , @NamedQuery(name = "DetalleReceta.findByNombreUnidad", query = "SELECT d FROM DetalleReceta d WHERE d.nombreUnidad = :nombreUnidad")})
public class DetalleReceta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private double cantidad;
    @Basic(optional = false)
    @Column(name = "costo_unitario")
    private double costoUnitario;
    @Basic(optional = false)
    @Column(name = "costo")
    private double costo;
    @Basic(optional = false)
    @Column(name = "nombre_unidad")
    private String nombreUnidad;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "receta", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Receta receta;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidad;

    public DetalleReceta() {
    }

    public DetalleReceta(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleReceta(Integer codigo, double cantidad, double costoUnitario, double costo, String nombreUnidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
        this.costo = costo;
        this.nombreUnidad = nombreUnidad;
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

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
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
        if (!(object instanceof DetalleReceta)) {
            return false;
        }
        DetalleReceta other = (DetalleReceta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleReceta[ codigo=" + codigo + " ]";
    }
    
}
