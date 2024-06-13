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
@Table(name = "factura_datos_de_vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacturaDatosDeVehiculo.findAll", query = "SELECT f FROM FacturaDatosDeVehiculo f")
    , @NamedQuery(name = "FacturaDatosDeVehiculo.findByCodigo", query = "SELECT f FROM FacturaDatosDeVehiculo f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "FacturaDatosDeVehiculo.findByModelo", query = "SELECT f FROM FacturaDatosDeVehiculo f WHERE f.modelo = :modelo")
    , @NamedQuery(name = "FacturaDatosDeVehiculo.findByMarca", query = "SELECT f FROM FacturaDatosDeVehiculo f WHERE f.marca = :marca")
    , @NamedQuery(name = "FacturaDatosDeVehiculo.findByChasis", query = "SELECT f FROM FacturaDatosDeVehiculo f WHERE f.chasis = :chasis")
    , @NamedQuery(name = "FacturaDatosDeVehiculo.findByPlaca", query = "SELECT f FROM FacturaDatosDeVehiculo f WHERE f.placa = :placa")
    , @NamedQuery(name = "FacturaDatosDeVehiculo.findByMatricula", query = "SELECT f FROM FacturaDatosDeVehiculo f WHERE f.matricula = :matricula")
    , @NamedQuery(name = "FacturaDatosDeVehiculo.findByColor", query = "SELECT f FROM FacturaDatosDeVehiculo f WHERE f.color = :color")
    , @NamedQuery(name = "FacturaDatosDeVehiculo.findByAnio", query = "SELECT f FROM FacturaDatosDeVehiculo f WHERE f.anio = :anio")
    , @NamedQuery(name = "FacturaDatosDeVehiculo.findByAdicional", query = "SELECT f FROM FacturaDatosDeVehiculo f WHERE f.adicional = :adicional")
    , @NamedQuery(name = "FacturaDatosDeVehiculo.findByVehiculo", query = "SELECT f FROM FacturaDatosDeVehiculo f WHERE f.vehiculo = :vehiculo")})
public class FacturaDatosDeVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "modelo")
    private String modelo;
    @Basic(optional = false)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @Column(name = "chasis")
    private String chasis;
    @Basic(optional = false)
    @Column(name = "placa")
    private String placa;
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @Column(name = "color")
    private String color;
    @Column(name = "anio")
    private Integer anio;
    @Basic(optional = false)
    @Column(name = "adicional")
    private boolean adicional;
    @Lob
    @Column(name = "nota")
    private String nota;
    @Basic(optional = false)
    @Column(name = "vehiculo")
    private String vehiculo;
    @JoinColumn(name = "factura", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private DetalleFactura factura;
    @JoinColumn(name = "tipo_vehiculo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoVehiculo tipoVehiculo;

    public FacturaDatosDeVehiculo() {
    }

    public FacturaDatosDeVehiculo(Integer codigo) {
        this.codigo = codigo;
    }

    public FacturaDatosDeVehiculo(Integer codigo, String modelo, String marca, String chasis, String placa, String color, boolean adicional, String vehiculo) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.marca = marca;
        this.chasis = chasis;
        this.placa = placa;
        this.color = color;
        this.adicional = adicional;
        this.vehiculo = vehiculo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public boolean getAdicional() {
        return adicional;
    }

    public void setAdicional(boolean adicional) {
        this.adicional = adicional;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public DetalleFactura getFactura() {
        return factura;
    }

    public void setFactura(DetalleFactura factura) {
        this.factura = factura;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
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
        if (!(object instanceof FacturaDatosDeVehiculo)) {
            return false;
        }
        FacturaDatosDeVehiculo other = (FacturaDatosDeVehiculo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.FacturaDatosDeVehiculo[ codigo=" + codigo + " ]";
    }
    
}
