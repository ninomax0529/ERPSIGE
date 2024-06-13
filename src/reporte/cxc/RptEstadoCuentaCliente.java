/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.cxc;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import manejo.factura.ManejoFactura;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author maximiliano
 */
public class RptEstadoCuentaCliente {

    private URL ruta_estado_cuenta;
    private URL ruta_estado_de_cuenta_al_corte;
    private URL ruta_img;

    public static RptEstadoCuentaCliente manager;

    private JasperReport SUB_RPT_DETALLE_FACTURA;
    private JasperReport reporte_estado_de_cuenta_al_corte;

    public static RptEstadoCuentaCliente getInstancia() {

        if (manager == null) {
            manager = new RptEstadoCuentaCliente();
            return manager;
        }
        return manager;
    }

    private JasperReport reporte_estado_cuenta;

//    private JasperReport SUB_RPT_DETALLE_ORDEN;
    /**
     * Constructor de ManejoImpresion
     */
    public RptEstadoCuentaCliente() {

        try {

            ruta_estado_cuenta = getClass().getResource("/reporte/cxc/RptCuentaPorCobrarCliente.jasper");
            ruta_estado_de_cuenta_al_corte = getClass().getResource("/reporte/cxc/jasper/RptEstadoDeCuentaCliente.jasper");

            reporte_estado_cuenta = (JasperReport) JRLoader.loadObject(ruta_estado_cuenta);
            reporte_estado_de_cuenta_al_corte = (JasperReport) JRLoader.loadObject(ruta_estado_de_cuenta_al_corte);

            ruta_img = getClass().getResource("/imagen/img_logo.png");
//                ruta_img = getClass().getResource("/imagen/logo_logios.jpg");

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void estadoCuentaCliente(Date fechaCorte) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("fecha", fechaCorte);

        parametros.put("SUBREPORT_DIR", "reporte/factura/");

        try {

            reporte_final = JasperFillManager.fillReport(reporte_estado_cuenta, parametros, Conexion.getInsatancia().getConnectionDB());

//            JasperViewer.viewReport(reporte_final, false);
            JasperPrint jasperPrint = reporte_final;

            int op;
//            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Facturacion", JOptionPane.YES_NO_OPTION);
//            if (op == 0) {

//            JasperPrintManager.printReport(jasperPrint, false);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C://rpd/Cliente.pdf");
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "PuanlamaFormu.pdf");
//            JasperExportManager.exportReportToPdf(jasperPrint);

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void estadoDeCuentaCliente(Integer cliente, Date fechaCorte, String sqlPendienteCero) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        Double balancePendiente = ManejoFactura.getInstancia().getMontoDeuda(cliente);
        parametros.put("paramCliente", cliente);
        parametros.put("fecha_corte", fechaCorte);
        parametros.put("sqlParamPendienteCero", sqlPendienteCero);
        parametros.put("totalDeuda", balancePendiente);
//        parametros.put("unidad_negocio", 2);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_estado_de_cuenta_al_corte, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void estadoDeCuentaCliente(Integer cliente, String paramFecha, String paramFechaFactura,
            String sqlPendienteCero, String paramDescripcionBusqueda, boolean entreFEcha) {

        Double balancePendiente = 0.00;

        if (entreFEcha) {
//            balancePendiente = ManejoFactura.getInstancia().getMontoDeudaEntreFecha(cliente);
        } else {
            balancePendiente = ManejoFactura.getInstancia().getMontoDeuda(cliente);
        }

        JasperPrint reporte_final;

        //
        Map parametros = new HashMap();

        parametros.put("paramCliente", cliente);
        parametros.put("paramFecha", paramFecha);
        parametros.put("paramFechaFactura", paramFechaFactura);
        parametros.put("sqlParamPendienteCero", sqlPendienteCero);
        parametros.put("paramDescripcionBusqueda", paramDescripcionBusqueda);
        parametros.put("totalDeuda", balancePendiente);
        parametros.put("unidad_negocio", 2);
//        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_estado_de_cuenta_al_corte, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void estadoDeCuentaCliente(Integer cliente, String paramFecha, String paramFechaFactura,
            String sqlPendienteCero, String paramDescripcionBusqueda, boolean entreFEcha, Date fi, Date ff) {

        Double balancePendiente = 0.00;

        if (entreFEcha) {
            balancePendiente = ManejoFactura.getInstancia().getMontoDeudaEntreFecha(cliente, fi, ff);
        } else {
            balancePendiente = ManejoFactura.getInstancia().getMontoDeuda(cliente);
        }

        JasperPrint reporte_final;

        //
        Map parametros = new HashMap();

        parametros.put("paramCliente", cliente);
        parametros.put("paramFecha", paramFecha);
        parametros.put("paramFechaFactura", paramFechaFactura);
        parametros.put("sqlParamPendienteCero", sqlPendienteCero);
        parametros.put("paramDescripcionBusqueda", paramDescripcionBusqueda);
        parametros.put("totalDeuda", balancePendiente);
//        parametros.put("unidad_negocio", 2);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_estado_de_cuenta_al_corte, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

        getInstancia().estadoDeCuentaCliente(850, new Date("2024/01/20"), " where pendiente>=0 ");
    }
}
