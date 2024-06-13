/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.cotizacion;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import manejo.ManejoConfiguracion;
import net.sf.jasperreports.engine.JRException;
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
public class RpCotizacionDeVentaIghTrack {

    public static RpCotizacionDeVentaIghTrack manager;

    //**************Reporte*********************** 
    private JasperReport SUB_RPT_DETALLE_FACTURA;
    private JasperReport reporte_maestro;
    private JasperReport SUB_SUB_RPT_DETALLE_VEHICULO;

    //Cierre declaracion reporte 
//*********************Ruta************************
    private URL RUTA_SUB_RPT_DETALLE_FACTURA;
    private URL RUTA_SUB_RPT_DETALLE_VEHICULO;
    private URL ruta_maestro_original;
    private URL ruta_img;

//Cierre declaracion ruta 
    public static RpCotizacionDeVentaIghTrack getInstancia() {

        if (manager == null) {

            manager = new RpCotizacionDeVentaIghTrack();
            return manager;
        }
        return manager;
    }

//    private JasperReport SUB_RPT_DETALLE_ORDEN;
    /**
     * Constructor de ManejoImpresion
     */
    public RpCotizacionDeVentaIghTrack() {

        try {

            ruta_maestro_original = getClass().getResource("/reporte/cotizacion/RptCotizacionClientePinturaTriplea.jasper");
            RUTA_SUB_RPT_DETALLE_FACTURA = getClass().getResource("/reporte/cotizacion/subRptDetalleCotizacionghTrack.jasper");
            RUTA_SUB_RPT_DETALLE_VEHICULO = getClass().getResource("/reporte/cotizacion/subRptDetCotizacionVehiculoIghTrack.jasper");

            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro_original);
            SUB_RPT_DETALLE_FACTURA = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURA);
            SUB_SUB_RPT_DETALLE_VEHICULO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_VEHICULO);

            ruta_img = getClass().getResource("/imagen/logo_igh_track.png");

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(int cotizacion, int formato) {

        Boolean impresionDirecta = ManejoConfiguracion.getInstancia().getConfiguracion().getImpresionDirecta();
        JasperPrint reporte_final = null;
        //
        Map parametros = new HashMap();

        parametros.put("factura", cotizacion);

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

    public void verCotizacion(int cotizacion, int formato, String estadoFactura) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("factura", cotizacion);
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

    public void verCotizacion(int cotizacion) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();

        try {

            parametros.put("cotizacion", cotizacion);

            parametros.put("unidad_negocio", 2);
            parametros.put("empresa", 3);

//            parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
//            parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());
            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
            parametros.put("SUB_RPT_DETALLE_VEHICULO", SUB_SUB_RPT_DETALLE_VEHICULO);

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        getInstancia().verCotizacion(7);
    }
}
