/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.unidadnegocio;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import manejo.ManejoConfiguracion;
import manejo.reporteUnidadNegocio.ManejoReporteUnidadNegocio;
import manejo.unidadDeNegocio.ManejoDocumentoUnidadNegocio;
import modelo.DocumentoUnidadDeNegocio;
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
public class RptFactura {

    public static RptFactura manager;

    //**************Reporte*********************** 
    private JasperReport SUB_RPT_DETALLE_FACTURA;
    private JasperReport SUB_RPT_DETALLE_FACTURA_AGRUPADO;
    private JasperReport SUB_RPT_DETALLE_FACTURA_TEMPORAL;
    private JasperReport SUB_RPT_DETALLE_DATOS_VEHICULO;
    private JasperReport REPORTE_MAESTRO;
    private JasperReport reporte_maestro_temporal;

    //Cierre declaracion reporte 
//*********************Ruta************************
    private URL RUTA_SUB_RPT_DETALLE_FACTURA;
    private URL RUTA_SUB_RPT_DETALLE_FACTURAAGRUPADO;
    private URL RUTA_SUB_RPT_DETALLE_FACTURA_TEMPORAL;
    private URL RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO;
    private URL RUTA_MAESTRA;
    private URL ruta_maestro_temporal;
    private URL ruta_img;
    private URL ruta_img1;

//Cierre declaracion ruta 
    public static RptFactura getInstancia() {

        if (manager == null) {

            manager = new RptFactura();
            return manager;
        }
        return manager;
    }

//    private JasperReport SUB_RPT_DETALLE_ORDEN;
    /**
     * Constructor de ManejoImpresion
     */
    public RptFactura() {

//        try {
//            RUTA_MAESTRA = getClass().getResource("/reporte/unidadnegocio/ightrack/jasper/RptFacturaClienteIghTrack.jasper");
//            ruta_maestro_temporal = getClass().getResource("/reporte/unidadnegocio/ightrack/jasper/RptFacturaClienteIghTrackTemporal.jasper");
//            RUTA_SUB_RPT_DETALLE_FACTURA = getClass().getResource("/reporte/unidadnegocio/ightrack/jasper/subRptDetalleFacturaIghTrack.jasper");
//            RUTA_SUB_RPT_DETALLE_FACTURAAGRUPADO = getClass().getResource("/reporte/unidadnegocio/ightrack/jasper/subRptDetAgrupoadoFacturaIghTrack.jasper");
//            RUTA_SUB_RPT_DETALLE_FACTURA_TEMPORAL = getClass().getResource("/reporte/unidadnegocio/ightrack/jasper/subRptDetalleFacturaIghTrackTemporal.jasper");
//            RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO = getClass().getResource("/reporte/unidadnegocio/ightrack/jasper/subRptDetalleDatoVehiculoIghTrack.jasper");
//            REPORTE_MAESTRO = (JasperReport) JRLoader.loadObject(RUTA_MAESTRA);
//            reporte_maestro_temporal = (JasperReport) JRLoader.loadObject(ruta_maestro_temporal);
//            SUB_RPT_DETALLE_FACTURA = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURA);
//            SUB_RPT_DETALLE_FACTURA_AGRUPADO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURAAGRUPADO);
//            SUB_RPT_DETALLE_FACTURA_TEMPORAL = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURA_TEMPORAL);
//            SUB_RPT_DETALLE_DATOS_VEHICULO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO);
        ruta_img = getClass().getResource("/imagen/img_nom_fsc.png");
        ruta_img1 = getClass().getResource("/imagen/img_nom_fsc_1.png");

//        } catch (JRException ex) {
//            ex.printStackTrace();
//        }
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

            reporte_final = JasperFillManager.fillReport(REPORTE_MAESTRO, parametros, Conexion.getInsatancia().getConnectionDB());

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

            reporte_final = JasperFillManager.fillReport(REPORTE_MAESTRO, parametros, Conexion.getInsatancia().getConnectionDB());

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

    public void verFactura(int factura, int ung) {

        JasperPrint reporte_final;
        int numeroRrpt = 0, numSubrpt = 0;

        String rutaRpt = null, rutaDetalleRpt = null;

        Map parametros = new HashMap();

        try {

            DocumentoUnidadDeNegocio docUng = ManejoDocumentoUnidadNegocio.getInstancia().getTipoDocumento(ung, 7);

            if (!(docUng == null)) {
                numeroRrpt = docUng.getNumero();
                numSubrpt = docUng.getNumeroSubReporte1();

                rutaRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, numeroRrpt);
                System.out.println("ruta : " + rutaRpt);
                rutaDetalleRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, numSubrpt);

            } else {

                rutaRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(7, 1);
                System.out.println("ruta : " + rutaRpt);
                rutaDetalleRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(7, 2);
            }

            System.out.println("rutaDetalleRpt : " + rutaDetalleRpt);

            String rutaDatosVehiculoRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, 3);

