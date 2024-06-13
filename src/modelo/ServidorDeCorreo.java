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
@Table(name = "servidor_de_correo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServidorDeCorreo.findAll", query = "SELECT s FROM ServidorDeCorreo s")
    , @NamedQuery(name = "ServidorDeCorreo.findByCodigo", query = "SELECT s FROM ServidorDeCorreo s WHERE s.codigo = :codigo")
    , @NamedQuery(name = "ServidorDeCorreo.findByFecha", query = "SELECT s FROM ServidorDeCorreo s WHERE s.fecha = :fecha")
    , @NamedQuery(name = "ServidorDeCorreo.findByClave", query = "SELECT s FROM ServidorDeCorreo s WHERE s.clave = :clave")
    , @NamedQuery(name = "ServidorDeCorreo.findByServidor", query = "SELECT s FROM ServidorDeCorreo s WHERE s.servidor = :servidor")
    , @NamedQuery(name = "ServidorDeCorreo.findByPuerto", query = "SELECT s FROM ServidorDeCorreo s WHERE s.puerto = :puerto")
    , @NamedQuery(name = "ServidorDeCorreo.findByHabilitado", query = "SELECT s FROM ServidorDeCorreo s WHERE s.habilitado = :habilitado")
    , @NamedQuery(name = "ServidorDeCorreo.findByExpresionCron", query = "SELECT s FROM ServidorDeCorreo s WHERE s.expresionCron = :expresionCron")})
public class ServidorDeCorreo implements Serializable {

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
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @Column(name = "servidor")
    private String servidor;
    @Basic(optional = false)
    @Column(name = "puerto")
    private int puerto;
    @Basic(optional = false)
    @Column(name = "habilitado")
    private boolean habilitado;
    @Basic(optional = false)
    @Column(name = "expresion_cron")
    private String expresionCron;

    public ServidorDeCorreo() {
    }

    public ServidorDeCorreo(Integer codigo) {
        this.codigo = codigo;
    }

    public ServidorDeCorreo(Integer codigo, Date fecha, String servidor, int puerto, boolean habilitado, String expresionCron) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.servidor = servidor;
        this.puerto = puerto;
        this.habilitado = habilitado;
        this.expresionCron = expresionCron;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getExpresionCron() {
        return expresionCron;
    }

    public void setExpresionCron(String expresionCron) {
        this.expresionCron = expresionCron;
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
        if (!(object instanceof ServidorDeCorreo)) {
            return false;
        }
        ServidorDeCorreo other = (ServidorDeCorreo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ServidorDeCorreo[ codigo=" + codigo + " ]";
    }
    
}
