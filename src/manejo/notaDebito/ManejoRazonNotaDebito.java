/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.notaDebito;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.RazonNotaDebito;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoRazonNotaDebito extends ManejoEstandar<RazonNotaDebito> {

    private static ManejoRazonNotaDebito manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoRazonNotaDebito getInstancia() {
        if (manejo == null) {
            manejo = new ManejoRazonNotaDebito();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<RazonNotaDebito> getRazonNotaDebito() {

        String query = " SELECT * FROM razon_nota_debito ";

        return session.createSQLQuery(query).addEntity(RazonNotaDebito.class).list();

    }

    @Override
    public Class getReferencia() {
        return RazonNotaDebito.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
