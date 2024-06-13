/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.caja;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Turno;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTurno extends ManejoEstandar<Turno> {

    private static ManejoTurno manejoTurno = null;
//    private Session session = HibernateUtil.getSession();

    public static ManejoTurno getInstancia() {
        if (manejoTurno == null) {
            manejoTurno = new ManejoTurno();
        }
        return manejoTurno;
    }

    @Override
    public Session getSession() {
        return HibernateUtil.getSession();
    }

    public List<Turno> getListaTurno() {

        String query = " SELECT * FROM turno  ";

        return getSession().createSQLQuery(query).addEntity(Turno.class).list();

    }

    @Override
    public Class getReferencia() {
        return Turno.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
