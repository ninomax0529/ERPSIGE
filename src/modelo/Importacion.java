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
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "importacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Importacion.findAll", query = "SELECT i FROM Importacion i")
    , @NamedQuery(name = "Importacion.findByCodigo", query = "SELECT i FROM Importacion i WHERE i.codigo = :codigo")
    , @NamedQuery(name = "Importacion.findByNombre", query = "SELECT i FROM Importacion i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "Importacion.findByFecha", query = "SELECT i FROM Importacion i WHERE i.fecha = :fecha")
    , @NamedQuery(name = "Importacion.findByImporte", query = "SELECT i FROM Importacion i WHERE i.importe = :importe")
    , @NamedQuery(name = "Importacion.findByFacturado", query = "SELECT i FROM Importacion i WHERE i.facturado = :facturado")})
public class Importacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe")
    private Float importe;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "facturado")
    private String facturado;

    public Importacion() {
    }

    public Importacion(Integer codigo) {
        this.codigo = codigo;
    }

    public Importacion(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFacturado() {
        return facturado;
    }

    public void setFacturado(String facturado) {
        this.facturado = facturado;
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
        if (!(object instanceof Importacion)) {
            return false;
        }
        Importacion other = (Importacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Importacion[ codigo=" + codigo + " ]";
    }
    
}
