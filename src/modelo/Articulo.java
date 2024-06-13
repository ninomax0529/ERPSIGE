/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a")
    , @NamedQuery(name = "Articulo.findByCodigo", query = "SELECT a FROM Articulo a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "Articulo.findByExistencia", query = "SELECT a FROM Articulo a WHERE a.existencia = :existencia")
    , @NamedQuery(name = "Articulo.findByMaximo", query = "SELECT a FROM Articulo a WHERE a.maximo = :maximo")
    , @NamedQuery(name = "Articulo.findByMinimo", query = "SELECT a FROM Articulo a WHERE a.minimo = :minimo")
    , @NamedQuery(name = "Articulo.findByApedir", query = "SELECT a FROM Articulo a WHERE a.apedir = :apedir")
    , @NamedQuery(name = "Articulo.findByPrecioCompra", query = "SELECT a FROM Articulo a WHERE a.precioCompra = :precioCompra")
    , @NamedQuery(name = "Articulo.findByUltimoSuplidor", query = "SELECT a FROM Articulo a WHERE a.ultimoSuplidor = :ultimoSuplidor")
    , @NamedQuery(name = "Articulo.findByUbicacion", query = "SELECT a FROM Articulo a WHERE a.ubicacion = :ubicacion")
    , @NamedQuery(name = "Articulo.findByExentoItbis", query = "SELECT a FROM Articulo a WHERE a.exentoItbis = :exentoItbis")
    , @NamedQuery(name = "Articulo.findByCodigoBarra", query = "SELECT a FROM Articulo a WHERE a.codigoBarra = :codigoBarra")
    , @NamedQuery(name = "Articulo.findByActivo", query = "SELECT a FROM Articulo a WHERE a.activo = :activo")
    , @NamedQuery(name = "Articulo.findByPrecioCompraAnterior", query = "SELECT a FROM Articulo a WHERE a.precioCompraAnterior = :precioCompraAnterior")
    , @NamedQuery(name = "Articulo.findByPrecioVentaAnterior", query = "SELECT a FROM Articulo a WHERE a.precioVentaAnterior = :precioVentaAnterior")
    , @NamedQuery(name = "Articulo.findByAlmacen", query = "SELECT a FROM Articulo a WHERE a.almacen = :almacen")
    , @NamedQuery(name = "Articulo.findByInventariable", query = "SELECT a FROM Articulo a WHERE a.inventariable = :inventariable")
    , @NamedQuery(name = "Articulo.findByPrecioVenta1", query = "SELECT a FROM Articulo a WHERE a.precioVenta1 = :precioVenta1")
    , @NamedQuery(name = "Articulo.findByPrecioVenta2", query = "SELECT a FROM Articulo a WHERE a.precioVenta2 = :precioVenta2")
    , @NamedQuery(name = "Articulo.findByPrecioVenta3", query = "SELECT a FROM Articulo a WHERE a.precioVenta3 = :precioVenta3")
    , @NamedQuery(name = "Articulo.findByMargenBeneficio1", query = "SELECT a FROM Articulo a WHERE a.margenBeneficio1 = :margenBeneficio1")
    , @NamedQuery(name = "Articulo.findByMargenBeneficio2", query = "SELECT a FROM Articulo a WHERE a.margenBeneficio2 = :margenBeneficio2")
    , @NamedQuery(name = "Articulo.findByMargenBeneficio3", query = "SELECT a FROM Articulo a WHERE a.margenBeneficio3 = :margenBeneficio3")
    , @NamedQuery(name = "Articulo.findByPorcientoUtilidad1", query = "SELECT a FROM Articulo a WHERE a.porcientoUtilidad1 = :porcientoUtilidad1")
    , @NamedQuery(name = "Articulo.findByPorcientoUtilidad2", query = "SELECT a FROM Articulo a WHERE a.porcientoUtilidad2 = :porcientoUtilidad2")
    , @NamedQuery(name = "Articulo.findByPorcientoUtilida3", query = "SELECT a FROM Articulo a WHERE a.porcientoUtilida3 = :porcientoUtilida3")
    , @NamedQuery(name = "Articulo.findByLineaArticulo", query = "SELECT a FROM Articulo a WHERE a.lineaArticulo = :lineaArticulo")
    , @NamedQuery(name = "Articulo.findByModelo", query = "SELECT a FROM Articulo a WHERE a.modelo = :modelo")
    , @NamedQuery(name = "Articulo.findByMarca", query = "SELECT a FROM Articulo a WHERE a.marca = :marca")
    , @NamedQuery(name = "Articulo.findByNombreLinea", query = "SELECT a FROM Articulo a WHERE a.nombreLinea = :nombreLinea")
    , @NamedQuery(name = "Articulo.findByParaVenta", query = "SELECT a FROM Articulo a WHERE a.paraVenta = :paraVenta")
    , @NamedQuery(name = "Articulo.findByParaConsumo", query = "SELECT a FROM Articulo a WHERE a.paraConsumo = :paraConsumo")
    , @NamedQuery(name = "Articulo.findByCantidadUnidades", query = "SELECT a FROM Articulo a WHERE a.cantidadUnidades = :cantidadUnidades")
    , @NamedQuery(name = "Articulo.findByPrecioCompraUnitario", query = "SELECT a FROM Articulo a WHERE a.precioCompraUnitario = :precioCompraUnitario")
    , @NamedQuery(name = "Articulo.findByRutaImg", query = "SELECT a FROM Articulo a WHERE a.rutaImg = :rutaImg")
    , @NamedQuery(name = "Articulo.findByPrecioventa1Itbis", query = "SELECT a FROM Articulo a WHERE a.precioventa1Itbis = :precioventa1Itbis")
    , @NamedQuery(name = "Articulo.findByPrecioventa2Itbis", query = "SELECT a FROM Articulo a WHERE a.precioventa2Itbis = :precioventa2Itbis")
    , @NamedQuery(name = "Articulo.findByPrecioventa3Itbis", query = "SELECT a FROM Articulo a WHERE a.precioventa3Itbis = :precioventa3Itbis")
    , @NamedQuery(name = "Articulo.findByParaDetalle", query = "SELECT a FROM Articulo a WHERE a.paraDetalle = :paraDetalle")
    , @NamedQuery(name = "Articulo.findByEmbase", query = "SELECT a FROM Articulo a WHERE a.embase = :embase")
    , @NamedQuery(name = "Articulo.findByNombreEmbase", query = "SELECT a FROM Articulo a WHERE a.nombreEmbase = :nombreEmbase")
    , @NamedQuery(name = "Articulo.findByCompuesto", query = "SELECT a FROM Articulo a WHERE a.compuesto = :compuesto")
    , @NamedQuery(name = "Articulo.findByNumero", query = "SELECT a FROM Articulo a WHERE a.numero = :numero")
    , @NamedQuery(name = "Articulo.findByNumeroImei", query = "SELECT a FROM Articulo a WHERE a.numeroImei = :numeroImei")
    , @NamedQuery(name = "Articulo.findByNumeroSim", query = "SELECT a FROM Articulo a WHERE a.numeroSim = :numeroSim")
    , @NamedQuery(name = "Articulo.findByTipoServicio", query = "SELECT a FROM Articulo a WHERE a.tipoServicio = :tipoServicio")})
