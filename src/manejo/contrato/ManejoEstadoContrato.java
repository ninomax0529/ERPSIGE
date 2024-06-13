/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contrato;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.EstadoContrato;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoEstadoContrato extends ManejoEstandar<EstadoContrato> {

    private static ManejoEstadoContrato manejoEstadoContrato = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoEstadoContrato getInstancia() {
        if (manejoEstadoContrato == null) {
            manejoEstadoContrato = new ManejoEstadoContrato();
        }
        return manejoEstadoContrato;
    }

    public List<EstadoContrato> getLista() {

        String query = " SELECT * FROM estado_contrato  ";

        return session.createSQLQuery(query).addEntity(EstadoContrato.class).list();

    }

    public EstadoContrato getEstado(int codigo) {

        String query = " SELECT * FROM estado_contrato  where codigo=" + codigo;

        return (EstadoContrato) session.createSQLQuery(query).addEntity(EstadoContrato.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return EstadoContrato.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
