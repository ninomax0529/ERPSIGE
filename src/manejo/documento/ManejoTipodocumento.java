/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.documento;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TipoDocumento;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipodocumento extends ManejoEstandar<TipoDocumento> {

    private static ManejoTipodocumento manejoTipodocumento = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipodocumento getInstancia() {
        if (manejoTipodocumento == null) {
            manejoTipodocumento = new ManejoTipodocumento();
        }
        return manejoTipodocumento;
    }

    public List<TipoDocumento> getLista() {

        String query = " SELECT * FROM tipo_documento  ";

        return session.createSQLQuery(query).addEntity(TipoDocumento.class).list();

    }

    public List<TipoDocumento> getLista(String movimiento) {

        String query = " SELECT * FROM tipo_documento where movimiento=:movimiento ";

        return session.createSQLQuery(query)
                .addEntity(TipoDocumento.class)
                .setParameter("movimiento", movimiento)
                .list();

    }
    
    
     public List<TipoDocumento> getTipoDocPorModulo(int modulo) {

        String query = " SELECT * FROM tipo_documento where modulo=:codMod ";

        return session.createSQLQuery(query)
                .addEntity(TipoDocumento.class)
                .setParameter("codMod", modulo)
                .list();

    }
    
    
      public TipoDocumento getTipoDocumento(int codigo) {

        String query = " SELECT * FROM tipo_documento where codigo=:codigo  ";

        return (TipoDocumento) session.createSQLQuery(query)
                .addEntity(TipoDocumento.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return TipoDocumento.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
