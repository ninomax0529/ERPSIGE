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
@Table(name = "instalador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Instalador.findAll", query = "SELECT i FROM Instalador i")
    , @NamedQuery(name = "Instalador.findByCodigo", query = "SELECT i FROM Instalador i WHERE i.codigo = :codigo")
    , @NamedQuery(name = "Instalador.findByNombre", query = "SELECT i FROM Instalador i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "Instalador.findByFecha", query = "SELECT i FROM Instalador i WHERE i.fecha = :fecha")})
public class Instalador implements Serializable {

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
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "tipo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoInstalador tipo;
    @OneToMany(mappedBy = "desintalador")
    private Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection;
    @OneToMany(mappedBy = "instalador")
    private Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection1;
    @OneToMany(mappedBy = "tecnico")
    private Collection<AsistenciaTecnica> asistenciaTecnicaCollection;

    public Instalador() {
    }

    public Instalador(Integer codigo) {
        this.codigo = codigo;
    }

    public Instalador(Integer codigo, String nombre, Date fecha) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fecha = fecha;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoInstalador getTipo() {
        return tipo;
    }

    public void setTipo(TipoInstalador tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public Collection<DetalleContratoDeServicio> getDetalleContratoDeServicioCollection() {
        return detalleContratoDeServicioCollection;
    }

    public void setDetalleContratoDeServicioCollection(Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection) {
        this.detalleContratoDeServicioCollection = detalleContratoDeServicioCollection;
    }

    @XmlTransient
    public Collection<DetalleContratoDeServicio> getDetalleContratoDeServicioCollection1() {
        return detalleContratoDeServicioCollection1;
    }

    public void setDetalleContratoDeServicioCollection1(Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection1) {
        this.detalleContratoDeServicioCollection1 = detalleContratoDeServicioCollection1;
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
        if (!(object instanceof Instalador)) {
            return false;
        }
        Instalador other = (Instalador) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Instalador[ codigo=" + codigo + " ]";
    }
    
}
