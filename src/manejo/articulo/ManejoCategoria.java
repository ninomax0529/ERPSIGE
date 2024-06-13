/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.articulo;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Categoria;
import modelo.SubCategoria;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoCategoria extends ManejoEstandar<Categoria> {

    private static ManejoCategoria manejoCategoria = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoCategoria getInstancia() {
        if (manejoCategoria == null) {
            manejoCategoria = new ManejoCategoria();
        }
        return manejoCategoria;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<Categoria> getCategoria() {

        String query = " SELECT * FROM categoria  ";

        return session.createSQLQuery(query).addEntity(Categoria.class).list();

    }

    public SubCategoria getSubCategoria(int categoria) {

        String query = " SELECT * FROM sub_categoria where categoria=:categoria  ";

        return (SubCategoria) session.createSQLQuery(query).addEntity(SubCategoria.class).uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return Categoria.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
