/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.gestionDeFlota;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.ClaseUtil;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author maximiliano
 */
public class RptFlota {

    private URL ruta_maestro;
    private URL ruta_img;
    private JasperReport reporte_maestro;
    private JasperReport reporte_flota;
    private URL ruta_flota;

    public static RptFlota manager;

    public static RptFlota getInstancia() {
        if (manager == null) {
            manager = new RptFlota();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptFlota() {

        try {

            ruta_maestro = getClass().getResource("RptAsignacionDeFlota.jasper");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);

            ruta_flota = getClass().getResource("RptRelacionAsignacionDeFlota.jasper");
            reporte_flota = (JasperReport) JRLoader.loadObject(ruta_flota);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(String sqlParam, String criterio) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

//        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        parametros.put("unidad_negocio", 2);
        parametros.put("sqlParam", sqlParam);
        parametros.put("sqlCriterio", criterio);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_flota, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperViewer.viewReport(reporte_final, false);

//            JasperPrintManager.printReport(reporte_final, false);
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void verAsignacion(int codAsignacion) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("numeroAsignacion", codAsignacion);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
//        parametros.put("unidad_negocio", 2);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

        Date fi = new Date("2023/03/01"),
                ff = new Date("2023/03/22");

        String sqlParam = " and  af.fecha between '" + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "'  and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'  ";
        getInstancia().imprimir(sqlParam,sqlParam);
    }
}
