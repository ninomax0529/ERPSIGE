/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "devolucion_de_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionDeInventario.findAll", query = "SELECT d FROM DevolucionDeInventario d")
    , @NamedQuery(name = "DevolucionDeInventario.findByCodigo", query = "SELECT d FROM DevolucionDeInventario d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DevolucionDeInventario.findByFecha", query = "SELECT d FROM DevolucionDeInventario d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "DevolucionDeInventario.findBySuplidor", query = "SELECT d FROM DevolucionDeInventario d WHERE d.suplidor = :suplidor")
    , @NamedQuery(name = "DevolucionDeInventario.findByNombreSuplidor", query = "SELECT d FROM DevolucionDeInventario d WHERE d.nombreSuplidor = :nombreSuplidor")
    , @NamedQuery(name = "DevolucionDeInventario.findByNumeroFactura", query = "SELECT d FROM DevolucionDeInventario d WHERE d.numeroFactura = :numeroFactura")
    , @NamedQuery(name = "DevolucionDeInventario.findByFechaRegistro", query = "SELECT d FROM DevolucionDeInventario d WHERE d.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "DevolucionDeInventario.findByAnulada", query = "SELECT d FROM DevolucionDeInventario d WHERE d.anulada = :anulada")})
public class DevolucionDeInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "suplidor")
    private Integer suplidor;
    @Column(name = "nombre_suplidor")
    private String nombreSuplidor;
    @Column(name = "numero_factura")
    private String numeroFactura;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "anulada")
    private boolean anulada;
    @OneToMany(mappedBy = "devolucionDeInventario",cascade = CascadeType.ALL)
    private Collection<DetalleDevolucionDeInventario> detalleDevolucionDeInventarioCollection;
    @JoinColumn(name = "factura", referencedColumnName = "codigo")
    @ManyToOne
    private Factura factura;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "tipo_devolucion", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoDevolucion tipoDevolucion;

    public DevolucionDeInventario() {
    }

    public DevolucionDeInventario(Integer codigo) {
        this.codigo = codigo;
    }

    public DevolucionDeInventario(Integer codigo, boolean anulada) {
        this.codigo = codigo;
        this.anulada = anulada;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Integer suplidor) {
        this.suplidor = suplidor;
    }

    public String getNombreSuplidor() {
        return nombreSuplidor;
    }

    public void setNombreSuplidor(String nombreSuplidor) {
        this.nombreSuplidor = nombreSuplidor;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    @XmlTransient
    public Collection<DetalleDevolucionDeInventario> getDetalleDevolucionDeInventarioCollection() {
        return detalleDevolucionDeInventarioCollection;
    }

    public void setDetalleDevolucionDeInventarioCollection(Collection<DetalleDevolucionDeInventario> detalleDevolucionDeInventarioCollection) {
        this.detalleDevolucionDeInventarioCollection = detalleDevolucionDeInventarioCollection;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public TipoDevolucion getTipoDevolucion() {
        return tipoDevolucion;
    }

    public void setTipoDevolucion(TipoDevolucion tipoDevolucion) {
        this.tipoDevolucion = tipoDevolucion;
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
        if (!(object instanceof DevolucionDeInventario)) {
            return false;
        }
        DevolucionDeInventario other = (DevolucionDeInventario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DevolucionDeInventario[ codigo=" + codigo + " ]";
    }
    
}
