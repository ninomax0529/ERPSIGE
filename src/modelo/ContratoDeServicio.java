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
@Table(name = "contrato_de_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoDeServicio.findAll", query = "SELECT c FROM ContratoDeServicio c")
    , @NamedQuery(name = "ContratoDeServicio.findByCodigo", query = "SELECT c FROM ContratoDeServicio c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "ContratoDeServicio.findByFecha", query = "SELECT c FROM ContratoDeServicio c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "ContratoDeServicio.findByFechaVencimiento", query = "SELECT c FROM ContratoDeServicio c WHERE c.fechaVencimiento = :fechaVencimiento")
    , @NamedQuery(name = "ContratoDeServicio.findByNumeroDeContrato", query = "SELECT c FROM ContratoDeServicio c WHERE c.numeroDeContrato = :numeroDeContrato")
    , @NamedQuery(name = "ContratoDeServicio.findBySubTotal", query = "SELECT c FROM ContratoDeServicio c WHERE c.subTotal = :subTotal")
    , @NamedQuery(name = "ContratoDeServicio.findByItbis", query = "SELECT c FROM ContratoDeServicio c WHERE c.itbis = :itbis")
    , @NamedQuery(name = "ContratoDeServicio.findByTotalAPagar", query = "SELECT c FROM ContratoDeServicio c WHERE c.totalAPagar = :totalAPagar")
    , @NamedQuery(name = "ContratoDeServicio.findByCanalDeVenta", query = "SELECT c FROM ContratoDeServicio c WHERE c.canalDeVenta = :canalDeVenta")
    , @NamedQuery(name = "ContratoDeServicio.findByFechaDeActivacion", query = "SELECT c FROM ContratoDeServicio c WHERE c.fechaDeActivacion = :fechaDeActivacion")
    , @NamedQuery(name = "ContratoDeServicio.findByFechaRegistro", query = "SELECT c FROM ContratoDeServicio c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "ContratoDeServicio.findByAnulado", query = "SELECT c FROM ContratoDeServicio c WHERE c.anulado = :anulado")
    , @NamedQuery(name = "ContratoDeServicio.findByRepresentante", query = "SELECT c FROM ContratoDeServicio c WHERE c.representante = :representante")
    , @NamedQuery(name = "ContratoDeServicio.findByCedulaRepresentante", query = "SELECT c FROM ContratoDeServicio c WHERE c.cedulaRepresentante = :cedulaRepresentante")
    , @NamedQuery(name = "ContratoDeServicio.findByPosicionRepresentante", query = "SELECT c FROM ContratoDeServicio c WHERE c.posicionRepresentante = :posicionRepresentante")
    , @NamedQuery(name = "ContratoDeServicio.findByPersonaResponsable", query = "SELECT c FROM ContratoDeServicio c WHERE c.personaResponsable = :personaResponsable")
    , @NamedQuery(name = "ContratoDeServicio.findByCedulaOPasaporte", query = "SELECT c FROM ContratoDeServicio c WHERE c.cedulaOPasaporte = :cedulaOPasaporte")
    , @NamedQuery(name = "ContratoDeServicio.findByTelefonoPersona", query = "SELECT c FROM ContratoDeServicio c WHERE c.telefonoPersona = :telefonoPersona")
    , @NamedQuery(name = "ContratoDeServicio.findByCuentaDeMonitoreo", query = "SELECT c FROM ContratoDeServicio c WHERE c.cuentaDeMonitoreo = :cuentaDeMonitoreo")
    , @NamedQuery(name = "ContratoDeServicio.findByFechaAnulado", query = "SELECT c FROM ContratoDeServicio c WHERE c.fechaAnulado = :fechaAnulado")
    , @NamedQuery(name = "ContratoDeServicio.findByNombreCliente", query = "SELECT c FROM ContratoDeServicio c WHERE c.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "ContratoDeServicio.findByNumero", query = "SELECT c FROM ContratoDeServicio c WHERE c.numero = :numero")
    , @NamedQuery(name = "ContratoDeServicio.findByFacturado", query = "SELECT c FROM ContratoDeServicio c WHERE c.facturado = :facturado")
    , @NamedQuery(name = "ContratoDeServicio.findByAvisoDiasParaVencer", query = "SELECT c FROM ContratoDeServicio c WHERE c.avisoDiasParaVencer = :avisoDiasParaVencer")
    , @NamedQuery(name = "ContratoDeServicio.findByCuandiaAntesDeVencer", query = "SELECT c FROM ContratoDeServicio c WHERE c.cuandiaAntesDeVencer = :cuandiaAntesDeVencer")
    , @NamedQuery(name = "ContratoDeServicio.findByNuevo", query = "SELECT c FROM ContratoDeServicio c WHERE c.nuevo = :nuevo")
    , @NamedQuery(name = "ContratoDeServicio.findByCantidadMes", query = "SELECT c FROM ContratoDeServicio c WHERE c.cantidadMes = :cantidadMes")})
