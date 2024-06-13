/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contabilidadd;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.GrupoCuenta;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoGrupoCuenta extends ManejoEstandar<GrupoCuenta> {

    private static ManejoGrupoCuenta manejoGrupoCuenta = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoGrupoCuenta getInstancia() {
        if (manejoGrupoCuenta == null) {
            manejoGrupoCuenta = new ManejoGrupoCuenta();
        }
        return manejoGrupoCuenta;
    }

    public List<GrupoCuenta> getLista() {

        String query = " SELECT * FROM grupo_cuenta  ";

        return session.createSQLQuery(query).addEntity(GrupoCuenta.class).list();

    }

    public GrupoCuenta getGrupoCuenta(int codigo) {

        String query = " SELECT * FROM grupo_cuenta where codigo=:codigo ";

        return (GrupoCuenta) session.createSQLQuery(query).addEntity(GrupoCuenta.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return GrupoCuenta.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
