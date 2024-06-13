/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cliente;

import manejo.unidad.*;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Plazo;
import modelo.Unidad;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoPlazo extends ManejoEstandar<Plazo> {

    private static ManejoPlazo manejoPlazo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoPlazo getInstancia() {
        if (manejoPlazo == null) {
            manejoPlazo = new ManejoPlazo();
        }
        return manejoPlazo;
    }

    public List<Plazo> getLista() {

        String query = " SELECT * FROM plazo  ";

        return session.createSQLQuery(query).addEntity(Plazo.class).list();

    }
    
    
     public Plazo getplazo(int dia) {

        String query = " SELECT * FROM plazo where dias=:dia limit 1";

        return (Plazo)session.createSQLQuery(query).addEntity(Plazo.class).setParameter("dia", dia).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return Plazo.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
