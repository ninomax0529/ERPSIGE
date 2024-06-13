/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "ubicacion_asistencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UbicacionAsistencia.findAll", query = "SELECT u FROM UbicacionAsistencia u")
    , @NamedQuery(name = "UbicacionAsistencia.findByCodigo", query = "SELECT u FROM UbicacionAsistencia u WHERE u.codigo = :codigo")
    , @NamedQuery(name = "UbicacionAsistencia.findByNombre", query = "SELECT u FROM UbicacionAsistencia u WHERE u.nombre = :nombre")})
public class UbicacionAsistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "ubicacion")
    private Collection<AsistenciaTecnica> asistenciaTecnicaCollection;

    public UbicacionAsistencia() {
    }

    public UbicacionAsistencia(Integer codigo) {
        this.codigo = codigo;
    }

    public UbicacionAsistencia(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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

    @XmlTransient
    public Collection<AsistenciaTecnica> getAsistenciaTecnicaCollection() {
        return asistenciaTecnicaCollection;
    }

    public void setAsistenciaTecnicaCollection(Collection<AsistenciaTecnica> asistenciaTecnicaCollection) {
        this.asistenciaTecnicaCollection = asistenciaTecnicaCollection;
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
        if (!(object instanceof UbicacionAsistencia)) {
            return false;
        }
        UbicacionAsistencia other = (UbicacionAsistencia) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.UbicacionAsistencia[ codigo=" + codigo + " ]";
    }
    
}
