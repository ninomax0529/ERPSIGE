/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.menuModulo;

/**
 *
 * @author maximilianoa-te
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import manejo.ManejoEstandar;
import modelo.Rol;
import modelo.RolMenuModulo;
import org.hibernate.Session;

/**
 *
 * @author maximiliano
 */
public class ManejoRolMenuModulo extends ManejoEstandar<RolMenuModulo> {

    private static ManejoRolMenuModulo manejoRolMenuModulo = null;
    private Session session = manejo.HibernateUtil.getSession();

    public static ManejoRolMenuModulo getInstancia() {
        if (manejoRolMenuModulo == null) {
            manejoRolMenuModulo = new ManejoRolMenuModulo();
        }
        return manejoRolMenuModulo;
    }

    public List<RolMenuModulo> getRolMenuModulo() {

        String query = " SELECT * FROM  rol_menu_modulo  ";

        return session.createSQLQuery(query).addEntity(Rol.class).list();

    }

    public List<RolMenuModulo> getRolMenuModulo(int rol) {

        String query = "SELECT * FROM  rol_menu_modulo where rol=:rol";

        return session.createSQLQuery(query)
                .addEntity(RolMenuModulo.class)
                .setParameter("rol", rol)
                .list();

    }

    public List<RolMenuModulo> getRolMenuModulo(int rol, int modulo) {

        String query = "SELECT * FROM  rol_menu_modulo where rol=:rol and modulo=:modulo ";

        return session.createSQLQuery(query)
                .addEntity(RolMenuModulo.class)
                .setParameter("rol", rol)
                .setParameter("modulo", modulo)
                .list();

    }

    public RolMenuModulo getRolMenuModulo(int rol, int modulo, int menuModulo) {

        String query = "SELECT * FROM  rol_menu_modulo where rol=:rol and modulo=:modulo and menu_modulo=:menuModulo ";

        return (RolMenuModulo)session.createSQLQuery(query)
                .addEntity(RolMenuModulo.class)
                .setParameter("rol", rol)
                .setParameter("modulo", modulo)
                .setParameter("menuModulo", menuModulo)
                .uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return RolMenuModulo.class;
    }

}
