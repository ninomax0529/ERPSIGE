/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.factura;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import manejo.ManejoConfiguracion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author maximiliano
 */
public class RptFactura {

    private URL ruta_maestro;
    private URL ruta_maestro_original;
    private URL ruta_formato_ticket;
    private URL ruta_maestro_ruta_rpt_entre_fecha;
    private URL ruta_maestro_ruta_rpt_por_fecha;
    private URL ruta_maestro_ruta_rpt_mensual;
    private URL ruta_factura_pendiente;
    private URL ruta_utilidad_bruta;
    private URL ruta_reporte_diario_venta;
    private URL ruta_img;
    private URL urlsub;
    public static RptFactura manager;

    private JasperReport SUB_RPT_DETALLE_FACTURA;

    public static RptFactura getInstancia() {
        if (manager == null) {
            manager = new RptFactura();
            return manager;
        }
        return manager;
    }
    private JasperReport reporte_maestro;
    private JasperReport reporte_maestro_original;
    private JasperReport reporte_formato_ticket;
    private JasperReport reporte_maestro_entre_fecha;
    private JasperReport reporte_maestro_por_fecha;
    private JasperReport reporte_maestro_mensual;
    private JasperReport reporte_utilidad_bruta;
    private JasperReport reporte_diario_venta;
    private JasperReport reporte_factura_pendiente;
    private URL RUTA_SUB_RPT_DETALLE_FACTURA;
//    private JasperReport SUB_RPT_DETALLE_ORDEN;

    /**
     * Constructor de ManejoImpresion
     */
    public RptFactura() {

        try {
//
//            ruta_maestro = getClass().getResource("/reporte/factura/FacturaVenta.jasper");
//            ruta_reporte_diario_venta = getClass().getResource("/reporte/factura/RptDeVenta.jasper");

            ruta_factura_pendiente = getClass().getResource("/reporte/factura/RptCuentaPorCobrar.jasper");
            ruta_maestro_ruta_rpt_entre_fecha = getClass().getResource("/reporte/factura/RptVentasEntreFecha.jasper");
            ruta_maestro_ruta_rpt_por_fecha = getClass().getResource("/reporte/factura/RptDiarioPorTipoDeVenta.jasper");
            ruta_maestro_ruta_rpt_mensual = getClass().getResource("/reporte/factura/RptVentasMensual.jasper");
            ruta_utilidad_bruta = getClass().getResource("/reporte/factura/RptUtilidadBruta.jasper");

            ruta_maestro = getClass().getResource("/reporte/factura/RptFacturaClienre.jasper");
            ruta_maestro_original = getClass().getResource("/reporte/factura/FacturaVentaOriginal.jasper");
            ruta_formato_ticket = getClass().getResource("/reporte/factura/FacturaVentaUV.jasper");
            RUTA_SUB_RPT_DETALLE_FACTURA = getClass().getResource("/reporte/factura/subRptDetalleFactura.jasper");

            ruta_reporte_diario_venta = getClass().getResource("/reporte/factura/RptDeVenta.jasper");

            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);
            reporte_maestro_original = (JasperReport) JRLoader.loadObject(ruta_maestro_original);
            reporte_formato_ticket = (JasperReport) JRLoader.loadObject(ruta_formato_ticket);
            reporte_maestro_entre_fecha = (JasperReport) JRLoader.loadObject(ruta_maestro_ruta_rpt_entre_fecha);
            reporte_maestro_por_fecha = (JasperReport) JRLoader.loadObject(ruta_maestro_ruta_rpt_por_fecha);
            reporte_maestro_mensual = (JasperReport) JRLoader.loadObject(ruta_maestro_ruta_rpt_mensual);
            SUB_RPT_DETALLE_FACTURA = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURA);
            reporte_diario_venta = (JasperReport) JRLoader.loadObject(ruta_reporte_diario_venta);
            reporte_factura_pendiente = (JasperReport) JRLoader.loadObject(ruta_factura_pendiente);
            reporte_utilidad_bruta = (JasperReport) JRLoader.loadObject(ruta_utilidad_bruta);

