/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import manejo.impuesto.ManejoRetencionDeImpuesto;

/**
 *
 * @author maximilianoa-te
 */
public class RetencionDGII {

    public static Double retencionISRPorServicio(Double subTotal, int tipoServicio) {

        Double valorRetenido = 0.00, porcientoRetencion = 0.00;
        Integer porciento = 0;
        if (tipoServicio == 1) {//codigo 1 el servicio profeccional

            porciento = ManejoRetencionDeImpuesto.getInstancia().getImpuestoIsr(2).getValor().intValue();
            porcientoRetencion = porciento / 100.00;

        } else if (tipoServicio == 2) { //codigo 2 el servicio no profeccional

            porciento = ManejoRetencionDeImpuesto.getInstancia().getImpuestoIsr(1).getValor().intValue();
            porcientoRetencion = porciento / 100.00;
        }

        valorRetenido = ClaseUtil.roundDouble(subTotal * porcientoRetencion, 2);

        return valorRetenido;
    }

    public static Double retencionITBISPorServicio(Double totalItbis, int tipoServicio, int tiposuplidor) {

        Double valorRetenido = 0.00, porcientoRetencion = 0.00;
        Integer porciento = 0;

        if (tipoServicio == 1) {//codigo 1 el servicio profeccional

            if (tiposuplidor == 1) {// persona juridica ( suplidor formal )

                porciento = ManejoRetencionDeImpuesto.getInstancia().getImpuestoItbis(3).getValor().intValue();
                porcientoRetencion = porciento / 100.00;

            } else if (tiposuplidor == 2) {//persona fisica (no foemal )

                porciento = ManejoRetencionDeImpuesto.getInstancia().getImpuestoItbis(4).getValor().intValue();
                porcientoRetencion = porciento / 100.00;
            }

        } else if (tipoServicio == 2) { //codigo 2 el servicio no profeccional

            if (tiposuplidor == 1) {// persona juridica ( suplidor formal )

                porciento = ManejoRetencionDeImpuesto.getInstancia().getImpuestoItbis(3).getValor().intValue();
                porcientoRetencion = porciento / 100.00;

            } else if (tiposuplidor == 2) {//persona fisica (no foemal )

                porciento = ManejoRetencionDeImpuesto.getInstancia().getImpuestoItbis(4).getValor().intValue();
                porcientoRetencion = porciento / 100.00;
            }

        }

        System.out.println("Porciento : " + porcientoRetencion + " porciento : " + porciento);
        valorRetenido = ClaseUtil.roundDouble(totalItbis * porcientoRetencion, 2);

        return valorRetenido;
    }

    public static Double retencionITBISPorBienes(Double totalItbis, int tiposuplidor) {

        Double valorRetenido = 0.00, porcientoRetencion = 0.00;
        Integer porciento = 0;

        if (tiposuplidor == 1) {// persona juridica ( suplidor formal )

            porciento = ManejoRetencionDeImpuesto.getInstancia().getImpuestoItbis(3).getValor().intValue();
            porcientoRetencion = 0/ 100.00;

        } else if (tiposuplidor == 2) {//persona fisica (no formal )

            porciento = ManejoRetencionDeImpuesto.getInstancia().getImpuestoItbis(4).getValor().intValue();
            porcientoRetencion = porciento / 100.00;
        }

       

        System.out.println("porcientoRetencion : " + porcientoRetencion + " porciento : " + porciento);
        valorRetenido = ClaseUtil.roundDouble(totalItbis * porcientoRetencion, 2);

        return valorRetenido;
    }

    public static void main(String[] args) {

//        System.out.println(" itbis : " + RetencionDGII.retencionITBISPorServicio(7200.00, 1, 2));
//
//        System.out.println(" irs : " + RetencionDGII.retencionISRPorServicio(40000.00, 1));
        
          System.out.println(" itbis : " + RetencionDGII.retencionITBISPorBienes(7200.00, 2));
    }

}
