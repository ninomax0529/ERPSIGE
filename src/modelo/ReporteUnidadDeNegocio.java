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
@Table(name = "reporte_unidad_de_negocio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteUnidadDeNegocio.findAll", query = "SELECT r FROM ReporteUnidadDeNegocio r")
    , @NamedQuery(name = "ReporteUnidadDeNegocio.findByCodigo", query = "SELECT r FROM ReporteUnidadDeNegocio r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "ReporteUnidadDeNegocio.findByFechaRegistro", query = "SELECT r FROM ReporteUnidadDeNegocio r WHERE r.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "ReporteUnidadDeNegocio.findByRutaRpt", query = "SELECT r FROM ReporteUnidadDeNegocio r WHERE r.rutaRpt = :rutaRpt")
    , @NamedQuery(name = "ReporteUnidadDeNegocio.findByNombreDocumento", query = "SELECT r FROM ReporteUnidadDeNegocio r WHERE r.nombreDocumento = :nombreDocumento")})
public class ReporteUnidadDeNegocio implements Serializable {

    @Column(name = "numero")
    private Integer numero;

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
    @Column(name = "ruta_rpt")
    private String rutaRpt;
    @Column(name = "nombre_documento")
    private String nombreDocumento;   
    @JoinColumn(name = "unidad_negocio", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private UnidadDeNegocio unidadNegocio;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "tipo_documento", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoDocumento tipoDocumento;

    public ReporteUnidadDeNegocio() {
    }

    public ReporteUnidadDeNegocio(Integer codigo) {
        this.codigo = codigo;
    }

    public ReporteUnidadDeNegocio(Integer codigo, Date fechaRegistro, String rutaRpt) {
        this.codigo = codigo;
        this.fechaRegistro = fechaRegistro;
        this.rutaRpt = rutaRpt;
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

    public String getRutaRpt() {
        return rutaRpt;
    }

    public void setRutaRpt(String rutaRpt) {
        this.rutaRpt = rutaRpt;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }


    public UnidadDeNegocio getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(UnidadDeNegocio unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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
        if (!(object instanceof ReporteUnidadDeNegocio)) {
            return false;
        }
        ReporteUnidadDeNegocio other = (ReporteUnidadDeNegocio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ReporteUnidadDeNegocio[ codigo=" + codigo + " ]";
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    
}