            ruta_img = getClass().getResource("/imagen/img_logo.png");
//                ruta_img = getClass().getResource("/imagen/logo_logios.jpg");

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(int factura, int formato) {

        Boolean impresionDirecta = ManejoConfiguracion.getInstancia().getConfiguracion().getImpresionDirecta();
        JasperPrint reporte_final = null;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("factura", factura);
//        parametros.put("SUBREPORT_DIR", "reporte/factura/");
////        parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);

        parametros.put("url", ruta_img.toString());

        try {

            if (formato == 1) {

                parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);

                reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());
            } else {
                parametros.put("SUBREPORT_DIR", "reporte/factura/");
                reporte_final = JasperFillManager.fillReport(reporte_formato_ticket, parametros, Conexion.getInsatancia().getConnectionDB());
            }

//            JasperViewer.viewReport(reporte_final, false);
            JasperPrint jasperPrint = reporte_final;

            int op;
//            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Facturacion", JOptionPane.YES_NO_OPTION);
//            if (op == 0) {

            if (impresionDirecta) {

                JasperPrintManager.printReport(jasperPrint, false);

            } else {

                JasperViewer.viewReport(jasperPrint, false);
            }

//           
//            }
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(int factura) {

        Boolean impresionDirecta = ManejoConfiguracion.getInstancia().getConfiguracion().getImpresionDirecta();
        JasperPrint reporte_final = null;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("factura", factura);
//        parametros.put("SUBREPORT_DIR", "reporte/factura/");
////        parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);

        parametros.put("url", ruta_img.toString());

        try {

            if (1 == 1) {

                parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);

                reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());
            } else {
                parametros.put("SUBREPORT_DIR", "reporte/factura/");
                reporte_final = JasperFillManager.fillReport(reporte_formato_ticket, parametros, Conexion.getInsatancia().getConnectionDB());
            }

//            JasperViewer.viewReport(reporte_final, false);
            JasperPrint jasperPrint = reporte_final;

            int op;
//            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Facturacion", JOptionPane.YES_NO_OPTION);
//            if (op == 0) {

            if (impresionDirecta) {

                JasperPrintManager.printReport(jasperPrint, false);

            } else {

                JasperViewer.viewReport(jasperPrint, false);
            }

//           
//            }
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(int factura, int formato, String estadoFactura) {

        Boolean impresionDirecta = ManejoConfiguracion.getInstancia().getConfiguracion().getImpresionDirecta();
        JasperPrint reporte_final = null;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("factura", factura);
        parametros.put("estado_factura", estadoFactura);
//        parametros.put("SUBREPORT_DIR", "reporte/factura/");
////        parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);

        parametros.put("url", ruta_img.toString());

        try {

            if (formato == 1) {

                parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);

                reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());
            } else {
                parametros.put("SUBREPORT_DIR", "reporte/factura/");
                reporte_final = JasperFillManager.fillReport(reporte_formato_ticket, parametros, Conexion.getInsatancia().getConnectionDB());
            }

//            JasperViewer.viewReport(reporte_final, false);
            JasperPrint jasperPrint = reporte_final;

            int op;
//            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Facturacion", JOptionPane.YES_NO_OPTION);
//            if (op == 0) {

            if (impresionDirecta) {

                JasperPrintManager.printReport(jasperPrint, false);

            } else {

                JasperViewer.viewReport(jasperPrint, false);
            }

//           
//            }
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void verFactura(int factura, int formato) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("factura", factura);

        parametros.put("url", ruta_img.toString());

