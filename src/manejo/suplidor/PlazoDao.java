/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.suplidor;


import java.util.List;
import manejo.HibernateUtil;
import modelo.Plazo;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class PlazoDao {
    
    private static PlazoDao manejoPlazoDao = null;
    private Session session = HibernateUtil.getSession();

    public static PlazoDao getInstancia() {
        if (manejoPlazoDao == null) {
            manejoPlazoDao = new PlazoDao();
        }
        return manejoPlazoDao;
    }


    public Session getSession() {
        return session;
    }

    public List<Plazo> getListaPlazo() {

        String query = "SELECT * FROM plazo";

        return session.createSQLQuery(query).addEntity(Plazo.class).list();

    }

    


     public static void main(String[] args) {
       
        
    }
     
     
    
}
