/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contabilidadd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import modelo.PeriodoContable;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class PeriodoContableDao {

    private static PeriodoContableDao manejoalmacen = null;
    private Session session = HibernateUtil.getSession();

    public static PeriodoContableDao getInstancia() {
        if (manejoalmacen == null) {
            manejoalmacen = new PeriodoContableDao();
        }
        return manejoalmacen;
    }

    public Session getSession() {
        return session;
    }

    public List<PeriodoContable> getPeriodoContable() {

        String query = "SELECT * FROM periodo_contable";

        return session.createSQLQuery(query).addEntity(PeriodoContable.class).list();

    }

    public String getEstadoPeriodoContable(Date fecha) {

        String estado = "N/A";
        String query = "SELECT * FROM periodo_contable where fecha_inicio='"
                + new SimpleDateFormat("yyyy-MM-dd").format(fecha) + "'";

        PeriodoContable perido = (PeriodoContable) session.createSQLQuery(query).addEntity(PeriodoContable.class).uniqueResult();

        estado = perido == null ? estado : perido.getEstado().getNombre();

        return estado;
    }

    public PeriodoContable getPeriodoContable(Date fecha) {

////        System.out.println("fecha "+fecha);
        String query = "SELECT * FROM periodo_contable where month(fecha_inicio)='"
                + new SimpleDateFormat("MM").format(fecha) + "'";
//        System.out.println("valor fecha "+query);

        PeriodoContable perido = (PeriodoContable) session.createSQLQuery(query).addEntity(PeriodoContable.class).uniqueResult();

        return perido;
    }

    public void salvar(PeriodoContable periodoContable) {

        session.beginTransaction();

        session.save(periodoContable);

        session.getTransaction().commit();

    }

    public static void main(String[] args) {
        System.out.println("Estado " + getInstancia().getPeriodoContable(new Date("2019/02/03")));
    }
}
