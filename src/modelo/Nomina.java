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
@Table(name = "nomina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nomina.findAll", query = "SELECT n FROM Nomina n")
    , @NamedQuery(name = "Nomina.findByCodigo", query = "SELECT n FROM Nomina n WHERE n.codigo = :codigo")
    , @NamedQuery(name = "Nomina.findByFechaInicio", query = "SELECT n FROM Nomina n WHERE n.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Nomina.findByFechaCorte", query = "SELECT n FROM Nomina n WHERE n.fechaCorte = :fechaCorte")
    , @NamedQuery(name = "Nomina.findByFechaRegistro", query = "SELECT n FROM Nomina n WHERE n.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Nomina.findByNumero", query = "SELECT n FROM Nomina n WHERE n.numero = :numero")
    , @NamedQuery(name = "Nomina.findByNumeroNomina", query = "SELECT n FROM Nomina n WHERE n.numeroNomina = :numeroNomina")
    , @NamedQuery(name = "Nomina.findByMes", query = "SELECT n FROM Nomina n WHERE n.mes = :mes")
    , @NamedQuery(name = "Nomina.findByAnio", query = "SELECT n FROM Nomina n WHERE n.anio = :anio")
    , @NamedQuery(name = "Nomina.findByMesAnio", query = "SELECT n FROM Nomina n WHERE n.mesAnio = :mesAnio")
    , @NamedQuery(name = "Nomina.findByCantidadEmpleado", query = "SELECT n FROM Nomina n WHERE n.cantidadEmpleado = :cantidadEmpleado")
    , @NamedQuery(name = "Nomina.findByTotalNomina", query = "SELECT n FROM Nomina n WHERE n.totalNomina = :totalNomina")
    , @NamedQuery(name = "Nomina.findByFechaPreparada", query = "SELECT n FROM Nomina n WHERE n.fechaPreparada = :fechaPreparada")
    , @NamedQuery(name = "Nomina.findByFechaRevisada", query = "SELECT n FROM Nomina n WHERE n.fechaRevisada = :fechaRevisada")
    , @NamedQuery(name = "Nomina.findByFechaAutorizada", query = "SELECT n FROM Nomina n WHERE n.fechaAutorizada = :fechaAutorizada")
    , @NamedQuery(name = "Nomina.findByFechaPagada", query = "SELECT n FROM Nomina n WHERE n.fechaPagada = :fechaPagada")
    , @NamedQuery(name = "Nomina.findByPreparadaPor", query = "SELECT n FROM Nomina n WHERE n.preparadaPor = :preparadaPor")
    , @NamedQuery(name = "Nomina.findByRevisadaPor", query = "SELECT n FROM Nomina n WHERE n.revisadaPor = :revisadaPor")
    , @NamedQuery(name = "Nomina.findByAutorizadaPor", query = "SELECT n FROM Nomina n WHERE n.autorizadaPor = :autorizadaPor")
    , @NamedQuery(name = "Nomina.findByPagadaPor", query = "SELECT n FROM Nomina n WHERE n.pagadaPor = :pagadaPor")
    , @NamedQuery(name = "Nomina.findByPagada", query = "SELECT n FROM Nomina n WHERE n.pagada = :pagada")
    , @NamedQuery(name = "Nomina.findByAnulada", query = "SELECT n FROM Nomina n WHERE n.anulada = :anulada")
    , @NamedQuery(name = "Nomina.findByFechaAnulada", query = "SELECT n FROM Nomina n WHERE n.fechaAnulada = :fechaAnulada")
    , @NamedQuery(name = "Nomina.findByNumeroQuincena", query = "SELECT n FROM Nomina n WHERE n.numeroQuincena = :numeroQuincena")
    , @NamedQuery(name = "Nomina.findByNumeroSemana", query = "SELECT n FROM Nomina n WHERE n.numeroSemana = :numeroSemana")
    , @NamedQuery(name = "Nomina.findByNombreMes", query = "SELECT n FROM Nomina n WHERE n.nombreMes = :nombreMes")
    , @NamedQuery(name = "Nomina.findByPeriodo", query = "SELECT n FROM Nomina n WHERE n.periodo = :periodo")
    , @NamedQuery(name = "Nomina.findByAnuladaPor", query = "SELECT n FROM Nomina n WHERE n.anuladaPor = :anuladaPor")})
