/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.notaCredito;

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
public class RptNotaCredito {

    private URL ruta_nc_suplidor;
    private URL ruta_nc_cliente;
    private URL ruta_img;
    private JasperReport reporte_nc_suplidor;
    private JasperReport reporte_nc_cliente;

    public static RptNotaCredito manager;

    public static RptNotaCredito getInstancia() {
        if (manager == null) {
            manager = new RptNotaCredito();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptNotaCredito() {

        try {

            ruta_nc_suplidor = getClass().getResource("RptNotaCreditoSuplidor.jasper");
            reporte_nc_suplidor = (JasperReport) JRLoader.loadObject(ruta_nc_suplidor);

            ruta_nc_cliente = getClass().getResource("RptNotaCreditoCliente.jasper");
            reporte_nc_cliente = (JasperReport) JRLoader.loadObject(ruta_nc_cliente);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void verNotaCreditoSuplidor(int nc) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("nota_credito", nc);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

//        parametros.put("unidad_negocio", 5);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_nc_suplidor, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void verNotaCreditoCliente(int nc) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("nota_credito", nc);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

//        parametros.put("unidad_negocio", 2);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_nc_cliente, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        getInstancia().verNotaCreditoCliente(8);
    }
}
