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
@Table(name = "detalle_registro_hora_extra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleRegistroHoraExtra.findAll", query = "SELECT d FROM DetalleRegistroHoraExtra d")
    , @NamedQuery(name = "DetalleRegistroHoraExtra.findByCodigo", query = "SELECT d FROM DetalleRegistroHoraExtra d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleRegistroHoraExtra.findByCantidad", query = "SELECT d FROM DetalleRegistroHoraExtra d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleRegistroHoraExtra.findBySueldoPorHora", query = "SELECT d FROM DetalleRegistroHoraExtra d WHERE d.sueldoPorHora = :sueldoPorHora")
    , @NamedQuery(name = "DetalleRegistroHoraExtra.findByPorCientoHoraExtra", query = "SELECT d FROM DetalleRegistroHoraExtra d WHERE d.porCientoHoraExtra = :porCientoHoraExtra")
    , @NamedQuery(name = "DetalleRegistroHoraExtra.findBySueldoPorHoraExtra", query = "SELECT d FROM DetalleRegistroHoraExtra d WHERE d.sueldoPorHoraExtra = :sueldoPorHoraExtra")
    , @NamedQuery(name = "DetalleRegistroHoraExtra.findByTotal", query = "SELECT d FROM DetalleRegistroHoraExtra d WHERE d.total = :total")
    , @NamedQuery(name = "DetalleRegistroHoraExtra.findByFecha", query = "SELECT d FROM DetalleRegistroHoraExtra d WHERE d.fecha = :fecha")})
public class DetalleRegistroHoraExtra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private double cantidad;
    @Basic(optional = false)
    @Lob
    @Column(name = "concepto")
    private String concepto;
    @Basic(optional = false)
    @Column(name = "sueldo_por_hora")
    private double sueldoPorHora;
    @Basic(optional = false)
    @Column(name = "por_ciento_hora_extra")
    private double porCientoHoraExtra;
    @Basic(optional = false)
    @Column(name = "sueldo_por_hora_extra")
    private double sueldoPorHoraExtra;
    @Basic(optional = false)
    @Column(name = "total")
    private double total;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "empleado", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Empleado empleado;
    @JoinColumn(name = "registro_hora_extra", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private RegistroHoraExtra registroHoraExtra;

    public DetalleRegistroHoraExtra() {
    }

    public DetalleRegistroHoraExtra(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleRegistroHoraExtra(Integer codigo, double cantidad, String concepto, double sueldoPorHora, double porCientoHoraExtra, double sueldoPorHoraExtra, double total, Date fecha) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.concepto = concepto;
        this.sueldoPorHora = sueldoPorHora;
        this.porCientoHoraExtra = porCientoHoraExtra;
        this.sueldoPorHoraExtra = sueldoPorHoraExtra;
        this.total = total;
        this.fecha = fecha;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getSueldoPorHora() {
        return sueldoPorHora;
    }

    public void setSueldoPorHora(double sueldoPorHora) {
        this.sueldoPorHora = sueldoPorHora;
    }

    public double getPorCientoHoraExtra() {
        return porCientoHoraExtra;
    }

    public void setPorCientoHoraExtra(double porCientoHoraExtra) {
        this.porCientoHoraExtra = porCientoHoraExtra;
    }

    public double getSueldoPorHoraExtra() {
        return sueldoPorHoraExtra;
    }

    public void setSueldoPorHoraExtra(double sueldoPorHoraExtra) {
        this.sueldoPorHoraExtra = sueldoPorHoraExtra;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public RegistroHoraExtra getRegistroHoraExtra() {
        return registroHoraExtra;
    }

    public void setRegistroHoraExtra(RegistroHoraExtra registroHoraExtra) {
        this.registroHoraExtra = registroHoraExtra;
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
        if (!(object instanceof DetalleRegistroHoraExtra)) {
            return false;
        }
        DetalleRegistroHoraExtra other = (DetalleRegistroHoraExtra) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleRegistroHoraExtra[ codigo=" + codigo + " ]";
    }
    
}
