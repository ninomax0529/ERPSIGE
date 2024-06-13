/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.suplidor;

import java.util.List;
import manejo.HibernateUtil;
import modelo.Moneda;
import modelo.TipoSuplidor;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class TipoSuplidorDao {

    private static TipoSuplidorDao manejoMonedaDao = null;
    private Session session = HibernateUtil.getSession();

    public static TipoSuplidorDao getInstancia() {
        if (manejoMonedaDao == null) {
            manejoMonedaDao = new TipoSuplidorDao();
        }
        return manejoMonedaDao;
    }

    public Session getSession() {
        return session;
    }

    public List<TipoSuplidor> getListaTipoSuplidor() {

        String query = "SELECT * FROM tipo_suplidor";

        return session.createSQLQuery(query).addEntity(TipoSuplidor.class).list();

    }

}
