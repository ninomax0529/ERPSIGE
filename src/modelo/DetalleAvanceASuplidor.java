/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "detalle_avance_a_suplidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleAvanceASuplidor.findAll", query = "SELECT d FROM DetalleAvanceASuplidor d")
    , @NamedQuery(name = "DetalleAvanceASuplidor.findByCodigo", query = "SELECT d FROM DetalleAvanceASuplidor d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleAvanceASuplidor.findByFecha", query = "SELECT d FROM DetalleAvanceASuplidor d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "DetalleAvanceASuplidor.findByComprobanteDePago", query = "SELECT d FROM DetalleAvanceASuplidor d WHERE d.comprobanteDePago = :comprobanteDePago")
    , @NamedQuery(name = "DetalleAvanceASuplidor.findByAvance", query = "SELECT d FROM DetalleAvanceASuplidor d WHERE d.avance = :avance")
    , @NamedQuery(name = "DetalleAvanceASuplidor.findByPendiente", query = "SELECT d FROM DetalleAvanceASuplidor d WHERE d.pendiente = :pendiente")
    , @NamedQuery(name = "DetalleAvanceASuplidor.findByAnulado", query = "SELECT d FROM DetalleAvanceASuplidor d WHERE d.anulado = :anulado")})
public class DetalleAvanceASuplidor implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "debito")
    private Double debito;
    @Column(name = "credito")
    private Double credito;

    @JoinColumn(name = "comprobante_de_pago", referencedColumnName = "codigo")
    @ManyToOne
    private ComprobanteDePago comprobanteDePago;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "avance")
    private double avance;
    @Basic(optional = false)
    @Column(name = "pendiente")
    private double pendiente;
    @Basic(optional = false)
    @Column(name = "anulado")
    private boolean anulado;
    @JoinColumn(name = "avance_a_suplidor", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private AvanceASuplidor avanceASuplidor;

    public DetalleAvanceASuplidor() {
    }

    public DetalleAvanceASuplidor(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleAvanceASuplidor(Integer codigo, Date fecha, double avance, double pendiente, boolean anulado) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.avance = avance;
        this.pendiente = pendiente;
        this.anulado = anulado;
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


    public double getAvance() {
        return avance;
    }

    public void setAvance(double avance) {
        this.avance = avance;
    }

    public double getPendiente() {
        return pendiente;
    }

    public void setPendiente(double pendiente) {
        this.pendiente = pendiente;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public AvanceASuplidor getAvanceASuplidor() {
        return avanceASuplidor;
    }

    public void setAvanceASuplidor(AvanceASuplidor avanceASuplidor) {
        this.avanceASuplidor = avanceASuplidor;
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
        if (!(object instanceof DetalleAvanceASuplidor)) {
            return false;
        }
        DetalleAvanceASuplidor other = (DetalleAvanceASuplidor) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleAvanceASuplidor[ codigo=" + codigo + " ]";
    }

    public ComprobanteDePago getComprobanteDePago() {
        return comprobanteDePago;
    }

    public void setComprobanteDePago(ComprobanteDePago comprobanteDePago) {
        this.comprobanteDePago = comprobanteDePago;
    }

    public Double getDebito() {
        return debito;
    }

    public void setDebito(Double debito) {
        this.debito = debito;
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }

}
