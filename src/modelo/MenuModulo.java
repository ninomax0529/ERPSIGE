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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "menu_modulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuModulo.findAll", query = "SELECT m FROM MenuModulo m")
    , @NamedQuery(name = "MenuModulo.findByCodigo", query = "SELECT m FROM MenuModulo m WHERE m.codigo = :codigo")
    , @NamedQuery(name = "MenuModulo.findByModulo", query = "SELECT m FROM MenuModulo m WHERE m.modulo = :modulo")
    , @NamedQuery(name = "MenuModulo.findByNombreModulo", query = "SELECT m FROM MenuModulo m WHERE m.nombreModulo = :nombreModulo")
    , @NamedQuery(name = "MenuModulo.findByMenu", query = "SELECT m FROM MenuModulo m WHERE m.menu = :menu")
    , @NamedQuery(name = "MenuModulo.findByIdMenu", query = "SELECT m FROM MenuModulo m WHERE m.idMenu = :idMenu")
    , @NamedQuery(name = "MenuModulo.findByPermiso", query = "SELECT m FROM MenuModulo m WHERE m.permiso = :permiso")})
public class MenuModulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "modulo")
    private int modulo;
    @Basic(optional = false)
    @Column(name = "nombre_modulo")
    private String nombreModulo;
    @Basic(optional = false)
    @Column(name = "menu")
    private String menu;
    @Basic(optional = false)
    @Column(name = "id_menu")
    private String idMenu;
    @Column(name = "permiso")
    private Integer permiso;
    @JoinColumn(name = "tipo_menu", referencedColumnName = "codigo")
    @ManyToOne
    private TipoMenu tipoMenu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuModulo")
    private Collection<RolMenuModulo> rolMenuModuloCollection;

    public MenuModulo() {
    }

    public MenuModulo(Integer codigo) {
        this.codigo = codigo;
    }

     public MenuModulo(String nombre) {
        this.codigo = codigo;
    }
    
    public MenuModulo(Integer codigo, int modulo, String nombreModulo, String menu, String idMenu) {
        this.codigo = codigo;
        this.modulo = modulo;
        this.nombreModulo = nombreModulo;
        this.menu = menu;
        this.idMenu = idMenu;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getModulo() {
        return modulo;
    }

    public void setModulo(int modulo) {
        this.modulo = modulo;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public Integer getPermiso() {
        return permiso;
    }

    public void setPermiso(Integer permiso) {
        this.permiso = permiso;
    }

    public TipoMenu getTipoMenu() {
        return tipoMenu;
    }

    public void setTipoMenu(TipoMenu tipoMenu) {
        this.tipoMenu = tipoMenu;
    }

    @XmlTransient
    public Collection<RolMenuModulo> getRolMenuModuloCollection() {
        return rolMenuModuloCollection;
    }

    public void setRolMenuModuloCollection(Collection<RolMenuModulo> rolMenuModuloCollection) {
        this.rolMenuModuloCollection = rolMenuModuloCollection;
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
        if (!(object instanceof MenuModulo)) {
            return false;
        }
        MenuModulo other = (MenuModulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.MenuModulo[ codigo=" + codigo + " ]";
    }
    
}
