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
@Table(name = "conduce")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conduce.findAll", query = "SELECT c FROM Conduce c")
    , @NamedQuery(name = "Conduce.findByCodigo", query = "SELECT c FROM Conduce c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Conduce.findByNumero", query = "SELECT c FROM Conduce c WHERE c.numero = :numero")
    , @NamedQuery(name = "Conduce.findBySecuenciaDocumento", query = "SELECT c FROM Conduce c WHERE c.secuenciaDocumento = :secuenciaDocumento")
    , @NamedQuery(name = "Conduce.findByFecha", query = "SELECT c FROM Conduce c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "Conduce.findByNombreCliente", query = "SELECT c FROM Conduce c WHERE c.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "Conduce.findByAnulado", query = "SELECT c FROM Conduce c WHERE c.anulado = :anulado")
    , @NamedQuery(name = "Conduce.findByFechaCreacion", query = "SELECT c FROM Conduce c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Conduce.findByFechaAnulado", query = "SELECT c FROM Conduce c WHERE c.fechaAnulado = :fechaAnulado")
    , @NamedQuery(name = "Conduce.findByUnidadDeNegocio", query = "SELECT c FROM Conduce c WHERE c.unidadDeNegocio = :unidadDeNegocio")})
public class Conduce implements Serializable {

    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "secuencia_documento")
    private Integer secuenciaDocumento;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Column(name = "anulado")
    private Boolean anulado;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_anulado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulado;
    @Lob
    @Column(name = "destino")
    private String destino; 
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @JoinColumn(name = "chofer", referencedColumnName = "codigo")
    @ManyToOne
    private Chofer chofer;
    @JoinColumn(name = "cliente", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "factura", referencedColumnName = "codigo")
    @ManyToOne
    private Factura factura;
    @JoinColumn(name = "pedido", referencedColumnName = "codigo")
    @ManyToOne
    private Pedido pedido;
    @JoinColumn(name = "transporte", referencedColumnName = "codigo")
    @ManyToOne
    private Transporte transporte;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "vehiculo", referencedColumnName = "codigo")
    @ManyToOne
    private Vehiculo vehiculo;
    @JoinColumn(name = "vendedor", referencedColumnName = "codigo")
    @ManyToOne
    private EjecutivoDeVenta vendedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conduce")
    private Collection<DetalleConduce> detalleConduceCollection;

    public Conduce() {
    }

    public Conduce(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getSecuenciaDocumento() {
        return secuenciaDocumento;
    }

    public void setSecuenciaDocumento(Integer secuenciaDocumento) {
        this.secuenciaDocumento = secuenciaDocumento;
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

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaAnulado() {
        return fechaAnulado;
    }

    public void setFechaAnulado(Date fechaAnulado) {
        this.fechaAnulado = fechaAnulado;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public EjecutivoDeVenta getVendedor() {
        return vendedor;
    }

    public void setVendedor(EjecutivoDeVenta vendedor) {
        this.vendedor = vendedor;
    }

    @XmlTransient
    public Collection<DetalleConduce> getDetalleConduceCollection() {
        return detalleConduceCollection;
    }

    public void setDetalleConduceCollection(Collection<DetalleConduce> detalleConduceCollection) {
        this.detalleConduceCollection = detalleConduceCollection;
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
        if (!(object instanceof Conduce)) {
            return false;
        }
        Conduce other = (Conduce) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Conduce[ codigo=" + codigo + " ]";
    }

    
}
