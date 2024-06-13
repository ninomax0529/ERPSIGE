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
@Table(name = "lista_de_precio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaDePrecio.findAll", query = "SELECT l FROM ListaDePrecio l")
    , @NamedQuery(name = "ListaDePrecio.findByCodigo", query = "SELECT l FROM ListaDePrecio l WHERE l.codigo = :codigo")
    , @NamedQuery(name = "ListaDePrecio.findByNombre", query = "SELECT l FROM ListaDePrecio l WHERE l.nombre = :nombre")
    , @NamedQuery(name = "ListaDePrecio.findByFecha", query = "SELECT l FROM ListaDePrecio l WHERE l.fecha = :fecha")
    , @NamedQuery(name = "ListaDePrecio.findByHabilitada", query = "SELECT l FROM ListaDePrecio l WHERE l.habilitada = :habilitada")})
public class ListaDePrecio implements Serializable {

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "habilitada")
    private boolean habilitada;
    @Lob
    @Column(name = "COMENTARIO")
    private String comentario;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "tipo_lista", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoListaDePrecio tipoLista;
    @JoinColumn(name = "tipo_venta", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoVenta tipoVenta;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listaDePrecio")
    private Collection<DetalleListaDePrecio> detalleListaDePrecioCollection;

    public ListaDePrecio() {
    }

    public ListaDePrecio(Integer codigo) {
        this.codigo = codigo;
    }

    public ListaDePrecio(Integer codigo, String nombre, Date fecha, boolean habilitada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fecha = fecha;
        this.habilitada = habilitada;
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

    public boolean getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public TipoListaDePrecio getTipoLista() {
        return tipoLista;
    }

    public void setTipoLista(TipoListaDePrecio tipoLista) {
        this.tipoLista = tipoLista;
    }

    public TipoVenta getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(TipoVenta tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<DetalleListaDePrecio> getDetalleListaDePrecioCollection() {
        return detalleListaDePrecioCollection;
    }

    public void setDetalleListaDePrecioCollection(Collection<DetalleListaDePrecio> detalleListaDePrecioCollection) {
        this.detalleListaDePrecioCollection = detalleListaDePrecioCollection;
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
        if (!(object instanceof ListaDePrecio)) {
            return false;
        }
        ListaDePrecio other = (ListaDePrecio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ListaDePrecio[ codigo=" + codigo + " ]";
    }
    
}
