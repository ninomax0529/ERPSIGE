/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo;

import manejo.unidad.*;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Configuracion;
import modelo.Unidad;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoConfiguracion extends ManejoEstandar<Configuracion> {

    private static ManejoConfiguracion manejoConfiguracion = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoConfiguracion getInstancia() {
        if (manejoConfiguracion == null) {
            manejoConfiguracion = new ManejoConfiguracion();
        }
        return manejoConfiguracion;
    }

    public List<Configuracion> getLista() {

        String query = " SELECT * FROM configuracion  ";

        return session.createSQLQuery(query).addEntity(Configuracion.class).list();

    }

    public Configuracion getConfiguracion() {

        String query = "SELECT * FROM configuracion limit 1";

        return (Configuracion) session.createSQLQuery(query).addEntity(Configuracion.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return Configuracion.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
