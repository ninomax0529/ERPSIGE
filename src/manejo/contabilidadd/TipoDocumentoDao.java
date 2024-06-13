/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contabilidadd;


import java.util.List;
import manejo.HibernateUtil;
import modelo.TipoDocumento;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class TipoDocumentoDao {
    
    private static TipoDocumentoDao manejoMonedaDao = null;
    private Session session = HibernateUtil.getSession();

    public static TipoDocumentoDao getInstancia() {
        if (manejoMonedaDao == null) {
            manejoMonedaDao = new TipoDocumentoDao();
        }
        return manejoMonedaDao;
    }


    public Session getSession() {
        return session;
    }

    public List<TipoDocumento> getTipoDocumento() {

        String query = "SELECT * FROM tipo_documento";

        return session.createSQLQuery(query).addEntity(TipoDocumento.class).list();

    }
    
      public TipoDocumento getTipoDocumento(int codigo) {

        TipoDocumento tipoDocumento;
        String query = "SELECT * FROM tipo_documento where  codigo=:codigo ";

        tipoDocumento =(TipoDocumento)session.createSQLQuery(query).addEntity(TipoDocumento.class).setParameter("codigo", codigo).uniqueResult();

        return tipoDocumento;
    }

   
  
     
     
    
}
