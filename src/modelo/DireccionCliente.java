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
import javax.persistence.Lob;
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
@Table(name = "direccion_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DireccionCliente.findAll", query = "SELECT d FROM DireccionCliente d")
    , @NamedQuery(name = "DireccionCliente.findByCodigo", query = "SELECT d FROM DireccionCliente d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DireccionCliente.findByRegion", query = "SELECT d FROM DireccionCliente d WHERE d.region = :region")
    , @NamedQuery(name = "DireccionCliente.findByCiudad", query = "SELECT d FROM DireccionCliente d WHERE d.ciudad = :ciudad")
    , @NamedQuery(name = "DireccionCliente.findByMunicipio", query = "SELECT d FROM DireccionCliente d WHERE d.municipio = :municipio")
    , @NamedQuery(name = "DireccionCliente.findBySector", query = "SELECT d FROM DireccionCliente d WHERE d.sector = :sector")
    , @NamedQuery(name = "DireccionCliente.findByCalle", query = "SELECT d FROM DireccionCliente d WHERE d.calle = :calle")
    , @NamedQuery(name = "DireccionCliente.findByAvenida", query = "SELECT d FROM DireccionCliente d WHERE d.avenida = :avenida")
    , @NamedQuery(name = "DireccionCliente.findByNumeroApartamento", query = "SELECT d FROM DireccionCliente d WHERE d.numeroApartamento = :numeroApartamento")
    , @NamedQuery(name = "DireccionCliente.findByNumeroCasa", query = "SELECT d FROM DireccionCliente d WHERE d.numeroCasa = :numeroCasa")
    , @NamedQuery(name = "DireccionCliente.findByNumeroEdificio", query = "SELECT d FROM DireccionCliente d WHERE d.numeroEdificio = :numeroEdificio")
    , @NamedQuery(name = "DireccionCliente.findByNumeroLocal", query = "SELECT d FROM DireccionCliente d WHERE d.numeroLocal = :numeroLocal")
    , @NamedQuery(name = "DireccionCliente.findByNumeroSuite", query = "SELECT d FROM DireccionCliente d WHERE d.numeroSuite = :numeroSuite")
    , @NamedQuery(name = "DireccionCliente.findByNumeroPiso", query = "SELECT d FROM DireccionCliente d WHERE d.numeroPiso = :numeroPiso")
    , @NamedQuery(name = "DireccionCliente.findByLetra", query = "SELECT d FROM DireccionCliente d WHERE d.letra = :letra")
    , @NamedQuery(name = "DireccionCliente.findByEdificio", query = "SELECT d FROM DireccionCliente d WHERE d.edificio = :edificio")
    , @NamedQuery(name = "DireccionCliente.findByResidencial", query = "SELECT d FROM DireccionCliente d WHERE d.residencial = :residencial")
    , @NamedQuery(name = "DireccionCliente.findByUrbanizacion", query = "SELECT d FROM DireccionCliente d WHERE d.urbanizacion = :urbanizacion")
    , @NamedQuery(name = "DireccionCliente.findByPlaza", query = "SELECT d FROM DireccionCliente d WHERE d.plaza = :plaza")
    , @NamedQuery(name = "DireccionCliente.findByTorre", query = "SELECT d FROM DireccionCliente d WHERE d.torre = :torre")
    , @NamedQuery(name = "DireccionCliente.findByKilometroNumero", query = "SELECT d FROM DireccionCliente d WHERE d.kilometroNumero = :kilometroNumero")})
public class DireccionCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "region")
    private String region;
    @Basic(optional = false)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @Column(name = "municipio")
    private String municipio;
    @Basic(optional = false)
    @Column(name = "sector")
    private String sector;
    @Basic(optional = false)
    @Lob
    @Column(name = "direcion")
    private String direcion;
    @Column(name = "calle")
    private String calle;
    @Column(name = "avenida")
    private String avenida;
    @Column(name = "numero_apartamento")
    private String numeroApartamento;
    @Column(name = "numero_casa")
    private String numeroCasa;
    @Column(name = "numero_edificio")
    private String numeroEdificio;
    @Column(name = "numero_local")
    private String numeroLocal;
    @Column(name = "numero_suite")
    private String numeroSuite;
    @Column(name = "numero_piso")
    private String numeroPiso;
    @Column(name = "letra")
    private String letra;
    @Column(name = "edificio")
    private String edificio;
    @Column(name = "residencial")
    private String residencial;
    @Column(name = "urbanizacion")
    private String urbanizacion;
    @Column(name = "plaza")
    private String plaza;
    @Column(name = "torre")
    private String torre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "kilometro_numero")
    private Double kilometroNumero;
    @JoinColumn(name = "cliente", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Cliente cliente;

    public DireccionCliente() {
    }

    public DireccionCliente(Integer codigo) {
        this.codigo = codigo;
    }

    public DireccionCliente(Integer codigo, String region, String ciudad, String municipio, String sector, String direcion) {
        this.codigo = codigo;
        this.region = region;
        this.ciudad = ciudad;
        this.municipio = municipio;
        this.sector = sector;
        this.direcion = direcion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
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

    public String getNumeroApartamento() {
        return numeroApartamento;
    }

    public void setNumeroApartamento(String numeroApartamento) {
        this.numeroApartamento = numeroApartamento;
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

    public String getNumeroLocal() {
        return numeroLocal;
    }

    public void setNumeroLocal(String numeroLocal) {
        this.numeroLocal = numeroLocal;
    }

    public String getNumeroSuite() {
        return numeroSuite;
    }

    public void setNumeroSuite(String numeroSuite) {
        this.numeroSuite = numeroSuite;
    }

    public String getNumeroPiso() {
        return numeroPiso;
    }

    public void setNumeroPiso(String numeroPiso) {
        this.numeroPiso = numeroPiso;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getResidencial() {
        return residencial;
    }

    public void setResidencial(String residencial) {
        this.residencial = residencial;
    }

    public String getUrbanizacion() {
        return urbanizacion;
    }

    public void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }

    public String getPlaza() {
        return plaza;
    }

    public void setPlaza(String plaza) {
        this.plaza = plaza;
    }

    public String getTorre() {
        return torre;
    }

    public void setTorre(String torre) {
        this.torre = torre;
    }

    public Double getKilometroNumero() {
        return kilometroNumero;
    }

    public void setKilometroNumero(Double kilometroNumero) {
        this.kilometroNumero = kilometroNumero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        if (!(object instanceof DireccionCliente)) {
            return false;
        }
        DireccionCliente other = (DireccionCliente) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DireccionCliente[ codigo=" + codigo + " ]";
    }
    
}
