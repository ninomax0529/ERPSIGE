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
@Table(name = "detalle_reconciliacion_interna_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleReconciliacionInternaCliente.findAll", query = "SELECT d FROM DetalleReconciliacionInternaCliente d")
    , @NamedQuery(name = "DetalleReconciliacionInternaCliente.findByCodigo", query = "SELECT d FROM DetalleReconciliacionInternaCliente d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetalleReconciliacionInternaCliente.findByDocumento", query = "SELECT d FROM DetalleReconciliacionInternaCliente d WHERE d.documento = :documento")
    , @NamedQuery(name = "DetalleReconciliacionInternaCliente.findByNumeroDocumento", query = "SELECT d FROM DetalleReconciliacionInternaCliente d WHERE d.numeroDocumento = :numeroDocumento")
    , @NamedQuery(name = "DetalleReconciliacionInternaCliente.findByDebito", query = "SELECT d FROM DetalleReconciliacionInternaCliente d WHERE d.debito = :debito")
    , @NamedQuery(name = "DetalleReconciliacionInternaCliente.findByCredito", query = "SELECT d FROM DetalleReconciliacionInternaCliente d WHERE d.credito = :credito")})
public class DetalleReconciliacionInternaCliente implements Serializable {

    @Column(name = "nombre_documento")
    private String nombreDocumento;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "documento")
    private int documento;
    @Basic(optional = false)
    @Column(name = "numero_documento")
    private double numeroDocumento;
    @Basic(optional = false)
    @Column(name = "debito")
    private double debito;
    @Basic(optional = false)
    @Column(name = "credito")
    private double credito;
    @JoinColumn(name = "tipo_documento", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoDocumento tipoDocumento;
    @JoinColumn(name = "reconciliacion", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private ReconciliacionInternaCliente reconciliacion;

    public DetalleReconciliacionInternaCliente() {
    }

    public DetalleReconciliacionInternaCliente(Integer codigo) {
        this.codigo = codigo;
    }

    public DetalleReconciliacionInternaCliente(Integer codigo, int documento, double numeroDocumento, double debito, double credito) {
        this.codigo = codigo;
        this.documento = documento;
        this.numeroDocumento = numeroDocumento;
        this.debito = debito;
        this.credito = credito;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public double getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(double numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public ReconciliacionInternaCliente getReconciliacion() {
        return reconciliacion;
    }

    public void setReconciliacion(ReconciliacionInternaCliente reconciliacion) {
        this.reconciliacion = reconciliacion;
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
        if (!(object instanceof DetalleReconciliacionInternaCliente)) {
            return false;
        }
        DetalleReconciliacionInternaCliente other = (DetalleReconciliacionInternaCliente) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleReconciliacionInternaCliente[ codigo=" + codigo + " ]";
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }
    
}