public class Articulo implements Serializable {

    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @Column(name = "itbis_gravado")
    private Double itbisGravado;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "existencia")
    private Double existencia;
    @Column(name = "maximo")
    private Double maximo;
    @Column(name = "minimo")
    private Double minimo;
    @Column(name = "apedir")
    private Double apedir;
    @Column(name = "precio_compra")
    private Double precioCompra;
    @Column(name = "ultimo_suplidor")
    private Integer ultimoSuplidor;
    @Column(name = "ubicacion")
    private String ubicacion;
    @Lob
    @Column(name = "referencia")
    private String referencia;
    @Column(name = "exento_itbis")
    private Boolean exentoItbis;
    @Column(name = "codigo_barra")
    private String codigoBarra;
    @Column(name = "activo")
    private Boolean activo;
    @Lob
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "precio_compra_anterior")
    private Double precioCompraAnterior;
    @Column(name = "precio_venta_anterior")
    private Double precioVentaAnterior;
    @Column(name = "almacen")
    private Integer almacen;
    @Column(name = "inventariable")
    private Boolean inventariable;
    @Column(name = "precio_venta1")
    private Double precioVenta1;
    @Column(name = "precio_venta2")
    private Double precioVenta2;
    @Column(name = "precio_venta3")
    private Double precioVenta3;
    @Column(name = "margen_beneficio1")
    private Double margenBeneficio1;
    @Column(name = "margen_beneficio2")
    private Double margenBeneficio2;
    @Column(name = "margen_beneficio3")
    private Double margenBeneficio3;
    @Column(name = "porciento_utilidad1")
    private Double porcientoUtilidad1;
    @Column(name = "porciento_utilidad2")
    private Double porcientoUtilidad2;
    @Column(name = "porciento_utilida3")
    private Double porcientoUtilida3;
    @Column(name = "linea_articulo")
    private Integer lineaArticulo;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "marca")
    private String marca;
    @Column(name = "nombre_linea")
    private String nombreLinea;
    @Column(name = "para_venta")
    private Boolean paraVenta;
    @Column(name = "para_consumo")
    private Boolean paraConsumo;
    @Column(name = "cantidad_unidades")
    private Double cantidadUnidades;
    @Column(name = "precio_compra_unitario")
    private Double precioCompraUnitario;
    @Column(name = "ruta_img")
    private String rutaImg;
    @Column(name = "precio_venta1Itbis")
    private Double precioventa1Itbis;
    @Column(name = "precio_venta2Itbis")
    private Double precioventa2Itbis;
    @Column(name = "precio_venta3Itbis")
    private Double precioventa3Itbis;
    @Column(name = "para_detalle")
    private Boolean paraDetalle;
    @Column(name = "embase")
    private Integer embase;
    @Column(name = "nombre_embase")
    private String nombreEmbase;
    @Basic(optional = false)
    @Column(name = "compuesto")
    private boolean compuesto;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "numero_imei")
    private String numeroImei;
    @Column(name = "numero_sim")
    private String numeroSim;
    @Column(name = "tipo_servicio")
    private Integer tipoServicio;
    @OneToMany(mappedBy = "articulo")
    private Collection<DetalleFacturaSuplidor> detalleFacturaSuplidorCollection;
    @JoinColumn(name = "unidad_entrada", referencedColumnName = "codigo")
    @ManyToOne
    private Unidad unidadEntrada;
    @JoinColumn(name = "categoria", referencedColumnName = "codigo")
    @ManyToOne
    private Categoria categoria;
    @JoinColumn(name = "sub_categoria", referencedColumnName = "codigo")
    @ManyToOne
    private SubCategoria subCategoria;
    @JoinColumn(name = "tipo_articulo", referencedColumnName = "codigo")
    @ManyToOne
    private TipoArticulo tipoArticulo;
    @JoinColumn(name = "unidad_salida", referencedColumnName = "codigo")
    @ManyToOne
    private Unidad unidadSalida;
    @JoinColumn(name = "calidad_pintura", referencedColumnName = "codigo")
    @ManyToOne
    private CalidadPintura calidadPintura;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "tipo_pintura", referencedColumnName = "codigo")
    @ManyToOne
    private TipoPintura tipoPintura;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private Collection<DetalleAjusteInventario> detalleAjusteInventarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipo")
    private Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private Collection<DetalleReceta> detalleRecetaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private Collection<RegistroDeImei> registroDeImeiCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private Collection<DetalleInventario> detalleInventarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private Collection<DetallePedido> detallePedidoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private Collection<RegistroLote> registroLoteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private Collection<ArticuloUnidad> articuloUnidadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private Collection<DetalleCierreMensualInventario> detalleCierreMensualInventarioCollection;
    @OneToMany(mappedBy = "articulo")
    private Collection<Ajuste> ajusteCollection;
    @OneToMany(mappedBy = "articulo")
    private Collection<DetalleNotaCredito> detalleNotaCreditoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private Collection<DetalleSalidaInventario> detalleSalidaInventarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private Collection<DetalleListaDePrecio> detalleListaDePrecioCollection;
    @OneToMany(mappedBy = "articulo")
    private Collection<DetalleDevolucionDeInventario> detalleDevolucionDeInventarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private Collection<DetalleInventarioFinal> detalleInventarioFinalCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private Collection<ExistenciaArticulo> existenciaArticuloCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private Collection<Receta> recetaCollection;
    @OneToMany(mappedBy = "articulo")
    private Collection<DetalleRecepcionArticulo> detalleRecepcionArticuloCollection;
    @OneToMany(mappedBy = "articulo")
    private Collection<DetalleFactura> detalleFacturaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private Collection<DetalleTransferenciaAlmacen> detalleTransferenciaAlmacenCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private Collection<DetalleEntradaInventario> detalleEntradaInventarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private Collection<DetalleSolicitudSalidaInventario> detalleSolicitudSalidaInventarioCollection;
    @OneToMany(mappedBy = "articulo")
    private Collection<DetalleFacturaTemporal> detalleFacturaTemporalCollection;
    @OneToMany(mappedBy = "articulo")
    private Collection<DetalleCotizacionDeVenta> detalleCotizacionDeVentaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private Collection<DetalleConduce> detalleConduceCollection;
    @OneToMany(mappedBy = "articulo")
    private Collection<DetalleOrdenCompra> detalleOrdenCompraCollection;

    public Articulo() {
    }

    public Articulo(Integer codigo) {
        this.codigo = codigo;
    }

    public Articulo(Integer codigo, boolean compuesto) {
        this.codigo = codigo;
        this.compuesto = compuesto;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getExistencia() {
        return existencia;
    }

    public void setExistencia(Double existencia) {
        this.existencia = existencia;
    }

    public Double getMaximo() {
        return maximo;
    }

    public void setMaximo(Double maximo) {
        this.maximo = maximo;
    }

    public Double getMinimo() {
        return minimo;
    }

    public void setMinimo(Double minimo) {
        this.minimo = minimo;
    }

    public Double getApedir() {
        return apedir;
    }

    public void setApedir(Double apedir) {
        this.apedir = apedir;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Integer getUltimoSuplidor() {
        return ultimoSuplidor;
    }

    public void setUltimoSuplidor(Integer ultimoSuplidor) {
        this.ultimoSuplidor = ultimoSuplidor;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Boolean getExentoItbis() {
        return exentoItbis;
    }

    public void setExentoItbis(Boolean exentoItbis) {
        this.exentoItbis = exentoItbis;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }


    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecioCompraAnterior() {
        return precioCompraAnterior;
    }

    public void setPrecioCompraAnterior(Double precioCompraAnterior) {
        this.precioCompraAnterior = precioCompraAnterior;
    }

    public Double getPrecioVentaAnterior() {
        return precioVentaAnterior;
    }

    public void setPrecioVentaAnterior(Double precioVentaAnterior) {
        this.precioVentaAnterior = precioVentaAnterior;
    }

    public Integer getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Integer almacen) {
        this.almacen = almacen;
    }

    public Boolean getInventariable() {
        return inventariable;
    }

    public void setInventariable(Boolean inventariable) {
        this.inventariable = inventariable;
    }

    public Double getPrecioVenta1() {
        return precioVenta1;
    }

    public void setPrecioVenta1(Double precioVenta1) {
        this.precioVenta1 = precioVenta1;
    }

    public Double getPrecioVenta2() {
        return precioVenta2;
    }

    public void setPrecioVenta2(Double precioVenta2) {
        this.precioVenta2 = precioVenta2;
    }

    public Double getPrecioVenta3() {
        return precioVenta3;
    }

    public void setPrecioVenta3(Double precioVenta3) {
        this.precioVenta3 = precioVenta3;
    }

    public Double getMargenBeneficio1() {
        return margenBeneficio1;
    }

    public void setMargenBeneficio1(Double margenBeneficio1) {
        this.margenBeneficio1 = margenBeneficio1;
    }

    public Double getMargenBeneficio2() {
        return margenBeneficio2;
    }

    public void setMargenBeneficio2(Double margenBeneficio2) {
        this.margenBeneficio2 = margenBeneficio2;
    }

    public Double getMargenBeneficio3() {
        return margenBeneficio3;
    }

    public void setMargenBeneficio3(Double margenBeneficio3) {
        this.margenBeneficio3 = margenBeneficio3;
    }

    public Double getPorcientoUtilidad1() {
        return porcientoUtilidad1;
    }

    public void setPorcientoUtilidad1(Double porcientoUtilidad1) {
        this.porcientoUtilidad1 = porcientoUtilidad1;
    }

    public Double getPorcientoUtilidad2() {
        return porcientoUtilidad2;
    }

    public void setPorcientoUtilidad2(Double porcientoUtilidad2) {
        this.porcientoUtilidad2 = porcientoUtilidad2;
    }

    public Double getPorcientoUtilida3() {
        return porcientoUtilida3;
    }

    public void setPorcientoUtilida3(Double porcientoUtilida3) {
        this.porcientoUtilida3 = porcientoUtilida3;
    }

    public Integer getLineaArticulo() {
        return lineaArticulo;
    }

    public void setLineaArticulo(Integer lineaArticulo) {
        this.lineaArticulo = lineaArticulo;
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

    public String getNombreLinea() {
        return nombreLinea;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    }

    public Boolean getParaVenta() {
        return paraVenta;
    }

    public void setParaVenta(Boolean paraVenta) {
        this.paraVenta = paraVenta;
    }

    public Boolean getParaConsumo() {
        return paraConsumo;
    }

    public void setParaConsumo(Boolean paraConsumo) {
        this.paraConsumo = paraConsumo;
    }

    public Double getCantidadUnidades() {
        return cantidadUnidades;
    }

    public void setCantidadUnidades(Double cantidadUnidades) {
        this.cantidadUnidades = cantidadUnidades;
    }

    public Double getPrecioCompraUnitario() {
        return precioCompraUnitario;
    }

    public void setPrecioCompraUnitario(Double precioCompraUnitario) {
        this.precioCompraUnitario = precioCompraUnitario;
    }

    public String getRutaImg() {
        return rutaImg;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }

    public Double getPrecioventa1Itbis() {
        return precioventa1Itbis;
    }

    public void setPrecioventa1Itbis(Double precioventa1Itbis) {
        this.precioventa1Itbis = precioventa1Itbis;
    }

    public Double getPrecioventa2Itbis() {
        return precioventa2Itbis;
    }

    public void setPrecioventa2Itbis(Double precioventa2Itbis) {
        this.precioventa2Itbis = precioventa2Itbis;
    }

    public Double getPrecioventa3Itbis() {
        return precioventa3Itbis;
    }

    public void setPrecioventa3Itbis(Double precioventa3Itbis) {
        this.precioventa3Itbis = precioventa3Itbis;
    }

    public Boolean getParaDetalle() {
        return paraDetalle;
    }

    public void setParaDetalle(Boolean paraDetalle) {
        this.paraDetalle = paraDetalle;
    }

    public Integer getEmbase() {
        return embase;
    }

    public void setEmbase(Integer embase) {
        this.embase = embase;
    }

    public String getNombreEmbase() {
        return nombreEmbase;
    }

    public void setNombreEmbase(String nombreEmbase) {
        this.nombreEmbase = nombreEmbase;
    }

    public boolean getCompuesto() {
        return compuesto;
    }

    public void setCompuesto(boolean compuesto) {
        this.compuesto = compuesto;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNumeroImei() {
        return numeroImei;
    }

    public void setNumeroImei(String numeroImei) {
        this.numeroImei = numeroImei;
    }

    public String getNumeroSim() {
        return numeroSim;
    }

    public void setNumeroSim(String numeroSim) {
        this.numeroSim = numeroSim;
    }

    public Integer getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(Integer tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    @XmlTransient
    public Collection<DetalleFacturaSuplidor> getDetalleFacturaSuplidorCollection() {
        return detalleFacturaSuplidorCollection;
    }

    public void setDetalleFacturaSuplidorCollection(Collection<DetalleFacturaSuplidor> detalleFacturaSuplidorCollection) {
        this.detalleFacturaSuplidorCollection = detalleFacturaSuplidorCollection;
    }

    public Unidad getUnidadEntrada() {
        return unidadEntrada;
    }

    public void setUnidadEntrada(Unidad unidadEntrada) {
        this.unidadEntrada = unidadEntrada;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public SubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }

    public TipoArticulo getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(TipoArticulo tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    public Unidad getUnidadSalida() {
        return unidadSalida;
    }

    public void setUnidadSalida(Unidad unidadSalida) {
        this.unidadSalida = unidadSalida;
    }

    public CalidadPintura getCalidadPintura() {
        return calidadPintura;
    }

    public void setCalidadPintura(CalidadPintura calidadPintura) {
        this.calidadPintura = calidadPintura;
    }

    public SecuenciaDocumento getSecuenciaDocumento() {
        return secuenciaDocumento;
    }

    public void setSecuenciaDocumento(SecuenciaDocumento secuenciaDocumento) {
        this.secuenciaDocumento = secuenciaDocumento;
    }

    public TipoPintura getTipoPintura() {
        return tipoPintura;
    }

    public void setTipoPintura(TipoPintura tipoPintura) {
        this.tipoPintura = tipoPintura;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    @XmlTransient
    public Collection<DetalleAjusteInventario> getDetalleAjusteInventarioCollection() {
        return detalleAjusteInventarioCollection;
    }

    public void setDetalleAjusteInventarioCollection(Collection<DetalleAjusteInventario> detalleAjusteInventarioCollection) {
        this.detalleAjusteInventarioCollection = detalleAjusteInventarioCollection;
    }

    @XmlTransient
    public Collection<DetalleContratoDeServicio> getDetalleContratoDeServicioCollection() {
        return detalleContratoDeServicioCollection;
    }

    public void setDetalleContratoDeServicioCollection(Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection) {
        this.detalleContratoDeServicioCollection = detalleContratoDeServicioCollection;
    }

    @XmlTransient
    public Collection<DetalleReceta> getDetalleRecetaCollection() {
        return detalleRecetaCollection;
    }

    public void setDetalleRecetaCollection(Collection<DetalleReceta> detalleRecetaCollection) {
        this.detalleRecetaCollection = detalleRecetaCollection;
    }

    @XmlTransient
    public Collection<RegistroDeImei> getRegistroDeImeiCollection() {
        return registroDeImeiCollection;
    }

    public void setRegistroDeImeiCollection(Collection<RegistroDeImei> registroDeImeiCollection) {
        this.registroDeImeiCollection = registroDeImeiCollection;
    }

    @XmlTransient
    public Collection<DetalleInventario> getDetalleInventarioCollection() {
        return detalleInventarioCollection;
    }

    public void setDetalleInventarioCollection(Collection<DetalleInventario> detalleInventarioCollection) {
        this.detalleInventarioCollection = detalleInventarioCollection;
    }

    @XmlTransient
    public Collection<DetallePedido> getDetallePedidoCollection() {
        return detallePedidoCollection;
    }

    public void setDetallePedidoCollection(Collection<DetallePedido> detallePedidoCollection) {
        this.detallePedidoCollection = detallePedidoCollection;
    }

    @XmlTransient
    public Collection<RegistroLote> getRegistroLoteCollection() {
        return registroLoteCollection;
    }

    public void setRegistroLoteCollection(Collection<RegistroLote> registroLoteCollection) {
        this.registroLoteCollection = registroLoteCollection;
    }

    @XmlTransient
    public Collection<ArticuloUnidad> getArticuloUnidadCollection() {
        return articuloUnidadCollection;
    }

    public void setArticuloUnidadCollection(Collection<ArticuloUnidad> articuloUnidadCollection) {
        this.articuloUnidadCollection = articuloUnidadCollection;
    }

    @XmlTransient
    public Collection<DetalleCierreMensualInventario> getDetalleCierreMensualInventarioCollection() {
        return detalleCierreMensualInventarioCollection;
    }

    public void setDetalleCierreMensualInventarioCollection(Collection<DetalleCierreMensualInventario> detalleCierreMensualInventarioCollection) {
        this.detalleCierreMensualInventarioCollection = detalleCierreMensualInventarioCollection;
    }

    @XmlTransient
    public Collection<Ajuste> getAjusteCollection() {
        return ajusteCollection;
    }

    public void setAjusteCollection(Collection<Ajuste> ajusteCollection) {
        this.ajusteCollection = ajusteCollection;
    }

    @XmlTransient
    public Collection<DetalleNotaCredito> getDetalleNotaCreditoCollection() {
        return detalleNotaCreditoCollection;
    }

    public void setDetalleNotaCreditoCollection(Collection<DetalleNotaCredito> detalleNotaCreditoCollection) {
        this.detalleNotaCreditoCollection = detalleNotaCreditoCollection;
    }

    @XmlTransient
    public Collection<DetalleSalidaInventario> getDetalleSalidaInventarioCollection() {
        return detalleSalidaInventarioCollection;
    }

    public void setDetalleSalidaInventarioCollection(Collection<DetalleSalidaInventario> detalleSalidaInventarioCollection) {
        this.detalleSalidaInventarioCollection = detalleSalidaInventarioCollection;
    }

    @XmlTransient
    public Collection<DetalleListaDePrecio> getDetalleListaDePrecioCollection() {
        return detalleListaDePrecioCollection;
    }

    public void setDetalleListaDePrecioCollection(Collection<DetalleListaDePrecio> detalleListaDePrecioCollection) {
        this.detalleListaDePrecioCollection = detalleListaDePrecioCollection;
    }

    @XmlTransient
    public Collection<DetalleDevolucionDeInventario> getDetalleDevolucionDeInventarioCollection() {
        return detalleDevolucionDeInventarioCollection;
    }

    public void setDetalleDevolucionDeInventarioCollection(Collection<DetalleDevolucionDeInventario> detalleDevolucionDeInventarioCollection) {
        this.detalleDevolucionDeInventarioCollection = detalleDevolucionDeInventarioCollection;
    }

    @XmlTransient
    public Collection<DetalleInventarioFinal> getDetalleInventarioFinalCollection() {
        return detalleInventarioFinalCollection;
    }

    public void setDetalleInventarioFinalCollection(Collection<DetalleInventarioFinal> detalleInventarioFinalCollection) {
        this.detalleInventarioFinalCollection = detalleInventarioFinalCollection;
    }

    @XmlTransient
    public Collection<ExistenciaArticulo> getExistenciaArticuloCollection() {
        return existenciaArticuloCollection;
    }

    public void setExistenciaArticuloCollection(Collection<ExistenciaArticulo> existenciaArticuloCollection) {
        this.existenciaArticuloCollection = existenciaArticuloCollection;
    }

    @XmlTransient
    public Collection<Receta> getRecetaCollection() {
        return recetaCollection;
    }

    public void setRecetaCollection(Collection<Receta> recetaCollection) {
        this.recetaCollection = recetaCollection;
    }

    @XmlTransient
    public Collection<DetalleRecepcionArticulo> getDetalleRecepcionArticuloCollection() {
        return detalleRecepcionArticuloCollection;
    }

    public void setDetalleRecepcionArticuloCollection(Collection<DetalleRecepcionArticulo> detalleRecepcionArticuloCollection) {
        this.detalleRecepcionArticuloCollection = detalleRecepcionArticuloCollection;
    }

    @XmlTransient
    public Collection<DetalleFactura> getDetalleFacturaCollection() {
        return detalleFacturaCollection;
    }

    public void setDetalleFacturaCollection(Collection<DetalleFactura> detalleFacturaCollection) {
        this.detalleFacturaCollection = detalleFacturaCollection;
    }

    @XmlTransient
    public Collection<DetalleTransferenciaAlmacen> getDetalleTransferenciaAlmacenCollection() {
        return detalleTransferenciaAlmacenCollection;
    }

    public void setDetalleTransferenciaAlmacenCollection(Collection<DetalleTransferenciaAlmacen> detalleTransferenciaAlmacenCollection) {
        this.detalleTransferenciaAlmacenCollection = detalleTransferenciaAlmacenCollection;
    }

    @XmlTransient
    public Collection<DetalleEntradaInventario> getDetalleEntradaInventarioCollection() {
        return detalleEntradaInventarioCollection;
    }

    public void setDetalleEntradaInventarioCollection(Collection<DetalleEntradaInventario> detalleEntradaInventarioCollection) {
        this.detalleEntradaInventarioCollection = detalleEntradaInventarioCollection;
    }

    @XmlTransient
    public Collection<DetalleSolicitudSalidaInventario> getDetalleSolicitudSalidaInventarioCollection() {
        return detalleSolicitudSalidaInventarioCollection;
    }

    public void setDetalleSolicitudSalidaInventarioCollection(Collection<DetalleSolicitudSalidaInventario> detalleSolicitudSalidaInventarioCollection) {
        this.detalleSolicitudSalidaInventarioCollection = detalleSolicitudSalidaInventarioCollection;
    }

    @XmlTransient
    public Collection<DetalleFacturaTemporal> getDetalleFacturaTemporalCollection() {
        return detalleFacturaTemporalCollection;
    }

    public void setDetalleFacturaTemporalCollection(Collection<DetalleFacturaTemporal> detalleFacturaTemporalCollection) {
        this.detalleFacturaTemporalCollection = detalleFacturaTemporalCollection;
    }

    @XmlTransient
    public Collection<DetalleCotizacionDeVenta> getDetalleCotizacionDeVentaCollection() {
        return detalleCotizacionDeVentaCollection;
    }

    public void setDetalleCotizacionDeVentaCollection(Collection<DetalleCotizacionDeVenta> detalleCotizacionDeVentaCollection) {
        this.detalleCotizacionDeVentaCollection = detalleCotizacionDeVentaCollection;
    }

    @XmlTransient
    public Collection<DetalleConduce> getDetalleConduceCollection() {
        return detalleConduceCollection;
    }

    public void setDetalleConduceCollection(Collection<DetalleConduce> detalleConduceCollection) {
        this.detalleConduceCollection = detalleConduceCollection;
    }

    @XmlTransient
    public Collection<DetalleOrdenCompra> getDetalleOrdenCompraCollection() {
        return detalleOrdenCompraCollection;
    }

    public void setDetalleOrdenCompraCollection(Collection<DetalleOrdenCompra> detalleOrdenCompraCollection) {
        this.detalleOrdenCompraCollection = detalleOrdenCompraCollection;
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
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Articulo[ codigo=" + codigo + " ]";
    }


    public Double getItbisGravado() {
        return itbisGravado;
    }

    public void setItbisGravado(Double itbisGravado) {
        this.itbisGravado = itbisGravado;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
}
