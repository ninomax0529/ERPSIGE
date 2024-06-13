/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.nomina;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
public class RptVolanteDePago {

    private URL ruta_maestro;
    private URL ruta_img;
    private JasperReport reporte_maestro;

    public static RptVolanteDePago manager;

    public static RptVolanteDePago getInstancia() {
        if (manager == null) {
            manager = new RptVolanteDePago();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptVolanteDePago() {

        try {

            ruta_maestro = getClass().getResource("RptVolanteDePago.jasper");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir(int nom, int empleado) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("nomina", nom);
        parametros.put("empleado", empleado);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrintManager.printReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void verReriboDePago(int nom, int empleado) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("nomina", nom);
        parametros.put("empleado", empleado);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        getInstancia().imprimir(5, 3);
    }
}
