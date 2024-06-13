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
@Table(name = "ejecutivo_de_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EjecutivoDeVenta.findAll", query = "SELECT e FROM EjecutivoDeVenta e")
    , @NamedQuery(name = "EjecutivoDeVenta.findByCodigo", query = "SELECT e FROM EjecutivoDeVenta e WHERE e.codigo = :codigo")
    , @NamedQuery(name = "EjecutivoDeVenta.findByNombre", query = "SELECT e FROM EjecutivoDeVenta e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "EjecutivoDeVenta.findByApelldido", query = "SELECT e FROM EjecutivoDeVenta e WHERE e.apelldido = :apelldido")
    , @NamedQuery(name = "EjecutivoDeVenta.findByActivo", query = "SELECT e FROM EjecutivoDeVenta e WHERE e.activo = :activo")
    , @NamedQuery(name = "EjecutivoDeVenta.findByFecha", query = "SELECT e FROM EjecutivoDeVenta e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "EjecutivoDeVenta.findByCelular", query = "SELECT e FROM EjecutivoDeVenta e WHERE e.celular = :celular")
    , @NamedQuery(name = "EjecutivoDeVenta.findByTelefono", query = "SELECT e FROM EjecutivoDeVenta e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "EjecutivoDeVenta.findByDireccion", query = "SELECT e FROM EjecutivoDeVenta e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "EjecutivoDeVenta.findByFechaCumplea\u00f1o", query = "SELECT e FROM EjecutivoDeVenta e WHERE e.fechaCumplea\u00f1o = :fechaCumplea\u00f1o")
    , @NamedQuery(name = "EjecutivoDeVenta.findByCedula", query = "SELECT e FROM EjecutivoDeVenta e WHERE e.cedula = :cedula")
    , @NamedQuery(name = "EjecutivoDeVenta.findByCorreo", query = "SELECT e FROM EjecutivoDeVenta e WHERE e.correo = :correo")
    , @NamedQuery(name = "EjecutivoDeVenta.findByPorcientoComision", query = "SELECT e FROM EjecutivoDeVenta e WHERE e.porcientoComision = :porcientoComision")})
public class EjecutivoDeVenta implements Serializable {

    @Basic(optional = false)
    @Column(name = "suplidor")
    private boolean suplidor;

    @Column(name = "comision_por_venta_compatibilidad")
    private Double comisionPorVentaCompatibilidad;

