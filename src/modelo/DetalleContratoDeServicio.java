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
@Table(name = "detalle_contrato_de_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleContratoDeServicio.findAll", query = "SELECT d FROM DetalleContratoDeServicio d")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByCodigo", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByCantidad", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByCondicion", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.condicion = :condicion")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByPrecioAcordado", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.precioAcordado = :precioAcordado")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByPrecioPagado", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.precioPagado = :precioPagado")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByMensualidad", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.mensualidad = :mensualidad")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByFechaDesde", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.fechaDesde = :fechaDesde")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByFechaHasta", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.fechaHasta = :fechaHasta")
    , @NamedQuery(name = "DetalleContratoDeServicio.findBySubTotal", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.subTotal = :subTotal")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByItbis", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.itbis = :itbis")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByTotal", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.total = :total")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByTasaItbis", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.tasaItbis = :tasaItbis")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByCantidadFrecuenciaDePago", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.cantidadFrecuenciaDePago = :cantidadFrecuenciaDePago")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByAdicional", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.adicional = :adicional")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByHabilitado", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.habilitado = :habilitado")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByFechaUltimoPagoDesde", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.fechaUltimoPagoDesde = :fechaUltimoPagoDesde")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByFechaUltimoPagoHasta", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.fechaUltimoPagoHasta = :fechaUltimoPagoHasta")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByRecurrente", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.recurrente = :recurrente")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByNumeroSim", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.numeroSim = :numeroSim")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByNumeroImei", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.numeroImei = :numeroImei")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByPrecioRenovacion", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.precioRenovacion = :precioRenovacion")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByEstado", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.estado = :estado")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByUsuario", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.usuario = :usuario")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByNombreUsuario", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByFacturado", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.facturado = :facturado")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByNombreInstalador", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.nombreInstalador = :nombreInstalador")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByFechaInstalacion", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.fechaInstalacion = :fechaInstalacion")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByNombreDesintalar", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.nombreDesintalar = :nombreDesintalar")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByFechaDesinstalacion", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.fechaDesinstalacion = :fechaDesinstalacion")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByFechaReinstalacion", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.fechaReinstalacion = :fechaReinstalacion")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByReinstaladoPor", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.reinstaladoPor = :reinstaladoPor")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByNombreReinstalador", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.nombreReinstalador = :nombreReinstalador")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByNombreEjecutivoDeVenta", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.nombreEjecutivoDeVenta = :nombreEjecutivoDeVenta")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByAvanve", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.avanve = :avanve")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByPrecioDeOferta", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.precioDeOferta = :precioDeOferta")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByCantidadMeses", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.cantidadMeses = :cantidadMeses")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByCantidadAnio", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.cantidadAnio = :cantidadAnio")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByReemplazo", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.reemplazo = :reemplazo")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByPrecioInstalacion", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.precioInstalacion = :precioInstalacion")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByAlmacen", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.almacen = :almacen")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByCambioDeModalidad", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.cambioDeModalidad = :cambioDeModalidad")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByGpsDelCliente", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.gpsDelCliente = :gpsDelCliente")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByReactivacion", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.reactivacion = :reactivacion")
    , @NamedQuery(name = "DetalleContratoDeServicio.findByTraspasado", query = "SELECT d FROM DetalleContratoDeServicio d WHERE d.traspasado = :traspasado")})
public class DetalleContratoDeServicio implements Serializable {

