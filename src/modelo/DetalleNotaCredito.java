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
@Table(name = "detalle_nota_credito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleNotaCredito.findAll", query = "SELECT d FROM DetalleNotaCredito d")
    , @NamedQuery(name = "DetalleNotaCredito.findByCodigo", query = "SELECT d FROM DetalleNotaCredito d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleNotaCredito.findByPrecio", query = "SELECT d FROM DetalleNotaCredito d WHERE d.precio = :precio")
    , @NamedQuery(name = "DetalleNotaCredito.findByDescuento", query = "SELECT d FROM DetalleNotaCredito d WHERE d.descuento = :descuento")
    , @NamedQuery(name = "DetalleNotaCredito.findByItbis", query = "SELECT d FROM DetalleNotaCredito d WHERE d.itbis = :itbis")
    , @NamedQuery(name = "DetalleNotaCredito.findByCantidad", query = "SELECT d FROM DetalleNotaCredito d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleNotaCredito.findByCantidadDevuelta", query = "SELECT d FROM DetalleNotaCredito d WHERE d.cantidadDevuelta = :cantidadDevuelta")
    , @NamedQuery(name = "DetalleNotaCredito.findByUnidad", query = "SELECT d FROM DetalleNotaCredito d WHERE d.unidad = :unidad")})
public class DetalleNotaCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "itbis")
    private Double itbis;
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "cantidad_devuelta")
    private Double cantidadDevuelta;
    @Column(name = "unidad")
    private String unidad;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne
    private Articulo articulo;
    @JoinColumn(name = "nota_credito", referencedColumnName = "codigo")
    @ManyToOne
    private NotaCredito notaCredito;

    public DetalleNotaCredito() {
    }

    public DetalleNotaCredito(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getItbis() {
        return itbis;
    }

    public void setItbis(Double itbis) {
        this.itbis = itbis;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCantidadDevuelta() {
        return cantidadDevuelta;
    }

    public void setCantidadDevuelta(Double cantidadDevuelta) {
        this.cantidadDevuelta = cantidadDevuelta;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public NotaCredito getNotaCredito() {
        return notaCredito;
    }

    public void setNotaCredito(NotaCredito notaCredito) {
        this.notaCredito = notaCredito;
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
        if (!(object instanceof DetalleNotaCredito)) {
            return false;
        }
        DetalleNotaCredito other = (DetalleNotaCredito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleNotaCredito[ codigo=" + codigo + " ]";
    }
    
}
