/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.contabilidad;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author MAXIMILIANO
 */
public class RptLibroContable {

    private URL ruta_maestro;

    private URL ruta_maestro_libro_mayor;
    private URL ruta_maestro_libro_mayor_por_cuenta;
    private URL ruta_img;
    private URL urlsub;
    private URL ruta_balnaza_comprobacion;
    private URL ruta_detalle_entrada_diario;
    private URL ruta_entrada_diario;
    public static RptLibroContable manager;
    private JasperReport reporte_maestro;
    private JasperReport reporte_entrada_diario;
    private JasperReport reporte_detalle_entrada_diario;
    private JasperReport reporte_maestro_balanza_de_comprobacion;
    private JasperReport reporte_maestro_libro_mayor;
    private JasperReport reporte_maestro_libro_mayor_por_cuenta;

    public static RptLibroContable getInstancia() {
        if (manager == null) {
            manager = new RptLibroContable();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptLibroContable() {

        try {

            ruta_balnaza_comprobacion = getClass().getResource("RptBalanzaDeComprobacion.jasper");
            ruta_maestro = getClass().getResource("RptLibroDiario.jasper");
//            ruta_img = getClass().getResource("logo_reportes.png");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);

            ruta_maestro_libro_mayor = getClass().getResource("RptLibroMayor.jasper");
            ruta_maestro_libro_mayor_por_cuenta = getClass().getResource("RptLibroMayorPorCuenta.jasper");
            ruta_entrada_diario = getClass().getResource("RptEntradaDeDiario.jasper");
            ruta_detalle_entrada_diario = getClass().getResource("/reporte/contabilidad/subRptDetalleEntradaDeDiario.jasper");
            
            reporte_maestro_libro_mayor = (JasperReport) JRLoader.loadObject(ruta_maestro_libro_mayor);
            reporte_maestro_libro_mayor_por_cuenta = (JasperReport) JRLoader.loadObject(ruta_maestro_libro_mayor_por_cuenta);
            reporte_maestro_balanza_de_comprobacion = (JasperReport) JRLoader.loadObject(ruta_balnaza_comprobacion);
            reporte_entrada_diario = (JasperReport) JRLoader.loadObject(ruta_entrada_diario);
            reporte_detalle_entrada_diario = (JasperReport) JRLoader.loadObject(ruta_detalle_entrada_diario);

            ruta_img = getClass().getResource("/imagen/magla_img.jpg");

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void imprimirLibroDiario(String query) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("query", query);
        parametros.put("url", ruta_img.toString());
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void entradaDeDiario(Integer entrada) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("entrada", entrada);
        parametros.put("SUB_RPT_DETALLE", reporte_detalle_entrada_diario);
//        parametros.put("unidad_negocio", 2);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_entrada_diario, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void imprimirBalanzaDeComprobacion(Date fechaInicio, Date fechaFinal) throws ParseException {

        JasperPrint reporte_final;
//        Date fechaInicio = ClaseUtil.getFechaPrimerDiasDelMes(fecha);

//        System.out.println("fecha "+new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio)+" "+fecha);
        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio);
        Date fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);
        String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(fechaFinal);
        Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        Map parametros = new HashMap();

        parametros.put("fecha_inicio_periodo", fechaIn);

        parametros.put("fecha_final_periodo", fechaFin);

        parametros.put("url", ruta_img.toString());
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager
                    .fillReport(reporte_maestro_balanza_de_comprobacion, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void imprimirLibroDiario(Date fechaInicio, Date fechaFinal) throws ParseException {

        JasperPrint reporte_final;
//        Date fechaInicio = ClaseUtil.getFechaPrimerDiasDelMes(fecha);

//        System.out.println("fecha "+new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio)+" "+fecha);
        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio);
        Date fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);
        String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(fechaFinal);
        Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        Map parametros = new HashMap();

        parametros.put("fecha_inicio_periodo", fechaIn);
        parametros.put("fecha_final_periodo", fechaFin);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        parametros.put("url", ruta_img.toString());

        try {

//            SessionImplementor miSessionImplementor = (SessionImplementor) HibernateUtil.getSession();
            reporte_final = JasperFillManager
                    .fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void imprimirLibroMayor(Date fechaInicio, Date fechaFinal) throws ParseException {

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio);
        Date fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);

        String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(fechaFinal);
        Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        Map parametros = new HashMap();

        parametros.put("fecha_inicio_periodo", fechaIn);
        parametros.put("fecha_final_periodo", fechaFin);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager
                    .fillReport(reporte_maestro_libro_mayor, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(reporte_final, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void imprimirLibroMayorPorCuenta(Date fechaInicio, Date fechaFinal, String cuenta) throws ParseException {

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio);
        Date fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);

        String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(fechaFinal);
        Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        Map parametros = new HashMap();

        System.out.println("Cuenta " + cuenta);

        parametros.put("fecha_inicio_periodo", fechaIn);

        parametros.put("fecha_final_periodo", fechaFin);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        parametros.put("paramSql", cuenta);

        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro_libro_mayor_por_cuenta, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //            getInstancia().imprimirLibroDiario(new Date("2019/12/01"),new Date("2020/01/15"));
//            getInstancia().imprimirLibroMayor(new Date("2019/12/01"), new Date("2020/01/15"));
        getInstancia().entradaDeDiario(11);
//             getInstancia().imprimirBalanzaDeComprobacion(new Date("2019/12/01"), new Date("2020/01/15"));
    }
}
