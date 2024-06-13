/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo;

import java.util.List;
import org.hibernate.Session;
import utiles.ClaseUtil;

/**
 *
 * @author maximilianoa-te
 * @param <T>
 */
public abstract class ManejoEstandar<T extends Object> {

    T obj;

    List<T> lista;

    public ManejoEstandar() {

    }

    public void persist(Object object) {

        try {

            getSession().beginTransaction();

            getSession().save(object);

            getSession().getTransaction().commit();

        } catch (Exception e) {

            getSession().getTransaction().rollback();
//            getSession().close();
            e.printStackTrace();
            ClaseUtil.mensaje("Hubo un error guardando el registro  " + e);
        }

    }

    public void update(Object object) {

        try {

            getSession().beginTransaction();

            getSession().merge(object);

            getSession().getTransaction().commit();
           
        } catch (Exception e) {

            getSession().getTransaction().rollback();
//            getSession().close();
            e.printStackTrace();
        }

    }

    public T salvar(T objeto) {

        try {

            persist(objeto);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return objeto;

    }

    public T get(int codigo) {
        return obj;

    }

    public T actualizar(T objeto) {
        update(objeto);

        return objeto;
    }

    public abstract Session getSession();

    public abstract Class getReferencia();
}
