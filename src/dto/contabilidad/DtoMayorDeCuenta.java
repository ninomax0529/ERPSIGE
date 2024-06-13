/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.contabilidad;

import java.util.Date;

/**
 *
 * @author maximilianoa-te
 */

public class DtoMayorDeCuenta  {

    /**
     * @return the conciliada
     */
    public boolean isConciliada() {
        return conciliada;
    }

    /**
     * @param conciliada the conciliada to set
     */
    public void setConciliada(boolean conciliada) {
        this.conciliada = conciliada;
    }

   
    private Integer codigo;
   
    private int asiento;
  
    private Date fecha;
   
    private String cuenta;
  
    private String descripcion;
    
    private double debito;
  
    private double credito;
    
    private String tipoDocumento;
  
    private String numeroDocumento;

    private double pagado;
 
    private String factura;
    private boolean conciliada;

    public DtoMayorDeCuenta() {
    }

    public DtoMayorDeCuenta(Integer codigo) {
        this.codigo = codigo;
    }

    public DtoMayorDeCuenta(Integer codigo, int asiento, Date fecha, String cuenta, String descripcion, double debito, double credito, String tipoDocumento, String numeroDocumento, double pagado, String factura) {
        this.codigo = codigo;
        this.asiento = asiento;
        this.fecha = fecha;
        this.cuenta = cuenta;
        this.descripcion = descripcion;
        this.debito = debito;
        this.credito = credito;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.pagado = pagado;
        this.factura = factura;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public double getPagado() {
        return pagado;
    }

    public void setPagado(double pagado) {
        this.pagado = pagado;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
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
        if (!(object instanceof DtoMayorDeCuenta)) {
            return false;
        }
        DtoMayorDeCuenta other = (DtoMayorDeCuenta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DtoMayorDeCuenta[ codigo=" + codigo + " ]";
    }
    
}
