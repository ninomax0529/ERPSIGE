/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.factura;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Cliente;
import modelo.HistoricoBalancePendiente;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoHistoricoBalancePendiente extends ManejoEstandar<HistoricoBalancePendiente> {

    private static ManejoHistoricoBalancePendiente manejoHistoricoBalancePendiente = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoHistoricoBalancePendiente getInstancia() {
        if (manejoHistoricoBalancePendiente == null) {
            manejoHistoricoBalancePendiente = new ManejoHistoricoBalancePendiente();
        }
        return manejoHistoricoBalancePendiente;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<HistoricoBalancePendiente> getLista() {

        String query = " SELECT * FROM historico_balance_pendiente  ";

        return session.createSQLQuery(query).addEntity(HistoricoBalancePendiente.class).list();

    }

    public List<HistoricoBalancePendiente> getLista(Cliente cliente) {

        String query = " SELECT * FROM historico_balance_pendiente  where  cliente:cliente ";

        return session.createSQLQuery(query)
                .addEntity(HistoricoBalancePendiente.class)
                .setParameter("cliente", cliente)
                .list();

    }

    @Override
    public Class getReferencia() {
        return HistoricoBalancePendiente.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
