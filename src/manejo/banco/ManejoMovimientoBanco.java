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
import modelo.MovimientoBanco;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoMovimientoBanco extends ManejoEstandar<MovimientoBanco> {

    private static ManejoMovimientoBanco manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoMovimientoBanco getInstancia() {
        if (manejo == null) {
            manejo = new ManejoMovimientoBanco();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<MovimientoBanco> getMovimientoBanco() {

        String query = " SELECT * FROM movimiento_banco  where  unidad_de_negocio="+ VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(MovimientoBanco.class).list();

    }
    
    
      public List<MovimientoBanco> getMovimientoBanco(Date fechaIni, Date fechaFin) {

        String query = "SELECT * FROM  movimiento_banco b  where  fecha_operacion  "
                + "  between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaIni) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaFin) + "' "
                + " and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(MovimientoBanco.class).list();

    }

    public List<MovimientoBanco> getMovimientoPorBanco(Banco banco) {

        String query = " SELECT * FROM movimiento_banco  where  banco=:banco ";

        return session.createSQLQuery(query)
                .addEntity(MovimientoBanco.class)
                .setParameter("banco", banco)
                .list();

    }

    public MovimientoBanco getaMovimientoBanco(int codigo) {

        String query = " SELECT * FROM movimiento_banco where codigo=:codigo ";

        return (MovimientoBanco) session.createSQLQuery(query)
                .addEntity(MovimientoBanco.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    public MovimientoBanco getBanco(int codigo, int unidadNegocio) {

        String query = " SELECT * FROM movimiento_banco where codigo=:codigo and unidad_de_negocio=:ung";

        return (MovimientoBanco) session.createSQLQuery(query)
                .addEntity(MovimientoBanco.class)
                .setParameter("codigo", codigo)
                .setParameter("ung", unidadNegocio)
                .uniqueResult();

    }


    @Override
    public Class getReferencia() {
        return Banco.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
