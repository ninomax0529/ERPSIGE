/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.caja;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Categoria;
import modelo.TipoMovimiento;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoMovimieto extends ManejoEstandar<TipoMovimiento> {

    private static ManejoTipoMovimieto manejoTipoMovimieto = null;
//    private Session session = HibernateUtil.getSession();

    public static ManejoTipoMovimieto getInstancia() {
        if (manejoTipoMovimieto == null) {
            manejoTipoMovimieto = new ManejoTipoMovimieto();
        }
        return manejoTipoMovimieto;
    }

    @Override
    public Session getSession() {
        return HibernateUtil.getSession();
    }

    public List<TipoMovimiento> getTipoMovimiento() {

        String query = " SELECT * FROM tipo_movimiento  ";

        return getSession().createSQLQuery(query).addEntity(TipoMovimiento.class).list();

    }

    public TipoMovimiento getTipoMovimiento(int codigo) {

        String query = " SELECT * FROM tipo_movimiento  where codigo=:codigo  ";

        return (TipoMovimiento) getSession().createSQLQuery(query)
                .addEntity(TipoMovimiento.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return Categoria.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
