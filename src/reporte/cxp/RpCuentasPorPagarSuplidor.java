/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.cxp;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utiles.Conexion;

/**
 *
 * @author maximiliano
 */
public class RpCuentasPorPagarSuplidor {

    private URL ruta_maestro;
    private URL ruta_maestro_ruta_rpt_entre_fecha;
    private URL ruta_maestro_ruta_rpt_por_fecha;
    private URL ruta_maestro_ruta_rpt_mensual;
    private URL ruta_factura_por_pagar;
    private URL ruta_utilidad_bruta;
    private URL ruta_reporte_diario_venta;
    private URL ruta_compra_proyecto;
    private URL ruta_img;
    private URL urlsub;
    public static RpCuentasPorPagarSuplidor manager;

    private JasperReport SUB_RPT_DETALLE_FACTURA;

    public static RpCuentasPorPagarSuplidor getInstancia() {
        if (manager == null) {
            manager = new RpCuentasPorPagarSuplidor();
            return manager;
        }
        return manager;
    }

    private JasperReport reporte_maestro_entre_fecha;
    private JasperReport reporte_maestro_por_fecha;
    private JasperReport reporte_maestro_mensual;

    private JasperReport reporte_factura_por_pagar;
    private JasperReport reporte_compra_proyecto;

    private URL RUTA_SUB_RPT_DETALLE_FACTURA;
//    private JasperReport SUB_RPT_DETALLE_ORDEN;

    /**
     * Constructor de ManejoImpresion
     */
    public RpCuentasPorPagarSuplidor() {

        try {

            ruta_factura_por_pagar = getClass().getResource("/reporte/cxp/jasper/RptFacturaPendienteSuplidor.jasper");
            ruta_maestro_ruta_rpt_entre_fecha = getClass().getResource("/reporte/factura/RptVentasEntreFecha.jasper");
            ruta_maestro_ruta_rpt_por_fecha = getClass().getResource("/reporte/factura/RptDiarioPorTipoDeVenta.jasper");
            ruta_maestro_ruta_rpt_mensual = getClass().getResource("/reporte/factura/RptVentasMensual.jasper");
            ruta_utilidad_bruta = getClass().getResource("/reporte/factura/RptUtilidadBruta.jasper");
            ruta_compra_proyecto = getClass().getResource("/reporte/cxp/RptCompraPorProyecto.jasper");

            ruta_maestro = getClass().getResource("/reporte/cxp/RptFacturaSuplidor.jasper");
            RUTA_SUB_RPT_DETALLE_FACTURA = getClass().getResource("/reporte/cxp/subRptDetalleFacturaSuplidor.jasper");

            ruta_reporte_diario_venta = getClass().getResource("/reporte/factura/RptDeVenta.jasper");

        
            reporte_maestro_entre_fecha = (JasperReport) JRLoader.loadObject(ruta_maestro_ruta_rpt_entre_fecha);
            reporte_maestro_por_fecha = (JasperReport) JRLoader.loadObject(ruta_maestro_ruta_rpt_por_fecha);
            reporte_maestro_mensual = (JasperReport) JRLoader.loadObject(ruta_maestro_ruta_rpt_mensual);
            SUB_RPT_DETALLE_FACTURA = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURA);
        
            reporte_factura_por_pagar = (JasperReport) JRLoader.loadObject(ruta_factura_por_pagar);
       
            reporte_compra_proyecto = (JasperReport) JRLoader.loadObject(ruta_compra_proyecto);

            ruta_img = getClass().getResource("/imagen/magla_img.jpg");
//                ruta_img = getClass().getResource("/imagen/logo_logios.jpg");

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void compraEntreFecha(Date fechaInicio, Date fechaFinal, String sqlParam) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("fechaIni", fechaInicio);
        parametros.put("fechafin", fechaFinal);
        parametros.put("unidad_negocio", 5);
        parametros.put("sqlParam", sqlParam);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_compra_proyecto, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

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

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("fecha", fecha);

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

    public void reporteFacturaPendiente(String parametro, Date fechaInicio, Date fechaFinal) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("fecha_inicio", fechaInicio);
        parametros.put("fecha_final", fechaFinal);
        parametros.put("sqlParam", parametro);

        parametros.put("SUBREPORT_DIR", "reporte/factura/");

        try {

            reporte_final = JasperFillManager.fillReport(reporte_factura_por_pagar, parametros, Conexion.getInsatancia().getConnectionDB());

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

    public void facturaPendiente(Date fechaDesde, Date fechaHasta, String sqlParam, String sqlCliente, String sqlPendienteCero) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();

        System.out.println("sqlParam rpt : " + sqlParam);
        parametros.put("fecha_desde", fechaDesde);
        parametros.put("fecha_hasta", fechaHasta);
        parametros.put("unidad_negocio", 5);
//        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        parametros.put("sqlParam", sqlParam);
        parametros.put("sqlCliente", sqlCliente);
        parametros.put("sqlParamPendienteCero", sqlPendienteCero);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_factura_por_pagar, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

          getInstancia().facturaPendiente(new Date("2022/08/01"), new Date("2022/10/31"),
                "   and  f.unidad_de_negocio=5  and  ri.fecha between '2022-12-01'  and '2023-12-31'    ",
                "  and  f.fecha between '2022-01-01'  and '2023-01-19'     and  f.unidad_de_negocio=5 ", "  where  r.pendiente>=0 ");
    }
}
