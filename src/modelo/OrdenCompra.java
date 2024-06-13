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
@Table(name = "orden_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenCompra.findAll", query = "SELECT o FROM OrdenCompra o")
    , @NamedQuery(name = "OrdenCompra.findByCodigo", query = "SELECT o FROM OrdenCompra o WHERE o.codigo = :codigo")
    , @NamedQuery(name = "OrdenCompra.findByFechaRegistro", query = "SELECT o FROM OrdenCompra o WHERE o.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "OrdenCompra.findBySuplidor", query = "SELECT o FROM OrdenCompra o WHERE o.suplidor = :suplidor")
    , @NamedQuery(name = "OrdenCompra.findByNombreSuplidor", query = "SELECT o FROM OrdenCompra o WHERE o.nombreSuplidor = :nombreSuplidor")
    , @NamedQuery(name = "OrdenCompra.findByRnc", query = "SELECT o FROM OrdenCompra o WHERE o.rnc = :rnc")
    , @NamedQuery(name = "OrdenCompra.findByComentario", query = "SELECT o FROM OrdenCompra o WHERE o.comentario = :comentario")
    , @NamedQuery(name = "OrdenCompra.findBySubtotal", query = "SELECT o FROM OrdenCompra o WHERE o.subtotal = :subtotal")
    , @NamedQuery(name = "OrdenCompra.findByAnulada", query = "SELECT o FROM OrdenCompra o WHERE o.anulada = :anulada")
    , @NamedQuery(name = "OrdenCompra.findByEstado", query = "SELECT o FROM OrdenCompra o WHERE o.estado = :estado")
    , @NamedQuery(name = "OrdenCompra.findByUsuario", query = "SELECT o FROM OrdenCompra o WHERE o.usuario = :usuario")
    , @NamedQuery(name = "OrdenCompra.findByFecha", query = "SELECT o FROM OrdenCompra o WHERE o.fecha = :fecha")
    , @NamedQuery(name = "OrdenCompra.findByAutorizada", query = "SELECT o FROM OrdenCompra o WHERE o.autorizada = :autorizada")
    , @NamedQuery(name = "OrdenCompra.findByFechaAutorizacion", query = "SELECT o FROM OrdenCompra o WHERE o.fechaAutorizacion = :fechaAutorizacion")
    , @NamedQuery(name = "OrdenCompra.findByFechaAnulada", query = "SELECT o FROM OrdenCompra o WHERE o.fechaAnulada = :fechaAnulada")
    , @NamedQuery(name = "OrdenCompra.findByAutorizador", query = "SELECT o FROM OrdenCompra o WHERE o.autorizador = :autorizador")
    , @NamedQuery(name = "OrdenCompra.findByNombreAutorizador", query = "SELECT o FROM OrdenCompra o WHERE o.nombreAutorizador = :nombreAutorizador")
    , @NamedQuery(name = "OrdenCompra.findByCompletada", query = "SELECT o FROM OrdenCompra o WHERE o.completada = :completada")
    , @NamedQuery(name = "OrdenCompra.findBySubTotal", query = "SELECT o FROM OrdenCompra o WHERE o.subTotal = :subTotal")
    , @NamedQuery(name = "OrdenCompra.findByTotalDescuento", query = "SELECT o FROM OrdenCompra o WHERE o.totalDescuento = :totalDescuento")
    , @NamedQuery(name = "OrdenCompra.findBySubTotalConDescuento", query = "SELECT o FROM OrdenCompra o WHERE o.subTotalConDescuento = :subTotalConDescuento")
    , @NamedQuery(name = "OrdenCompra.findByTotalItbis", query = "SELECT o FROM OrdenCompra o WHERE o.totalItbis = :totalItbis")
    , @NamedQuery(name = "OrdenCompra.findByTotalIsr", query = "SELECT o FROM OrdenCompra o WHERE o.totalIsr = :totalIsr")
    , @NamedQuery(name = "OrdenCompra.findByTotalAPagar", query = "SELECT o FROM OrdenCompra o WHERE o.totalAPagar = :totalAPagar")
    , @NamedQuery(name = "OrdenCompra.findByTotal", query = "SELECT o FROM OrdenCompra o WHERE o.total = :total")
    , @NamedQuery(name = "OrdenCompra.findByFacturada", query = "SELECT o FROM OrdenCompra o WHERE o.facturada = :facturada")
    , @NamedQuery(name = "OrdenCompra.findByFechaFacturada", query = "SELECT o FROM OrdenCompra o WHERE o.fechaFacturada = :fechaFacturada")
    , @NamedQuery(name = "OrdenCompra.findByNumero", query = "SELECT o FROM OrdenCompra o WHERE o.numero = :numero")})
