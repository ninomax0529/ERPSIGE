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
@Table(name = "ajuste_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AjusteInventario.findAll", query = "SELECT a FROM AjusteInventario a")
    , @NamedQuery(name = "AjusteInventario.findByCodigo", query = "SELECT a FROM AjusteInventario a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "AjusteInventario.findByFecha", query = "SELECT a FROM AjusteInventario a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "AjusteInventario.findByTipoAjuste", query = "SELECT a FROM AjusteInventario a WHERE a.tipoAjuste = :tipoAjuste")
    , @NamedQuery(name = "AjusteInventario.findByFechaRegistro", query = "SELECT a FROM AjusteInventario a WHERE a.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "AjusteInventario.findByUsuario", query = "SELECT a FROM AjusteInventario a WHERE a.usuario = :usuario")
    , @NamedQuery(name = "AjusteInventario.findByConcepto", query = "SELECT a FROM AjusteInventario a WHERE a.concepto = :concepto")
    , @NamedQuery(name = "AjusteInventario.findByAnulado", query = "SELECT a FROM AjusteInventario a WHERE a.anulado = :anulado")})
public class AjusteInventario implements Serializable {

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
    @Column(name = "tipo_ajuste")
    private int tipoAjuste;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "concepto")
    private int concepto;
    @Basic(optional = false)
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "anulado")
    private boolean anulado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ajusteInventario")
    private Collection<DetalleAjusteInventario> detalleAjusteInventarioCollection;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

    public AjusteInventario() {
    }

    public AjusteInventario(Integer codigo) {
        this.codigo = codigo;
    }

    public AjusteInventario(Integer codigo, Date fecha, int tipoAjuste, Date fechaRegistro, int usuario, int concepto, String observacion, boolean anulado) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.tipoAjuste = tipoAjuste;
        this.fechaRegistro = fechaRegistro;
        this.usuario = usuario;
        this.concepto = concepto;
        this.observacion = observacion;
        this.anulado = anulado;
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

    public int getTipoAjuste() {
        return tipoAjuste;
    }

    public void setTipoAjuste(int tipoAjuste) {
        this.tipoAjuste = tipoAjuste;
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

    public int getConcepto() {
        return concepto;
    }

    public void setConcepto(int concepto) {
        this.concepto = concepto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    @XmlTransient
    public Collection<DetalleAjusteInventario> getDetalleAjusteInventarioCollection() {
        return detalleAjusteInventarioCollection;
    }

    public void setDetalleAjusteInventarioCollection(Collection<DetalleAjusteInventario> detalleAjusteInventarioCollection) {
        this.detalleAjusteInventarioCollection = detalleAjusteInventarioCollection;
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
        if (!(object instanceof AjusteInventario)) {
            return false;
        }
        AjusteInventario other = (AjusteInventario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.AjusteInventario[ codigo=" + codigo + " ]";
    }
    
}
