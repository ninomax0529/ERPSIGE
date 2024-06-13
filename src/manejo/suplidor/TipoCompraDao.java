/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.suplidor;


import java.util.List;
import manejo.HibernateUtil;
import modelo.Moneda;
import modelo.TipoCompra;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class TipoCompraDao {
    
    private static TipoCompraDao manejoMonedaDao = null;
    private Session session = HibernateUtil.getSession();

    public static TipoCompraDao getInstancia() {
        if (manejoMonedaDao == null) {
            manejoMonedaDao = new TipoCompraDao();
        }
        return manejoMonedaDao;
    }


    public Session getSession() {
        return session;
    }

    public List<TipoCompra> getTipoCompra() {

        String query = "SELECT * FROM tipo_compra";

        return session.createSQLQuery(query).addEntity(TipoCompra.class).list();

    }

   
  
     
     
    
}
