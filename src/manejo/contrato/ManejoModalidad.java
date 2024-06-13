/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contrato;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.ModalidadEquipo;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoModalidad extends ManejoEstandar<ModalidadEquipo> {

    private static ManejoModalidad manejoModalidad = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoModalidad getInstancia() {
        if (manejoModalidad == null) {
            manejoModalidad = new ManejoModalidad();
        }
        return manejoModalidad;
    }

    public List<ModalidadEquipo> getLista() {

        String query = " SELECT * FROM modalidad_equipo  ";

        return session.createSQLQuery(query).addEntity(ModalidadEquipo.class).list();

    }

    public ModalidadEquipo getModalidad(int codigo) {

        String query = " SELECT * FROM modalidad_equipo  where codigo=" + codigo;

        return (ModalidadEquipo) session.createSQLQuery(query).addEntity(ModalidadEquipo.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return ModalidadEquipo.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
