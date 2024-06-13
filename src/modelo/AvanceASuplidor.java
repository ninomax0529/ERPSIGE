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
@Table(name = "avance_a_suplidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AvanceASuplidor.findAll", query = "SELECT a FROM AvanceASuplidor a")
    , @NamedQuery(name = "AvanceASuplidor.findByCodigo", query = "SELECT a FROM AvanceASuplidor a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "AvanceASuplidor.findByFecha", query = "SELECT a FROM AvanceASuplidor a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "AvanceASuplidor.findByNombreSuplidor", query = "SELECT a FROM AvanceASuplidor a WHERE a.nombreSuplidor = :nombreSuplidor")
    , @NamedQuery(name = "AvanceASuplidor.findByTotalAvance", query = "SELECT a FROM AvanceASuplidor a WHERE a.totalAvance = :totalAvance")
    , @NamedQuery(name = "AvanceASuplidor.findByTotalPendiente", query = "SELECT a FROM AvanceASuplidor a WHERE a.totalPendiente = :totalPendiente")
    , @NamedQuery(name = "AvanceASuplidor.findByFechaActualizacion", query = "SELECT a FROM AvanceASuplidor a WHERE a.fechaActualizacion = :fechaActualizacion")})
public class AvanceASuplidor implements Serializable {

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
    @Column(name = "nombre_suplidor")
    private String nombreSuplidor;
    @Basic(optional = false)
    @Column(name = "total_avance")
    private double totalAvance;
    @Basic(optional = false)
    @Column(name = "total_pendiente")
    private double totalPendiente;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "suplidor", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Suplidor suplidor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avanceASuplidor")
    private Collection<DetalleAvanceASuplidor> detalleAvanceASuplidorCollection;

    public AvanceASuplidor() {
    }

    public AvanceASuplidor(Integer codigo) {
        this.codigo = codigo;
    }

    public AvanceASuplidor(Integer codigo, Date fecha, String nombreSuplidor, double totalAvance, double totalPendiente) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.nombreSuplidor = nombreSuplidor;
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

    public String getNombreSuplidor() {
        return nombreSuplidor;
    }

    public void setNombreSuplidor(String nombreSuplidor) {
        this.nombreSuplidor = nombreSuplidor;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
    }

    @XmlTransient
    public Collection<DetalleAvanceASuplidor> getDetalleAvanceASuplidorCollection() {
        return detalleAvanceASuplidorCollection;
    }

    public void setDetalleAvanceASuplidorCollection(Collection<DetalleAvanceASuplidor> detalleAvanceASuplidorCollection) {
        this.detalleAvanceASuplidorCollection = detalleAvanceASuplidorCollection;
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
        if (!(object instanceof AvanceASuplidor)) {
            return false;
        }
        AvanceASuplidor other = (AvanceASuplidor) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.AvanceASuplidor[ codigo=" + codigo + " ]";
    }
    
}
