/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.notaDebito;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TipoNotaCredito;
import modelo.TipoNotaDebito;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoNotaDebito extends ManejoEstandar<TipoNotaDebito> {

    private static ManejoTipoNotaDebito manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoNotaDebito getInstancia() {
        if (manejo == null) {
            manejo = new ManejoTipoNotaDebito();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<TipoNotaDebito> getTipoNotaDebito() {

        String query = " SELECT * FROM tipo_nota_debito ";

        return session.createSQLQuery(query).addEntity(TipoNotaDebito.class).list();

    }
    
    
      public TipoNotaDebito getTipoNotaDebito(int codigo) {

        String query = " SELECT * FROM tipo_nota_debito where codigo=:codigo  ";

        return (TipoNotaDebito) session.createSQLQuery(query)
                .addEntity(TipoNotaDebito.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return TipoNotaDebito.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
