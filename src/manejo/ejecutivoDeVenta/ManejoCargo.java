/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.ejecutivoDeVenta;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Cargo;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoCargo extends ManejoEstandar<Cargo> {

    private static ManejoCargo manejoCargo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoCargo getInstancia() {
        if (manejoCargo == null) {
            manejoCargo = new ManejoCargo();
        }
        return manejoCargo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<Cargo> getLista() {

        String query = " SELECT * FROM cargo  ";

        return session.createSQLQuery(query).addEntity(Cargo.class).list();

    }

    @Override
    public Class getReferencia() {
        return Cargo.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
