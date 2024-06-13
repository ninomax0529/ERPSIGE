/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.asistenciaTecnica;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TipoDeAsistencia;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoAsistencia extends ManejoEstandar<TipoDeAsistencia> {

    private static ManejoTipoAsistencia manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoAsistencia getInstancia() {
        if (manejo == null) {
            manejo = new ManejoTipoAsistencia();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<TipoDeAsistencia> getListaTipoAsistencia() {

        String query = " SELECT * FROM tipo_de_asistencia  ";

        return session.createSQLQuery(query).addEntity(TipoDeAsistencia.class).list();

    }

    public TipoDeAsistencia getTipoAsistencia(int codigo) {

        String query = " SELECT * FROM tipo_de_asistencia  where codigo=:codigo ";

        return (TipoDeAsistencia)session
                  .createSQLQuery(query)
                  .addEntity(TipoDeAsistencia.class)
                  .setParameter("codigo", codigo)                 
                   .uniqueResult();
               
               

    }

    @Override
    public Class getReferencia() {
        return TipoDeAsistencia.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
