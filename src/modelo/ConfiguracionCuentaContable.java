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
@Table(name = "configuracion_cuenta_contable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfiguracionCuentaContable.findAll", query = "SELECT c FROM ConfiguracionCuentaContable c")
    , @NamedQuery(name = "ConfiguracionCuentaContable.findByCodigo", query = "SELECT c FROM ConfiguracionCuentaContable c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "ConfiguracionCuentaContable.findByFecha", query = "SELECT c FROM ConfiguracionCuentaContable c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "ConfiguracionCuentaContable.findByNombreModulo", query = "SELECT c FROM ConfiguracionCuentaContable c WHERE c.nombreModulo = :nombreModulo")
    , @NamedQuery(name = "ConfiguracionCuentaContable.findByFechaRegistro", query = "SELECT c FROM ConfiguracionCuentaContable c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "ConfiguracionCuentaContable.findByHabilitada", query = "SELECT c FROM ConfiguracionCuentaContable c WHERE c.habilitada = :habilitada")
    , @NamedQuery(name = "ConfiguracionCuentaContable.findByNombreTipoConcepto", query = "SELECT c FROM ConfiguracionCuentaContable c WHERE c.nombreTipoConcepto = :nombreTipoConcepto")
    , @NamedQuery(name = "ConfiguracionCuentaContable.findByDescripcion", query = "SELECT c FROM ConfiguracionCuentaContable c WHERE c.descripcion = :descripcion")})
public class ConfiguracionCuentaContable implements Serializable {

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
    @Column(name = "nombre_modulo")
    private String nombreModulo;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "habilitada")
    private boolean habilitada;
    @Column(name = "nombre_tipo_concepto")
    private String nombreTipoConcepto;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "configuracionCuentaContable")
    private Collection<DetalleConfiguaracionCuentaContable> detalleConfiguaracionCuentaContableCollection;
    @JoinColumn(name = "modulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Modulo modulo;
    @JoinColumn(name = "tipo_concepto", referencedColumnName = "codigo")
    @ManyToOne
    private TipoConcepto tipoConcepto;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

    public ConfiguracionCuentaContable() {
    }

    public ConfiguracionCuentaContable(Integer codigo) {
        this.codigo = codigo;
    }

    public ConfiguracionCuentaContable(Integer codigo, Date fecha, String nombreModulo, Date fechaRegistro, boolean habilitada) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.nombreModulo = nombreModulo;
        this.fechaRegistro = fechaRegistro;
        this.habilitada = habilitada;
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

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    public String getNombreTipoConcepto() {
        return nombreTipoConcepto;
    }

    public void setNombreTipoConcepto(String nombreTipoConcepto) {
        this.nombreTipoConcepto = nombreTipoConcepto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<DetalleConfiguaracionCuentaContable> getDetalleConfiguaracionCuentaContableCollection() {
        return detalleConfiguaracionCuentaContableCollection;
    }

    public void setDetalleConfiguaracionCuentaContableCollection(Collection<DetalleConfiguaracionCuentaContable> detalleConfiguaracionCuentaContableCollection) {
        this.detalleConfiguaracionCuentaContableCollection = detalleConfiguaracionCuentaContableCollection;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public TipoConcepto getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(TipoConcepto tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfiguracionCuentaContable)) {
            return false;
        }
        ConfiguracionCuentaContable other = (ConfiguracionCuentaContable) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ConfiguracionCuentaContable[ codigo=" + codigo + " ]";
    }
    
}
