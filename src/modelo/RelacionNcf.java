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
@Table(name = "relacion_ncf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelacionNcf.findAll", query = "SELECT r FROM RelacionNcf r")
    , @NamedQuery(name = "RelacionNcf.findByCodigo", query = "SELECT r FROM RelacionNcf r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "RelacionNcf.findBySecuencia", query = "SELECT r FROM RelacionNcf r WHERE r.secuencia = :secuencia")
    , @NamedQuery(name = "RelacionNcf.findByRangoInicial", query = "SELECT r FROM RelacionNcf r WHERE r.rangoInicial = :rangoInicial")
    , @NamedQuery(name = "RelacionNcf.findByRangoFinal", query = "SELECT r FROM RelacionNcf r WHERE r.rangoFinal = :rangoFinal")
    , @NamedQuery(name = "RelacionNcf.findByActivo", query = "SELECT r FROM RelacionNcf r WHERE r.activo = :activo")
    , @NamedQuery(name = "RelacionNcf.findByActual", query = "SELECT r FROM RelacionNcf r WHERE r.actual = :actual")
    , @NamedQuery(name = "RelacionNcf.findByCantidad", query = "SELECT r FROM RelacionNcf r WHERE r.cantidad = :cantidad")
    , @NamedQuery(name = "RelacionNcf.findByFechaValidoDesde", query = "SELECT r FROM RelacionNcf r WHERE r.fechaValidoDesde = :fechaValidoDesde")
    , @NamedQuery(name = "RelacionNcf.findByFechaValidoHasta", query = "SELECT r FROM RelacionNcf r WHERE r.fechaValidoHasta = :fechaValidoHasta")})
public class RelacionNcf implements Serializable {

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    @Column(name = "secuencia_inicial")
    private String secuenciaInicial;
    @Column(name = "secuencia_final")
    private String secuenciaFinal;

    @Column(name = "secuencia_actual")
    private String secuenciaActual;
    @Column(name = "serie")
    private String serie;
    @Column(name = "tipo")
    private String tipo;

    @Column(name = "empresa_o_grupo")
    private Integer empresaOGrupo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "secuencia")
    private Integer secuencia;
    @Column(name = "rango_inicial")
    private String rangoInicial;
    @Column(name = "rango_final")
    private String rangoFinal;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "actual")
    private String actual;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "fecha_valido_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaValidoDesde;
    @Column(name = "fecha_valido_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaValidoHasta;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "tipo_ncf", referencedColumnName = "codigo")
    @ManyToOne
    private TipoNcf tipoNcf;

    public RelacionNcf() {
    }

    public RelacionNcf(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public String getRangoInicial() {
        return rangoInicial;
    }

    public void setRangoInicial(String rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    public String getRangoFinal() {
        return rangoFinal;
    }

    public void setRangoFinal(String rangoFinal) {
        this.rangoFinal = rangoFinal;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaValidoDesde() {
        return fechaValidoDesde;
    }

    public void setFechaValidoDesde(Date fechaValidoDesde) {
        this.fechaValidoDesde = fechaValidoDesde;
    }

    public Date getFechaValidoHasta() {
        return fechaValidoHasta;
    }

    public void setFechaValidoHasta(Date fechaValidoHasta) {
        this.fechaValidoHasta = fechaValidoHasta;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public TipoNcf getTipoNcf() {
        return tipoNcf;
    }

    public void setTipoNcf(TipoNcf tipoNcf) {
        this.tipoNcf = tipoNcf;
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
        if (!(object instanceof RelacionNcf)) {
            return false;
        }
        RelacionNcf other = (RelacionNcf) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RelacionNcf[ codigo=" + codigo + " ]";
    }

    public Integer getEmpresaOGrupo() {
        return empresaOGrupo;
    }

    public void setEmpresaOGrupo(Integer empresaOGrupo) {
        this.empresaOGrupo = empresaOGrupo;
    }

    public String getSecuenciaActual() {
        return secuenciaActual;
    }

    public void setSecuenciaActual(String secuenciaActual) {
        this.secuenciaActual = secuenciaActual;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSecuenciaInicial() {
        return secuenciaInicial;
    }

    public void setSecuenciaInicial(String secuenciaInicial) {
        this.secuenciaInicial = secuenciaInicial;
    }

    public String getSecuenciaFinal() {
        return secuenciaFinal;
    }

    public void setSecuenciaFinal(String secuenciaFinal) {
        this.secuenciaFinal = secuenciaFinal;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
}
