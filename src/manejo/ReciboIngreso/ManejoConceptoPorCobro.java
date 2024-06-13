/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.ReciboIngreso;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.ConceptoPorCobro;

import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoConceptoPorCobro extends ManejoEstandar<ConceptoPorCobro> {

    private static ManejoConceptoPorCobro manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoConceptoPorCobro getInstancia() {

        if (manejo == null) {
            manejo = new ManejoConceptoPorCobro();
        }
        return manejo;
    }

    public List<ConceptoPorCobro> getLista() {

        String query = " SELECT * FROM concepto_por_cobro  ";

        return session.createSQLQuery(query).addEntity(ConceptoPorCobro.class).list();

    }

    public ConceptoPorCobro getConceptoPorCobro(int codigo) {

        String query = " SELECT * FROM concepto_por_cobro  where codigo=" + codigo;

        return (ConceptoPorCobro) session.createSQLQuery(query).addEntity(ConceptoPorCobro.class).uniqueResult();

    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class getReferencia() {
        return ConceptoPorCobro.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getLista());
    }

}
