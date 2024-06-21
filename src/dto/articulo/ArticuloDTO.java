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
public class ArticuloDTO {

    private Integer codigo;
    private String nombre;
    private String unidad;
    private Double cantidad;
    private Integer factura;
    private Date fecha;
    private Double costoUnitario;
    private Double precioVenta;
    private Double precioVentaLpg;
    private Double precioVentaLpm;
    private Double precioVentaLpn;
    private Double precioVentaLpd;
    private Double existencia;
    private String categoria;
    private Integer listaDePrecio;
    private Integer almacen;
    private String nombreAlmacen;
    private Integer CodigoUnidad;
    private Boolean habilitado;

    public Integer getListaDePrecio() {
        return listaDePrecio;
    }

    public void setListaDePrecio(Integer listaDePrecio) {
        this.listaDePrecio = listaDePrecio;
    }

    public Integer getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Integer almacen) {
        this.almacen = almacen;
    }

    public Integer getCodigoUnidad() {
        return CodigoUnidad;
    }

    public void setCodigoUnidad(Integer CodigoUnidad) {
        this.CodigoUnidad = CodigoUnidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getExistencia() {
        return existencia;
    }

    public void setExistencia(Double existencia) {
        this.existencia = existencia;
    }

    /**
     * @return the precioVentaLpg
     */
    public Double getPrecioVentaLpg() {
        return precioVentaLpg;
    }

    /**
     * @param precioVentaLpg the precioVentaLpg to set
     */
    public void setPrecioVentaLpg(Double precioVentaLpg) {
        this.precioVentaLpg = precioVentaLpg;
    }

    /**
     * @return the precioVentaLpm
     */
    public Double getPrecioVentaLpm() {
        return precioVentaLpm;
    }

    /**
     * @param precioVentaLpm the precioVentaLpm to set
     */
    public void setPrecioVentaLpm(Double precioVentaLpm) {
        this.precioVentaLpm = precioVentaLpm;
    }

    /**
     * @return the precioVentaLpn
     */
    public Double getPrecioVentaLpn() {
        return precioVentaLpn;
    }

    /**
     * @param precioVentaLpn the precioVentaLpn to set
     */
    public void setPrecioVentaLpn(Double precioVentaLpn) {
        this.precioVentaLpn = precioVentaLpn;
    }

    /**
     * @return the precioVentaLpd
     */
    public Double getPrecioVentaLpd() {
        return precioVentaLpd;
    }

    /**
     * @param precioVentaLpd the precioVentaLpd to set
     */
    public void setPrecioVentaLpd(Double precioVentaLpd) {
        this.precioVentaLpd = precioVentaLpd;
    }

    public Integer getFactura() {
        return factura;
    }

    public void setFactura(Integer factura) {
        this.factura = factura;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }
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
     * @return the factura
     */
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
     * @return the precioVenta
     *
     *
     * /
     **
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

    /**
     * @return the habilitado
     */
    public Boolean getHabilitado() {
        return habilitado;
    }

    /**
     * @param habilitado the habilitado to set
     */
    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * @return the nombreAlmacen
     */
    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    /**
     * @param nombreAlmacen the nombreAlmacen to set
     */
    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

}
