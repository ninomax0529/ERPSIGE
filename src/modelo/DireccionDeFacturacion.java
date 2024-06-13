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
@Table(name = "direccion_de_facturacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DireccionDeFacturacion.findAll", query = "SELECT d FROM DireccionDeFacturacion d")
    , @NamedQuery(name = "DireccionDeFacturacion.findByCodigo", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DireccionDeFacturacion.findByContratoDeServicio", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.contratoDeServicio = :contratoDeServicio")
    , @NamedQuery(name = "DireccionDeFacturacion.findByCiudad", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.ciudad = :ciudad")
    , @NamedQuery(name = "DireccionDeFacturacion.findByMunicipio", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.municipio = :municipio")
    , @NamedQuery(name = "DireccionDeFacturacion.findBySector", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.sector = :sector")
    , @NamedQuery(name = "DireccionDeFacturacion.findByUrbanizacion", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.urbanizacion = :urbanizacion")
    , @NamedQuery(name = "DireccionDeFacturacion.findByResidencial", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.residencial = :residencial")
    , @NamedQuery(name = "DireccionDeFacturacion.findByBario", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.bario = :bario")
    , @NamedQuery(name = "DireccionDeFacturacion.findByCalle", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.calle = :calle")
    , @NamedQuery(name = "DireccionDeFacturacion.findByAvenida", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.avenida = :avenida")
    , @NamedQuery(name = "DireccionDeFacturacion.findByKilometro", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.kilometro = :kilometro")
    , @NamedQuery(name = "DireccionDeFacturacion.findByNumeroCasa", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.numeroCasa = :numeroCasa")
    , @NamedQuery(name = "DireccionDeFacturacion.findByNumeroEdificio", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.numeroEdificio = :numeroEdificio")
    , @NamedQuery(name = "DireccionDeFacturacion.findByNombreEdificio", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.nombreEdificio = :nombreEdificio")
    , @NamedQuery(name = "DireccionDeFacturacion.findByNombreTorre", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.nombreTorre = :nombreTorre")
    , @NamedQuery(name = "DireccionDeFacturacion.findByNombrePlaza", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.nombrePlaza = :nombrePlaza")
    , @NamedQuery(name = "DireccionDeFacturacion.findByNumeroApartamento", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.numeroApartamento = :numeroApartamento")
    , @NamedQuery(name = "DireccionDeFacturacion.findByNumeroSuite", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.numeroSuite = :numeroSuite")
    , @NamedQuery(name = "DireccionDeFacturacion.findByNumeroLocal", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.numeroLocal = :numeroLocal")
    , @NamedQuery(name = "DireccionDeFacturacion.findByLetra", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.letra = :letra")
    , @NamedQuery(name = "DireccionDeFacturacion.findByNumeroPiso", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.numeroPiso = :numeroPiso")
    , @NamedQuery(name = "DireccionDeFacturacion.findByFechaRegistro", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "DireccionDeFacturacion.findByUsuario", query = "SELECT d FROM DireccionDeFacturacion d WHERE d.usuario = :usuario")})
public class DireccionDeFacturacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "contrato_de_servicio")
    private Integer contratoDeServicio;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "municipio")
    private String municipio;
    @Column(name = "sector")
    private String sector;
    @Column(name = "urbanizacion")
    private String urbanizacion;
    @Column(name = "residencial")
    private String residencial;
    @Column(name = "bario")
    private String bario;
    @Column(name = "calle")
    private String calle;
    @Column(name = "avenida")
    private String avenida;
    @Column(name = "kilometro")
    private String kilometro;
    @Column(name = "numero_casa")
    private String numeroCasa;
    @Column(name = "numero_edificio")
    private String numeroEdificio;
    @Column(name = "nombre_edificio")
    private String nombreEdificio;
    @Column(name = "nombre_torre")
    private String nombreTorre;
    @Column(name = "nombre_plaza")
    private String nombrePlaza;
    @Column(name = "numero_apartamento")
    private String numeroApartamento;
    @Column(name = "numero_suite")
    private String numeroSuite;
    @Column(name = "numero_local")
    private String numeroLocal;
    @Column(name = "letra")
    private String letra;
    @Column(name = "numero_piso")
    private String numeroPiso;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "usuario")
    private Integer usuario;

    public DireccionDeFacturacion() {
    }

    public DireccionDeFacturacion(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getContratoDeServicio() {
        return contratoDeServicio;
    }

    public void setContratoDeServicio(Integer contratoDeServicio) {
        this.contratoDeServicio = contratoDeServicio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getUrbanizacion() {
        return urbanizacion;
    }

    public void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }

    public String getResidencial() {
        return residencial;
    }

    public void setResidencial(String residencial) {
        this.residencial = residencial;
    }

    public String getBario() {
        return bario;
    }

    public void setBario(String bario) {
        this.bario = bario;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAvenida() {
        return avenida;
    }

    public void setAvenida(String avenida) {
        this.avenida = avenida;
    }

    public String getKilometro() {
        return kilometro;
    }

    public void setKilometro(String kilometro) {
        this.kilometro = kilometro;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getNumeroEdificio() {
        return numeroEdificio;
    }

    public void setNumeroEdificio(String numeroEdificio) {
        this.numeroEdificio = numeroEdificio;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

    public String getNombreTorre() {
        return nombreTorre;
    }

    public void setNombreTorre(String nombreTorre) {
        this.nombreTorre = nombreTorre;
    }

    public String getNombrePlaza() {
        return nombrePlaza;
    }

    public void setNombrePlaza(String nombrePlaza) {
        this.nombrePlaza = nombrePlaza;
    }

    public String getNumeroApartamento() {
        return numeroApartamento;
    }

    public void setNumeroApartamento(String numeroApartamento) {
        this.numeroApartamento = numeroApartamento;
    }

    public String getNumeroSuite() {
        return numeroSuite;
    }

    public void setNumeroSuite(String numeroSuite) {
        this.numeroSuite = numeroSuite;
    }

    public String getNumeroLocal() {
        return numeroLocal;
    }

    public void setNumeroLocal(String numeroLocal) {
        this.numeroLocal = numeroLocal;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getNumeroPiso() {
        return numeroPiso;
    }

    public void setNumeroPiso(String numeroPiso) {
        this.numeroPiso = numeroPiso;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof DireccionDeFacturacion)) {
            return false;
        }
        DireccionDeFacturacion other = (DireccionDeFacturacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DireccionDeFacturacion[ codigo=" + codigo + " ]";
    }
    
}
