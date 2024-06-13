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
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByCodigo", query = "SELECT c FROM Cliente c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Cliente.findByRnc", query = "SELECT c FROM Cliente c WHERE c.rnc = :rnc")
    , @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email")
    , @NamedQuery(name = "Cliente.findByContacto", query = "SELECT c FROM Cliente c WHERE c.contacto = :contacto")
    , @NamedQuery(name = "Cliente.findByCelular", query = "SELECT c FROM Cliente c WHERE c.celular = :celular")
    , @NamedQuery(name = "Cliente.findByPuntoAcomulados", query = "SELECT c FROM Cliente c WHERE c.puntoAcomulados = :puntoAcomulados")
    , @NamedQuery(name = "Cliente.findByMiembro", query = "SELECT c FROM Cliente c WHERE c.miembro = :miembro")
    , @NamedQuery(name = "Cliente.findByDiasCredito", query = "SELECT c FROM Cliente c WHERE c.diasCredito = :diasCredito")
    , @NamedQuery(name = "Cliente.findByMontoCredito", query = "SELECT c FROM Cliente c WHERE c.montoCredito = :montoCredito")
    , @NamedQuery(name = "Cliente.findByApellido", query = "SELECT c FROM Cliente c WHERE c.apellido = :apellido")
    , @NamedQuery(name = "Cliente.findByFechaCumpleano", query = "SELECT c FROM Cliente c WHERE c.fechaCumpleano = :fechaCumpleano")
    , @NamedQuery(name = "Cliente.findByMontoDisponible", query = "SELECT c FROM Cliente c WHERE c.montoDisponible = :montoDisponible")
    , @NamedQuery(name = "Cliente.findByFecha", query = "SELECT c FROM Cliente c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "Cliente.findByPrecio", query = "SELECT c FROM Cliente c WHERE c.precio = :precio")
    , @NamedQuery(name = "Cliente.findByCreditoBloquiado", query = "SELECT c FROM Cliente c WHERE c.creditoBloquiado = :creditoBloquiado")
    , @NamedQuery(name = "Cliente.findByPersonaResponsable", query = "SELECT c FROM Cliente c WHERE c.personaResponsable = :personaResponsable")
    , @NamedQuery(name = "Cliente.findByTelefonoPersonaResponsable", query = "SELECT c FROM Cliente c WHERE c.telefonoPersonaResponsable = :telefonoPersonaResponsable")
    , @NamedQuery(name = "Cliente.findByCedulaPasaportePersonaResponsable", query = "SELECT c FROM Cliente c WHERE c.cedulaPasaportePersonaResponsable = :cedulaPasaportePersonaResponsable")
    , @NamedQuery(name = "Cliente.findByCiudad", query = "SELECT c FROM Cliente c WHERE c.ciudad = :ciudad")
    , @NamedQuery(name = "Cliente.findByNombreCiudad", query = "SELECT c FROM Cliente c WHERE c.nombreCiudad = :nombreCiudad")})
