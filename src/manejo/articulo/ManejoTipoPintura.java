/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.articulo;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TipoPintura;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoPintura extends ManejoEstandar<TipoPintura> {

    private static ManejoTipoPintura manejoTipoPintura = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoPintura getInstancia() {
        if (manejoTipoPintura == null) {
            manejoTipoPintura = new ManejoTipoPintura();
        }
        return manejoTipoPintura;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<TipoPintura> getListaTipoPintura() {

        String query = " SELECT * FROM tipo_pintura  ";

        return session.createSQLQuery(query).addEntity(TipoPintura.class).list();

    }

    @Override
    public Class getReferencia() {
        return TipoPintura.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
