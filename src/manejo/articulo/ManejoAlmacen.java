/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.articulo;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Almacen;
import modelo.Caja;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoAlmacen extends ManejoEstandar<Almacen> {

    private static ManejoAlmacen manejoAlmacen = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoAlmacen getInstancia() {
        if (manejoAlmacen == null) {
            manejoAlmacen = new ManejoAlmacen();
        }
        return manejoAlmacen;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<Almacen> getLista() {

        String query = " SELECT * FROM almacen  ";

        return session.createSQLQuery(query).addEntity(Almacen.class).list();

    }

    public List<Almacen> getAlmacenPorUnidadDeNegocio(int unidadDeNegocio) {

        String query = " SELECT * FROM almacen where  unidad_de_negocio=:ung ";

        return session.createSQLQuery(query)
                .addEntity(Almacen.class)
                .setParameter("ung", unidadDeNegocio)
                .list();

    }

    public Almacen getalmacen(int codigo) {

        String query = " SELECT * FROM almacen where codigo=:codigo ";

        return (Almacen) session.createSQLQuery(query)
                .addEntity(Almacen.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    public Almacen getalmacen(int codigo, int unidadNegocio) {

        String query = " SELECT * FROM almacen where codigo=:codigo and unidad_de_negocio=:ung";

        return (Almacen) session.createSQLQuery(query)
                .addEntity(Almacen.class)
                .setParameter("codigo", codigo)
                .setParameter("ung",unidadNegocio)
                .uniqueResult();

    }

    public List<Almacen> getLista(int codalmacen) {

        String query = " SELECT * FROM almacen  where codigo<> " + codalmacen;

        return session.createSQLQuery(query).addEntity(Almacen.class).list();

    }

    @Override
    public Class getReferencia() {
        return Almacen.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
