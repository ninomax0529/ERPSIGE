/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.delivery;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Delivery;

import org.hibernate.Session;
//import utils.Encriptar;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoDelivery extends ManejoEstandar<Delivery> {

    private static ManejoDelivery manejoDelivery = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoDelivery getInstancia() {
        if (manejoDelivery == null) {
            manejoDelivery = new ManejoDelivery();
        }
        return manejoDelivery;
    }

    public Delivery getDelivery(int codigo) {

        Delivery delivery = (Delivery) session.createQuery("FROM Delivery WHERE codigo = :codigo ")
                .setParameter("codigo", codigo)
                .uniqueResult();
        return delivery;
    }


    @Override
    public Session getSession() {
        return session;
    }

    public List<Delivery> getLista() {

        String query = "SELECT * FROM  delivery  ";

        return session.createSQLQuery(query).addEntity(Delivery.class).list();

    }

    @Override
    public Class getReferencia() {
        return Delivery.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista().get(0).getNombre());
    }

}
