/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cxp;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Moneda;
import modelo.TipoDeRetencionIsr;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoMoneda extends ManejoEstandar<Moneda> {

    private static ManejoMoneda manejo = null;
//    private Session session = HibernateUtil.getSession();

    public static ManejoMoneda getInstancia() {
        if (manejo == null) {
            manejo = new ManejoMoneda();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return HibernateUtil.getSession();
    }

    public List<Moneda> getListaMoneda() {

        String query = " SELECT * FROM moneda  ";

        return getSession().createSQLQuery(query).addEntity(Moneda.class).list();

    }

    public Moneda getMoneda(int codigo) {

        String query = " SELECT * FROM moneda  where codigo=:codigo  ";

        return (Moneda) getSession().createSQLQuery(query)
                .addEntity(TipoDeRetencionIsr.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return Moneda.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
