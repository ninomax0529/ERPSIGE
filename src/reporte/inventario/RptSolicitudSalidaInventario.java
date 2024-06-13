/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.inventario;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author MAXIMILIANO
 */
public class RptSolicitudSalidaInventario {

    private URL ruta_maestro;

    private URL ruta_img;

    private URL RUTA_SUB_RPT_DETALLE;
    public static RptSolicitudSalidaInventario manager;
    private JasperReport reporte_maestro;

    private JasperReport SUB_RPT_DETALLE;

    public static RptSolicitudSalidaInventario getInstancia() {
        if (manager == null) {
            manager = new RptSolicitudSalidaInventario();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptSolicitudSalidaInventario() {

        try {

            ruta_maestro = getClass().getResource("RptSolicitudSalidaInventario.jasper");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);
            RUTA_SUB_RPT_DETALLE = getClass().getResource("subRptDetalleSolicitudSalidaInventario.jasper");
            SUB_RPT_DETALLE = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE);

            ruta_img = getClass().getResource("/img/naturaleza.png");

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void imprimir(Integer salida) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("salida", salida);
        parametros.put("url", ruta_img.toString());
        parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE);

//        try {
//
//            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, ConexionProyectoDb.getConnectionDB());
//
//            JasperViewer.viewReport(reporte_final, false);
//
//        } catch (JRException ex) {
//            ex.printStackTrace();
//        }
    }

    public static void main(String[] args) {

        RptSolicitudSalidaInventario.getInstancia().imprimir(31);
    }
}
