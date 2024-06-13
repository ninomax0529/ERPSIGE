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
@Table(name = "inventario_final")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventarioFinal.findAll", query = "SELECT i FROM InventarioFinal i")
    , @NamedQuery(name = "InventarioFinal.findByCodigo", query = "SELECT i FROM InventarioFinal i WHERE i.codigo = :codigo")
    , @NamedQuery(name = "InventarioFinal.findByFecha", query = "SELECT i FROM InventarioFinal i WHERE i.fecha = :fecha")
    , @NamedQuery(name = "InventarioFinal.findByFechaRegistro", query = "SELECT i FROM InventarioFinal i WHERE i.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "InventarioFinal.findByUsuario", query = "SELECT i FROM InventarioFinal i WHERE i.usuario = :usuario")})
public class InventarioFinal implements Serializable {

    @Column(name = "anulado")
    private Boolean anulado;
    @Column(name = "fecha_anulado")
    @Temporal(TemporalType.DATE)
    private Date fechaAnulado;
    @Column(name = "anulado_por")
    private String anuladoPor;

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
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventarioFinal")
    private Collection<DetalleInventarioFinal> detalleInventarioFinalCollection;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

    public InventarioFinal() {
    }

    public InventarioFinal(Integer codigo) {
        this.codigo = codigo;
    }

    public InventarioFinal(Integer codigo, Date fecha, int usuario) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.usuario = usuario;
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

    @XmlTransient
    public Collection<DetalleInventarioFinal> getDetalleInventarioFinalCollection() {
        return detalleInventarioFinalCollection;
    }

    public void setDetalleInventarioFinalCollection(Collection<DetalleInventarioFinal> detalleInventarioFinalCollection) {
        this.detalleInventarioFinalCollection = detalleInventarioFinalCollection;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
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
        if (!(object instanceof InventarioFinal)) {
            return false;
        }
        InventarioFinal other = (InventarioFinal) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.InventarioFinal[ codigo=" + codigo + " ]";
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public Date getFechaAnulado() {
        return fechaAnulado;
    }

    public void setFechaAnulado(Date fechaAnulado) {
        this.fechaAnulado = fechaAnulado;
    }

    public String getAnuladoPor() {
        return anuladoPor;
    }

    public void setAnuladoPor(String anuladoPor) {
        this.anuladoPor = anuladoPor;
    }
    
}
