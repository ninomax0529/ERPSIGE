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
@Table(name = "detalle_conciliacion_bancaria_libro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleConciliacionBancariaLibro.findAll", query = "SELECT d FROM DetalleConciliacionBancariaLibro d")
    , @NamedQuery(name = "DetalleConciliacionBancariaLibro.findByCodigo", query = "SELECT d FROM DetalleConciliacionBancariaLibro d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleConciliacionBancariaLibro.findByConcepto", query = "SELECT d FROM DetalleConciliacionBancariaLibro d WHERE d.concepto = :concepto")
    , @NamedQuery(name = "DetalleConciliacionBancariaLibro.findByDebito", query = "SELECT d FROM DetalleConciliacionBancariaLibro d WHERE d.debito = :debito")
    , @NamedQuery(name = "DetalleConciliacionBancariaLibro.findByCredito", query = "SELECT d FROM DetalleConciliacionBancariaLibro d WHERE d.credito = :credito")
    , @NamedQuery(name = "DetalleConciliacionBancariaLibro.findByFecha", query = "SELECT d FROM DetalleConciliacionBancariaLibro d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "DetalleConciliacionBancariaLibro.findByNumeroDocumento", query = "SELECT d FROM DetalleConciliacionBancariaLibro d WHERE d.numeroDocumento = :numeroDocumento")
    , @NamedQuery(name = "DetalleConciliacionBancariaLibro.findByNombreOperacion", query = "SELECT d FROM DetalleConciliacionBancariaLibro d WHERE d.nombreOperacion = :nombreOperacion")
    , @NamedQuery(name = "DetalleConciliacionBancariaLibro.findByEntidad", query = "SELECT d FROM DetalleConciliacionBancariaLibro d WHERE d.entidad = :entidad")})
public class DetalleConciliacionBancariaLibro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "concepto")
    private String concepto;
    @Basic(optional = false)
    @Column(name = "debito")
    private double debito;
    @Basic(optional = false)
    @Column(name = "credito")
    private double credito;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Column(name = "nombre_operacion")
    private String nombreOperacion;
    @Basic(optional = false)
    @Column(name = "entidad")
    private String entidad;
    @JoinColumn(name = "conciliacion_bancario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private ConciliacionBancaria conciliacionBancario;

    public DetalleConciliacionBancariaLibro() {
    }

    public DetalleConciliacionBancariaLibro(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleConciliacionBancariaLibro(Integer codigo, String concepto, double debito, double credito, String entidad) {
        this.codigo = codigo;
        this.concepto = concepto;
        this.debito = debito;
        this.credito = credito;
        this.entidad = entidad;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public ConciliacionBancaria getConciliacionBancario() {
        return conciliacionBancario;
    }

    public void setConciliacionBancario(ConciliacionBancaria conciliacionBancario) {
        this.conciliacionBancario = conciliacionBancario;
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
        if (!(object instanceof DetalleConciliacionBancariaLibro)) {
            return false;
        }
        DetalleConciliacionBancariaLibro other = (DetalleConciliacionBancariaLibro) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleConciliacionBancariaLibro[ codigo=" + codigo + " ]";
    }
    
}
