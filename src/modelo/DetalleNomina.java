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
@Table(name = "detalle_nomina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleNomina.findAll", query = "SELECT d FROM DetalleNomina d")
    , @NamedQuery(name = "DetalleNomina.findByCodigo", query = "SELECT d FROM DetalleNomina d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleNomina.findBySueldo", query = "SELECT d FROM DetalleNomina d WHERE d.sueldo = :sueldo")
    , @NamedQuery(name = "DetalleNomina.findBySueldoQuincenal", query = "SELECT d FROM DetalleNomina d WHERE d.sueldoQuincenal = :sueldoQuincenal")
    , @NamedQuery(name = "DetalleNomina.findByCedula", query = "SELECT d FROM DetalleNomina d WHERE d.cedula = :cedula")
    , @NamedQuery(name = "DetalleNomina.findByComision", query = "SELECT d FROM DetalleNomina d WHERE d.comision = :comision")
    , @NamedQuery(name = "DetalleNomina.findByHorasExtra", query = "SELECT d FROM DetalleNomina d WHERE d.horasExtra = :horasExtra")
    , @NamedQuery(name = "DetalleNomina.findByTotalOtrasBeneficio", query = "SELECT d FROM DetalleNomina d WHERE d.totalOtrasBeneficio = :totalOtrasBeneficio")
    , @NamedQuery(name = "DetalleNomina.findByOtrasRemuneraciones", query = "SELECT d FROM DetalleNomina d WHERE d.otrasRemuneraciones = :otrasRemuneraciones")
    , @NamedQuery(name = "DetalleNomina.findByTotalBruto", query = "SELECT d FROM DetalleNomina d WHERE d.totalBruto = :totalBruto")
    , @NamedQuery(name = "DetalleNomina.findByMontoAfp", query = "SELECT d FROM DetalleNomina d WHERE d.montoAfp = :montoAfp")
    , @NamedQuery(name = "DetalleNomina.findByMontoSfs", query = "SELECT d FROM DetalleNomina d WHERE d.montoSfs = :montoSfs")
    , @NamedQuery(name = "DetalleNomina.findByCantidadDependiente", query = "SELECT d FROM DetalleNomina d WHERE d.cantidadDependiente = :cantidadDependiente")
    , @NamedQuery(name = "DetalleNomina.findByMontoDependienteAdicional", query = "SELECT d FROM DetalleNomina d WHERE d.montoDependienteAdicional = :montoDependienteAdicional")
    , @NamedQuery(name = "DetalleNomina.findByMontoDependiente", query = "SELECT d FROM DetalleNomina d WHERE d.montoDependiente = :montoDependiente")
    , @NamedQuery(name = "DetalleNomina.findByCxcEmpleado", query = "SELECT d FROM DetalleNomina d WHERE d.cxcEmpleado = :cxcEmpleado")
    , @NamedQuery(name = "DetalleNomina.findByIr3", query = "SELECT d FROM DetalleNomina d WHERE d.ir3 = :ir3")
    , @NamedQuery(name = "DetalleNomina.findByAvanceASueldo", query = "SELECT d FROM DetalleNomina d WHERE d.avanceASueldo = :avanceASueldo")
    , @NamedQuery(name = "DetalleNomina.findByTotalDescuento", query = "SELECT d FROM DetalleNomina d WHERE d.totalDescuento = :totalDescuento")
    , @NamedQuery(name = "DetalleNomina.findBySueldoNeto", query = "SELECT d FROM DetalleNomina d WHERE d.sueldoNeto = :sueldoNeto")
    , @NamedQuery(name = "DetalleNomina.findByRevisado", query = "SELECT d FROM DetalleNomina d WHERE d.revisado = :revisado")})
