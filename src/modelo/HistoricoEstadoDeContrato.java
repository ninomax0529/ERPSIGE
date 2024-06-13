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
@Table(name = "historico_estado_de_contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoricoEstadoDeContrato.findAll", query = "SELECT h FROM HistoricoEstadoDeContrato h")
    , @NamedQuery(name = "HistoricoEstadoDeContrato.findByCodigo", query = "SELECT h FROM HistoricoEstadoDeContrato h WHERE h.codigo = :codigo")
    , @NamedQuery(name = "HistoricoEstadoDeContrato.findByFecha", query = "SELECT h FROM HistoricoEstadoDeContrato h WHERE h.fecha = :fecha")})
public class HistoricoEstadoDeContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Lob
    @Column(name = "nota_estado")
    private String notaEstado;
    @JoinColumn(name = "estado", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private EstadoContrato estado;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public HistoricoEstadoDeContrato() {
    }

    public HistoricoEstadoDeContrato(Integer codigo) {
        this.codigo = codigo;
    }

    public HistoricoEstadoDeContrato(Integer codigo, Date fecha) {
        this.codigo = codigo;
        this.fecha = fecha;
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

    public String getNotaEstado() {
        return notaEstado;
    }

    public void setNotaEstado(String notaEstado) {
        this.notaEstado = notaEstado;
    }

    public EstadoContrato getEstado() {
        return estado;
    }

    public void setEstado(EstadoContrato estado) {
        this.estado = estado;
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
        if (!(object instanceof HistoricoEstadoDeContrato)) {
            return false;
        }
        HistoricoEstadoDeContrato other = (HistoricoEstadoDeContrato) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.HistoricoEstadoDeContrato[ codigo=" + codigo + " ]";
    }
    
}
