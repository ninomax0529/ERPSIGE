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
@Table(name = "detalle_lista_de_precio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleListaDePrecio.findAll", query = "SELECT d FROM DetalleListaDePrecio d")
    , @NamedQuery(name = "DetalleListaDePrecio.findByCodigo", query = "SELECT d FROM DetalleListaDePrecio d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleListaDePrecio.findByCostoUnitario", query = "SELECT d FROM DetalleListaDePrecio d WHERE d.costoUnitario = :costoUnitario")
    , @NamedQuery(name = "DetalleListaDePrecio.findByPrecio", query = "SELECT d FROM DetalleListaDePrecio d WHERE d.precio = :precio")
    , @NamedQuery(name = "DetalleListaDePrecio.findByMargenDeBeneficio", query = "SELECT d FROM DetalleListaDePrecio d WHERE d.margenDeBeneficio = :margenDeBeneficio")
    , @NamedQuery(name = "DetalleListaDePrecio.findByPorcientoUtilidad", query = "SELECT d FROM DetalleListaDePrecio d WHERE d.porcientoUtilidad = :porcientoUtilidad")
    , @NamedQuery(name = "DetalleListaDePrecio.findByCantidadMinima", query = "SELECT d FROM DetalleListaDePrecio d WHERE d.cantidadMinima = :cantidadMinima")
    , @NamedQuery(name = "DetalleListaDePrecio.findByCantidadMaxima", query = "SELECT d FROM DetalleListaDePrecio d WHERE d.cantidadMaxima = :cantidadMaxima")
    , @NamedQuery(name = "DetalleListaDePrecio.findByHabilitado", query = "SELECT d FROM DetalleListaDePrecio d WHERE d.habilitado = :habilitado")
    , @NamedQuery(name = "DetalleListaDePrecio.findByPrecioAnterior", query = "SELECT d FROM DetalleListaDePrecio d WHERE d.precioAnterior = :precioAnterior")})
public class DetalleListaDePrecio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "costo_unitario")
    private double costoUnitario;
    @Basic(optional = false)
    @Column(name = "precio")
    private double precio;
    @Basic(optional = false)
    @Column(name = "margen_de_beneficio")
    private double margenDeBeneficio;
    @Basic(optional = false)
    @Column(name = "porciento_utilidad")
    private double porcientoUtilidad;
    @Basic(optional = false)
    @Column(name = "cantidad_minima")
    private double cantidadMinima;
    @Basic(optional = false)
    @Column(name = "cantidad_maxima")
    private double cantidadMaxima;
    @Basic(optional = false)
    @Column(name = "habilitado")
    private boolean habilitado;
    @Basic(optional = false)
    @Column(name = "precio_anterior")
    private double precioAnterior;
    @JoinColumn(name = "lista_de_precio", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private ListaDePrecio listaDePrecio;
    @JoinColumn(name = "unidad_salida", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidadSalida;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

    public DetalleListaDePrecio() {
    }

    public DetalleListaDePrecio(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleListaDePrecio(Integer codigo, double costoUnitario, double precio, double margenDeBeneficio, double porcientoUtilidad, double cantidadMinima, double cantidadMaxima, boolean habilitado, double precioAnterior) {
        this.codigo = codigo;
        this.costoUnitario = costoUnitario;
        this.precio = precio;
        this.margenDeBeneficio = margenDeBeneficio;
        this.porcientoUtilidad = porcientoUtilidad;
        this.cantidadMinima = cantidadMinima;
        this.cantidadMaxima = cantidadMaxima;
        this.habilitado = habilitado;
        this.precioAnterior = precioAnterior;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getMargenDeBeneficio() {
        return margenDeBeneficio;
    }

    public void setMargenDeBeneficio(double margenDeBeneficio) {
        this.margenDeBeneficio = margenDeBeneficio;
    }

    public double getPorcientoUtilidad() {
        return porcientoUtilidad;
    }

    public void setPorcientoUtilidad(double porcientoUtilidad) {
        this.porcientoUtilidad = porcientoUtilidad;
    }

    public double getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(double cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public double getCantidadMaxima() {
        return cantidadMaxima;
    }

    public void setCantidadMaxima(double cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public double getPrecioAnterior() {
        return precioAnterior;
    }

    public void setPrecioAnterior(double precioAnterior) {
        this.precioAnterior = precioAnterior;
    }

    public ListaDePrecio getListaDePrecio() {
        return listaDePrecio;
    }

    public void setListaDePrecio(ListaDePrecio listaDePrecio) {
        this.listaDePrecio = listaDePrecio;
    }

    public Unidad getUnidadSalida() {
        return unidadSalida;
    }

    public void setUnidadSalida(Unidad unidadSalida) {
        this.unidadSalida = unidadSalida;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
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
        if (!(object instanceof DetalleListaDePrecio)) {
            return false;
        }
        DetalleListaDePrecio other = (DetalleListaDePrecio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleListaDePrecio[ codigo=" + codigo + " ]";
    }
    
}
