/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.suplidor;


import java.util.List;
import manejo.HibernateUtil;
import modelo.Moneda;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class MonedaDao {
    
    private static MonedaDao manejoMonedaDao = null;
    private Session session = HibernateUtil.getSession();

    public static MonedaDao getInstancia() {
        if (manejoMonedaDao == null) {
            manejoMonedaDao = new MonedaDao();
        }
        return manejoMonedaDao;
    }


    public Session getSession() {
        return session;
    }

    public List<Moneda> getMoneda() {

        String query = "SELECT * FROM moneda";

        return session.createSQLQuery(query).addEntity(Moneda.class).list();

    }

   
  
     
     
    
}
