/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.impuesto;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Impuesto;
import modelo.RetencionDeImpuesto;

import org.hibernate.Session;
//import utils.Encriptar;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoRetencionDeImpuesto extends ManejoEstandar<RetencionDeImpuesto> {

    private static ManejoRetencionDeImpuesto manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoRetencionDeImpuesto getInstancia() {
        if (manejo == null) {
            manejo = new ManejoRetencionDeImpuesto();
        }
        return manejo;
    }

    public List<RetencionDeImpuesto> getImpuesto() {

        String query = "SELECT * FROM retencion_de_impuesto";

        return session.createSQLQuery(query).addEntity(Impuesto.class).list();

    }

    public List<RetencionDeImpuesto> getListaImpuestoIsr() {

        String query = "SELECT * from retencion_de_impuesto  WHERE  tipo_impuesto=1 and valor>2 ";

        return session.createSQLQuery(query).addEntity(Impuesto.class).list();

    }

    public RetencionDeImpuesto getImpuestoItbis(int impuesto) {

        String query = "SELECT * FROM retencion_de_impuesto  where tipo_impuesto=2  and codigo=:impuesto";

        return (RetencionDeImpuesto) session.createSQLQuery(query).addEntity(RetencionDeImpuesto.class)
                .setParameter("impuesto", impuesto).uniqueResult();

    }

    public RetencionDeImpuesto getImpuestoIsr(int impuesto) {

        String query = "SELECT * FROM retencion_de_impuesto  where tipo_impuesto=1 and codigo=:impuesto";

        return (RetencionDeImpuesto) session.createSQLQuery(query)
                .addEntity(RetencionDeImpuesto.class).setParameter("impuesto", impuesto).uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return RetencionDeImpuesto.class;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getDescuentoPorUsuario("max","123").getMaximo());
    }

}
