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
@Table(name = "asistencia_tecnica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsistenciaTecnica.findAll", query = "SELECT a FROM AsistenciaTecnica a")
    , @NamedQuery(name = "AsistenciaTecnica.findByCodigo", query = "SELECT a FROM AsistenciaTecnica a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "AsistenciaTecnica.findByFechaRegistro", query = "SELECT a FROM AsistenciaTecnica a WHERE a.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "AsistenciaTecnica.findByNombreCliente", query = "SELECT a FROM AsistenciaTecnica a WHERE a.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "AsistenciaTecnica.findByNombreTecnico", query = "SELECT a FROM AsistenciaTecnica a WHERE a.nombreTecnico = :nombreTecnico")
    , @NamedQuery(name = "AsistenciaTecnica.findByFechaCierre", query = "SELECT a FROM AsistenciaTecnica a WHERE a.fechaCierre = :fechaCierre")
    , @NamedQuery(name = "AsistenciaTecnica.findByCantidad", query = "SELECT a FROM AsistenciaTecnica a WHERE a.cantidad = :cantidad")
    , @NamedQuery(name = "AsistenciaTecnica.findByPrecio", query = "SELECT a FROM AsistenciaTecnica a WHERE a.precio = :precio")
    , @NamedQuery(name = "AsistenciaTecnica.findByDetalleContrato", query = "SELECT a FROM AsistenciaTecnica a WHERE a.detalleContrato = :detalleContrato")
    , @NamedQuery(name = "AsistenciaTecnica.findByAnulada", query = "SELECT a FROM AsistenciaTecnica a WHERE a.anulada = :anulada")
    , @NamedQuery(name = "AsistenciaTecnica.findByFechaAnulada", query = "SELECT a FROM AsistenciaTecnica a WHERE a.fechaAnulada = :fechaAnulada")
    , @NamedQuery(name = "AsistenciaTecnica.findByAnuladaPor", query = "SELECT a FROM AsistenciaTecnica a WHERE a.anuladaPor = :anuladaPor")})
public class AsistenciaTecnica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Column(name = "nombre_tecnico")
    private String nombreTecnico;
    @Column(name = "fecha_cierre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierre;
    @Lob
    @Column(name = "origen_asistencia")
    private String origenAsistencia;
    @Lob
    @Column(name = "solucion")
    private String solucion;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Column(name = "detalle_contrato")
    private Integer detalleContrato;
    @Basic(optional = false)
    @Column(name = "anulada")
    private boolean anulada;
    @Column(name = "fecha_anulada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulada;
    @Column(name = "anulada_por")
    private String anuladaPor;
    @Lob
    @Column(name = "razon_anulada")
    private String razonAnulada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asistencia")
    private Collection<DetalleAsistenciaTecnica> detalleAsistenciaTecnicaCollection;
    @JoinColumn(name = "cliente", referencedColumnName = "codigo")
    @ManyToOne
    private Cliente cliente;
    @JoinColumn(name = "estado", referencedColumnName = "codigo")
    @ManyToOne
    private EstadoAsistenciaTecnica estado;
    @JoinColumn(name = "tecnico", referencedColumnName = "codigo")
    @ManyToOne
    private Instalador tecnico;
    @JoinColumn(name = "tipo_de_asistencia", referencedColumnName = "codigo")
    @ManyToOne
    private TipoDeAsistencia tipoDeAsistencia;
    @JoinColumn(name = "ubicacion", referencedColumnName = "codigo")
    @ManyToOne
    private UbicacionAsistencia ubicacion;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;

    public AsistenciaTecnica() {
    }

    public AsistenciaTecnica(Integer codigo) {
        this.codigo = codigo;
    }

    public AsistenciaTecnica(Integer codigo, boolean anulada) {
        this.codigo = codigo;
        this.anulada = anulada;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreTecnico() {
        return nombreTecnico;
    }

    public void setNombreTecnico(String nombreTecnico) {
        this.nombreTecnico = nombreTecnico;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getOrigenAsistencia() {
        return origenAsistencia;
    }

    public void setOrigenAsistencia(String origenAsistencia) {
        this.origenAsistencia = origenAsistencia;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getDetalleContrato() {
        return detalleContrato;
    }

    public void setDetalleContrato(Integer detalleContrato) {
        this.detalleContrato = detalleContrato;
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

    public String getRazonAnulada() {
        return razonAnulada;
    }

    public void setRazonAnulada(String razonAnulada) {
        this.razonAnulada = razonAnulada;
    }

    @XmlTransient
    public Collection<DetalleAsistenciaTecnica> getDetalleAsistenciaTecnicaCollection() {
        return detalleAsistenciaTecnicaCollection;
    }

    public void setDetalleAsistenciaTecnicaCollection(Collection<DetalleAsistenciaTecnica> detalleAsistenciaTecnicaCollection) {
        this.detalleAsistenciaTecnicaCollection = detalleAsistenciaTecnicaCollection;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EstadoAsistenciaTecnica getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsistenciaTecnica estado) {
        this.estado = estado;
    }

    public Instalador getTecnico() {
        return tecnico;
    }

    public void setTecnico(Instalador tecnico) {
        this.tecnico = tecnico;
    }

    public TipoDeAsistencia getTipoDeAsistencia() {
        return tipoDeAsistencia;
    }

    public void setTipoDeAsistencia(TipoDeAsistencia tipoDeAsistencia) {
        this.tipoDeAsistencia = tipoDeAsistencia;
    }

    public UbicacionAsistencia getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionAsistencia ubicacion) {
        this.ubicacion = ubicacion;
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
        if (!(object instanceof AsistenciaTecnica)) {
            return false;
        }
        AsistenciaTecnica other = (AsistenciaTecnica) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.AsistenciaTecnica[ codigo=" + codigo + " ]";
    }
    
}
