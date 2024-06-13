/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.venta;

/**
 *
 * @author maximilianoa-te
 */
public class FacturaDTO {

    public FacturaDTO() {
    }

    private String fecha;
    private String cliente;
    private String ncf;
    private Double subTotal;
    private Double itbis;
    private Double total;

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
     * @return the ncf
     */
    public String getNcf() {
        return ncf;
    }

    /**
     * @param ncf the ncf to set
     */
    public void setNcf(String ncf) {
        this.ncf = ncf;
    }

    /**
     * @return the subTotal
     */
    public Double getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal the subTotal to set
     */
    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * @return the itbis
     */
    public Double getItbis() {
        return itbis;
    }

    /**
     * @param itbis the itbis to set
     */
    public void setItbis(Double itbis) {
        this.itbis = itbis;
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

}
