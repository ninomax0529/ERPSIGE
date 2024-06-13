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
@Table(name = "cotizacion_de_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CotizacionDeVenta.findAll", query = "SELECT c FROM CotizacionDeVenta c")
    , @NamedQuery(name = "CotizacionDeVenta.findByCodigo", query = "SELECT c FROM CotizacionDeVenta c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "CotizacionDeVenta.findByFecha", query = "SELECT c FROM CotizacionDeVenta c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "CotizacionDeVenta.findByNombreCliente", query = "SELECT c FROM CotizacionDeVenta c WHERE c.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "CotizacionDeVenta.findByFechaVencimiento", query = "SELECT c FROM CotizacionDeVenta c WHERE c.fechaVencimiento = :fechaVencimiento")
    , @NamedQuery(name = "CotizacionDeVenta.findByMonto", query = "SELECT c FROM CotizacionDeVenta c WHERE c.monto = :monto")
    , @NamedQuery(name = "CotizacionDeVenta.findByItbis", query = "SELECT c FROM CotizacionDeVenta c WHERE c.itbis = :itbis")
    , @NamedQuery(name = "CotizacionDeVenta.findByDescuento", query = "SELECT c FROM CotizacionDeVenta c WHERE c.descuento = :descuento")
    , @NamedQuery(name = "CotizacionDeVenta.findByPorcientoDescuento", query = "SELECT c FROM CotizacionDeVenta c WHERE c.porcientoDescuento = :porcientoDescuento")
    , @NamedQuery(name = "CotizacionDeVenta.findByAnulada", query = "SELECT c FROM CotizacionDeVenta c WHERE c.anulada = :anulada")
    , @NamedQuery(name = "CotizacionDeVenta.findByFechaAnulada", query = "SELECT c FROM CotizacionDeVenta c WHERE c.fechaAnulada = :fechaAnulada")
    , @NamedQuery(name = "CotizacionDeVenta.findByTotal", query = "SELECT c FROM CotizacionDeVenta c WHERE c.total = :total")
    , @NamedQuery(name = "CotizacionDeVenta.findBySubTotal", query = "SELECT c FROM CotizacionDeVenta c WHERE c.subTotal = :subTotal")
    , @NamedQuery(name = "CotizacionDeVenta.findByFechaCreacion", query = "SELECT c FROM CotizacionDeVenta c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CotizacionDeVenta.findByNumero", query = "SELECT c FROM CotizacionDeVenta c WHERE c.numero = :numero")
    , @NamedQuery(name = "CotizacionDeVenta.findByFacturada", query = "SELECT c FROM CotizacionDeVenta c WHERE c.facturada = :facturada")
    , @NamedQuery(name = "CotizacionDeVenta.findByAutorizadorDescuento", query = "SELECT c FROM CotizacionDeVenta c WHERE c.autorizadorDescuento = :autorizadorDescuento")})
public class CotizacionDeVenta implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cotizacion")
    private Collection<DetalleCotizacionVehiculo> detalleCotizacionVehiculoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @Column(name = "itbis")
    private Double itbis;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "porciento_descuento")
    private Double porcientoDescuento;
    @Column(name = "anulada")
    private Boolean anulada;
    @Column(name = "fecha_anulada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulada;
    @Column(name = "total")
    private Double total;
    @Column(name = "sub_total")
    private Double subTotal;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "numero")
    private Integer numero;
    @Basic(optional = false)
    @Column(name = "facturada")
    private boolean facturada;
    @Column(name = "autorizador_descuento")
    private Integer autorizadorDescuento;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "cliente", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "vendedor", referencedColumnName = "codigo")
    @ManyToOne
    private EjecutivoDeVenta vendedor;
    @OneToMany(mappedBy = "cotizacionDeVenta")
    private Collection<FacturaTemporal> facturaTemporalCollection;
    @OneToMany(mappedBy = "cotizacionDeVenta")
    private Collection<Factura> facturaCollection;
    @OneToMany(mappedBy = "cotizacionDeVenta",cascade = CascadeType.ALL)
    private Collection<DetalleCotizacionDeVenta> detalleCotizacionDeVentaCollection;

    public CotizacionDeVenta() {
    }

    public CotizacionDeVenta(Integer codigo) {
        this.codigo = codigo;
    }

    public CotizacionDeVenta(Integer codigo, boolean facturada) {
        this.codigo = codigo;
        this.facturada = facturada;
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getItbis() {
        return itbis;
    }

    public void setItbis(Double itbis) {
        this.itbis = itbis;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getPorcientoDescuento() {
        return porcientoDescuento;
    }

    public void setPorcientoDescuento(Double porcientoDescuento) {
        this.porcientoDescuento = porcientoDescuento;
    }

    public Boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(Boolean anulada) {
        this.anulada = anulada;
    }

    public Date getFechaAnulada() {
        return fechaAnulada;
    }

    public void setFechaAnulada(Date fechaAnulada) {
        this.fechaAnulada = fechaAnulada;
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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public boolean getFacturada() {
        return facturada;
    }

    public void setFacturada(boolean facturada) {
        this.facturada = facturada;
    }

    public Integer getAutorizadorDescuento() {
        return autorizadorDescuento;
    }

    public void setAutorizadorDescuento(Integer autorizadorDescuento) {
        this.autorizadorDescuento = autorizadorDescuento;
    }

    public SecuenciaDocumento getSecuenciaDocumento() {
        return secuenciaDocumento;
    }

    public void setSecuenciaDocumento(SecuenciaDocumento secuenciaDocumento) {
        this.secuenciaDocumento = secuenciaDocumento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public EjecutivoDeVenta getVendedor() {
        return vendedor;
    }

    public void setVendedor(EjecutivoDeVenta vendedor) {
        this.vendedor = vendedor;
    }

    @XmlTransient
    public Collection<FacturaTemporal> getFacturaTemporalCollection() {
        return facturaTemporalCollection;
    }

    public void setFacturaTemporalCollection(Collection<FacturaTemporal> facturaTemporalCollection) {
        this.facturaTemporalCollection = facturaTemporalCollection;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    @XmlTransient
    public Collection<DetalleCotizacionDeVenta> getDetalleCotizacionDeVentaCollection() {
        return detalleCotizacionDeVentaCollection;
    }

    public void setDetalleCotizacionDeVentaCollection(Collection<DetalleCotizacionDeVenta> detalleCotizacionDeVentaCollection) {
        this.detalleCotizacionDeVentaCollection = detalleCotizacionDeVentaCollection;
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
        if (!(object instanceof CotizacionDeVenta)) {
            return false;
        }
        CotizacionDeVenta other = (CotizacionDeVenta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CotizacionDeVenta[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<DetalleCotizacionVehiculo> getDetalleCotizacionVehiculoCollection() {
        return detalleCotizacionVehiculoCollection;
    }

    public void setDetalleCotizacionVehiculoCollection(Collection<DetalleCotizacionVehiculo> detalleCotizacionVehiculoCollection) {
        this.detalleCotizacionVehiculoCollection = detalleCotizacionVehiculoCollection;
    }
    
}
