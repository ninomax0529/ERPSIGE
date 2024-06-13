/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.cxp;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.CostosYGastos;
import modelo.TipoDeRetencionIsr;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoDeIsr extends ManejoEstandar<TipoDeRetencionIsr> {

    private static ManejoTipoDeIsr manejo = null;
//    private Session session = HibernateUtil.getSession();

    public static ManejoTipoDeIsr getInstancia() {
        if (manejo == null) {
            manejo = new ManejoTipoDeIsr();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return HibernateUtil.getSession();
    }

    public List<TipoDeRetencionIsr> getListaTipoDeRetencionIsr() {

        String query = " SELECT * FROM tipo_de_retencion_isr  ";

        return getSession().createSQLQuery(query).addEntity(TipoDeRetencionIsr.class).list();

    }

    public TipoDeRetencionIsr getTipoDeRetencionIsr(int codigo) {

        String query = " SELECT * FROM tipo_de_retencion_isr  where codigo=:codigo  ";

        return (TipoDeRetencionIsr) getSession().createSQLQuery(query)
                .addEntity(TipoDeRetencionIsr.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return TipoDeRetencionIsr.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
