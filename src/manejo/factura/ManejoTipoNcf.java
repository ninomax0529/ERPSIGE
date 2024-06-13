/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.factura;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.TipoNcf;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoNcf extends ManejoEstandar<TipoNcf> {

    private static ManejoTipoNcf manejoTipoNcf = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoNcf getInstancia() {
        if (manejoTipoNcf == null) {
            manejoTipoNcf = new ManejoTipoNcf();
        }
        return manejoTipoNcf;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<TipoNcf> getListaTipoNcf() {

        String query = "   SELECT * FROM tipo_ncf  where habilitado=true  ";

        return session.createSQLQuery(query).addEntity(TipoNcf.class).list();

    }

    public List<TipoNcf> getListaTipoNcf(String origen) {

        String query = " SELECT * FROM tipo_ncf  where habilitado=true and origen='"+origen+"' " ;

        return session.createSQLQuery(query)
                .addEntity(TipoNcf.class)             
                .list();

    }

    public List<TipoNcf> getTipoNcfPorUnidaDeNegocio(int ung) {

        String query = " SELECT * FROM tipo_ncf  where habilitado=true  and  unidad_negocio=:ung ";

        return session.createSQLQuery(query).addEntity(TipoNcf.class).list();

    }

    public TipoNcf getTipoNcf(int codigo) {

        String query = " SELECT * FROM tipo_ncf  where codigo=:codigo";

        return (TipoNcf) session.createSQLQuery(query)
                .addEntity(TipoNcf.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return TipoNcf.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