public class OrdenCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "suplidor")
    private Integer suplidor;
    @Column(name = "nombre_suplidor")
    private String nombreSuplidor;
    @Column(name = "rnc")
    private String rnc;
    @Column(name = "comentario")
    private String comentario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "anulada")
    private Boolean anulada;
    @Column(name = "estado")
    private String estado;
    @Column(name = "usuario")
    private Integer usuario;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "autorizada")
    private Boolean autorizada;
    @Column(name = "fecha_autorizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAutorizacion;
    @Column(name = "fecha_anulada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulada;
    @Column(name = "autorizador")
    private Integer autorizador;
    @Column(name = "nombre_autorizador")
    private String nombreAutorizador;
    @Column(name = "completada")
    private String completada;
    @Column(name = "sub_total")
    private Double subTotal;
    @Column(name = "total_descuento")
    private Double totalDescuento;
    @Column(name = "sub_total_con_descuento")
    private Double subTotalConDescuento;
    @Column(name = "total_itbis")
    private Double totalItbis;
    @Column(name = "total_isr")
    private Double totalIsr;
    @Column(name = "total_a_pagar")
    private Double totalAPagar;
    @Column(name = "total")
    private Double total;
    @Column(name = "facturada")
    private Boolean facturada;
    @Column(name = "fecha_facturada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFacturada;
    @Column(name = "numero")
    private Integer numero;
    @OneToMany(mappedBy = "ordenCompra")
    private Collection<EntradaInventario> entradaInventarioCollection;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "moneda", referencedColumnName = "codigo")
    @ManyToOne
    private Moneda moneda;
    @JoinColumn(name = "plazo", referencedColumnName = "codigo")
    @ManyToOne
    private Plazo plazo;
    @JoinColumn(name = "tiempo_entrega", referencedColumnName = "codigo")
    @ManyToOne
    private TiempoEntrega tiempoEntrega;
    @OneToMany(mappedBy = "ordenCompra")
    private Collection<DetalleEntradaInventario> detalleEntradaInventarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenCompra")
    private Collection<DetalleOrdenCompra> detalleOrdenCompraCollection;

    public OrdenCompra() {
    }

    public OrdenCompra(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Integer suplidor) {
        this.suplidor = suplidor;
    }

    public String getNombreSuplidor() {
        return nombreSuplidor;
    }

    public void setNombreSuplidor(String nombreSuplidor) {
        this.nombreSuplidor = nombreSuplidor;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(Boolean anulada) {
        this.anulada = anulada;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getAutorizada() {
        return autorizada;
    }

    public void setAutorizada(Boolean autorizada) {
        this.autorizada = autorizada;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public Date getFechaAnulada() {
        return fechaAnulada;
    }

    public void setFechaAnulada(Date fechaAnulada) {
        this.fechaAnulada = fechaAnulada;
    }

    public Integer getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(Integer autorizador) {
        this.autorizador = autorizador;
    }

    public String getNombreAutorizador() {
        return nombreAutorizador;
    }

    public void setNombreAutorizador(String nombreAutorizador) {
        this.nombreAutorizador = nombreAutorizador;
    }

    public String getCompletada() {
        return completada;
    }

    public void setCompletada(String completada) {
        this.completada = completada;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(Double totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public Double getSubTotalConDescuento() {
        return subTotalConDescuento;
    }

    public void setSubTotalConDescuento(Double subTotalConDescuento) {
        this.subTotalConDescuento = subTotalConDescuento;
    }

    public Double getTotalItbis() {
        return totalItbis;
    }

    public void setTotalItbis(Double totalItbis) {
        this.totalItbis = totalItbis;
    }

    public Double getTotalIsr() {
        return totalIsr;
    }

    public void setTotalIsr(Double totalIsr) {
        this.totalIsr = totalIsr;
    }

    public Double getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(Double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getFacturada() {
        return facturada;
    }

    public void setFacturada(Boolean facturada) {
        this.facturada = facturada;
    }

    public Date getFechaFacturada() {
        return fechaFacturada;
    }

    public void setFechaFacturada(Date fechaFacturada) {
        this.fechaFacturada = fechaFacturada;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @XmlTransient
    public Collection<EntradaInventario> getEntradaInventarioCollection() {
        return entradaInventarioCollection;
    }

    public void setEntradaInventarioCollection(Collection<EntradaInventario> entradaInventarioCollection) {
        this.entradaInventarioCollection = entradaInventarioCollection;
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

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Plazo getPlazo() {
        return plazo;
    }

    public void setPlazo(Plazo plazo) {
        this.plazo = plazo;
    }

    public TiempoEntrega getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(TiempoEntrega tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    @XmlTransient
    public Collection<DetalleEntradaInventario> getDetalleEntradaInventarioCollection() {
        return detalleEntradaInventarioCollection;
    }

    public void setDetalleEntradaInventarioCollection(Collection<DetalleEntradaInventario> detalleEntradaInventarioCollection) {
        this.detalleEntradaInventarioCollection = detalleEntradaInventarioCollection;
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
        if (!(object instanceof OrdenCompra)) {
            return false;
        }
        OrdenCompra other = (OrdenCompra) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.OrdenCompra[ codigo=" + codigo + " ]";
    }
    
}
