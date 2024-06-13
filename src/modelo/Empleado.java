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
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByCodigo", query = "SELECT e FROM Empleado e WHERE e.codigo = :codigo")
    , @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empleado.findByFecha", query = "SELECT e FROM Empleado e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "Empleado.findBySexo", query = "SELECT e FROM Empleado e WHERE e.sexo = :sexo")
    , @NamedQuery(name = "Empleado.findBySueldoMensual", query = "SELECT e FROM Empleado e WHERE e.sueldoMensual = :sueldoMensual")
    , @NamedQuery(name = "Empleado.findBySueldoQuincenal", query = "SELECT e FROM Empleado e WHERE e.sueldoQuincenal = :sueldoQuincenal")
    , @NamedQuery(name = "Empleado.findBySueldoSemanal", query = "SELECT e FROM Empleado e WHERE e.sueldoSemanal = :sueldoSemanal")
    , @NamedQuery(name = "Empleado.findByApellido", query = "SELECT e FROM Empleado e WHERE e.apellido = :apellido")
    , @NamedQuery(name = "Empleado.findByFechaCumpleanio", query = "SELECT e FROM Empleado e WHERE e.fechaCumpleanio = :fechaCumpleanio")
    , @NamedQuery(name = "Empleado.findByTelefono", query = "SELECT e FROM Empleado e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Empleado.findByCelular", query = "SELECT e FROM Empleado e WHERE e.celular = :celular")
    , @NamedQuery(name = "Empleado.findByCedula", query = "SELECT e FROM Empleado e WHERE e.cedula = :cedula")
    , @NamedQuery(name = "Empleado.findByCorreo", query = "SELECT e FROM Empleado e WHERE e.correo = :correo")
    , @NamedQuery(name = "Empleado.findBySueldoPorHora", query = "SELECT e FROM Empleado e WHERE e.sueldoPorHora = :sueldoPorHora")
    , @NamedQuery(name = "Empleado.findBySueldoPorDia", query = "SELECT e FROM Empleado e WHERE e.sueldoPorDia = :sueldoPorDia")})
public class Empleado implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responsable")
    private Collection<AsignacionDeFlota> asignacionDeFlotaCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @Column(name = "sueldo_mensual")
    private double sueldoMensual;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sueldo_quincenal")
    private Double sueldoQuincenal;
    @Column(name = "sueldo_semanal")
    private Double sueldoSemanal;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Lob
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "fecha_cumpleanio")
    @Temporal(TemporalType.DATE)
    private Date fechaCumpleanio;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "celular")
    private String celular;
    @Column(name = "cedula")
    private String cedula;
    @Column(name = "correo")
    private String correo;
    @Column(name = "sueldo_por_hora")
    private Double sueldoPorHora;
    @Column(name = "sueldo_por_dia")
    private Double sueldoPorDia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Collection<DetalleRegistroHoraExtra> detalleRegistroHoraExtraCollection;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "cargo", referencedColumnName = "codigo")
    @ManyToOne
    private Cargo cargo;
    @JoinColumn(name = "condicion", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private CondicionEmpleado condicion;
    @JoinColumn(name = "estado", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private EstadoEmpleado estado;
    @JoinColumn(name = "estado_civil", referencedColumnName = "codigo")
    @ManyToOne
    private EstadoCivil estadoCivil;
    @JoinColumn(name = "tipo_nomina", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoDeNomina tipoNomina;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Collection<DependienteEmpleado> dependienteEmpleadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Collection<DetalleNomina> detalleNominaCollection;

    public Empleado() {
    }

    public Empleado(Integer codigo) {
        this.codigo = codigo;
    }

    public Empleado(Integer codigo, String nombre, Date fecha, String sexo, double sueldoMensual, String apellido) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fecha = fecha;
        this.sexo = sexo;
        this.sueldoMensual = sueldoMensual;
        this.apellido = apellido;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(double sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public Double getSueldoQuincenal() {
        return sueldoQuincenal;
    }

    public void setSueldoQuincenal(Double sueldoQuincenal) {
        this.sueldoQuincenal = sueldoQuincenal;
    }

    public Double getSueldoSemanal() {
        return sueldoSemanal;
    }

    public void setSueldoSemanal(Double sueldoSemanal) {
        this.sueldoSemanal = sueldoSemanal;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaCumpleanio() {
        return fechaCumpleanio;
    }

    public void setFechaCumpleanio(Date fechaCumpleanio) {
        this.fechaCumpleanio = fechaCumpleanio;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Double getSueldoPorHora() {
        return sueldoPorHora;
    }

    public void setSueldoPorHora(Double sueldoPorHora) {
        this.sueldoPorHora = sueldoPorHora;
    }

    public Double getSueldoPorDia() {
        return sueldoPorDia;
    }

    public void setSueldoPorDia(Double sueldoPorDia) {
        this.sueldoPorDia = sueldoPorDia;
    }

    @XmlTransient
    public Collection<DetalleRegistroHoraExtra> getDetalleRegistroHoraExtraCollection() {
        return detalleRegistroHoraExtraCollection;
    }

    public void setDetalleRegistroHoraExtraCollection(Collection<DetalleRegistroHoraExtra> detalleRegistroHoraExtraCollection) {
        this.detalleRegistroHoraExtraCollection = detalleRegistroHoraExtraCollection;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public CondicionEmpleado getCondicion() {
        return condicion;
    }

    public void setCondicion(CondicionEmpleado condicion) {
        this.condicion = condicion;
    }

    public EstadoEmpleado getEstado() {
        return estado;
    }

    public void setEstado(EstadoEmpleado estado) {
        this.estado = estado;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public TipoDeNomina getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(TipoDeNomina tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<DependienteEmpleado> getDependienteEmpleadoCollection() {
        return dependienteEmpleadoCollection;
    }

    public void setDependienteEmpleadoCollection(Collection<DependienteEmpleado> dependienteEmpleadoCollection) {
        this.dependienteEmpleadoCollection = dependienteEmpleadoCollection;
    }

    @XmlTransient
    public Collection<DetalleNomina> getDetalleNominaCollection() {
        return detalleNominaCollection;
    }

    public void setDetalleNominaCollection(Collection<DetalleNomina> detalleNominaCollection) {
        this.detalleNominaCollection = detalleNominaCollection;
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
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Empleado[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<AsignacionDeFlota> getAsignacionDeFlotaCollection() {
        return asignacionDeFlotaCollection;
    }

    public void setAsignacionDeFlotaCollection(Collection<AsignacionDeFlota> asignacionDeFlotaCollection) {
        this.asignacionDeFlotaCollection = asignacionDeFlotaCollection;
    }
    
}