            System.out.println("rutaDatosVehiculoRpt " + rutaDatosVehiculoRpt);
            /*ruta deL reporte */
            RUTA_MAESTRA = getClass().getResource(rutaRpt);
            RUTA_SUB_RPT_DETALLE_FACTURA = getClass().getResource(rutaDetalleRpt);
            if (rutaDatosVehiculoRpt != null) {
                RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO = getClass().getResource(rutaDatosVehiculoRpt);
            }


            /*ruta deL reporte */
 /*CARGANDO LOS REPORTES */
            REPORTE_MAESTRO = (JasperReport) JRLoader.loadObject(RUTA_MAESTRA);
            SUB_RPT_DETALLE_FACTURA = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURA);
            if (rutaDatosVehiculoRpt != null) {
                SUB_RPT_DETALLE_DATOS_VEHICULO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO);
            }

            /*CARGANDO LOS REPORTES */
 /*PARAMETROS DEL REPORTE */
            parametros.put("url", ruta_img.toString());
            parametros.put("url1", ruta_img1.toString());
            parametros.put("factura", factura);
            parametros.put("empresa", 3);
//            parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());
            parametros.put("unidad_negocio", ung);
            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

            /*PARAMETROS DEL REPORTE */
            reporte_final = JasperFillManager.fillReport(REPORTE_MAESTRO, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void verFactura(int factura, int ung, int formato) {

        JasperPrint reporte_final;
        int numeroRrpt = 0, numSubrpt = 0;

         System.out.println("ung : "+ung+" formato "+formato);
        String rutaRpt = null, rutaDetalleRpt = null;

        Map parametros = new HashMap();

        try {

            DocumentoUnidadDeNegocio docUng = ManejoDocumentoUnidadNegocio.getInstancia().getTipoDocumento(ung, 7, formato);

            System.out.println("docUng "+docUng);
            if (!(docUng == null)) {
                numeroRrpt = docUng.getNumero();
                numSubrpt = docUng.getNumeroSubReporte1();

                rutaRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, numeroRrpt);
                System.out.println("ruta : " + rutaRpt);
                rutaDetalleRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, numSubrpt);

            } else {

                rutaRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(7, 1);
                System.out.println("ruta : " + rutaRpt);
                rutaDetalleRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(7, 2);
            }

            System.out.println("rutaDetalleRpt : " + rutaDetalleRpt);

            String rutaDatosVehiculoRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, 3);

            System.out.println("rutaDatosVehiculoRpt " + rutaDatosVehiculoRpt);
            /*ruta deL reporte */
            RUTA_MAESTRA = getClass().getResource(rutaRpt);
            RUTA_SUB_RPT_DETALLE_FACTURA = getClass().getResource(rutaDetalleRpt);
            if (rutaDatosVehiculoRpt != null) {
                RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO = getClass().getResource(rutaDatosVehiculoRpt);
            }


            /*ruta deL reporte */
 /*CARGANDO LOS REPORTES */
            REPORTE_MAESTRO = (JasperReport) JRLoader.loadObject(RUTA_MAESTRA);
            SUB_RPT_DETALLE_FACTURA = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURA);
            if (rutaDatosVehiculoRpt != null) {
                SUB_RPT_DETALLE_DATOS_VEHICULO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO);
            }

            /*CARGANDO LOS REPORTES */
 /*PARAMETROS DEL REPORTE */
            parametros.put("url", ruta_img.toString());
            parametros.put("url1", ruta_img1.toString());
            parametros.put("factura", factura);
            parametros.put("empresa", 3);
