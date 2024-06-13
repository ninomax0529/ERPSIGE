/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.compra;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utiles.Conexion;

/**
 *
 * @author MAXIMILIANO
 */
public class RptOrdenCompraPinturaTriplea {

    private URL ruta_maestro;

    private URL ruta_maestro_libro_mayor;
    private URL ruta_maestro_libro_mayor_por_cuenta;
    private URL ruta_img;
    private URL urlsub;
    private URL ruta_balnaza_comprobacion;
    private URL ruta_img_proyecto;
    private URL RUTA_SUB_RPT_DETALLE_ORDEN;
    public static RptOrdenCompraPinturaTriplea manager;
    private JasperReport reporte_maestro;
    private JasperReport reporte_maestro_balanza_de_comprobacion;
    private JasperReport reporte_maestro_libro_mayor;
    private JasperReport reporte_maestro_libro_mayor_por_cuenta;
    private JasperReport SUB_RPT_DETALLE_ORDEN;

    public static RptOrdenCompraPinturaTriplea getInstancia() {
        if (manager == null) {
            manager = new RptOrdenCompraPinturaTriplea();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptOrdenCompraPinturaTriplea() {

        try {

            ruta_maestro = getClass().getResource("RptOrdenCompra.jasper");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);
            RUTA_SUB_RPT_DETALLE_ORDEN = getClass().getResource("subRptOrdenDeCompra.jasper");
            SUB_RPT_DETALLE_ORDEN = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_ORDEN);

            ruta_img = getClass().getResource("/imagen/logo_pintura_ultimo.png");

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void imprimir(Integer orden) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("numero_orden", orden);
        parametros.put("url", ruta_img.toString());
        parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_ORDEN);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        RptOrdenCompraPinturaTriplea.getInstancia().imprimir(1);
    }
}
