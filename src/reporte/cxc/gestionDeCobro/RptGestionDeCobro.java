/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.cxc.gestionDeCobro;

import reporte.cxc.*;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import modelo.ReciboIngreso;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author MAXIMILIANO
 */
public class RptGestionDeCobro {

    private URL ruta_maestro;
    private URL ruta_formato_ticket;
    private URL ruta_formato_ticket_sin_factura;
    private URL ruta_img;
    private URL urlsub;
    public static RptGestionDeCobro manager;
    private JasperReport reporte_maestro;
    private JasperReport reporte_formato_ticket;
    private JasperReport reporte_formato_ticket_sin_factura;
    private JasperReport SUB_RPT_DETALLE_FACTURA;
    private URL RUTA_SUB_RPT_DETALLE_RECIBO;

    public static RptGestionDeCobro getInstancia() {
        if (manager == null) {
            manager = new RptGestionDeCobro();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptGestionDeCobro() {

        try {

            ruta_maestro = getClass().getResource("RptGestionDeCobroEntreFechajrxml.jasper");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);
          
        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void imprimir(Date fi, Date ff) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        ruta_img = getClass().getResource("/imagen/logo_igh_track.png");

        parametros.put("fechaIni", fi);
        parametros.put("fechaFin", ff);
        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(int recibo, int formato) {

        JasperPrint reporte_final = null;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("url", ruta_img.toString());
        parametros.put("recibo", recibo);

        try {

            if (formato == 1) {

                parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
                reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            } else {

                parametros.put("SUBREPORT_DIR", "reporte/cxc/");
                reporte_final = JasperFillManager.fillReport(reporte_formato_ticket, parametros, Conexion.getInsatancia().getConnectionDB());
            }

//            JasperViewer.viewReport(reporte_final, false);
            JasperPrint jasperPrint = reporte_final;

            int op;
//            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Facturacion", JOptionPane.YES_NO_OPTION);
//            if (op == 0) {

//            JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimirRcSinFactura(int recibo, int formato) {

        JasperPrint reporte_final = null;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("url", ruta_img.toString());
        parametros.put("recibo", recibo);

        try {

            if (formato == 1) {

                parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
                reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            } else {

                parametros.put("SUBREPORT_DIR", "reporte/cxc/");
                reporte_final = JasperFillManager.fillReport(reporte_formato_ticket_sin_factura, parametros, Conexion.getInsatancia().getConnectionDB());
            }

//            JasperViewer.viewReport(reporte_final, false);
            JasperPrint jasperPrint = reporte_final;

            int op;
//            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Facturacion", JOptionPane.YES_NO_OPTION);
//            if (op == 0) {

//            JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void verRecibo(int recibo, int formato) {

        JasperPrint reporte_final = null;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("url", ruta_img.toString());
        parametros.put("recibo", recibo);

        try {

            if (formato == 1) {

                parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
                reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            } else {

                parametros.put("SUBREPORT_DIR", "reporte/cxc/");
                reporte_final = JasperFillManager.fillReport(reporte_formato_ticket, parametros, Conexion.getInsatancia().getConnectionDB());
            }

//            JasperViewer.viewReport(reporte_final, false);
            JasperPrint jasperPrint = reporte_final;

            int op;
//            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Facturacion", JOptionPane.YES_NO_OPTION);
//            if (op == 0) {

//            JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void verRecibo(int recibo) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("recibo", recibo);
        parametros.put("url", ruta_img.toString());
        parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);

//        parametros.put("SUBREPORT_DIR", "reporte/cxc/");
        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

//            JasperViewer.viewReport(reporte_final, false);
            JasperPrint jasperPrint = reporte_final;

            int op;
//            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Facturacion", JOptionPane.YES_NO_OPTION);
//            if (op == 0) {

//            JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimirPorLote(ReciboIngreso recibo) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("factura", recibo.getCodigo());

        parametros.put("url", ruta_img.toString());

        try {

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrintManager.printReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        getInstancia().imprimir(new Date("2022/08/27"),new Date("2022/08/31"));
    }
}
