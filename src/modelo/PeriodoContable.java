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
@Table(name = "periodo_contable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodoContable.findAll", query = "SELECT p FROM PeriodoContable p")
    , @NamedQuery(name = "PeriodoContable.findByCodigo", query = "SELECT p FROM PeriodoContable p WHERE p.codigo = :codigo")
    , @NamedQuery(name = "PeriodoContable.findByFechaInicio", query = "SELECT p FROM PeriodoContable p WHERE p.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "PeriodoContable.findByFechaFinal", query = "SELECT p FROM PeriodoContable p WHERE p.fechaFinal = :fechaFinal")
    , @NamedQuery(name = "PeriodoContable.findByFechaRegistro", query = "SELECT p FROM PeriodoContable p WHERE p.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "PeriodoContable.findByPeriodo", query = "SELECT p FROM PeriodoContable p WHERE p.periodo = :periodo")})
public class PeriodoContable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "periodo")
    private String periodo;
    @JoinColumn(name = "estado", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private EstadoPeriodo estado;

    public PeriodoContable() {
    }

    public PeriodoContable(Integer codigo) {
        this.codigo = codigo;
    }

    public PeriodoContable(Integer codigo, Date fechaInicio, Date fechaRegistro) {
        this.codigo = codigo;
        this.fechaInicio = fechaInicio;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public EstadoPeriodo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPeriodo estado) {
        this.estado = estado;
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
        if (!(object instanceof PeriodoContable)) {
            return false;
        }
        PeriodoContable other = (PeriodoContable) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.PeriodoContable[ codigo=" + codigo + " ]";
    }
    
}
