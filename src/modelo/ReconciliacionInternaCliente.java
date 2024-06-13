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
@Table(name = "reconciliacion_interna_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReconciliacionInternaCliente.findAll", query = "SELECT r FROM ReconciliacionInternaCliente r")
    , @NamedQuery(name = "ReconciliacionInternaCliente.findByCodigo", query = "SELECT r FROM ReconciliacionInternaCliente r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "ReconciliacionInternaCliente.findByFecha", query = "SELECT r FROM ReconciliacionInternaCliente r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "ReconciliacionInternaCliente.findByMonto", query = "SELECT r FROM ReconciliacionInternaCliente r WHERE r.monto = :monto")
    , @NamedQuery(name = "ReconciliacionInternaCliente.findByNombreCliente", query = "SELECT r FROM ReconciliacionInternaCliente r WHERE r.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "ReconciliacionInternaCliente.findByAnulada", query = "SELECT r FROM ReconciliacionInternaCliente r WHERE r.anulada = :anulada")
    , @NamedQuery(name = "ReconciliacionInternaCliente.findByFechaAnulada", query = "SELECT r FROM ReconciliacionInternaCliente r WHERE r.fechaAnulada = :fechaAnulada")
    , @NamedQuery(name = "ReconciliacionInternaCliente.findByAnuladaPor", query = "SELECT r FROM ReconciliacionInternaCliente r WHERE r.anuladaPor = :anuladaPor")})
public class ReconciliacionInternaCliente implements Serializable {

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @Basic(optional = false)
    @Column(name = "imprimir")
    private boolean imprimir;

    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private UnidadDeNegocio unidadDeNegocio;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "monto")
    private double monto;
    @Basic(optional = false)
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Basic(optional = false)
    @Column(name = "anulada")
    private boolean anulada;
    @Column(name = "fecha_anulada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulada;
    @Column(name = "anulada_por")
    private String anuladaPor;
    @Lob
    @Column(name = "razon_anulacion")
    private String razonAnulacion;
    @JoinColumn(name = "cliente", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reconciliacion")
    private Collection<DetalleReconciliacionInternaCliente> detalleReconciliacionInternaClienteCollection;

    public ReconciliacionInternaCliente() {
    }

    public ReconciliacionInternaCliente(Integer codigo) {
        this.codigo = codigo;
    }

    public ReconciliacionInternaCliente(Integer codigo, Date fecha, double monto, String nombreCliente, boolean anulada) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.monto = monto;
        this.nombreCliente = nombreCliente;
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

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public Date getFechaAnulada() {
        return fechaAnulada;
    }

    public void setFechaAnulada(Date fechaAnulada) {
        this.fechaAnulada = fechaAnulada;
    }

    public String getAnuladaPor() {
        return anuladaPor;
    }

    public void setAnuladaPor(String anuladaPor) {
        this.anuladaPor = anuladaPor;
    }

    public String getRazonAnulacion() {
        return razonAnulacion;
    }

    public void setRazonAnulacion(String razonAnulacion) {
        this.razonAnulacion = razonAnulacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<DetalleReconciliacionInternaCliente> getDetalleReconciliacionInternaClienteCollection() {
        return detalleReconciliacionInternaClienteCollection;
    }

    public void setDetalleReconciliacionInternaClienteCollection(Collection<DetalleReconciliacionInternaCliente> detalleReconciliacionInternaClienteCollection) {
        this.detalleReconciliacionInternaClienteCollection = detalleReconciliacionInternaClienteCollection;
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
        if (!(object instanceof ReconciliacionInternaCliente)) {
            return false;
        }
        ReconciliacionInternaCliente other = (ReconciliacionInternaCliente) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ReconciliacionInternaCliente[ codigo=" + codigo + " ]";
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public boolean getImprimir() {
        return imprimir;
    }

    public void setImprimir(boolean imprimir) {
        this.imprimir = imprimir;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}
