/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.reconciliacion;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author maximiliano
 */
public class RptReconciliacionInterna {

    public static RptReconciliacionInterna manager;

    //**************Reporte*********************** 
    private JasperReport RPT_RECONCI_JASPER;
    private JasperReport SUB_RPT_DETALLE_RECONCI;

    //Cierre declaracion reporte 
//*********************Ruta************************
    private URL RUTA_RECONCI;
    private URL RPT_RUTA_RECONCI;

//Cierre declaracion ruta 
    public static RptReconciliacionInterna getInstancia() {

        if (manager == null) {

            manager = new RptReconciliacionInterna();
            return manager;
        }
        return manager;
    }

//    private JasperReport SUB_RPT_DETALLE_ORDEN;
    /**
     * Constructor de ManejoImpresion
     */
    public RptReconciliacionInterna() {

        try {

            RUTA_RECONCI = getClass().getResource("/reporte/reconciliacion/RptReconciliacionInterna.jasper");
            RPT_RECONCI_JASPER = (JasperReport) JRLoader.loadObject(RUTA_RECONCI);

            RPT_RUTA_RECONCI = getClass().getResource("/reporte/reconciliacion/subRptDetalleReconciliacionInterna.jasper");
            SUB_RPT_DETALLE_RECONCI = (JasperReport) JRLoader.loadObject(RPT_RUTA_RECONCI);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void verReporte(int reconci) {

        try {

            JasperPrint jasperPrint;
            Map parametros = new HashMap();

            parametros.put("reconciliacion", reconci);
//             parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
            parametros.put("unidad_negocio",2);
            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_RECONCI);

            jasperPrint = JasperFillManager.fillReport(RPT_RECONCI_JASPER, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void verReporte(Date fi, Date ff) {

        try {

            JasperPrint jasperPrint;
            Map parametros = new HashMap();

            parametros.put("fecha_desde", fi);
            parametros.put("fecha_hasta", ff);
//           parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
            parametros.put("unidad_negocio", 2);

            jasperPrint = JasperFillManager.fillReport(RPT_RECONCI_JASPER, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

//        getInstancia().verReporte(new Date("2022/01/01"), new Date("2023/07/20"));
        getInstancia().verReporte(3);
    }
}
