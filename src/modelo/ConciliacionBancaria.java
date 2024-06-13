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
@Table(name = "conciliacion_bancaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConciliacionBancaria.findAll", query = "SELECT c FROM ConciliacionBancaria c")
    , @NamedQuery(name = "ConciliacionBancaria.findByCodigo", query = "SELECT c FROM ConciliacionBancaria c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "ConciliacionBancaria.findByFecha", query = "SELECT c FROM ConciliacionBancaria c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "ConciliacionBancaria.findByFechaDesde", query = "SELECT c FROM ConciliacionBancaria c WHERE c.fechaDesde = :fechaDesde")
    , @NamedQuery(name = "ConciliacionBancaria.findByFechaHasta", query = "SELECT c FROM ConciliacionBancaria c WHERE c.fechaHasta = :fechaHasta")
    , @NamedQuery(name = "ConciliacionBancaria.findByAnulada", query = "SELECT c FROM ConciliacionBancaria c WHERE c.anulada = :anulada")
    , @NamedQuery(name = "ConciliacionBancaria.findByNombreBanco", query = "SELECT c FROM ConciliacionBancaria c WHERE c.nombreBanco = :nombreBanco")
    , @NamedQuery(name = "ConciliacionBancaria.findByCuentaBanco", query = "SELECT c FROM ConciliacionBancaria c WHERE c.cuentaBanco = :cuentaBanco")
    , @NamedQuery(name = "ConciliacionBancaria.findByCuentaLibro", query = "SELECT c FROM ConciliacionBancaria c WHERE c.cuentaLibro = :cuentaLibro")
    , @NamedQuery(name = "ConciliacionBancaria.findByFechaRegistro", query = "SELECT c FROM ConciliacionBancaria c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "ConciliacionBancaria.findByPeriodo", query = "SELECT c FROM ConciliacionBancaria c WHERE c.periodo = :periodo")
    , @NamedQuery(name = "ConciliacionBancaria.findByNombreDelPeriodo", query = "SELECT c FROM ConciliacionBancaria c WHERE c.nombreDelPeriodo = :nombreDelPeriodo")
    , @NamedQuery(name = "ConciliacionBancaria.findBySaldoAConciliarBanco", query = "SELECT c FROM ConciliacionBancaria c WHERE c.saldoAConciliarBanco = :saldoAConciliarBanco")
    , @NamedQuery(name = "ConciliacionBancaria.findBySaldoAConciliarLibro", query = "SELECT c FROM ConciliacionBancaria c WHERE c.saldoAConciliarLibro = :saldoAConciliarLibro")
    , @NamedQuery(name = "ConciliacionBancaria.findBySaldoConciliadoLibro", query = "SELECT c FROM ConciliacionBancaria c WHERE c.saldoConciliadoLibro = :saldoConciliadoLibro")
    , @NamedQuery(name = "ConciliacionBancaria.findBySaldoConciliadoBanco", query = "SELECT c FROM ConciliacionBancaria c WHERE c.saldoConciliadoBanco = :saldoConciliadoBanco")
    , @NamedQuery(name = "ConciliacionBancaria.findByMes", query = "SELECT c FROM ConciliacionBancaria c WHERE c.mes = :mes")
    , @NamedQuery(name = "ConciliacionBancaria.findByNombreMes", query = "SELECT c FROM ConciliacionBancaria c WHERE c.nombreMes = :nombreMes")})
