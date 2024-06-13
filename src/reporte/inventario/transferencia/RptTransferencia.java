/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.inventario.transferencia;

import java.net.URL;
import java.sql.Connection;
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
public class RptTransferencia {

    private URL ruta_maestro;

    private URL ruta_maestro_libro_mayor;
    private URL ruta_maestro_libro_mayor_por_cuenta;
    private URL ruta_img;
    private URL urlsub;
    private URL ruta_balnaza_comprobacion;
    private URL ruta_img_proyecto;
    private URL RUTA_SUB_RPT_DETALLE;
    public static RptTransferencia manager;
    private JasperReport reporte_maestro;
    private JasperReport reporte_maestro_balanza_de_comprobacion;
    private JasperReport reporte_maestro_libro_mayor;
    private JasperReport reporte_maestro_libro_mayor_por_cuenta;
    private JasperReport SUB_RPT_DETALLE;

    public static RptTransferencia getInstancia() {
        if (manager == null) {
            manager = new RptTransferencia();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptTransferencia() {

        try {

            ruta_maestro = getClass().getResource("RptTransferenciaPinturaTriplea.jasper");
            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);
            RUTA_SUB_RPT_DETALLE = getClass().getResource("subRptDetalletTransferecniaPinturaTriplea.jasper");
            SUB_RPT_DETALLE = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE);

            ruta_img = getClass().getResource("/imagen/logo_pintura_triplea_v2.png");

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void imprimir(Integer trans) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {

            ruta_img = getClass().getResource("/imagen/logo_pintura_triplea_v2.png");

        } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {

            ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
        }

        parametros.put("transferencia", trans);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());
        parametros.put("url", ruta_img.toString());
        parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE);

        try {

            Connection con;
            con = Conexion.getInsatancia().getConnectionDB();
            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, con);

            JasperViewer.viewReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        RptTransferencia.getInstancia().imprimir(1);
    }
}
