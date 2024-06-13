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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "direccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Direccion.findAll", query = "SELECT d FROM Direccion d")
    , @NamedQuery(name = "Direccion.findByCodigo", query = "SELECT d FROM Direccion d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "Direccion.findByPais", query = "SELECT d FROM Direccion d WHERE d.pais = :pais")
    , @NamedQuery(name = "Direccion.findByRegion", query = "SELECT d FROM Direccion d WHERE d.region = :region")
    , @NamedQuery(name = "Direccion.findByProvincia", query = "SELECT d FROM Direccion d WHERE d.provincia = :provincia")
    , @NamedQuery(name = "Direccion.findByMunicipio", query = "SELECT d FROM Direccion d WHERE d.municipio = :municipio")
    , @NamedQuery(name = "Direccion.findByCiudad", query = "SELECT d FROM Direccion d WHERE d.ciudad = :ciudad")
    , @NamedQuery(name = "Direccion.findBySector", query = "SELECT d FROM Direccion d WHERE d.sector = :sector")})
public class Direccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "pais")
    private String pais;
    @Basic(optional = false)
    @Column(name = "region")
    private String region;
    @Basic(optional = false)
    @Column(name = "provincia")
    private String provincia;
    @Basic(optional = false)
    @Column(name = "municipio")
    private String municipio;
    @Basic(optional = false)
    @Lob
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @Column(name = "sector")
    private String sector;

    public Direccion() {
    }

    public Direccion(Integer codigo) {
        this.codigo = codigo;
    }

    public Direccion(Integer codigo, String pais, String region, String provincia, String municipio, String direccion, String ciudad, String sector) {
        this.codigo = codigo;
        this.pais = pais;
        this.region = region;
        this.provincia = provincia;
        this.municipio = municipio;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.sector = sector;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
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
        if (!(object instanceof Direccion)) {
            return false;
        }
        Direccion other = (Direccion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Direccion[ codigo=" + codigo + " ]";
    }
    
}
