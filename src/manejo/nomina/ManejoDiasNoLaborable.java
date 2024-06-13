/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.nomina;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DiasFeriado;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoDiasNoLaborable extends ManejoEstandar<DiasFeriado> {

    private static ManejoDiasNoLaborable manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoDiasNoLaborable getInstancia() {
        if (manejo == null) {
            manejo = new ManejoDiasNoLaborable();
        }
        return manejo;
    }

    public List<DiasFeriado> getLista() {

        String query = " SELECT * FROM dias_feriado ";

        return session.createSQLQuery(query).addEntity(DiasFeriado.class).list();

    }

    public DiasFeriado getDiasFeriado(int dia) {

        String query = " SELECT * FROM dias_feriado  where codigo=" + dia;

        return (DiasFeriado) session.createSQLQuery(query).addEntity(DiasFeriado.class).uniqueResult();

    }

    public Boolean getEsDiasFeriado(Date fecha) {

        boolean existe = false;
        String query = " SELECT * FROM dias_feriado  where fecha='" + new SimpleDateFormat("yy-MM-dd").format(fecha) + "' limit 1 ";

        existe= session.createSQLQuery(query)
                 .addEntity(DiasFeriado.class)
                  .uniqueResult()==null ? false : true ;
        
        return existe;

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return DiasFeriado.class;
    }

    public static void main(String[] args) {

        System.out.println("Datos " + getInstancia().getEsDiasFeriado(new Date("2023/08/24")));
    }

}
