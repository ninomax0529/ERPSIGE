/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.factura;


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
public class RptFacturaant {

    private URL ruta_maestro;
    private URL ruta_img;
    private URL urlsub;
    public static RptFacturaant manager;
    private JasperReport reporte_maestro;

    public static RptFacturaant getInstancia() {
        if (manager == null) {
            manager = new RptFacturaant();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptFacturaant() {

        try {

//            ruta_maestro = getClass().getResource("RptReciboEmpeno.jasper");
            ruta_maestro = getClass().getResource("FacturaVenta.jasper");
//            ruta_img = getClass().getResource("logo_reportes.png");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);
//            ruta_img = getClass().getResource("/imagen/juaquinc01.png");
            ruta_img = getClass().getResource("/imagen/img_logo.png");

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

    public void imprimir(Integer factura) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("factura", factura);
//      
        parametros.put("url", ruta_img.toString());

        //aqui coloca la ruta donde se encuentra el archivo del o los subreporte  
//        parametros.put("SUBREPORT_DIR", getClass().getResource("/Reportes/Mina/").toString());
        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            int op;

//            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Produccion Trituradora", JOptionPane.YES_NO_OPTION);
//            if (op == 0) {
//                JasperPrintManager.printReport(jasperPrint, false);
//            }else{
            JasperViewer.viewReport(reporte_final, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        getInstancia().imprimir(11);
    }
}
