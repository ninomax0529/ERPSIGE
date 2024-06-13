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
@Table(name = "detalle_asistencia_tecnica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleAsistenciaTecnica.findAll", query = "SELECT d FROM DetalleAsistenciaTecnica d")
    , @NamedQuery(name = "DetalleAsistenciaTecnica.findByCodigo", query = "SELECT d FROM DetalleAsistenciaTecnica d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleAsistenciaTecnica.findByArticulo", query = "SELECT d FROM DetalleAsistenciaTecnica d WHERE d.articulo = :articulo")
    , @NamedQuery(name = "DetalleAsistenciaTecnica.findByNombreArticulo", query = "SELECT d FROM DetalleAsistenciaTecnica d WHERE d.nombreArticulo = :nombreArticulo")
    , @NamedQuery(name = "DetalleAsistenciaTecnica.findByFechaDetalle", query = "SELECT d FROM DetalleAsistenciaTecnica d WHERE d.fechaDetalle = :fechaDetalle")})
public class DetalleAsistenciaTecnica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "articulo")
    private Integer articulo;
    @Column(name = "nombre_articulo")
    private String nombreArticulo;
    @Column(name = "fecha_detalle")
    @Temporal(TemporalType.DATE)
    private Date fechaDetalle;
    @Lob
    @Column(name = "concepto")
    private String concepto;
    @JoinColumn(name = "asistencia", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private AsistenciaTecnica asistencia;

    public DetalleAsistenciaTecnica() {
    }

    public DetalleAsistenciaTecnica(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getArticulo() {
        return articulo;
    }

    public void setArticulo(Integer articulo) {
        this.articulo = articulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public Date getFechaDetalle() {
        return fechaDetalle;
    }

    public void setFechaDetalle(Date fechaDetalle) {
        this.fechaDetalle = fechaDetalle;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public AsistenciaTecnica getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(AsistenciaTecnica asistencia) {
        this.asistencia = asistencia;
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
        if (!(object instanceof DetalleAsistenciaTecnica)) {
            return false;
        }
        DetalleAsistenciaTecnica other = (DetalleAsistenciaTecnica) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleAsistenciaTecnica[ codigo=" + codigo + " ]";
    }
    
}
