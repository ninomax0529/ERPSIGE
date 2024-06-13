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
import modelo.PlanDeServicio;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoPlanDeServicio extends ManejoEstandar<PlanDeServicio> {

    private static ManejoPlanDeServicio manejoPlanDeServicio = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoPlanDeServicio getInstancia() {
        if (manejoPlanDeServicio == null) {
            manejoPlanDeServicio = new ManejoPlanDeServicio();
        }
        return manejoPlanDeServicio;
    }

    public List<PlanDeServicio> getLista() {

        String query = " SELECT * FROM plan_de_servicio  ";

        return session.createSQLQuery(query).addEntity(PlanDeServicio.class).list();

    }

    public PlanDeServicio getPlanDeServicio(int codigo) {

        String query = " SELECT * FROM plan_de_servicio  where codigo=" + codigo;

        return (PlanDeServicio) session.createSQLQuery(query).addEntity(PlanDeServicio.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return PlanDeServicio.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
