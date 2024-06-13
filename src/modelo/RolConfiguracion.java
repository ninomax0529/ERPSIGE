/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "rol_configuracion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolConfiguracion.findAll", query = "SELECT r FROM RolConfiguracion r")
    , @NamedQuery(name = "RolConfiguracion.findByCodigo", query = "SELECT r FROM RolConfiguracion r WHERE r.codigo = :codigo")})
public class RolConfiguracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @JoinColumn(name = "permiso", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Permiso permiso;
    @JoinColumn(name = "rol", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Rol rol;
    @JoinColumn(name = "sub_menu", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private SubMenu subMenu;

    public RolConfiguracion() {
    }

    public RolConfiguracion(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public SubMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(SubMenu subMenu) {
        this.subMenu = subMenu;
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
        if (!(object instanceof RolConfiguracion)) {
            return false;
        }
        RolConfiguracion other = (RolConfiguracion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RolConfiguracion[ codigo=" + codigo + " ]";
    }
    
}
