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
@Table(name = "avance_de_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AvanceDeCliente.findAll", query = "SELECT a FROM AvanceDeCliente a")
    , @NamedQuery(name = "AvanceDeCliente.findByCodigo", query = "SELECT a FROM AvanceDeCliente a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "AvanceDeCliente.findByFecha", query = "SELECT a FROM AvanceDeCliente a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "AvanceDeCliente.findByNombreCliente", query = "SELECT a FROM AvanceDeCliente a WHERE a.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "AvanceDeCliente.findByTotalAvance", query = "SELECT a FROM AvanceDeCliente a WHERE a.totalAvance = :totalAvance")
    , @NamedQuery(name = "AvanceDeCliente.findByTotalPendiente", query = "SELECT a FROM AvanceDeCliente a WHERE a.totalPendiente = :totalPendiente")
    , @NamedQuery(name = "AvanceDeCliente.findByFechaActualizacion", query = "SELECT a FROM AvanceDeCliente a WHERE a.fechaActualizacion = :fechaActualizacion")})
public class AvanceDeCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Basic(optional = false)
    @Column(name = "total_avance")
    private double totalAvance;
    @Basic(optional = false)
    @Column(name = "total_pendiente")
    private double totalPendiente;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @JoinColumn(name = "cliente", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avanceDeCliente")
    private Collection<DetalleAvanceDeCliente> detalleAvanceDeClienteCollection;

    public AvanceDeCliente() {
    }

    public AvanceDeCliente(Integer codigo) {
        this.codigo = codigo;
    }

    public AvanceDeCliente(Integer codigo, Date fecha, String nombreCliente, double totalAvance, double totalPendiente) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
        this.totalAvance = totalAvance;
        this.totalPendiente = totalPendiente;
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public double getTotalAvance() {
        return totalAvance;
    }

    public void setTotalAvance(double totalAvance) {
        this.totalAvance = totalAvance;
    }

    public double getTotalPendiente() {
        return totalPendiente;
    }

    public void setTotalPendiente(double totalPendiente) {
        this.totalPendiente = totalPendiente;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
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
    public Collection<DetalleAvanceDeCliente> getDetalleAvanceDeClienteCollection() {
        return detalleAvanceDeClienteCollection;
    }

    public void setDetalleAvanceDeClienteCollection(Collection<DetalleAvanceDeCliente> detalleAvanceDeClienteCollection) {
        this.detalleAvanceDeClienteCollection = detalleAvanceDeClienteCollection;
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
        if (!(object instanceof AvanceDeCliente)) {
            return false;
        }
        AvanceDeCliente other = (AvanceDeCliente) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.AvanceDeCliente[ codigo=" + codigo + " ]";
    }
    
}
