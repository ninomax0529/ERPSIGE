/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.nomina;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DeducionesTss;

import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoDeducionesTss extends ManejoEstandar<DeducionesTss> {

    private static ManejoDeducionesTss manejoDeducionesTss = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoDeducionesTss getInstancia() {
        if (manejoDeducionesTss == null) {
            manejoDeducionesTss = new ManejoDeducionesTss();
        }
        return manejoDeducionesTss;
    }

    public List<DeducionesTss> getLista() {

        String query = " SELECT * FROM deduciones_tss  ";

        return session.createSQLQuery(query).addEntity(DeducionesTss.class).list();

    }

    public DeducionesTss getDeducionesTss(int construbuyente) {

        String query = " SELECT * FROM deduciones_tss   where constribuyente=" + construbuyente+" limit 1 ";

        return (DeducionesTss) session.createSQLQuery(query).addEntity(DeducionesTss.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return DeducionesTss.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
