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
@Table(name = "detalle_solicitud_salida_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleSolicitudSalidaInventario.findAll", query = "SELECT d FROM DetalleSolicitudSalidaInventario d")
    , @NamedQuery(name = "DetalleSolicitudSalidaInventario.findByCodigo", query = "SELECT d FROM DetalleSolicitudSalidaInventario d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleSolicitudSalidaInventario.findByDescripcionArticulo", query = "SELECT d FROM DetalleSolicitudSalidaInventario d WHERE d.descripcionArticulo = :descripcionArticulo")
    , @NamedQuery(name = "DetalleSolicitudSalidaInventario.findByCantidadSolicitada", query = "SELECT d FROM DetalleSolicitudSalidaInventario d WHERE d.cantidadSolicitada = :cantidadSolicitada")
    , @NamedQuery(name = "DetalleSolicitudSalidaInventario.findByCantidadDespachada", query = "SELECT d FROM DetalleSolicitudSalidaInventario d WHERE d.cantidadDespachada = :cantidadDespachada")})
public class DetalleSolicitudSalidaInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "descripcion_articulo")
    private String descripcionArticulo;
    @Basic(optional = false)
    @Column(name = "cantidad_solicitada")
    private double cantidadSolicitada;
    @Basic(optional = false)
    @Column(name = "cantidad_despachada")
    private double cantidadDespachada;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "solicitud_salida_inventario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private SolicitudSalidaInventario solicitudSalidaInventario;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Unidad unidad;
    @JoinColumn(name = "almacen", referencedColumnName = "codigo")
    @ManyToOne
    private Almacen almacen;

    public DetalleSolicitudSalidaInventario() {
    }

    public DetalleSolicitudSalidaInventario(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleSolicitudSalidaInventario(Integer codigo, String descripcionArticulo, double cantidadSolicitada, double cantidadDespachada) {
        this.codigo = codigo;
        this.descripcionArticulo = descripcionArticulo;
        this.cantidadSolicitada = cantidadSolicitada;
        this.cantidadDespachada = cantidadDespachada;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public double getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(double cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public double getCantidadDespachada() {
        return cantidadDespachada;
    }

    public void setCantidadDespachada(double cantidadDespachada) {
        this.cantidadDespachada = cantidadDespachada;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public SolicitudSalidaInventario getSolicitudSalidaInventario() {
        return solicitudSalidaInventario;
    }

    public void setSolicitudSalidaInventario(SolicitudSalidaInventario solicitudSalidaInventario) {
        this.solicitudSalidaInventario = solicitudSalidaInventario;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
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
        if (!(object instanceof DetalleSolicitudSalidaInventario)) {
            return false;
        }
        DetalleSolicitudSalidaInventario other = (DetalleSolicitudSalidaInventario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleSolicitudSalidaInventario[ codigo=" + codigo + " ]";
    }
    
}
