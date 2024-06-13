/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.articulo;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.ArticuloUnidad;
import modelo.Unidad;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoArticuloUnidad extends ManejoEstandar<ArticuloUnidad> {

    private static ManejoArticuloUnidad manejoArticuloUnidad = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoArticuloUnidad getInstancia() {
        if (manejoArticuloUnidad == null) {
            manejoArticuloUnidad = new ManejoArticuloUnidad();
        }
        return manejoArticuloUnidad;
    }

    public List<ArticuloUnidad> getLista() {

        String query = " SELECT * FROM articulo_unidad ";

        session.clear();
        return session.createSQLQuery(query).addEntity(ArticuloUnidad.class).list();

    }

    public List<ArticuloUnidad> getListaUnidadSslida(int articulo) {

        String query = " SELECT * FROM articulo_unidad  where articulo=:articulo  order by  cantidad_unidades; ";

        session.clear();
        return session.createSQLQuery(query)
                .addEntity(ArticuloUnidad.class)
                .setParameter("articulo", articulo)
                .list();

    }

    public ArticuloUnidad getArticuloUnidadSslida(int articulo) {

        String query = " SELECT * FROM articulo_unidad  where articulo=:articulo and unidad_inventario=true limit 1 ";

        System.out.println("query " + query);
        getSession().clear();
        return (ArticuloUnidad) session.createSQLQuery(query)
                .addEntity(ArticuloUnidad.class)
                .setParameter("articulo", articulo)
                .uniqueResult();

    }

    public ArticuloUnidad getArticuloUnidadSslida(int articulo, int unidad) {

        System.out.println("Unidad : " + unidad + " articulo " + articulo);
        String query = " SELECT * FROM articulo_unidad  where articulo=:articulo and unidad=:unidad ";

        getSession().clear();
        return (ArticuloUnidad) session.createSQLQuery(query)
                .addEntity(ArticuloUnidad.class)
                .setParameter("articulo", articulo)
                .setParameter("unidad", unidad).uniqueResult();

    }

    public ArticuloUnidad getArticuloUnidadEntrada(int articulo, int unidadEntrada) {

        System.out.println("Unidad : " + unidadEntrada + " articulo " + articulo);
        String query = " SELECT * FROM articulo_unidad  where articulo=:articulo and unidad=:unidad ";

        getSession().clear();
        return (ArticuloUnidad) session.createSQLQuery(query)
                .addEntity(ArticuloUnidad.class)
                .setParameter("articulo", articulo)
                .setParameter("unidad", unidadEntrada).uniqueResult();

    }

    public Double getFactorVentaDeArticulo(int articulo, int almacen, int unidad, int listaPrecio) {

        Double existenciaArticulo;

        String query = "   SELECT factor_venta "
                + " from lista_de_precio lp,detalle_lista_de_precio dlp,\n"
                + "  unidad u,articulo_unidad au,existencia_articulo ea\n"
                + " where  lp.codigo=dlp.lista_de_precio \n"
                + "  and  u.codigo=dlp.unidad_salida\n"
                + "  and u.codigo=au.unidad \n"
                + " and au.articulo=dlp.articulo\n"
                + "  and  lp.habilitada=true\n"
                + " and ea.articulo=au.articulo\n"
                + " and dlp.articulo=:articulo   and au.unidad=:unidad and lp.codigo=:listaPrecio  and almacen=:almacen ";

        System.out.println("SQL " + query);

        session.clear();
        existenciaArticulo = (Double) session.createSQLQuery(query)
                .setParameter("articulo", articulo)
                .setParameter("unidad", unidad)
                .setParameter("listaPrecio", listaPrecio)
                .setParameter("almacen", almacen)
                .uniqueResult();

        existenciaArticulo = existenciaArticulo == null ? 0.00 : existenciaArticulo;

        return existenciaArticulo;
    }

    public void eliminar(ArticuloUnidad ded) {

        try {

            session.merge(ded);
            session.delete(ded);
            session.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return ArticuloUnidad.class;
    }

    public static void main(String[] args) {

        System.out.println("Datos " + getInstancia().getListaUnidadSslida(1));
    }

}
