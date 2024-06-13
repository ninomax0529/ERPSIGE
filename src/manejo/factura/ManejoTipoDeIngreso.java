/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.factura;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TipoDeIngreso;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoDeIngreso extends ManejoEstandar<TipoDeIngreso> {

    private static ManejoTipoDeIngreso manejo = null;
//    private Session session = HibernateUtil.getSession();

    public static ManejoTipoDeIngreso getInstancia() {
        if (manejo == null) {
            manejo = new ManejoTipoDeIngreso();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return HibernateUtil.getSession();
    }

    public List<TipoDeIngreso> getListaTipoDeIngreso() {

        String query = " SELECT * FROM tipo_de_ingreso  ";

        return getSession().createSQLQuery(query).addEntity(TipoDeIngreso.class).list();

    }

    public TipoDeIngreso getTipoDeIngreso(int codigo) {

        String query = " SELECT * FROM tipo_de_ingreso  where codigo=:codigo  ";

        return (TipoDeIngreso) getSession().createSQLQuery(query)
                .addEntity(TipoDeIngreso.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return TipoDeIngreso.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
