/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.unidadnegocio;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import manejo.ManejoConfiguracion;
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
public class RptContrato {

    public static RptContrato manager;

    //**************Reporte*********************** 
    private JasperReport RPT_CONTRATO_JASPER;

    //Cierre declaracion reporte 
//*********************Ruta************************
    private URL RUTA_CONTRATO;

//Cierre declaracion ruta 
    public static RptContrato getInstancia() {

        if (manager == null) {

            manager = new RptContrato();
            return manager;
        }
        return manager;
    }

//    private JasperReport SUB_RPT_DETALLE_ORDEN;
    /**
     * Constructor de ManejoImpresion
     */
    public RptContrato() {

        try {

            RUTA_CONTRATO = getClass().getResource("/reporte/unidadnegocio/ightrack/contrato/jasper/RptContatoSuspendidoEntreFecha.jasper");
            RPT_CONTRATO_JASPER = (JasperReport) JRLoader.loadObject(RUTA_CONTRATO);

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
            parametros.put("unidad_negocio",2);

            jasperPrint = JasperFillManager.fillReport(RPT_CONTRATO_JASPER, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

        getInstancia().verReporte(new Date("2022/01/01"), new Date("2023/07/20"));
//        getInstancia().verFacturaDetAgrupado(1129);
    }
}
