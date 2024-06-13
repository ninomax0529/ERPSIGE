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
import utiles.ClaseUtil;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author maximiliano
 */
public class RptFacturaIghTrack {

    public static RptFacturaIghTrack manager;

    //**************Reporte*********************** 
    private JasperReport SUB_RPT_DETALLE_FACTURA;
    private JasperReport SUB_RPT_DETALLE_FACTURA_AGRUPADO;
    private JasperReport SUB_RPT_DETALLE_FACTURA_TEMPORAL;
    private JasperReport SUB_RPT_DETALLE_DATOS_VEHICULO;
    private JasperReport reporte_maestro;
    private JasperReport reporte_maestro_temporal;

    //Cierre declaracion reporte 
//*********************Ruta************************
    private URL RUTA_SUB_RPT_DETALLE_FACTURA;
    private URL RUTA_SUB_RPT_DETALLE_FACTURAAGRUPADO;
    private URL RUTA_SUB_RPT_DETALLE_FACTURA_TEMPORAL;
    private URL RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO;
    private URL ruta_maestro_original;
    private URL ruta_maestro_temporal;
    private URL ruta_img;

//Cierre declaracion ruta 
    public static RptFacturaIghTrack getInstancia() {

        if (manager == null) {

            manager = new RptFacturaIghTrack();
            return manager;
        }
        return manager;
    }

//    private JasperReport SUB_RPT_DETALLE_ORDEN;
    /**
     * Constructor de ManejoImpresion
     */
    public RptFacturaIghTrack() {

        try {

            ruta_maestro_original = getClass().getResource("/reporte/factura/ightrack/RptFacturaClienteIghTrack.jasper");
            ruta_maestro_temporal = getClass().getResource("/reporte/factura/RptFacturaClienteIghTrackTemporal.jasper");
            RUTA_SUB_RPT_DETALLE_FACTURA = getClass().getResource("/reporte/factura/subRptDetalleFacturaIghTrack.jasper");
            RUTA_SUB_RPT_DETALLE_FACTURAAGRUPADO = getClass().getResource("/reporte/factura/subRptDetAgrupoadoFacturaIghTrack.jasper");
            RUTA_SUB_RPT_DETALLE_FACTURA_TEMPORAL = getClass().getResource("/reporte/factura/subRptDetalleFacturaIghTrackTemporal.jasper");
            RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO = getClass().getResource("/reporte/factura/subRptDetalleDatoVehiculoIghTrack.jasper");

            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro_original);
            reporte_maestro_temporal = (JasperReport) JRLoader.loadObject(ruta_maestro_temporal);
            SUB_RPT_DETALLE_FACTURA = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURA);
            SUB_RPT_DETALLE_FACTURA_AGRUPADO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURAAGRUPADO);
            SUB_RPT_DETALLE_FACTURA_TEMPORAL = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURA_TEMPORAL);
            SUB_RPT_DETALLE_DATOS_VEHICULO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO);

            ruta_img = getClass().getResource("/imagen/logo_igh_track.png");

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
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

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

        parametros.put("factura", factura);
        parametros.put("estado_factura", estadoFactura);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

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
        
        Map parametros = new HashMap();
   
        parametros.put("factura", factura);
          parametros.put("unidad_negocio",2);
//        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());


        try {

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);


        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void verFacturaDetAgrupado(int factura) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("factura", factura);
          parametros.put("unidad_negocio",2);
//        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
//        parametros.put("estado_factura", estadoFactura);

//        parametros.put("url", ruta_img.toString());
        try {

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA_AGRUPADO);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

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

    public void verFacturaTemporal(int factura) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("factura", factura);
//        parametros.put("estado_factura", estadoFactura);

        parametros.put("url", ruta_img.toString());

        try {

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA_TEMPORAL);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

            reporte_final = JasperFillManager.fillReport(reporte_maestro_temporal, parametros, Conexion.getInsatancia().getConnectionDB());

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
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

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
            carpeta.mkdirs();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String ruta = carpeta + "/FacturaCliente_" + factura.getNumero() + ".pdf";

//        String ruta = "C://factura pdf/FacturaCliente_" + factura.getNumero() + ".pdf";
        Map parametros = new HashMap();

        parametros.put("factura", factura.getCodigo());
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperExportManager.exportReportToPdfFile(reporte_final, ruta);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

        return ruta;
    }

    public String exportarFacturaAPDF(Factura factura, String rutaStr) {

        JasperPrint reporte_final;
        String ruta = null;
        String mes = factura.getFecha().toString();
        String mesActual = ClaseUtil.getMesAno(factura.getFecha());

        System.out.println("factura.getFecha() " + factura.getFecha().toString());
        File carpeta = null;

        try {

            System.out.println("rutaStr " + rutaStr);
//            rutaStr=rutaStr.replace("\\","///");
//            mes =new SimpleDateFormat("yyyy-MM-dd").format(factura.getFecha().toString());
            System.out.println(" mes " + mes);
//            carpeta = new File("C://facturapdf/" + mes);
            carpeta = new File(rutaStr + "\\" + mesActual + "\\" + mes);
            System.out.println("carpeta " + carpeta);

//            ruta = rutaStr + "\\FacturaCliente_" + factura.getNumero() + ".pdf";
//            carpeta = new File(rutaStr);
            carpeta.mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ruta = carpeta + "\\" + factura.getCliente().getNombre().trim().replace("ñ", "n") + "_" + factura.getNcf() + ".pdf";
        System.out.println("ruta : " + ruta + " ruta str " + rutaStr);
        Map parametros = new HashMap();

        parametros.put("factura", factura.getCodigo());
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperExportManager.exportReportToPdfFile(reporte_final, ruta);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

        return ruta;
    }

    public void imprimirPorLote(int nom, int empleado) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("nomina", nom);
        parametros.put("empleado", empleado);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrintManager.printReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimirPorLote(Factura factura) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("factura", factura.getCodigo());
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrintManager.printReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

         getInstancia().verFactura(1170);
//        getInstancia().verFacturaDetAgrupado(1170);
    }
}
