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
@Table(name = "gestion_de_cobro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GestionDeCobro.findAll", query = "SELECT g FROM GestionDeCobro g")
    , @NamedQuery(name = "GestionDeCobro.findByCodigo", query = "SELECT g FROM GestionDeCobro g WHERE g.codigo = :codigo")
    , @NamedQuery(name = "GestionDeCobro.findByNombreCliente", query = "SELECT g FROM GestionDeCobro g WHERE g.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "GestionDeCobro.findByFechaCobro", query = "SELECT g FROM GestionDeCobro g WHERE g.fechaCobro = :fechaCobro")
    , @NamedQuery(name = "GestionDeCobro.findByNumeroFactura", query = "SELECT g FROM GestionDeCobro g WHERE g.numeroFactura = :numeroFactura")
    , @NamedQuery(name = "GestionDeCobro.findByCobrador", query = "SELECT g FROM GestionDeCobro g WHERE g.cobrador = :cobrador")
    , @NamedQuery(name = "GestionDeCobro.findByNombreCobrador", query = "SELECT g FROM GestionDeCobro g WHERE g.nombreCobrador = :nombreCobrador")
    , @NamedQuery(name = "GestionDeCobro.findByMontoPendiente", query = "SELECT g FROM GestionDeCobro g WHERE g.montoPendiente = :montoPendiente")})
public class GestionDeCobro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Basic(optional = false)
    @Column(name = "fecha_cobro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCobro;
    @Basic(optional = false)
    @Column(name = "numero_factura")
    private String numeroFactura;
    @Basic(optional = false)
    @Lob
    @Column(name = "nota")
    private String nota;
    @Basic(optional = false)
    @Column(name = "cobrador")
    private int cobrador;
    @Basic(optional = false)
    @Column(name = "nombre_cobrador")
    private String nombreCobrador;
    @Basic(optional = false)
    @Column(name = "monto_pendiente")
    private double montoPendiente;
    @JoinColumn(name = "cliente", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "factura", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Factura factura;

    public GestionDeCobro() {
    }

    public GestionDeCobro(Integer codigo) {
        this.codigo = codigo;
    }

    public GestionDeCobro(Integer codigo, String nombreCliente, Date fechaCobro, String numeroFactura, String nota, int cobrador, String nombreCobrador, double montoPendiente) {
        this.codigo = codigo;
        this.nombreCliente = nombreCliente;
        this.fechaCobro = fechaCobro;
        this.numeroFactura = numeroFactura;
        this.nota = nota;
        this.cobrador = cobrador;
        this.nombreCobrador = nombreCobrador;
        this.montoPendiente = montoPendiente;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getCobrador() {
        return cobrador;
    }

    public void setCobrador(int cobrador) {
        this.cobrador = cobrador;
    }

    public String getNombreCobrador() {
        return nombreCobrador;
    }

    public void setNombreCobrador(String nombreCobrador) {
        this.nombreCobrador = nombreCobrador;
    }

    public double getMontoPendiente() {
        return montoPendiente;
    }

    public void setMontoPendiente(double montoPendiente) {
        this.montoPendiente = montoPendiente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
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
        if (!(object instanceof GestionDeCobro)) {
            return false;
        }
        GestionDeCobro other = (GestionDeCobro) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.GestionDeCobro[ codigo=" + codigo + " ]";
    }
    
}
