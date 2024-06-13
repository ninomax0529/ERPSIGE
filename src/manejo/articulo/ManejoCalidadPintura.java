/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.articulo;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.CalidadPintura;
import modelo.TipoArticulo;
import modelo.TipoVenta;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoCalidadPintura extends ManejoEstandar<CalidadPintura> {

    private static ManejoCalidadPintura manejoCalidadPintura = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoCalidadPintura getInstancia() {
        if (manejoCalidadPintura == null) {
            manejoCalidadPintura = new ManejoCalidadPintura();
        }
        return manejoCalidadPintura;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<CalidadPintura> getLista() {

        String query = " SELECT * FROM calidad_pintura  ";

        return session.createSQLQuery(query).addEntity(CalidadPintura.class).list();

    }

    @Override
    public Class getReferencia() {
        return CalidadPintura.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
