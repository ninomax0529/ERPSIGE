/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.impuesto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DetalleFormato606;
import modelo.DetalleFormato607;
import modelo.Formato606;
import modelo.Formato607;

import org.hibernate.Session;
import utiles.Conexion;
//import utils.Encriptar;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoFormato607 extends ManejoEstandar<Formato607> {

    private static ManejoFormato607 manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoFormato607 getInstancia() {
        if (manejo == null) {
            manejo = new ManejoFormato607();
        }
        return manejo;
    }

    public List<Formato607> getFormato607() {

        String query = "SELECT * FROM formato_607 ";

        return session.createSQLQuery(query).addEntity(Formato606.class).list();

    }

    public List<Formato607> getListaFormato606(Integer periodo) {

        String query = " SELECT * from formato_607  WHERE  periodo=:periodoParam ";

        return session.createSQLQuery(query).addEntity(Formato606.class)
                .setParameter("periodoParam", periodo)
                .list();

    }

    public Formato607 getFormato607(int anio, int mes) {

        String query = " SELECT * FROM formato_607  where  anio=:anio  and mes=:mes ";

        return (Formato607) session.createSQLQuery(query).addEntity(Formato607.class)
                .setParameter("anio", anio)
                .setParameter("mes", mes)
                .uniqueResult();

    }

    public Formato607 getFormato607(int codigo) {

        String query = "SELECT * FROM formato_607  where  codigo=:codigo";

        return (Formato607) session.createSQLQuery(query).addEntity(Formato607.class)
                .setParameter("codigo", codigo).uniqueResult();

    }

    public Formato607 getFormato606(int anio, int mes) {

        String query = " SELECT * FROM formato_607  where  anio=:anio  and mes=:mes ";

        return (Formato607) session.createSQLQuery(query).addEntity(Formato607.class)
                .setParameter("anio", anio)
                .setParameter("mes", mes)
                .uniqueResult();

    }

    public List<DetalleFormato607> getDetalleEntradaDiario(Formato607 formato) {

        List<DetalleFormato607> lista;
        String query = "SELECT * FROM detalle_formato_607  where  fprmato607=:formato ";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleFormato607.class)
                .setParameter("formato", formato.getCodigo()).list();

        return lista;
    }

    public void eliminarfORMATO607(int formato) {

        Connection cn = Conexion.getInsatancia().getConnectionDB();
        try {

            PreparedStatement pst = cn.prepareStatement(" delete from detalle_formato_607 where formato_607=? ");

            pst.setInt(1, formato);

            System.out.println("eliminando detalle  " + pst.execute());

            pst = cn.prepareStatement(" delete from  formato_607  where  codigo=?  ");

            pst.setInt(1, formato);
            System.out.println("eliminado header  " + pst.execute());

        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            try {
//                cn.close();
//                System.out.println("conexion cerrada " + cn.isClosed());
//            } catch (SQLException ex) {
//                Logger.getLogger(ManejoFactura.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

    }

    @Override
    public Class getReferencia() {
        return Formato607.class;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public static void main(String[] args) {

        getInstancia().eliminarfORMATO607(1);
//        System.out.println("Datos " + getInstancia().eliminarfORMATO606(1));
    }

}
