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
@Table(name = "unidad_de_negocio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadDeNegocio.findAll", query = "SELECT u FROM UnidadDeNegocio u")
    , @NamedQuery(name = "UnidadDeNegocio.findByCodigo", query = "SELECT u FROM UnidadDeNegocio u WHERE u.codigo = :codigo")
    , @NamedQuery(name = "UnidadDeNegocio.findByNombre", query = "SELECT u FROM UnidadDeNegocio u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "UnidadDeNegocio.findByNombreUsuario", query = "SELECT u FROM UnidadDeNegocio u WHERE u.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "UnidadDeNegocio.findByFechaRegistro", query = "SELECT u FROM UnidadDeNegocio u WHERE u.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "UnidadDeNegocio.findByTelefono", query = "SELECT u FROM UnidadDeNegocio u WHERE u.telefono = :telefono")
    , @NamedQuery(name = "UnidadDeNegocio.findByEmail", query = "SELECT u FROM UnidadDeNegocio u WHERE u.email = :email")
    , @NamedQuery(name = "UnidadDeNegocio.findBySucursal", query = "SELECT u FROM UnidadDeNegocio u WHERE u.sucursal = :sucursal")
    , @NamedQuery(name = "UnidadDeNegocio.findByPrincipal", query = "SELECT u FROM UnidadDeNegocio u WHERE u.principal = :principal")
    , @NamedQuery(name = "UnidadDeNegocio.findByNombrePrincipal", query = "SELECT u FROM UnidadDeNegocio u WHERE u.nombrePrincipal = :nombrePrincipal")
    , @NamedQuery(name = "UnidadDeNegocio.findByDiaDeFacturacion", query = "SELECT u FROM UnidadDeNegocio u WHERE u.diaDeFacturacion = :diaDeFacturacion")
    , @NamedQuery(name = "UnidadDeNegocio.findByDiaAntesDeVencer", query = "SELECT u FROM UnidadDeNegocio u WHERE u.diaAntesDeVencer = :diaAntesDeVencer")
    , @NamedQuery(name = "UnidadDeNegocio.findByRnc", query = "SELECT u FROM UnidadDeNegocio u WHERE u.rnc = :rnc")})
public class UnidadDeNegocio implements Serializable {

    /**
     * @return the diaDeRenovacion
     */
    public Integer getDiaDeRenovacion() {
        return diaDeRenovacion;
    }

    /**
     * @param diaDeRenovacion the diaDeRenovacion to set
     */
    public void setDiaDeRenovacion(Integer diaDeRenovacion) {
        this.diaDeRenovacion = diaDeRenovacion;
    }

    /**
     * @return the mesDeRenovacion
     */
    public Integer getMesDeRenovacion() {
        return mesDeRenovacion;
    }

