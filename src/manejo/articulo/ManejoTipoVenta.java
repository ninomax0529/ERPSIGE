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
import modelo.TipoVenta;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoVenta extends ManejoEstandar<TipoVenta> {

    private static ManejoTipoVenta manejoTipoVenta = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoVenta getInstancia() {
        if (manejoTipoVenta == null) {
            manejoTipoVenta = new ManejoTipoVenta();
        }
        return manejoTipoVenta;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<TipoVenta> getListaTipoVenta() {

        String query = " SELECT * FROM tipo_venta  ";

        return session.createSQLQuery(query).addEntity(TipoVenta.class).list();

    }

    @Override
    public Class getReferencia() {
        return TipoVenta.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
