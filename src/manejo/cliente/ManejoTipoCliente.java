/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cliente;


import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TipoCliente;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoCliente extends ManejoEstandar<TipoCliente> {

    private static ManejoTipoCliente manejoTipoCliente = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoCliente getInstancia() {
        if (manejoTipoCliente == null) {
            manejoTipoCliente = new ManejoTipoCliente();
        }
        return manejoTipoCliente;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<TipoCliente> getLista() {

        String query = "select * from tipo_cliente  ";

        return session.createSQLQuery(query).addEntity(TipoCliente.class).list();

    }

    @Override
    public Class getReferencia() {
        return TipoCliente.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
