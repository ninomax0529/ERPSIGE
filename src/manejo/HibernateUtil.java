package manejo;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author maximilianoa-te
 */
public class HibernateUtil {

    public static EntityManagerFactory emf;
    public static final ThreadLocal sessionThreadLocal = new ThreadLocal();
    private static SessionFactory sessionFactory = null;
    public static String baseDatoProyecto = "";
    public static Map<String, String> env = new HashMap<String, String>();

    static {

        try {

            emf = Persistence.createEntityManagerFactory("ERP");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        } finally {
            getSession().close();
        }
    }

    public static Session getSession() {
        Session s = null;
        s = (Session) sessionThreadLocal.get();
        if (s == null) {

            if (sessionFactory == null) {
                sessionFactory = emf.createEntityManager().unwrap(Session.class).getSessionFactory();
            }

            s = sessionFactory.openSession();

            s.clear();
            s.setFlushMode(FlushMode.AUTO);
            s.setCacheMode(CacheMode.IGNORE);
            if ((s instanceof SessionImpl)) {
                ((SessionImpl) s).setAutoClear(true);
            }

            sessionThreadLocal.set(s);
            System.out.println("Abrimos Session");

        } else if (!s.isOpen()) {

            emf.createEntityManager().unwrap(Session.class).clear();
            sessionFactory.getCache().evictAllRegions();

            s = sessionFactory.openSession();

            System.out.println("Abrimos de nuevo la Session");
            s.clear();
            s.setFlushMode(FlushMode.AUTO);
            s.setCacheMode(CacheMode.IGNORE);
            if ((s instanceof SessionImpl)) {
                ((SessionImpl) s).setAutoClear(true);
            }
            sessionThreadLocal.set(null);
            sessionThreadLocal.set(s);
        } else {
            s.clear();
            System.out.println("Le damos un Clear a la Session");
        }

        return s;
    }

    public static void closeSession() {
        Session s = (Session) sessionThreadLocal.get();
        if (s != null) {
            s.close();
            sessionThreadLocal.set(null);
        }
    }

    public static void shutDown() {
        if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
            sessionFactory.close();
        }
        emf.close();
    }

    public static void main(String[] args) {
        HibernateUtil.getSession();
//        System.out.println("Estoy conectado"+ HibernateUtil.getSession().createSQLQuery("select * from usuariop").list().get(0));
    }
}
