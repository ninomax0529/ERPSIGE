/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.cliente;

import java.net.URL;
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
public class RptClientes {

    private URL ruta_maestro;
    private URL ruta_img;
    private URL urlsub;
    public static RptClientes manager;
    private JasperReport reporte_maestro;

    public static RptClientes getInstancia() {
        if (manager == null) {
            manager = new RptClientes();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptClientes() {

        try {

            ruta_maestro = getClass().getResource("/reporte/cliente/RptClientes.jasper");

            ruta_img = getClass().getResource("/imagen/magla_img.jpg");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void imprimir() {

        JasperPrint reporte_final;
        //

        if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {

            ruta_img = getClass().getResource("/imagen/logo_pintura_triplea_v2.png");

        } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {

            ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
        }

        Map parametros = new HashMap();
        String titulo = "";
        parametros.put("url", ruta_img.toString());
        parametros.put("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, Conexion.getInsatancia().getConnectionDB());

//            JasperViewer.viewReport(reporte_final, false);
            JasperPrint jasperPrint = reporte_final;

            int op;
//            op = JOptionPane.showConfirmDialog(null, "Desea imprimir el reporte?", "Facturacion", JOptionPane.YES_NO_OPTION);
//            if (op == 0) {

//            JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer.viewReport(jasperPrint, false);
//            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

        getInstancia().imprimir();
    }
}
