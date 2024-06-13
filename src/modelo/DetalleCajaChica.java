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
@Table(name = "detalle_caja_chica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCajaChica.findAll", query = "SELECT d FROM DetalleCajaChica d")
    , @NamedQuery(name = "DetalleCajaChica.findByCodigo", query = "SELECT d FROM DetalleCajaChica d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleCajaChica.findByDocumento", query = "SELECT d FROM DetalleCajaChica d WHERE d.documento = :documento")
    , @NamedQuery(name = "DetalleCajaChica.findByConcepto", query = "SELECT d FROM DetalleCajaChica d WHERE d.concepto = :concepto")
    , @NamedQuery(name = "DetalleCajaChica.findByMonto", query = "SELECT d FROM DetalleCajaChica d WHERE d.monto = :monto")
    , @NamedQuery(name = "DetalleCajaChica.findByNombreMovimiento", query = "SELECT d FROM DetalleCajaChica d WHERE d.nombreMovimiento = :nombreMovimiento")
    , @NamedQuery(name = "DetalleCajaChica.findByIngresosNeto", query = "SELECT d FROM DetalleCajaChica d WHERE d.ingresosNeto = :ingresosNeto")
    , @NamedQuery(name = "DetalleCajaChica.findByAnulado", query = "SELECT d FROM DetalleCajaChica d WHERE d.anulado = :anulado")})
public class DetalleCajaChica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "documento")
    private String documento;
    @Basic(optional = false)
    @Column(name = "concepto")
    private String concepto;
    @Lob
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @Column(name = "monto")
    private double monto;
    @Column(name = "nombre_movimiento")
    private String nombreMovimiento;
    @Basic(optional = false)
    @Column(name = "ingresos_neto")
    private double ingresosNeto;
    @Basic(optional = false)
    @Column(name = "anulado")
    private boolean anulado;
    @JoinColumn(name = "caja_chica", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private CajaChica cajaChica;
    @JoinColumn(name = "tipo_documento", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoDocumento tipoDocumento;
    @JoinColumn(name = "tipo_movimiento", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoMovimiento tipoMovimiento;

    public DetalleCajaChica() {
    }

    public DetalleCajaChica(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleCajaChica(Integer codigo, String concepto, double monto, double ingresosNeto, boolean anulado) {
        this.codigo = codigo;
        this.concepto = concepto;
        this.monto = monto;
        this.ingresosNeto = ingresosNeto;
        this.anulado = anulado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public void setNombreMovimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }

    public double getIngresosNeto() {
        return ingresosNeto;
    }

    public void setIngresosNeto(double ingresosNeto) {
        this.ingresosNeto = ingresosNeto;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public CajaChica getCajaChica() {
        return cajaChica;
    }

    public void setCajaChica(CajaChica cajaChica) {
        this.cajaChica = cajaChica;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
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
        if (!(object instanceof DetalleCajaChica)) {
            return false;
        }
        DetalleCajaChica other = (DetalleCajaChica) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleCajaChica[ codigo=" + codigo + " ]";
    }
    
}
