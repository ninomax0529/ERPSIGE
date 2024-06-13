/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "modulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modulo.findAll", query = "SELECT m FROM Modulo m")
    , @NamedQuery(name = "Modulo.findByCodigo", query = "SELECT m FROM Modulo m WHERE m.codigo = :codigo")
    , @NamedQuery(name = "Modulo.findByNombre", query = "SELECT m FROM Modulo m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "Modulo.findByPosicion", query = "SELECT m FROM Modulo m WHERE m.posicion = :posicion")
    , @NamedQuery(name = "Modulo.findByImg", query = "SELECT m FROM Modulo m WHERE m.img = :img")
    , @NamedQuery(name = "Modulo.findByIdModulo", query = "SELECT m FROM Modulo m WHERE m.idModulo = :idModulo")})
public class Modulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Column(name = "ruta")
    private String ruta;
    @Basic(optional = false)
    @Column(name = "posicion")
    private int posicion;
    @Column(name = "img")
    private String img;
    @Column(name = "id_modulo")
    private String idModulo;
    @OneToMany(mappedBy = "modulo")
    private Collection<EntradaDiario> entradaDiarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modulo")
    private Collection<TipoConcepto> tipoConceptoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modulo")
    private Collection<SubModulo> subModuloCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modulo")
    private Collection<ConfiguracionCuentaContable> configuracionCuentaContableCollection;

    public Modulo() {
    }

    public Modulo(Integer codigo) {
        this.codigo = codigo;
    }

    public Modulo(Integer codigo, String nombre, int posicion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.posicion = posicion;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    @XmlTransient
    public Collection<EntradaDiario> getEntradaDiarioCollection() {
        return entradaDiarioCollection;
    }

    public void setEntradaDiarioCollection(Collection<EntradaDiario> entradaDiarioCollection) {
        this.entradaDiarioCollection = entradaDiarioCollection;
    }

    @XmlTransient
    public Collection<TipoConcepto> getTipoConceptoCollection() {
        return tipoConceptoCollection;
    }

    public void setTipoConceptoCollection(Collection<TipoConcepto> tipoConceptoCollection) {
        this.tipoConceptoCollection = tipoConceptoCollection;
    }

    @XmlTransient
    public Collection<SubModulo> getSubModuloCollection() {
        return subModuloCollection;
    }

    public void setSubModuloCollection(Collection<SubModulo> subModuloCollection) {
        this.subModuloCollection = subModuloCollection;
    }

    @XmlTransient
    public Collection<ConfiguracionCuentaContable> getConfiguracionCuentaContableCollection() {
        return configuracionCuentaContableCollection;
    }

    public void setConfiguracionCuentaContableCollection(Collection<ConfiguracionCuentaContable> configuracionCuentaContableCollection) {
        this.configuracionCuentaContableCollection = configuracionCuentaContableCollection;
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
        if (!(object instanceof Modulo)) {
            return false;
        }
        Modulo other = (Modulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Modulo[ codigo=" + codigo + " ]";
    }
    
}
