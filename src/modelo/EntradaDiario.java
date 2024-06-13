/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "entrada_diario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntradaDiario.findAll", query = "SELECT e FROM EntradaDiario e")
    , @NamedQuery(name = "EntradaDiario.findByCodigo", query = "SELECT e FROM EntradaDiario e WHERE e.codigo = :codigo")
    , @NamedQuery(name = "EntradaDiario.findByConcepto", query = "SELECT e FROM EntradaDiario e WHERE e.concepto = :concepto")
    , @NamedQuery(name = "EntradaDiario.findByFecha", query = "SELECT e FROM EntradaDiario e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "EntradaDiario.findByMonto", query = "SELECT e FROM EntradaDiario e WHERE e.monto = :monto")
    , @NamedQuery(name = "EntradaDiario.findByMoneda", query = "SELECT e FROM EntradaDiario e WHERE e.moneda = :moneda")
    , @NamedQuery(name = "EntradaDiario.findByUsuario", query = "SELECT e FROM EntradaDiario e WHERE e.usuario = :usuario")
    , @NamedQuery(name = "EntradaDiario.findByFechaRegistro", query = "SELECT e FROM EntradaDiario e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "EntradaDiario.findByAnulada", query = "SELECT e FROM EntradaDiario e WHERE e.anulada = :anulada")
    , @NamedQuery(name = "EntradaDiario.findByDocumento", query = "SELECT e FROM EntradaDiario e WHERE e.documento = :documento")
    , @NamedQuery(name = "EntradaDiario.findByNumeroDocumento", query = "SELECT e FROM EntradaDiario e WHERE e.numeroDocumento = :numeroDocumento")
    , @NamedQuery(name = "EntradaDiario.findByConfiguracionCuentaContable", query = "SELECT e FROM EntradaDiario e WHERE e.configuracionCuentaContable = :configuracionCuentaContable")
    , @NamedQuery(name = "EntradaDiario.findByNumero", query = "SELECT e FROM EntradaDiario e WHERE e.numero = :numero")})
public class EntradaDiario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "concepto")
    private String concepto;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "monto")
    private String monto;
    @Column(name = "moneda")
    private Integer moneda;
    @Column(name = "usuario")
    private Integer usuario;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "anulada")
    private Boolean anulada;
    @Column(name = "documento")
    private Integer documento;
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Column(name = "configuracion_cuenta_contable")
    private Integer configuracionCuentaContable;
    @Column(name = "numero")
    private Integer numero;
    @JoinColumn(name = "secuencia_documento", referencedColumnName = "codigo")
    @ManyToOne
    private SecuenciaDocumento secuenciaDocumento;
    @JoinColumn(name = "modulo", referencedColumnName = "codigo")
    @ManyToOne
    private Modulo modulo;
    @JoinColumn(name = "tipo_asiento", referencedColumnName = "codigo")
    @ManyToOne
    private TipoAsiento tipoAsiento;
    @JoinColumn(name = "tipo_documento", referencedColumnName = "codigo")
    @ManyToOne
    private TipoDocumento tipoDocumento;
    @JoinColumn(name = "tipo_entrada", referencedColumnName = "codigo")
    @ManyToOne
    private TipoEntrada tipoEntrada;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entradaDiario",orphanRemoval = true,fetch = FetchType.EAGER)
    private Collection<DetalleEntradaDiario> detalleEntradaDiarioCollection;

    public EntradaDiario() {
    }

    public EntradaDiario(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public Integer getMoneda() {
        return moneda;
    }

    public void setMoneda(Integer moneda) {
        this.moneda = moneda;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(Boolean anulada) {
        this.anulada = anulada;
    }

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Integer getConfiguracionCuentaContable() {
        return configuracionCuentaContable;
    }

    public void setConfiguracionCuentaContable(Integer configuracionCuentaContable) {
        this.configuracionCuentaContable = configuracionCuentaContable;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public SecuenciaDocumento getSecuenciaDocumento() {
        return secuenciaDocumento;
    }

    public void setSecuenciaDocumento(SecuenciaDocumento secuenciaDocumento) {
        this.secuenciaDocumento = secuenciaDocumento;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public TipoAsiento getTipoAsiento() {
        return tipoAsiento;
    }

    public void setTipoAsiento(TipoAsiento tipoAsiento) {
        this.tipoAsiento = tipoAsiento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TipoEntrada getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(TipoEntrada tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    @XmlTransient
    public Collection<DetalleEntradaDiario> getDetalleEntradaDiarioCollection() {
        return detalleEntradaDiarioCollection;
    }

    public void setDetalleEntradaDiarioCollection(Collection<DetalleEntradaDiario> detalleEntradaDiarioCollection) {
        this.detalleEntradaDiarioCollection = detalleEntradaDiarioCollection;
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
        if (!(object instanceof EntradaDiario)) {
            return false;
        }
        EntradaDiario other = (EntradaDiario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.EntradaDiario[ codigo=" + codigo + " ]";
    }
    
}
