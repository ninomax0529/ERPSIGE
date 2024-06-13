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
@Table(name = "recepcion_articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecepcionArticulo.findAll", query = "SELECT r FROM RecepcionArticulo r")
    , @NamedQuery(name = "RecepcionArticulo.findByCodigo", query = "SELECT r FROM RecepcionArticulo r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "RecepcionArticulo.findByFecha", query = "SELECT r FROM RecepcionArticulo r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "RecepcionArticulo.findByNoFactura", query = "SELECT r FROM RecepcionArticulo r WHERE r.noFactura = :noFactura")
    , @NamedQuery(name = "RecepcionArticulo.findByNcf", query = "SELECT r FROM RecepcionArticulo r WHERE r.ncf = :ncf")
    , @NamedQuery(name = "RecepcionArticulo.findByValorFactura", query = "SELECT r FROM RecepcionArticulo r WHERE r.valorFactura = :valorFactura")
    , @NamedQuery(name = "RecepcionArticulo.findByFechaVencimiento", query = "SELECT r FROM RecepcionArticulo r WHERE r.fechaVencimiento = :fechaVencimiento")
    , @NamedQuery(name = "RecepcionArticulo.findByValorAbonado", query = "SELECT r FROM RecepcionArticulo r WHERE r.valorAbonado = :valorAbonado")
    , @NamedQuery(name = "RecepcionArticulo.findByValorPendiente", query = "SELECT r FROM RecepcionArticulo r WHERE r.valorPendiente = :valorPendiente")
    , @NamedQuery(name = "RecepcionArticulo.findByFechaFactura", query = "SELECT r FROM RecepcionArticulo r WHERE r.fechaFactura = :fechaFactura")})
public class RecepcionArticulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "no_factura")
    private String noFactura;
    @Column(name = "ncf")
    private String ncf;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_factura")
    private Double valorFactura;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Column(name = "valor_abonado")
    private Double valorAbonado;
    @Column(name = "valor_pendiente")
    private Double valorPendiente;
    @Column(name = "fecha_factura")
    @Temporal(TemporalType.DATE)
    private Date fechaFactura;
    @JoinColumn(name = "codicion_pago", referencedColumnName = "codigo")
    @ManyToOne
    private CondicionPago codicionPago;
    @JoinColumn(name = "plazo", referencedColumnName = "codigo")
    @ManyToOne
    private Plazo plazo;
    @JoinColumn(name = "suplidor", referencedColumnName = "codigo")
    @ManyToOne
    private Suplidor suplidor;
    @OneToMany(mappedBy = "recepcionArticulo",cascade = CascadeType.ALL)
    private Collection<DetalleRecepcionArticulo> detalleRecepcionArticuloCollection;

    public RecepcionArticulo() {
    }

    public RecepcionArticulo(Integer codigo) {
        this.codigo = codigo;
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

    public String getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(String noFactura) {
        this.noFactura = noFactura;
    }

    public String getNcf() {
        return ncf;
    }

    public void setNcf(String ncf) {
        this.ncf = ncf;
    }

    public Double getValorFactura() {
        return valorFactura;
    }

    public void setValorFactura(Double valorFactura) {
        this.valorFactura = valorFactura;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Double getValorAbonado() {
        return valorAbonado;
    }

    public void setValorAbonado(Double valorAbonado) {
        this.valorAbonado = valorAbonado;
    }

    public Double getValorPendiente() {
        return valorPendiente;
    }

    public void setValorPendiente(Double valorPendiente) {
        this.valorPendiente = valorPendiente;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public CondicionPago getCodicionPago() {
        return codicionPago;
    }

    public void setCodicionPago(CondicionPago codicionPago) {
        this.codicionPago = codicionPago;
    }

    public Plazo getPlazo() {
        return plazo;
    }

    public void setPlazo(Plazo plazo) {
        this.plazo = plazo;
    }

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
    }

    @XmlTransient
    public Collection<DetalleRecepcionArticulo> getDetalleRecepcionArticuloCollection() {
        return detalleRecepcionArticuloCollection;
    }

    public void setDetalleRecepcionArticuloCollection(Collection<DetalleRecepcionArticulo> detalleRecepcionArticuloCollection) {
        this.detalleRecepcionArticuloCollection = detalleRecepcionArticuloCollection;
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
        if (!(object instanceof RecepcionArticulo)) {
            return false;
        }
        RecepcionArticulo other = (RecepcionArticulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RecepcionArticulo[ codigo=" + codigo + " ]";
    }
    
}
