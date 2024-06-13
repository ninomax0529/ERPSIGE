/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.ightrack;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utiles.Conexion;

/**
 *
 * @author MAXIMILIANO
 */
public class RptEstadoContrato {

    private URL ruta_maestro;
    private URL ruta_maestro_resumen;
    private URL ruta_servicio;
    private URL ruta_maestro_comision;
    private URL ruta_img;
    private URL urlsub;
    public static RptEstadoContrato manager;
    private JasperReport reporte_maestro;
    private JasperReport reporte_maestro_resumen;
    private JasperReport reporte_servicio;
    private JasperReport reporte_maestro_comision;

    public static RptEstadoContrato getInstancia() {
        if (manager == null) {
            manager = new RptEstadoContrato();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptEstadoContrato() {

        try {

            ruta_maestro = getClass().getResource("/reporte/ightrack/RptResumenContrato.jasper");
            ruta_servicio = getClass().getResource("/reporte/ightrack/RptServicioMenAnual.jasper");
            ruta_maestro_comision = getClass().getResource("/reporte/comisiones/RptComisionPorInstalacion.jasper");
            ruta_maestro_resumen = getClass().getResource("/reporte/ightrack/RptResumenContratoPorCliente.jasper");

            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);
            reporte_servicio = (JasperReport) JRLoader.loadObject(ruta_servicio);
            reporte_maestro_comision = (JasperReport) JRLoader.loadObject(ruta_maestro_comision);
            reporte_maestro_resumen = (JasperReport) JRLoader.loadObject(ruta_maestro_resumen);

            ruta_img = getClass().getResource("/imagen/magla_img.jpg");

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void imprimir(String sql) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
//       }   
        parametros.put("url", ruta_img.toString());
        parametros.put("sql", sql);
//        parametros.put("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

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

    public void imprimirResumen(String sql) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
//       }   
        parametros.put("url", ruta_img.toString());
        parametros.put("sql", sql);
//        parametros.put("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro_resumen, parametros, Conexion.getInsatancia().getConnectionDB());

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

    public void servicioCliente() {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        try {

            reporte_final = JasperFillManager.fillReport(reporte_servicio, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void comisionPorServicio(Date fi, Date ff) {

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fi);
        Date fechaIn = null, fechaFin = null;
        try {
            fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);

            String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(ff);
            fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        } catch (ParseException ex) {
            Logger.getLogger(RptEstadoContrato.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map parametros = new HashMap();

        parametros.put("fecha_desde", fechaIn);

        parametros.put("fecha_hasta", fechaFin);

        ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
//       }   
        parametros.put("url", ruta_img.toString());
//        parametros.put("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro_comision, parametros, Conexion.getInsatancia().getConnectionDB());

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

        getInstancia().imprimirResumen("");
    }
}
