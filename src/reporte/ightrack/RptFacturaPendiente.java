/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.ightrack;

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
import utiles.VariablesGlobales;

/**
 *
 * @author maximiliano
 */
public class RptFacturaPendiente {

    private URL ruta_fact_pendiente;
    private URL ruta_fact_pendiente_al_corte;
    private URL ruta_estado_de_cuenta_al_corte;
    private JasperReport SUB_RPT_GESTION_COBRO;

    private URL RUTA_SUB_RPT_GESTION_COBRO;
    private URL ruta_img;
    private JasperReport reporte_fact_pendiente;
    private JasperReport reporte_fact_pendiente_al_corte;
    private JasperReport reporte_estado_de_cuenta_al_corte;

    public static RptFacturaPendiente manager;

    public static RptFacturaPendiente getInstancia() {

        if (manager == null) {
            manager = new RptFacturaPendiente();
            return manager;
        }
        return manager;
    }

//    private JasperReport SUB_RPT_DETALLE_ORDEN;
    /**
     * Constructor de ManejoImpresion
     */
    public RptFacturaPendiente() {

        try {

            ruta_fact_pendiente = getClass().getResource("/reporte/ightrack/RptFacturaPendienteAlCorteNota.jasper");
            ruta_estado_de_cuenta_al_corte = getClass().getResource("/reporte/cxc/jasper/RptEstadoDeCuentaCliente.jasper");
//            ruta_fact_pendiente = getClass().getResource("/reporte/ightrack/RptFacturaPendienteNota.jasper");
            reporte_fact_pendiente = (JasperReport) JRLoader.loadObject(ruta_fact_pendiente);

            ruta_fact_pendiente_al_corte = getClass().getResource("/reporte/ightrack/RptFacturaPendienteAlCorteNota.jasper");
            reporte_fact_pendiente_al_corte = (JasperReport) JRLoader.loadObject(ruta_fact_pendiente_al_corte);

            reporte_estado_de_cuenta_al_corte = (JasperReport) JRLoader.loadObject(ruta_estado_de_cuenta_al_corte);

            RUTA_SUB_RPT_GESTION_COBRO = getClass().getResource("subRptGestionCobro.jasper");

            RUTA_SUB_RPT_GESTION_COBRO = getClass().getResource("subRptGestionCobro.jasper");

            SUB_RPT_GESTION_COBRO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_GESTION_COBRO);

            ruta_img = getClass().getResource("/imagen/logo_igh_track.png");

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void facturaPendiente(Date fechaDesde, Date fechaHasta) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("url", ruta_img.toString());
        parametros.put("fecha_desde", fechaDesde);
        parametros.put("fecha_hasta", fechaHasta);
        parametros.put("SUB_RPT_DETALLE", SUB_RPT_GESTION_COBRO);
        parametros.put("unidad_negocio", 2);
//        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_fact_pendiente, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

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
        String titulo = "";

        System.out.println("sqlParam rpt : " + sqlParam);
        parametros.put("url", ruta_img.toString());
        parametros.put("fecha_desde", fechaDesde);
        parametros.put("fecha_hasta", fechaHasta);
        parametros.put("SUB_RPT_DETALLE", SUB_RPT_GESTION_COBRO);
//        parametros.put("unidad_negocio", 2);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        parametros.put("sqlParam", sqlParam);
        parametros.put("sqlCliente", sqlCliente);
        parametros.put("sqlParamPendienteCero", sqlPendienteCero);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_fact_pendiente, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void facturaPendiente(Date fechaDesde, Date fechaHasta, String sqlParam, String sqlCliente, String sqlPendienteCero, String sqlFecha) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        System.out.println("sqlParam rpt : " + sqlParam);
        parametros.put("url", ruta_img.toString());
        parametros.put("fecha_desde", fechaDesde);
        parametros.put("fecha_hasta", fechaHasta);
        parametros.put("SUB_RPT_DETALLE", SUB_RPT_GESTION_COBRO);
//        parametros.put("unidad_negocio", 2);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        parametros.put("sqlParam", sqlParam);
        parametros.put("sqlCliente", sqlCliente);
        parametros.put("sqlFecha", sqlFecha);
        parametros.put("sqlParamPendienteCero", sqlPendienteCero);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_fact_pendiente, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void facturaPendientealCorte(Date fechaHasta) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("url", ruta_img.toString());
        parametros.put("fecha_hasta", fechaHasta);
        parametros.put("SUB_RPT_DETALLE", SUB_RPT_GESTION_COBRO);
        parametros.put("unidad_negocio", 2);
//        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_fact_pendiente_al_corte, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void estadoDeCuentaCliente(Integer cliente, Date fechaCorte) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();

        parametros.put("cliente", 480);
        parametros.put("fecha_corte", fechaCorte);
        parametros.put("unidad_negocio", 2);
//        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_fact_pendiente_al_corte, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

//        getInstancia().facturaPendientealCorte(new Date("2022/08/30"));
        getInstancia().facturaPendiente(new Date("2022/08/01"), new Date("2022/10/31"),
                "   and  f.unidad_de_negocio=2  and  ri.fecha between '2022-12-01'  and '2022-12-31'    ",
                "  and  f.fecha between '2023-01-01'  and '2023-01-19'     and  f.unidad_de_negocio=2 ", "  where  r.pendiente>=0 ");
    }
}
