/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.articulo;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DetalleAjusteInventario;
import modelo.DetalleListaDePrecio;
import modelo.ListaDePrecio;

import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoListaDePrecio extends ManejoEstandar<ListaDePrecio> {

    private static ManejoListaDePrecio manejoListaDePrecio = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoListaDePrecio getInstancia() {
        if (manejoListaDePrecio == null) {
            manejoListaDePrecio = new ManejoListaDePrecio();
        }
        return manejoListaDePrecio;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<ListaDePrecio> getListaDePrecio() {

        String query = " SELECT * FROM lista_de_precio" ;

        return session.createSQLQuery(query).addEntity(ListaDePrecio.class).list();

    }

//    public List<ListaDePrecio> getListaDePrecio(int codigo) {
//
//        String query = "SELECT * FROM  lista_de_precio  where codigo=:codigo";
//
//        return session.createSQLQuery(query).addEntity(ListaDePrecio.class).setParameter("codigo", codigo).list();
//
//    }
    public Boolean existeArticuloConMinimoYMaximo(int articulo, double minimo, double maximo) {

        Boolean existe = false;

        String query = " SELECT dlp.* from lista_de_precio lp,detalle_lista_de_precio dlp where lp.codigo=dlp.lista_de_precio "
                + " and  lp.habilitada=true and  articulo=:articulo and  cantidad_minima>=:minima  and  cantidad_maxima<=:maxima limit 1";

        existe = session.createSQLQuery(query)
                .setParameter("articulo", articulo)
                .setParameter("minima", minimo)
                .setParameter("maxima", maximo)
                .uniqueResult() == null ? false : true;

        return existe;
    }

    public ListaDePrecio getListaDePrecio(int listaDePrecio) {

        String query = "SELECT * FROM  lista_de_precio  where tipo_venta=4  and  codigo=:codigo ";

        return (ListaDePrecio) session
                .createSQLQuery(query)
                .addEntity(ListaDePrecio.class)
                .setParameter("codigo", listaDePrecio)
                .uniqueResult();

    }

    public ListaDePrecio getListaDePrecioGeneral() {

        String query = "SELECT * FROM  lista_de_precio  where tipo_venta=4  ";

        return (ListaDePrecio) session
                .createSQLQuery(query)
                .addEntity(ListaDePrecio.class)
                .uniqueResult();

    }

    public ListaDePrecio getListaDePrecioGeneral(int tipoVenta) {

        String query = "SELECT * FROM  lista_de_precio  where tipo_venta=:tipoVenta  ";

        return (ListaDePrecio) session
                .createSQLQuery(query)
                .addEntity(ListaDePrecio.class)
                .setParameter("tipoVenta", tipoVenta)
                .uniqueResult();

    }

    public List<DetalleListaDePrecio> getDetalleListaDePrecio(int cdigoLista) {

        String query = " SELECT * FROM  detalle_lista_de_precio  where lista_de_precio=:codigo and unidad_de_negocio=:ug ";

        return session.createSQLQuery(query)
                .addEntity(DetalleListaDePrecio.class)
                .setParameter("codigo", cdigoLista)
                .setParameter("ug", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list();

    }

    public List<DetalleListaDePrecio> getArticuloDetalleListaDePrecio(int tipoart, int categoria, int subCategoria, int unidad, int calidad) {

        String query = " SELECT * FROM  detalle_lista_de_precio \n"
                + "\n"
                + " where lista_de_precio=1\n"
                + "\n"
                + "and articulo in\n"
                + "\n"
                + "(\n"
                + "SELECT a.codigo from articulo a\n"
                + "\n"
                + "inner JOIN articulo_unidad au on  a.codigo=au.articulo\n"
                + "\n"
                + " where tipo_articulo=" + tipoart
                + "\n"
                + " and categoria=" + categoria
                + " and  sub_categoria=" + subCategoria
                + " and au.unidad=" + unidad
                + "  and calidad_pintura=" + calidad
                + ")\n"
                + "";

//        String query = " SELECT * FROM  detalle_lista_de_precio \n"
//                + "\n"
//                + " where lista_de_precio=1\n"
//                + "\n"
//                + "and articulo in\n"
//                + "\n"
//                + "(\n"
//                + "SELECT a.codigo from articulo a\n"
//                + "\n"
//                + "inner JOIN articulo_unidad au on  a.codigo=au.articulo\n"
//                + "\n"
//                + " where tipo_articulo=:tipo \n"
//               
//                + "\n"
//                + " and categoria=:categoria\n"
//                + "and  sub_categoria=:subCategoria\n"
//                + "and au.unidad=:unidad\n"
//                + "\n" 
//                + ")\n"
//                + "";
        System.out.println("sQL " + query);

        return session.createSQLQuery(query)
                .addEntity(DetalleListaDePrecio.class)
                /*   .setParameter("tipo", tipoart)
              .setParameter("categoria", subCategoria)
                .setParameter("subCategoria", subCategoria)
                .setParameter("unidad", unidad)*/
                .list();

    }

    public List<DetalleListaDePrecio> getListaPrecioPorArticulo(int articulo) {

        String query = " SELECT * FROM  lista_de_precio  where articulo=:articulo";

        return session.createSQLQuery(query)
                .addEntity(DetalleListaDePrecio.class)
                .setParameter("articulo", articulo).list();

    }

    public DetalleListaDePrecio getDetalleListaDePrecio(int articulo, double cantidad) {

        DetalleListaDePrecio detalleListaDePrecio = null;

        String query = " SELECT dlp.* from lista_de_precio lp,detalle_lista_de_precio dlp where lp.codigo=dlp.lista_de_precio "
                + " and  lp.habilitada=true and  articulo=:articulo "
                + "  and  :cantidad between cantidad_minima and  cantidad_maxima limit 1 ";

        System.out.println("SQL : " + query);

        detalleListaDePrecio = (DetalleListaDePrecio) session.createSQLQuery(query)
                .addEntity(DetalleListaDePrecio.class)
                .setParameter("articulo", articulo)
                .setParameter("cantidad", cantidad)
                .uniqueResult();

        if (detalleListaDePrecio == null) {

            return getDetalleListaDePrecioGeneral(articulo, 4);

        }

        return detalleListaDePrecio;
    }

    public DetalleListaDePrecio getDetalleListaDePrecio(int articulo, int unidad, double cantidad) {

        System.out.println("Unidad : " + unidad);
        DetalleListaDePrecio detalleListaDePrecio = null;

        String query = " SELECT dlp.* from lista_de_precio lp,detalle_lista_de_precio dlp where lp.codigo=dlp.lista_de_precio "
                + "  and lp.habilitada=true and  articulo=:articulo  and  unidad_salida=:unidad "
                + "  and  :cantidad  between cantidad_minima and  cantidad_maxima  and dlp.unidad_de_negocio=:unidadNegocio  limit 1 ";

        System.out.println("SQL : " + query);

        detalleListaDePrecio = (DetalleListaDePrecio) session.createSQLQuery(query)
                .addEntity(DetalleListaDePrecio.class)
                .setParameter("articulo", articulo)
                .setParameter("unidad", unidad)
                .setParameter("cantidad", cantidad)
                .setParameter("unidadNegocio", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .uniqueResult();

        if (detalleListaDePrecio == null) {

            return getDetalleListaDePrecioGeneral(articulo, unidad, 4);

        }

        return detalleListaDePrecio;
    }

    public DetalleListaDePrecio getDetalleListaDePrecio(int articulo, int unidad) {

        System.out.println("Unidad : " + unidad);
        DetalleListaDePrecio detalleListaDePrecio = null;

        String query = " SELECT dlp.* from lista_de_precio lp,detalle_lista_de_precio dlp where lp.codigo=dlp.lista_de_precio "
                + " and  lp.habilitada=true and  articulo=:articulo  and  unidad_salida=:unidad "
                + " limit 1 ";

        System.out.println("SQL : " + query);

        detalleListaDePrecio = (DetalleListaDePrecio) session.createSQLQuery(query)
                .addEntity(DetalleListaDePrecio.class)
                .setParameter("articulo", articulo)
                .setParameter("unidad", unidad)
                .uniqueResult();

        if (detalleListaDePrecio == null) {

            return getDetalleListaDePrecioGeneral(articulo, unidad, 4);

        }

        return detalleListaDePrecio;
    }

    public DetalleListaDePrecio getDetalleListaDePrecioGeneral(int articulo, int tipoVenta) {

        DetalleListaDePrecio detalleListaDePrecio = null;

        String query = " SELECT dlp.* from lista_de_precio lp,detalle_lista_de_precio dlp  where  lp.codigo=dlp.lista_de_precio  "
                + "   and  lp.habilitada=true and  articulo=:articulo and  lp.tipo_venta=:tipoVenta   limit 1 ";

        detalleListaDePrecio = (DetalleListaDePrecio) session
                .createSQLQuery(query)
                .addEntity(DetalleListaDePrecio.class)
                .setParameter("articulo", articulo)
                .setParameter("tipoVenta", tipoVenta)
                .uniqueResult();

        return detalleListaDePrecio;
    }

    public DetalleListaDePrecio getDetalleListaDePrecioGeneral(int articulo, int unidad, int tipoVenta) {

        DetalleListaDePrecio detalleListaDePrecio = null;

        String query = " SELECT dlp.* from lista_de_precio lp,detalle_lista_de_precio dlp where lp.codigo=dlp.lista_de_precio "
                + " and  lp.habilitada=true and  articulo=:articulo and  lp.tipo_venta=:tipoVenta  and  unidad_salida=:unidad  limit 1";

        detalleListaDePrecio = (DetalleListaDePrecio) session
                .createSQLQuery(query)
                .addEntity(DetalleListaDePrecio.class)
                .setParameter("articulo", articulo)
                .setParameter("unidad", unidad)
                .setParameter("tipoVenta", tipoVenta)
                .uniqueResult();

        return detalleListaDePrecio;
    }

    public Double getPrecioVentaDeArticulo(int articulo, int unidad) {

        Double existenciaArticulo;

        String query = "   SELECT dlp.precio "
                + " from lista_de_precio lp,detalle_lista_de_precio dlp,\n"
                + "  unidad u,articulo_unidad au "
                + " where  lp.codigo=dlp.lista_de_precio \n"
                + "  and  u.codigo=dlp.unidad_salida\n"
                + "  and u.codigo=au.unidad \n"
                + " and au.articulo=dlp.articulo\n"
                + "  and  lp.habilitada=true\n"
                + " and dlp.articulo=:articulo   and  au.unidad=:unidad and lp.tipo_venta=4 ";

        System.out.println("SQL " + query);
        existenciaArticulo = (Double) session.createSQLQuery(query)
                .setParameter("articulo", articulo)
                .setParameter("unidad", unidad)
                .uniqueResult();

        existenciaArticulo = existenciaArticulo == null ? 0.00 : existenciaArticulo;

        return existenciaArticulo;
    }

    public Double getDetalleListaDePrecioPorArticulo(int articulo, int tipoVenta) {

        DetalleListaDePrecio detalleListaDePrecio = null;

        String query = " SELECT dlp.* from lista_de_precio lp,detalle_lista_de_precio dlp where lp.codigo=dlp.lista_de_precio "
                + " and  lp.habilitada=true and  articulo=:articulo and  lp.tipo_venta=:tipoVenta   limit 1";

        detalleListaDePrecio = (DetalleListaDePrecio) session
                .createSQLQuery(query)
                .addEntity(DetalleListaDePrecio.class)
                .setParameter("articulo", articulo)
                .setParameter("tipoVenta", tipoVenta)
                .uniqueResult();

        System.out.println(" detalleListaDePrecio : " + detalleListaDePrecio + " articulo " + articulo + " tipoVenta : " + tipoVenta);
        return detalleListaDePrecio == null ? 0.00 : detalleListaDePrecio.getPrecio();
    }

    public static void main(String[] args) {

//        System.out.println("Existe : " + getInstancia().getArticuloDetalleListaDePrecio(1, 2, 10,2).get(0).getArticulo().getDescripcion());
//        for (SalidaArticulo entrada : getInstancia().getListaSalidaArticuloDTO(120, new Date(), new Date())) {
//            System.out.println("Datos " + entrada.getNombre());
//        }
    }

    @Override
    public Class getReferencia() {
        return ListaDePrecio.class;
    }

}
