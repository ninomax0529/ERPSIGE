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
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
    , @NamedQuery(name = "Pedido.findByCodigo", query = "SELECT p FROM Pedido p WHERE p.codigo = :codigo")
    , @NamedQuery(name = "Pedido.findByFecha", query = "SELECT p FROM Pedido p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Pedido.findByCalle", query = "SELECT p FROM Pedido p WHERE p.calle = :calle")
    , @NamedQuery(name = "Pedido.findByCasa", query = "SELECT p FROM Pedido p WHERE p.casa = :casa")
    , @NamedQuery(name = "Pedido.findByAnulado", query = "SELECT p FROM Pedido p WHERE p.anulado = :anulado")
    , @NamedQuery(name = "Pedido.findByFechaAnulado", query = "SELECT p FROM Pedido p WHERE p.fechaAnulado = :fechaAnulado")
    , @NamedQuery(name = "Pedido.findByTipoVenta", query = "SELECT p FROM Pedido p WHERE p.tipoVenta = :tipoVenta")
    , @NamedQuery(name = "Pedido.findByTotal", query = "SELECT p FROM Pedido p WHERE p.total = :total")
    , @NamedQuery(name = "Pedido.findBySubTotal", query = "SELECT p FROM Pedido p WHERE p.subTotal = :subTotal")
    , @NamedQuery(name = "Pedido.findByFechaCreacion", query = "SELECT p FROM Pedido p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Pedido.findByHoraCreacion", query = "SELECT p FROM Pedido p WHERE p.horaCreacion = :horaCreacion")
    , @NamedQuery(name = "Pedido.findByHoraEmpacado", query = "SELECT p FROM Pedido p WHERE p.horaEmpacado = :horaEmpacado")
    , @NamedQuery(name = "Pedido.findByHoraEntragadoDelivery", query = "SELECT p FROM Pedido p WHERE p.horaEntragadoDelivery = :horaEntragadoDelivery")
    , @NamedQuery(name = "Pedido.findByHoraEntragadoCliente", query = "SELECT p FROM Pedido p WHERE p.horaEntragadoCliente = :horaEntragadoCliente")
    , @NamedQuery(name = "Pedido.findByNombreCliente", query = "SELECT p FROM Pedido p WHERE p.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "Pedido.findByNombreDelivery", query = "SELECT p FROM Pedido p WHERE p.nombreDelivery = :nombreDelivery")
    , @NamedQuery(name = "Pedido.findByDevueltaDe", query = "SELECT p FROM Pedido p WHERE p.devueltaDe = :devueltaDe")
    , @NamedQuery(name = "Pedido.findByValorDevuelta", query = "SELECT p FROM Pedido p WHERE p.valorDevuelta = :valorDevuelta")
    , @NamedQuery(name = "Pedido.findByNumeroDeTurno", query = "SELECT p FROM Pedido p WHERE p.numeroDeTurno = :numeroDeTurno")
    , @NamedQuery(name = "Pedido.findByNumero", query = "SELECT p FROM Pedido p WHERE p.numero = :numero")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "calle")
    private String calle;
    @Column(name = "casa")
    private String casa;
    @Basic(optional = false)
    @Column(name = "anulado")
    private boolean anulado;
    @Column(name = "fecha_anulado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulado;
    @Column(name = "tipo_venta")
    private Integer tipoVenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @Column(name = "sub_total")
    private Double subTotal;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "hora_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaCreacion;
    @Column(name = "hora_empacado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaEmpacado;
    @Column(name = "hora_entragado_delivery")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaEntragadoDelivery;
    @Column(name = "hora_entragado_cliente")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaEntragadoCliente;
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Column(name = "nombre_delivery")
    private String nombreDelivery;
    @Basic(optional = false)
    @Lob
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "devuelta_de")
    private Double devueltaDe;
    @Column(name = "valor_devuelta")
    private Double valorDevuelta;
    @Column(name = "numero_de_turno")
    private Integer numeroDeTurno;
    @Column(name = "numero")
    private Integer numero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Collection<DetallePedido> detallePedidoCollection;
    @OneToMany(mappedBy = "pedido")
    private Collection<FacturaTemporal> facturaTemporalCollection;
    @OneToMany(mappedBy = "pedido")
    private Collection<Conduce> conduceCollection;
    @OneToMany(mappedBy = "pedido")
    private Collection<Factura> facturaCollection;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "cliente", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "delivery", referencedColumnName = "codigo")
    @ManyToOne
    private Delivery delivery;
    @JoinColumn(name = "estado", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private EstadoPedido estado;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;

    public Pedido() {
    }

    public Pedido(Integer codigo) {
        this.codigo = codigo;
    }

    public Pedido(Integer codigo, boolean anulado, Date horaCreacion, String direccion) {
        this.codigo = codigo;
        this.anulado = anulado;
        this.horaCreacion = horaCreacion;
        this.direccion = direccion;
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

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
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

    public Integer getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(Integer tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(Date horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public Date getHoraEmpacado() {
        return horaEmpacado;
    }

    public void setHoraEmpacado(Date horaEmpacado) {
        this.horaEmpacado = horaEmpacado;
    }

    public Date getHoraEntragadoDelivery() {
        return horaEntragadoDelivery;
    }

    public void setHoraEntragadoDelivery(Date horaEntragadoDelivery) {
        this.horaEntragadoDelivery = horaEntragadoDelivery;
    }

    public Date getHoraEntragadoCliente() {
        return horaEntragadoCliente;
    }

    public void setHoraEntragadoCliente(Date horaEntragadoCliente) {
        this.horaEntragadoCliente = horaEntragadoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreDelivery() {
        return nombreDelivery;
    }

    public void setNombreDelivery(String nombreDelivery) {
        this.nombreDelivery = nombreDelivery;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getDevueltaDe() {
        return devueltaDe;
    }

    public void setDevueltaDe(Double devueltaDe) {
        this.devueltaDe = devueltaDe;
    }

    public Double getValorDevuelta() {
        return valorDevuelta;
    }

    public void setValorDevuelta(Double valorDevuelta) {
        this.valorDevuelta = valorDevuelta;
    }

    public Integer getNumeroDeTurno() {
        return numeroDeTurno;
    }

    public void setNumeroDeTurno(Integer numeroDeTurno) {
        this.numeroDeTurno = numeroDeTurno;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @XmlTransient
    public Collection<DetallePedido> getDetallePedidoCollection() {
        return detallePedidoCollection;
    }

    public void setDetallePedidoCollection(Collection<DetallePedido> detallePedidoCollection) {
        this.detallePedidoCollection = detallePedidoCollection;
    }

    @XmlTransient
    public Collection<FacturaTemporal> getFacturaTemporalCollection() {
        return facturaTemporalCollection;
    }

    public void setFacturaTemporalCollection(Collection<FacturaTemporal> facturaTemporalCollection) {
        this.facturaTemporalCollection = facturaTemporalCollection;
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

    public SecuenciaDocumento getSecuenciaDocumento() {
        return secuenciaDocumento;
    }

    public void setSecuenciaDocumento(SecuenciaDocumento secuenciaDocumento) {
        this.secuenciaDocumento = secuenciaDocumento;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Pedido[ codigo=" + codigo + " ]";
    }
    
}
