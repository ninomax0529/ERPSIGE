/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.inventario.devolucion;

import manejo.unidad.*;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TipoDevolucion;
import modelo.Unidad;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoDevolucion extends ManejoEstandar<TipoDevolucion> {

    private static ManejoTipoDevolucion manejoUnidad = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoDevolucion getInstancia() {
        if (manejoUnidad == null) {
            manejoUnidad = new ManejoTipoDevolucion();
        }
        return manejoUnidad;
    }

    public List<TipoDevolucion> getLista() {

        String query = " SELECT * FROM tipo_devolucion  ";

        return session.createSQLQuery(query).addEntity(TipoDevolucion.class).list();

    }
    
      public List<Unidad> getLista(String unidadES) {

        String query = " SELECT * FROM unidad  where codigo in ("+unidadES+")";

        return session.createSQLQuery(query).addEntity(Unidad.class).list();

    }
      
         public Unidad getUnidad(int codUnidad) {

        String query = " SELECT * FROM unidad  where codigo="+codUnidad;

        return (Unidad)session.createSQLQuery(query).addEntity(Unidad.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return TipoDevolucion.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
