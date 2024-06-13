/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.proyecto;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Chofer;
import modelo.EstadoProyecto;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoEstadoProyecto extends ManejoEstandar<EstadoProyecto> {

    private static ManejoEstadoProyecto manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoEstadoProyecto getInstancia() {
        if (manejo == null) {
            manejo = new ManejoEstadoProyecto();
        }
        return manejo;
    }

    public List<EstadoProyecto> getLista() {

        String query = " SELECT * FROM estado_proyecto ";

        return session.createSQLQuery(query).addEntity(EstadoProyecto.class).list();

    }

    public EstadoProyecto getEstadoProyecto(int codigo) {

        String query = " SELECT * FROM estado_proyecto  where codigo=" + codigo;

        return (EstadoProyecto) session.createSQLQuery(query).addEntity(EstadoProyecto.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return EstadoProyecto.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
