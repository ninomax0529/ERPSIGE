/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.empleado;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.CondicionEmpleado;
import modelo.EstadoEmpleado;

import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoEstadoEmpleado extends ManejoEstandar<EstadoEmpleado> {

    private static ManejoEstadoEmpleado manejoEstadoEmpleado = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoEstadoEmpleado getInstancia() {
        
        if (manejoEstadoEmpleado == null) {
            manejoEstadoEmpleado = new ManejoEstadoEmpleado();
        }
        return manejoEstadoEmpleado;
    }

    public List<EstadoEmpleado> getLista() {

        String query = " SELECT * FROM estado_empleado  ";

        return session.createSQLQuery(query).addEntity(EstadoEmpleado.class).list();

    }

    public EstadoEmpleado getEstadoEmpleado(int codigo) {

        String query = " SELECT * FROM estado_empleado  where codigo=" + codigo;

        return (EstadoEmpleado) session.createSQLQuery(query).addEntity(EstadoEmpleado.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return EstadoEmpleado.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
