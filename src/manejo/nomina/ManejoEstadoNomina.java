/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.nomina;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.EstadoNomina;

import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoEstadoNomina extends ManejoEstandar<EstadoNomina> {

    private static ManejoEstadoNomina manejoEstadoNomina = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoEstadoNomina getInstancia() {
        if (manejoEstadoNomina == null) {
            manejoEstadoNomina = new ManejoEstadoNomina();
        }
        return manejoEstadoNomina;
    }

    public List<EstadoNomina> getLista() {

        String query = " SELECT * FROM estado_nomina  ";

        return session.createSQLQuery(query).addEntity(EstadoNomina.class).list();

    }

    public EstadoNomina getEstadoNomina(int codigo) {

        String query = " SELECT * FROM estado_nomina  where codigo=" + codigo;

        return (EstadoNomina) session.createSQLQuery(query).addEntity(EstadoNomina.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return EstadoNomina.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
