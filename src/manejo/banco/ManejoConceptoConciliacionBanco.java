/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.banco;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Banco;
import modelo.ConceptoConciliacionBancaria;
import modelo.CuentaBanco;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoConceptoConciliacionBanco extends ManejoEstandar<ConceptoConciliacionBancaria> {

    private static ManejoConceptoConciliacionBanco manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoConceptoConciliacionBanco getInstancia() {
        if (manejo == null) {
            manejo = new ManejoConceptoConciliacionBanco();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<ConceptoConciliacionBancaria> getListaConcepto(String origen, String op) {

        String query = " SELECT * FROM concepto_conciliacion_bancaria "
                + " where origen=:origen  and  operacion=:op ";

        return getSession().createSQLQuery(query)
                .addEntity(ConceptoConciliacionBancaria.class)
                .setParameter("origen", origen)
                .setParameter("op", op)
                .list();

    }

    public List<ConceptoConciliacionBancaria> getListaConcepto() {

        String query = " SELECT * FROM concepto_conciliacion_bancaria ";
//
//        getSession().setFlushMode(FlushMode.ALWAYS);
//        getSession().flush();

        return getSession().createSQLQuery(query).addEntity(ConceptoConciliacionBancaria.class).list();

    }

    public ConceptoConciliacionBancaria getConcepto(Integer codigo) {

        String query = "SELECT * FROM concepto_conciliacion_bancaria where codigo=:codigo ";

        return (ConceptoConciliacionBancaria) getSession().createSQLQuery(query).addEntity(ConceptoConciliacionBancaria.class).setParameter("codigo", codigo).uniqueResult();

    }

    @Override
    public Class getReferencia() {
        return ConceptoConciliacionBancaria.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
