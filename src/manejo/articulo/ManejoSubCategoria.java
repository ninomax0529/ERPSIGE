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
public class ManejoSubCategoria extends ManejoEstandar<SubCategoria> {

    private static ManejoSubCategoria manejoSubCategoria = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoSubCategoria getInstancia() {
        if (manejoSubCategoria == null) {
            manejoSubCategoria = new ManejoSubCategoria();
        }
        return manejoSubCategoria;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<SubCategoria> getSubCategoria(Categoria categoria) {

        String query = " SELECT * FROM sub_categoria  where categoria=:categoria ";

        return session.createSQLQuery(query).addEntity(SubCategoria.class)
                .setParameter("categoria", categoria.getCodigo()).list();

    }

    public List<SubCategoria> getSubCategorias(int categoria) {

        String query = " SELECT * FROM sub_categoria  where categoria=:categoria ";

        return session.createSQLQuery(query).addEntity(SubCategoria.class)
                .setParameter("categoria", categoria).list();

    }

    public List<SubCategoria> getSubCategoria() {

        String query = " SELECT * FROM sub_categoria  ";

        return session.createSQLQuery(query).addEntity(SubCategoria.class).list();

    }

    public SubCategoria getSubCategoria(int codigo) {

        String query = " SELECT * FROM sub_categoria   where codigo=:codigo ";

        return (SubCategoria) session.createSQLQuery(query).addEntity(SubCategoria.class)
                .setParameter("codigo", codigo).uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return SubCategoria.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
