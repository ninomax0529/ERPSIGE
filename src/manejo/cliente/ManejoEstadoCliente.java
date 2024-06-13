/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cliente;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.EstadoCliente;
import modelo.TipoArticulo;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoEstadoCliente extends ManejoEstandar<EstadoCliente> {

    private static ManejoEstadoCliente manejoEstadoCliente = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoEstadoCliente getInstancia() {
        if (manejoEstadoCliente == null) {
            manejoEstadoCliente = new ManejoEstadoCliente();
        }
        return manejoEstadoCliente;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<EstadoCliente> getLista() {

        String query = " SELECT * FROM estado_cliente  ";

        return session.createSQLQuery(query).addEntity(EstadoCliente.class).list();

    }

    @Override
    public Class getReferencia() {
        return EstadoCliente.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
