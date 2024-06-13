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
@Table(name = "secuencia_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SecuenciaDocumento.findAll", query = "SELECT s FROM SecuenciaDocumento s")
    , @NamedQuery(name = "SecuenciaDocumento.findByCodigo", query = "SELECT s FROM SecuenciaDocumento s WHERE s.codigo = :codigo")
    , @NamedQuery(name = "SecuenciaDocumento.findByNumero", query = "SELECT s FROM SecuenciaDocumento s WHERE s.numero = :numero")
    , @NamedQuery(name = "SecuenciaDocumento.findByPrefijo", query = "SELECT s FROM SecuenciaDocumento s WHERE s.prefijo = :prefijo")
    , @NamedQuery(name = "SecuenciaDocumento.findByUsuario", query = "SELECT s FROM SecuenciaDocumento s WHERE s.usuario = :usuario")
    , @NamedQuery(name = "SecuenciaDocumento.findBySufijo", query = "SELECT s FROM SecuenciaDocumento s WHERE s.sufijo = :sufijo")
    , @NamedQuery(name = "SecuenciaDocumento.findByFechaRegistro", query = "SELECT s FROM SecuenciaDocumento s WHERE s.fechaRegistro = :fechaRegistro")})
public class SecuenciaDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    @Column(name = "prefijo")
    private String prefijo;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Column(name = "sufijo")
    private String sufijo;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<EntradaDiario> entradaDiarioCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<Articulo> articuloCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<TransferenciaAlmacen> transferenciaAlmacenCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<NotaCreditoSuplidor> notaCreditoSuplidorCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<CotizacionDeVenta> cotizacionDeVentaCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<FacturaSuplidor> facturaSuplidorCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<SolicitudCheque> solicitudChequeCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<EntradaInventario> entradaInventarioCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<TransferenciaBanco> transferenciaBancoCollection;
    @JoinColumn(name = "tipo_documento", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoDocumento tipoDocumento;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private UnidadDeNegocio unidadDeNegocio;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<FacturaTemporal> facturaTemporalCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<OrdenCompra> ordenCompraCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<ReciboIngreso> reciboIngresoCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<NotaDebito> notaDebitoCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<ContratoDeServicio> contratoDeServicioCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<ComprobanteDePago> comprobanteDePagoCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<Factura> facturaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "secuenciaDocumento")
    private Collection<Nomina> nominaCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<Pedido> pedidoCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<SolicitudSalidaInventario> solicitudSalidaInventarioCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<NotaCredito> notaCreditoCollection;
    @OneToMany(mappedBy = "secuenciaDocumento")
    private Collection<SalidaInventario> salidaInventarioCollection;

    public SecuenciaDocumento() {
    }

    public SecuenciaDocumento(Integer codigo) {
        this.codigo = codigo;
    }

    public SecuenciaDocumento(Integer codigo, int numero, int usuario, Date fechaRegistro) {
        this.codigo = codigo;
        this.numero = numero;
        this.usuario = usuario;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getSufijo() {
        return sufijo;
    }

    public void setSufijo(String sufijo) {
        this.sufijo = sufijo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @XmlTransient
    public Collection<EntradaDiario> getEntradaDiarioCollection() {
        return entradaDiarioCollection;
    }

    public void setEntradaDiarioCollection(Collection<EntradaDiario> entradaDiarioCollection) {
        this.entradaDiarioCollection = entradaDiarioCollection;
    }

    @XmlTransient
    public Collection<Articulo> getArticuloCollection() {
        return articuloCollection;
    }

    public void setArticuloCollection(Collection<Articulo> articuloCollection) {
        this.articuloCollection = articuloCollection;
    }

    @XmlTransient
    public Collection<TransferenciaAlmacen> getTransferenciaAlmacenCollection() {
        return transferenciaAlmacenCollection;
    }

    public void setTransferenciaAlmacenCollection(Collection<TransferenciaAlmacen> transferenciaAlmacenCollection) {
        this.transferenciaAlmacenCollection = transferenciaAlmacenCollection;
    }

    @XmlTransient
    public Collection<NotaCreditoSuplidor> getNotaCreditoSuplidorCollection() {
        return notaCreditoSuplidorCollection;
    }

    public void setNotaCreditoSuplidorCollection(Collection<NotaCreditoSuplidor> notaCreditoSuplidorCollection) {
        this.notaCreditoSuplidorCollection = notaCreditoSuplidorCollection;
    }

    @XmlTransient
    public Collection<CotizacionDeVenta> getCotizacionDeVentaCollection() {
        return cotizacionDeVentaCollection;
    }

    public void setCotizacionDeVentaCollection(Collection<CotizacionDeVenta> cotizacionDeVentaCollection) {
        this.cotizacionDeVentaCollection = cotizacionDeVentaCollection;
    }

    @XmlTransient
    public Collection<FacturaSuplidor> getFacturaSuplidorCollection() {
        return facturaSuplidorCollection;
    }

    public void setFacturaSuplidorCollection(Collection<FacturaSuplidor> facturaSuplidorCollection) {
        this.facturaSuplidorCollection = facturaSuplidorCollection;
    }

    @XmlTransient
    public Collection<SolicitudCheque> getSolicitudChequeCollection() {
        return solicitudChequeCollection;
    }

    public void setSolicitudChequeCollection(Collection<SolicitudCheque> solicitudChequeCollection) {
        this.solicitudChequeCollection = solicitudChequeCollection;
    }

    @XmlTransient
    public Collection<EntradaInventario> getEntradaInventarioCollection() {
        return entradaInventarioCollection;
    }

    public void setEntradaInventarioCollection(Collection<EntradaInventario> entradaInventarioCollection) {
        this.entradaInventarioCollection = entradaInventarioCollection;
    }

    @XmlTransient
    public Collection<TransferenciaBanco> getTransferenciaBancoCollection() {
        return transferenciaBancoCollection;
    }

    public void setTransferenciaBancoCollection(Collection<TransferenciaBanco> transferenciaBancoCollection) {
        this.transferenciaBancoCollection = transferenciaBancoCollection;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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
    public Collection<OrdenCompra> getOrdenCompraCollection() {
        return ordenCompraCollection;
    }

    public void setOrdenCompraCollection(Collection<OrdenCompra> ordenCompraCollection) {
        this.ordenCompraCollection = ordenCompraCollection;
    }

    @XmlTransient
    public Collection<ReciboIngreso> getReciboIngresoCollection() {
        return reciboIngresoCollection;
    }

    public void setReciboIngresoCollection(Collection<ReciboIngreso> reciboIngresoCollection) {
        this.reciboIngresoCollection = reciboIngresoCollection;
    }

    @XmlTransient
    public Collection<NotaDebito> getNotaDebitoCollection() {
        return notaDebitoCollection;
    }

    public void setNotaDebitoCollection(Collection<NotaDebito> notaDebitoCollection) {
        this.notaDebitoCollection = notaDebitoCollection;
    }

    @XmlTransient
    public Collection<ContratoDeServicio> getContratoDeServicioCollection() {
        return contratoDeServicioCollection;
    }

    public void setContratoDeServicioCollection(Collection<ContratoDeServicio> contratoDeServicioCollection) {
        this.contratoDeServicioCollection = contratoDeServicioCollection;
    }

    @XmlTransient
    public Collection<ComprobanteDePago> getComprobanteDePagoCollection() {
        return comprobanteDePagoCollection;
    }

    public void setComprobanteDePagoCollection(Collection<ComprobanteDePago> comprobanteDePagoCollection) {
        this.comprobanteDePagoCollection = comprobanteDePagoCollection;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    @XmlTransient
    public Collection<Nomina> getNominaCollection() {
        return nominaCollection;
    }

    public void setNominaCollection(Collection<Nomina> nominaCollection) {
        this.nominaCollection = nominaCollection;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    @XmlTransient
    public Collection<SolicitudSalidaInventario> getSolicitudSalidaInventarioCollection() {
        return solicitudSalidaInventarioCollection;
    }

    public void setSolicitudSalidaInventarioCollection(Collection<SolicitudSalidaInventario> solicitudSalidaInventarioCollection) {
        this.solicitudSalidaInventarioCollection = solicitudSalidaInventarioCollection;
    }

    @XmlTransient
    public Collection<NotaCredito> getNotaCreditoCollection() {
        return notaCreditoCollection;
    }

    public void setNotaCreditoCollection(Collection<NotaCredito> notaCreditoCollection) {
        this.notaCreditoCollection = notaCreditoCollection;
    }

    @XmlTransient
    public Collection<SalidaInventario> getSalidaInventarioCollection() {
        return salidaInventarioCollection;
    }

    public void setSalidaInventarioCollection(Collection<SalidaInventario> salidaInventarioCollection) {
        this.salidaInventarioCollection = salidaInventarioCollection;
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
        if (!(object instanceof SecuenciaDocumento)) {
            return false;
        }
        SecuenciaDocumento other = (SecuenciaDocumento) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.SecuenciaDocumento[ codigo=" + codigo + " ]";
    }
    
}
