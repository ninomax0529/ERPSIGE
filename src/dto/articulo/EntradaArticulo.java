/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.articulo;

import java.util.Date;
import javafx.scene.control.Button;

/**
 *
 * @author maximilianoa-te
 */
public class EntradaArticulo {

    private Integer codigo;
    private String nombre;
    private String unidad;
    private Double cantidad;
    private Integer entrada;
    private Date fecha;
    private Double costoUnitario;
    private Double costoTotal;
    private String anulada;
    private Button button;

    /**
     * @return the button
     */
    public Button getButton() {
        return button;
    }

    /**
     * @param button the button to set
     */
    public void setButton(Button button) {
        this.button = button;
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the unidad
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    /**
     * @return the cantidad
     */
    public Double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the entrada
     */
    public Integer getEntrada() {
        return entrada;
    }

    /**
     * @param entrada the entrada to set
     */
    public void setEntrada(Integer entrada) {
        this.entrada = entrada;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the costoUnitario
     */
    public Double getCostoUnitario() {
        return costoUnitario;
    }

    /**
     * @param costoUnitario the costoUnitario to set
     */
    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    /**
     * @return the costoTotal
     */
    public Double getCostoTotal() {
        return costoTotal;
    }

    /**
     * @param costoTotal the costoTotal to set
     */
    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    /**
     * @return the anulada
     */
    public String getAnulada() {
        return anulada;
    }

    /**
     * @param anulada the anulada to set
     */
    public void setAnulada(String anulada) {
        this.anulada = anulada;
    }

}
