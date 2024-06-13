/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.notaDebito;

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
public class RptNotaDebito {

    private URL ruta_nd_suplidor;
    private URL ruta_nd_cliente;
    private URL ruta_img;
    private JasperReport reporte_nd_suplidor;
    private JasperReport reporte_nd_cliente;

    public static RptNotaDebito manager;

    public static RptNotaDebito getInstancia() {
        if (manager == null) {
            manager = new RptNotaDebito();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptNotaDebito() {

        try {

            ruta_nd_suplidor = getClass().getResource("RptNotaDebitoSuplidor.jasper");
            reporte_nd_suplidor = (JasperReport) JRLoader.loadObject(ruta_nd_suplidor);

            ruta_nd_cliente = getClass().getResource("RptNotaDebitoCliente.jasper");
            reporte_nd_cliente = (JasperReport) JRLoader.loadObject(ruta_nd_cliente);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void verNotaDebitoSuplidor(int nd) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("nota_debito", nd);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

//        parametros.put("unidad_negocio", 2);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_nd_suplidor, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void verNotaDebitoCliente(int nd) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("nota_debito", nd);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

//        parametros.put("unidad_negocio", 2);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_nd_cliente, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        getInstancia().verNotaDebitoCliente(7);
    }
}
