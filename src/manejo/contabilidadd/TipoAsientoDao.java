/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contabilidadd;


import java.util.List;
import manejo.HibernateUtil;
;
import modelo.TipoAsiento;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class TipoAsientoDao {
    
    private static TipoAsientoDao manejoalmacen = null;
    private Session session = HibernateUtil.getSession();

    public static TipoAsientoDao getInstancia() {
        if (manejoalmacen == null) {
            manejoalmacen = new TipoAsientoDao();
        }
        return manejoalmacen;
    }


    public Session getSession() {
        return session;
    }

    public List<TipoAsiento> getTipoAsiento() {

        String query = "SELECT * FROM tipo_asiento";

        return session.createSQLQuery(query).addEntity(TipoAsiento.class).list();

    }

    public TipoAsiento getTipoAsiento(int codigo) {

        TipoAsiento tipoasiento;
        String query = "SELECT * FROM tipo_asiento where  codigo=:codigo ";

        tipoasiento = (TipoAsiento) session.createSQLQuery(query).addEntity(TipoAsiento.class).setParameter("codigo", codigo).uniqueResult();

        return tipoasiento;
    }
    


  
     
     
    
}
