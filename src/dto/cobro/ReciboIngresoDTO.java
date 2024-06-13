/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.cobro;

/**
 *
 * @author maximilianoa-te
 */
public class ReciboIngresoDTO {

    private String cliente;
    private String concepto;
    private String tipoRecibo;
    private String numero;
    private String fecha;
    private Double total;
    private Double montoAvance;
    private Double montoCobrado;
    private Double pagoConAvance;

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoRecibo() {
        return tipoRecibo;
    }

    public void setTipoRecibo(String tipoRecibo) {
        this.tipoRecibo = tipoRecibo;
    }

    /**
     * @return the cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the concepto
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * @param concepto the concepto to set
     */
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @return the montoAvance
     */
    public Double getMontoAvance() {
        return montoAvance;
    }

    /**
     * @param montoAvance the montoAvance to set
     */
    public void setMontoAvance(Double montoAvance) {
        this.montoAvance = montoAvance;
    }

    /**
     * @return the montoCobrado
     */
    public Double getMontoCobrado() {
        return montoCobrado;
    }

    /**
     * @param montoCobrado the montoCobrado to set
     */
    public void setMontoCobrado(Double montoCobrado) {
        this.montoCobrado = montoCobrado;
    }

    /**
     * @return the pagoConAvance
     */
    public Double getPagoConAvance() {
        return pagoConAvance;
    }

    /**
     * @param pagoConAvance the pagoConAvance to set
     */
    public void setPagoConAvance(Double pagoConAvance) {
        this.pagoConAvance = pagoConAvance;
    }

}