    @Column(name = "fecha_habilitado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHabilitado;

    @Column(name = "fecha_suspendido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSuspendido;

    @Lob
    @Column(name = "razon_actualizacion")
    private String razonActualizacion;

    @OneToMany(mappedBy = "equipoGps")
    private Collection<DatosDeVehiculo> datosDeVehiculoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicio")
    private Collection<DatosDeVehiculo> datosDeVehiculoCollection1;

    @Basic(optional = false)
    @Column(name = "compatibilidad")
    private boolean compatibilidad;
    @Column(name = "suplidor")
    private Integer suplidor;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "condicion")
    private Integer condicion;
    @Basic(optional = false)
    @Column(name = "precio_acordado")
    private double precioAcordado;
    @Basic(optional = false)
    @Column(name = "precio_pagado")
    private double precioPagado;
    @Basic(optional = false)
    @Column(name = "mensualidad")
    private double mensualidad;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "fecha_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Basic(optional = false)
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sub_total")
    private Double subTotal;
    @Column(name = "itbis")
    private Double itbis;
    @Column(name = "total")
    private Double total;
    @Column(name = "tasa_itbis")
    private Double tasaItbis;
    @Column(name = "cantidad_frecuencia_de_pago")
    private Double cantidadFrecuenciaDePago;
    @Basic(optional = false)
    @Column(name = "adicional")
    private boolean adicional;
    @Basic(optional = false)
    @Column(name = "habilitado")
    private boolean habilitado;
    @Column(name = "fecha_ultimo_pago_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimoPagoDesde;
    @Column(name = "fecha_ultimo_pago_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimoPagoHasta;
    @Basic(optional = false)
    @Column(name = "recurrente")
    private boolean recurrente;
    @Column(name = "numero_sim")
    private String numeroSim;
    @Column(name = "numero_imei")
    private String numeroImei;
    @Column(name = "precio_renovacion")
    private Double precioRenovacion;
    @Column(name = "estado")
    private String estado;
    @Column(name = "usuario")
    private Integer usuario;
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Lob
    @Column(name = "razon_desinstalacion")
    private String razonDesinstalacion;
    @Basic(optional = false)
    @Column(name = "facturado")
    private boolean facturado;
    @Column(name = "nombre_instalador")
    private String nombreInstalador;
    @Column(name = "fecha_instalacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInstalacion;
    @Column(name = "nombre_desintalar")
    private String nombreDesintalar;
    @Column(name = "fecha_desinstalacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesinstalacion;
    @Column(name = "fecha_reinstalacion")
    @Temporal(TemporalType.DATE)
    private Date fechaReinstalacion;
    @Column(name = "reinstalado_por")
    private Integer reinstaladoPor;
    @Column(name = "nombre_reinstalador")
    private String nombreReinstalador;
    @Column(name = "nombre_ejecutivo_de_venta")
    private String nombreEjecutivoDeVenta;
    @Basic(optional = false)
    @Column(name = "avanve")
    private boolean avanve;
    @Column(name = "precio_de_oferta")
    private Double precioDeOferta;
    @Column(name = "cantidad_meses")
    private Integer cantidadMeses;
    @Column(name = "cantidad_anio")
    private Integer cantidadAnio;
    @Basic(optional = false)
    @Column(name = "reemplazo")
    private boolean reemplazo;
    @Column(name = "precio_instalacion")
    private Double precioInstalacion;
    @Column(name = "almacen")
    private Integer almacen;
    @Basic(optional = false)
    @Column(name = "cambio_de_modalidad")
    private boolean cambioDeModalidad;
    @Basic(optional = false)
    @Column(name = "gps_del_cliente")
    private boolean gpsDelCliente;
    @Basic(optional = false)
    @Column(name = "reactivacion")
    private boolean reactivacion;
    @Basic(optional = false)
    @Column(name = "traspasado")
    private boolean traspasado;
    @JoinColumn(name = "equipo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo equipo;
    @JoinColumn(name = "unidad", referencedColumnName = "codigo")
    @ManyToOne
    private Unidad unidad;
    @JoinColumn(name = "desintalador", referencedColumnName = "codigo")
    @ManyToOne
    private Instalador desintalador;
    @JoinColumn(name = "contrato_de_servicio", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private ContratoDeServicio contratoDeServicio;
    @JoinColumn(name = "frecuencia_de_pago", referencedColumnName = "codigo")
    @ManyToOne
    private FrecuenciaDePago frecuenciaDePago;
    @JoinColumn(name = "modalidad", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private ModalidadEquipo modalidad;
    @JoinColumn(name = "imei", referencedColumnName = "codigo")
    @ManyToOne
    private RegistroDeImei imei;
    @JoinColumn(name = "tipo_de_servicio", referencedColumnName = "codigo")
    @ManyToOne
    private TipoDeServicio tipoDeServicio;
    @JoinColumn(name = "sim", referencedColumnName = "codigo")
    @ManyToOne
    private RegistroDeSim sim;
    @JoinColumn(name = "ejecutivo_de_venta", referencedColumnName = "codigo")
    @ManyToOne
    private EjecutivoDeVenta ejecutivoDeVenta;
    @JoinColumn(name = "instalador", referencedColumnName = "codigo")
    @ManyToOne
    private Instalador instalador;

    public DetalleContratoDeServicio() {
    }

    public DetalleContratoDeServicio(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleContratoDeServicio(Integer codigo, int cantidad, double precioAcordado, double precioPagado, double mensualidad, String descripcion, Date fechaDesde, Date fechaHasta, boolean adicional, boolean habilitado, boolean recurrente, boolean facturado, boolean avanve, boolean reemplazo, boolean cambioDeModalidad, boolean gpsDelCliente, boolean reactivacion, boolean traspasado) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precioAcordado = precioAcordado;
        this.precioPagado = precioPagado;
        this.mensualidad = mensualidad;
        this.descripcion = descripcion;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.adicional = adicional;
        this.habilitado = habilitado;
        this.recurrente = recurrente;
        this.facturado = facturado;
        this.avanve = avanve;
        this.reemplazo = reemplazo;
        this.cambioDeModalidad = cambioDeModalidad;
        this.gpsDelCliente = gpsDelCliente;
        this.reactivacion = reactivacion;
        this.traspasado = traspasado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCondicion() {
        return condicion;
    }

    public void setCondicion(Integer condicion) {
        this.condicion = condicion;
    }

    public double getPrecioAcordado() {
        return precioAcordado;
    }

    public void setPrecioAcordado(double precioAcordado) {
        this.precioAcordado = precioAcordado;
    }

    public double getPrecioPagado() {
        return precioPagado;
    }

    public void setPrecioPagado(double precioPagado) {
        this.precioPagado = precioPagado;
    }

    public double getMensualidad() {
        return mensualidad;
    }

    public void setMensualidad(double mensualidad) {
        this.mensualidad = mensualidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTasaItbis() {
        return tasaItbis;
    }

    public void setTasaItbis(Double tasaItbis) {
        this.tasaItbis = tasaItbis;
    }

    public Double getCantidadFrecuenciaDePago() {
        return cantidadFrecuenciaDePago;
    }

    public void setCantidadFrecuenciaDePago(Double cantidadFrecuenciaDePago) {
        this.cantidadFrecuenciaDePago = cantidadFrecuenciaDePago;
    }

    public boolean getAdicional() {
        return adicional;
    }

    public void setAdicional(boolean adicional) {
        this.adicional = adicional;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Date getFechaUltimoPagoDesde() {
        return fechaUltimoPagoDesde;
    }

    public void setFechaUltimoPagoDesde(Date fechaUltimoPagoDesde) {
        this.fechaUltimoPagoDesde = fechaUltimoPagoDesde;
    }

    public Date getFechaUltimoPagoHasta() {
        return fechaUltimoPagoHasta;
    }

    public void setFechaUltimoPagoHasta(Date fechaUltimoPagoHasta) {
        this.fechaUltimoPagoHasta = fechaUltimoPagoHasta;
    }

    public boolean getRecurrente() {
        return recurrente;
    }

    public void setRecurrente(boolean recurrente) {
        this.recurrente = recurrente;
    }

    public String getNumeroSim() {
        return numeroSim;
    }

    public void setNumeroSim(String numeroSim) {
        this.numeroSim = numeroSim;
    }

    public String getNumeroImei() {
        return numeroImei;
    }

    public void setNumeroImei(String numeroImei) {
        this.numeroImei = numeroImei;
    }

    public Double getPrecioRenovacion() {
        return precioRenovacion;
    }

    public void setPrecioRenovacion(Double precioRenovacion) {
        this.precioRenovacion = precioRenovacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRazonDesinstalacion() {
        return razonDesinstalacion;
    }

    public void setRazonDesinstalacion(String razonDesinstalacion) {
        this.razonDesinstalacion = razonDesinstalacion;
    }

    public boolean getFacturado() {
        return facturado;
    }

    public void setFacturado(boolean facturado) {
        this.facturado = facturado;
    }

    public String getNombreInstalador() {
        return nombreInstalador;
    }

    public void setNombreInstalador(String nombreInstalador) {
        this.nombreInstalador = nombreInstalador;
    }

    public Date getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(Date fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public String getNombreDesintalar() {
        return nombreDesintalar;
    }

    public void setNombreDesintalar(String nombreDesintalar) {
        this.nombreDesintalar = nombreDesintalar;
    }

    public Date getFechaDesinstalacion() {
        return fechaDesinstalacion;
    }

    public void setFechaDesinstalacion(Date fechaDesinstalacion) {
        this.fechaDesinstalacion = fechaDesinstalacion;
    }

    public Date getFechaReinstalacion() {
        return fechaReinstalacion;
    }

    public void setFechaReinstalacion(Date fechaReinstalacion) {
        this.fechaReinstalacion = fechaReinstalacion;
    }

    public Integer getReinstaladoPor() {
        return reinstaladoPor;
    }

    public void setReinstaladoPor(Integer reinstaladoPor) {
        this.reinstaladoPor = reinstaladoPor;
    }

    public String getNombreReinstalador() {
        return nombreReinstalador;
    }

    public void setNombreReinstalador(String nombreReinstalador) {
        this.nombreReinstalador = nombreReinstalador;
    }

    public String getNombreEjecutivoDeVenta() {
        return nombreEjecutivoDeVenta;
    }

    public void setNombreEjecutivoDeVenta(String nombreEjecutivoDeVenta) {
        this.nombreEjecutivoDeVenta = nombreEjecutivoDeVenta;
    }

    public boolean getAvanve() {
        return avanve;
    }

    public void setAvanve(boolean avanve) {
        this.avanve = avanve;
    }

    public Double getPrecioDeOferta() {
        return precioDeOferta;
    }

    public void setPrecioDeOferta(Double precioDeOferta) {
        this.precioDeOferta = precioDeOferta;
    }

    public Integer getCantidadMeses() {
        return cantidadMeses;
    }

    public void setCantidadMeses(Integer cantidadMeses) {
        this.cantidadMeses = cantidadMeses;
    }

    public Integer getCantidadAnio() {
        return cantidadAnio;
    }

    public void setCantidadAnio(Integer cantidadAnio) {
        this.cantidadAnio = cantidadAnio;
    }

    public boolean getReemplazo() {
        return reemplazo;
    }

    public void setReemplazo(boolean reemplazo) {
        this.reemplazo = reemplazo;
    }

    public Double getPrecioInstalacion() {
        return precioInstalacion;
    }

    public void setPrecioInstalacion(Double precioInstalacion) {
        this.precioInstalacion = precioInstalacion;
    }

    public Integer getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Integer almacen) {
        this.almacen = almacen;
    }

    public boolean getCambioDeModalidad() {
        return cambioDeModalidad;
    }

    public void setCambioDeModalidad(boolean cambioDeModalidad) {
        this.cambioDeModalidad = cambioDeModalidad;
    }

    public boolean getGpsDelCliente() {
        return gpsDelCliente;
    }

    public void setGpsDelCliente(boolean gpsDelCliente) {
        this.gpsDelCliente = gpsDelCliente;
    }

    public boolean getReactivacion() {
        return reactivacion;
    }

    public void setReactivacion(boolean reactivacion) {
        this.reactivacion = reactivacion;
    }

    public boolean getTraspasado() {
        return traspasado;
    }

    public void setTraspasado(boolean traspasado) {
        this.traspasado = traspasado;
    }

    public Articulo getEquipo() {
        return equipo;
    }

    public void setEquipo(Articulo equipo) {
        this.equipo = equipo;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public Instalador getDesintalador() {
        return desintalador;
    }

    public void setDesintalador(Instalador desintalador) {
        this.desintalador = desintalador;
    }

    public ContratoDeServicio getContratoDeServicio() {
        return contratoDeServicio;
    }

    public void setContratoDeServicio(ContratoDeServicio contratoDeServicio) {
        this.contratoDeServicio = contratoDeServicio;
    }

    public FrecuenciaDePago getFrecuenciaDePago() {
        return frecuenciaDePago;
    }

    public void setFrecuenciaDePago(FrecuenciaDePago frecuenciaDePago) {
        this.frecuenciaDePago = frecuenciaDePago;
    }

    public ModalidadEquipo getModalidad() {
        return modalidad;
    }

    public void setModalidad(ModalidadEquipo modalidad) {
        this.modalidad = modalidad;
    }

    public RegistroDeImei getImei() {
        return imei;
    }

    public void setImei(RegistroDeImei imei) {
        this.imei = imei;
    }

    public TipoDeServicio getTipoDeServicio() {
        return tipoDeServicio;
    }

    public void setTipoDeServicio(TipoDeServicio tipoDeServicio) {
        this.tipoDeServicio = tipoDeServicio;
    }

    public RegistroDeSim getSim() {
        return sim;
    }

    public void setSim(RegistroDeSim sim) {
        this.sim = sim;
    }

    public EjecutivoDeVenta getEjecutivoDeVenta() {
        return ejecutivoDeVenta;
    }

    public void setEjecutivoDeVenta(EjecutivoDeVenta ejecutivoDeVenta) {
        this.ejecutivoDeVenta = ejecutivoDeVenta;
    }

    public Instalador getInstalador() {
        return instalador;
    }

    public void setInstalador(Instalador instalador) {
        this.instalador = instalador;
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
        if (!(object instanceof DetalleContratoDeServicio)) {
            return false;
        }
        DetalleContratoDeServicio other = (DetalleContratoDeServicio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleContratoDeServicio[ codigo=" + codigo + " ]";
    }

    public boolean getCompatibilidad() {
        return compatibilidad;
    }

    public void setCompatibilidad(boolean compatibilidad) {
        this.compatibilidad = compatibilidad;
    }

    public Integer getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Integer suplidor) {
        this.suplidor = suplidor;
    }

    @XmlTransient
    public Collection<DatosDeVehiculo> getDatosDeVehiculoCollection() {
        return datosDeVehiculoCollection;
    }

    public void setDatosDeVehiculoCollection(Collection<DatosDeVehiculo> datosDeVehiculoCollection) {
        this.datosDeVehiculoCollection = datosDeVehiculoCollection;
    }

    @XmlTransient
    public Collection<DatosDeVehiculo> getDatosDeVehiculoCollection1() {
        return datosDeVehiculoCollection1;
    }

    public void setDatosDeVehiculoCollection1(Collection<DatosDeVehiculo> datosDeVehiculoCollection1) {
        this.datosDeVehiculoCollection1 = datosDeVehiculoCollection1;
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
    
}
