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
@Table(name = "escala_salarial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EscalaSalarial.findAll", query = "SELECT e FROM EscalaSalarial e")
    , @NamedQuery(name = "EscalaSalarial.findByCodigo", query = "SELECT e FROM EscalaSalarial e WHERE e.codigo = :codigo")
    , @NamedQuery(name = "EscalaSalarial.findByFechaRegistro", query = "SELECT e FROM EscalaSalarial e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "EscalaSalarial.findByFecha", query = "SELECT e FROM EscalaSalarial e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "EscalaSalarial.findByAnio", query = "SELECT e FROM EscalaSalarial e WHERE e.anio = :anio")
    , @NamedQuery(name = "EscalaSalarial.findByExcedenteDesde", query = "SELECT e FROM EscalaSalarial e WHERE e.excedenteDesde = :excedenteDesde")
    , @NamedQuery(name = "EscalaSalarial.findByExcedenteHasta", query = "SELECT e FROM EscalaSalarial e WHERE e.excedenteHasta = :excedenteHasta")
    , @NamedQuery(name = "EscalaSalarial.findByTasa", query = "SELECT e FROM EscalaSalarial e WHERE e.tasa = :tasa")})
public class EscalaSalarial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "anio")
    private int anio;
    @Basic(optional = false)
    @Column(name = "excedente_desde")
    private double excedenteDesde;
    @Basic(optional = false)
    @Column(name = "excedente_hasta")
    private double excedenteHasta;
    @Basic(optional = false)
    @Column(name = "tasa")
    private double tasa;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public EscalaSalarial() {
    }

    public EscalaSalarial(Integer codigo) {
        this.codigo = codigo;
    }

    public EscalaSalarial(Integer codigo, Date fechaRegistro, Date fecha, int anio, double excedenteDesde, double excedenteHasta, double tasa) {
        this.codigo = codigo;
        this.fechaRegistro = fechaRegistro;
        this.fecha = fecha;
        this.anio = anio;
        this.excedenteDesde = excedenteDesde;
        this.excedenteHasta = excedenteHasta;
        this.tasa = tasa;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getExcedenteDesde() {
        return excedenteDesde;
    }

    public void setExcedenteDesde(double excedenteDesde) {
        this.excedenteDesde = excedenteDesde;
    }

    public double getExcedenteHasta() {
        return excedenteHasta;
    }

    public void setExcedenteHasta(double excedenteHasta) {
        this.excedenteHasta = excedenteHasta;
    }

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
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
        if (!(object instanceof EscalaSalarial)) {
            return false;
        }
        EscalaSalarial other = (EscalaSalarial) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.EscalaSalarial[ codigo=" + codigo + " ]";
    }
    
}
