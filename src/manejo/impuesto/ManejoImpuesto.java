/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.impuesto;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DescuentoPorUsuario;
import modelo.Impuesto;

import org.hibernate.Session;
//import utils.Encriptar;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoImpuesto extends ManejoEstandar<Impuesto> {

    private static ManejoImpuesto manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoImpuesto getInstancia() {
        if (manejo == null) {
            manejo = new ManejoImpuesto();
        }
        return manejo;
    }

    public List<Impuesto> getImpuesto() {

        String query = "SELECT * FROM impuesto";

        return session.createSQLQuery(query).addEntity(Impuesto.class).list();

    }

    public List<Impuesto> getListaImpuestoIsr() {

        String query = "SELECT * from impuesto  WHERE  tipo_impuesto=1 and valor>2 ";

        return session.createSQLQuery(query).addEntity(Impuesto.class).list();

    }

    public Impuesto getImpuestoItbis(int impuesto) {

        String query = "SELECT * FROM impuesto  where tipo_impuesto=2  and codigo=:impuesto";

        return (Impuesto) session.createSQLQuery(query).addEntity(Impuesto.class)
                .setParameter("impuesto", impuesto).uniqueResult();

    }

    public Impuesto getImpuestoIsr(int impuesto) {

        String query = "SELECT * FROM impuesto  where tipo_impuesto=1 and codigo=:impuesto";

        return (Impuesto) session.createSQLQuery(query)
                .addEntity(Impuesto.class).setParameter("impuesto", impuesto).uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return DescuentoPorUsuario.class;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getDescuentoPorUsuario("max","123").getMaximo());
    }

}
