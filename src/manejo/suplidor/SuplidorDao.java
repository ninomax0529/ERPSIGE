/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.suplidor;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Suplidor;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class SuplidorDao extends ManejoEstandar<Suplidor> {

    private static SuplidorDao manejosuplidor = null;
    private Session session = HibernateUtil.getSession();

    public static SuplidorDao getInstancia() {
        if (manejosuplidor == null) {
            manejosuplidor = new SuplidorDao();
        }
        return manejosuplidor;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<Suplidor> getSuplidores() {

        String query = "SELECT * FROM suplidor ";

        return session.createSQLQuery(query).addEntity(Suplidor.class).list();

    }

    public List<Suplidor> getSuplidoresConFacturaPendiente() {

        String query = "SELECT * FROM suplidor  where codigo in( SELECT suplidor FROM factura_suplidor where monto_pendiente>0 and anulada=false ) ";

        return session.createSQLQuery(query).addEntity(Suplidor.class).list();

    }

    public Suplidor getSuplidor(int codigo) {

        Suplidor suplidor = null;

        String query = "SELECT * FROM suplidor where  codigo=:codigo ";

        suplidor = (Suplidor) session.createSQLQuery(query)
                .addEntity(Suplidor.class)
                .setParameter("codigo", codigo).uniqueResult();

        return suplidor;
    }

//    public void salvar(Suplidor suplidor) {
//
//        session.beginTransaction();
//
//        session.save(suplidor);
//
//        session.getTransaction().commit();
//
//    }
//
//    public void actualizar(Suplidor suplidor) {
//
//        session.beginTransaction();
//
//        session.update(suplidor);
//
//        session.getTransaction().commit();
//
//    }
    @Override
    public Class getReferencia() {
        return Suplidor.class;
    }

}
