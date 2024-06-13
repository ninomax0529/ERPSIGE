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

import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoCondicionEmpleado extends ManejoEstandar<CondicionEmpleado> {

    private static ManejoCondicionEmpleado manejoCondicionEmpleado = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoCondicionEmpleado getInstancia() {
        if (manejoCondicionEmpleado == null) {
            manejoCondicionEmpleado = new ManejoCondicionEmpleado();
        }
        return manejoCondicionEmpleado;
    }

    public List<CondicionEmpleado> getLista() {

        String query = " SELECT * FROM condicion_empleado  ";

        return session.createSQLQuery(query).addEntity(CondicionEmpleado.class).list();

    }

    public CondicionEmpleado getTipoVehiculo(int codigo) {

        String query = " SELECT * FROM condicion_empleado  where codigo=" + codigo;

        return (CondicionEmpleado) session.createSQLQuery(query).addEntity(CondicionEmpleado.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return CondicionEmpleado.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
