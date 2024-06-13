/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.banco;

import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.Banco;
import modelo.TipoCuentaBanco;
import org.hibernate.Session;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoTipoCuentaBanco extends ManejoEstandar<TipoCuentaBanco> {

    private static ManejoTipoCuentaBanco manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoTipoCuentaBanco getInstancia() {
        if (manejo == null) {
            manejo = new ManejoTipoCuentaBanco();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<TipoCuentaBanco> getLista() {

        String query = " SELECT * FROM tipo_cuenta_banco  ";

        return session.createSQLQuery(query).addEntity(TipoCuentaBanco.class).list();

    }


    public TipoCuentaBanco getaTipoCuentaBanco(int codigo) {

        String query = " SELECT * FROM tipo_cuenta_banco where codigo=:codigo ";

        return (TipoCuentaBanco) session.createSQLQuery(query)
                .addEntity(TipoCuentaBanco.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    public TipoCuentaBanco getTipoCuentaBanco(int codigo, int unidadNegocio) {

        String query = " SELECT * FROM tipo_cuenta_banco  where codigo=:codigo and unidad_de_negocio=:ung ";

        return (TipoCuentaBanco) session.createSQLQuery(query)
                .addEntity(TipoCuentaBanco.class)
                .setParameter("codigo", codigo)
                .setParameter("ung",unidadNegocio)
                .uniqueResult();

    }

    public List<TipoCuentaBanco> getLista(int codigo) {

        String query = " SELECT * FROM tipo_cuenta_banco  where codigo<> " + codigo;

        return session.createSQLQuery(query).addEntity(TipoCuentaBanco.class).list();

    }

    @Override
    public Class getReferencia() {
        return TipoCuentaBanco.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
