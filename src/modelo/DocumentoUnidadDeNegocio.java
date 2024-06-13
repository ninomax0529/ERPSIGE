/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "documento_unidad_de_negocio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentoUnidadDeNegocio.findAll", query = "SELECT d FROM DocumentoUnidadDeNegocio d")
    , @NamedQuery(name = "DocumentoUnidadDeNegocio.findByCodigo", query = "SELECT d FROM DocumentoUnidadDeNegocio d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DocumentoUnidadDeNegocio.findByTipoDocumento", query = "SELECT d FROM DocumentoUnidadDeNegocio d WHERE d.tipoDocumento = :tipoDocumento")
    , @NamedQuery(name = "DocumentoUnidadDeNegocio.findByNumero", query = "SELECT d FROM DocumentoUnidadDeNegocio d WHERE d.numero = :numero")
    , @NamedQuery(name = "DocumentoUnidadDeNegocio.findByNombreDocumento", query = "SELECT d FROM DocumentoUnidadDeNegocio d WHERE d.nombreDocumento = :nombreDocumento")})
public class DocumentoUnidadDeNegocio implements Serializable {

    @Column(name = "numero_sub_reporte_1")
    private Integer numeroSubReporte1;
    @Column(name = "numero_sub_reporte_2")
    private Integer numeroSubReporte2;
    @Column(name = "numero_sub_reporte_3")
    private Integer numeroSubReporte3;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "tipo_documento")
    private int tipoDocumento;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @Column(name = "nombre_documento")
    private String nombreDocumento;
    @JoinColumn(name = "unidad_negocio", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private UnidadDeNegocio unidadNegocio;

    public DocumentoUnidadDeNegocio() {
    }

    public DocumentoUnidadDeNegocio(Integer codigo) {
        this.codigo = codigo;
    }

    public DocumentoUnidadDeNegocio(Integer codigo, int tipoDocumento, int numero, String nombreDocumento) {
        this.codigo = codigo;
        this.tipoDocumento = tipoDocumento;
        this.numero = numero;
        this.nombreDocumento = nombreDocumento;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoUnidadDeNegocio)) {
            return false;
        }
        DocumentoUnidadDeNegocio other = (DocumentoUnidadDeNegocio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DocumentoUnidadDeNegocio[ codigo=" + codigo + " ]";
    }

    public Integer getNumeroSubReporte1() {
        return numeroSubReporte1;
    }

    public void setNumeroSubReporte1(Integer numeroSubReporte1) {
        this.numeroSubReporte1 = numeroSubReporte1;
    }

    public Integer getNumeroSubReporte2() {
        return numeroSubReporte2;
    }

    public void setNumeroSubReporte2(Integer numeroSubReporte2) {
        this.numeroSubReporte2 = numeroSubReporte2;
    }

    public Integer getNumeroSubReporte3() {
        return numeroSubReporte3;
    }

    public void setNumeroSubReporte3(Integer numeroSubReporte3) {
        this.numeroSubReporte3 = numeroSubReporte3;
    }
    
}
