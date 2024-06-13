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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maximilianoa-te
 */
@Entity
@Table(name = "registro_de_imei")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroDeImei.findAll", query = "SELECT r FROM RegistroDeImei r")
    , @NamedQuery(name = "RegistroDeImei.findByCodigo", query = "SELECT r FROM RegistroDeImei r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "RegistroDeImei.findByNumero", query = "SELECT r FROM RegistroDeImei r WHERE r.numero = :numero")
    , @NamedQuery(name = "RegistroDeImei.findByFechaRegistro", query = "SELECT r FROM RegistroDeImei r WHERE r.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "RegistroDeImei.findByFechaEntrada", query = "SELECT r FROM RegistroDeImei r WHERE r.fechaEntrada = :fechaEntrada")
    , @NamedQuery(name = "RegistroDeImei.findByNumeroEntrada", query = "SELECT r FROM RegistroDeImei r WHERE r.numeroEntrada = :numeroEntrada")
    , @NamedQuery(name = "RegistroDeImei.findByEntradaInventario", query = "SELECT r FROM RegistroDeImei r WHERE r.entradaInventario = :entradaInventario")
    , @NamedQuery(name = "RegistroDeImei.findByDisponible", query = "SELECT r FROM RegistroDeImei r WHERE r.disponible = :disponible")})
public class RegistroDeImei implements Serializable {

    @Column(name = "numero_sim")
    private String numeroSim;
    @JoinColumn(name = "sim", referencedColumnName = "codigo")
    @ManyToOne
    private RegistroDeSim sim;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fecha_entrada")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    @Column(name = "numero_entrada")
    private String numeroEntrada;
    @Column(name = "entrada_inventario")
    private Integer entradaInventario;
    @Basic(optional = false)
    @Column(name = "disponible")
    private boolean disponible;
    @OneToMany(mappedBy = "imei")
    private Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection;
    @JoinColumn(name = "articulo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public RegistroDeImei() {
    }

    public RegistroDeImei(Integer codigo) {
        this.codigo = codigo;
    }

    public RegistroDeImei(Integer codigo, String numero, Date fechaRegistro, boolean disponible) {
        this.codigo = codigo;
        this.numero = numero;
        this.fechaRegistro = fechaRegistro;
        this.disponible = disponible;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getNumeroEntrada() {
        return numeroEntrada;
    }

    public void setNumeroEntrada(String numeroEntrada) {
        this.numeroEntrada = numeroEntrada;
    }

    public Integer getEntradaInventario() {
        return entradaInventario;
    }

    public void setEntradaInventario(Integer entradaInventario) {
        this.entradaInventario = entradaInventario;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @XmlTransient
    public Collection<DetalleContratoDeServicio> getDetalleContratoDeServicioCollection() {
        return detalleContratoDeServicioCollection;
    }

    public void setDetalleContratoDeServicioCollection(Collection<DetalleContratoDeServicio> detalleContratoDeServicioCollection) {
        this.detalleContratoDeServicioCollection = detalleContratoDeServicioCollection;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
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
        if (!(object instanceof RegistroDeImei)) {
            return false;
        }
        RegistroDeImei other = (RegistroDeImei) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RegistroDeImei[ codigo=" + codigo + " ]";
    }

    public String getNumeroSim() {
        return numeroSim;
    }

    public void setNumeroSim(String numeroSim) {
        this.numeroSim = numeroSim;
    }

    public RegistroDeSim getSim() {
        return sim;
    }

    public void setSim(RegistroDeSim sim) {
        this.sim = sim;
    }
    
}
