/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.unidad;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Unidad;
//import modelo.Usuariop;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoUnidad extends ManejoEstandar<Unidad> {

    private static ManejoUnidad manejoUnidad = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoUnidad getInstancia() {
        if (manejoUnidad == null) {
            manejoUnidad = new ManejoUnidad();
        }
        return manejoUnidad;
    }

    public List<Unidad> getLista() {

        String query = " SELECT * FROM unidad  ";

        return session.createSQLQuery(query).addEntity(Unidad.class).list();

    }

    public List<Unidad> getListaUnidad() {

        String query = " SELECT * FROM unidad where unidad_de_negocio=:ung ";

        return session.createSQLQuery(query)
                .addEntity(Unidad.class)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .list();

    }

    public List<Unidad> getLista(String unidadES) {

        String query = " SELECT * FROM unidad  where codigo in (" + unidadES + ")";

        return session.createSQLQuery(query).addEntity(Unidad.class).list();

    }

    public Unidad getUnidad(int codUnidad) {

        String query = " SELECT * FROM unidad  where codigo=" + codUnidad;

        return (Unidad) session.createSQLQuery(query).addEntity(Unidad.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return Unidad.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
