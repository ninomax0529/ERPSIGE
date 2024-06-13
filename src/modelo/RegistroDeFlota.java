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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "registro_de_flota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroDeFlota.findAll", query = "SELECT r FROM RegistroDeFlota r")
    , @NamedQuery(name = "RegistroDeFlota.findByCodigo", query = "SELECT r FROM RegistroDeFlota r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "RegistroDeFlota.findByFecha", query = "SELECT r FROM RegistroDeFlota r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "RegistroDeFlota.findByAsignada", query = "SELECT r FROM RegistroDeFlota r WHERE r.asignada = :asignada")
    , @NamedQuery(name = "RegistroDeFlota.findByNombreFlota", query = "SELECT r FROM RegistroDeFlota r WHERE r.nombreFlota = :nombreFlota")
    , @NamedQuery(name = "RegistroDeFlota.findByImei", query = "SELECT r FROM RegistroDeFlota r WHERE r.imei = :imei")
    , @NamedQuery(name = "RegistroDeFlota.findBySim", query = "SELECT r FROM RegistroDeFlota r WHERE r.sim = :sim")
    , @NamedQuery(name = "RegistroDeFlota.findByModelo", query = "SELECT r FROM RegistroDeFlota r WHERE r.modelo = :modelo")
    , @NamedQuery(name = "RegistroDeFlota.findByMarca", query = "SELECT r FROM RegistroDeFlota r WHERE r.marca = :marca")
    , @NamedQuery(name = "RegistroDeFlota.findByNombreUsuario", query = "SELECT r FROM RegistroDeFlota r WHERE r.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "RegistroDeFlota.findByAnulado", query = "SELECT r FROM RegistroDeFlota r WHERE r.anulado = :anulado")
    , @NamedQuery(name = "RegistroDeFlota.findByFechaAnulado", query = "SELECT r FROM RegistroDeFlota r WHERE r.fechaAnulado = :fechaAnulado")
    , @NamedQuery(name = "RegistroDeFlota.findByAnuladoPor", query = "SELECT r FROM RegistroDeFlota r WHERE r.anuladoPor = :anuladoPor")
    , @NamedQuery(name = "RegistroDeFlota.findByFechaRegistro", query = "SELECT r FROM RegistroDeFlota r WHERE r.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "RegistroDeFlota.findByFechaGarantiaDesde", query = "SELECT r FROM RegistroDeFlota r WHERE r.fechaGarantiaDesde = :fechaGarantiaDesde")
    , @NamedQuery(name = "RegistroDeFlota.findByFechaGarantiaHasta", query = "SELECT r FROM RegistroDeFlota r WHERE r.fechaGarantiaHasta = :fechaGarantiaHasta")
    , @NamedQuery(name = "RegistroDeFlota.findByTieneGarantia", query = "SELECT r FROM RegistroDeFlota r WHERE r.tieneGarantia = :tieneGarantia")
    , @NamedQuery(name = "RegistroDeFlota.findByMesesDeGarantia", query = "SELECT r FROM RegistroDeFlota r WHERE r.mesesDeGarantia = :mesesDeGarantia")
    , @NamedQuery(name = "RegistroDeFlota.findByNueva", query = "SELECT r FROM RegistroDeFlota r WHERE r.nueva = :nueva")
    , @NamedQuery(name = "RegistroDeFlota.findByUsada", query = "SELECT r FROM RegistroDeFlota r WHERE r.usada = :usada")
    , @NamedQuery(name = "RegistroDeFlota.findByFechaEstado", query = "SELECT r FROM RegistroDeFlota r WHERE r.fechaEstado = :fechaEstado")})
public class RegistroDeFlota implements Serializable {

