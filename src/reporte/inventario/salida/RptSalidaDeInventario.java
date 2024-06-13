/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.inventario.salida;

import java.net.URL;
import java.sql.Connection;
import java.util.Date;
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
public class RptSalidaDeInventario {

    private URL ruta_maestro;

    private URL ruta_maestro_libro_mayor;
    private URL ruta_maestro_libro_mayor_por_cuenta;
    private URL ruta_img;
    private URL urlsub;
    private URL ruta_img_proyecto;
    private URL ruta_entrada_articulo;
    private URL RUTA_SUB_RPT_DETALLE;
    private URL ruta_entrada_inv;
    private URL RURA_SUB_RPT_DETALLE;
    public static RptSalidaDeInventario manager;
    private JasperReport reporte_maestro;
    private JasperReport reporte_entrada_inv;
    private JasperReport reporte_entrada_articulo;
    private JasperReport reporte_maestro_libro_mayor;
    private JasperReport reporte_maestro_libro_mayor_por_cuenta;
    private JasperReport SUB_RPT_DETALLE;

    public static RptSalidaDeInventario getInstancia() {
        if (manager == null) {
            manager = new RptSalidaDeInventario();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptSalidaDeInventario() {

        try {

            ruta_maestro = getClass().getResource("RptSalidaDeInventario.jasper");
            RUTA_SUB_RPT_DETALLE = getClass().getResource("subRptDetalleSalidaInventario.jasper");

            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);

            SUB_RPT_DETALLE = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_DETALLE);

            ruta_img = getClass().getResource("/imagen/logo_pintura_triplea_v2.png");

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void imprimir(Integer salida) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("salida", salida);
        parametros.put("unidad_negocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        parametros.put("empresa", VariablesGlobales.USUARIO.getUnidadDeNegocio().getEmpresaOGrupo().getCodigo());
        parametros.put("url", ruta_img.toString());
        parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE);
        Connection con;
        con = Conexion.getInsatancia().getConnectionDB();

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro, parametros, con);

            JasperViewer.viewReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void entradaInventario(Date fechaIni, Date fechaFin) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();
        parametros.put("fechaini", fechaIni);
        parametros.put("fechafin", fechaFin);
        parametros.put("url", ruta_img.toString());
        parametros.put("SUB_RPT_DETALLE", SUB_RPT_DETALLE);

        try {

            reporte_final = JasperFillManager.fillReport(reporte_entrada_inv, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            ex.printStackTrace();
        }

    }

    public void entradaInventario(Date fechaIni, Date fechaFin, int articulo) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();
        parametros.put("fechaini", fechaIni);
        parametros.put("fechafin", fechaFin);
        parametros.put("codigo", articulo);
        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_entrada_articulo, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

        getInstancia().imprimir(1);
//        getInstancia().entradaInventario(new Date("2020/10/01"), new Date("2020/10/29"), 120);
    }
}
