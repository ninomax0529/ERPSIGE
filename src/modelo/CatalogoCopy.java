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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "catalogo_copy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatalogoCopy.findAll", query = "SELECT c FROM CatalogoCopy c")
    , @NamedQuery(name = "CatalogoCopy.findByCodigo", query = "SELECT c FROM CatalogoCopy c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "CatalogoCopy.findByCuenta", query = "SELECT c FROM CatalogoCopy c WHERE c.cuenta = :cuenta")
    , @NamedQuery(name = "CatalogoCopy.findByCuentaControl", query = "SELECT c FROM CatalogoCopy c WHERE c.cuentaControl = :cuentaControl")
    , @NamedQuery(name = "CatalogoCopy.findByClasificacion", query = "SELECT c FROM CatalogoCopy c WHERE c.clasificacion = :clasificacion")
    , @NamedQuery(name = "CatalogoCopy.findByTipo", query = "SELECT c FROM CatalogoCopy c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "CatalogoCopy.findByOrigen", query = "SELECT c FROM CatalogoCopy c WHERE c.origen = :origen")
    , @NamedQuery(name = "CatalogoCopy.findByNivel", query = "SELECT c FROM CatalogoCopy c WHERE c.nivel = :nivel")
    , @NamedQuery(name = "CatalogoCopy.findByFechaCreacion", query = "SELECT c FROM CatalogoCopy c WHERE c.fechaCreacion = :fechaCreacion")})
public class CatalogoCopy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "cuenta")
    private String cuenta;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cuenta_control")
    private Integer cuentaControl;
    @Column(name = "clasificacion")
    private String clasificacion;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "origen")
    private String origen;
    @Column(name = "nivel")
    private Integer nivel;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @JoinColumn(name = "grupo", referencedColumnName = "codigo")
    @ManyToOne
    private GrupoCuenta grupo;
    @JoinColumn(name = "sub_grupo", referencedColumnName = "codigo")
    @ManyToOne
    private SubGrupoCuenta subGrupo;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

    public CatalogoCopy() {
    }

    public CatalogoCopy(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCuentaControl() {
        return cuentaControl;
    }

    public void setCuentaControl(Integer cuentaControl) {
        this.cuentaControl = cuentaControl;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public GrupoCuenta getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoCuenta grupo) {
        this.grupo = grupo;
    }

    public SubGrupoCuenta getSubGrupo() {
        return subGrupo;
    }

    public void setSubGrupo(SubGrupoCuenta subGrupo) {
        this.subGrupo = subGrupo;
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
        if (!(object instanceof CatalogoCopy)) {
            return false;
        }
        CatalogoCopy other = (CatalogoCopy) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CatalogoCopy[ codigo=" + codigo + " ]";
    }
    
}
