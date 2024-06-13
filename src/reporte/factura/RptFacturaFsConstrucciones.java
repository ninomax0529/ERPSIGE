/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.factura;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import manejo.ManejoConfiguracion;
import modelo.Factura;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author maximiliano
 */
public class RptFacturaFsConstrucciones {

    public static RptFacturaFsConstrucciones manager;

    //**************Reporte*********************** 
    private JasperReport SUB_RPT_DETALLE_FACTURA;
     private JasperReport reporte_maestro;

    //Cierre declaracion reporte 
//*********************Ruta************************
    private URL RUTA_SUB_RPT_DETALLE_FACTURA;
    private URL RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO;
    private URL ruta_maestro_original;
    private URL ruta_img;
    private URL ruta_img1;

//Cierre declaracion ruta 
    public static RptFacturaFsConstrucciones getInstancia() {

        if (manager == null) {

            manager = new RptFacturaFsConstrucciones();
            return manager;
        }
        return manager;
    }

//    private JasperReport SUB_RPT_DETALLE_ORDEN;
    /**
     * Constructor de ManejoImpresion
     */
    public RptFacturaFsConstrucciones() {

        try {

            ruta_maestro_original = getClass().getResource("/reporte/factura/RptFacturaClienteFSConstrucciones.jasper");
            RUTA_SUB_RPT_DETALLE_FACTURA = getClass().getResource("/reporte/factura/subRptDetalleFacturaFsConstrucciones.jasper");

            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro_original);
            SUB_RPT_DETALLE_FACTURA = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURA);

            ruta_img = getClass().getResource("/imagen/img_nom_fsc.png");
            ruta_img1 = getClass().getResource("/imagen/img_nom_fsc_1.png");

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(int factura, int formato) {

        Boolean impresionDirecta = ManejoConfiguracion.getInstancia().getConfiguracion().getImpresionDirecta();
        JasperPrint reporte_final = null;
        //
        Map parametros = new HashMap();

        parametros.put("factura", factura);
        parametros.put("url", ruta_img.toString());

        try {

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
        
            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            if (impresionDirecta) {

                JasperPrintManager.printReport(jasperPrint, false);

            } else {

                JasperViewer.viewReport(jasperPrint, false);
            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void verFactura(int factura, int formato, String estadoFactura) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("factura", factura);
        parametros.put("estado_factura", estadoFactura);

        parametros.put("url", ruta_img.toString());

        try {

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
        
            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            int op;
//            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Facturacion", JOptionPane.YES_NO_OPTION);
//            if (op == 0) {

//            JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void verFactura(int factura) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        try {

            parametros.put("factura", factura);
            parametros.put("url", ruta_img.toString());
            parametros.put("url1", ruta_img1.toString());
//            parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
            parametros.put("unidad_negocio", 5);

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
         
            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            int op;
//            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Facturacion", JOptionPane.YES_NO_OPTION);
//            if (op == 0) {

//            JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void exportarAPDF(Factura factura) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("factura", factura.getCodigo());

        parametros.put("url", ruta_img.toString());

        try {

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
         

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperExportManager.exportReportToPdfFile(reporte_final, "C://rpd/FacturaCliente_" + factura.getNumero() + ".pdf");

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public String exportarFacturaAPDF(Factura factura) {

        JasperPrint reporte_final;
        String mes = factura.getFecha().toString();

        System.out.println("factura.getFecha() " + factura.getFecha().toString());
        File carpeta = null;

        try {

//            mes =new SimpleDateFormat("yyyy-MM-dd").format(factura.getFecha().toString());
            System.out.println(" mes " + mes);
            carpeta = new File("C://facturapdf/" + mes);
            carpeta.mkdir();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String ruta = carpeta + "/FacturaCliente_" + factura.getNumero() + ".pdf";

//        String ruta = "C://factura pdf/FacturaCliente_" + factura.getNumero() + ".pdf";
        Map parametros = new HashMap();

        parametros.put("factura", factura.getCodigo());

        parametros.put("url", ruta_img.toString());

        try {

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
         
            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperExportManager.exportReportToPdfFile(reporte_final, ruta);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

        return ruta;
    }

    public static void main(String[] args) {

        getInstancia().verFactura(1069);
    }
}
