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
@Table(name = "registro_caja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroCaja.findAll", query = "SELECT r FROM RegistroCaja r")
    , @NamedQuery(name = "RegistroCaja.findByCodigo", query = "SELECT r FROM RegistroCaja r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "RegistroCaja.findByCaja", query = "SELECT r FROM RegistroCaja r WHERE r.caja = :caja")
    , @NamedQuery(name = "RegistroCaja.findByFecha", query = "SELECT r FROM RegistroCaja r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "RegistroCaja.findByIngreso", query = "SELECT r FROM RegistroCaja r WHERE r.ingreso = :ingreso")
    , @NamedQuery(name = "RegistroCaja.findByEgreso", query = "SELECT r FROM RegistroCaja r WHERE r.egreso = :egreso")
    , @NamedQuery(name = "RegistroCaja.findByCajero", query = "SELECT r FROM RegistroCaja r WHERE r.cajero = :cajero")
    , @NamedQuery(name = "RegistroCaja.findByFechaCreacion", query = "SELECT r FROM RegistroCaja r WHERE r.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "RegistroCaja.findByTipoMovimiento", query = "SELECT r FROM RegistroCaja r WHERE r.tipoMovimiento = :tipoMovimiento")})
public class RegistroCaja implements Serializable {

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
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "ingreso")
    private double ingreso;
    @Basic(optional = false)
    @Column(name = "egreso")
    private double egreso;
    @Basic(optional = false)
    @Column(name = "cajero")
    private int cajero;
    @Basic(optional = false)
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "tipo_movimiento")
    private int tipoMovimiento;

    public RegistroCaja() {
    }

    public RegistroCaja(Integer codigo) {
        this.codigo = codigo;
    }

    public RegistroCaja(Integer codigo, int caja, Date fecha, double ingreso, double egreso, int cajero, Date fechaCreacion, int tipoMovimiento) {
        this.codigo = codigo;
        this.caja = caja;
        this.fecha = fecha;
        this.ingreso = ingreso;
        this.egreso = egreso;
        this.cajero = cajero;
        this.fechaCreacion = fechaCreacion;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public double getEgreso() {
        return egreso;
    }

    public void setEgreso(double egreso) {
        this.egreso = egreso;
    }

    public int getCajero() {
        return cajero;
    }

    public void setCajero(int cajero) {
        this.cajero = cajero;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
        if (!(object instanceof RegistroCaja)) {
            return false;
        }
        RegistroCaja other = (RegistroCaja) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RegistroCaja[ codigo=" + codigo + " ]";
    }
    
}
