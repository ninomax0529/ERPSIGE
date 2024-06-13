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
@Table(name = "datos_de_vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosDeVehiculo.findAll", query = "SELECT d FROM DatosDeVehiculo d")})
public class DatosDeVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
  
    @JoinColumn(name = "equipo_gps", referencedColumnName = "codigo")
    @ManyToOne
    private DetalleContratoDeServicio equipoGps;
    @Basic(optional = false)
    @Column(name = "modelo")
    private String modelo;
    @Basic(optional = false)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @Column(name = "chasis")
    private String chasis;
    @Basic(optional = false)
    @Column(name = "placa")
    private String placa;
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @Column(name = "color")
    private String color;
    @Column(name = "anio")
    private Integer anio;
    @Basic(optional = false)
    @Column(name = "adicional")
    private boolean adicional;
    @Basic(optional = false)
    @Column(name = "vehiculo")
    private String vehiculo;
    @Basic(optional = false)
    @Column(name = "habilitado")
    private boolean habilitado;
    @Column(name = "fecha_instalacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInstalacion;
    @Column(name = "fecha_desinstalacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesinstalacion;
    @Lob
    @Column(name = "razon_desinstalacion")
    private String razonDesinstalacion;
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Column(name = "usuario")
    private Integer usuario;
    @Column(name = "estado")
    private String estado;
    @Column(name = "contrato")
    private Integer contrato;
    @Basic(optional = false)
    @Column(name = "renovar")
    private boolean renovar;
    @Basic(optional = false)
    @Column(name = "traspasasado")
    private boolean traspasasado;
    @Lob
    @Column(name = "razon_actualizacion")
    private String razonActualizacion;
    @Column(name = "fecha_suspendido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSuspendido;
    @Column(name = "fecha_habilitado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHabilitado;
    @Basic(optional = false)
    @Column(name = "seleccionado")
    private boolean seleccionado;
    @JoinColumn(name = "servicio", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private DetalleContratoDeServicio servicio;
    @JoinColumn(name = "tipo_vehiculo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoVehiculo tipoVehiculo;

    public DatosDeVehiculo() {
    }

    public DatosDeVehiculo(Integer codigo) {
        this.codigo = codigo;
    }

    public DatosDeVehiculo(Integer codigo, String modelo, String marca, String chasis, String placa, String color, boolean adicional, String vehiculo, boolean habilitado, boolean renovar, boolean traspasasado, boolean seleccionado) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.marca = marca;
        this.chasis = chasis;
        this.placa = placa;
        this.color = color;
        this.adicional = adicional;
        this.vehiculo = vehiculo;
        this.habilitado = habilitado;
        this.renovar = renovar;
        this.traspasasado = traspasasado;
        this.seleccionado = seleccionado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleContratoDeServicio getEquipoGps() {
        return equipoGps;
    }

    public void setEquipoGps(DetalleContratoDeServicio equipoGps) {
        this.equipoGps = equipoGps;
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

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public boolean getAdicional() {
        return adicional;
    }

    public void setAdicional(boolean adicional) {
        this.adicional = adicional;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Date getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(Date fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public Date getFechaDesinstalacion() {
        return fechaDesinstalacion;
    }

    public void setFechaDesinstalacion(Date fechaDesinstalacion) {
        this.fechaDesinstalacion = fechaDesinstalacion;
    }

    public String getRazonDesinstalacion() {
        return razonDesinstalacion;
    }

    public void setRazonDesinstalacion(String razonDesinstalacion) {
        this.razonDesinstalacion = razonDesinstalacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getContrato() {
        return contrato;
    }

    public void setContrato(Integer contrato) {
        this.contrato = contrato;
    }

    public boolean getRenovar() {
        return renovar;
    }

    public void setRenovar(boolean renovar) {
        this.renovar = renovar;
    }

    public boolean getTraspasasado() {
        return traspasasado;
    }

    public void setTraspasasado(boolean traspasasado) {
        this.traspasasado = traspasasado;
    }

    public String getRazonActualizacion() {
        return razonActualizacion;
    }

    public void setRazonActualizacion(String razonActualizacion) {
        this.razonActualizacion = razonActualizacion;
    }

    public Date getFechaSuspendido() {
        return fechaSuspendido;
    }

    public void setFechaSuspendido(Date fechaSuspendido) {
        this.fechaSuspendido = fechaSuspendido;
    }

    public Date getFechaHabilitado() {
        return fechaHabilitado;
    }

    public void setFechaHabilitado(Date fechaHabilitado) {
        this.fechaHabilitado = fechaHabilitado;
    }

    public boolean getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public DetalleContratoDeServicio getServicio() {
        return servicio;
    }

    public void setServicio(DetalleContratoDeServicio servicio) {
        this.servicio = servicio;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
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
        if (!(object instanceof DatosDeVehiculo)) {
            return false;
        }
        DatosDeVehiculo other = (DatosDeVehiculo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DatosDeVehiculo[ codigo=" + codigo + " ]";
    }
    
}
