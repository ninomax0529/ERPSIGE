/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.empleado;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.EstadoCivil;

import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoEstadoCivil extends ManejoEstandar<EstadoCivil> {

    private static ManejoEstadoCivil manejoEstadoCivil = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoEstadoCivil getInstancia() {
        
        if (manejoEstadoCivil == null) {
            manejoEstadoCivil = new ManejoEstadoCivil();
        }
        return manejoEstadoCivil;
    }

    public List<EstadoCivil> getLista() {

        String query = " SELECT * FROM estado_civil  ";

        return session.createSQLQuery(query).addEntity(EstadoCivil.class).list();

    }

    public EstadoCivil getEstadoEmpleado(int codigo) {

        String query = " SELECT * FROM estado_civil  where codigo=" + codigo;

        return (EstadoCivil) session.createSQLQuery(query).addEntity(EstadoCivil.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return EstadoCivil.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