//            parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());
            parametros.put("unidad_negocio", ung);
            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

            /*PARAMETROS DEL REPORTE */
            reporte_final = JasperFillManager.fillReport(REPORTE_MAESTRO, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void imprimirFactura(int factura, int ung) {

        System.out.println("imprimiendo factura ... " + factura + " unidad de negocio : " + ung);
        JasperPrint reporte_final;
        int numeroRrpt = 0, numSubrpt = 0;

        String rutaRpt = null, rutaDetalleRpt = null;

        Map parametros = new HashMap();

        try {

            DocumentoUnidadDeNegocio docUng = ManejoDocumentoUnidadNegocio.getInstancia().getTipoDocumento(ung, 7);

            if (!(docUng == null)) {
                numeroRrpt = docUng.getNumero();
                numSubrpt = docUng.getNumeroSubReporte1();

                rutaRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, numeroRrpt);
                System.out.println("ruta : " + rutaRpt);
                rutaDetalleRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, numSubrpt);

            } else {

                rutaRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(7, 1);
                System.out.println("ruta : " + rutaRpt);
                rutaDetalleRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(7, 2);
            }

            System.out.println("rutaDetalleRpt : " + rutaDetalleRpt);

            String rutaDatosVehiculoRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, 3);

            System.out.println("rutaDatosVehiculoRpt " + rutaDatosVehiculoRpt);
            /*ruta deL reporte */
            RUTA_MAESTRA = getClass().getResource(rutaRpt);
            RUTA_SUB_RPT_DETALLE_FACTURA = getClass().getResource(rutaDetalleRpt);
            if (rutaDatosVehiculoRpt != null) {
                RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO = getClass().getResource(rutaDatosVehiculoRpt);
            }


            /*ruta deL reporte */
 /*CARGANDO LOS REPORTES */
            REPORTE_MAESTRO = (JasperReport) JRLoader.loadObject(RUTA_MAESTRA);
            SUB_RPT_DETALLE_FACTURA = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURA);
            if (rutaDatosVehiculoRpt != null) {
                SUB_RPT_DETALLE_DATOS_VEHICULO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO);
            }

            /*CARGANDO LOS REPORTES */
 /*PARAMETROS DEL REPORTE */
            parametros.put("url", ruta_img.toString());
            parametros.put("url1", ruta_img1.toString());
            parametros.put("factura", factura);
//            parametros.put("empresa", 3);
            parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());
            parametros.put("unidad_negocio", ung);
            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

            /*PARAMETROS DEL REPORTE */
            reporte_final = JasperFillManager.fillReport(REPORTE_MAESTRO, parametros, Conexion.getInsatancia().getConnectionDB());

//            JasperPrint jasperPrint = reporte_final;
            JasperPrintManager.printReport(reporte_final, false);

