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
@Table(name = "forma_pago_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormaPagoFactura.findAll", query = "SELECT f FROM FormaPagoFactura f")
    , @NamedQuery(name = "FormaPagoFactura.findByCodigo", query = "SELECT f FROM FormaPagoFactura f WHERE f.codigo = :codigo")})
public class FormaPagoFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @JoinColumn(name = "factura", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Factura factura;
    @JoinColumn(name = "forma_pago", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private FormaPago formaPago;

    public FormaPagoFactura() {
    }

    public FormaPagoFactura(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
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
        if (!(object instanceof FormaPagoFactura)) {
            return false;
        }
        FormaPagoFactura other = (FormaPagoFactura) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.FormaPagoFactura[ codigo=" + codigo + " ]";
    }
    
}