public class Nomina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fecha_corte")
    @Temporal(TemporalType.DATE)
    private Date fechaCorte;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @Column(name = "numero_nomina")
    private int numeroNomina;
    @Basic(optional = false)
    @Column(name = "mes")
    private int mes;
    @Basic(optional = false)
    @Column(name = "anio")
    private int anio;
    @Basic(optional = false)
    @Column(name = "mes_anio")
    private String mesAnio;
    @Basic(optional = false)
    @Column(name = "cantidad_empleado")
    private int cantidadEmpleado;
    @Basic(optional = false)
    @Column(name = "total_nomina")
    private double totalNomina;
    @Column(name = "fecha_preparada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPreparada;
    @Column(name = "fecha_revisada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRevisada;
    @Column(name = "fecha_autorizada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAutorizada;
    @Column(name = "fecha_pagada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPagada;
    @Column(name = "preparada_por")
    private Integer preparadaPor;
    @Column(name = "revisada_por")
    private Integer revisadaPor;
    @Column(name = "autorizada_por")
    private Integer autorizadaPor;
    @Column(name = "pagada_por")
    private Integer pagadaPor;
    @Basic(optional = false)
    @Column(name = "pagada")
    private boolean pagada;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "anulada")
    private boolean anulada;
    @Column(name = "fecha_anulada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulada;
    @Lob
    @Column(name = "nota_anulacion")
    private String notaAnulacion;
    @Column(name = "numero_quincena")
    private String numeroQuincena;
    @Column(name = "numero_semana")
    private String numeroSemana;
    @Column(name = "nombre_mes")
    private String nombreMes;
    @Column(name = "periodo")
    private String periodo;
    @Lob
    @Column(name = "razon_de_anulacion")
    private String razonDeAnulacion;
    @Column(name = "anulada_por")
    private Integer anuladaPor;
    @JoinColumn(name = "estado", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private EstadoNomina estado;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "tipo_nomina", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoDeNomina tipoNomina;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nomina")
    private Collection<DetalleNomina> detalleNominaCollection;

    public Nomina() {
    }

    public Nomina(Integer codigo) {
        this.codigo = codigo;
    }

    public Nomina(Integer codigo, Date fechaInicio, Date fechaCorte, Date fechaRegistro, int numero, int numeroNomina, int mes, int anio, String mesAnio, int cantidadEmpleado, double totalNomina, boolean pagada, boolean anulada) {
        this.codigo = codigo;
        this.fechaInicio = fechaInicio;
        this.fechaCorte = fechaCorte;
        this.fechaRegistro = fechaRegistro;
        this.numero = numero;
        this.numeroNomina = numeroNomina;
        this.mes = mes;
        this.anio = anio;
        this.mesAnio = mesAnio;
        this.cantidadEmpleado = cantidadEmpleado;
        this.totalNomina = totalNomina;
        this.pagada = pagada;
        this.anulada = anulada;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumeroNomina() {
        return numeroNomina;
    }

    public void setNumeroNomina(int numeroNomina) {
        this.numeroNomina = numeroNomina;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getMesAnio() {
        return mesAnio;
    }

    public void setMesAnio(String mesAnio) {
        this.mesAnio = mesAnio;
    }

    public int getCantidadEmpleado() {
        return cantidadEmpleado;
    }

    public void setCantidadEmpleado(int cantidadEmpleado) {
        this.cantidadEmpleado = cantidadEmpleado;
    }

    public double getTotalNomina() {
        return totalNomina;
    }

    public void setTotalNomina(double totalNomina) {
        this.totalNomina = totalNomina;
    }

    public Date getFechaPreparada() {
        return fechaPreparada;
    }

    public void setFechaPreparada(Date fechaPreparada) {
        this.fechaPreparada = fechaPreparada;
    }

    public Date getFechaRevisada() {
        return fechaRevisada;
    }

    public void setFechaRevisada(Date fechaRevisada) {
        this.fechaRevisada = fechaRevisada;
    }

    public Date getFechaAutorizada() {
        return fechaAutorizada;
    }

    public void setFechaAutorizada(Date fechaAutorizada) {
        this.fechaAutorizada = fechaAutorizada;
    }

    public Date getFechaPagada() {
        return fechaPagada;
    }

    public void setFechaPagada(Date fechaPagada) {
        this.fechaPagada = fechaPagada;
    }

    public Integer getPreparadaPor() {
        return preparadaPor;
    }

    public void setPreparadaPor(Integer preparadaPor) {
        this.preparadaPor = preparadaPor;
    }

    public Integer getRevisadaPor() {
        return revisadaPor;
    }

    public void setRevisadaPor(Integer revisadaPor) {
        this.revisadaPor = revisadaPor;
    }

    public Integer getAutorizadaPor() {
        return autorizadaPor;
    }

    public void setAutorizadaPor(Integer autorizadaPor) {
        this.autorizadaPor = autorizadaPor;
    }

    public Integer getPagadaPor() {
        return pagadaPor;
    }

    public void setPagadaPor(Integer pagadaPor) {
        this.pagadaPor = pagadaPor;
    }

    public boolean getPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public String getNotaAnulacion() {
        return notaAnulacion;
    }

    public void setNotaAnulacion(String notaAnulacion) {
        this.notaAnulacion = notaAnulacion;
    }

    public String getNumeroQuincena() {
        return numeroQuincena;
    }

    public void setNumeroQuincena(String numeroQuincena) {
        this.numeroQuincena = numeroQuincena;
    }

    public String getNumeroSemana() {
        return numeroSemana;
    }

    public void setNumeroSemana(String numeroSemana) {
        this.numeroSemana = numeroSemana;
    }

    public String getNombreMes() {
        return nombreMes;
    }

    public void setNombreMes(String nombreMes) {
        this.nombreMes = nombreMes;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getRazonDeAnulacion() {
        return razonDeAnulacion;
    }

    public void setRazonDeAnulacion(String razonDeAnulacion) {
        this.razonDeAnulacion = razonDeAnulacion;
    }

    public Integer getAnuladaPor() {
        return anuladaPor;
    }

    public void setAnuladaPor(Integer anuladaPor) {
        this.anuladaPor = anuladaPor;
    }

    public EstadoNomina getEstado() {
        return estado;
    }

    public void setEstado(EstadoNomina estado) {
        this.estado = estado;
    }

    public SecuenciaDocumento getSecuenciaDocumento() {
        return secuenciaDocumento;
    }

    public void setSecuenciaDocumento(SecuenciaDocumento secuenciaDocumento) {
        this.secuenciaDocumento = secuenciaDocumento;
    }

    public TipoDeNomina getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(TipoDeNomina tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<DetalleNomina> getDetalleNominaCollection() {
        return detalleNominaCollection;
    }

    public void setDetalleNominaCollection(Collection<DetalleNomina> detalleNominaCollection) {
        this.detalleNominaCollection = detalleNominaCollection;
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
        if (!(object instanceof Nomina)) {
            return false;
        }
        Nomina other = (Nomina) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Nomina[ codigo=" + codigo + " ]";
    }
    
}
