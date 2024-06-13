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
@Table(name = "concepto_conciliacion_bancaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConceptoConciliacionBancaria.findAll", query = "SELECT c FROM ConceptoConciliacionBancaria c")
    , @NamedQuery(name = "ConceptoConciliacionBancaria.findByCodigo", query = "SELECT c FROM ConceptoConciliacionBancaria c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "ConceptoConciliacionBancaria.findByNombre", query = "SELECT c FROM ConceptoConciliacionBancaria c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "ConceptoConciliacionBancaria.findByOrigen", query = "SELECT c FROM ConceptoConciliacionBancaria c WHERE c.origen = :origen")
    , @NamedQuery(name = "ConceptoConciliacionBancaria.findByOperacion", query = "SELECT c FROM ConceptoConciliacionBancaria c WHERE c.operacion = :operacion")
    , @NamedQuery(name = "ConceptoConciliacionBancaria.findByFechaRegistro", query = "SELECT c FROM ConceptoConciliacionBancaria c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "ConceptoConciliacionBancaria.findByUsuario", query = "SELECT c FROM ConceptoConciliacionBancaria c WHERE c.usuario = :usuario")
    , @NamedQuery(name = "ConceptoConciliacionBancaria.findByNombreUsuario", query = "SELECT c FROM ConceptoConciliacionBancaria c WHERE c.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "ConceptoConciliacionBancaria.findByHabilitado", query = "SELECT c FROM ConceptoConciliacionBancaria c WHERE c.habilitado = :habilitado")
    , @NamedQuery(name = "ConceptoConciliacionBancaria.findByNumero", query = "SELECT c FROM ConceptoConciliacionBancaria c WHERE c.numero = :numero")})
public class ConceptoConciliacionBancaria implements Serializable {

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
    @Column(name = "origen")
    private String origen;
    @Basic(optional = false)
    @Column(name = "operacion")
    private String operacion;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "habilitado")
    private boolean habilitado;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;

    public ConceptoConciliacionBancaria() {
    }

    public ConceptoConciliacionBancaria(Integer codigo) {
        this.codigo = codigo;
    }

    public ConceptoConciliacionBancaria(Integer codigo, String nombre, String origen, String operacion, Date fechaRegistro, int usuario, String nombreUsuario, boolean habilitado, int numero) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.origen = origen;
        this.operacion = operacion;
        this.fechaRegistro = fechaRegistro;
        this.usuario = usuario;
        this.nombreUsuario = nombreUsuario;
        this.habilitado = habilitado;
        this.numero = numero;
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

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
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
        if (!(object instanceof ConceptoConciliacionBancaria)) {
            return false;
        }
        ConceptoConciliacionBancaria other = (ConceptoConciliacionBancaria) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ConceptoConciliacionBancaria[ codigo=" + codigo + " ]";
    }
    
}
