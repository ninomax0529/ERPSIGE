/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.factura;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import manejo.HibernateUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author MAXIMILIANO
 */
public class RptReciboPago {

    private URL ruta_maestro;
    private URL ruta_img;
    private URL urlsub;
    public static RptReciboPago manager;
    private JasperReport reporte_maestro;

    public static RptReciboPago getInstancia() {
        if (manager == null) {
            manager = new RptReciboPago();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptReciboPago() {

        try {

//            ruta_maestro = getClass().getResource("RptReciboEmpeno.jasper");
            ruta_maestro = getClass().getResource("RptTkReciboDePago.jasper");
//            ruta_img = getClass().getResource("logo_reportes.png");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);
//            ruta_img = getClass().getResource("/imagen/juaquinc01.png");
            ruta_img = getClass().getResource("/imagen/elogio1.png");

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void imprimir(String query) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("query", query);
//      
//        parametros.put("url", ruta_img.toString());

        //aqui coloca la ruta donde se encuentra el archivo del o los subreporte  
//        parametros.put("SUBREPORT_DIR", getClass().getResource("/Reportes/Mina/").toString());
//        try {
//
//            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, HibernateUtil.getSession().connection());
//
//            JasperPrint jasperPrint = reporte_final;
//
//            int op;
//
////            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Produccion Trituradora", JOptionPane.YES_NO_OPTION);
////            if (op == 0) {
////                JasperPrintManager.printReport(jasperPrint, false);
////            }else{
//            JasperViewer.viewReport(reporte_final, false);
////            }
//
//        } catch (JRException ex) {
//            ex.printStackTrace();
//        }
    }

    public void imprimir(Integer prestamo) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("pago", prestamo);
//      
        parametros.put("url", ruta_img.toString());

        //aqui coloca la ruta donde se encuentra el archivo del o los subreporte  
//        parametros.put("SUBREPORT_DIR", getClass().getResource("/Reportes/Mina/").toString());
//        try {
//
//            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, HibernateUtil.getSession().connection());
//
//            JasperPrint jasperPrint = reporte_final;
//
//            int op;
//
////            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Produccion Trituradora", JOptionPane.YES_NO_OPTION);
////            if (op == 0) {
////                JasperPrintManager.printReport(jasperPrint, false);
////            }else{
//            JasperViewer.viewReport(reporte_final, false);
////            }
//
//        } catch (JRException ex) {
//            ex.printStackTrace();
//        }
    }

    public static void main(String[] args) {

        getInstancia().imprimir(20);
    }
}
