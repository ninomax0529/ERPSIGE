/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.factura;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import utiles.Conexion;

/**
 *
 * @author maximiliano
 */
public class Rpt_visualizar_factura {

    private URL ruta_maestro;
    private URL ruta_img;
    private URL urlsub;
    public static Rpt_visualizar_factura manager;

    public static Rpt_visualizar_factura getInstancia() {
        if (manager == null) {
            manager = new Rpt_visualizar_factura();
            return manager;
        }
        return manager;
    }
    private JasperReport reporte_maestro;

    /**
     * Constructor de ManejoImpresion
     */
    public Rpt_visualizar_factura() {
        try {

            ruta_maestro = getClass().getResource("/Reportes/factura/FacturaContinua_nueva.jasper");
            urlsub = getClass().getResource("/Reportes/FacturaContinua_header.jasper");

//            urlsub = getClass().getResource("/Reportes/factura/FacturaContinua_header.jasper");
//            urlsub=getClass().getResource("FacturaContinua_header.jasper");
//            ruta_img = getClass().getResource("icons/logo_transparente.gif");
            ruta_img = getClass().getResource("/imagen/elogio1.png");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(int cod) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("factura", cod);
        parametros.put("SUBREPORT_DIR", "reporte/factura/");
//        parametros.put("SUBREPORT_DIR",urlsub.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrintManager.printReport(reporte_final, false);
//            JasperViewer.viewReport(reporte_final, false);

            JasperPrint jasperPrint = reporte_final;

            int op;
//            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Facturacion", JOptionPane.YES_NO_OPTION);
//            if (op == 0) {

//                JasperPrintManager.printReport(jasperPrint,false);
//            }
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        getInstancia().imprimir(12);
    }
}