//            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public String exportarFacturaAPDF(Factura factura, String rutaStr) {

        int ung = factura.getUnidadDeNegocio().getCodigo();
        System.out.println(" Exportando factura ... " + factura + " de  unidad de negocio : " + factura.getUnidadDeNegocio().getNombre());
        JasperPrint reporte_final;
        int numeroRrpt = 0, numSubrpt = 0;

        String rutaRpt = null, rutaDetalleRpt = null, ruta = null;

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

        try {

            DocumentoUnidadDeNegocio docUng = ManejoDocumentoUnidadNegocio.getInstancia().getTipoDocumento(ung, 7);

            if (!(docUng == null)) {
                numeroRrpt = docUng.getNumero();
                numSubrpt = docUng.getNumeroSubReporte1();

                rutaRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, numeroRrpt);
                System.out.println("ruta : " + rutaRpt);
                rutaDetalleRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, numSubrpt);

            } else {

                rutaRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(7, 1);
                System.out.println("ruta : " + rutaRpt);
                rutaDetalleRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(7, 2);
            }

            System.out.println("rutaDetalleRpt : " + rutaDetalleRpt);

            String rutaDatosVehiculoRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, 3);

            System.out.println("rutaDatosVehiculoRpt " + rutaDatosVehiculoRpt);
            /*ruta deL reporte */
            RUTA_MAESTRA = getClass().getResource(rutaRpt);
            RUTA_SUB_RPT_DETALLE_FACTURA = getClass().getResource(rutaDetalleRpt);
            if (rutaDatosVehiculoRpt != null) {
                RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO = getClass().getResource(rutaDatosVehiculoRpt);
            }


            /*ruta deL reporte */
 /*CARGANDO LOS REPORTES */
            REPORTE_MAESTRO = (JasperReport) JRLoader.loadObject(RUTA_MAESTRA);
            SUB_RPT_DETALLE_FACTURA = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURA);
            if (rutaDatosVehiculoRpt != null) {
                SUB_RPT_DETALLE_DATOS_VEHICULO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO);
            }

            /*CARGANDO LOS REPORTES */
 /*PARAMETROS DEL REPORTE */
            parametros.put("url", ruta_img.toString());
            parametros.put("url1", ruta_img1.toString());
            parametros.put("factura", factura.getCodigo());
//            parametros.put("empresa", 3);
            parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());
            parametros.put("unidad_negocio", ung);
            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

            /*PARAMETROS DEL REPORTE */
            reporte_final = JasperFillManager.fillReport(REPORTE_MAESTRO, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperExportManager.exportReportToPdfFile(reporte_final, ruta);
        } catch (JRException ex) {
            ex.printStackTrace();
        }

        return ruta;
    }

    public void verFacturaTemporal(int factura, int ung) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        try {

            String rutaRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, 4).trim();
            System.out.println("ruta : " + rutaRpt);
            String rutaDetalleRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, 5);
//            String rutaDatosVehiculoRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, 3);

            String rutaDatosVehiculoRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, 6);

            System.out.println("rutaDatosVehiculoRpt " + rutaDatosVehiculoRpt);
            /*ruta deL reporte */

            RUTA_SUB_RPT_DETALLE_FACTURA = getClass().getResource(rutaDetalleRpt);
            if (rutaDatosVehiculoRpt != null) {
                RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO = getClass().getResource(rutaDatosVehiculoRpt);
            }

            /*ruta deL reporte */
            RUTA_MAESTRA = getClass().getResource(rutaRpt);
            RUTA_SUB_RPT_DETALLE_FACTURA = getClass().getResource(rutaDetalleRpt);
//            RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO = getClass().getResource(rutaDatosVehiculoRpt);

            /*ruta deL reporte */
 /*CARGANDO LOS REPORTES */
            REPORTE_MAESTRO = (JasperReport) JRLoader.loadObject(RUTA_MAESTRA);
            SUB_RPT_DETALLE_FACTURA = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURA);
            SUB_RPT_DETALLE_DATOS_VEHICULO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO);

            /*CARGANDO LOS REPORTES */
 /*PARAMETROS DEL REPORTE */
            parametros.put("factura", factura);
            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);
            parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());
            parametros.put("unidad_negocio", ung);

            /*PARAMETROS DEL REPORTE */
            reporte_final = JasperFillManager.fillReport(REPORTE_MAESTRO, parametros, Conexion.getInsatancia().getConnectionDB());

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
        parametros.put("unidad_negocio", 2);
//        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
//        parametros.put("estado_factura", estadoFactura);

