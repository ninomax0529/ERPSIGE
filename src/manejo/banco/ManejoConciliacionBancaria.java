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
import modelo.ConciliacionBancaria;
import modelo.DetalleConciliacionBancariaBanco;
import modelo.DetalleConciliacionBancariaLibro;
import modelo.MovimientoBanco;
import org.hibernate.Session;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoConciliacionBancaria extends ManejoEstandar<ConciliacionBancaria> {

    private static ManejoConciliacionBancaria manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoConciliacionBancaria getInstancia() {
        if (manejo == null) {
            manejo = new ManejoConciliacionBancaria();
        }
        return manejo;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List<ConciliacionBancaria> getConciliacionBancaria() {

        String query = " SELECT * FROM conciliacion_bancaria  where  unidad_de_negocio="+ VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(ConciliacionBancaria.class).list();

    }
    
    
      public List<ConciliacionBancaria> getConciliacionBancaria(Date fechaIni, Date fechaFin) {

        String query = "SELECT * FROM  conciliacion_bancaria b  where  fecha "
                + "  between '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaIni) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaFin) + "' "
                + " and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(ConciliacionBancaria.class).list();

    }

    public List<ConciliacionBancaria> getConciliacionBancaria(Banco banco) {

        String query = " SELECT * FROM conciliacion_bancaria  where  banco=:banco ";

        return session.createSQLQuery(query)
                .addEntity(ConciliacionBancaria.class)
                .setParameter("banco", banco)
                .list();

    }
    
     public List<DetalleConciliacionBancariaBanco> getConciliacionBancariaBanco(int conciliacion) {

        String query = " SELECT * FROM detalle_conciliacion_bancaria_banco  where  conciliacion_bancario=:conciliacion  ";

        return session.createSQLQuery(query)
                .addEntity(DetalleConciliacionBancariaBanco.class)
                .setParameter("conciliacion", conciliacion)
                .list();

    }
     
       public List<DetalleConciliacionBancariaLibro> getConciliacionBancariaLibro(int conciliacion) {

        String query = " SELECT * FROM detalle_conciliacion_bancaria_libro  where  conciliacion_bancario=:conciliacion  ";

        return session.createSQLQuery(query)
                .addEntity(DetalleConciliacionBancariaLibro.class)
                .setParameter("conciliacion", conciliacion)
                .list();

    }


    public ConciliacionBancaria getaConciliacionBancaria(int codigo) {

        String query = " SELECT * FROM conciliacion_bancaria where codigo=:codigo ";

        return (ConciliacionBancaria) session.createSQLQuery(query)
                .addEntity(ConciliacionBancaria.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

    }

    public ConciliacionBancaria getBanco(int codigo, int unidadNegocio) {

        String query = " SELECT * FROM conciliacion_bancaria where codigo=:codigo and unidad_de_negocio=:ung";

        return (ConciliacionBancaria) session.createSQLQuery(query)
                .addEntity(ConciliacionBancaria.class)
                .setParameter("codigo", codigo)
                .setParameter("ung", unidadNegocio)
                .uniqueResult();

    }


    @Override
    public Class getReferencia() {
        return ConciliacionBancaria.class;
    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getArticulo().getDescripcion());
    }

}
