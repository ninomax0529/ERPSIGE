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
@Table(name = "dependiente_empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DependienteEmpleado.findAll", query = "SELECT d FROM DependienteEmpleado d")
    , @NamedQuery(name = "DependienteEmpleado.findByCodigo", query = "SELECT d FROM DependienteEmpleado d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DependienteEmpleado.findByNombre", query = "SELECT d FROM DependienteEmpleado d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "DependienteEmpleado.findByApellido", query = "SELECT d FROM DependienteEmpleado d WHERE d.apellido = :apellido")
    , @NamedQuery(name = "DependienteEmpleado.findByFechaRegistro", query = "SELECT d FROM DependienteEmpleado d WHERE d.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "DependienteEmpleado.findByCedula", query = "SELECT d FROM DependienteEmpleado d WHERE d.cedula = :cedula")
    , @NamedQuery(name = "DependienteEmpleado.findBySexo", query = "SELECT d FROM DependienteEmpleado d WHERE d.sexo = :sexo")
    , @NamedQuery(name = "DependienteEmpleado.findByEdad", query = "SELECT d FROM DependienteEmpleado d WHERE d.edad = :edad")
    , @NamedQuery(name = "DependienteEmpleado.findByFechaDeNacimiento", query = "SELECT d FROM DependienteEmpleado d WHERE d.fechaDeNacimiento = :fechaDeNacimiento")
    , @NamedQuery(name = "DependienteEmpleado.findByMayorDeEdad", query = "SELECT d FROM DependienteEmpleado d WHERE d.mayorDeEdad = :mayorDeEdad")})
public class DependienteEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "cedula")
    private String cedula;
    @Column(name = "sexo")
    private Integer sexo;
    @Basic(optional = false)
    @Column(name = "edad")
    private int edad;
    @Column(name = "fecha_de_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaDeNacimiento;
    @Basic(optional = false)
    @Column(name = "mayor_de_edad")
    private boolean mayorDeEdad;
    @JoinColumn(name = "empleado", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Empleado empleado;

    public DependienteEmpleado() {
    }

    public DependienteEmpleado(Integer codigo) {
        this.codigo = codigo;
    }

    public DependienteEmpleado(Integer codigo, String nombre, String apellido, Date fechaRegistro, int edad, boolean mayorDeEdad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaRegistro = fechaRegistro;
        this.edad = edad;
        this.mayorDeEdad = mayorDeEdad;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public boolean getMayorDeEdad() {
        return mayorDeEdad;
    }

    public void setMayorDeEdad(boolean mayorDeEdad) {
        this.mayorDeEdad = mayorDeEdad;
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
        if (!(object instanceof DependienteEmpleado)) {
            return false;
        }
        DependienteEmpleado other = (DependienteEmpleado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DependienteEmpleado[ codigo=" + codigo + " ]";
    }
    
}
