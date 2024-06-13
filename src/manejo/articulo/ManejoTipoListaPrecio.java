/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.articulo;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TipoArticulo;
import modelo.TipoListaDePrecio;
import modelo.TipoVenta;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoListaPrecio extends ManejoEstandar<TipoListaDePrecio> {

    private static ManejoTipoListaPrecio manejoTipoListaPrecio = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoListaPrecio getInstancia() {
        if (manejoTipoListaPrecio == null) {
            manejoTipoListaPrecio = new ManejoTipoListaPrecio();
        }
        return manejoTipoListaPrecio;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<TipoListaDePrecio> getListaTipoListaDePrecio() {

        String query = " SELECT * FROM tipo_lista_de_precio  ";

        return session.createSQLQuery(query).addEntity(TipoListaDePrecio.class).list();

    }

    @Override
    public Class getReferencia() {
        return TipoListaDePrecio.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
