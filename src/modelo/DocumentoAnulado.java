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
import javax.persistence.Lob;
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
@Table(name = "documento_anulado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentoAnulado.findAll", query = "SELECT d FROM DocumentoAnulado d")
    , @NamedQuery(name = "DocumentoAnulado.findByCodigo", query = "SELECT d FROM DocumentoAnulado d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DocumentoAnulado.findByFecha", query = "SELECT d FROM DocumentoAnulado d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "DocumentoAnulado.findByDocumento", query = "SELECT d FROM DocumentoAnulado d WHERE d.documento = :documento")
    , @NamedQuery(name = "DocumentoAnulado.findByNumeroDocumento", query = "SELECT d FROM DocumentoAnulado d WHERE d.numeroDocumento = :numeroDocumento")
    , @NamedQuery(name = "DocumentoAnulado.findByFechaRegistro", query = "SELECT d FROM DocumentoAnulado d WHERE d.fechaRegistro = :fechaRegistro")})
public class DocumentoAnulado implements Serializable {

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
    @Column(name = "documento")
    private int documento;
    @Basic(optional = false)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Lob
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "tipo_documento", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoDocumento tipoDocumento;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

    public DocumentoAnulado() {
    }

    public DocumentoAnulado(Integer codigo) {
        this.codigo = codigo;
    }

    public DocumentoAnulado(Integer codigo, Date fecha, int documento, String numeroDocumento, Date fechaRegistro, String comentario) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.documento = documento;
        this.numeroDocumento = numeroDocumento;
        this.fechaRegistro = fechaRegistro;
        this.comentario = comentario;
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

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof DocumentoAnulado)) {
            return false;
        }
        DocumentoAnulado other = (DocumentoAnulado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DocumentoAnulado[ codigo=" + codigo + " ]";
    }
    
}
