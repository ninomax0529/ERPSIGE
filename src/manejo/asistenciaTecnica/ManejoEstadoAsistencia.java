/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.asistenciaTecnica;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.EstadoAsistenciaTecnica;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoEstadoAsistencia extends ManejoEstandar<EstadoAsistenciaTecnica> {

    private static ManejoEstadoAsistencia manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoEstadoAsistencia getInstancia() {
        if (manejo == null) {
            manejo = new ManejoEstadoAsistencia();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<EstadoAsistenciaTecnica> getEstadoAsistenciaTecnica() {

        String query = " SELECT * FROM estado_asistencia_tecnica  ";

        return session.createSQLQuery(query).addEntity(EstadoAsistenciaTecnica.class).list();

    }

    @Override
    public Class getReferencia() {
        return EstadoAsistenciaTecnica.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
