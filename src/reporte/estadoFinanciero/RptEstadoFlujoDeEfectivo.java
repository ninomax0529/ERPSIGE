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

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utiles.Conexion;

/**
 *
 * @author MAXIMILIANO
 */
public class RptEstadoFlujoDeEfectivo {

    public static RptEstadoFlujoDeEfectivo manager;

    private URL RUTA_RPT_flujo_de_efectivo;
    private URL RUTA_SUB_RPT_ACT_OPERACION;
    private URL RUTA_SUB_RPT_ACT_INVERSION;
    private URL RUTA_SUB_RPT_ACT_FINANCIAMIENTO;


    private JasperReport RPT_RPT_estado_situacion;
    private JasperReport SUB_RPT_ACT_OPERACION;
    private JasperReport SUB_RPT_ACT_INVERSION;
    private JasperReport SUB_RPT_ACT_FINANCIAMIENTO;

    public static RptEstadoFlujoDeEfectivo getInstancia() {
        if (manager == null) {
            manager = new RptEstadoFlujoDeEfectivo();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptEstadoFlujoDeEfectivo() {

        try {

            RUTA_RPT_flujo_de_efectivo = getClass().getResource("flujoDeEfectivo/RptEstadoFlujoDeEfectivo_v1.jasper");
            RUTA_SUB_RPT_ACT_OPERACION = getClass().getResource("flujoDeEfectivo/subRpt/subActividadDeOperacion.jasper");
            RUTA_SUB_RPT_ACT_INVERSION = getClass().getResource("flujoDeEfectivo/subRpt/subActividadDeInversion.jasper");
            RUTA_SUB_RPT_ACT_FINANCIAMIENTO = getClass().getResource("flujoDeEfectivo/subRpt/subActividadDeFinaciamiento.jasper");


            RPT_RPT_estado_situacion = (JasperReport) JRLoader.loadObject(RUTA_RPT_flujo_de_efectivo);
            SUB_RPT_ACT_OPERACION = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_ACT_OPERACION);
            SUB_RPT_ACT_INVERSION = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_ACT_INVERSION);
            SUB_RPT_ACT_FINANCIAMIENTO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_ACT_FINANCIAMIENTO);
          
        

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void estadoFlujoDeEfectivo(Date fechaEsAnterior, Date fechaEsActual) throws ParseException {

        JasperPrint reporte_final;

        String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(fechaEsActual);
        String fechaanteriorString = new SimpleDateFormat("yyyy-MM-dd").format(fechaEsAnterior);
        Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);
        Date fechaIni = new SimpleDateFormat("yyyy-MM-dd").parse(fechaanteriorString);

        System.out.println("fechaFin : " + fechaFinString);
          System.out.println("fechaiNI : " + fechaanteriorString);
        Map parametros = new HashMap();

        
        /*SUB REPORTE COMO PARAMETROS */
        parametros.put("fecha_es_actual", fechaFin);
        parametros.put("fecha_es_anterior", fechaIni);
        parametros.put("unidad_negocio", 5);
        
        
        /*SUBREPORTE COMO PARAMETROS */
        parametros.put("SUB_RPT_ACTIVIDAD_DE_OPERACION", SUB_RPT_ACT_OPERACION);
        parametros.put("SUB_RPT_ACTIVIDAD_DE_INVERSION", SUB_RPT_ACT_INVERSION);
        parametros.put("SUB_RPT_ACTIVIDAD_DE_FINANC", SUB_RPT_ACT_FINANCIAMIENTO);

      

//         parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        try {

            reporte_final = JasperFillManager
                    .fillReport(RPT_RPT_estado_situacion, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(reporte_final, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParseException {

        getInstancia().estadoFlujoDeEfectivo(new Date("2023/01/30"),new Date("2023/04/30"));
//        getInstancia().balanceGeneral(new Date("2023/12/22"), 3);
    }
}
