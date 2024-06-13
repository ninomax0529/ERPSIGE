/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.pedido;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.EstadoPedido;

import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoEstadoPedido extends ManejoEstandar<EstadoPedido> {

    private static ManejoEstadoPedido manejoEstadoPedido = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoEstadoPedido getInstancia() {
        if (manejoEstadoPedido == null) {
            manejoEstadoPedido = new ManejoEstadoPedido();
        }
        return manejoEstadoPedido;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<EstadoPedido> getListaEstado() {

        String query = " SELECT * FROM estado_pedido  ";

        return session.createSQLQuery(query).addEntity(EstadoPedido.class).list();

    }

    public EstadoPedido getEstadoPedido(int codigo) {

        String query = " SELECT * FROM estado_pedido where codigo=:codigo ";

        return (EstadoPedido) session.createSQLQuery(query)
                .addEntity(EstadoPedido.class)
                .setParameter("codigo", codigo).uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return EstadoPedido.class;
    }

    public static void main(String[] args) {

        System.out.println("Datos " + getInstancia().getEstadoPedido(1).getNombre());
    }

}
