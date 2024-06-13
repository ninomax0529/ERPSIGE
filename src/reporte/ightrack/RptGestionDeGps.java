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
public class RptGestionDeGps {

    private URL ruta_maestra;
    private URL ruta_img;

    public static RptGestionDeGps manager;

    public static RptGestionDeGps getInstancia() {

        if (manager == null) {
            manager = new RptGestionDeGps();
            return manager;
        }
        return manager;
    }

    private JasperReport reporte_maestro;
    private JasperReport reporte_imei;
    private JasperReport reporte_imei_gps_instalado;

//    private JasperReport SUB_RPT_DETALLE_ORDEN;
    /**
     * Constructor de ManejoImpresion
     */
    public RptGestionDeGps() {

        try {

            ruta_maestra = getClass().getResource("/reporte/ightrack/RptInventarioDeGps.jasper");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestra);
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

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(String sqlParam) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";
        parametros.put("url", ruta_img.toString());
        parametros.put("sqlParam", sqlParam);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_imei, parametros, Conexion.getInsatancia().getConnectionDB());

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

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void inventarioGps() {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

        getInstancia().inventarioGps();

    }
}
