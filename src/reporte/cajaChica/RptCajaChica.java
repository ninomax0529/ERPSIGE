/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.cajaChica;

import java.net.URL;
import java.text.SimpleDateFormat;
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
public class RptCajaChica {

    private URL ruta_maestro;
    private URL ruta_cuadre_caja;
    private URL ruta_img;
    private URL urlsub;
    private URL ruta_comprabante_egreso;
    private URL ruta_comprabante_apertura_caja;
    public static RptCajaChica manager;

    public static RptCajaChica getInstancia() {
        if (manager == null) {
            manager = new RptCajaChica();
            return manager;
        }
        return manager;
    }
    private JasperReport reporte_maestro;
    private JasperReport reporte_maestro_comprabante_egreso;
    private JasperReport reporte_comprabante_apertura_caja;
    private JasperReport reporte_cuadre_caja;

    /**
     * Constructor de ManejoImpresion
     */
    public RptCajaChica() {

        try {

            ruta_maestro = getClass().getResource("/reporte/cajaChica/RptCuadreCaja.jasper");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);

            ruta_comprabante_egreso = getClass().getResource("/reporte/cajaChica/RptComprobanteDeEgreso.jasper");
            reporte_maestro_comprabante_egreso = (JasperReport) JRLoader.loadObject(ruta_comprabante_egreso);

            ruta_cuadre_caja = getClass().getResource("/reporte/cajaChica/RptCuadreCajaPorFecha.jasper");
            reporte_cuadre_caja = (JasperReport) JRLoader.loadObject(ruta_cuadre_caja);

            ruta_comprabante_apertura_caja = getClass().getResource("/reporte/cajaChica/RptComprobanteAperturaDeCaja.jasper");
            reporte_comprabante_apertura_caja = (JasperReport) JRLoader.loadObject(ruta_comprabante_apertura_caja);

            ruta_img = getClass().getResource("/imagen/img_logo.png");

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimirCuadreCaja(int cuadre) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("SUBREPORT_DIR", "reporte/factura/");

//        parametros.put("url", "icons/logo_transparente.gif");
        parametros.put("numero_cuadre", cuadre);
        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

//            JasperViewer.viewReport(reporte_final, false);
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

    public void imprimirComprobanteEgreso(int comprabante) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("SUBREPORT_DIR", "reporte/factura/");

        parametros.put("num_comprabante", comprabante);
        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro_comprabante_egreso, parametros, Conexion.getInsatancia().getConnectionDB());

//            JasperViewer.viewReport(reporte_final, false);
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
    
      public void imprimirComprobanteAperturaCaja(int comprabante) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("SUBREPORT_DIR", "reporte/factura/");

        parametros.put("num_comprabante", comprabante);
        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_comprabante_apertura_caja, parametros, Conexion.getInsatancia().getConnectionDB());

//            JasperViewer.viewReport(reporte_final, false);
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

    

    public void imprimirCuadreCaja(Date fecha, int turno) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("SUBREPORT_DIR", "reporte/factura/");

        parametros.put("url", ruta_img.toString());
        parametros.put("fecha", fecha);
        parametros.put("turno", turno);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_cuadre_caja, parametros, Conexion.getInsatancia().getConnectionDB());

//            JasperViewer.viewReport(reporte_final, false);
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

    public static void main(String[] args) {

        String numDocumento=(new SimpleDateFormat("ddmmss").format(new Date())).replace("-", " ");
        System.out.println("num "+numDocumento .trim());
//        getInstancia().imprimirComprobanteAperturaCaja(210903);
    }
}
