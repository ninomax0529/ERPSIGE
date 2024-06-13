/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.reporteUnidadNegocio;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.ReporteUnidadDeNegocio;

import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoReporteUnidadNegocio extends ManejoEstandar<ReporteUnidadDeNegocio> {

    private static ManejoReporteUnidadNegocio manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoReporteUnidadNegocio getInstancia() {
        if (manejo == null) {
            manejo = new ManejoReporteUnidadNegocio();
        }
        return manejo;
    }

    public List<ReporteUnidadDeNegocio> getLista() {

        String query = " SELECT * FROM reporte_unidad_de_negocio ";

        return session.createSQLQuery(query).addEntity(ReporteUnidadDeNegocio.class).list();

    }

    public ReporteUnidadDeNegocio getEstadoProyecto(int codigo) {

        String query = " SELECT * FROM reporte_unidad_de_negocio  where codigo=" + codigo;

        return (ReporteUnidadDeNegocio) session.createSQLQuery(query).addEntity(ReporteUnidadDeNegocio.class).uniqueResult();

    }

    public String getRutaDocumento(int ung, int tipoDoc, int numero) {

        String ruta = "", query = "";

        System.out.println("numero "+numero);
        if (numero > 0) {

            query = "  SELECT ruta_rpt FROM reporte_unidad_de_negocio  rpt  where unidad_negocio=:ung "
                    + "  and tipo_documento=:tipoDoc  and numero=:numero limit 1 ";

            System.out.println("sql 1 " + query);
            ruta = (String) session.createSQLQuery(query)
                    .setParameter("ung", ung)
                    .setParameter("tipoDoc", tipoDoc)
                    .setParameter("numero", numero)
                    .uniqueResult();
        } else {

//            if (ruta == null) {

                query = "  SELECT ruta_rpt FROM reporte_unidad_de_negocio  rpt  where unidad_negocio is null  "
                        + "  and tipo_documento=:tipoDoc   and numero=:numero limit 1 ";

                System.out.println("sql 2 " + query);
                ruta = (String) session.createSQLQuery(query)
                        .setParameter("tipoDoc", tipoDoc)
                        .setParameter("numero", numero)
                        .uniqueResult();

//            }
        }

        return ruta;
    }
    
    
    
    
    public String getRutaDocumento(int tipoDoc, int numero) {

        String ruta = "", query = "";

        System.out.println("numero "+numero);

                query = "  SELECT ruta_rpt FROM reporte_unidad_de_negocio  rpt  where unidad_negocio is null  "
                        + "  and tipo_documento=:tipoDoc   and numero=:numero limit 1 ";

                System.out.println("sql 2 " + query);
                ruta = (String) session.createSQLQuery(query)
                        .setParameter("tipoDoc", tipoDoc)
                        .setParameter("numero", numero)
                        .uniqueResult();

        return ruta;
    }

    public String getRutaSubRptDocumento(int ung, int tipoDoc, int numero) {

        String ruta = "";
        String query = "  SELECT ruta_rpt FROM reporte_unidad_de_negocio  rpt  where unidad_negocio=:ung "
                + "  and tipo_documento=:tipoDoc  and numero=:numero limit 1 ";

        ruta = (String) session.createSQLQuery(query)
                .setParameter("ung", ung)
                .setParameter("tipoDoc", tipoDoc)
                .setParameter("numero", numero)
                .uniqueResult();

        if (ruta == null) {

            query = "  SELECT ruta_rpt FROM reporte_unidad_de_negocio  rpt  where unidad_negocio=:ung "
                    + "  and tipo_documento=:tipoDoc  and numero=:numero limit 1 ";

            ruta = (String) session.createSQLQuery(query)
                    .setParameter("ung", 1)
                    .setParameter("tipoDoc", tipoDoc)
                    .setParameter("numero", 6)
                    .uniqueResult();

        }

        return ruta;
    }

    public String getSubRptRutaDocumento(int ung, int tipoDoc, int numero) {

        String ruta = "";
        String query = "  SELECT ruta_rpt FROM reporte_unidad_de_negocio  rpt  where unidad_negocio=:ung "
                + "  and tipo_documento=:tipoDoc  and numero=:numero limit 1 ";

        ruta = (String) session.createSQLQuery(query)
                .setParameter("ung", ung)
                .setParameter("tipoDoc", tipoDoc)
                .setParameter("numero", numero)
                .uniqueResult();

        return ruta;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return ReporteUnidadDeNegocio.class;
    }

    public static void main(String[] args) {

        System.out.println("Datos " + getInstancia().getRutaDocumento(2, 7, 1));
    }

}
