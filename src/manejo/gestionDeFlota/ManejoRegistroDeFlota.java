/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.gestionDeFlota;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.RegistroDeFlota;

import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoRegistroDeFlota extends ManejoEstandar<RegistroDeFlota> {

    private static ManejoRegistroDeFlota manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoRegistroDeFlota getInstancia() {
        if (manejo == null) {
            manejo = new ManejoRegistroDeFlota();
        }
        return manejo;
    }

    public List<RegistroDeFlota> getLista() {

        String query = " SELECT * FROM registro_de_flota  ";

        return session.createSQLQuery(query).addEntity(RegistroDeFlota.class).list();

    }

    public List<RegistroDeFlota> getFlotaDisponible() {

        String query = " SELECT * FROM registro_de_flota where asignada=false ";

        return session.createSQLQuery(query).addEntity(RegistroDeFlota.class).list();

    }


    public List<RegistroDeFlota> getListaEntreFecha(Date fi, Date ff) {

        String query = " SELECT * FROM registro_de_flota a  where   a.fecha between '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'" + " ";

        return session.createSQLQuery(query)
                .addEntity(RegistroDeFlota.class)
                .list();

    }

    public RegistroDeFlota existeInstalacion(String sim) {

        String query = " SELECT * FROM registro_de_flota  where sim=" + sim;

        return (RegistroDeFlota) session.createSQLQuery(query)
                .addEntity(RegistroDeFlota.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return RegistroDeFlota.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
