/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.suplidor;


import java.util.List;
import manejo.HibernateUtil;
import modelo.TiempoEntrega;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class TiempoDeEntregaDao {
    
    private static TiempoDeEntregaDao manejoTiempoDeEntregaDao = null;
    private Session session = HibernateUtil.getSession();

    public static TiempoDeEntregaDao getInstancia() {
        if (manejoTiempoDeEntregaDao == null) {
            manejoTiempoDeEntregaDao = new TiempoDeEntregaDao();
        }
        return manejoTiempoDeEntregaDao;
    }


    public Session getSession() {
        return session;
    }

    public List<TiempoEntrega> getListaTiempoEntrega() {

        String query = "SELECT * FROM tiempo_entrega";

        return session.createSQLQuery(query).addEntity(TiempoEntrega.class).list();

    }

    
     public static void main(String[] args) {
       
        
    }
     
     
    
}
