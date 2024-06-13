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
@Table(name = "cargo_automatico_a_tarjeta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CargoAutomaticoATarjeta.findAll", query = "SELECT c FROM CargoAutomaticoATarjeta c")
    , @NamedQuery(name = "CargoAutomaticoATarjeta.findByCodigo", query = "SELECT c FROM CargoAutomaticoATarjeta c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "CargoAutomaticoATarjeta.findByTitular", query = "SELECT c FROM CargoAutomaticoATarjeta c WHERE c.titular = :titular")
    , @NamedQuery(name = "CargoAutomaticoATarjeta.findByCedula", query = "SELECT c FROM CargoAutomaticoATarjeta c WHERE c.cedula = :cedula")
    , @NamedQuery(name = "CargoAutomaticoATarjeta.findByPasaporte", query = "SELECT c FROM CargoAutomaticoATarjeta c WHERE c.pasaporte = :pasaporte")
    , @NamedQuery(name = "CargoAutomaticoATarjeta.findByNumeroDeTarjeta", query = "SELECT c FROM CargoAutomaticoATarjeta c WHERE c.numeroDeTarjeta = :numeroDeTarjeta")
    , @NamedQuery(name = "CargoAutomaticoATarjeta.findByCvvDeTarjeta", query = "SELECT c FROM CargoAutomaticoATarjeta c WHERE c.cvvDeTarjeta = :cvvDeTarjeta")
    , @NamedQuery(name = "CargoAutomaticoATarjeta.findByFechaExpiracion", query = "SELECT c FROM CargoAutomaticoATarjeta c WHERE c.fechaExpiracion = :fechaExpiracion")
    , @NamedQuery(name = "CargoAutomaticoATarjeta.findByDiaDeCobro", query = "SELECT c FROM CargoAutomaticoATarjeta c WHERE c.diaDeCobro = :diaDeCobro")
    , @NamedQuery(name = "CargoAutomaticoATarjeta.findByFechaDeActivacionServicio", query = "SELECT c FROM CargoAutomaticoATarjeta c WHERE c.fechaDeActivacionServicio = :fechaDeActivacionServicio")
    , @NamedQuery(name = "CargoAutomaticoATarjeta.findByTotalAPagar", query = "SELECT c FROM CargoAutomaticoATarjeta c WHERE c.totalAPagar = :totalAPagar")})
public class CargoAutomaticoATarjeta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "titular")
    private String titular;
    @Column(name = "cedula")
    private String cedula;
    @Column(name = "pasaporte")
    private String pasaporte;
    @Column(name = "numero_de_tarjeta")
    private Integer numeroDeTarjeta;
    @Basic(optional = false)
    @Column(name = "cvv_de_tarjeta")
    private int cvvDeTarjeta;
    @Basic(optional = false)
    @Column(name = "fecha_expiracion")
    @Temporal(TemporalType.DATE)
    private Date fechaExpiracion;
    @Basic(optional = false)
    @Column(name = "dia_de_cobro")
    private int diaDeCobro;
    @Basic(optional = false)
    @Column(name = "fecha_de_activacion_servicio")
    @Temporal(TemporalType.DATE)
    private Date fechaDeActivacionServicio;
    @Basic(optional = false)
    @Column(name = "total_a_pagar")
    private double totalAPagar;
    @JoinColumn(name = "contrato_de_servicio", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private ContratoDeServicio contratoDeServicio;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public CargoAutomaticoATarjeta() {
    }

    public CargoAutomaticoATarjeta(Integer codigo) {
        this.codigo = codigo;
    }

    public CargoAutomaticoATarjeta(Integer codigo, String titular, int cvvDeTarjeta, Date fechaExpiracion, int diaDeCobro, Date fechaDeActivacionServicio, double totalAPagar) {
        this.codigo = codigo;
        this.titular = titular;
        this.cvvDeTarjeta = cvvDeTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.diaDeCobro = diaDeCobro;
        this.fechaDeActivacionServicio = fechaDeActivacionServicio;
        this.totalAPagar = totalAPagar;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public Integer getNumeroDeTarjeta() {
        return numeroDeTarjeta;
    }

    public void setNumeroDeTarjeta(Integer numeroDeTarjeta) {
        this.numeroDeTarjeta = numeroDeTarjeta;
    }

    public int getCvvDeTarjeta() {
        return cvvDeTarjeta;
    }

    public void setCvvDeTarjeta(int cvvDeTarjeta) {
        this.cvvDeTarjeta = cvvDeTarjeta;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public int getDiaDeCobro() {
        return diaDeCobro;
    }

    public void setDiaDeCobro(int diaDeCobro) {
        this.diaDeCobro = diaDeCobro;
    }

    public Date getFechaDeActivacionServicio() {
        return fechaDeActivacionServicio;
    }

    public void setFechaDeActivacionServicio(Date fechaDeActivacionServicio) {
        this.fechaDeActivacionServicio = fechaDeActivacionServicio;
    }

    public double getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public ContratoDeServicio getContratoDeServicio() {
        return contratoDeServicio;
    }

    public void setContratoDeServicio(ContratoDeServicio contratoDeServicio) {
        this.contratoDeServicio = contratoDeServicio;
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
        if (!(object instanceof CargoAutomaticoATarjeta)) {
            return false;
        }
        CargoAutomaticoATarjeta other = (CargoAutomaticoATarjeta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CargoAutomaticoATarjeta[ codigo=" + codigo + " ]";
    }
    
}
