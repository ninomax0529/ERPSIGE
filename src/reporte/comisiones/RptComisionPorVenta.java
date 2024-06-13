/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.comisiones;

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
import utiles.VariablesGlobales;

/**
 *
 * @author MAXIMILIANO
 */
public class RptComisionPorVenta {

    private URL ruta_maestro;
    private URL SUB_ruta_RESUMEN;
    private URL ruta_maestro_comision;
    private URL ruta_comision_cliente;
    private URL ruta_resumen_comision;
    private URL ruta_img;
    private URL urlsub;
    public static RptComisionPorVenta manager;
    private JasperReport reporte_maestro;
    private JasperReport reporte_maestro_comision;
    private JasperReport reporte_comision_cliente;
    private JasperReport resumen_comision;
    private JasperReport SUB_resumen_comision;

    public static RptComisionPorVenta getInstancia() {
        if (manager == null) {
            manager = new RptComisionPorVenta();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptComisionPorVenta() {

        try {

            ruta_maestro = getClass().getResource("/reporte/comisiones/RptComisionPorVentas_v1.jasper");
            SUB_ruta_RESUMEN = getClass().getResource("/reporte/comisiones/subRptResumenInstalacion.jasper");
            ruta_maestro_comision = getClass().getResource("/reporte/comisiones/RptComisionPorInstalacion.jasper");
            ruta_comision_cliente = getClass().getResource("/reporte/comisiones/RptComisionPorVentaDeCliente.jasper");
            ruta_resumen_comision = getClass().getResource("/reporte/comisiones/RptResumenComisionPorVenta_v1.jasper");

            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);
            reporte_maestro_comision = (JasperReport) JRLoader.loadObject(ruta_maestro_comision);
            reporte_comision_cliente = (JasperReport) JRLoader.loadObject(ruta_comision_cliente);
            resumen_comision = (JasperReport) JRLoader.loadObject(ruta_resumen_comision);
            SUB_resumen_comision = (JasperReport) JRLoader.loadObject(SUB_ruta_RESUMEN);

            ruta_img = getClass().getResource("/imagen/magla_img.jpg");

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void imprimir(Date fi, Date ff, String sqlParam) {

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fi);
        Date fechaIn = null, fechaFin = null;
        try {
            fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);

            String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(ff);
            fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        } catch (ParseException ex) {
            Logger.getLogger(RptComisionPorVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("que param : " + sqlParam);
        Map parametros = new HashMap();

        parametros.put("fecha_desde", fechaIn);
        parametros.put("fecha_hasta", fechaFin);
        parametros.put("sqlParam", sqlParam);

        ruta_img = getClass().getResource("/imagen/logo_igh_track.png");

        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());

        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(Date fi, Date ff, String sqlParam, String codigoVendedor) {

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fi);
        Date fechaIn = null, fechaFin = null;
        try {
            fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);

            String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(ff);
            fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        } catch (ParseException ex) {
            Logger.getLogger(RptComisionPorVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("que param : " + sqlParam);
        Map parametros = new HashMap();

        parametros.put("fecha_desde", fechaIn);
        parametros.put("fecha_hasta", fechaFin);
        parametros.put("sqlParam", sqlParam);
        parametros.put("cod_vendedor", codigoVendedor);

        ruta_img = getClass().getResource("/imagen/logo_igh_track.png");

        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());

        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void comisionPorServicio(Date fi, Date ff, String sqlParam) {

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fi);
        Date fechaIn = null, fechaFin = null;
        try {
            fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);

            String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(ff);
            fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        } catch (ParseException ex) {
            Logger.getLogger(RptComisionPorVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map parametros = new HashMap();

        parametros.put("fecha_desde", fechaIn);
        parametros.put("fecha_hasta", fechaFin);
        parametros.put("sqlParam", sqlParam);
        parametros.put("SUB_RPT_RESUMEN", SUB_resumen_comision);
        parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());

        ruta_img = getClass().getResource("/imagen/logo_igh_track.png");

        parametros.put("url", ruta_img.toString());
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
//        parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());

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

    public void comisionPorVentaCliente(Date fi, Date ff) {

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fi);
        Date fechaIn = null, fechaFin = null;
        try {
            fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);

            String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(ff);
            fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        } catch (ParseException ex) {
            Logger.getLogger(RptComisionPorVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map parametros = new HashMap();

        parametros.put("fecha_desde", fechaIn);
        parametros.put("fecha_hasta", fechaFin);
        parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());

        ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
//       }   
        parametros.put("url", ruta_img.toString());
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_comision_cliente, parametros, Conexion.getInsatancia().getConnectionDB());

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

    public void resumenComisionPorVenta(Date fi, Date ff) {

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fi);
        Date fechaIn = null, fechaFin = null;
        try {
            fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);

            String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(ff);
            fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        } catch (ParseException ex) {
            Logger.getLogger(RptComisionPorVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map parametros = new HashMap();

        parametros.put("fecha_desde", fechaIn);
        parametros.put("fecha_hasta", fechaFin);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());

        ruta_img = getClass().getResource("/imagen/logo_igh_track.png");

        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(resumen_comision, parametros, Conexion.getInsatancia().getConnectionDB());

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

    public void resumenComisionPorVenta(Date fi, Date ff, String sqlParam, String codVendedor) {

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fi);
        Date fechaIn = null, fechaFin = null;

        try {
            fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);

            String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(ff);
            fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        } catch (ParseException ex) {
            Logger.getLogger(RptComisionPorVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map parametros = new HashMap();

        parametros.put("fecha_desde", fechaIn);
        parametros.put("fecha_hasta", fechaFin);
        parametros.put("sqlParam", sqlParam);
        parametros.put("cod_vendedor", codVendedor);

        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());

        ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
//       }   
        parametros.put("url", ruta_img.toString());
//        parametros.put("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(resumen_comision, parametros, Conexion.getInsatancia().getConnectionDB());

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

        getInstancia().resumenComisionPorVenta(new Date("2023/03/01"), new Date("2023/03/15"));
    }
}
