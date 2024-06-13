/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.factura;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TipoFormaPago;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoFormaPago extends ManejoEstandar<TipoFormaPago> {

    private static ManejoTipoFormaPago manejoTipoFormaPago = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoFormaPago getInstancia() {
        if (manejoTipoFormaPago == null) {
            manejoTipoFormaPago = new ManejoTipoFormaPago();
        }
        return manejoTipoFormaPago;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<TipoFormaPago> getListaTipoFormaPago() {

        String query = " SELECT * FROM tipo_forma_pago  ";

        return session.createSQLQuery(query).addEntity(TipoFormaPago.class).list();

    }

    public TipoFormaPago getTipoFormaPago(int codigo) {

        String query = " SELECT * FROM tipo_forma_pago  where codigo=:codigo  ";

        return (TipoFormaPago) session.createSQLQuery(query).addEntity(TipoFormaPago.class).setParameter("codigo", codigo).uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return TipoFormaPago.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
