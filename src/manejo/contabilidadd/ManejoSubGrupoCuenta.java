/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contabilidadd;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.SubGrupoCuenta;
//import modelo.Usuariop;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoSubGrupoCuenta extends ManejoEstandar<SubGrupoCuenta> {

    private static ManejoSubGrupoCuenta manejoSubGrupoCuenta = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoSubGrupoCuenta getInstancia() {
        if (manejoSubGrupoCuenta == null) {
            manejoSubGrupoCuenta = new ManejoSubGrupoCuenta();
        }
        return manejoSubGrupoCuenta;
    }

    public List<SubGrupoCuenta> getLista() {

        String query = " SELECT * FROM sub_grupo_cuenta  ";

        return session.createSQLQuery(query).addEntity(SubGrupoCuenta.class).list();

    }

    public List<SubGrupoCuenta> getLista(int grupo) {

        String query = " SELECT * FROM sub_grupo_cuenta  where grupo=:grupo ";

        return session.createSQLQuery(query)
                .addEntity(SubGrupoCuenta.class)
                .setParameter("grupo", grupo)
                .list();

    }

    public SubGrupoCuenta getSubGrupoCuenta(int codigo) {

        String query = " SELECT * FROM sub_grupo_cuenta where codigo=:codigo ";

        return (SubGrupoCuenta) session.createSQLQuery(query).addEntity(SubGrupoCuenta.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return SubGrupoCuenta.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
