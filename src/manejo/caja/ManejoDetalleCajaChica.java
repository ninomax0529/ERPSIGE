/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.caja;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Caja;
import modelo.DetalleCajaChica;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoDetalleCajaChica extends ManejoEstandar<DetalleCajaChica> {

    private static ManejoDetalleCajaChica manejoDetalleCajaChica = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoDetalleCajaChica getInstancia() {
        if (manejoDetalleCajaChica == null) {
            manejoDetalleCajaChica = new ManejoDetalleCajaChica();
        }
        return manejoDetalleCajaChica;
    }

    @Override
    public Session getSession() {
        return  session;
    }

    public List<Caja> getListaCaja() {

        String query = " SELECT * FROM caja  ";

        return getSession().createSQLQuery(query).addEntity(Caja.class).list();

    }

    @Override
    public Class getReferencia() {
        return DetalleCajaChica.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
