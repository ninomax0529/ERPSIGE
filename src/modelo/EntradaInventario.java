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
@Table(name = "entrada_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntradaInventario.findAll", query = "SELECT e FROM EntradaInventario e")
    , @NamedQuery(name = "EntradaInventario.findByCodigo", query = "SELECT e FROM EntradaInventario e WHERE e.codigo = :codigo")
    , @NamedQuery(name = "EntradaInventario.findByFecha", query = "SELECT e FROM EntradaInventario e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "EntradaInventario.findByFechaRegistro", query = "SELECT e FROM EntradaInventario e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "EntradaInventario.findByComentario", query = "SELECT e FROM EntradaInventario e WHERE e.comentario = :comentario")
    , @NamedQuery(name = "EntradaInventario.findByEstado", query = "SELECT e FROM EntradaInventario e WHERE e.estado = :estado")
    , @NamedQuery(name = "EntradaInventario.findByFechaContabilizacion", query = "SELECT e FROM EntradaInventario e WHERE e.fechaContabilizacion = :fechaContabilizacion")
    , @NamedQuery(name = "EntradaInventario.findByMoneda", query = "SELECT e FROM EntradaInventario e WHERE e.moneda = :moneda")
    , @NamedQuery(name = "EntradaInventario.findByAnulada", query = "SELECT e FROM EntradaInventario e WHERE e.anulada = :anulada")
    , @NamedQuery(name = "EntradaInventario.findByFechaAnulada", query = "SELECT e FROM EntradaInventario e WHERE e.fechaAnulada = :fechaAnulada")
    , @NamedQuery(name = "EntradaInventario.findByUsuario", query = "SELECT e FROM EntradaInventario e WHERE e.usuario = :usuario")
    , @NamedQuery(name = "EntradaInventario.findByAnuladaPor", query = "SELECT e FROM EntradaInventario e WHERE e.anuladaPor = :anuladaPor")
    , @NamedQuery(name = "EntradaInventario.findByAlmacen", query = "SELECT e FROM EntradaInventario e WHERE e.almacen = :almacen")
    , @NamedQuery(name = "EntradaInventario.findByNumeroFactura", query = "SELECT e FROM EntradaInventario e WHERE e.numeroFactura = :numeroFactura")
    , @NamedQuery(name = "EntradaInventario.findByNombreSuplidor", query = "SELECT e FROM EntradaInventario e WHERE e.nombreSuplidor = :nombreSuplidor")
    , @NamedQuery(name = "EntradaInventario.findByNumero", query = "SELECT e FROM EntradaInventario e WHERE e.numero = :numero")})
public class EntradaInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_contabilizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaContabilizacion;
    @Column(name = "moneda")
    private Integer moneda;
    @Basic(optional = false)
    @Column(name = "anulada")
    private boolean anulada;
    @Column(name = "fecha_anulada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulada;
    @Column(name = "usuario")
    private Integer usuario;
    @Column(name = "anulada_por")
    private Integer anuladaPor;
    @Column(name = "almacen")
    private Integer almacen;
    @Column(name = "numero_factura")
    private String numeroFactura;
    @Column(name = "nombre_suplidor")
    private String nombreSuplidor;
    @Column(name = "numero")
    private Integer numero;
    @JoinColumn(name = "tipo_entrada", referencedColumnName = "codigo")
    @ManyToOne
    private TipoEntrada tipoEntrada;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "orden_compra", referencedColumnName = "codigo")
    @ManyToOne
    private OrdenCompra ordenCompra;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "suplidor", referencedColumnName = "codigo")
    @ManyToOne
    private Suplidor suplidor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entradaInventario",orphanRemoval = true)
    private Collection<DetalleEntradaInventario> detalleEntradaInventarioCollection;

    public EntradaInventario() {
    }

    public EntradaInventario(Integer codigo) {
        this.codigo = codigo;
    }

    public EntradaInventario(Integer codigo, boolean anulada) {
        this.codigo = codigo;
        this.anulada = anulada;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaContabilizacion() {
        return fechaContabilizacion;
    }

    public void setFechaContabilizacion(Date fechaContabilizacion) {
        this.fechaContabilizacion = fechaContabilizacion;
    }

    public Integer getMoneda() {
        return moneda;
    }

    public void setMoneda(Integer moneda) {
        this.moneda = moneda;
    }

    public boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public Date getFechaAnulada() {
        return fechaAnulada;
    }

    public void setFechaAnulada(Date fechaAnulada) {
        this.fechaAnulada = fechaAnulada;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getAnuladaPor() {
        return anuladaPor;
    }

    public void setAnuladaPor(Integer anuladaPor) {
        this.anuladaPor = anuladaPor;
    }

    public Integer getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Integer almacen) {
        this.almacen = almacen;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getNombreSuplidor() {
        return nombreSuplidor;
    }

    public void setNombreSuplidor(String nombreSuplidor) {
        this.nombreSuplidor = nombreSuplidor;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public TipoEntrada getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(TipoEntrada tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public SecuenciaDocumento getSecuenciaDocumento() {
        return secuenciaDocumento;
    }

    public void setSecuenciaDocumento(SecuenciaDocumento secuenciaDocumento) {
        this.secuenciaDocumento = secuenciaDocumento;
    }

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
    }

    @XmlTransient
    public Collection<DetalleEntradaInventario> getDetalleEntradaInventarioCollection() {
        return detalleEntradaInventarioCollection;
    }

    public void setDetalleEntradaInventarioCollection(Collection<DetalleEntradaInventario> detalleEntradaInventarioCollection) {
        this.detalleEntradaInventarioCollection = detalleEntradaInventarioCollection;
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
        if (!(object instanceof EntradaInventario)) {
            return false;
        }
        EntradaInventario other = (EntradaInventario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.EntradaInventario[ codigo=" + codigo + " ]";
    }
    
}
