/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.vehiculo;

import manejo.unidad.*;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Unidad;
import modelo.Vehiculo;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoVehiculo extends ManejoEstandar<Vehiculo> {

    private static ManejoVehiculo manejoVehiculo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoVehiculo getInstancia() {
        if (manejoVehiculo == null) {
            manejoVehiculo = new ManejoVehiculo();
        }
        return manejoVehiculo;
    }

    public List<Vehiculo> getLista() {

        String query = " SELECT * FROM vehiculo  ";

        return session.createSQLQuery(query).addEntity(Vehiculo.class).list();

    }

    public Vehiculo getVehiculo(int codigo) {

        String query = " SELECT * FROM unidad  where codigo=" + codigo;

        return (Vehiculo) session.createSQLQuery(query).addEntity(Vehiculo.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return Vehiculo.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
