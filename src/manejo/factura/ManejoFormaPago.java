/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.factura;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Factura;
import modelo.FormaPago;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoFormaPago extends ManejoEstandar<FormaPago> {

    private static ManejoFormaPago manejoFormaPago = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoFormaPago getInstancia() {
        if (manejoFormaPago == null) {
            manejoFormaPago = new ManejoFormaPago();
        }
        return manejoFormaPago;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<FormaPago> getListaFormaPago() {

        String query = " SELECT * FROM forma_pago  ";

        return session.createSQLQuery(query).addEntity(FormaPago.class).list();

    }

      public List<FormaPago> getListaFormaPago(int docum,int tipoDoc) {

        String query = " SELECT * FROM forma_pago where tipo_documento=:tipoDoc and documento=:docum ";

        return session.createSQLQuery(query)
                .addEntity(FormaPago.class)
                .setParameter("tipoDoc", tipoDoc)
                .setParameter("docum",docum)
                .list();

    }
    
    @Override
    public Class getReferencia() {
        return FormaPago.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
