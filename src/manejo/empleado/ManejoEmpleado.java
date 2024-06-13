/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.empleado;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Empleado;

import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoEmpleado extends ManejoEstandar<Empleado> {

    private static ManejoEmpleado manejoEmpleado = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoEmpleado getInstancia() {
        if (manejoEmpleado == null) {
            manejoEmpleado = new ManejoEmpleado();
        }
        return manejoEmpleado;
    }

    public List<Empleado> getLista() {

        String query = " SELECT * FROM empleado where unidad_de_negocio=:ung  ";

        return session.createSQLQuery(query)
                .addEntity(Empleado.class)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list();

    }

    public List<Empleado> getLista(int tipoNom) {

        String query = " SELECT * FROM empleado where  condicion=1 and  tipo_nomina=:tipoNom and unidad_de_negocio=:ung ";

        System.out.println("sql " + query + " tipoNom " + tipoNom + " " + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo());
        return session.createSQLQuery(query)
                .addEntity(Empleado.class)
                .setParameter("tipoNom", tipoNom)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list();

    }

    public Empleado getEmpleado(int codigo) {

        String query = " SELECT * FROM empleado  where codigo=:codigo and unidad_de_negocio=:ung " ;

        return (Empleado) session.createSQLQuery(query)
                .addEntity(Empleado.class)
                .setParameter("codigo", codigo)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return Empleado.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