public class DetalleNomina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "sueldo")
    private double sueldo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sueldo_quincenal")
    private Double sueldoQuincenal;
    @Basic(optional = false)
    @Column(name = "cedula")
    private String cedula;
    @Basic(optional = false)
    @Column(name = "comision")
    private double comision;
    @Basic(optional = false)
    @Column(name = "horas_extra")
    private double horasExtra;
    @Basic(optional = false)
    @Column(name = "total_otras_beneficio")
    private double totalOtrasBeneficio;
    @Basic(optional = false)
    @Column(name = "otras_remuneraciones")
    private double otrasRemuneraciones;
    @Basic(optional = false)
    @Column(name = "total_bruto")
    private double totalBruto;
    @Basic(optional = false)
    @Column(name = "monto_afp")
    private double montoAfp;
    @Basic(optional = false)
    @Column(name = "monto_sfs")
    private double montoSfs;
    @Basic(optional = false)
    @Column(name = "cantidad_dependiente")
    private int cantidadDependiente;
    @Basic(optional = false)
    @Column(name = "monto_dependiente_adicional")
    private double montoDependienteAdicional;
    @Basic(optional = false)
    @Column(name = "monto_dependiente")
    private double montoDependiente;
    @Column(name = "cxc_empleado")
    private Double cxcEmpleado;
    @Basic(optional = false)
    @Column(name = "ir3")
    private double ir3;
    @Basic(optional = false)
    @Column(name = "avance_a_sueldo")
    private double avanceASueldo;
    @Basic(optional = false)
    @Column(name = "total_descuento")
    private double totalDescuento;
    @Basic(optional = false)
    @Column(name = "sueldo_neto")
    private double sueldoNeto;
    @Basic(optional = false)
    @Column(name = "revisado")
    private boolean revisado;
    @JoinColumn(name = "nomina", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Nomina nomina;
    @JoinColumn(name = "empleado", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Empleado empleado;

    public DetalleNomina() {
    }

    public DetalleNomina(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleNomina(Integer codigo, double sueldo, String cedula, double comision, double horasExtra, double totalOtrasBeneficio, double otrasRemuneraciones, double totalBruto, double montoAfp, double montoSfs, int cantidadDependiente, double montoDependienteAdicional, double montoDependiente, double ir3, double avanceASueldo, double totalDescuento, double sueldoNeto, boolean revisado) {
        this.codigo = codigo;
        this.sueldo = sueldo;
        this.cedula = cedula;
        this.comision = comision;
        this.horasExtra = horasExtra;
        this.totalOtrasBeneficio = totalOtrasBeneficio;
        this.otrasRemuneraciones = otrasRemuneraciones;
        this.totalBruto = totalBruto;
        this.montoAfp = montoAfp;
        this.montoSfs = montoSfs;
        this.cantidadDependiente = cantidadDependiente;
        this.montoDependienteAdicional = montoDependienteAdicional;
        this.montoDependiente = montoDependiente;
        this.ir3 = ir3;
        this.avanceASueldo = avanceASueldo;
        this.totalDescuento = totalDescuento;
        this.sueldoNeto = sueldoNeto;
        this.revisado = revisado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public Double getSueldoQuincenal() {
        return sueldoQuincenal;
    }

    public void setSueldoQuincenal(Double sueldoQuincenal) {
        this.sueldoQuincenal = sueldoQuincenal;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public double getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(double horasExtra) {
        this.horasExtra = horasExtra;
    }

    public double getTotalOtrasBeneficio() {
        return totalOtrasBeneficio;
    }

    public void setTotalOtrasBeneficio(double totalOtrasBeneficio) {
        this.totalOtrasBeneficio = totalOtrasBeneficio;
    }

    public double getOtrasRemuneraciones() {
        return otrasRemuneraciones;
    }

    public void setOtrasRemuneraciones(double otrasRemuneraciones) {
        this.otrasRemuneraciones = otrasRemuneraciones;
    }

    public double getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(double totalBruto) {
        this.totalBruto = totalBruto;
    }

    public double getMontoAfp() {
        return montoAfp;
    }

    public void setMontoAfp(double montoAfp) {
        this.montoAfp = montoAfp;
    }

    public double getMontoSfs() {
        return montoSfs;
    }

    public void setMontoSfs(double montoSfs) {
        this.montoSfs = montoSfs;
    }

    public int getCantidadDependiente() {
        return cantidadDependiente;
    }

    public void setCantidadDependiente(int cantidadDependiente) {
        this.cantidadDependiente = cantidadDependiente;
    }

    public double getMontoDependienteAdicional() {
        return montoDependienteAdicional;
    }

    public void setMontoDependienteAdicional(double montoDependienteAdicional) {
        this.montoDependienteAdicional = montoDependienteAdicional;
    }

    public double getMontoDependiente() {
        return montoDependiente;
    }

    public void setMontoDependiente(double montoDependiente) {
        this.montoDependiente = montoDependiente;
    }

    public Double getCxcEmpleado() {
        return cxcEmpleado;
    }

    public void setCxcEmpleado(Double cxcEmpleado) {
        this.cxcEmpleado = cxcEmpleado;
    }

    public double getIr3() {
        return ir3;
    }

    public void setIr3(double ir3) {
        this.ir3 = ir3;
    }

    public double getAvanceASueldo() {
        return avanceASueldo;
    }

    public void setAvanceASueldo(double avanceASueldo) {
        this.avanceASueldo = avanceASueldo;
    }

    public double getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(double totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public double getSueldoNeto() {
        return sueldoNeto;
    }

    public void setSueldoNeto(double sueldoNeto) {
        this.sueldoNeto = sueldoNeto;
    }

    public boolean getRevisado() {
        return revisado;
    }

    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }

    public Nomina getNomina() {
        return nomina;
    }

    public void setNomina(Nomina nomina) {
        this.nomina = nomina;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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
        if (!(object instanceof DetalleNomina)) {
            return false;
        }
        DetalleNomina other = (DetalleNomina) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleNomina[ codigo=" + codigo + " ]";
    }
    
}
