/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.pagos;

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
public class RptPago {

    private URL ruta_maestro;
    private URL ruta_formato_ticket;
    private URL ruta_formato_ticket_sin_factura;
    private URL ruta_img;
    private URL urlsub;
    public static RptPago manager;
    private JasperReport reporte_maestro;
    private JasperReport reporte_formato_ticket;
    private JasperReport reporte_formato_ticket_sin_factura;
    private JasperReport SUB_RPT_DETALLE_COMPRBANTE;
    private JasperReport SUB_RPT_FORMAPAGO;
    private URL RUTA_SUB_RPT_DETALLE_RECIBO;
    private URL RUTA_SUB_RPT_FORMAPAGO;

    public static RptPago getInstancia() {
        if (manager == null) {
            manager = new RptPago();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptPago() {

        try {

            ruta_maestro = getClass().getResource("RptComprobanteDePago.jasper");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);
            RUTA_SUB_RPT_DETALLE_RECIBO = getClass().getResource("subRptDetalleComprobanteDePago.jasper");
            RUTA_SUB_RPT_FORMAPAGO = getClass().getResource("subRptFromaDePago.jasper");
            SUB_RPT_DETALLE_COMPRBANTE = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_RECIBO);
            SUB_RPT_FORMAPAGO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_FORMAPAGO);

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void imprimir(Date fi, Date ff) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

//        if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {
//
//            ruta_img = getClass().getResource("/imagen/logo_pintura_triplea_v2.png");
//
//        } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {
//
        ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
//        }

        parametros.put("url", ruta_img.toString());
        parametros.put("fechaIni", fi);
        parametros.put("fechaFin", ff);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(Date fi, Date ff, String sqlParam, String tipoRecibo) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

//        if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {
//
//            ruta_img = getClass().getResource("/imagen/logo_pintura_triplea_v2.png");
//
//        } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {
//
        ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
//        }

        parametros.put("url", ruta_img.toString());
        parametros.put("fechaIni", fi);
        parametros.put("fechaFin", ff);
        parametros.put("sqlParam", ff);
        parametros.put("tipoRecibo", ff);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

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

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_COMPRBANTE);

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrintManager.printReport(reporte_final, false);

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

                parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_COMPRBANTE);
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

                parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_COMPRBANTE);
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

                parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_COMPRBANTE);
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
        parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_COMPRBANTE);

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

    public void imprimir(int recibo) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        parametros.put("recibo", recibo);
        parametros.put("unidad_negocio",VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_COMPRBANTE);
        parametros.put("SUB_RPT_FORMA_PAGO", SUB_RPT_FORMAPAGO);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

        getInstancia().imprimir(2);
    }
}
