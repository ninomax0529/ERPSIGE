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
import modelo.CuentaBanco;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoBanco extends ManejoEstandar<Banco> {

    private static ManejoBanco manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoBanco getInstancia() {
        if (manejo == null) {
            manejo = new ManejoBanco();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<Banco> getLista() {

        String query = " SELECT * FROM banco  where  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(Banco.class).list();

    }

    public List<CuentaBanco> getCuentaBanco() {

        String query = "SELECT * FROM cuenta_banco";

        return getSession().createSQLQuery(query).addEntity(CuentaBanco.class).list();

    }

    public List<Banco> getBanco(Date fechaIni, Date fechaFin) {

        String query = "SELECT * FROM  banco b  where  fecha  "
                + "  between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaIni) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaFin) + "' "
                + " and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(Banco.class).list();

    }

    public List<CuentaBanco> getCuentaBanco(Banco banco) {

        String query = " SELECT * FROM cuenta_banco  where  banco=:banco ";

        return session.createSQLQuery(query)
                .addEntity(CuentaBanco.class)
                .setParameter("banco", banco)
                .list();

    }

    public Banco getaBanco(int codigo) {

        String query = " SELECT * FROM banco where codigo=:codigo ";

        return (Banco) session.createSQLQuery(query)
                .addEntity(Banco.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    public Banco getBanco(int codigo, int unidadNegocio) {

        String query = " SELECT * FROM banco where codigo=:codigo and unidad_de_negocio=:ung";

        return (Banco) session.createSQLQuery(query)
                .addEntity(Banco.class)
                .setParameter("codigo", codigo)
                .setParameter("ung", unidadNegocio)
                .uniqueResult();

    }

    public List<Banco> getLista(int codigo) {

        String query = " SELECT * FROM banco  where codigo<> " + codigo;

        return session.createSQLQuery(query).addEntity(Banco.class).list();

    }

    @Override
    public Class getReferencia() {
        return Banco.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