    /**
     * @param mesDeRenovacion the mesDeRenovacion to set
     */
    public void setMesDeRenovacion(Integer mesDeRenovacion) {
        this.mesDeRenovacion = mesDeRenovacion;
    }

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadDeNegocio")
    private Collection<ReconciliacionInternaCliente> reconciliacionInternaClienteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadDeNegocio")
    private Collection<ConciliacionBancaria> conciliacionBancariaCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Conduce> conduceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadNegocio")
    private Collection<DocumentoUnidadDeNegocio> documentoUnidadDeNegocioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadNegocio")
    private Collection<ReporteUnidadDeNegocio> reporteUnidadDeNegocioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadNegocio")
    private Collection<ReporteUnidadDeNegocio> reporteUnidadDeNegociuoCollection;
    @Column(name = "nombre_empresa_o_grupo")
    private String nombreEmpresaOGrupo;
    @JoinColumn(name = "empresa_o_grupo", referencedColumnName = "codigo")
    @ManyToOne
    private EmpresaOGrupo empresaOGrupo;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<RegistroDeFlota> registroDeFlotaCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<AsignacionDeFlota> asignacionDeFlotaCollection;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto_gravado")
    private Double montoGravado;

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
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Lob
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "sucursal")
    private boolean sucursal;
    @Column(name = "principal")
    private Integer principal;
    @Column(name = "dia_de_renovacion")
    private Integer diaDeRenovacion;
    @Column(name = "mes_de_renovacion")
    private Integer mesDeRenovacion;
    @Column(name = "nombre_principal")
    private String nombrePrincipal;
    @Column(name = "dia_de_facturacion")
    private Integer diaDeFacturacion;
    @Column(name = "dia_antes_de_vencer")
    private Integer diaAntesDeVencer;
    @Column(name = "rnc")
    private String rnc;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<EntradaDiario> entradaDiarioCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Adjunto> adjuntoCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Articulo> articuloCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<HistoricoPrecio> historicoPrecioCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Caja> cajaCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<TransferenciaAlmacen> transferenciaAlmacenCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<NotaCreditoSuplidor> notaCreditoSuplidorCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<CotizacionDeVenta> cotizacionDeVentaCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<FacturaSuplidor> facturaSuplidorCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<SolicitudCheque> solicitudChequeCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<ListaDePrecio> listaDePrecioCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<EntradaInventario> entradaInventarioCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<ArticuloUnidad> articuloUnidadCollection;
    @OneToMany(mappedBy = "unidadNegocio")
    private Collection<Formato607> formato607Collection;
    @OneToMany(mappedBy = "unidadNegocio")
    private Collection<Formato606> formato606Collection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Cxp> cxpCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<TransferenciaBanco> transferenciaBancoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadDeNegocio")
    private Collection<SecuenciaDocumento> secuenciaDocumentoCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Cliente> clienteCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<AjusteInventario> ajusteInventarioCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<FacturaTemporal> facturaTemporalCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<OrdenCompra> ordenCompraCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Cheque> chequeCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<DetalleListaDePrecio> detalleListaDePrecioCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<SecuenciaCheque> secuenciaChequeCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<MovimientoBanco> movimientoBancoCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<ReciboIngreso> reciboIngresoCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<DocumentoAnulado> documentoAnuladoCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Inventario> inventarioCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<CreditoCliente> creditoClienteCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Banco> bancoCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<NotaDebito> notaDebitoCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Usuario> usuarioCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<ExistenciaArticulo> existenciaArticuloCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<RegistroHoraExtra> registroHoraExtraCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Receta> recetaCollection;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<InventarioFinal> inventarioFinalCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Almacen> almacenCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<CierreMensualInventario> cierreMensualInventarioCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<ContratoDeServicio> contratoDeServicioCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Unidad> unidadCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<ComprobanteDePago> comprobanteDePagoCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<CuentaBanco> cuentaBancoCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Configuracion> configuracionCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Factura> facturaCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Nomina> nominaCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Pedido> pedidoCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<SolicitudSalidaInventario> solicitudSalidaInventarioCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<ConfiguracionCuentaContable> configuracionCuentaContableCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Solicitante> solicitanteCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Catalogo> catalogoCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Suplidor> suplidorCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<Empleado> empleadoCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<CajaChica> cajaChicaCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<NotaCredito> notaCreditoCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<RelacionNcf> relacionNcfCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<SalidaInventario> salidaInventarioCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<CatalogoCopy> catalogoCopyCollection;
    @OneToMany(mappedBy = "unidadDeNegocio")
    private Collection<DevolucionDeInventario> devolucionDeInventarioCollection;

    public UnidadDeNegocio() {
    }

    public UnidadDeNegocio(Integer codigo) {
        this.codigo = codigo;
    }

    public UnidadDeNegocio(Integer codigo, String nombre, String descripcion, String nombreUsuario, Date fechaRegistro, boolean sucursal) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nombreUsuario = nombreUsuario;
        this.fechaRegistro = fechaRegistro;
        this.sucursal = sucursal;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    public boolean getSucursal() {
        return sucursal;
    }

    public void setSucursal(boolean sucursal) {
        this.sucursal = sucursal;
    }

    public Integer getPrincipal() {
        return principal;
    }

    public void setPrincipal(Integer principal) {
        this.principal = principal;
    }

    public String getNombrePrincipal() {
        return nombrePrincipal;
    }

    public void setNombrePrincipal(String nombrePrincipal) {
        this.nombrePrincipal = nombrePrincipal;
    }

    public Integer getDiaDeFacturacion() {
        return diaDeFacturacion;
    }

    public void setDiaDeFacturacion(Integer diaDeFacturacion) {
        this.diaDeFacturacion = diaDeFacturacion;
    }

    public Integer getDiaAntesDeVencer() {
        return diaAntesDeVencer;
    }

    public void setDiaAntesDeVencer(Integer diaAntesDeVencer) {
        this.diaAntesDeVencer = diaAntesDeVencer;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    @XmlTransient
    public Collection<EntradaDiario> getEntradaDiarioCollection() {
        return entradaDiarioCollection;
    }

    public void setEntradaDiarioCollection(Collection<EntradaDiario> entradaDiarioCollection) {
        this.entradaDiarioCollection = entradaDiarioCollection;
    }

    @XmlTransient
    public Collection<Adjunto> getAdjuntoCollection() {
        return adjuntoCollection;
    }

    public void setAdjuntoCollection(Collection<Adjunto> adjuntoCollection) {
        this.adjuntoCollection = adjuntoCollection;
    }

    @XmlTransient
    public Collection<Articulo> getArticuloCollection() {
        return articuloCollection;
    }

    public void setArticuloCollection(Collection<Articulo> articuloCollection) {
        this.articuloCollection = articuloCollection;
    }

    @XmlTransient
    public Collection<HistoricoPrecio> getHistoricoPrecioCollection() {
        return historicoPrecioCollection;
    }

    public void setHistoricoPrecioCollection(Collection<HistoricoPrecio> historicoPrecioCollection) {
        this.historicoPrecioCollection = historicoPrecioCollection;
    }

    @XmlTransient
    public Collection<Caja> getCajaCollection() {
        return cajaCollection;
    }

    public void setCajaCollection(Collection<Caja> cajaCollection) {
        this.cajaCollection = cajaCollection;
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
    public Collection<ListaDePrecio> getListaDePrecioCollection() {
        return listaDePrecioCollection;
    }

    public void setListaDePrecioCollection(Collection<ListaDePrecio> listaDePrecioCollection) {
        this.listaDePrecioCollection = listaDePrecioCollection;
    }

    @XmlTransient
    public Collection<EntradaInventario> getEntradaInventarioCollection() {
        return entradaInventarioCollection;
    }

    public void setEntradaInventarioCollection(Collection<EntradaInventario> entradaInventarioCollection) {
        this.entradaInventarioCollection = entradaInventarioCollection;
    }

    @XmlTransient
    public Collection<ArticuloUnidad> getArticuloUnidadCollection() {
        return articuloUnidadCollection;
    }

    public void setArticuloUnidadCollection(Collection<ArticuloUnidad> articuloUnidadCollection) {
        this.articuloUnidadCollection = articuloUnidadCollection;
    }

    @XmlTransient
    public Collection<Formato607> getFormato607Collection() {
        return formato607Collection;
    }

    public void setFormato607Collection(Collection<Formato607> formato607Collection) {
        this.formato607Collection = formato607Collection;
    }

    @XmlTransient
    public Collection<Formato606> getFormato606Collection() {
        return formato606Collection;
    }

    public void setFormato606Collection(Collection<Formato606> formato606Collection) {
        this.formato606Collection = formato606Collection;
    }

    @XmlTransient
    public Collection<Cxp> getCxpCollection() {
        return cxpCollection;
    }

    public void setCxpCollection(Collection<Cxp> cxpCollection) {
        this.cxpCollection = cxpCollection;
    }

    @XmlTransient
    public Collection<TransferenciaBanco> getTransferenciaBancoCollection() {
        return transferenciaBancoCollection;
    }

    public void setTransferenciaBancoCollection(Collection<TransferenciaBanco> transferenciaBancoCollection) {
        this.transferenciaBancoCollection = transferenciaBancoCollection;
    }

    @XmlTransient
    public Collection<SecuenciaDocumento> getSecuenciaDocumentoCollection() {
        return secuenciaDocumentoCollection;
    }

    public void setSecuenciaDocumentoCollection(Collection<SecuenciaDocumento> secuenciaDocumentoCollection) {
        this.secuenciaDocumentoCollection = secuenciaDocumentoCollection;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @XmlTransient
    public Collection<AjusteInventario> getAjusteInventarioCollection() {
        return ajusteInventarioCollection;
    }

    public void setAjusteInventarioCollection(Collection<AjusteInventario> ajusteInventarioCollection) {
        this.ajusteInventarioCollection = ajusteInventarioCollection;
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
    public Collection<Cheque> getChequeCollection() {
        return chequeCollection;
    }

    public void setChequeCollection(Collection<Cheque> chequeCollection) {
        this.chequeCollection = chequeCollection;
    }

    @XmlTransient
    public Collection<DetalleListaDePrecio> getDetalleListaDePrecioCollection() {
        return detalleListaDePrecioCollection;
    }

    public void setDetalleListaDePrecioCollection(Collection<DetalleListaDePrecio> detalleListaDePrecioCollection) {
        this.detalleListaDePrecioCollection = detalleListaDePrecioCollection;
    }

    @XmlTransient
    public Collection<SecuenciaCheque> getSecuenciaChequeCollection() {
        return secuenciaChequeCollection;
    }

    public void setSecuenciaChequeCollection(Collection<SecuenciaCheque> secuenciaChequeCollection) {
        this.secuenciaChequeCollection = secuenciaChequeCollection;
    }

    @XmlTransient
    public Collection<MovimientoBanco> getMovimientoBancoCollection() {
        return movimientoBancoCollection;
    }

    public void setMovimientoBancoCollection(Collection<MovimientoBanco> movimientoBancoCollection) {
        this.movimientoBancoCollection = movimientoBancoCollection;
    }

    @XmlTransient
    public Collection<ReciboIngreso> getReciboIngresoCollection() {
        return reciboIngresoCollection;
    }

    public void setReciboIngresoCollection(Collection<ReciboIngreso> reciboIngresoCollection) {
        this.reciboIngresoCollection = reciboIngresoCollection;
    }

    @XmlTransient
    public Collection<DocumentoAnulado> getDocumentoAnuladoCollection() {
        return documentoAnuladoCollection;
    }

    public void setDocumentoAnuladoCollection(Collection<DocumentoAnulado> documentoAnuladoCollection) {
        this.documentoAnuladoCollection = documentoAnuladoCollection;
    }

    @XmlTransient
    public Collection<Inventario> getInventarioCollection() {
        return inventarioCollection;
    }

    public void setInventarioCollection(Collection<Inventario> inventarioCollection) {
        this.inventarioCollection = inventarioCollection;
    }

    @XmlTransient
    public Collection<CreditoCliente> getCreditoClienteCollection() {
        return creditoClienteCollection;
    }

    public void setCreditoClienteCollection(Collection<CreditoCliente> creditoClienteCollection) {
        this.creditoClienteCollection = creditoClienteCollection;
    }

    @XmlTransient
    public Collection<Banco> getBancoCollection() {
        return bancoCollection;
    }

    public void setBancoCollection(Collection<Banco> bancoCollection) {
        this.bancoCollection = bancoCollection;
    }

    @XmlTransient
    public Collection<NotaDebito> getNotaDebitoCollection() {
        return notaDebitoCollection;
    }

    public void setNotaDebitoCollection(Collection<NotaDebito> notaDebitoCollection) {
        this.notaDebitoCollection = notaDebitoCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<ExistenciaArticulo> getExistenciaArticuloCollection() {
        return existenciaArticuloCollection;
    }

    public void setExistenciaArticuloCollection(Collection<ExistenciaArticulo> existenciaArticuloCollection) {
        this.existenciaArticuloCollection = existenciaArticuloCollection;
    }

    @XmlTransient
    public Collection<RegistroHoraExtra> getRegistroHoraExtraCollection() {
        return registroHoraExtraCollection;
    }

    public void setRegistroHoraExtraCollection(Collection<RegistroHoraExtra> registroHoraExtraCollection) {
        this.registroHoraExtraCollection = registroHoraExtraCollection;
    }

    @XmlTransient
    public Collection<Receta> getRecetaCollection() {
        return recetaCollection;
    }

    public void setRecetaCollection(Collection<Receta> recetaCollection) {
        this.recetaCollection = recetaCollection;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<InventarioFinal> getInventarioFinalCollection() {
        return inventarioFinalCollection;
    }

    public void setInventarioFinalCollection(Collection<InventarioFinal> inventarioFinalCollection) {
        this.inventarioFinalCollection = inventarioFinalCollection;
    }

    @XmlTransient
    public Collection<Almacen> getAlmacenCollection() {
        return almacenCollection;
    }

    public void setAlmacenCollection(Collection<Almacen> almacenCollection) {
        this.almacenCollection = almacenCollection;
    }

    @XmlTransient
    public Collection<CierreMensualInventario> getCierreMensualInventarioCollection() {
        return cierreMensualInventarioCollection;
    }

    public void setCierreMensualInventarioCollection(Collection<CierreMensualInventario> cierreMensualInventarioCollection) {
        this.cierreMensualInventarioCollection = cierreMensualInventarioCollection;
    }

    @XmlTransient
    public Collection<ContratoDeServicio> getContratoDeServicioCollection() {
        return contratoDeServicioCollection;
    }

    public void setContratoDeServicioCollection(Collection<ContratoDeServicio> contratoDeServicioCollection) {
        this.contratoDeServicioCollection = contratoDeServicioCollection;
    }

    @XmlTransient
    public Collection<Unidad> getUnidadCollection() {
        return unidadCollection;
    }

    public void setUnidadCollection(Collection<Unidad> unidadCollection) {
        this.unidadCollection = unidadCollection;
    }

    @XmlTransient
    public Collection<ComprobanteDePago> getComprobanteDePagoCollection() {
        return comprobanteDePagoCollection;
    }

    public void setComprobanteDePagoCollection(Collection<ComprobanteDePago> comprobanteDePagoCollection) {
        this.comprobanteDePagoCollection = comprobanteDePagoCollection;
    }

    @XmlTransient
    public Collection<CuentaBanco> getCuentaBancoCollection() {
        return cuentaBancoCollection;
    }

    public void setCuentaBancoCollection(Collection<CuentaBanco> cuentaBancoCollection) {
        this.cuentaBancoCollection = cuentaBancoCollection;
    }

    @XmlTransient
    public Collection<Configuracion> getConfiguracionCollection() {
        return configuracionCollection;
    }

    public void setConfiguracionCollection(Collection<Configuracion> configuracionCollection) {
        this.configuracionCollection = configuracionCollection;
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
    public Collection<ConfiguracionCuentaContable> getConfiguracionCuentaContableCollection() {
        return configuracionCuentaContableCollection;
    }

    public void setConfiguracionCuentaContableCollection(Collection<ConfiguracionCuentaContable> configuracionCuentaContableCollection) {
        this.configuracionCuentaContableCollection = configuracionCuentaContableCollection;
    }

    @XmlTransient
    public Collection<Solicitante> getSolicitanteCollection() {
        return solicitanteCollection;
    }

    public void setSolicitanteCollection(Collection<Solicitante> solicitanteCollection) {
        this.solicitanteCollection = solicitanteCollection;
    }

    @XmlTransient
    public Collection<Catalogo> getCatalogoCollection() {
        return catalogoCollection;
    }

    public void setCatalogoCollection(Collection<Catalogo> catalogoCollection) {
        this.catalogoCollection = catalogoCollection;
    }

    @XmlTransient
    public Collection<Suplidor> getSuplidorCollection() {
        return suplidorCollection;
    }

    public void setSuplidorCollection(Collection<Suplidor> suplidorCollection) {
        this.suplidorCollection = suplidorCollection;
    }

    @XmlTransient
    public Collection<Empleado> getEmpleadoCollection() {
        return empleadoCollection;
    }

    public void setEmpleadoCollection(Collection<Empleado> empleadoCollection) {
        this.empleadoCollection = empleadoCollection;
    }

    @XmlTransient
    public Collection<CajaChica> getCajaChicaCollection() {
        return cajaChicaCollection;
    }

    public void setCajaChicaCollection(Collection<CajaChica> cajaChicaCollection) {
        this.cajaChicaCollection = cajaChicaCollection;
    }

    @XmlTransient
    public Collection<NotaCredito> getNotaCreditoCollection() {
        return notaCreditoCollection;
    }

    public void setNotaCreditoCollection(Collection<NotaCredito> notaCreditoCollection) {
        this.notaCreditoCollection = notaCreditoCollection;
    }

    @XmlTransient
    public Collection<RelacionNcf> getRelacionNcfCollection() {
        return relacionNcfCollection;
    }

    public void setRelacionNcfCollection(Collection<RelacionNcf> relacionNcfCollection) {
        this.relacionNcfCollection = relacionNcfCollection;
    }

    @XmlTransient
    public Collection<SalidaInventario> getSalidaInventarioCollection() {
        return salidaInventarioCollection;
    }

    public void setSalidaInventarioCollection(Collection<SalidaInventario> salidaInventarioCollection) {
        this.salidaInventarioCollection = salidaInventarioCollection;
    }

    @XmlTransient
    public Collection<CatalogoCopy> getCatalogoCopyCollection() {
        return catalogoCopyCollection;
    }

    public void setCatalogoCopyCollection(Collection<CatalogoCopy> catalogoCopyCollection) {
        this.catalogoCopyCollection = catalogoCopyCollection;
    }

    @XmlTransient
    public Collection<DevolucionDeInventario> getDevolucionDeInventarioCollection() {
        return devolucionDeInventarioCollection;
    }

    public void setDevolucionDeInventarioCollection(Collection<DevolucionDeInventario> devolucionDeInventarioCollection) {
        this.devolucionDeInventarioCollection = devolucionDeInventarioCollection;
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
        if (!(object instanceof UnidadDeNegocio)) {
            return false;
        }
        UnidadDeNegocio other = (UnidadDeNegocio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.UnidadDeNegocio[ codigo=" + codigo + " ]";
    }

    public Double getMontoGravado() {
        return montoGravado;
    }

    public void setMontoGravado(Double montoGravado) {
        this.montoGravado = montoGravado;
    }

    @XmlTransient
    public Collection<RegistroDeFlota> getRegistroDeFlotaCollection() {
        return registroDeFlotaCollection;
    }

    public void setRegistroDeFlotaCollection(Collection<RegistroDeFlota> registroDeFlotaCollection) {
        this.registroDeFlotaCollection = registroDeFlotaCollection;
    }

    @XmlTransient
    public Collection<AsignacionDeFlota> getAsignacionDeFlotaCollection() {
        return asignacionDeFlotaCollection;
    }

    public void setAsignacionDeFlotaCollection(Collection<AsignacionDeFlota> asignacionDeFlotaCollection) {
        this.asignacionDeFlotaCollection = asignacionDeFlotaCollection;
    }

    public String getNombreEmpresaOGrupo() {
        return nombreEmpresaOGrupo;
    }

    public void setNombreEmpresaOGrupo(String nombreEmpresaOGrupo) {
        this.nombreEmpresaOGrupo = nombreEmpresaOGrupo;
    }

    public EmpresaOGrupo getEmpresaOGrupo() {
        return empresaOGrupo;
    }

    public void setEmpresaOGrupo(EmpresaOGrupo empresaOGrupo) {
        this.empresaOGrupo = empresaOGrupo;
    }

    @XmlTransient
    public Collection<ReporteUnidadDeNegocio> getReporteUnidadDeNegociuoCollection() {
        return reporteUnidadDeNegociuoCollection;
    }

    public void setReporteUnidadDeNegociuoCollection(Collection<ReporteUnidadDeNegocio> reporteUnidadDeNegociuoCollection) {
        this.reporteUnidadDeNegociuoCollection = reporteUnidadDeNegociuoCollection;
    }

    @XmlTransient
    public Collection<ReporteUnidadDeNegocio> getReporteUnidadDeNegocioCollection() {
        return reporteUnidadDeNegocioCollection;
    }

    public void setReporteUnidadDeNegocioCollection(Collection<ReporteUnidadDeNegocio> reporteUnidadDeNegocioCollection) {
        this.reporteUnidadDeNegocioCollection = reporteUnidadDeNegocioCollection;
    }

    @XmlTransient
    public Collection<DocumentoUnidadDeNegocio> getDocumentoUnidadDeNegocioCollection() {
        return documentoUnidadDeNegocioCollection;
    }

    public void setDocumentoUnidadDeNegocioCollection(Collection<DocumentoUnidadDeNegocio> documentoUnidadDeNegocioCollection) {
        this.documentoUnidadDeNegocioCollection = documentoUnidadDeNegocioCollection;
    }

    @XmlTransient
    public Collection<Conduce> getConduceCollection() {
        return conduceCollection;
    }

    public void setConduceCollection(Collection<Conduce> conduceCollection) {
        this.conduceCollection = conduceCollection;
    }

    @XmlTransient
    public Collection<ConciliacionBancaria> getConciliacionBancariaCollection() {
        return conciliacionBancariaCollection;
    }

    public void setConciliacionBancariaCollection(Collection<ConciliacionBancaria> conciliacionBancariaCollection) {
        this.conciliacionBancariaCollection = conciliacionBancariaCollection;
    }

    @XmlTransient
    public Collection<ReconciliacionInternaCliente> getReconciliacionInternaClienteCollection() {
        return reconciliacionInternaClienteCollection;
    }

    public void setReconciliacionInternaClienteCollection(Collection<ReconciliacionInternaCliente> reconciliacionInternaClienteCollection) {
        this.reconciliacionInternaClienteCollection = reconciliacionInternaClienteCollection;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

}
