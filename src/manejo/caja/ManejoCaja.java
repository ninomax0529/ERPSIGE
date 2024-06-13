/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.caja;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Caja;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoCaja extends ManejoEstandar<Caja> {

    private static ManejoCaja manejoCaja = null;
//    private Session session = HibernateUtil.getSession();

    public static ManejoCaja getInstancia() {
        if (manejoCaja == null) {
            manejoCaja = new ManejoCaja();
        }
        return manejoCaja;
    }

    @Override
    public Session getSession() {
        return  HibernateUtil.getSession();
    }

    public List<Caja> getListaCaja() {

        String query = " SELECT * FROM caja  ";

        return getSession().createSQLQuery(query).addEntity(Caja.class).list();

    }

    @Override
    public Class getReferencia() {
        return Caja.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
