/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.contabilidadd;

import java.util.List;
import manejo.HibernateUtil;
import modelo.Modulo;
import org.hibernate.Session;

/**
 *
 * @author luis
 */
public class ModuloDao {

    private static ModuloDao manejoalmacen = null;
    private Session session = HibernateUtil.getSession();

    public static ModuloDao getInstancia() {
        if (manejoalmacen == null) {
            manejoalmacen = new ModuloDao();
        }
        return manejoalmacen;
    }

    public Session getSession() {
        return session;
    }

    public List<Modulo> getModulo() {

        String query = "SELECT * FROM modulo order by posicion ";

        return session.createSQLQuery(query).addEntity(Modulo.class).list();

    }

    public Modulo getModulo(int codigo) {

        Modulo modulo;
        String query = "SELECT * FROM modulo where  codigo=:codigo ";

        modulo = (Modulo) session.createSQLQuery(query).addEntity(Modulo.class).setParameter("codigo", codigo).uniqueResult();

        return modulo;
    }

    public static void main(String[] args) {

    }

}
