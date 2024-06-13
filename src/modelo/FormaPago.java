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
@Table(name = "forma_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormaPago.findAll", query = "SELECT f FROM FormaPago f")
    , @NamedQuery(name = "FormaPago.findByCodigo", query = "SELECT f FROM FormaPago f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "FormaPago.findByReferencia", query = "SELECT f FROM FormaPago f WHERE f.referencia = :referencia")
    , @NamedQuery(name = "FormaPago.findByMonto", query = "SELECT f FROM FormaPago f WHERE f.monto = :monto")
    , @NamedQuery(name = "FormaPago.findByDocumento", query = "SELECT f FROM FormaPago f WHERE f.documento = :documento")
    , @NamedQuery(name = "FormaPago.findByTipoDocumento", query = "SELECT f FROM FormaPago f WHERE f.tipoDocumento = :tipoDocumento")
    , @NamedQuery(name = "FormaPago.findByFecha", query = "SELECT f FROM FormaPago f WHERE f.fecha = :fecha")
    , @NamedQuery(name = "FormaPago.findByUnidadDeNegocio", query = "SELECT f FROM FormaPago f WHERE f.unidadDeNegocio = :unidadDeNegocio")})
public class FormaPago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "referencia")
    private String referencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @Column(name = "documento")
    private Integer documento;
    @Column(name = "tipo_documento")
    private Integer tipoDocumento;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "unidad_de_negocio")
    private Integer unidadDeNegocio;
    @JoinColumn(name = "tipo_forma_pago", referencedColumnName = "codigo")
    @ManyToOne
    private TipoFormaPago tipoFormaPago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formaPago")
    private Collection<FormaPagoFactura> formaPagoFacturaCollection;

    public FormaPago() {
    }

    public FormaPago(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(Integer unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public TipoFormaPago getTipoFormaPago() {
        return tipoFormaPago;
    }

    public void setTipoFormaPago(TipoFormaPago tipoFormaPago) {
        this.tipoFormaPago = tipoFormaPago;
    }

    @XmlTransient
    public Collection<FormaPagoFactura> getFormaPagoFacturaCollection() {
        return formaPagoFacturaCollection;
    }

    public void setFormaPagoFacturaCollection(Collection<FormaPagoFactura> formaPagoFacturaCollection) {
        this.formaPagoFacturaCollection = formaPagoFacturaCollection;
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
        if (!(object instanceof FormaPago)) {
            return false;
        }
        FormaPago other = (FormaPago) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.FormaPago[ codigo=" + codigo + " ]";
    }
    
}