public class ConciliacionBancaria implements Serializable {

    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private UnidadDeNegocio unidadDeNegocio;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "fecha_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Basic(optional = false)
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    @Basic(optional = false)
    @Column(name = "anulada")
    private boolean anulada;
    @Basic(optional = false)
    @Column(name = "nombre_banco")
    private String nombreBanco;
    @Basic(optional = false)
    @Column(name = "cuenta_banco")
    private String cuentaBanco;
    @Basic(optional = false)
    @Column(name = "cuenta_libro")
    private String cuentaLibro;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "periodo")
    private Integer periodo;
    @Column(name = "nombre_del_periodo")
    private String nombreDelPeriodo;
    @Basic(optional = false)
    @Column(name = "saldo_a_conciliar_banco")
    private double saldoAConciliarBanco;
    @Basic(optional = false)
    @Column(name = "saldo_a_conciliar_libro")
    private double saldoAConciliarLibro;
    @Basic(optional = false)
    @Column(name = "saldo_conciliado_libro")
    private double saldoConciliadoLibro;
    @Basic(optional = false)
    @Column(name = "saldo_conciliado_banco")
    private double saldoConciliadoBanco;
    @Column(name = "mes")
    private Integer mes;
    @Column(name = "nombre_mes")
    private String nombreMes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conciliacionBancario")
    private Collection<DetalleConciliacionBancariaLibro> detalleConciliacionBancariaLibroCollection;
    @JoinColumn(name = "banco", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Banco banco;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conciliacionBancario")
    private Collection<DetalleConciliacionBancariaBanco> detalleConciliacionBancariaBancoCollection;

    public ConciliacionBancaria() {
    }

    public ConciliacionBancaria(Integer codigo) {
        this.codigo = codigo;
    }

    public ConciliacionBancaria(Integer codigo, Date fecha, Date fechaDesde, Date fechaHasta, boolean anulada, String nombreBanco, String cuentaBanco, String cuentaLibro, Date fechaRegistro, double saldoAConciliarBanco, double saldoAConciliarLibro, double saldoConciliadoLibro, double saldoConciliadoBanco) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.anulada = anulada;
        this.nombreBanco = nombreBanco;
        this.cuentaBanco = cuentaBanco;
        this.cuentaLibro = cuentaLibro;
        this.fechaRegistro = fechaRegistro;
        this.saldoAConciliarBanco = saldoAConciliarBanco;
        this.saldoAConciliarLibro = saldoAConciliarLibro;
        this.saldoConciliadoLibro = saldoConciliadoLibro;
        this.saldoConciliadoBanco = saldoConciliadoBanco;
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

    public boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getCuentaBanco() {
        return cuentaBanco;
    }

    public void setCuentaBanco(String cuentaBanco) {
        this.cuentaBanco = cuentaBanco;
    }

    public String getCuentaLibro() {
        return cuentaLibro;
    }

    public void setCuentaLibro(String cuentaLibro) {
        this.cuentaLibro = cuentaLibro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public String getNombreDelPeriodo() {
        return nombreDelPeriodo;
    }

    public void setNombreDelPeriodo(String nombreDelPeriodo) {
        this.nombreDelPeriodo = nombreDelPeriodo;
    }

    public double getSaldoAConciliarBanco() {
        return saldoAConciliarBanco;
    }

    public void setSaldoAConciliarBanco(double saldoAConciliarBanco) {
        this.saldoAConciliarBanco = saldoAConciliarBanco;
    }

    public double getSaldoAConciliarLibro() {
        return saldoAConciliarLibro;
    }

    public void setSaldoAConciliarLibro(double saldoAConciliarLibro) {
        this.saldoAConciliarLibro = saldoAConciliarLibro;
    }

    public double getSaldoConciliadoLibro() {
        return saldoConciliadoLibro;
    }

    public void setSaldoConciliadoLibro(double saldoConciliadoLibro) {
        this.saldoConciliadoLibro = saldoConciliadoLibro;
    }

    public double getSaldoConciliadoBanco() {
        return saldoConciliadoBanco;
    }

    public void setSaldoConciliadoBanco(double saldoConciliadoBanco) {
        this.saldoConciliadoBanco = saldoConciliadoBanco;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public String getNombreMes() {
        return nombreMes;
    }

    public void setNombreMes(String nombreMes) {
        this.nombreMes = nombreMes;
    }

    @XmlTransient
    public Collection<DetalleConciliacionBancariaLibro> getDetalleConciliacionBancariaLibroCollection() {
        return detalleConciliacionBancariaLibroCollection;
    }

    public void setDetalleConciliacionBancariaLibroCollection(Collection<DetalleConciliacionBancariaLibro> detalleConciliacionBancariaLibroCollection) {
        this.detalleConciliacionBancariaLibroCollection = detalleConciliacionBancariaLibroCollection;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<DetalleConciliacionBancariaBanco> getDetalleConciliacionBancariaBancoCollection() {
        return detalleConciliacionBancariaBancoCollection;
    }

    public void setDetalleConciliacionBancariaBancoCollection(Collection<DetalleConciliacionBancariaBanco> detalleConciliacionBancariaBancoCollection) {
        this.detalleConciliacionBancariaBancoCollection = detalleConciliacionBancariaBancoCollection;
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
        if (!(object instanceof ConciliacionBancaria)) {
            return false;
        }
        ConciliacionBancaria other = (ConciliacionBancaria) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ConciliacionBancaria[ codigo=" + codigo + " ]";
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }
    
}
