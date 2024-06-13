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
@Table(name = "detalle_cotizacion_vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCotizacionVehiculo.findAll", query = "SELECT d FROM DetalleCotizacionVehiculo d")
    , @NamedQuery(name = "DetalleCotizacionVehiculo.findByCodigo", query = "SELECT d FROM DetalleCotizacionVehiculo d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleCotizacionVehiculo.findByVehiculo", query = "SELECT d FROM DetalleCotizacionVehiculo d WHERE d.vehiculo = :vehiculo")
    , @NamedQuery(name = "DetalleCotizacionVehiculo.findByColor", query = "SELECT d FROM DetalleCotizacionVehiculo d WHERE d.color = :color")
    , @NamedQuery(name = "DetalleCotizacionVehiculo.findByPlaca", query = "SELECT d FROM DetalleCotizacionVehiculo d WHERE d.placa = :placa")
    , @NamedQuery(name = "DetalleCotizacionVehiculo.findByMarca", query = "SELECT d FROM DetalleCotizacionVehiculo d WHERE d.marca = :marca")})
public class DetalleCotizacionVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "vehiculo")
    private String vehiculo;
    @Column(name = "color")
    private String color;
    @Column(name = "placa")
    private String placa;
    @Column(name = "marca")
    private String marca;
    @JoinColumn(name = "cotizacion", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private CotizacionDeVenta cotizacion;

    public DetalleCotizacionVehiculo() {
    }

    public DetalleCotizacionVehiculo(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleCotizacionVehiculo(Integer codigo, String vehiculo) {
        this.codigo = codigo;
        this.vehiculo = vehiculo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public CotizacionDeVenta getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(CotizacionDeVenta cotizacion) {
        this.cotizacion = cotizacion;
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
        if (!(object instanceof DetalleCotizacionVehiculo)) {
            return false;
        }
        DetalleCotizacionVehiculo other = (DetalleCotizacionVehiculo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleCotizacionVehiculo[ codigo=" + codigo + " ]";
    }
    
}
