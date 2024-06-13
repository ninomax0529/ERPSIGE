/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo;

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
import modelo.Rol;
import org.hibernate.Session;

/**
 *
 * @author maximiliano
 */
public class ManejoRol  extends ManejoEstandar<Rol> {

    private static ManejoRol manejoRolDao = null;
    private Session session = manejo.HibernateUtil.getSession();

    public static ManejoRol getInstancia() {
        if (manejoRolDao == null) {
            manejoRolDao = new ManejoRol();
        }
        return manejoRolDao;
    }

    public List<Rol> getRol() {

        String query = "SELECT * FROM  rol  ";

        return session.createSQLQuery(query).addEntity(Rol.class).list();

    }
    
     public Rol getRol(int codigo) {

        String query = "SELECT * FROM  rol where codigo=:codigo ";

        return (Rol)session.createSQLQuery(query).addEntity(Rol.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

 
    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
      return Rol.class;
    }

}
