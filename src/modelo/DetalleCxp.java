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
@Table(name = "detalle_cxp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCxp.findAll", query = "SELECT d FROM DetalleCxp d")
    , @NamedQuery(name = "DetalleCxp.findByCodigo", query = "SELECT d FROM DetalleCxp d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleCxp.findByFactura", query = "SELECT d FROM DetalleCxp d WHERE d.factura = :factura")
    , @NamedQuery(name = "DetalleCxp.findByNcf", query = "SELECT d FROM DetalleCxp d WHERE d.ncf = :ncf")
    , @NamedQuery(name = "DetalleCxp.findByMontoAbonado", query = "SELECT d FROM DetalleCxp d WHERE d.montoAbonado = :montoAbonado")})
public class DetalleCxp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "factura")
    private String factura;
    @Column(name = "ncf")
    private String ncf;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto_abonado")
    private Double montoAbonado;
    @JoinColumn(name = "cxp", referencedColumnName = "codigo")
    @ManyToOne
    private Cxp cxp;

    public DetalleCxp() {
    }

    public DetalleCxp(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getNcf() {
        return ncf;
    }

    public void setNcf(String ncf) {
        this.ncf = ncf;
    }

    public Double getMontoAbonado() {
        return montoAbonado;
    }

    public void setMontoAbonado(Double montoAbonado) {
        this.montoAbonado = montoAbonado;
    }

    public Cxp getCxp() {
        return cxp;
    }

    public void setCxp(Cxp cxp) {
        this.cxp = cxp;
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
        if (!(object instanceof DetalleCxp)) {
            return false;
        }
        DetalleCxp other = (DetalleCxp) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleCxp[ codigo=" + codigo + " ]";
    }
    
}
