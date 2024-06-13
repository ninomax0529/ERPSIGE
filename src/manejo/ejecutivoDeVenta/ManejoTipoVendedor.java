/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.ejecutivoDeVenta;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Cargo;
import modelo.TipoVendedor;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoVendedor extends ManejoEstandar<TipoVendedor> {

    private static ManejoTipoVendedor manejoTipoVendedor = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoVendedor getInstancia() {
        if (manejoTipoVendedor == null) {
            manejoTipoVendedor = new ManejoTipoVendedor();
        }
        return manejoTipoVendedor;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<TipoVendedor> getLista() {

        String query = " SELECT * FROM tipo_vendedor  ";

        return session.createSQLQuery(query).addEntity(TipoVendedor.class).list();

    }

    @Override
    public Class getReferencia() {
        return TipoVendedor.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
