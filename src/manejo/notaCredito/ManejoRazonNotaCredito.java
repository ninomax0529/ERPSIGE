/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.notaCredito;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.RazonNotaCredito;

import modelo.TipoNcf;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoRazonNotaCredito extends ManejoEstandar<RazonNotaCredito> {

    private static ManejoRazonNotaCredito manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoRazonNotaCredito getInstancia() {
        if (manejo == null) {
            manejo = new ManejoRazonNotaCredito();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<RazonNotaCredito> getRazonNotaCredito() {

        String query = " SELECT * FROM razon_nota_credito ";

        return session.createSQLQuery(query).addEntity(RazonNotaCredito.class).list();

    }

    @Override
    public Class getReferencia() {
        return RazonNotaCredito.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
