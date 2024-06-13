/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contabilidadd;


import java.util.List;
import manejo.HibernateUtil;
import modelo.Catalogo;
import modelo.TipoEntrada;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class TipoEntradaDao {
    
    private static TipoEntradaDao manejoalmacen = null;
    private Session session = HibernateUtil.getSession();

    public static TipoEntradaDao getInstancia() {
        if (manejoalmacen == null) {
            manejoalmacen = new TipoEntradaDao();
        }
        return manejoalmacen;
    }


    public Session getSession() {
        return session;
    }

    public List<TipoEntrada> getTipoEntrada() {

        String query = "SELECT * FROM tipo_entrada";

        return session.createSQLQuery(query).addEntity(TipoEntrada.class).list();

    }
    
      public TipoEntrada getTipoEntrada(int codigo) {

        String query = "SELECT * FROM tipo_entrada  where codigo=:codigo";

        return (TipoEntrada)session.createSQLQuery(query).addEntity(TipoEntrada.class).setParameter("codigo", codigo).uniqueResult();

    }

    


     public static void main(String[] args) {
        
        List<TipoEntrada> catalogo = TipoEntradaDao.getInstancia().getTipoEntrada();
        for(int i=0;i<catalogo.size();i++)
         System.out.println("Catalogo: "+catalogo.get(i).getDescripcion());
        
        
    }
     
     
    
}
