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
import utiles.VariablesGlobales;

/**
 *
 * @author MAXIMILIANO
 */
public class RptEstadoDeSituacion {

    public static RptEstadoDeSituacion manager;

    private URL RUTA_RPT_estado_situacion;
    private URL RUTA_SUB_RPT_ACTI_CIRCULANTE;
    private URL RUTA_SUB_RPT_ACT_FIJO;
    private URL RUTA_SUB_RPT_ACT_DIFERIDO;
    private URL RUTA_SUB_RPT_PASIVO_CIRCULANTE;
    private URL RUTA_SUB_RPT_PASIVO_FIJO;
    private URL RUTA_SUB_RPT_PATRIMONIO;

    private JasperReport RPT_RPT_estado_situacion;
    private JasperReport SUB_RPT_ACT_CIRCULANTE;
    private JasperReport SUB_RPT_ACT_FIJO;
    private JasperReport SUB_RPT_ACT_DIFERIDO;
    private JasperReport SUB_RPT_PASIVO_CIRCULANTE;
    private JasperReport SUB_RPT_PASIVO_FIJO;
    private JasperReport SUB_RPT_PATRIMONIO;
    private JasperReport SUB_RPT_OTROS_GASTOS;
    private JasperReport SUB_RPT_IMPUSTO_Y_UTILIDAD_NETA;

    public static RptEstadoDeSituacion getInstancia() {
        if (manager == null) {
            manager = new RptEstadoDeSituacion();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptEstadoDeSituacion() {

        try {

            RUTA_RPT_estado_situacion = getClass().getResource("deSituacion/RptEstadoDeSituacion_v1.jasper");
            RUTA_SUB_RPT_ACTI_CIRCULANTE = getClass().getResource("deSituacion/subRpt/subActivosCirculantes.jasper");
            RUTA_SUB_RPT_ACT_FIJO = getClass().getResource("deSituacion/subRpt/subActivosFijo.jasper");
            RUTA_SUB_RPT_ACT_DIFERIDO = getClass().getResource("deSituacion/subRpt/subActivosDiferido.jasper");
            
            RUTA_SUB_RPT_PASIVO_CIRCULANTE = getClass().getResource("deSituacion/subRpt/subPasivoCirculantes.jasper");
            RUTA_SUB_RPT_PASIVO_FIJO = getClass().getResource("deSituacion/subRpt/subPasivoNoCirculantes.jasper");
            RUTA_SUB_RPT_PATRIMONIO = getClass().getResource("deSituacion/subRpt/subPatrimonio.jasper");

            RPT_RPT_estado_situacion = (JasperReport) JRLoader.loadObject(RUTA_RPT_estado_situacion);
            SUB_RPT_ACT_CIRCULANTE = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_ACTI_CIRCULANTE);
            SUB_RPT_ACT_FIJO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_ACT_FIJO);
            SUB_RPT_ACT_DIFERIDO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_ACT_DIFERIDO);
            SUB_RPT_PASIVO_CIRCULANTE = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_PASIVO_CIRCULANTE);
            SUB_RPT_PASIVO_FIJO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_PASIVO_FIJO);
            SUB_RPT_PATRIMONIO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_PATRIMONIO);

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void estadoDesituacion(Date fechaFinal) throws ParseException {

        JasperPrint reporte_final;

     
        String fechaFinString = new SimpleDateFormat("yyyy-MM-dd").format(fechaFinal);
        Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinString);

 
        System.out.println("fechaFin : " + fechaFinString);
        Map parametros = new HashMap();

        /*SUBREPORTE COMO PARAMETROS */
        parametros.put("SUB_RPT_ACTIVO_CIRCULANTE", SUB_RPT_ACT_CIRCULANTE);
        parametros.put("SUB_RPT_ACTIVO_FIJO", SUB_RPT_ACT_FIJO);
        parametros.put("SUB_RPT_ACTIVO_DIFERIDO", SUB_RPT_ACT_DIFERIDO);
//
        parametros.put("SUB_RPT_PASIVO_CIRCULANTE", SUB_RPT_PASIVO_CIRCULANTE);
        parametros.put("SUB_RPT_PASIVO_NO_CIRCULANTE", SUB_RPT_PASIVO_FIJO);//
        parametros.put("SUB_RPT_PATRIMONIO", SUB_RPT_PATRIMONIO);


        /*SUB REPORTE COMO PARAMETROS */
        parametros.put("fecha_hasta", fechaFin);
//        parametros.put("unidad_negocio", 5);
        parametros.put("sqlSaldo", " and  saldo<>0 ");        
         parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

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

        getInstancia().estadoDesituacion(new Date("2023/05/30"));
//        getInstancia().balanceGeneral(new Date("2023/12/22"), 3);
    }
}