public class ContratoDeServicio implements Serializable {

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
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Basic(optional = false)
    @Column(name = "numero_de_contrato")
    private String numeroDeContrato;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sub_total")
    private Double subTotal;
    @Column(name = "itbis")
    private Double itbis;
    @Basic(optional = false)
    @Column(name = "total_a_pagar")
    private double totalAPagar;
    @Basic(optional = false)
    @Column(name = "canal_de_venta")
    private String canalDeVenta;
    @Column(name = "fecha_de_activacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeActivacion;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "anulado")
    private boolean anulado;
    @Lob
    @Column(name = "nota_estado")
    private String notaEstado;
    @Column(name = "representante")
    private String representante;
    @Column(name = "cedula_representante")
    private String cedulaRepresentante;
    @Column(name = "posicion_representante")
    private String posicionRepresentante;
    @Column(name = "persona_responsable")
    private String personaResponsable;
    @Column(name = "cedula_o_pasaporte")
    private String cedulaOPasaporte;
    @Column(name = "telefono_persona")
    private String telefonoPersona;
    @Column(name = "cuenta_de_monitoreo")
    private String cuentaDeMonitoreo;
    @Column(name = "fecha_anulado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulado;
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Column(name = "numero")
    private Integer numero;
    @Basic(optional = false)
    @Column(name = "facturado")
    private boolean facturado;
    @Basic(optional = false)
    @Column(name = "aviso_dias_para_vencer")
    private boolean avisoDiasParaVencer;
    @Column(name = "cuandia_antes_de_vencer")
    private Integer cuandiaAntesDeVencer;
    @Basic(optional = false)
    @Column(name = "nuevo")
    private boolean nuevo;
    @Basic(optional = false)
    @Column(name = "cantidad_mes")
    private int cantidadMes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratoDeServicio")
    private Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection;
    @JoinColumn(name = "cliente", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "ejecutivo_de_venta", referencedColumnName = "codigo")
    @ManyToOne
    private EjecutivoDeVenta ejecutivoDeVenta;
    @JoinColumn(name = "estado", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private EstadoContrato estado;
    @JoinColumn(name = "frecuencia_de_pago", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private FrecuenciaDePago frecuenciaDePago;
    @JoinColumn(name = "plan_de_servicio", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private PlanDeServicio planDeServicio;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contrato")
    private Collection<DetalleCorteDeFacturacion> detalleCorteDeFacturacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratoDeServicio")
    private Collection<CargoAutomaticoATarjeta> cargoAutomaticoATarjetaCollection;

    public ContratoDeServicio() {
    }

    public ContratoDeServicio(Integer codigo) {
        this.codigo = codigo;
    }

    public ContratoDeServicio(Integer codigo, Date fecha, String numeroDeContrato, double totalAPagar, String canalDeVenta, Date fechaRegistro, boolean anulado, boolean facturado, boolean avisoDiasParaVencer, boolean nuevo, int cantidadMes) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.numeroDeContrato = numeroDeContrato;
        this.totalAPagar = totalAPagar;
        this.canalDeVenta = canalDeVenta;
        this.fechaRegistro = fechaRegistro;
        this.anulado = anulado;
        this.facturado = facturado;
        this.avisoDiasParaVencer = avisoDiasParaVencer;
        this.nuevo = nuevo;
        this.cantidadMes = cantidadMes;
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

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNumeroDeContrato() {
        return numeroDeContrato;
    }

    public void setNumeroDeContrato(String numeroDeContrato) {
        this.numeroDeContrato = numeroDeContrato;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getItbis() {
        return itbis;
    }

    public void setItbis(Double itbis) {
        this.itbis = itbis;
    }

    public double getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public String getCanalDeVenta() {
        return canalDeVenta;
    }

    public void setCanalDeVenta(String canalDeVenta) {
        this.canalDeVenta = canalDeVenta;
    }

    public Date getFechaDeActivacion() {
        return fechaDeActivacion;
    }

    public void setFechaDeActivacion(Date fechaDeActivacion) {
        this.fechaDeActivacion = fechaDeActivacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public String getNotaEstado() {
        return notaEstado;
    }

    public void setNotaEstado(String notaEstado) {
        this.notaEstado = notaEstado;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getCedulaRepresentante() {
        return cedulaRepresentante;
    }

    public void setCedulaRepresentante(String cedulaRepresentante) {
        this.cedulaRepresentante = cedulaRepresentante;
    }

    public String getPosicionRepresentante() {
        return posicionRepresentante;
    }

    public void setPosicionRepresentante(String posicionRepresentante) {
        this.posicionRepresentante = posicionRepresentante;
    }

    public String getPersonaResponsable() {
        return personaResponsable;
    }

    public void setPersonaResponsable(String personaResponsable) {
        this.personaResponsable = personaResponsable;
    }

    public String getCedulaOPasaporte() {
        return cedulaOPasaporte;
    }

    public void setCedulaOPasaporte(String cedulaOPasaporte) {
        this.cedulaOPasaporte = cedulaOPasaporte;
    }

    public String getTelefonoPersona() {
        return telefonoPersona;
    }

    public void setTelefonoPersona(String telefonoPersona) {
        this.telefonoPersona = telefonoPersona;
    }

    public String getCuentaDeMonitoreo() {
        return cuentaDeMonitoreo;
    }

    public void setCuentaDeMonitoreo(String cuentaDeMonitoreo) {
        this.cuentaDeMonitoreo = cuentaDeMonitoreo;
    }

    public Date getFechaAnulado() {
        return fechaAnulado;
    }

    public void setFechaAnulado(Date fechaAnulado) {
        this.fechaAnulado = fechaAnulado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public boolean getFacturado() {
        return facturado;
    }

    public void setFacturado(boolean facturado) {
        this.facturado = facturado;
    }

    public boolean getAvisoDiasParaVencer() {
        return avisoDiasParaVencer;
    }

    public void setAvisoDiasParaVencer(boolean avisoDiasParaVencer) {
        this.avisoDiasParaVencer = avisoDiasParaVencer;
    }

    public Integer getCuandiaAntesDeVencer() {
        return cuandiaAntesDeVencer;
    }

    public void setCuandiaAntesDeVencer(Integer cuandiaAntesDeVencer) {
        this.cuandiaAntesDeVencer = cuandiaAntesDeVencer;
    }

    public boolean getNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public int getCantidadMes() {
        return cantidadMes;
    }

    public void setCantidadMes(int cantidadMes) {
        this.cantidadMes = cantidadMes;
    }

    @XmlTransient
    public Collection<DetalleContratoDeServicio> getDetalleContratoDeServicioCollection() {
        return detalleContratoDeServicioCollection;
    }

    public void setDetalleContratoDeServicioCollection(Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection) {
        this.detalleContratoDeServicioCollection = detalleContratoDeServicioCollection;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EjecutivoDeVenta getEjecutivoDeVenta() {
        return ejecutivoDeVenta;
    }

    public void setEjecutivoDeVenta(EjecutivoDeVenta ejecutivoDeVenta) {
        this.ejecutivoDeVenta = ejecutivoDeVenta;
    }

    public EstadoContrato getEstado() {
        return estado;
    }

    public void setEstado(EstadoContrato estado) {
        this.estado = estado;
    }

    public FrecuenciaDePago getFrecuenciaDePago() {
        return frecuenciaDePago;
    }

    public void setFrecuenciaDePago(FrecuenciaDePago frecuenciaDePago) {
        this.frecuenciaDePago = frecuenciaDePago;
    }

    public PlanDeServicio getPlanDeServicio() {
        return planDeServicio;
    }

    public void setPlanDeServicio(PlanDeServicio planDeServicio) {
        this.planDeServicio = planDeServicio;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public SecuenciaDocumento getSecuenciaDocumento() {
        return secuenciaDocumento;
    }

    public void setSecuenciaDocumento(SecuenciaDocumento secuenciaDocumento) {
        this.secuenciaDocumento = secuenciaDocumento;
    }

    @XmlTransient
    public Collection<DetalleCorteDeFacturacion> getDetalleCorteDeFacturacionCollection() {
        return detalleCorteDeFacturacionCollection;
    }

    public void setDetalleCorteDeFacturacionCollection(Collection<DetalleCorteDeFacturacion> detalleCorteDeFacturacionCollection) {
        this.detalleCorteDeFacturacionCollection = detalleCorteDeFacturacionCollection;
    }

    @XmlTransient
    public Collection<CargoAutomaticoATarjeta> getCargoAutomaticoATarjetaCollection() {
        return cargoAutomaticoATarjetaCollection;
    }

    public void setCargoAutomaticoATarjetaCollection(Collection<CargoAutomaticoATarjeta> cargoAutomaticoATarjetaCollection) {
        this.cargoAutomaticoATarjetaCollection = cargoAutomaticoATarjetaCollection;
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
        if (!(object instanceof ContratoDeServicio)) {
            return false;
        }
        ContratoDeServicio other = (ContratoDeServicio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ContratoDeServicio[ codigo=" + codigo + " ]";
    }
    
}
