/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cxp;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.FormaPagoDgii;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoFormaPagodgii extends ManejoEstandar<FormaPagoDgii> {

    private static ManejoFormaPagodgii manejo = null;
//    private Session session = HibernateUtil.getSession();

    public static ManejoFormaPagodgii getInstancia() {
        if (manejo == null) {
            manejo = new ManejoFormaPagodgii();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return HibernateUtil.getSession();
    }

    public List<FormaPagoDgii> getListaFormaPagoDgii() {

        String query = " SELECT * FROM forma_pago_dgii  ";

        return getSession().createSQLQuery(query).addEntity(FormaPagoDgii.class).list();

    }

    public FormaPagoDgii getFormaPagoDgii(int codigo) {

        String query = " SELECT * FROM forma_pago_dgii  where codigo=:codigo  ";

        return (FormaPagoDgii) getSession().createSQLQuery(query)
                .addEntity(FormaPagoDgii.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return FormaPagoDgii.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
