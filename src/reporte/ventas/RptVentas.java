/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.ventas;

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
public class RptVentas {

    private URL ruta_resumen_cxc_al_corte;
    private URL ruta_ventas_por_dia;
    private URL ruta_resumen_cxc;
    private URL ruta_img;
    private JasperReport reporte_resumen_cxc_al_corte;
    private JasperReport reporte_venta_por_dia;
    private JasperReport reporte_resumen_cxc;

    public static RptVentas manager;

    public static RptVentas getInstancia() {

        if (manager == null) {
            manager = new RptVentas();
            return manager;
        }
        return manager;
    }

//    private JasperReport SUB_RPT_DETALLE_ORDEN;
    /**
     * Constructor de ManejoImpresion
     */
    public RptVentas() {

        try {

            ruta_resumen_cxc_al_corte = getClass().getResource("/reporte/ightrack/cxc/RptResumenCxcAlCorte.jasper");
            ruta_ventas_por_dia = getClass().getResource("/reporte/ventas/jasper/RptVentasPorDia.jasper");
            reporte_resumen_cxc_al_corte = (JasperReport) JRLoader.loadObject(ruta_resumen_cxc_al_corte);
            reporte_venta_por_dia = (JasperReport) JRLoader.loadObject(ruta_ventas_por_dia);

            ruta_resumen_cxc = getClass().getResource("/reporte/ightrack/cxc/RptResumenCxc.jasper");
            reporte_resumen_cxc = (JasperReport) JRLoader.loadObject(ruta_resumen_cxc);

            ruta_img = getClass().getResource("/imagen/logo_igh_track.png");

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void resumenCxc(Date fechaDesde, Date fechaHasta) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();

        parametros.put("url", ruta_img.toString());
        parametros.put("fecha_desde", fechaDesde);
        parametros.put("fecha_hasta", fechaHasta);
//        parametros.put("unidad_negocio", 2);
//        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_resumen_cxc, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void ventasPorDia(Date fechaDesde, Date fechaHasta, String sqlParam, String tipoVenta) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();

        parametros.put("fechaIni", fechaDesde);
        parametros.put("fechaFin", fechaHasta);
        parametros.put("sqlParam", sqlParam);
        parametros.put("tipoVenta", tipoVenta);
        parametros.put("unidad_negocio", 2);
//        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        System.out.println("sqlParam : " + sqlParam);
        try {

            reporte_final = JasperFillManager.fillReport(reporte_venta_por_dia, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void resumenCxcalCorte(Date fechaCorte) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("url", ruta_img.toString());
        parametros.put("fecha_corte", fechaCorte);
//        parametros.put("unidad_negocio", 2);
//        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_resumen_cxc_al_corte, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void resumenCxcalCorte(Date fechaCorte, String sqlParam) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("url", ruta_img.toString());
        parametros.put("fecha_corte", fechaCorte);
        parametros.put("sqlParam", sqlParam);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_resumen_cxc_al_corte, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void resumenCxc(Date fechaCorte, String sqlParam, String sqlCliente) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("url", ruta_img.toString());
        parametros.put("fecha_corte", fechaCorte);
        parametros.put("sqlParam", sqlParam);
        parametros.put("sqlCliente", sqlCliente);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_resumen_cxc_al_corte, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void resumenCxcContratoAlCorte(Date fechaCorte) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("url", ruta_img.toString());
        parametros.put("fecha_corte", fechaCorte);
//        parametros.put("unidad_negocio", 2);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_venta_por_dia, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

//        getInstancia().resumenCxcContratoAlCorte(new Date("2022/10/30"));
        getInstancia().ventasPorDia(new Date("2022/12/01"), new Date("2022/12/31"), " and f.unidad_de_negocio=2 ","");
    }
}