        try {

            if (formato == 1) {

                parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);

                reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());
            } else {

                parametros.put("SUBREPORT_DIR", "reporte/factura/");
                reporte_final = JasperFillManager.fillReport(reporte_formato_ticket, parametros, Conexion.getInsatancia().getConnectionDB());
            }
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

    public void verFactura(int factura, int formato, String estadoFactura) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("factura", factura);
        parametros.put("estado_factura", estadoFactura);

        parametros.put("url", ruta_img.toString());

        try {

            if (formato == 1) {

                parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);

                reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());
            } else {

                parametros.put("SUBREPORT_DIR", "reporte/factura/");
                reporte_final = JasperFillManager.fillReport(reporte_formato_ticket, parametros, Conexion.getInsatancia().getConnectionDB());
            }
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

    public void verFacturaOriginal(int factura, int formato, String estadoFactura) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("factura", factura);
        parametros.put("estado_factura", estadoFactura);

        parametros.put("url", ruta_img.toString());

        try {

            if (formato == 1) {//Forma to 8 1/2 por 14

                parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);

                reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            } else { //Formato ticket

                parametros.put("SUBREPORT_DIR", "reporte/factura/");
                reporte_final = JasperFillManager.fillReport(reporte_maestro_original, parametros, Conexion.getInsatancia().getConnectionDB());
            }
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

    public void reporteEntreFecha(Date fechaInicio, Date fechaFinal) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("fechaini", fechaInicio);
        parametros.put("fechafin", fechaFinal);
        parametros.put("url", ruta_img.toString());
        parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro_entre_fecha, parametros, Conexion.getInsatancia().getConnectionDB());

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

    public void reporteEntreFecha(String sqlParam, Date fechaInicio, Date fechaFinal) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {

            ruta_img = getClass().getResource("/imagen/logo_pintura_triplea_v2.png");

        } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {

            ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
        }

        parametros.put("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("fechaini", fechaInicio);
        parametros.put("fechafin", fechaFinal);
        parametros.put("sqlParam", sqlParam);
        parametros.put("url", ruta_img.toString());
        parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro_entre_fecha, parametros, Conexion.getInsatancia().getConnectionDB());

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

    public void reporteDiarioVenta(Date fecha) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {

            ruta_img = getClass().getResource("/imagen/logo_pintura_triplea_v2.png");

        } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {

            ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
        }

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("fecha", fecha);
//        parametros.put("ung", 2);
        parametros.put("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro_por_fecha, parametros, Conexion.getInsatancia().getConnectionDB());

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

    public void reporteMensualVenta(Date fecha) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {

            ruta_img = getClass().getResource("/imagen/logo_pintura_triplea_v2.png");

        } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {

            ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
        }

        parametros.put("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("fecha", fecha);

        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro_mensual, parametros, Conexion.getInsatancia().getConnectionDB());

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

    public void reporteUtilidadBruta(Date fechaInicio, Date fechaFin) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {

            ruta_img = getClass().getResource("/imagen/logo_pintura_triplea_v2.png");

        } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {

            ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
        }

        parametros.put("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("fechaini", fechaInicio);
        parametros.put("fechafin", fechaFin);

        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_utilidad_bruta, parametros, Conexion.getInsatancia().getConnectionDB());

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

    public void reporteDiarioDeVenta(Date fecha) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("fecha", fecha);

        parametros.put("SUBREPORT_DIR", "reporte/factura/");

        try {

            reporte_final = JasperFillManager.fillReport(reporte_diario_venta, parametros, Conexion.getInsatancia().getConnectionDB());

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

    public void reporteFacturaPendiente(String parametro, Date fechaInicio, Date fechaFinal) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {

            ruta_img = getClass().getResource("/imagen/logo_pintura_triplea_v2.png");

        } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {

            ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
        }
        parametros.put("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("fecha_inicio", fechaInicio);
        parametros.put("fecha_final", fechaFinal);

        parametros.put("sqlParam", parametro);

        parametros.put("SUBREPORT_DIR", "reporte/factura/");

        try {

            reporte_final = JasperFillManager.fillReport(reporte_factura_pendiente, parametros, Conexion.getInsatancia().getConnectionDB());

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
    
     public void imprimirPorLote(int nom, int empleado) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("nomina", nom);
        parametros.put("empleado", empleado);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrintManager.printReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

        getInstancia().reporteDiarioVenta(new Date("2021/12/06"));

//        getInstancia().verFacturaOriginal(444, 2, "PENDIENTE");
    }
}
