/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contabilidadd;

import java.util.List;
import manejo.HibernateUtil;
import modelo.TipoConcepto;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class TipoConceptoDao {

    private static TipoConceptoDao manejoaTipoConceptoDao = null;
    private Session session = HibernateUtil.getSession();

    public static TipoConceptoDao getInstancia() {
        if (manejoaTipoConceptoDao == null) {
            manejoaTipoConceptoDao = new TipoConceptoDao();
        }
        return manejoaTipoConceptoDao;
    }

    public void salvar(TipoConcepto tipoConcepto) {
        session.beginTransaction();

        session.save(tipoConcepto);

        session.getTransaction().commit();

    }

    public Session getSession() {
        return session;
    }

    public List<TipoConcepto> getTipoConcepto() {

        String query = "SELECT * FROM tipo_concepto";

        return session.createSQLQuery(query).addEntity(TipoConcepto.class).list();

    }

    public List<TipoConcepto> configuracionTipoConceptoPorModulo(int modulo) {

       
        String query = " SELECT * FROM tipo_concepto  "
                + " where CODIGO IN ( SELECT tipo_concepto  FROM configuracion_cuenta_contable "
                + " where habilitada=true ) and modulo=:modulo";

        return session.createSQLQuery(query).addEntity(TipoConcepto.class).setParameter("modulo", modulo).list();

    }
    
      public List<TipoConcepto> getTipoConceptoPorModulo(int modulo) {

        String query = "  SELECT * FROM tipo_concepto tc  where  tc.modulo=:modulo";

        return session.createSQLQuery(query).addEntity(TipoConcepto.class).setParameter("modulo", modulo).list();

    }

    public void remover(TipoConcepto tipoConcepto) {

        try {

            session.beginTransaction();

            session.delete(tipoConcepto);

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }

}
