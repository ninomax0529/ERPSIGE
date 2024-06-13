/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.pedido;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utiles.Conexion;

/**
 *
 * @author maximiliano
 */
public class RptPedido {

    private URL ruta_maestro;
    private URL ruta_pedido;
    private URL ruta_pedido_delivery;
    private URL ruta_img;
    private URL urlsub;
    private JasperReport reporte_maestro;
    private JasperReport reporte_pedido;
    private JasperReport reporte_pedido_delivery;
    public static RptPedido manager;

    public static RptPedido getInstancia() {
        if (manager == null) {
            manager = new RptPedido();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptPedido() {
        
        try {

            ruta_maestro = getClass().getResource("FacturaContinua_header.jasper");
//            urlsub = getClass().getResource("/Reportes/FacturaContinua_header.jasper");
            ruta_pedido = getClass().getResource("PedidodeVenta.jasper");
            ruta_pedido_delivery = getClass().getResource("PedidodeVentaDelivery.jasper");

//            urlsub = getClass().getResource("/Reportes/factura/FacturaContinua_header.jasper");
//            urlsub=getClass().getResource("FacturaContinua_header.jasper");
//            ruta_img = getClass().getResource("icons/logo_transparente.gif");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);
            reporte_pedido = (JasperReport) JRLoader.loadObject(ruta_pedido);
            reporte_pedido_delivery = (JasperReport) JRLoader.loadObject(ruta_pedido_delivery);

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

    public void imprimirPedido(int pedido) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("pedido", pedido);
        parametros.put("SUBREPORT_DIR", reporte_maestro);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_pedido, parametros, Conexion.getInsatancia().getConnectionDB());

//            JasperPrintManager.printReport(reporte_final, false);
//            JasperViewer.viewReport(reporte_final, false);
            JasperPrint jasperPrint = reporte_final;
            JasperViewer.viewReport(jasperPrint, false);

            int op;
//            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Facturacion", JOptionPane.YES_NO_OPTION);
//            if (op == 0) {

//                JasperPrintManager.printReport(jasperPrint,false);
//            }
        } catch (JRException ex) {

            ex.printStackTrace();
        }

    }

    public void imprimirPedidoDelivery(int pedido) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("pedido", pedido);
        parametros.put("SUBREPORT_DIR", reporte_maestro);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_pedido_delivery, parametros, Conexion.getInsatancia().getConnectionDB());

//            JasperPrintManager.printReport(reporte_final, false);
//            JasperViewer.viewReport(reporte_final, false);
            JasperPrint jasperPrint = reporte_final;
            JasperViewer.viewReport(jasperPrint, false);

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
        getInstancia().imprimirPedidoDelivery(3);
    }
}
