/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.DetalleFormato606;
import modelo.DetalleFormato607;

/**
 *
 * @author maximilianoa-te
 */
public class CrearFormatoDgii {

    public CrearFormatoDgii() {

    }

    public static void crearArchivo606(List<DetalleFormato606> lista, String ruta) {

        String ruraArchivo = ruta + "/DGII_F_606_" + new SimpleDateFormat("hh mm ss").format(new Date()) + "_" + lista.get(0).getFormato606().getPeriodo() + ".txt";
        try {

//            FileWriter fWriter = new FileWriter("C:\\Max Erp\\colmado erp\\ERPSIGE\\606.txt");
            FileWriter fWriter = new FileWriter(ruraArchivo);

            try (PrintWriter ptWriter = new PrintWriter(fWriter, false)) {
                StringBuilder str = new StringBuilder();

                //primera linea del archivo 606 
                System.out.println("lista.get(0).getRncOCedula() " + lista.get(0).getRncOCedula());
                str.append(606).
                        append("|").
                        append(lista.get(0).getFormato606().getRnc().replace("-", ""))
                        .append("|")
                        .append(lista.get(0).getFormato606().getPeriodo())
                        .append("|")
                        .append(lista.get(0).getFormato606().getCantidadRegistro());

                ptWriter.println(str);

                for (DetalleFormato606 dft : lista) {

                    str = new StringBuilder();

                    str.append(dft.getRncOCedula().replace("-", ""))
                            .append("|")
                            .append(dft.getTipoId())
                            .append("|")
                            .append(dft.getTipoBienesYServicios().getCodigo().toString().length() > 1 ? dft.getTipoBienesYServicios().getCodigo()
                                    : "0" + dft.getTipoBienesYServicios().getCodigo()
                            )
                            .append("|")
                            .append(dft.getNcf())
                            .append("|")
                            .append(dft.getNcfModificado() == null ? "" : dft.getNcfModificado())
                            .append("|")
                            .append(dft.getFechaComprobante().toString().replace("-", "").trim())
                            .append("|")
                            .append(dft.getFechaPago() == null ? "" : dft.getFechaPago().toString().replace("-", "").trim())
                            .append("|")
                            .append((dft.getMontoEnServicio() == null || dft.getMontoEnServicio() <= 0) ? "" : dft.getMontoEnServicio())
                            .append("|")
                            .append((dft.getMontoEnBienes() == null || dft.getMontoEnBienes() <= 0) ? "" : dft.getMontoEnBienes())
                            .append("|")
                            .append(dft.getTotalFacturado())
                            .append("|")
                            .append(dft.getItbisFacturado() <= 0 ? "" : dft.getItbisFacturado())
                            .append("|")
                            .append((dft.getItbisRetenido() == null || dft.getItbisRetenido() <= 0) ? "" : dft.getItbisRetenido())
                            .append("|")
                            .append((dft.getItbisSujetoAProporcionalidad() == null || dft.getItbisSujetoAProporcionalidad() <= 0) ? "" : dft.getItbisSujetoAProporcionalidad())
                            .append("|")
                            .append((dft.getItbisLlevadoAlCosto() == null || dft.getItbisLlevadoAlCosto() <= 0) ? "0" : dft.getItbisLlevadoAlCosto())
                            .append("|")
                            .append((dft.getItbisPorAdelantar() == null || dft.getItbisPorAdelantar() <= 0) ? "0" : dft.getItbisPorAdelantar())
                            .append("|")
                            .append((dft.getItbisPercibidoEnCompra() == null || dft.getItbisPercibidoEnCompra() <= 0) ? "" : dft.getItbisPercibidoEnCompra())
                            .append("|")
                            .append(dft.getTipoRetencionIsr() == null ? "" : dft.getTipoRetencionIsr().getCodigo())
                            .append("|")
                            .append((dft.getIsrRetenido() == null || dft.getIsrRetenido() <= 0) ? "" : dft.getIsrRetenido())
                            .append("|")
                            .append((dft.getIsrPercibidoEnCompra() == null || dft.getIsrPercibidoEnCompra() <= 0) ? "" : dft.getIsrPercibidoEnCompra())
                            .append("|")
                            .append((dft.getImpuestoSelectivoAlComsumo() == null || dft.getImpuestoSelectivoAlComsumo() <= 0) ? "" : dft.getImpuestoSelectivoAlComsumo())
                            .append("|")
                            .append((dft.getOtrosImpuestoTasa() == null || dft.getOtrosImpuestoTasa() <= 0) ? "" : dft.getOtrosImpuestoTasa())
                            .append("|")
                            .append((dft.getMontoPropinaLegal() == null || dft.getMontoPropinaLegal() <= 0) ? "" : dft.getMontoPropinaLegal())
                            .append("|")
                            .append(dft.getFormaDePago() == null ? "" : "0" + dft.getFormaDePago().getCodigo());

                    ptWriter.println(str);

                }

                ptWriter.flush();
                fWriter.close();
                ptWriter.close();
                Desktop.getDesktop().open(new File(ruraArchivo));
            }

        } catch (IOException ex) {
            Logger.getLogger(CrearFormatoDgii.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void crearArchivo607(List<DetalleFormato607> lista, String ruta) {

        String ruraArchivo = ruta + "/DGII_F_607_" + new SimpleDateFormat("hh mm ss").format(new Date()) + "_" + lista.get(0).getFormato607().getPeriodo() + ".txt";
        try {

//            FileWriter fWriter = new FileWriter("C:\\Max Erp\\colmado erp\\ERPSIGE\\606.txt");
            FileWriter fWriter = new FileWriter(ruraArchivo);

            try (PrintWriter ptWriter = new PrintWriter(fWriter, false)) {
                StringBuilder str = new StringBuilder();

                //primera linea del archivo 607
                System.out.println("lista.get(0).getRncOCedula() " + lista.get(0).getRncOCedula());
                str.append(607).
                        append("|").
                        append(lista.get(0).getFormato607().getRnc().replace("-", ""))
                        .append("|")
                        .append(lista.get(0).getFormato607().getPeriodo())
                        .append("|")
                        .append(lista.get(0).getFormato607().getCantidadRegistro());

                ptWriter.println(str);

                for (DetalleFormato607 dft : lista) {

                    str = new StringBuilder();

                    str.append(dft.getRncOCedula().replace("-", ""))
                            .append("|")
                            .append(dft.getTipoId())
                            .append("|")
                            .append(dft.getNcf())
                            .append("|")
                            .append(dft.getNcfModificado() == null ? "" : dft.getNcfModificado())
                            .append("|")
                            .append(dft.getTipoDeIngreso().getCodigo())
                            .append("|")
                            .append(dft.getFechaComprobante().toString().replace("-", "").trim())
                            .append("|")
                            .append(dft.getFechaDeRetension() == null ? "" : dft.getFechaDeRetension().toString().replace("-", "").trim())
                            .append("|")
                            .append(dft.getTotalFacturado())
                            .append("|")
                            .append(dft.getItbisFacturado() <= 0 ? "" : dft.getItbisFacturado())
                            .append("|")
                            .append((dft.getItbisRetenidoPorTercero() == null || dft.getItbisRetenidoPorTercero() <= 0) ? "" : dft.getItbisRetenidoPorTercero())
                            .append("|")
                            .append((dft.getItbisPercibido() == null || dft.getItbisPercibido() <= 0) ? "" : dft.getItbisPercibido())
                            .append("|")
                            .append((dft.getIsrRetenidoPorTercero() == null || dft.getIsrRetenidoPorTercero() <= 0) ? "0" : dft.getIsrRetenidoPorTercero())
                            .append("|")
                            .append((dft.getIsrPercibido() == null || dft.getIsrPercibido() <= 0) ? "0" : dft.getIsrPercibido())
                            .append("|")
                            .append((dft.getImpuestoSelectivoAlComsumo() == null || dft.getImpuestoSelectivoAlComsumo() <= 0) ? "" : dft.getImpuestoSelectivoAlComsumo())
                            .append("|")
                            .append((dft.getOtrosImpuestoTasa() == null || dft.getOtrosImpuestoTasa() <= 0) ? "" : dft.getOtrosImpuestoTasa())
                            .append("|")
                            .append((dft.getMontoPropinaLegal() == null || dft.getMontoPropinaLegal() <= 0) ? "" : dft.getMontoPropinaLegal())
                            .append("|")
                            .append(dft.getEfectivo() == null ? "" : dft.getEfectivo())
                            .append("|")
                            .append(dft.getChqTransfDeposito() == null ? "" : dft.getChqTransfDeposito())
                            .append("|")
                            .append(dft.getTarjetaDebitoCredito() == null ? "" : dft.getTarjetaDebitoCredito())
                            .append("|")
                            .append(dft.getVentaACredito() == null ? "" :dft.getVentaACredito())
                            .append("|")
                            .append(dft.getBonosOCertificado() == null ? "" :dft.getBonosOCertificado())
                            .append("|")
                            .append(dft.getPermuta() == null ? "" : dft.getPermuta())
                            .append("|")
                            .append(dft.getOtrasFormaDeVenta() == null ? "" :dft.getOtrasFormaDeVenta());

                    ptWriter.println(str);

                }

                ptWriter.flush();
                fWriter.close();
                ptWriter.close();
                Desktop.getDesktop().open(new File(ruraArchivo));
            }

        } catch (IOException ex) {
            Logger.getLogger(CrearFormatoDgii.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {

        CrearFormatoDgii cfdi = new CrearFormatoDgii();
        cfdi.crearArchivo606(null, "");

    }
}
