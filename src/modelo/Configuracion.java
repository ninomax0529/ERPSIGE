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
@Table(name = "configuracion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configuracion.findAll", query = "SELECT c FROM Configuracion c")
    , @NamedQuery(name = "Configuracion.findByCodigo", query = "SELECT c FROM Configuracion c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Configuracion.findByTelefono", query = "SELECT c FROM Configuracion c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Configuracion.findByActivo", query = "SELECT c FROM Configuracion c WHERE c.activo = :activo")
    , @NamedQuery(name = "Configuracion.findByRnc", query = "SELECT c FROM Configuracion c WHERE c.rnc = :rnc")
    , @NamedQuery(name = "Configuracion.findBySlogan", query = "SELECT c FROM Configuracion c WHERE c.slogan = :slogan")
    , @NamedQuery(name = "Configuracion.findByWebsite", query = "SELECT c FROM Configuracion c WHERE c.website = :website")
    , @NamedQuery(name = "Configuracion.findByItbis", query = "SELECT c FROM Configuracion c WHERE c.itbis = :itbis")
    , @NamedQuery(name = "Configuracion.findByFax", query = "SELECT c FROM Configuracion c WHERE c.fax = :fax")
    , @NamedQuery(name = "Configuracion.findByBd", query = "SELECT c FROM Configuracion c WHERE c.bd = :bd")
    , @NamedQuery(name = "Configuracion.findByFormatoDocumento", query = "SELECT c FROM Configuracion c WHERE c.formatoDocumento = :formatoDocumento")
    , @NamedQuery(name = "Configuracion.findByImpresionDirecta", query = "SELECT c FROM Configuracion c WHERE c.impresionDirecta = :impresionDirecta")
    , @NamedQuery(name = "Configuracion.findByActivarAbonoInicial", query = "SELECT c FROM Configuracion c WHERE c.activarAbonoInicial = :activarAbonoInicial")
    , @NamedQuery(name = "Configuracion.findByFacturarSinApertuaraDeCaja", query = "SELECT c FROM Configuracion c WHERE c.facturarSinApertuaraDeCaja = :facturarSinApertuaraDeCaja")
    , @NamedQuery(name = "Configuracion.findByFacturarSinImprimir", query = "SELECT c FROM Configuracion c WHERE c.facturarSinImprimir = :facturarSinImprimir")
    , @NamedQuery(name = "Configuracion.findByHoraExtraMenor69", query = "SELECT c FROM Configuracion c WHERE c.horaExtraMenor69 = :horaExtraMenor69")
    , @NamedQuery(name = "Configuracion.findByHoraExtraMayor68", query = "SELECT c FROM Configuracion c WHERE c.horaExtraMayor68 = :horaExtraMayor68")})
public class Configuracion implements Serializable {

    @Column(name = "calcular_ultima_nomina")
    private Boolean calcularUltimaNomina;

    @Column(name = "aviso_ncf_disponible")
    private Integer avisoNcfDisponible;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Lob
    @Column(name = "empresa")
    private String empresa;
    @Column(name = "telefono")
    private String telefono;
    @Lob
    @Column(name = "direccion")
    private String direccion;
    @Lob
    @Column(name = "email")
    private String email;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "rnc")
    private String rnc;
    @Column(name = "slogan")
    private String slogan;
    @Column(name = "website")
    private String website;
    @Column(name = "itbis")
    private Long itbis;
    @Column(name = "fax")
    private String fax;
    @Column(name = "bd")
    private String bd;
    @Column(name = "formato_documento")
    private Integer formatoDocumento;
    @Basic(optional = false)
    @Column(name = "impresion_directa")
    private boolean impresionDirecta;
    @Basic(optional = false)
    @Column(name = "activar_abono_inicial")
    private boolean activarAbonoInicial;
    @Basic(optional = false)
    @Column(name = "facturar_sin_apertuara_de_caja")
    private boolean facturarSinApertuaraDeCaja;
    @Basic(optional = false)
    @Column(name = "facturar_sin_imprimir")
    private boolean facturarSinImprimir;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "hora_extra_menor_69")
    private Double horaExtraMenor69;
    @Column(name = "hora_extra_mayor_68")
    private Double horaExtraMayor68;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

    public Configuracion() {
    }

    public Configuracion(Integer codigo) {
        this.codigo = codigo;
    }

    public Configuracion(Integer codigo, boolean impresionDirecta, boolean activarAbonoInicial, boolean facturarSinApertuaraDeCaja, boolean facturarSinImprimir) {
        this.codigo = codigo;
        this.impresionDirecta = impresionDirecta;
        this.activarAbonoInicial = activarAbonoInicial;
        this.facturarSinApertuaraDeCaja = facturarSinApertuaraDeCaja;
        this.facturarSinImprimir = facturarSinImprimir;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Long getItbis() {
        return itbis;
    }

    public void setItbis(Long itbis) {
        this.itbis = itbis;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public Integer getFormatoDocumento() {
        return formatoDocumento;
    }

    public void setFormatoDocumento(Integer formatoDocumento) {
        this.formatoDocumento = formatoDocumento;
    }

    public boolean getImpresionDirecta() {
        return impresionDirecta;
    }

    public void setImpresionDirecta(boolean impresionDirecta) {
        this.impresionDirecta = impresionDirecta;
    }

    public boolean getActivarAbonoInicial() {
        return activarAbonoInicial;
    }

    public void setActivarAbonoInicial(boolean activarAbonoInicial) {
        this.activarAbonoInicial = activarAbonoInicial;
    }

    public boolean getFacturarSinApertuaraDeCaja() {
        return facturarSinApertuaraDeCaja;
    }

    public void setFacturarSinApertuaraDeCaja(boolean facturarSinApertuaraDeCaja) {
        this.facturarSinApertuaraDeCaja = facturarSinApertuaraDeCaja;
    }

    public boolean getFacturarSinImprimir() {
        return facturarSinImprimir;
    }

    public void setFacturarSinImprimir(boolean facturarSinImprimir) {
        this.facturarSinImprimir = facturarSinImprimir;
    }

    public Double getHoraExtraMenor69() {
        return horaExtraMenor69;
    }

    public void setHoraExtraMenor69(Double horaExtraMenor69) {
        this.horaExtraMenor69 = horaExtraMenor69;
    }

    public Double getHoraExtraMayor68() {
        return horaExtraMayor68;
    }

    public void setHoraExtraMayor68(Double horaExtraMayor68) {
        this.horaExtraMayor68 = horaExtraMayor68;
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
        if (!(object instanceof Configuracion)) {
            return false;
        }
        Configuracion other = (Configuracion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Configuracion[ codigo=" + codigo + " ]";
    }

    public Integer getAvisoNcfDisponible() {
        return avisoNcfDisponible;
    }

    public void setAvisoNcfDisponible(Integer avisoNcfDisponible) {
        this.avisoNcfDisponible = avisoNcfDisponible;
    }

    public Boolean getCalcularUltimaNomina() {
        return calcularUltimaNomina;
    }

    public void setCalcularUltimaNomina(Boolean calcularUltimaNomina) {
        this.calcularUltimaNomina = calcularUltimaNomina;
    }
    
}
