/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.inventario;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Solicitante;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoSolicitante extends ManejoEstandar<Solicitante> {

    private static ManejoSolicitante manejoSolicitante = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoSolicitante getInstancia() {
        if (manejoSolicitante == null) {
            manejoSolicitante = new ManejoSolicitante();
        }
        return manejoSolicitante;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<Solicitante> getListaSolicitante() {

        String query = " SELECT * FROM solicitante  ";

        return session.createSQLQuery(query).addEntity(Solicitante.class).list();

    }

    @Override
    public Class getReferencia() {
        return Solicitante.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
