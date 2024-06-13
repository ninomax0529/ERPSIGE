/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.nomina;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.ConstribuyenteTss;

import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoConstrubuyenteTss extends ManejoEstandar<ConstribuyenteTss> {

    private static ManejoConstrubuyenteTss manejoConstrubuyenteTss = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoConstrubuyenteTss getInstancia() {
        if (manejoConstrubuyenteTss == null) {
            manejoConstrubuyenteTss = new ManejoConstrubuyenteTss();
        }
        return manejoConstrubuyenteTss;
    }

    public List<ConstribuyenteTss> getLista() {

        String query = " SELECT * FROM constribuyente_tss  ";

        return session.createSQLQuery(query).addEntity(ConstribuyenteTss.class).list();

    }

    public ConstribuyenteTss getConstribuyenteTss(int codigo) {

        String query = " SELECT * FROM constribuyente_tss  where codigo=" + codigo;

        return (ConstribuyenteTss) session.createSQLQuery(query).addEntity(ConstribuyenteTss.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return ConstribuyenteTss.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
