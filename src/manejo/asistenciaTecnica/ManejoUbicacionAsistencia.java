/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.asistenciaTecnica;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.UbicacionAsistencia;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoUbicacionAsistencia extends ManejoEstandar<UbicacionAsistencia> {

    private static ManejoUbicacionAsistencia manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoUbicacionAsistencia getInstancia() {
        if (manejo == null) {
            manejo = new ManejoUbicacionAsistencia();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<UbicacionAsistencia> getUbicacionAsistencia() {

        String query = " SELECT * FROM Ubicacion_asistencia  ";

        return session.createSQLQuery(query).addEntity(UbicacionAsistencia.class).list();

    }

    @Override
    public Class getReferencia() {
        return UbicacionAsistencia.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
