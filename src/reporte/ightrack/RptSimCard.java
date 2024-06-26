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
public class RptSimCard {

    private URL ruta_fact_pendiente;
    private URL ruta_img;

    public static RptSimCard manager;

    public static RptSimCard getInstancia() {

        if (manager == null) {
            manager = new RptSimCard();
            return manager;
        }
        return manager;
    }

    private JasperReport reporte_fact_pendiente;

//    private JasperReport SUB_RPT_DETALLE_ORDEN;
    /**
     * Constructor de ManejoImpresion
     */
    public RptSimCard() {

        try {

            ruta_fact_pendiente = getClass().getResource("/reporte/ightrack/RptSimDisponible.jasper");

            reporte_fact_pendiente = (JasperReport) JRLoader.loadObject(ruta_fact_pendiente);
            ruta_img = getClass().getResource("/imagen/logo_igh_track.png");

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(String parametro) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("url", ruta_img.toString());
        parametros.put("sqlParam", parametro);
//        parametros.put("fecha_desde", fi);
//        parametros.put("fecha_hasta", ff);

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

    public static void main(String[] args) {

        getInstancia().imprimir(" codigo>0 ");
    }
}
