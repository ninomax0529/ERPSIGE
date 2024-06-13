/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.inventario;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import utiles.Conexion;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author maximilianoa-te
 */
public class RptImprimirEtiqueta {

    private static RptImprimirEtiqueta rptImprimirEtiqueta = null;
    private JasperReport cajas, reporte_generico, reporte, reporte_etiqueta;
    private JRPrintServiceExporter exporter = new JRPrintServiceExporter();
    private PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();

    private URL ruta_img;

    public RptImprimirEtiqueta() {

        try {

            URL ruta = getClass().getResource("EtiquetaTriplea_logo_ORG.jasper");

            URL ruta_generica = getClass().getResource("/reporte/inventario/articulo/Etiqueta_articulo.jasper");

            ruta_img = getClass().getResource("/imagen/logo_pintura_ultimo.png");

            reporte = (JasperReport) JRLoader.loadObject(ruta);
            reporte_generico = (JasperReport) JRLoader.loadObject(ruta_generica);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public static RptImprimirEtiqueta getInstancia() {

        if (rptImprimirEtiqueta == null) {

            rptImprimirEtiqueta = new RptImprimirEtiqueta();
        }
        return rptImprimirEtiqueta;
    }

    public void imprimir(String codigo, String impresora) {

        Map parametros = new HashMap();
        parametros.put("articulo", codigo);

        JasperPrint reporte_final;
        Connection con;
        con = Conexion.getInsatancia().getConnectionDB();

        try {

//            reporte_final = JasperFillManager.fillReport(reporte, parametros, con);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);

            imprimirLabel(jasperPrint, impresora);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void imprimirPorLote(String[] codigo, String impresora) {

        Map parametros = new HashMap();
        parametros.put("articulo", codigo);

        JasperPrint reporte_final;
        Connection con;
        con = Conexion.getInsatancia().getConnectionDB();

        try {

//            reporte_final = JasperFillManager.fillReport(reporte, parametros, con);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);

//            JasperViewer.viewReport(jasperPrint, false);
            imprimirLabel(jasperPrint, impresora);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void verEtiqueta(String codigo) {

        Map parametros = new HashMap();
        parametros.put("articulo", codigo);
        parametros.put("url", ruta_img.toString());

        JasperPrint reporte_final;
        Connection con;
        con = Conexion.getInsatancia().getConnectionDB();

        try {

//            reporte_final = JasperFillManager.fillReport(reporte, parametros, con);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void verEtiqueta(String codigo, int lote) {

        Map parametros = new HashMap();
        parametros.put("articulo", codigo);
        parametros.put("lote", lote);
        parametros.put("url", ruta_img.toString());

        JasperPrint reporte_final;
        Connection con;
        con = Conexion.getInsatancia().getConnectionDB();

        try {

//            reporte_final = JasperFillManager.fillReport(reporte, parametros, con);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void verEtiqueta(String codigo, int lote, String unidad) {

        Map parametros = new HashMap();
        parametros.put("articulo", codigo);
        parametros.put("lote", lote);
//        parametros.put("produccion", produccion);
        parametros.put("unidad", unidad);
        parametros.put("url", ruta_img.toString());

        JasperPrint reporte_final;
        Connection con;
        con = Conexion.getInsatancia().getConnectionDB();

        try {

//            reporte_final = JasperFillManager.fillReport(reporte, parametros, con);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void verEtiquetaGenerica(String codigo, int ung) {

        Map parametros = new HashMap();
        
        parametros.put("articulo", codigo);
        parametros.put("unidad_negocio", ung);
    
        Connection con;
        con = Conexion.getInsatancia().getConnectionDB();

        try {

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte_generico, parametros, con);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void imprimirLabe(String codigo, int lote) {

        Map parametros = new HashMap();
        parametros.put("articulo", codigo);
        parametros.put("lote", lote);
        parametros.put("url", ruta_img.toString());

        JasperPrint reporte_final;
        Connection con;
        con = Conexion.getInsatancia().getConnectionDB();

        try {

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);

            JasperPrintManager.printReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void imprimirArticuloEtiquetado() {

        Map parametros = new HashMap();
        parametros.put("url", ruta_img.toString());

        JasperPrint reporte_final;
        Connection con;
        con = Conexion.getInsatancia().getConnectionDB();

        try {

//            reporte_final = JasperFillManager.fillReport(reporte, parametros, con);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte_etiqueta, parametros, con);

            JasperViewer.viewReport(jasperPrint, false);

//            imprimirLabel(jasperPrint,impresora);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void imprimirLabel(JasperPrint rep, String impresora) {

//        String labelPrinter = "Brother QL-1050";
        String labelPrinter = impresora;

        // Obtenemos todos las improsoras conectadas a la PC.
        PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService pservice = null;

        // Recorremos el arreglo que contiene los printers
        // y guardamos en {pservice} el printer con el nombre guardado 
        for (PrintService p : printService) {

//            System.out.println("nombre printer " + p.getName());
            if (p.getName().equals(labelPrinter)) {
                System.out.println("nombre printer " + p.getName());
                pservice = p;
                break;
            }
        }

        if (pservice == null || rep == null) {
            return;
        }

        try {

            JRPrintServiceExporter jrPS = new JRPrintServiceExporter();
            jrPS.setParameter(JRExporterParameter.JASPER_PRINT, rep);
            jrPS.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, pservice);
            jrPS.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, false);

            jrPS.exportReport();

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public List<String> getImpresora(JasperPrint rep) {

        String labelPrinter = "Brother QL-1050";

        // Obtenemos todos las improsoras conectadas a la PC.
        PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService pservice = null;

        List<String> lista = new ArrayList<>();
        // Recorremos el arreglo que contiene los printers
        // y guardamos en {pservice} el printer con el nombre guardado 
        for (PrintService p : printService) {

//            System.out.println("nombre printer " + p.getName());
            lista.add(p.getName());

            if (p.getName().equals(labelPrinter)) {

                System.out.println("nombre printer " + p.getName());
                pservice = p;
            }
        }

        if (pservice == null || rep == null) {
            return lista;
        }

        try {

            JRPrintServiceExporter jrPS = new JRPrintServiceExporter();
            jrPS.setParameter(JRExporterParameter.JASPER_PRINT, rep);
            jrPS.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, pservice);
            jrPS.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, false);

            jrPS.exportReport();

        } catch (JRException ex) {
        }

        return lista;
    }

    public static void main(String[] args) {

//        RptImprimirEtiqueta.getInstancia().imprimirArticuloEtiquetado();
//        JasperPrint jp = null;
//        for (String p : getInstancia().getImpresora(jp)) {
//
//            System.out.println("Nombre Printer** " + p);
//        }
        System.out.println("entro al casa ");
        RptImprimirEtiqueta.getInstancia().verEtiqueta("987654752",12,"GALON");
//        System.out.println("Salio");
    }
}
