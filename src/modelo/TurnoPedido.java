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
@Table(name = "turno_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TurnoPedido.findAll", query = "SELECT t FROM TurnoPedido t")
    , @NamedQuery(name = "TurnoPedido.findByCodigo", query = "SELECT t FROM TurnoPedido t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TurnoPedido.findByFecha", query = "SELECT t FROM TurnoPedido t WHERE t.fecha = :fecha")
    , @NamedQuery(name = "TurnoPedido.findByNumero", query = "SELECT t FROM TurnoPedido t WHERE t.numero = :numero")})
public class TurnoPedido implements Serializable {

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
    @Column(name = "numero")
    private int numero;

    public TurnoPedido() {
    }

    public TurnoPedido(Integer codigo) {
        this.codigo = codigo;
    }

    public TurnoPedido(Integer codigo, Date fecha, int numero) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.numero = numero;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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
        if (!(object instanceof TurnoPedido)) {
            return false;
        }
        TurnoPedido other = (TurnoPedido) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TurnoPedido[ codigo=" + codigo + " ]";
    }
    
}
