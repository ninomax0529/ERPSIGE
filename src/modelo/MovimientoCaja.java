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
@Table(name = "movimiento_caja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovimientoCaja.findAll", query = "SELECT m FROM MovimientoCaja m")
    , @NamedQuery(name = "MovimientoCaja.findByCodigo", query = "SELECT m FROM MovimientoCaja m WHERE m.codigo = :codigo")
    , @NamedQuery(name = "MovimientoCaja.findByCaja", query = "SELECT m FROM MovimientoCaja m WHERE m.caja = :caja")
    , @NamedQuery(name = "MovimientoCaja.findByCajero", query = "SELECT m FROM MovimientoCaja m WHERE m.cajero = :cajero")
    , @NamedQuery(name = "MovimientoCaja.findByFecha", query = "SELECT m FROM MovimientoCaja m WHERE m.fecha = :fecha")
    , @NamedQuery(name = "MovimientoCaja.findByTipoMovimiento", query = "SELECT m FROM MovimientoCaja m WHERE m.tipoMovimiento = :tipoMovimiento")})
public class MovimientoCaja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "caja")
    private int caja;
    @Basic(optional = false)
    @Column(name = "cajero")
    private int cajero;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "tipo_movimiento")
    private int tipoMovimiento;

    public MovimientoCaja() {
    }

    public MovimientoCaja(Integer codigo) {
        this.codigo = codigo;
    }

    public MovimientoCaja(Integer codigo, int caja, int cajero, Date fecha, int tipoMovimiento) {
        this.codigo = codigo;
        this.caja = caja;
        this.cajero = cajero;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getCaja() {
        return caja;
    }

    public void setCaja(int caja) {
        this.caja = caja;
    }

    public int getCajero() {
        return cajero;
    }

    public void setCajero(int cajero) {
        this.cajero = cajero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
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
        if (!(object instanceof MovimientoCaja)) {
            return false;
        }
        MovimientoCaja other = (MovimientoCaja) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.MovimientoCaja[ codigo=" + codigo + " ]";
    }
    
}
