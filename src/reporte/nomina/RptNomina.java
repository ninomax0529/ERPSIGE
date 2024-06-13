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
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author maximiliano
 */
public class RptNomina {

    private URL ruta_maestro;
    private URL ruta_img;
    private JasperReport reporte_maestro;

    public static RptNomina manager;

    public static RptNomina getInstancia() {
        if (manager == null) {
            manager = new RptNomina();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptNomina() {

        try {

            ruta_maestro = getClass().getResource("RptNomina.jasper");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);
           

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void verReporte(int nom) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("codigo", nom);
        parametros.put("unidad_negocio",VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
     
       try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;
            JasperViewer.viewReport(jasperPrint, false);
            
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }
    
    
       public void verReporte(int nom, int empleado) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("nomina", nom);
        parametros.put("empleado", empleado);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;
            JasperViewer.viewReport(jasperPrint, false);
            
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        getInstancia().verReporte(7);
    }
}
