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
@Table(name = "rol_menu_modulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolMenuModulo.findAll", query = "SELECT r FROM RolMenuModulo r")
    , @NamedQuery(name = "RolMenuModulo.findByCodigo", query = "SELECT r FROM RolMenuModulo r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "RolMenuModulo.findByNombreMenu", query = "SELECT r FROM RolMenuModulo r WHERE r.nombreMenu = :nombreMenu")
    , @NamedQuery(name = "RolMenuModulo.findByModulo", query = "SELECT r FROM RolMenuModulo r WHERE r.modulo = :modulo")
    , @NamedQuery(name = "RolMenuModulo.findByHabilitado", query = "SELECT r FROM RolMenuModulo r WHERE r.habilitado = :habilitado")})
public class RolMenuModulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre_menu")
    private String nombreMenu;
    @Basic(optional = false)
    @Column(name = "modulo")
    private int modulo;
    @Basic(optional = false)
    @Column(name = "habilitado")
    private boolean habilitado;
    @JoinColumn(name = "rol", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Rol rol;
    @JoinColumn(name = "menu_modulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private MenuModulo menuModulo;

    public RolMenuModulo() {
    }

    public RolMenuModulo(Integer codigo) {
        this.codigo = codigo;
    }

    public RolMenuModulo(Integer codigo, String nombreMenu, int modulo, boolean habilitado) {
        this.codigo = codigo;
        this.nombreMenu = nombreMenu;
        this.modulo = modulo;
        this.habilitado = habilitado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public int getModulo() {
        return modulo;
    }

    public void setModulo(int modulo) {
        this.modulo = modulo;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public MenuModulo getMenuModulo() {
        return menuModulo;
    }

    public void setMenuModulo(MenuModulo menuModulo) {
        this.menuModulo = menuModulo;
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
        if (!(object instanceof RolMenuModulo)) {
            return false;
        }
        RolMenuModulo other = (RolMenuModulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RolMenuModulo[ codigo=" + codigo + " ]";
    }
    
}
