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
@Table(name = "sub_menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubMenu.findAll", query = "SELECT s FROM SubMenu s")
    , @NamedQuery(name = "SubMenu.findByCodigo", query = "SELECT s FROM SubMenu s WHERE s.codigo = :codigo")
    , @NamedQuery(name = "SubMenu.findByNombre", query = "SELECT s FROM SubMenu s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SubMenu.findByMenu", query = "SELECT s FROM SubMenu s WHERE s.menu = :menu")
    , @NamedQuery(name = "SubMenu.findById", query = "SELECT s FROM SubMenu s WHERE s.id = :id")})
public class SubMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "menu")
    private Integer menu;
    @Column(name = "id")
    private String id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subMenu")
    private Collection<RolConfiguracion> rolConfiguracionCollection;

    public SubMenu() {
    }

    public SubMenu(Integer codigo) {
        this.codigo = codigo;
    }

    public SubMenu(Integer codigo, String nombre) {
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

    public Integer getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu = menu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<RolConfiguracion> getRolConfiguracionCollection() {
        return rolConfiguracionCollection;
    }

    public void setRolConfiguracionCollection(Collection<RolConfiguracion> rolConfiguracionCollection) {
        this.rolConfiguracionCollection = rolConfiguracionCollection;
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
        if (!(object instanceof SubMenu)) {
            return false;
        }
        SubMenu other = (SubMenu) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.SubMenu[ codigo=" + codigo + " ]";
    }
    
}
