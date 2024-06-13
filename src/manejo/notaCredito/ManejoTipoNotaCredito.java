/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.notaCredito;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TipoNotaCredito;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoNotaCredito extends ManejoEstandar<TipoNotaCredito> {

    private static ManejoTipoNotaCredito manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoNotaCredito getInstancia() {
        if (manejo == null) {
            manejo = new ManejoTipoNotaCredito();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<TipoNotaCredito> getTipoNotaCredito() {

        String query = " SELECT * FROM tipo_nota_credito ";

        return session.createSQLQuery(query).addEntity(TipoNotaCredito.class).list();

    }
    
    
      public TipoNotaCredito getTipoNotaCredito(int codigo) {

        String query = " SELECT * FROM tipo_nota_credito where codigo=:codigo  ";

        return (TipoNotaCredito) session.createSQLQuery(query)
                .addEntity(TipoNotaCredito.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return TipoNotaCredito.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
