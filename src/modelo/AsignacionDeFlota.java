/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "asignacion_de_flota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsignacionDeFlota.findAll", query = "SELECT a FROM AsignacionDeFlota a")
    , @NamedQuery(name = "AsignacionDeFlota.findByCodigo", query = "SELECT a FROM AsignacionDeFlota a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "AsignacionDeFlota.findByFecha", query = "SELECT a FROM AsignacionDeFlota a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "AsignacionDeFlota.findByNombreResponsable", query = "SELECT a FROM AsignacionDeFlota a WHERE a.nombreResponsable = :nombreResponsable")
    , @NamedQuery(name = "AsignacionDeFlota.findByNombreFlota", query = "SELECT a FROM AsignacionDeFlota a WHERE a.nombreFlota = :nombreFlota")
    , @NamedQuery(name = "AsignacionDeFlota.findByImei", query = "SELECT a FROM AsignacionDeFlota a WHERE a.imei = :imei")
    , @NamedQuery(name = "AsignacionDeFlota.findBySim", query = "SELECT a FROM AsignacionDeFlota a WHERE a.sim = :sim")
    , @NamedQuery(name = "AsignacionDeFlota.findByModelo", query = "SELECT a FROM AsignacionDeFlota a WHERE a.modelo = :modelo")
    , @NamedQuery(name = "AsignacionDeFlota.findByMarca", query = "SELECT a FROM AsignacionDeFlota a WHERE a.marca = :marca")
    , @NamedQuery(name = "AsignacionDeFlota.findByNombreUsuario", query = "SELECT a FROM AsignacionDeFlota a WHERE a.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "AsignacionDeFlota.findByAnulado", query = "SELECT a FROM AsignacionDeFlota a WHERE a.anulado = :anulado")
    , @NamedQuery(name = "AsignacionDeFlota.findByFechaAnulado", query = "SELECT a FROM AsignacionDeFlota a WHERE a.fechaAnulado = :fechaAnulado")
    , @NamedQuery(name = "AsignacionDeFlota.findByAnuladorPor", query = "SELECT a FROM AsignacionDeFlota a WHERE a.anuladorPor = :anuladorPor")
    , @NamedQuery(name = "AsignacionDeFlota.findByDepartamento", query = "SELECT a FROM AsignacionDeFlota a WHERE a.departamento = :departamento")
    , @NamedQuery(name = "AsignacionDeFlota.findByNombreDepartamento", query = "SELECT a FROM AsignacionDeFlota a WHERE a.nombreDepartamento = :nombreDepartamento")
    , @NamedQuery(name = "AsignacionDeFlota.findByEmpresa", query = "SELECT a FROM AsignacionDeFlota a WHERE a.empresa = :empresa")
    , @NamedQuery(name = "AsignacionDeFlota.findByNombreEmpresa", query = "SELECT a FROM AsignacionDeFlota a WHERE a.nombreEmpresa = :nombreEmpresa")
    , @NamedQuery(name = "AsignacionDeFlota.findByCargo", query = "SELECT a FROM AsignacionDeFlota a WHERE a.cargo = :cargo")
    , @NamedQuery(name = "AsignacionDeFlota.findByNombreCargo", query = "SELECT a FROM AsignacionDeFlota a WHERE a.nombreCargo = :nombreCargo")
    , @NamedQuery(name = "AsignacionDeFlota.findByEstado", query = "SELECT a FROM AsignacionDeFlota a WHERE a.estado = :estado")
    , @NamedQuery(name = "AsignacionDeFlota.findByFechaEstado", query = "SELECT a FROM AsignacionDeFlota a WHERE a.fechaEstado = :fechaEstado")
    , @NamedQuery(name = "AsignacionDeFlota.findByFechaRegistro", query = "SELECT a FROM AsignacionDeFlota a WHERE a.fechaRegistro = :fechaRegistro")})
public class AsignacionDeFlota implements Serializable {

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
    @Column(name = "nombre_responsable")
    private String nombreResponsable;
    @Basic(optional = false)
    @Column(name = "nombre_flota")
    private String nombreFlota;
    @Basic(optional = false)
    @Column(name = "imei")
    private String imei;
    @Basic(optional = false)
    @Column(name = "sim")
    private String sim;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "anulado")
    private boolean anulado;
    @Column(name = "fecha_anulado")
    @Temporal(TemporalType.DATE)
    private Date fechaAnulado;
    @Column(name = "anulador_por")
    private String anuladorPor;
    @Column(name = "departamento")
    private Integer departamento;
    @Column(name = "nombre_departamento")
    private String nombreDepartamento;
    @Column(name = "empresa")
    private Integer empresa;
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;
    @Column(name = "cargo")
    private Integer cargo;
    @Column(name = "nombre_cargo")
    private String nombreCargo;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_estado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "responsable", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Empleado responsable;
    @JoinColumn(name = "flota", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private RegistroDeFlota flota;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public AsignacionDeFlota() {
    }

    public AsignacionDeFlota(Integer codigo) {
        this.codigo = codigo;
    }

    public AsignacionDeFlota(Integer codigo, Date fecha, String nombreResponsable, String nombreFlota, String imei, String sim, String nombreUsuario, boolean anulado) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.nombreResponsable = nombreResponsable;
        this.nombreFlota = nombreFlota;
        this.imei = imei;
        this.sim = sim;
        this.nombreUsuario = nombreUsuario;
        this.anulado = anulado;
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

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getNombreFlota() {
        return nombreFlota;
    }

    public void setNombreFlota(String nombreFlota) {
        this.nombreFlota = nombreFlota;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public Date getFechaAnulado() {
        return fechaAnulado;
    }

    public void setFechaAnulado(Date fechaAnulado) {
        this.fechaAnulado = fechaAnulado;
    }

    public String getAnuladorPor() {
        return anuladorPor;
    }

    public void setAnuladorPor(String anuladorPor) {
        this.anuladorPor = anuladorPor;
    }

    public Integer getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public Integer getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Integer empresa) {
        this.empresa = empresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Integer getCargo() {
        return cargo;
    }

    public void setCargo(Integer cargo) {
        this.cargo = cargo;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
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

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public Empleado getResponsable() {
        return responsable;
    }

    public void setResponsable(Empleado responsable) {
        this.responsable = responsable;
    }

    public RegistroDeFlota getFlota() {
        return flota;
    }

    public void setFlota(RegistroDeFlota flota) {
        this.flota = flota;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof AsignacionDeFlota)) {
            return false;
        }
        AsignacionDeFlota other = (AsignacionDeFlota) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.AsignacionDeFlota[ codigo=" + codigo + " ]";
    }
    
}
