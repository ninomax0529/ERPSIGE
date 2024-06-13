/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.factura;

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
public class RptNcfFacturado {

    private URL ruta_maestro;
    private URL ruta_img;
    private URL urlsub;
    public static RptNcfFacturado manager;

    public static RptNcfFacturado getInstancia() {
        if (manager == null) {
            manager = new RptNcfFacturado();
            return manager;
        }
        return manager;
    }
    private JasperReport reporte_maestro;

    /**
     * Constructor de ManejoImpresion
     */
    public RptNcfFacturado() {
        try {

            ruta_maestro = getClass().getResource("/reporte/factura/RptNcfEntreFecha.jasper");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);
            ruta_img = getClass().getResource("/imagen/logo_fsc.png");

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(Date fi, Date ff) {

        JasperPrint reporte_final;
        
//        System.out.println("ruta_img.toString() "+ruta_img.toString());
        //
        Map parametros = new HashMap();

//        parametros.put("url", ruta_img.toString());
        parametros.put("fecha_desde", fi);
        parametros.put("fecha_hasta", ff);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

        getInstancia().imprimir(new Date("2022/07/01"), new Date("2022/07/28"));
    }
}
