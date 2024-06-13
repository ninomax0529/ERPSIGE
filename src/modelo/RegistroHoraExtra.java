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
@Table(name = "registro_hora_extra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroHoraExtra.findAll", query = "SELECT r FROM RegistroHoraExtra r")
    , @NamedQuery(name = "RegistroHoraExtra.findByCodigo", query = "SELECT r FROM RegistroHoraExtra r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "RegistroHoraExtra.findByFechaRegistro", query = "SELECT r FROM RegistroHoraExtra r WHERE r.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "RegistroHoraExtra.findByMes", query = "SELECT r FROM RegistroHoraExtra r WHERE r.mes = :mes")
    , @NamedQuery(name = "RegistroHoraExtra.findByAnio", query = "SELECT r FROM RegistroHoraExtra r WHERE r.anio = :anio")
    , @NamedQuery(name = "RegistroHoraExtra.findByMesAnio", query = "SELECT r FROM RegistroHoraExtra r WHERE r.mesAnio = :mesAnio")
    , @NamedQuery(name = "RegistroHoraExtra.findByTotal", query = "SELECT r FROM RegistroHoraExtra r WHERE r.total = :total")
    , @NamedQuery(name = "RegistroHoraExtra.findByFechaInicio", query = "SELECT r FROM RegistroHoraExtra r WHERE r.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "RegistroHoraExtra.findByFechaFinal", query = "SELECT r FROM RegistroHoraExtra r WHERE r.fechaFinal = :fechaFinal")
    , @NamedQuery(name = "RegistroHoraExtra.findByPeriodo", query = "SELECT r FROM RegistroHoraExtra r WHERE r.periodo = :periodo")
    , @NamedQuery(name = "RegistroHoraExtra.findByNombreMes", query = "SELECT r FROM RegistroHoraExtra r WHERE r.nombreMes = :nombreMes")})
public class RegistroHoraExtra implements Serializable {

    @Basic(optional = false)
    @Column(name = "anulada")
    private boolean anulada;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
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
    @Column(name = "total")
    private double total;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Column(name = "periodo")
    private String periodo;
    @Column(name = "nombre_mes")
    private String nombreMes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registroHoraExtra",orphanRemoval = true)
    private Collection<DetalleRegistroHoraExtra> detalleRegistroHoraExtraCollection;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public RegistroHoraExtra() {
    }

    public RegistroHoraExtra(Integer codigo) {
        this.codigo = codigo;
    }

    public RegistroHoraExtra(Integer codigo, Date fechaRegistro, int mes, int anio, String mesAnio, double total) {
        this.codigo = codigo;
        this.fechaRegistro = fechaRegistro;
        this.mes = mes;
        this.anio = anio;
        this.mesAnio = mesAnio;
        this.total = total;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getNombreMes() {
        return nombreMes;
    }

    public void setNombreMes(String nombreMes) {
        this.nombreMes = nombreMes;
    }

    @XmlTransient
    public Collection<DetalleRegistroHoraExtra> getDetalleRegistroHoraExtraCollection() {
        return detalleRegistroHoraExtraCollection;
    }

    public void setDetalleRegistroHoraExtraCollection(Collection<DetalleRegistroHoraExtra> detalleRegistroHoraExtraCollection) {
        this.detalleRegistroHoraExtraCollection = detalleRegistroHoraExtraCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroHoraExtra)) {
            return false;
        }
        RegistroHoraExtra other = (RegistroHoraExtra) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RegistroHoraExtra[ codigo=" + codigo + " ]";
    }

    public boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }
    
}