public class Cliente implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<ReconciliacionInternaCliente> reconciliacionInternaClienteCollection;

    @JoinColumn(name = "vendedor", referencedColumnName = "codigo")
    @ManyToOne
    private EjecutivoDeVenta vendedor;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "rnc")
    private String rnc;
    @Lob
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email")
    private String email;
    @Column(name = "contacto")
    private String contacto;
    @Column(name = "celular")
    private String celular;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "punto_acomulados")
    private Double puntoAcomulados;
    @Column(name = "miembro")
    private Boolean miembro;
    @Column(name = "dias_credito")
    private Integer diasCredito;
    @Column(name = "monto_credito")
    private Double montoCredito;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fecha_cumpleano")
    @Temporal(TemporalType.DATE)
    private Date fechaCumpleano;
    @Column(name = "monto_disponible")
    private Double montoDisponible;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "precio")
    private Integer precio;
    @Basic(optional = false)
    @Column(name = "credito_bloquiado")
    private boolean creditoBloquiado;
    @Column(name = "persona_responsable")
    private String personaResponsable;
    @Column(name = "telefono_persona_responsable")
    private String telefonoPersonaResponsable;
    @Column(name = "cedula_pasaporte_persona_responsable")
    private String cedulaPasaportePersonaResponsable;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "ciudad")
    private Integer ciudad;
    @Column(name = "nombre_ciudad")
    private String nombreCiudad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<CotizacionDeVenta> cotizacionDeVentaCollection;
    @JoinColumn(name = "estado_cliente", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private EstadoCliente estadoCliente;
    @JoinColumn(name = "tipo_cliente", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoCliente tipoCliente;
    @JoinColumn(name = "tipo_ncf", referencedColumnName = "codigo")
    @ManyToOne
    private TipoNcf tipoNcf;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<FacturaTemporal> facturaTemporalCollection;
    @OneToMany(mappedBy = "cliente")
    private Collection<ReciboIngreso> reciboIngresoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<CreditoCliente> creditoClienteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<DireccionCliente> direccionClienteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<GestionDeCobro> gestionDeCobroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<AvanceDeCliente> avanceDeClienteCollection;
    @OneToMany(mappedBy = "cliente")
    private Collection<AsistenciaTecnica> asistenciaTecnicaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<ContratoDeServicio> contratoDeServicioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<Conduce> conduceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<Factura> facturaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<Pedido> pedidoCollection;

    public Cliente() {
    }

    public Cliente(Integer codigo) {
        this.codigo = codigo;
    }

    public Cliente(Integer codigo, boolean creditoBloquiado) {
        this.codigo = codigo;
        this.creditoBloquiado = creditoBloquiado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Double getPuntoAcomulados() {
        return puntoAcomulados;
    }

    public void setPuntoAcomulados(Double puntoAcomulados) {
        this.puntoAcomulados = puntoAcomulados;
    }

    public Boolean getMiembro() {
        return miembro;
    }

    public void setMiembro(Boolean miembro) {
        this.miembro = miembro;
    }

    public Integer getDiasCredito() {
        return diasCredito;
    }

    public void setDiasCredito(Integer diasCredito) {
        this.diasCredito = diasCredito;
    }

    public Double getMontoCredito() {
        return montoCredito;
    }

    public void setMontoCredito(Double montoCredito) {
        this.montoCredito = montoCredito;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaCumpleano() {
        return fechaCumpleano;
    }

    public void setFechaCumpleano(Date fechaCumpleano) {
        this.fechaCumpleano = fechaCumpleano;
    }

    public Double getMontoDisponible() {
        return montoDisponible;
    }

    public void setMontoDisponible(Double montoDisponible) {
        this.montoDisponible = montoDisponible;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public boolean getCreditoBloquiado() {
        return creditoBloquiado;
    }

    public void setCreditoBloquiado(boolean creditoBloquiado) {
        this.creditoBloquiado = creditoBloquiado;
    }

    public String getPersonaResponsable() {
        return personaResponsable;
    }

    public void setPersonaResponsable(String personaResponsable) {
        this.personaResponsable = personaResponsable;
    }

    public String getTelefonoPersonaResponsable() {
        return telefonoPersonaResponsable;
    }

    public void setTelefonoPersonaResponsable(String telefonoPersonaResponsable) {
        this.telefonoPersonaResponsable = telefonoPersonaResponsable;
    }

    public String getCedulaPasaportePersonaResponsable() {
        return cedulaPasaportePersonaResponsable;
    }

    public void setCedulaPasaportePersonaResponsable(String cedulaPasaportePersonaResponsable) {
        this.cedulaPasaportePersonaResponsable = cedulaPasaportePersonaResponsable;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getCiudad() {
        return ciudad;
    }

    public void setCiudad(Integer ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    @XmlTransient
    public Collection<CotizacionDeVenta> getCotizacionDeVentaCollection() {
        return cotizacionDeVentaCollection;
    }

    public void setCotizacionDeVentaCollection(Collection<CotizacionDeVenta> cotizacionDeVentaCollection) {
        this.cotizacionDeVentaCollection = cotizacionDeVentaCollection;
    }

    public EstadoCliente getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(EstadoCliente estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public TipoNcf getTipoNcf() {
        return tipoNcf;
    }

    public void setTipoNcf(TipoNcf tipoNcf) {
        this.tipoNcf = tipoNcf;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    @XmlTransient
    public Collection<FacturaTemporal> getFacturaTemporalCollection() {
        return facturaTemporalCollection;
    }

    public void setFacturaTemporalCollection(Collection<FacturaTemporal> facturaTemporalCollection) {
        this.facturaTemporalCollection = facturaTemporalCollection;
    }

    @XmlTransient
    public Collection<ReciboIngreso> getReciboIngresoCollection() {
        return reciboIngresoCollection;
    }

    public void setReciboIngresoCollection(Collection<ReciboIngreso> reciboIngresoCollection) {
        this.reciboIngresoCollection = reciboIngresoCollection;
    }

    @XmlTransient
    public Collection<CreditoCliente> getCreditoClienteCollection() {
        return creditoClienteCollection;
    }

    public void setCreditoClienteCollection(Collection<CreditoCliente> creditoClienteCollection) {
        this.creditoClienteCollection = creditoClienteCollection;
    }

    @XmlTransient
    public Collection<DireccionCliente> getDireccionClienteCollection() {
        return direccionClienteCollection;
    }

    public void setDireccionClienteCollection(Collection<DireccionCliente> direccionClienteCollection) {
        this.direccionClienteCollection = direccionClienteCollection;
    }

    @XmlTransient
    public Collection<GestionDeCobro> getGestionDeCobroCollection() {
        return gestionDeCobroCollection;
    }

    public void setGestionDeCobroCollection(Collection<GestionDeCobro> gestionDeCobroCollection) {
        this.gestionDeCobroCollection = gestionDeCobroCollection;
    }

    @XmlTransient
    public Collection<AvanceDeCliente> getAvanceDeClienteCollection() {
        return avanceDeClienteCollection;
    }

    public void setAvanceDeClienteCollection(Collection<AvanceDeCliente> avanceDeClienteCollection) {
        this.avanceDeClienteCollection = avanceDeClienteCollection;
    }

    @XmlTransient
    public Collection<AsistenciaTecnica> getAsistenciaTecnicaCollection() {
        return asistenciaTecnicaCollection;
    }

    public void setAsistenciaTecnicaCollection(Collection<AsistenciaTecnica> asistenciaTecnicaCollection) {
        this.asistenciaTecnicaCollection = asistenciaTecnicaCollection;
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

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
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
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cliente[ codigo=" + codigo + " ]";
    }

    public EjecutivoDeVenta getVendedor() {
        return vendedor;
    }

    public void setVendedor(EjecutivoDeVenta vendedor) {
        this.vendedor = vendedor;
    }

    @XmlTransient
    public Collection<ReconciliacionInternaCliente> getReconciliacionInternaClienteCollection() {
        return reconciliacionInternaClienteCollection;
    }

    public void setReconciliacionInternaClienteCollection(Collection<ReconciliacionInternaCliente> reconciliacionInternaClienteCollection) {
        this.reconciliacionInternaClienteCollection = reconciliacionInternaClienteCollection;
    }
    
}
