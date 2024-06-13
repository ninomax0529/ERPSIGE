/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.ejecutivoDeVenta;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.EjecutivoDeVenta;

import org.hibernate.Session;
//import utils.Encriptar;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoEjecutivoDeVenta extends ManejoEstandar<EjecutivoDeVenta> {

    private static ManejoEjecutivoDeVenta manejoEjecutivoDeVenta = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoEjecutivoDeVenta getInstancia() {
        if (manejoEjecutivoDeVenta == null) {
            manejoEjecutivoDeVenta = new ManejoEjecutivoDeVenta();
        }
        return manejoEjecutivoDeVenta;
    }

    public EjecutivoDeVenta getEjecutivo(int codigo) {

        EjecutivoDeVenta ejecutivo = (EjecutivoDeVenta) getSession()
                .createSQLQuery(" select *  FROM ejecutivo_de_venta WHERE codigo=:codigo ")
                .addEntity(EjecutivoDeVenta.class)
                .setParameter("codigo", codigo)
                .uniqueResult();
        return ejecutivo;
    }

    public EjecutivoDeVenta getEjecutivo(String nombre) {

        EjecutivoDeVenta ejecutivo = (EjecutivoDeVenta) getSession().createQuery("FROM ejecutivo_de_venta WHERE nombre like '" + nombre + "%'")
                .uniqueResult();
        return ejecutivo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<EjecutivoDeVenta> getLista() {

        String query = "SELECT * FROM  ejecutivo_de_venta  ";

        return getSession().createSQLQuery(query).addEntity(EjecutivoDeVenta.class).list();

    }

    public List<EjecutivoDeVenta> getLista(boolean isSuplidor) {

        String query = "SELECT * FROM  ejecutivo_de_venta where suplidor=:suplidor ";

        return getSession().createSQLQuery(query)
                .addEntity(EjecutivoDeVenta.class)
                .setParameter("suplidor", isSuplidor)
                .list();

    }

    @Override
    public Class getReferencia() {
        return EjecutivoDeVenta.class;
    }

    public static void main(String[] args) {

        System.out.println("Datos " + getInstancia().getEjecutivo(1).getNombre());
    }

}
