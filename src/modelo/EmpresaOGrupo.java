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
@Table(name = "empresa_o_grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpresaOGrupo.findAll", query = "SELECT e FROM EmpresaOGrupo e")
    , @NamedQuery(name = "EmpresaOGrupo.findByCodigo", query = "SELECT e FROM EmpresaOGrupo e WHERE e.codigo = :codigo")
    , @NamedQuery(name = "EmpresaOGrupo.findByNombre", query = "SELECT e FROM EmpresaOGrupo e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "EmpresaOGrupo.findByNombreUsuario", query = "SELECT e FROM EmpresaOGrupo e WHERE e.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "EmpresaOGrupo.findByFechaRegistro", query = "SELECT e FROM EmpresaOGrupo e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "EmpresaOGrupo.findByTelefono", query = "SELECT e FROM EmpresaOGrupo e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "EmpresaOGrupo.findByEmail", query = "SELECT e FROM EmpresaOGrupo e WHERE e.email = :email")
    , @NamedQuery(name = "EmpresaOGrupo.findByRnc", query = "SELECT e FROM EmpresaOGrupo e WHERE e.rnc = :rnc")})
public class EmpresaOGrupo implements Serializable {

    @Lob
    @Column(name = "logo")
    private byte[] logo;

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
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Lob
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email")
    private String email;
    @Column(name = "rnc")
    private String rnc;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(mappedBy = "empresaOGrupo")
    private Collection<UnidadDeNegocio> unidadDeNegocioCollection;

    public EmpresaOGrupo() {
    }

    public EmpresaOGrupo(Integer codigo) {
        this.codigo = codigo;
    }

    public EmpresaOGrupo(Integer codigo, String nombre, String descripcion, String nombreUsuario, Date fechaRegistro) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nombreUsuario = nombreUsuario;
        this.fechaRegistro = fechaRegistro;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<UnidadDeNegocio> getUnidadDeNegocioCollection() {
        return unidadDeNegocioCollection;
    }

    public void setUnidadDeNegocioCollection(Collection<UnidadDeNegocio> unidadDeNegocioCollection) {
        this.unidadDeNegocioCollection = unidadDeNegocioCollection;
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
        if (!(object instanceof EmpresaOGrupo)) {
            return false;
        }
        EmpresaOGrupo other = (EmpresaOGrupo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.EmpresaOGrupo[ codigo=" + codigo + " ]";
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
    
}
