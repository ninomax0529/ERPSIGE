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
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoMenuModulo extends ManejoEstandar<MenuModulo> {

    private static ManejoMenuModulo manejoMenuModulo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoMenuModulo getInstancia() {
        if (manejoMenuModulo == null) {
            manejoMenuModulo = new ManejoMenuModulo();
        }
        return manejoMenuModulo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<MenuModulo> getLista() {

        String query = " SELECT * FROM menu_modulo  ";

        return session.createSQLQuery(query).addEntity(MenuModulo.class).list();

    }

    public List<MenuModulo> getLista(int modulo) {

        String query = " SELECT * FROM menu_modulo where modulo=:modulo order by codigo ";

        return session.createSQLQuery(query).addEntity(MenuModulo.class)
                .setParameter("modulo", modulo)
                .list();
    }

    public List<MenuModulo> getListaPermisoMenu(int menuModulo, int codigoMenu) {

        String query = " SELECT * FROM menu_modulo where modulo=:modulo and  permiso=:codigoMenu ";

        return session.createSQLQuery(query).addEntity(MenuModulo.class)
                .setParameter("modulo", menuModulo)
                .setParameter("codigoMenu", codigoMenu)
                .list();
    }

    public Integer getMenuModulo(int modulo, String nombreOperacion) {
        MenuModulo menuModulo = null;
        String query = " SELECT * FROM menu_modulo where modulo=:modulo  and  id_menu='" + nombreOperacion + "'  limit 1 ";

        System.out.println("query " + query);
        menuModulo= (MenuModulo) session.createSQLQuery(query).addEntity(MenuModulo.class)
                .setParameter("modulo", modulo)
                .uniqueResult();
        
        return  menuModulo==null ? 0 : menuModulo.getCodigo();
    }

    @Override
    public Class getReferencia() {
        return MenuModulo.class;
    }

    public static void main(String[] args) {

        System.out.println("Datos " + getInstancia().getMenuModulo(9, "btnRegistroContrato"));
    }

}
