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
@Table(name = "dias_feriado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiasFeriado.findAll", query = "SELECT d FROM DiasFeriado d")
    , @NamedQuery(name = "DiasFeriado.findByCodigo", query = "SELECT d FROM DiasFeriado d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DiasFeriado.findByFecha", query = "SELECT d FROM DiasFeriado d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "DiasFeriado.findByDia", query = "SELECT d FROM DiasFeriado d WHERE d.dia = :dia")
    , @NamedQuery(name = "DiasFeriado.findByNombreDia", query = "SELECT d FROM DiasFeriado d WHERE d.nombreDia = :nombreDia")
    , @NamedQuery(name = "DiasFeriado.findByMes", query = "SELECT d FROM DiasFeriado d WHERE d.mes = :mes")
    , @NamedQuery(name = "DiasFeriado.findByNombreMes", query = "SELECT d FROM DiasFeriado d WHERE d.nombreMes = :nombreMes")
    , @NamedQuery(name = "DiasFeriado.findByUsuario", query = "SELECT d FROM DiasFeriado d WHERE d.usuario = :usuario")
    , @NamedQuery(name = "DiasFeriado.findByNombreUsuario", query = "SELECT d FROM DiasFeriado d WHERE d.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "DiasFeriado.findByFechaRegistro", query = "SELECT d FROM DiasFeriado d WHERE d.fechaRegistro = :fechaRegistro")})
public class DiasFeriado implements Serializable {

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
    @Column(name = "dia")
    private int dia;
    @Basic(optional = false)
    @Column(name = "nombre_dia")
    private String nombreDia;
    @Basic(optional = false)
    @Column(name = "mes")
    private int mes;
    @Basic(optional = false)
    @Column(name = "nombre_mes")
    private String nombreMes;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    public DiasFeriado() {
    }

    public DiasFeriado(Integer codigo) {
        this.codigo = codigo;
    }

    public DiasFeriado(Integer codigo, Date fecha, int dia, String nombreDia, int mes, String nombreMes, int usuario, String nombreUsuario, Date fechaRegistro) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.dia = dia;
        this.nombreDia = nombreDia;
        this.mes = mes;
        this.nombreMes = nombreMes;
        this.usuario = usuario;
        this.nombreUsuario = nombreUsuario;
        this.fechaRegistro = fechaRegistro;
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

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getNombreMes() {
        return nombreMes;
    }

    public void setNombreMes(String nombreMes) {
        this.nombreMes = nombreMes;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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
        if (!(object instanceof DiasFeriado)) {
            return false;
        }
        DiasFeriado other = (DiasFeriado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DiasFeriado[ codigo=" + codigo + " ]";
    }
    
}
