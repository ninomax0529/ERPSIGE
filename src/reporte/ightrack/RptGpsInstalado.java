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

/**
 *
 * @author maximiliano
 */
public class RptGpsInstalado {

    private URL ruta_gps_entre_fecha;
    private URL ruta_imei;
    private URL ruta_imei_gps_instalado;
    private URL ruta_gps_vendido;
    private URL sub_ruta_gps_vendido;
    private URL ruta_img;

    public static RptGpsInstalado manager;

    public static RptGpsInstalado getInstancia() {

        if (manager == null) {
            manager = new RptGpsInstalado();
            return manager;
        }
        return manager;
    }

    private JasperReport reporte_gps_entre_fecha;
    private JasperReport reporte_imei;
    private JasperReport reporte_imei_gps_instalado;
    private JasperReport reporte_gps_vendido;
    private JasperReport sub_reporte_gps_vendido;

//    private JasperReport SUB_RPT_DETALLE_ORDEN;
    /**
     * Constructor de ManejoImpresion
     */
    public RptGpsInstalado() {

        try {

            ruta_gps_entre_fecha = getClass().getResource("/reporte/ightrack/RptGpsInstaladoEntreFecha.jasper");
            ruta_imei_gps_instalado = getClass().getResource("/reporte/ightrack/RptGpsInstalado.jasper");
            ruta_imei = getClass().getResource("/reporte/ightrack/RptImeiGps.jasper");
            ruta_gps_vendido = getClass().getResource("/reporte/ightrack/RptGpsVendidoEntreFecha.jasper");
            sub_ruta_gps_vendido = getClass().getResource("/reporte/ightrack/subRptResumenGpsVendido.jasper");
            reporte_gps_entre_fecha = (JasperReport) JRLoader.loadObject(ruta_gps_entre_fecha);
            reporte_imei_gps_instalado = (JasperReport) JRLoader.loadObject(ruta_imei_gps_instalado);
            reporte_imei = (JasperReport) JRLoader.loadObject(ruta_imei);
            reporte_gps_vendido = (JasperReport) JRLoader.loadObject(ruta_gps_vendido);
            sub_reporte_gps_vendido = (JasperReport) JRLoader.loadObject(sub_ruta_gps_vendido);

            ruta_img = getClass().getResource("/imagen/logo_igh_track.png");

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(Date fi, Date ff) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("url", ruta_img.toString());
        parametros.put("fecha_desde", fi);
        parametros.put("fecha_hasta", ff);

//        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        try {

            reporte_final = JasperFillManager.fillReport(reporte_gps_entre_fecha, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(String sqlParam, Date fi, Date ff, String estado) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();

        parametros.put("url", ruta_img.toString());
        parametros.put("sqlParam", sqlParam);
        parametros.put("fecha_desde", fi);
        parametros.put("fecha_hasta", ff);
        parametros.put("estado", estado);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_imei_gps_instalado, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(String sqlParam, Date fi, Date ff, String estado, String ubicacion) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();

        parametros.put("url", ruta_img.toString());
        parametros.put("sqlParam", sqlParam);
        parametros.put("fecha_desde", fi);
        parametros.put("fecha_hasta", ff);
        parametros.put("estado", estado);
        parametros.put("ubicacion", ubicacion);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_imei_gps_instalado, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimirInstalado() {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";
        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_imei_gps_instalado, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(Date fi, Date ff, String sqlParam) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("url", ruta_img.toString());
        parametros.put("fecha_desde", fi);
        parametros.put("fecha_hasta", ff);

//        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        try {

            reporte_final = JasperFillManager.fillReport(reporte_gps_entre_fecha, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void gpsVendido(Date fi, Date ff) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("fecha_desde", fi);
        parametros.put("fecha_hasta", ff);
        parametros.put("SUB_RPT_RESUMEN", sub_reporte_gps_vendido);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_gps_vendido, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

        getInstancia().gpsVendido(new Date("2022/01/01"), new Date("2022/08/30"));
//        getInstancia().imprimir(" codigo>0  and  disponible=true  ");
    }
}
