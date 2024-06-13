/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "formato_606")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formato606.findAll", query = "SELECT f FROM Formato606 f")
    , @NamedQuery(name = "Formato606.findByCodigo", query = "SELECT f FROM Formato606 f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "Formato606.findByRnc", query = "SELECT f FROM Formato606 f WHERE f.rnc = :rnc")
    , @NamedQuery(name = "Formato606.findByPeriodo", query = "SELECT f FROM Formato606 f WHERE f.periodo = :periodo")
    , @NamedQuery(name = "Formato606.findByCantidadRegistro", query = "SELECT f FROM Formato606 f WHERE f.cantidadRegistro = :cantidadRegistro")
    , @NamedQuery(name = "Formato606.findByFechaRegistro", query = "SELECT f FROM Formato606 f WHERE f.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Formato606.findByFecha", query = "SELECT f FROM Formato606 f WHERE f.fecha = :fecha")
    , @NamedQuery(name = "Formato606.findByAnio", query = "SELECT f FROM Formato606 f WHERE f.anio = :anio")
    , @NamedQuery(name = "Formato606.findByMes", query = "SELECT f FROM Formato606 f WHERE f.mes = :mes")
    , @NamedQuery(name = "Formato606.findByNombreUsuario", query = "SELECT f FROM Formato606 f WHERE f.nombreUsuario = :nombreUsuario")})
public class Formato606 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "rnc")
    private String rnc;
    @Basic(optional = false)
    @Column(name = "periodo")
    private int periodo;
    @Basic(optional = false)
    @Column(name = "cantidad_registro")
    private int cantidadRegistro;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "anio")
    private Integer anio;
    @Column(name = "mes")
    private Integer mes;
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @JoinColumn(name = "unidad_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadNegocio;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "formato606",cascade = CascadeType.ALL)
    private Collection<DetalleFormato606> detalleFormato606Collection;

    public Formato606() {
    }

    public Formato606(Integer codigo) {
        this.codigo = codigo;
    }

    public Formato606(Integer codigo, String rnc, int periodo, int cantidadRegistro) {
        this.codigo = codigo;
        this.rnc = rnc;
        this.periodo = periodo;
        this.cantidadRegistro = cantidadRegistro;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getCantidadRegistro() {
        return cantidadRegistro;
    }

    public void setCantidadRegistro(int cantidadRegistro) {
        this.cantidadRegistro = cantidadRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public UnidadDeNegocio getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(UnidadDeNegocio unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<DetalleFormato606> getDetalleFormato606Collection() {
        return detalleFormato606Collection;
    }

    public void setDetalleFormato606Collection(Collection<DetalleFormato606> detalleFormato606Collection) {
        this.detalleFormato606Collection = detalleFormato606Collection;
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
        if (!(object instanceof Formato606)) {
            return false;
        }
        Formato606 other = (Formato606) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Formato606[ codigo=" + codigo + " ]";
    }
    
}
