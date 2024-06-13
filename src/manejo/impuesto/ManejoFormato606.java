/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.impuesto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DetalleFormato606;
import modelo.Formato606;

import org.hibernate.Session;
import utiles.Conexion;
//import utils.Encriptar;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoFormato606 extends ManejoEstandar<Formato606> {

    private static ManejoFormato606 manejo = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoFormato606 getInstancia() {
        if (manejo == null) {
            manejo = new ManejoFormato606();
        }
        return manejo;
    }

    public List<Formato606> getFormato606() {

        String query = "SELECT * FROM formato_606 ";

        return session.createSQLQuery(query).addEntity(Formato606.class).list();

    }

    public List<Formato606> getListaFormato606(Integer periodo) {

        String query = " SELECT * from formato_606  WHERE  periodo=:periodoParam ";

        return session.createSQLQuery(query).addEntity(Formato606.class)
                .setParameter("periodoParam", periodo)
                .list();

    }

    public Formato606 getFormato606(int codigo) {

        String query = "SELECT * FROM formato_606  where  codigo=:codigo";

        return (Formato606) session.createSQLQuery(query).addEntity(Formato606.class)
                .setParameter("codigo", codigo).uniqueResult();

    }

    public Formato606 getFormato606(int anio, int mes) {

        String query = " SELECT * FROM formato_606  where  anio=:anio  and mes=:mes ";

        return (Formato606) session.createSQLQuery(query).addEntity(Formato606.class)
                .setParameter("anio", anio)
                .setParameter("mes", mes)
                .uniqueResult();

    }

    public List<DetalleFormato606> getDetalleEntradaDiario(Formato606 formato) {

        List<DetalleFormato606> lista;
        String query = "SELECT * FROM detalle_formato_606  where  fprmato606=:formato ";

        lista = session.createSQLQuery(query)
                .addEntity(DetalleFormato606.class)
                .setParameter("formato", formato.getCodigo()).list();

        return lista;
    }

    public void eliminarfORMATO606(int codigo) {

        Connection cn = Conexion.getInsatancia().getConnectionDB();
        try {

            PreparedStatement pst = cn.prepareStatement(" delete from detalle_formato_606 where formato_606=? ");

            pst.setInt(1, codigo);

            System.out.println("eliminado detalle  " + pst.execute());

            pst = cn.prepareStatement(" delete from  formato_606  where  codigo=?  ");

            pst.setInt(1, codigo);
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
        return Formato606.class;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public static void main(String[] args) {

        getInstancia().eliminarfORMATO606(2);
//        System.out.println("Datos " + getInstancia().eliminarfORMATO606(1));
    }

}
