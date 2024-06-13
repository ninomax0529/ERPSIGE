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
@Table(name = "adjunto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adjunto.findAll", query = "SELECT a FROM Adjunto a")
    , @NamedQuery(name = "Adjunto.findByCodigo", query = "SELECT a FROM Adjunto a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "Adjunto.findByUrlOrigen", query = "SELECT a FROM Adjunto a WHERE a.urlOrigen = :urlOrigen")
    , @NamedQuery(name = "Adjunto.findByUrlDestino", query = "SELECT a FROM Adjunto a WHERE a.urlDestino = :urlDestino")
    , @NamedQuery(name = "Adjunto.findByDocumento", query = "SELECT a FROM Adjunto a WHERE a.documento = :documento")
    , @NamedQuery(name = "Adjunto.findByTipoDocumento", query = "SELECT a FROM Adjunto a WHERE a.tipoDocumento = :tipoDocumento")
    , @NamedQuery(name = "Adjunto.findByNombreDocumentoDestino", query = "SELECT a FROM Adjunto a WHERE a.nombreDocumentoDestino = :nombreDocumentoDestino")
    , @NamedQuery(name = "Adjunto.findByNombreDocumentoOrigen", query = "SELECT a FROM Adjunto a WHERE a.nombreDocumentoOrigen = :nombreDocumentoOrigen")
    , @NamedQuery(name = "Adjunto.findByExtensionDocumento", query = "SELECT a FROM Adjunto a WHERE a.extensionDocumento = :extensionDocumento")
    , @NamedQuery(name = "Adjunto.findByIpComputadorOrigen", query = "SELECT a FROM Adjunto a WHERE a.ipComputadorOrigen = :ipComputadorOrigen")
    , @NamedQuery(name = "Adjunto.findByFecha", query = "SELECT a FROM Adjunto a WHERE a.fecha = :fecha")})
public class Adjunto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "url_origen")
    private String urlOrigen;
    @Column(name = "url_destino")
    private String urlDestino;
    @Basic(optional = false)
    @Column(name = "documento")
    private int documento;
    @Basic(optional = false)
    @Column(name = "tipo_documento")
    private int tipoDocumento;
    @Basic(optional = false)
    @Column(name = "nombre_documento_destino")
    private String nombreDocumentoDestino;
    @Column(name = "nombre_documento_origen")
    private String nombreDocumentoOrigen;
    @Column(name = "extension_documento")
    private String extensionDocumento;
    @Column(name = "ip_computador_origen")
    private String ipComputadorOrigen;
    @Lob
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

    public Adjunto() {
    }

    public Adjunto(Integer codigo) {
        this.codigo = codigo;
    }

    public Adjunto(Integer codigo, int documento, int tipoDocumento, String nombreDocumentoDestino, Date fecha) {
        this.codigo = codigo;
        this.documento = documento;
        this.tipoDocumento = tipoDocumento;
        this.nombreDocumentoDestino = nombreDocumentoDestino;
        this.fecha = fecha;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getUrlOrigen() {
        return urlOrigen;
    }

    public void setUrlOrigen(String urlOrigen) {
        this.urlOrigen = urlOrigen;
    }

    public String getUrlDestino() {
        return urlDestino;
    }

    public void setUrlDestino(String urlDestino) {
        this.urlDestino = urlDestino;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombreDocumentoDestino() {
        return nombreDocumentoDestino;
    }

    public void setNombreDocumentoDestino(String nombreDocumentoDestino) {
        this.nombreDocumentoDestino = nombreDocumentoDestino;
    }

    public String getNombreDocumentoOrigen() {
        return nombreDocumentoOrigen;
    }

    public void setNombreDocumentoOrigen(String nombreDocumentoOrigen) {
        this.nombreDocumentoOrigen = nombreDocumentoOrigen;
    }

    public String getExtensionDocumento() {
        return extensionDocumento;
    }

    public void setExtensionDocumento(String extensionDocumento) {
        this.extensionDocumento = extensionDocumento;
    }

    public String getIpComputadorOrigen() {
        return ipComputadorOrigen;
    }

    public void setIpComputadorOrigen(String ipComputadorOrigen) {
        this.ipComputadorOrigen = ipComputadorOrigen;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof Adjunto)) {
            return false;
        }
        Adjunto other = (Adjunto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Adjunto[ codigo=" + codigo + " ]";
    }
    
}
