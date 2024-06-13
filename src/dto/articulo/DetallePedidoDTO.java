/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.articulo;

import javafx.scene.image.Image;
import modelo.Articulo;
import modelo.Pedido;

import modelo.Unidad;

/**
 *
 * @author password 1234567890
 */
public class DetallePedidoDTO {

    private Integer numeroArticulo;

    private Integer codigo;

    private Double cantidad;

    private Double precio;

    private Double descuento;

    private Double itbis;

    private Double existencia;

    private Double nuevaExistencia;

    private Double total;

    private Double subTotal;

    private String nombreArticulo;

    private Double precioItbis;

    private Articulo articulo;

    private Pedido pedido;

    private Unidad unidad;
    private Image imagen;

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public DetallePedidoDTO() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getItbis() {
        return itbis;
    }

    public void setItbis(Double itbis) {
        this.itbis = itbis;
    }

    public Double getExistencia() {
        return existencia;
    }

    public void setExistencia(Double existencia) {
        this.existencia = existencia;
    }

    public Double getNuevaExistencia() {
        return nuevaExistencia;
    }

    public void setNuevaExistencia(Double nuevaExistencia) {
        this.nuevaExistencia = nuevaExistencia;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public Double getPrecioItbis() {
        return precioItbis;
    }

    public void setPrecioItbis(Double precioItbis) {
        this.precioItbis = precioItbis;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    public Integer getNumeroArticulo() {
        return numeroArticulo;
    }

    public void setNumeroArticulo(Integer numeroArticulo) {
        this.numeroArticulo = numeroArticulo;
    }

}