//        parametros.put("url", ruta_img.toString());
        try {

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA_AGRUPADO);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

            reporte_final = JasperFillManager.fillReport(REPORTE_MAESTRO, parametros, Conexion.getInsatancia().getConnectionDB());

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

            reporte_final = JasperFillManager.fillReport(REPORTE_MAESTRO, parametros, Conexion.getInsatancia().getConnectionDB());

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

            reporte_final = JasperFillManager.fillReport(REPORTE_MAESTRO, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperExportManager.exportReportToPdfFile(reporte_final, ruta);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

        return ruta;
    }

    public String exportarFacturaAPDFAnt(Factura factura, String rutaStr) {

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

            reporte_final = JasperFillManager.fillReport(REPORTE_MAESTRO, parametros, Conexion.getInsatancia().getConnectionDB());

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

            reporte_final = JasperFillManager.fillReport(REPORTE_MAESTRO, parametros, Conexion.getInsatancia().getConnectionDB());

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

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA_AGRUPADO);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

            reporte_final = JasperFillManager.fillReport(REPORTE_MAESTRO, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrintManager.printReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public String exportarFacturaAPDF(Factura factura, int ung) {

        JasperPrint reporte_final;
        int numeroRrpt = 0, numSubrpt = 0;
        String ruta = null;

        String rutaRpt = null, rutaDetalleRpt = null;

        Map parametros = new HashMap();

        try {

            DocumentoUnidadDeNegocio docUng = ManejoDocumentoUnidadNegocio.getInstancia().getTipoDocumento(ung, 7);

            if (!(docUng == null)) {
                numeroRrpt = docUng.getNumero();
                numSubrpt = docUng.getNumeroSubReporte1();

                rutaRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, numeroRrpt);
                System.out.println("ruta : " + rutaRpt);
                rutaDetalleRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, numSubrpt);

            } else {

                rutaRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(7, 1);
                System.out.println("ruta : " + rutaRpt);
                rutaDetalleRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(7, 2);
            }

            System.out.println("rutaDetalleRpt : " + rutaDetalleRpt);

            String rutaDatosVehiculoRpt = ManejoReporteUnidadNegocio.getInstancia().getRutaDocumento(ung, 7, 3);

            System.out.println("rutaDatosVehiculoRpt " + rutaDatosVehiculoRpt);
            /*ruta deL reporte */
            RUTA_MAESTRA = getClass().getResource(rutaRpt);
            RUTA_SUB_RPT_DETALLE_FACTURA = getClass().getResource(rutaDetalleRpt);
            if (rutaDatosVehiculoRpt != null) {
                RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO = getClass().getResource(rutaDatosVehiculoRpt);
            }


            /*ruta deL reporte */
 /*CARGANDO LOS REPORTES */
            REPORTE_MAESTRO = (JasperReport) JRLoader.loadObject(RUTA_MAESTRA);
            SUB_RPT_DETALLE_FACTURA = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_FACTURA);
            if (rutaDatosVehiculoRpt != null) {
                SUB_RPT_DETALLE_DATOS_VEHICULO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_DATOS_VEHICULO);
            }

            /*CARGANDO LOS REPORTES */
 /*PARAMETROS DEL REPORTE */
            parametros.put("url", ruta_img.toString());
            parametros.put("url1", ruta_img1.toString());
            parametros.put("factura", factura.getCodigo());
//            parametros.put("empresa", 3);
            parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());
            parametros.put("unidad_negocio", ung);
            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_FACTURA);
            parametros.put("SUB_RPT_DATO_VEHICULO", SUB_RPT_DETALLE_DATOS_VEHICULO);

            /*PARAMETROS DEL REPORTE */
            reporte_final = JasperFillManager.fillReport(REPORTE_MAESTRO, parametros, Conexion.getInsatancia().getConnectionDB());

            File carpeta = null;
            String mes = factura.getFecha().toString();

            System.out.println(" mes " + mes);
            carpeta = new File("C://facturapdf/" + mes);
            carpeta.mkdirs();

            ruta = carpeta + "/FacturaCliente_" + factura.getNumero() + ".pdf";

            JasperExportManager.exportReportToPdfFile(reporte_final, ruta);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

        return ruta;
    }

    public static void main(String[] args) {

        getInstancia().verFactura(1765, 3,2);
//        getInstancia().verFacturaDetAgrupado(1129);
    }
}
