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
import manejo.ejecutivoDeVenta.ManejoEjecutivoDeVenta;
import modelo.EjecutivoDeVenta;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utiles.Conexion;

/**
 *
 * @author MAXIMILIANO
 */
public class RptComisionPorCobro {

    private URL ruta_maestro;
    private URL ruta_maestro_comision;
    private URL ruta_img;
    private URL urlsub;
    public static RptComisionPorCobro manager;
    private JasperReport reporte_maestro;
    private JasperReport reporte_maestro_comision;

    public static RptComisionPorCobro getInstancia() {
        if (manager == null) {
            manager = new RptComisionPorCobro();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptComisionPorCobro() {

        try {
            ruta_maestro = getClass().getResource("/reporte/comisiones/RptComisionPorCobros_v1.jasper");
            ruta_maestro_comision = getClass().getResource("/reporte/comisiones/RptComisionPorCobros.jasper");

            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);
            reporte_maestro_comision = (JasperReport) JRLoader.loadObject(ruta_maestro_comision);

            ruta_img = getClass().getResource("/imagen/magla_img.jpg");

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void imprimir(Date fi, Date ff) {

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fi);
        Date fechaIn = null, fechaFin = null;
        try {
            fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);

            String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(ff);
            fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        } catch (ParseException ex) {
            Logger.getLogger(RptComisionPorCobro.class.getName()).log(Level.SEVERE, null, ex);
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

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(Date fi, Date ff, EjecutivoDeVenta ejv, boolean isSuplidor) {

        String vendedor = ejv.getNombre();
        Double comisionCobro = ejv.getComisionPorCobros() / 100;
        String cargo = ejv.getCargo().getNombre();
        String sqlParam = "";

        int codVendedor = ejv.getCodigo();

        if (isSuplidor == true) {
            System.out.println("isSuplidor " + isSuplidor);
//            sqlParam = "  and  suplidor_de_venta=" + ejv.getCodigo();
                 sqlParam = "  and  origen_factura=4 and  suplidor_de_venta=" + ejv.getCodigo();
            System.out.println("sqlParam : " + sqlParam);

        } else {
            sqlParam = "  and vendedor=" + ejv.getCodigo();
            System.out.println("vendedor " + isSuplidor);
            System.out.println("sqlParam : " + sqlParam);
        }

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fi);
        Date fechaIn = null, fechaFin = null;
        try {
            fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);

            String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(ff);
            fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        } catch (ParseException ex) {
            Logger.getLogger(RptComisionPorCobro.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map parametros = new HashMap();

        parametros.put("fecha_desde", fechaIn);
        parametros.put("fecha_hasta", fechaFin);
        parametros.put("cod_vendedor", codVendedor);
        parametros.put("sqlParam", sqlParam);
        parametros.put("cargo", cargo);
        parametros.put("vendedor", vendedor);
        parametros.put("porciento_comision", comisionCobro);

        ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
//       }   
        parametros.put("url", ruta_img.toString());
//        parametros.put("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

        EjecutivoDeVenta ejv = ManejoEjecutivoDeVenta.getInstancia().getEjecutivo(84);

        getInstancia().imprimir(new Date("2024/01/01"), new Date("2024/06/31"), ejv, true);
    }
}