    @OneToMany(mappedBy = "vendedor")
    private Collection<Cliente> clienteCollection;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "comision_por_venta")
    private Double comisionPorVenta;
    @Column(name = "comision_por_cobros")
    private Double comisionPorCobros;

    @Basic(optional = false)
    @Column(name = "porciento_comision")
    private double porcientoComision;

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
    @Column(name = "apelldido")
    private String apelldido;
    @Basic(optional = false)
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "celular")
    private String celular;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "fecha_cumplea\u00f1o")
    @Temporal(TemporalType.DATE)
    private Date fechaCumpleaño;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "cedula")
    private String cedula;
    @Column(name = "correo")
    private String correo;
    @OneToMany(mappedBy = "ejecutivoDeVenta")
    private Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection;
    @OneToMany(mappedBy = "vendedor")
    private Collection<CotizacionDeVenta> cotizacionDeVentaCollection;
    @JoinColumn(name = "cargo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Cargo cargo;
    @JoinColumn(name = "tipo_ejecutivo_venta", referencedColumnName = "codigo")
    @ManyToOne
    private TipoVendedor tipoEjecutivoVenta;
    @OneToMany(mappedBy = "vendedor")
    private Collection<FacturaTemporal> facturaTemporalCollection;
    @OneToMany(mappedBy = "ejecutivoDeVenta")
    private Collection<ContratoDeServicio> contratoDeServicioCollection;
    @OneToMany(mappedBy = "vendedor")
    private Collection<Conduce> conduceCollection;
    @OneToMany(mappedBy = "vendedor")
    private Collection<Factura> facturaCollection;

    public EjecutivoDeVenta() {
    }

    public EjecutivoDeVenta(Integer codigo) {
        this.codigo = codigo;
    }

    public EjecutivoDeVenta(Integer codigo, String nombre, String apelldido, boolean activo, Date fecha, String celular, double porcientoComision) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apelldido = apelldido;
        this.activo = activo;
        this.fecha = fecha;
        this.celular = celular;
        this.porcientoComision = porcientoComision;
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

    public String getApelldido() {
        return apelldido;
    }

    public void setApelldido(String apelldido) {
        this.apelldido = apelldido;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaCumpleaño() {
        return fechaCumpleaño;
    }

    public void setFechaCumpleaño(Date fechaCumpleaño) {
        this.fechaCumpleaño = fechaCumpleaño;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public Double getPorcientoComision() {
        return porcientoComision;
    }

    public void setPorcientoComision(Double porcientoComision) {
        this.porcientoComision = porcientoComision;
    }

    @XmlTransient
    public Collection<DetalleContratoDeServicio> getDetalleContratoDeServicioCollection() {
        return detalleContratoDeServicioCollection;
    }

    public void setDetalleContratoDeServicioCollection(Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection) {
        this.detalleContratoDeServicioCollection = detalleContratoDeServicioCollection;
    }

    @XmlTransient
    public Collection<CotizacionDeVenta> getCotizacionDeVentaCollection() {
        return cotizacionDeVentaCollection;
    }

    public void setCotizacionDeVentaCollection(Collection<CotizacionDeVenta> cotizacionDeVentaCollection) {
        this.cotizacionDeVentaCollection = cotizacionDeVentaCollection;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public TipoVendedor getTipoEjecutivoVenta() {
        return tipoEjecutivoVenta;
    }

    public void setTipoEjecutivoVenta(TipoVendedor tipoEjecutivoVenta) {
        this.tipoEjecutivoVenta = tipoEjecutivoVenta;
    }

    @XmlTransient
    public Collection<FacturaTemporal> getFacturaTemporalCollection() {
        return facturaTemporalCollection;
    }

    public void setFacturaTemporalCollection(Collection<FacturaTemporal> facturaTemporalCollection) {
        this.facturaTemporalCollection = facturaTemporalCollection;
    }

    @XmlTransient
    public Collection<ContratoDeServicio> getContratoDeServicioCollection() {
        return contratoDeServicioCollection;
    }

    public void setContratoDeServicioCollection(Collection<ContratoDeServicio> contratoDeServicioCollection) {
        this.contratoDeServicioCollection = contratoDeServicioCollection;
    }

    @XmlTransient
    public Collection<Conduce> getConduceCollection() {
        return conduceCollection;
    }

    public void setConduceCollection(Collection<Conduce> conduceCollection) {
        this.conduceCollection = conduceCollection;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
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
        if (!(object instanceof EjecutivoDeVenta)) {
            return false;
        }
        EjecutivoDeVenta other = (EjecutivoDeVenta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.EjecutivoDeVenta[ codigo=" + codigo + " ]";
    }

    public void setPorcientoComision(double porcientoComision) {
        this.porcientoComision = porcientoComision;
    }

    public Double getComisionPorVenta() {
        return comisionPorVenta;
    }

    public void setComisionPorVenta(Double comisionPorVenta) {
        this.comisionPorVenta = comisionPorVenta;
    }

    public Double getComisionPorCobros() {
        return comisionPorCobros;
    }

    public void setComisionPorCobros(Double comisionPorCobros) {
        this.comisionPorCobros = comisionPorCobros;
    }

    public Double getComisionPorVentaCompatibilidad() {
        return comisionPorVentaCompatibilidad;
    }

    public void setComisionPorVentaCompatibilidad(Double comisionPorVentaCompatibilidad) {
        this.comisionPorVentaCompatibilidad = comisionPorVentaCompatibilidad;
    }

    public boolean getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(boolean suplidor) {
        this.suplidor = suplidor;
    }

}
