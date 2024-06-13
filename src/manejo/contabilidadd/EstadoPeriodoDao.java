/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contabilidadd;


import java.util.List;
import manejo.HibernateUtil;
import modelo.EstadoPeriodo;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class EstadoPeriodoDao {
    
    private static EstadoPeriodoDao manejoalmacen = null;
    private Session session = HibernateUtil.getSession();

    public static EstadoPeriodoDao getInstancia() {
        if (manejoalmacen == null) {
            manejoalmacen = new EstadoPeriodoDao();
        }
        return manejoalmacen;
    }


    public Session getSession() {
        return session;
    }

    public List<EstadoPeriodo> getEstado() {

        String query = "SELECT * FROM estado_periodo";

        return session.createSQLQuery(query).addEntity(EstadoPeriodo.class).list();

    }

      


     public static void main(String[] args) {
        
        List<EstadoPeriodo> catalogo = EstadoPeriodoDao.getInstancia().getEstado();
        for(int i=0;i<catalogo.size();i++)
         System.out.println("Catalogo: "+catalogo.get(i).getNombre());
        
        
    }
     
     
    
}
