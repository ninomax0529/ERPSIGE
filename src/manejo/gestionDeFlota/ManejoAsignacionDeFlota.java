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
import modelo.AsignacionDeFlota;

import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoAsignacionDeFlota extends ManejoEstandar<AsignacionDeFlota> {

    private static ManejoAsignacionDeFlota manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoAsignacionDeFlota getInstancia() {
        if (manejo == null) {
            manejo = new ManejoAsignacionDeFlota();
        }
        return manejo;
    }

    public List<AsignacionDeFlota> getLista() {

        String query = " SELECT * FROM asignacion_de_flota  ";

        return session.createSQLQuery(query).addEntity(AsignacionDeFlota.class).list();

    }

    public List<AsignacionDeFlota> getListaEntreFecha(Date fi, Date ff) {

        String query = " SELECT * FROM asignacion_de_flota a  where   a.fecha between '"
                + new SimpleDateFormat("yyyy-MM-dd").format(fi) + "' and '"
                + new SimpleDateFormat("yyyy-MM-dd").format(ff) + "'" + " ";

        return session.createSQLQuery(query)
                .addEntity(AsignacionDeFlota.class)
                .list();

    }

    public AsignacionDeFlota existeInstalacion(String sim) {

        String query = " SELECT * FROM asignacion_de_flota  where sim=" + sim;

        return (AsignacionDeFlota) session.createSQLQuery(query)
                .addEntity(AsignacionDeFlota.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return AsignacionDeFlota.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
