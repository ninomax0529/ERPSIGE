/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.menuModulo;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.MenuModulo;
import modelo.Modulo;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoModulo extends ManejoEstandar<Modulo> {

    private static ManejoModulo manejoModulo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoModulo getInstancia() {
        if (manejoModulo == null) {
            manejoModulo = new ManejoModulo();
        }
        return manejoModulo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<Modulo> getLista() {

        String query = " SELECT * FROM modulo  ";

        return session.createSQLQuery(query).addEntity(Modulo.class).list();

    }

    public List<Modulo> getLista(int rol) {

        String query = " SELECT * from modulo  where codigo in (SELECT modulo FROM  rol_menu_modulo where rol=:rol  and habilitado=true ) ";

        return session.createSQLQuery(query)
                .addEntity(Modulo.class)
                .setParameter("rol", rol)
                .list();

    }

    public Modulo getModulo(int codigo) {

        Modulo modulo;
        String query = "SELECT * FROM modulo where  codigo=:codigo ";

        modulo = (Modulo) session.createSQLQuery(query)
                .addEntity(Modulo.class).setParameter("codigo", codigo).uniqueResult();

        return modulo;
    }

    @Override
    public Class getReferencia() {
        return Modulo.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
