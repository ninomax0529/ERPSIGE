/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.contabilidad.dgii;

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
public class RptFormatoDgii {

    private URL ruta_formato606;
    private URL ruta_formato607;
    public static RptFormatoDgii manager;
    private JasperReport reporte_formato606;
    private JasperReport reporte_formato607;

    public static RptFormatoDgii getInstancia() {
        if (manager == null) {
            manager = new RptFormatoDgii();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptFormatoDgii() {

        try {

            ruta_formato606 = getClass().getResource("RptFormato606.jasper");
            reporte_formato606 = (JasperReport) JRLoader.loadObject(ruta_formato606);

            ruta_formato607 = getClass().getResource("RptFormato607.jasper");
            reporte_formato607 = (JasperReport) JRLoader.loadObject(ruta_formato607);

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void reporte606(Date fechaInicio, Date fechaFinal) throws ParseException {

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio);
        Date fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);
        String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(fechaFinal);
        Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        Map parametros = new HashMap();

        parametros.put("fechaIni", fechaIn);
        parametros.put("fechafin", fechaFin);
//        parametros.put("unidad_negocio", 5);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

//            SessionImplementor miSessionImplementor = (SessionImplementor) HibernateUtil.getSession();
            reporte_final = JasperFillManager
                    .fillReport(reporte_formato606, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void reporte607(Date fechaInicio, Date fechaFinal) throws ParseException {

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio);
        Date fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);
        String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(fechaFinal);
        Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        Map parametros = new HashMap();

        parametros.put("fechaIni", fechaIn);
        parametros.put("fechafin", fechaFin);
//        parametros.put("unidad_negocio", 2);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

//            SessionImplementor miSessionImplementor = (SessionImplementor) HibernateUtil.getSession();
            reporte_final = JasperFillManager
                    .fillReport(reporte_formato607, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            //
//        try {
////            getInstancia().imprimirLibroDiario(new Date("2019/12/01"),new Date("2020/01/15"));
////            getInstancia().imprimirLibroMayor(new Date("2019/12/01"), new Date("2020/01/15"));
            getInstancia().reporte607(new Date("2023/01/01"), new Date("2023/01/15"));
////             getInstancia().imprimirBalanzaDeComprobacion(new Date("2019/12/01"), new Date("2020/01/15"));
//
//        } catch (ParseException ex) {
//            Logger.getLogger(RptFormatoDgii.class.getName()).log(Level.SEVERE, null, ex);
//        }
        } catch (ParseException ex) {
            Logger.getLogger(RptFormatoDgii.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
