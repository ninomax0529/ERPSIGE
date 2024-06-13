/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.unidadDeNegocio;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.UnidadDeNegocio;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoUnidadDeNegocio extends ManejoEstandar<UnidadDeNegocio> {

    private static ManejoUnidadDeNegocio manejoUnidad = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoUnidadDeNegocio getInstancia() {
        if (manejoUnidad == null) {
            manejoUnidad = new ManejoUnidadDeNegocio();
        }
        return manejoUnidad;
    }

    public List<UnidadDeNegocio> getLista() {

        String query = " SELECT * FROM unidad_de_negocio  ";

        return session.createSQLQuery(query).addEntity(UnidadDeNegocio.class).list();

    }

    public List<UnidadDeNegocio> getLista(String empresa) {

        String query = " SELECT * FROM unidad_de_negocio  where  nombre_empresa_o_grupo like '%" + empresa + "%' ";

        System.out.println("query " + query);
        return session.createSQLQuery(query)
                .addEntity(UnidadDeNegocio.class)
                .list();

    }
    
      public List<UnidadDeNegocio> getListaConRnc() {

        String query = " SELECT * FROM unidad_de_negocio  where LENGTH(rnc)>5  ";

        System.out.println("query " + query);
        return session.createSQLQuery(query)
                .addEntity(UnidadDeNegocio.class)
                .list();

    }

    public UnidadDeNegocio getUnidad(int codUnidad) {

        String query = " SELECT * FROM unidad_de_negocio  where codigo=" + codUnidad;

        return (UnidadDeNegocio) session.createSQLQuery(query).addEntity(UnidadDeNegocio.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return UnidadDeNegocio.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
