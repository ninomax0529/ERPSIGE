/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Rpt_acuserecibo
 */
package reporte.inventario;

import java.net.URL;
import java.util.Date;
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
public class RptInventario {

    private URL ruta_maestro;
    private URL ruta_maestro_inventario;
    private URL ruta_movimiento_inv;
    private URL ruta_maestro_inventario_codigo;
    private URL ruta_maestro_lista_precio;
    private URL ruta_maestro_precio_de_lista;
    private URL ruta_img;
    private URL ruta_entrada_inv;
    private URL ruta_costo_inv;
    private URL RURA_SUB_RPT_DETALLE;

    private JasperReport reporte_maestro;
    private JasperReport reporte_maestro_inventario;
    private JasperReport reporte_entrada_inv;
    private JasperReport reporte_movimiento_inv;
    private JasperReport reporte_maestro_lista_precio;
    private JasperReport reporte_maestro_precio_de_lista;
    private JasperReport reporte_maestro_costo_inv;
    private JasperReport reporte_maestro_inventario_codigo;
    private JasperReport SUB_RPT_DETALLE;

    public static RptInventario manager;

    public static RptInventario getInstancia() {
        if (manager == null) {
            manager = new RptInventario();
            return manager;
        }
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptInventario() {

        try {

            ruta_maestro = getClass().getResource("/reporte/inventario/RptInventario.jasper");
            ruta_maestro_inventario = getClass().getResource("/reporte/inventario/RptInventarioInicial.jasper");
            ruta_maestro_inventario_codigo = getClass().getResource("/reporte/inventario/RptInventarioInicialPorCodigo.jasper");
            ruta_maestro_lista_precio = getClass().getResource("/reporte/inventario/RptListaDePrecio.jasper");
            ruta_maestro_precio_de_lista = getClass().getResource("/reporte/inventario/RptPrecioDeLista.jasper");
            ruta_costo_inv = getClass().getResource("/reporte/inventario/RptCostoInventario.jasper");
            ruta_movimiento_inv = getClass().getResource("/reporte/inventario/RptMovimientoArticulo.jasper");

            reporte_maestro = (JasperReport) JRLoader.loadObject(ruta_maestro);

            reporte_maestro_inventario = (JasperReport) JRLoader.loadObject(ruta_maestro_inventario);
            reporte_maestro_inventario_codigo = (JasperReport) JRLoader.loadObject(ruta_maestro_inventario_codigo);
            reporte_maestro_lista_precio = (JasperReport) JRLoader.loadObject(ruta_maestro_lista_precio);
            reporte_maestro_precio_de_lista = (JasperReport) JRLoader.loadObject(ruta_maestro_precio_de_lista);
            reporte_maestro_costo_inv = (JasperReport) JRLoader.loadObject(ruta_costo_inv);
            reporte_movimiento_inv = (JasperReport) JRLoader.loadObject(ruta_movimiento_inv);

            if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 1) {

                ruta_img = getClass().getResource("/imagen/logo_pintura_triplea_v2.png");

            } else if (VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo() == 2) {

                ruta_img = getClass().getResource("/imagen/logo_igh_track.png");
            }

//            ruta_img = getClass().getResource("/imagen/magla_img.jpg");
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void imprimir() {

        JasperPrint reporte_final;
        //
        Map parametros = new HashMap();
        String titulo = "";

        parametros.put("SUBREPORT_DIR", "reporte/factura/");

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

    public void inventarioInicial(Date fecha) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("fecha", fecha);
        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro_inventario, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

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

    public void listaPrecio() {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro_lista_precio, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            ex.printStackTrace();
        }

    }

    public void precioDeLista() {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro_precio_de_lista, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            ex.printStackTrace();
        }

    }

    public void costoInventario() {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro_costo_inv, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            ex.printStackTrace();
        }

    }

    public void inventarioInicial(int inventario) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();
        parametros.put("codigo", inventario);
        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_maestro_inventario_codigo, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            ex.printStackTrace();
        }

    }

    public void movimientoInventario(Date fechaIni, Date fechaFin) {

        JasperPrint reporte_final;

        Map parametros = new HashMap();

        parametros.put("fechaIni", fechaIni);
        parametros.put("fechaFinal", fechaFin);

        parametros.put("url", ruta_img.toString());

        try {

            reporte_final = JasperFillManager.fillReport(reporte_movimiento_inv, parametros, Conexion.getInsatancia().getConnectionDB());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

        getInstancia().inventarioInicial(new Date());
    }
}
