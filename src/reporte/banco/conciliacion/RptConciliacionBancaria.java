/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte.banco.conciliacion;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import manejo.HibernateUtil;
import modelo.ConciliacionBancaria;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import util.ClaseUtil;

/**
 *
 * @author MAXIMILIANO
 */
public class RptConciliacionBancaria {

    private URL ruta_maestro;

    private URL ruta_img;
    private URL ruta_img_proyecto;
    private URL urlsub;
    private URL ruta_conciliacion;
    private URL RUTA_SUB_RPT_MAS_BANCO;
    private URL RUTA_SUB_RPT_MAS_LIBRO;
    private URL RUTA_SUB_RPT_MENOS_BANCO;
    private URL RUTA_SUB_RPT_MENOS_LIBRO;
    public static RptConciliacionBancaria manager;
    private JasperReport reporte_maestro_relacion;
    private JasperReport reporte_maestro_conciliacion;
    private JasperReport SUB_RPT_MAS_BANCO;
    private JasperReport SUB_RPT_MAS_LIBRO;
    private JasperReport SUB_RPT_MENOS_BANCO;
    private JasperReport SUB_RPT_MENOS_LIBRO;

    public static Session session = HibernateUtil.getSession();

    public static RptConciliacionBancaria getInstancia() {
        if (manager == null) {
            manager = new RptConciliacionBancaria();
            return manager;
        }

        session = HibernateUtil.getSession();
        return manager;
    }

    /**
     * Constructor de ManejoImpresion
     */
    public RptConciliacionBancaria() {

        try {

            ruta_conciliacion = getClass().getResource("RptConciliacionBan.jasper");

            RUTA_SUB_RPT_MAS_BANCO = getClass().getResource("/reporte/banco/conciliacion/subRptConciliacionBanco.jasper");
            RUTA_SUB_RPT_MAS_LIBRO = getClass().getResource("/reporte/banco/conciliacion/subRptConciliacionLibro.jasper");
            RUTA_SUB_RPT_MENOS_BANCO = getClass().getResource("/reporte/banco/conciliacion/subRptConciliacionMenosBanco.jasper");
            RUTA_SUB_RPT_MENOS_LIBRO = getClass().getResource("/reporte/banco/conciliacion/subRptConciliacionMenosLibro.jasper");
            ruta_img = getClass().getResource("/imagen/logo_fsc_uv.PNG");
            ruta_img_proyecto = getClass().getResource("/imagen/logo_fsc_uv.PNG");

            reporte_maestro_conciliacion = (JasperReport) JRLoader.loadObject(ruta_conciliacion);
            SUB_RPT_MAS_BANCO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_MAS_BANCO);
            SUB_RPT_MAS_LIBRO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_MAS_LIBRO);
            SUB_RPT_MENOS_BANCO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_MENOS_BANCO);
            SUB_RPT_MENOS_LIBRO = (JasperReport) JRLoader.loadObject(RUTA_SUB_RPT_MENOS_LIBRO);

        } catch (JRException ex) {

            ex.printStackTrace();
        }
    }

    public void imprimirConciliacion(ConciliacionBancaria conciliacion) {

        JasperPrint reporte_final;
        String mes = conciliacion.getNombreMes().toUpperCase(),
                banco = conciliacion.getNombreBanco();
        int anio = ClaseUtil.getAno(conciliacion.getFechaHasta());
        int ung=conciliacion.getUnidadDeNegocio().getCodigo();

        Map parametros = new HashMap();

        parametros.put("numero", conciliacion.getCodigo());
        parametros.put("url", ruta_img.toString());
        parametros.put("BANCO_MAS", SUB_RPT_MAS_BANCO);
        parametros.put("LIBRO_MAS", SUB_RPT_MAS_LIBRO);
        parametros.put("BANCO_MENOS", SUB_RPT_MENOS_BANCO);
        parametros.put("LIBRO_MENOS", SUB_RPT_MENOS_LIBRO);
        parametros.put("tituto", banco);
        parametros.put("unidad_negocio", ung);
        parametros.put("subTitulo", "CORRESPONDIENTE AL MES DE " + mes + " DEL AÑO " + anio);

        SessionImplementor miSessionImplementor = (SessionImplementor) session;

        //aqui coloca la ruta donde se encuentra el archivo del o los subreporte  
//        parametros.put("SUBREPORT_DIR", getClass().getResource("/Reportes/Mina/").toString());
        try {

            reporte_final = JasperFillManager
                    .fillReport(reporte_maestro_conciliacion, parametros, miSessionImplementor.connection());

            JasperPrint jasperPrint = reporte_final;

            JasperViewer.viewReport(reporte_final, false);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

//        getInstancia().imprimirConciliacion(2);
//        NumeroALetra numeroALetra = new NumeroALetra();
//        String montoLEnLetra = numeroALetra.Convertir("2250.26", true);
//        getInstancia().imprimirCheque(9, montoLEnLetra);
    }
}
