/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.suplidor;

import java.util.List;
import manejo.HibernateUtil;
import modelo.Impuesto;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class ImpuestoDao {

    private static ImpuestoDao manejoMonedaDao = null;
    private Session session = HibernateUtil.getSession();

    public static ImpuestoDao getInstancia() {
        if (manejoMonedaDao == null) {
            manejoMonedaDao = new ImpuestoDao();
        }
        return manejoMonedaDao;
    }

    public Session getSession() {
        return session;
    }

    public List<Impuesto> getImpuesto() {

        String query = "SELECT * FROM impuesto";

        return session.createSQLQuery(query).addEntity(Impuesto.class).list();

    }

    public Impuesto getImpuestoItbis(int impuesto) {

        String query = " SELECT * FROM impuesto  where tipo_impuesto=2  and codigo=:impuesto";

        return (Impuesto) session.createSQLQuery(query).addEntity(Impuesto.class)
                .setParameter("impuesto", impuesto).uniqueResult();

    }

    public Impuesto getImpuestoIsr(int impuesto) {

        String query = "SELECT * FROM impuesto  where tipo_impuesto=1 and codigo=:impuesto";

        return (Impuesto) session.createSQLQuery(query)
                .addEntity(Impuesto.class).setParameter("impuesto", impuesto).uniqueResult();

    }

}
