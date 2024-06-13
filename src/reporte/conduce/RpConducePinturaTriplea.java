/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.conduce;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import manejo.ManejoConfiguracion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author maximiliano
 */
public class RpConducePinturaTriplea {

    public static RpConducePinturaTriplea manager;

    //**************Reporte*********************** 
    private JasperReport SUB_RPT_DETALLE_CONDUCE;
    private JasperReport reporte_maestro;

    //Cierre declaracion reporte 
//*********************Ruta************************
    private URL RUTA_SUB_RPT_DETALLE_CONDUCE;
    private URL ruta_maestro_original;
    private URL ruta_img;

//Cierre declaracion ruta 
    public static RpConducePinturaTriplea getInstancia() {

        if (manager == null) {

            manager = new RpConducePinturaTriplea();
            return manager;
        }
        return manager;
    }

//    private JasperReport SUB_RPT_DETALLE_ORDEN;
    /**
     * Constructor de ManejoImpresion
     */
    public RpConducePinturaTriplea() {

        try {

            ruta_maestro_original = getClass().getResource("/reporte/conduce/RptConduceClientePinturaTriplea.jasper");
            RUTA_SUB_RPT_DETALLE_CONDUCE = getClass().getResource("/reporte/conduce/subRptDetalleConducePinturaTriplea.jasper");

            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro_original);
            SUB_RPT_DETALLE_CONDUCE = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE_CONDUCE);

            ruta_img = getClass().getResource("/imagen/logo_pintura_triplea_v2.png");

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(int cotizacion, int formato) {

        Boolean impresionDirecta = ManejoConfiguracion.getInstancia().getConfiguracion().getImpresionDirecta();
        JasperPrint reporte_final = null;
        //
        Map parametros = new HashMap();

        parametros.put("factura", cotizacion);

        parametros.put("url", ruta_img.toString());

        try {

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_CONDUCE);

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

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


    public void verConduce(int conduce) {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();

        try {

            parametros.put("url", ruta_img.toString());

            parametros.put("conduce", conduce);
            parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
            parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());

            parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE_CONDUCE);

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        getInstancia().verConduce(6);
    }
}
