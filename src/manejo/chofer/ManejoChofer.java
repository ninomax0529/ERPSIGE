/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.chofer;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Chofer;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoChofer extends ManejoEstandar<Chofer> {

    private static ManejoChofer manejoVehiculo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoChofer getInstancia() {
        if (manejoVehiculo == null) {
            manejoVehiculo = new ManejoChofer();
        }
        return manejoVehiculo;
    }

    public List<Chofer> getLista() {

        String query = " SELECT * FROM chofer ";

        return session.createSQLQuery(query).addEntity(Chofer.class).list();

    }

    public Chofer getChofer(int codigo) {

        String query = " SELECT * FROM chofer  where codigo=" + codigo;

        return (Chofer) session.createSQLQuery(query).addEntity(Chofer.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return Chofer.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
