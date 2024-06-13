/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.menuModulo;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TipoMenu;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoMenu extends ManejoEstandar<TipoMenu> {

    private static ManejoTipoMenu manejoTipoMenu = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoMenu getInstancia() {
        if (manejoTipoMenu == null) {
            manejoTipoMenu = new ManejoTipoMenu();
        }
        return manejoTipoMenu;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<TipoMenu> getLista() {

        String query = " SELECT * FROM tipo_menu  ";

        return session.createSQLQuery(query).addEntity(TipoMenu.class).list();

    }


    public TipoMenu getTipoMenu(int codigo) {

        TipoMenu tipoMenu;
        String query = "SELECT * FROM tipo_menu where  codigo=:codigo ";

        tipoMenu = (TipoMenu) session.createSQLQuery(query)
                .addEntity(TipoMenu.class).setParameter("codigo", codigo).uniqueResult();

        return tipoMenu;
    }

    @Override
    public Class getReferencia() {
        return TipoMenu.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
