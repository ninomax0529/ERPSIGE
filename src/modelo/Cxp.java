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
@Table(name = "cxp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cxp.findAll", query = "SELECT c FROM Cxp c")
    , @NamedQuery(name = "Cxp.findByCodigo", query = "SELECT c FROM Cxp c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Cxp.findByNombreBeneficiario", query = "SELECT c FROM Cxp c WHERE c.nombreBeneficiario = :nombreBeneficiario")
    , @NamedQuery(name = "Cxp.findByFecha", query = "SELECT c FROM Cxp c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "Cxp.findByMonto", query = "SELECT c FROM Cxp c WHERE c.monto = :monto")
    , @NamedQuery(name = "Cxp.findByMontoPagado", query = "SELECT c FROM Cxp c WHERE c.montoPagado = :montoPagado")
    , @NamedQuery(name = "Cxp.findByMontoPendiente", query = "SELECT c FROM Cxp c WHERE c.montoPendiente = :montoPendiente")
    , @NamedQuery(name = "Cxp.findByNcf", query = "SELECT c FROM Cxp c WHERE c.ncf = :ncf")
    , @NamedQuery(name = "Cxp.findByNoFactura", query = "SELECT c FROM Cxp c WHERE c.noFactura = :noFactura")
    , @NamedQuery(name = "Cxp.findByAnulada", query = "SELECT c FROM Cxp c WHERE c.anulada = :anulada")
    , @NamedQuery(name = "Cxp.findByFechaVencimiento", query = "SELECT c FROM Cxp c WHERE c.fechaVencimiento = :fechaVencimiento")})
public class Cxp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "nombre_beneficiario")
    private String nombreBeneficiario;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @Lob
    @Column(name = "concepto")
    private String concepto;
    @Column(name = "monto_pagado")
    private Double montoPagado;
    @Column(name = "monto_pendiente")
    private Double montoPendiente;
    @Column(name = "ncf")
    private String ncf;
    @Column(name = "no_factura")
    private String noFactura;
    @Column(name = "anulada")
    private Boolean anulada;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @JoinColumn(name = "suplidor", referencedColumnName = "codigo")
    @ManyToOne
    private Suplidor suplidor;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @OneToMany(mappedBy = "cxp")
    private Collection<DetalleCxp> detalleCxpCollection;

    public Cxp() {
    }

    public Cxp(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public void setNombreBeneficiario(String nombreBeneficiario) {
        this.nombreBeneficiario = nombreBeneficiario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(Double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public Double getMontoPendiente() {
        return montoPendiente;
    }

    public void setMontoPendiente(Double montoPendiente) {
        this.montoPendiente = montoPendiente;
    }

    public String getNcf() {
        return ncf;
    }

    public void setNcf(String ncf) {
        this.ncf = ncf;
    }

    public String getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(String noFactura) {
        this.noFactura = noFactura;
    }

    public Boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(Boolean anulada) {
        this.anulada = anulada;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
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

    @XmlTransient
    public Collection<DetalleCxp> getDetalleCxpCollection() {
        return detalleCxpCollection;
    }

    public void setDetalleCxpCollection(Collection<DetalleCxp> detalleCxpCollection) {
        this.detalleCxpCollection = detalleCxpCollection;
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
        if (!(object instanceof Cxp)) {
            return false;
        }
        Cxp other = (Cxp) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cxp[ codigo=" + codigo + " ]";
    }
    
}
