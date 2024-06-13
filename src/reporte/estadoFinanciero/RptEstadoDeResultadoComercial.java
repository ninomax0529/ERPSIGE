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
public class RptEstadoDeResultadoComercial {

    public static RptEstadoDeResultadoComercial manager;

    private URL RUTA_RPT;
    private URL RUTA_SUB_RPT_INGRESOS;
    private URL RUTA_SUB_RPT_INGRESOS_FINANCIERO;
    private URL RUTA_SUB_RPT_COSTOS;
    private URL RUTA_SUB_RPT_GASTOS;
    private URL RUTA_SUB_RPT_GASTOS_FINACNIEROS;
    private URL RUTA_SUB_RPT_OTROS_INGRESOS;
    private URL RUTA_SUB_RPT_OTROS_GASTOS;
    private URL RUTA_SUB_RPT_IMPUSTO_Y_UTILIDAD_NETA;

    private JasperReport RPT_RPT;
    private JasperReport SUB_RPT_INGRESOS;
    private JasperReport SUB_RPT_INGRESOS_FINANCIERO;
    private JasperReport SUB_RPT_COSTOS_Y_UTILIDAD_BRUTA;
    private JasperReport SUB_RPT_GASTOS_Y_UTILIDAD_OPERATIVA;
    private JasperReport SUB_RPT_GASTOS_FINACNIEROS;
    private JasperReport SUB_RPT_OTROS_INGRESOS;
    private JasperReport SUB_RPT_OTROS_GASTOS;
    private JasperReport SUB_RPT_IMPUSTO_Y_UTILIDAD_NETA;

    public static RptEstadoDeResultadoComercial getInstancia() {
        if (manager == null) {
            manager = new RptEstadoDeResultadoComercial();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptEstadoDeResultadoComercial() {

        try {

            RUTA_RPT = getClass().getResource("deResultado/comercial/RptEstadoDeResultado_v1.jasper");
            RUTA_SUB_RPT_INGRESOS = getClass().getResource("deResultado/comercial/subRpt/subRptIngresoDeOperaciuon.jasper");
            RUTA_SUB_RPT_INGRESOS_FINANCIERO = getClass().getResource("deResultado/comercial/subRpt/subRptIngresoFinaciero.jasper");
            RUTA_SUB_RPT_COSTOS = getClass().getResource("deResultado/comercial/subRpt/subRptCostoDeOperaciuon.jasper");
            RUTA_SUB_RPT_GASTOS = getClass().getResource("deResultado/comercial/subRpt/subRptGastosDeOperaciuon.jasper");
            RUTA_SUB_RPT_GASTOS_FINACNIEROS = getClass().getResource("deResultado/comercial/subRpt/subGastosFinaciero.jasper");
            RUTA_SUB_RPT_OTROS_INGRESOS = getClass().getResource("deResultado/comercial/subRpt/subRptOtrosIngreso.jasper");
            RUTA_SUB_RPT_OTROS_GASTOS = getClass().getResource("deResultado/comercial/subRpt/subOtrosGastos.jasper");
            RUTA_SUB_RPT_IMPUSTO_Y_UTILIDAD_NETA = getClass().getResource("deResultado/comercial/subRpt/subRptImpustoYUtilidadNeta.jasper");

            RPT_RPT = (JasperReport) JRLoader.loadObject(RUTA_RPT);
            SUB_RPT_INGRESOS = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_INGRESOS);
            SUB_RPT_INGRESOS_FINANCIERO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_INGRESOS_FINANCIERO);
            SUB_RPT_COSTOS_Y_UTILIDAD_BRUTA = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_COSTOS);
            SUB_RPT_GASTOS_Y_UTILIDAD_OPERATIVA = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_GASTOS);
            SUB_RPT_GASTOS_FINACNIEROS = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_GASTOS_FINACNIEROS);
            SUB_RPT_OTROS_INGRESOS = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_OTROS_INGRESOS);
            SUB_RPT_OTROS_GASTOS = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_OTROS_GASTOS);
            SUB_RPT_IMPUSTO_Y_UTILIDAD_NETA = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_IMPUSTO_Y_UTILIDAD_NETA);

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void estadoDeresultado(Date fechaInicio, Date fechaFinal) throws ParseException {

        JasperPrint reporte_final;

        String fechaInString = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio);
        Date fechaIn = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInString);

        String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(fechaFinal);
        Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

        System.out.println("fechaIn : " + fechaInString);
        System.out.println("fechaFin : " + fechaFinString);
        Map parametros = new HashMap();

        /*SUBREPORTE COMO PARAMETROS */
        parametros.put("SUB_RPT_INGRESOS", SUB_RPT_INGRESOS);
        parametros.put("SUB_RPT_COSTOS", SUB_RPT_COSTOS_Y_UTILIDAD_BRUTA);
        parametros.put("SUB_RPT_GASTOS", SUB_RPT_GASTOS_Y_UTILIDAD_OPERATIVA);
        parametros.put("SUB_RPT_INGRESOS_FINANCIEROS", SUB_RPT_INGRESOS_FINANCIERO);
        parametros.put("SUB_RPT_GASTOS_FINANCIEROS", SUB_RPT_GASTOS_FINACNIEROS);
        parametros.put("SUB_RPT_OTROS_INGRESOS", SUB_RPT_OTROS_INGRESOS);
        parametros.put("SUB_RPT_OTROS_GASTOS", SUB_RPT_OTROS_GASTOS);
        parametros.put("SUB_RPT_IMPUESTO_Y_UTILIDAD_NETA", SUB_RPT_IMPUSTO_Y_UTILIDAD_NETA);

        /*SUB REPORTE COMO PARAMETROS */
        parametros.put("fecha_desde", fechaIn);
        parametros.put("fecha_hasta", fechaFin);
        parametros.put("unidad_negocio", 5);

//         parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        try {

            reporte_final = JasperFillManager
                    .fillReport(RPT_RPT, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(reporte_final, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParseException {

        getInstancia().estadoDeresultado(new Date("2022/01/01"), new Date("2023/04/30"));
//        getInstancia().balanceGeneral(new Date("2023/12/22"), 3);
    }
}
