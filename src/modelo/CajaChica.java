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
@Table(name = "caja_chica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CajaChica.findAll", query = "SELECT c FROM CajaChica c")
    , @NamedQuery(name = "CajaChica.findByCodigo", query = "SELECT c FROM CajaChica c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "CajaChica.findByFechaRegistro", query = "SELECT c FROM CajaChica c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "CajaChica.findByFechaApertura", query = "SELECT c FROM CajaChica c WHERE c.fechaApertura = :fechaApertura")
    , @NamedQuery(name = "CajaChica.findByFechaCierre", query = "SELECT c FROM CajaChica c WHERE c.fechaCierre = :fechaCierre")
    , @NamedQuery(name = "CajaChica.findBySaldoInicial", query = "SELECT c FROM CajaChica c WHERE c.saldoInicial = :saldoInicial")
    , @NamedQuery(name = "CajaChica.findBySaldoFinal", query = "SELECT c FROM CajaChica c WHERE c.saldoFinal = :saldoFinal")
    , @NamedQuery(name = "CajaChica.findByEstado", query = "SELECT c FROM CajaChica c WHERE c.estado = :estado")
    , @NamedQuery(name = "CajaChica.findByHoraApertura", query = "SELECT c FROM CajaChica c WHERE c.horaApertura = :horaApertura")
    , @NamedQuery(name = "CajaChica.findByHoraCierre", query = "SELECT c FROM CajaChica c WHERE c.horaCierre = :horaCierre")
    , @NamedQuery(name = "CajaChica.findByTurno", query = "SELECT c FROM CajaChica c WHERE c.turno = :turno")
    , @NamedQuery(name = "CajaChica.findByNombreTurno", query = "SELECT c FROM CajaChica c WHERE c.nombreTurno = :nombreTurno")
    , @NamedQuery(name = "CajaChica.findByIngresosNeto", query = "SELECT c FROM CajaChica c WHERE c.ingresosNeto = :ingresosNeto")})
public class CajaChica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "fecha_apertura")
    @Temporal(TemporalType.DATE)
    private Date fechaApertura;
    @Column(name = "fecha_cierre")
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;
    @Basic(optional = false)
    @Column(name = "saldo_inicial")
    private double saldoInicial;
    @Basic(optional = false)
    @Column(name = "saldo_final")
    private double saldoFinal;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @Column(name = "hora_apertura")
    @Temporal(TemporalType.TIME)
    private Date horaApertura;
    @Column(name = "hora_cierre")
    @Temporal(TemporalType.TIME)
    private Date horaCierre;
    @Column(name = "turno")
    private Integer turno;
    @Column(name = "nombre_turno")
    private String nombreTurno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ingresos_neto")
    private Double ingresosNeto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cajaChica")
    private Collection<DetalleCajaChica> detalleCajaChicaCollection;
    @JoinColumn(name = "caja", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Caja caja;
    @JoinColumn(name = "cajero", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario cajero;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

    public CajaChica() {
    }

    public CajaChica(Integer codigo) {
        this.codigo = codigo;
    }

    public CajaChica(Integer codigo, Date fechaRegistro, Date fechaApertura, double saldoInicial, double saldoFinal, String estado) {
        this.codigo = codigo;
        this.fechaRegistro = fechaRegistro;
        this.fechaApertura = fechaApertura;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoFinal;
        this.estado = estado;
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

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(Date horaApertura) {
        this.horaApertura = horaApertura;
    }

    public Date getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(Date horaCierre) {
        this.horaCierre = horaCierre;
    }

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }

    public String getNombreTurno() {
        return nombreTurno;
    }

    public void setNombreTurno(String nombreTurno) {
        this.nombreTurno = nombreTurno;
    }

    public Double getIngresosNeto() {
        return ingresosNeto;
    }

    public void setIngresosNeto(Double ingresosNeto) {
        this.ingresosNeto = ingresosNeto;
    }

    @XmlTransient
    public Collection<DetalleCajaChica> getDetalleCajaChicaCollection() {
        return detalleCajaChicaCollection;
    }

    public void setDetalleCajaChicaCollection(Collection<DetalleCajaChica> detalleCajaChicaCollection) {
        this.detalleCajaChicaCollection = detalleCajaChicaCollection;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public Usuario getCajero() {
        return cajero;
    }

    public void setCajero(Usuario cajero) {
        this.cajero = cajero;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
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
        if (!(object instanceof CajaChica)) {
            return false;
        }
        CajaChica other = (CajaChica) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CajaChica[ codigo=" + codigo + " ]";
    }
    
}
