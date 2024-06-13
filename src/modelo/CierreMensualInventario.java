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
@Table(name = "cierre_mensual_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CierreMensualInventario.findAll", query = "SELECT c FROM CierreMensualInventario c")
    , @NamedQuery(name = "CierreMensualInventario.findByCodigo", query = "SELECT c FROM CierreMensualInventario c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "CierreMensualInventario.findByFecha", query = "SELECT c FROM CierreMensualInventario c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "CierreMensualInventario.findByHora", query = "SELECT c FROM CierreMensualInventario c WHERE c.hora = :hora")
    , @NamedQuery(name = "CierreMensualInventario.findByEstado", query = "SELECT c FROM CierreMensualInventario c WHERE c.estado = :estado")
    , @NamedQuery(name = "CierreMensualInventario.findByAnulado", query = "SELECT c FROM CierreMensualInventario c WHERE c.anulado = :anulado")
    , @NamedQuery(name = "CierreMensualInventario.findByCantidadArticulo", query = "SELECT c FROM CierreMensualInventario c WHERE c.cantidadArticulo = :cantidadArticulo")
    , @NamedQuery(name = "CierreMensualInventario.findByFechaRegistro", query = "SELECT c FROM CierreMensualInventario c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "CierreMensualInventario.findByValorInventario", query = "SELECT c FROM CierreMensualInventario c WHERE c.valorInventario = :valorInventario")
    , @NamedQuery(name = "CierreMensualInventario.findByFechaAnulado", query = "SELECT c FROM CierreMensualInventario c WHERE c.fechaAnulado = :fechaAnulado")
    , @NamedQuery(name = "CierreMensualInventario.findByAnuladoPor", query = "SELECT c FROM CierreMensualInventario c WHERE c.anuladoPor = :anuladoPor")})
public class CierreMensualInventario implements Serializable {

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
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "anulado")
    private boolean anulado;
    @Basic(optional = false)
    @Column(name = "cantidad_articulo")
    private int cantidadArticulo;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Lob
    @Column(name = "comentario")
    private String comentario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_inventario")
    private Double valorInventario;
    @Column(name = "fecha_anulado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulado;
    @Column(name = "anulado_por")
    private String anuladoPor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cierreMensual")
    private Collection<DetalleCierreMensualInventario> detalleCierreMensualInventarioCollection;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

    public CierreMensualInventario() {
    }

    public CierreMensualInventario(Integer codigo) {
        this.codigo = codigo;
    }

    public CierreMensualInventario(Integer codigo, Date fecha, Date hora, int estado, boolean anulado, int cantidadArticulo) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.anulado = anulado;
        this.cantidadArticulo = cantidadArticulo;
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

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public int getCantidadArticulo() {
        return cantidadArticulo;
    }

    public void setCantidadArticulo(int cantidadArticulo) {
        this.cantidadArticulo = cantidadArticulo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Double getValorInventario() {
        return valorInventario;
    }

    public void setValorInventario(Double valorInventario) {
        this.valorInventario = valorInventario;
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

    @XmlTransient
    public Collection<DetalleCierreMensualInventario> getDetalleCierreMensualInventarioCollection() {
        return detalleCierreMensualInventarioCollection;
    }

    public void setDetalleCierreMensualInventarioCollection(Collection<DetalleCierreMensualInventario> detalleCierreMensualInventarioCollection) {
        this.detalleCierreMensualInventarioCollection = detalleCierreMensualInventarioCollection;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof CierreMensualInventario)) {
            return false;
        }
        CierreMensualInventario other = (CierreMensualInventario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CierreMensualInventario[ codigo=" + codigo + " ]";
    }
    
}
