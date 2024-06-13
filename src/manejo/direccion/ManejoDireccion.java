/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.direccion;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Caja;
import modelo.Direccion;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoDireccion extends ManejoEstandar<Direccion> {

    private static ManejoDireccion manejoDireccion = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoDireccion getInstancia() {
        if (manejoDireccion == null) {
            manejoDireccion = new ManejoDireccion();
        }
        return manejoDireccion;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<Direccion> getLista() {

        String query = " SELECT * FROM direccion  ";

        return session.createSQLQuery(query).addEntity(Direccion.class).list();

    }

    @Override
    public Class getReferencia() {
        return Direccion.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