    @Basic(optional = false)
    @Column(name = "precio")
    private double precio;
    @Basic(optional = false)
    @Column(name = "empresa_telefonica")
    private String empresaTelefonica;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "asignada")
    private boolean asignada;
    @Basic(optional = false)
    @Column(name = "nombre_flota")
    private String nombreFlota;
    @Basic(optional = false)
    @Column(name = "imei")
    private String imei;
    @Basic(optional = false)
    @Column(name = "sim")
    private String sim;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "anulado")
    private boolean anulado;
    @Column(name = "fecha_anulado")
    @Temporal(TemporalType.DATE)
    private Date fechaAnulado;
    @Column(name = "anulado_por")
    private String anuladoPor;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Lob
    @Column(name = "nota")
    private String nota;
    @Column(name = "fecha_garantia_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaGarantiaDesde;
    @Column(name = "fecha_garantia_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaGarantiaHasta;
    @Basic(optional = false)
    @Column(name = "tiene_garantia")
    private boolean tieneGarantia;
    @Basic(optional = false)
    @Column(name = "meses_de_garantia")
    private int mesesDeGarantia;
    @Basic(optional = false)
    @Column(name = "nueva")
    private boolean nueva;
    @Basic(optional = false)
    @Column(name = "usada")
    private boolean usada;
    @Column(name = "fechaEstado")
    @Temporal(TemporalType.DATE)
    private Date fechaEstado;
    @JoinColumn(name = "unidad_de_negocio", referencedColumnName = "codigo")
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public RegistroDeFlota() {
    }

    public RegistroDeFlota(Integer codigo) {
        this.codigo = codigo;
    }

    public RegistroDeFlota(Integer codigo, Date fecha, boolean asignada, String nombreFlota, String imei, String sim, String nombreUsuario, boolean anulado, boolean tieneGarantia, int mesesDeGarantia, boolean nueva, boolean usada) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.asignada = asignada;
        this.nombreFlota = nombreFlota;
        this.imei = imei;
        this.sim = sim;
        this.nombreUsuario = nombreUsuario;
        this.anulado = anulado;
        this.tieneGarantia = tieneGarantia;
        this.mesesDeGarantia = mesesDeGarantia;
        this.nueva = nueva;
        this.usada = usada;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getAsignada() {
        return asignada;
    }

    public void setAsignada(boolean asignada) {
        this.asignada = asignada;
    }

    public String getNombreFlota() {
        return nombreFlota;
    }

    public void setNombreFlota(String nombreFlota) {
        this.nombreFlota = nombreFlota;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public Date getFechaAnulado() {
        return fechaAnulado;
    }

    public void setFechaAnulado(Date fechaAnulado) {
        this.fechaAnulado = fechaAnulado;
    }

    public String getAnuladoPor() {
        return anuladoPor;
    }

    public void setAnuladoPor(String anuladoPor) {
        this.anuladoPor = anuladoPor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Date getFechaGarantiaDesde() {
        return fechaGarantiaDesde;
    }

    public void setFechaGarantiaDesde(Date fechaGarantiaDesde) {
        this.fechaGarantiaDesde = fechaGarantiaDesde;
    }

    public Date getFechaGarantiaHasta() {
        return fechaGarantiaHasta;
    }

    public void setFechaGarantiaHasta(Date fechaGarantiaHasta) {
        this.fechaGarantiaHasta = fechaGarantiaHasta;
    }

    public boolean getTieneGarantia() {
        return tieneGarantia;
    }

    public void setTieneGarantia(boolean tieneGarantia) {
        this.tieneGarantia = tieneGarantia;
    }

    public int getMesesDeGarantia() {
        return mesesDeGarantia;
    }

    public void setMesesDeGarantia(int mesesDeGarantia) {
        this.mesesDeGarantia = mesesDeGarantia;
    }

    public boolean getNueva() {
        return nueva;
    }

    public void setNueva(boolean nueva) {
        this.nueva = nueva;
    }

    public boolean getUsada() {
        return usada;
    }

    public void setUsada(boolean usada) {
        this.usada = usada;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
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
        if (!(object instanceof RegistroDeFlota)) {
            return false;
        }
        RegistroDeFlota other = (RegistroDeFlota) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RegistroDeFlota[ codigo=" + codigo + " ]";
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEmpresaTelefonica() {
        return empresaTelefonica;
    }

    public void setEmpresaTelefonica(String empresaTelefonica) {
        this.empresaTelefonica = empresaTelefonica;
    }
    
}
