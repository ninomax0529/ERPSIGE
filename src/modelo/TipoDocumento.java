/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "tipo_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t")
    , @NamedQuery(name = "TipoDocumento.findByCodigo", query = "SELECT t FROM TipoDocumento t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoDocumento.findByNombre", query = "SELECT t FROM TipoDocumento t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoDocumento.findByMovimiento", query = "SELECT t FROM TipoDocumento t WHERE t.movimiento = :movimiento")
    , @NamedQuery(name = "TipoDocumento.findByModulo", query = "SELECT t FROM TipoDocumento t WHERE t.modulo = :modulo")})
public class TipoDocumento implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDocumento")
    private Collection<DetalleReconciliacionInternaCliente> detalleReconciliacionInternaClienteCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDocumento")
    private Collection<ReporteUnidadDeNegocio> reporteUnidadDeNegocioCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDocumento")
    private Collection<ReporteUnidadDeNegocio> reporteUnidadDeNegociuoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "movimiento")
    private String movimiento;
    @Column(name = "modulo")
    private Integer modulo;
    @OneToMany(mappedBy = "tipoDocumento")
    private Collection<EntradaDiario> entradaDiarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDocumento")
    private Collection<SecuenciaDocumento> secuenciaDocumentoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDocumento")
    private Collection<DetalleCajaChica> detalleCajaChicaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDocumento")
    private Collection<DocumentoAnulado> documentoAnuladoCollection;
    @OneToMany(mappedBy = "tipoDocumento")
    private Collection<NotaDebito> notaDebitoCollection;
    @OneToMany(mappedBy = "tipoDocumento")
    private Collection<NotaCredito> notaCreditoCollection;

    public TipoDocumento() {
    }

    public TipoDocumento(Integer codigo) {
        this.codigo = codigo;
    }

    public TipoDocumento(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public Integer getModulo() {
        return modulo;
    }

    public void setModulo(Integer modulo) {
        this.modulo = modulo;
    }

    @XmlTransient
    public Collection<EntradaDiario> getEntradaDiarioCollection() {
        return entradaDiarioCollection;
    }

    public void setEntradaDiarioCollection(Collection<EntradaDiario> entradaDiarioCollection) {
        this.entradaDiarioCollection = entradaDiarioCollection;
    }

    @XmlTransient
    public Collection<SecuenciaDocumento> getSecuenciaDocumentoCollection() {
        return secuenciaDocumentoCollection;
    }

    public void setSecuenciaDocumentoCollection(Collection<SecuenciaDocumento> secuenciaDocumentoCollection) {
        this.secuenciaDocumentoCollection = secuenciaDocumentoCollection;
    }

    @XmlTransient
    public Collection<DetalleCajaChica> getDetalleCajaChicaCollection() {
        return detalleCajaChicaCollection;
    }

    public void setDetalleCajaChicaCollection(Collection<DetalleCajaChica> detalleCajaChicaCollection) {
        this.detalleCajaChicaCollection = detalleCajaChicaCollection;
    }

    @XmlTransient
    public Collection<DocumentoAnulado> getDocumentoAnuladoCollection() {
        return documentoAnuladoCollection;
    }

    public void setDocumentoAnuladoCollection(Collection<DocumentoAnulado> documentoAnuladoCollection) {
        this.documentoAnuladoCollection = documentoAnuladoCollection;
    }

    @XmlTransient
    public Collection<NotaDebito> getNotaDebitoCollection() {
        return notaDebitoCollection;
    }

    public void setNotaDebitoCollection(Collection<NotaDebito> notaDebitoCollection) {
        this.notaDebitoCollection = notaDebitoCollection;
    }

    @XmlTransient
    public Collection<NotaCredito> getNotaCreditoCollection() {
        return notaCreditoCollection;
    }

    public void setNotaCreditoCollection(Collection<NotaCredito> notaCreditoCollection) {
        this.notaCreditoCollection = notaCreditoCollection;
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
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TipoDocumento[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<ReporteUnidadDeNegocio> getReporteUnidadDeNegociuoCollection() {
        return reporteUnidadDeNegociuoCollection;
    }

    public void setReporteUnidadDeNegociuoCollection(Collection<ReporteUnidadDeNegocio> reporteUnidadDeNegociuoCollection) {
        this.reporteUnidadDeNegociuoCollection = reporteUnidadDeNegociuoCollection;
    }

    @XmlTransient
    public Collection<ReporteUnidadDeNegocio> getReporteUnidadDeNegocioCollection() {
        return reporteUnidadDeNegocioCollection;
    }

    public void setReporteUnidadDeNegocioCollection(Collection<ReporteUnidadDeNegocio> reporteUnidadDeNegocioCollection) {
        this.reporteUnidadDeNegocioCollection = reporteUnidadDeNegocioCollection;
    }

    @XmlTransient
    public Collection<DetalleReconciliacionInternaCliente> getDetalleReconciliacionInternaClienteCollection() {
        return detalleReconciliacionInternaClienteCollection;
    }

    public void setDetalleReconciliacionInternaClienteCollection(Collection<DetalleReconciliacionInternaCliente> detalleReconciliacionInternaClienteCollection) {
        this.detalleReconciliacionInternaClienteCollection = detalleReconciliacionInternaClienteCollection;
    }
    
}
