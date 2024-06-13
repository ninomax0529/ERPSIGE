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
@Table(name = "configuracion_cheque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfiguracionCheque.findAll", query = "SELECT c FROM ConfiguracionCheque c")
    , @NamedQuery(name = "ConfiguracionCheque.findByCodigo", query = "SELECT c FROM ConfiguracionCheque c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "ConfiguracionCheque.findByFilaInicio", query = "SELECT c FROM ConfiguracionCheque c WHERE c.filaInicio = :filaInicio")
    , @NamedQuery(name = "ConfiguracionCheque.findByFilaFinal", query = "SELECT c FROM ConfiguracionCheque c WHERE c.filaFinal = :filaFinal")
    , @NamedQuery(name = "ConfiguracionCheque.findByColumnaInicio", query = "SELECT c FROM ConfiguracionCheque c WHERE c.columnaInicio = :columnaInicio")
    , @NamedQuery(name = "ConfiguracionCheque.findByColumnaFinal", query = "SELECT c FROM ConfiguracionCheque c WHERE c.columnaFinal = :columnaFinal")})
public class ConfiguracionCheque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "fila_inicio")
    private int filaInicio;
    @Basic(optional = false)
    @Column(name = "fila_final")
    private int filaFinal;
    @Basic(optional = false)
    @Column(name = "columna_inicio")
    private int columnaInicio;
    @Basic(optional = false)
    @Column(name = "columna_final")
    private int columnaFinal;
    @JoinColumn(name = "campo_cheque", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private CampoCheque campoCheque;

    public ConfiguracionCheque() {
    }

    public ConfiguracionCheque(Integer codigo) {
        this.codigo = codigo;
    }

    public ConfiguracionCheque(Integer codigo, int filaInicio, int filaFinal, int columnaInicio, int columnaFinal) {
        this.codigo = codigo;
        this.filaInicio = filaInicio;
        this.filaFinal = filaFinal;
        this.columnaInicio = columnaInicio;
        this.columnaFinal = columnaFinal;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getFilaInicio() {
        return filaInicio;
    }

    public void setFilaInicio(int filaInicio) {
        this.filaInicio = filaInicio;
    }

    public int getFilaFinal() {
        return filaFinal;
    }

    public void setFilaFinal(int filaFinal) {
        this.filaFinal = filaFinal;
    }

    public int getColumnaInicio() {
        return columnaInicio;
    }

    public void setColumnaInicio(int columnaInicio) {
        this.columnaInicio = columnaInicio;
    }

    public int getColumnaFinal() {
        return columnaFinal;
    }

    public void setColumnaFinal(int columnaFinal) {
        this.columnaFinal = columnaFinal;
    }

    public CampoCheque getCampoCheque() {
        return campoCheque;
    }

    public void setCampoCheque(CampoCheque campoCheque) {
        this.campoCheque = campoCheque;
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
        if (!(object instanceof ConfiguracionCheque)) {
            return false;
        }
        ConfiguracionCheque other = (ConfiguracionCheque) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ConfiguracionCheque[ codigo=" + codigo + " ]";
    }
    
}
