/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.articulo;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TipoArticulo;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoArticulo extends ManejoEstandar<TipoArticulo> {

    private static ManejoTipoArticulo manejoTipoArticulo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoArticulo getInstancia() {
        if (manejoTipoArticulo == null) {
            manejoTipoArticulo = new ManejoTipoArticulo();
        }
        return manejoTipoArticulo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<TipoArticulo> getListaTipoArticulo() {

        String query = " SELECT * FROM tipo_articulo  ";

        return session.createSQLQuery(query).addEntity(TipoArticulo.class).list();

    }

    @Override
    public Class getReferencia() {
        return TipoArticulo.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
