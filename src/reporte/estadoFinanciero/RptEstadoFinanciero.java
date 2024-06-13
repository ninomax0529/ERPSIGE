/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.estadoFinanciero;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import manejo.HibernateUtil;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author MAXIMILIANO
 */
public class RptEstadoFinanciero {

    private URL ruta_maestro;
    private URL ruta_estado_situacion;
    private URL ruta_estado_resultado;
    private URL RUTA_SUB_RPT_DETALLE_ACTIVO;
    private URL RUTA_SUB_RPT_DETALLE_PASIVO;
    private URL RUTA_SUB_RPT_CAPITAL;
    private URL RUTA_SUB_RPT_PASIVO_CAPITAL;
    
    private URL RUTA_SUB_RPT_INGRESOS;
    private URL RUTA_SUB_RPT_COSTOS;
    private URL RUTA_SUB_RPT_GASTOS;

    private URL ruta_img;
    private URL urlsub;
    public static RptEstadoFinanciero manager;
    private JasperReport reporte_maestro;
    private JasperReport reporte_estado_situacion;
    private JasperReport reporte_estado_resultado;

    private JasperReport SUB_RPT_DETALLE_ACTIVO;
    private JasperReport SUB_RPT_DETALLE_PASIVO;
    private JasperReport SUB_RPT_CAPITAL;
    private JasperReport SUB_RPT_PASIVO_CAPITAL;
    private JasperReport SUB_RPT_INGRESOS;
    private JasperReport SUB_RPT_COSTOS;
    private JasperReport SUB_RPT_GASTOS;

    public static RptEstadoFinanciero getInstancia() {
        if (manager == null) {
            manager = new RptEstadoFinanciero();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptEstadoFinanciero() {

        try {

            ruta_maestro = getClass().getResource("RptBalanceGeneral.jasper");
            ruta_estado_situacion = getClass().getResource("RptEstadoDesituacion.jasper");
            ruta_estado_resultado = getClass().getResource("RptEstadoDeResultado_v1.jasper");
            RUTA_SUB_RPT_INGRESOS = getClass().getResource("RptEstadoDeResultado_v1.jasper");

            RUTA_SUB_RPT_DETALLE_ACTIVO = getClass().getResource("subrptActivos.jasper");
            RUTA_SUB_RPT_DETALLE_PASIVO = getClass().getResource("subrptPasivos.jasper");

            RUTA_SUB_RPT_CAPITAL = getClass().getResource("subrptCapital.jasper");
            RUTA_SUB_RPT_PASIVO_CAPITAL = getClass().getResource("subrptPasivoMasCapital.jasper");

            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);
            reporte_estado_situacion = (JasperReport) JRLoader.loadObject(ruta_estado_situacion);
            reporte_estado_resultado = (JasperReport) JRLoader.loadObject(ruta_estado_resultado);
            SUB_RPT_INGRESOS = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_INGRESOS);

            SUB_RPT_DETALLE_ACTIVO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_ACTIVO);
            SUB_RPT_DETALLE_PASIVO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_PASIVO);

            SUB_RPT_CAPITAL = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_CAPITAL);
            SUB_RPT_PASIVO_CAPITAL = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_PASIVO_CAPITAL);

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void imprimirLibroDiario(String query) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("query", query);
//      
//        parametros.put("url", ruta_img.toString());

        //aqui coloca la ruta donde se encuentra el archivo del o los subreporte  
//        parametros.put("SUBREPORT_DIR", getClass().getResource("/Reportes/Mina/").toString());
        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, HibernateUtil.getSession().close());

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

    public void imprimirBalanceGeneral(Date fechaIni, Date fechaFin) throws ParseException {

        JasperPrint reporte_final;
//        Date fechaInicio = ClaseUtil.getFechaPrimerDiasDelAno(fecha);

//        System.out.println("fecha "+new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio)+" "+fecha);
//        String fechaInicioString = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio);
//        Date fechaIni = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicioString);
//
//        String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(fecha);
//        Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);
        Map parametros = new HashMap();

        parametros.put("fecha_desde", fechaIni);
        parametros.put("fecha_hasta", fechaFin);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        parametros.put("SUB_RPT_ACTIVO", SUB_RPT_DETALLE_ACTIVO);
        parametros.put("SUB_RPT_PASIVO", SUB_RPT_DETALLE_PASIVO);

        parametros.put("SUB_RPT_CAPITAL", SUB_RPT_CAPITAL);
        parametros.put("SUB_RPT_PASIVO_CAPITAL", SUB_RPT_PASIVO_CAPITAL);

//      
//        parametros.put("url", ruta_img.toString());
        //aqui coloca la ruta donde se encuentra el archivo del o los subreporte  
//        parametros.put("SUBREPORT_DIR", getClass().getResource("/Reportes/Mina/").toString());
        try {

            reporte_final = JasperFillManager
                    .fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

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

    public void balanceGeneral(Date fechaFin, int nivel) throws ParseException {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("fecha", fechaFin);
        parametros.put("nivel", nivel);
//        parametros.put("unidad_negocio", 5);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager
                    .fillReport(reporte_estado_situacion, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void imprimirEstadodeResultado(Date fechaInicio, Date fechaFinal) throws ParseException {

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio);
        Date fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);

        String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(fechaFinal);
        Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        Map parametros = new HashMap();

        parametros.put("fecha_desde", fechaIn);

        parametros.put("fecha_hasta", fechaFin);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

////         parametros.put("pago", prestamo);
//      
//        parametros.put("url", ruta_img.toString());
        //aqui coloca la ruta donde se encuentra el archivo del o los subreporte  
//        parametros.put("SUBREPORT_DIR", getClass().getResource("/Reportes/Mina/").toString());
        try {

            reporte_final = JasperFillManager
                    .fillReport(reporte_estado_resultado, parametros, Conexion.getInsatancia().getConnectionDB());

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

    public void estadodeResultado(Date fechaInicio, Date fechaFinal) throws ParseException {

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio);
        Date fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);

        String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(fechaFinal);
        Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        Map parametros = new HashMap();

        parametros.put("fecha_desde", fechaIn);
        parametros.put("fecha_hasta", fechaFin);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        /* SUB REPORTES */
        parametros.put("SUB_RPT_INGRESOS", fechaFin);
        parametros.put("SUB_RPT_COSTOS", fechaFin);
        parametros.put("SUB_RPT_GASTOS", fechaFin);

////         parametros.put("pago", prestamo);
//      
//        parametros.put("url", ruta_img.toString());
        //aqui coloca la ruta donde se encuentra el archivo del o los subreporte  
//        parametros.put("SUBREPORT_DIR", getClass().getResource("/Reportes/Mina/").toString());
        try {

            reporte_final = JasperFillManager
                    .fillReport(reporte_estado_resultado, parametros, Conexion.getInsatancia().getConnectionDB());

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

    public static void main(String[] args) throws ParseException {

//        getInstancia().imprimirEstadodeResultado(new Date("2020/12/22"), new Date("2020/12/23"));
        getInstancia().balanceGeneral(new Date("2023/12/22"), 3);
    }
}
