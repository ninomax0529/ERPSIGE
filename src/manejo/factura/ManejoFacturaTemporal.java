/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo.factura;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejo.HibernateUtil;
import manejo.ManejoEstandar;
import modelo.DetalleFacturaTemporal;
import modelo.FacturaTemporal;
import org.hibernate.Session;
import utiles.Conexion;
import utiles.VariablesGlobales;

/**
 *
 * @author maximilianoa-te
 */
public class ManejoFacturaTemporal extends ManejoEstandar<FacturaTemporal> {

    private static ManejoFacturaTemporal manejoFactura = null;
    private Session session = HibernateUtil.getSession();

    public static ManejoFacturaTemporal getInstancia() {
        if (manejoFactura == null) {
            manejoFactura = new ManejoFacturaTemporal();
        }
        return manejoFactura;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public FacturaTemporal getFactura(int codigo) {

        FacturaTemporal factura = null;

        String query = " select * From factura_temporal f where f.codigo=:codigo ";

        System.out.println("Query " + query);

        factura = (FacturaTemporal) getSession().createSQLQuery(query).addEntity(FacturaTemporal.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

        return factura;
    }

    public List<FacturaTemporal> getLista() {

        String query = " SELECT * FROM factura_temporal  where  unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query).addEntity(FacturaTemporal.class).list();

    }

    public List<FacturaTemporal> getListaFactura(Date fecha) {

        String query = " SELECT * FROM  factura_temporal  where  fecha=:fecha ";
//        String query = " SELECT * FROM  factura  where  fecha=:fecha  and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        return session.createSQLQuery(query)
                .addEntity(FacturaTemporal.class)
                .setParameter("fecha", fecha)
                .list();

    }

    public List<FacturaTemporal> getLista(Date fechaDesde, Date fechaHasta) {

        String query = " SELECT * FROM factura_temporal where fecha "
                + " between  '" + new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde) + "' "
                + "and '" + new SimpleDateFormat("yyyy-MM-dd")
                        .format(fechaHasta) + "' and unidad_de_negocio=" + VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo();

        System.out.println("Sql " + query);
        return session.createSQLQuery(query).addEntity(FacturaTemporal.class).list();

    }

    public List<DetalleFacturaTemporal> getDetalleFactura(int factura) {

        String query = " SELECT * FROM detalle_factura_temporal  where factura=:factura  ";

        return session.createSQLQuery(query).addEntity(DetalleFacturaTemporal.class).setParameter("factura", factura).list();

    }

    public boolean existeSecuencia(int numero) {

        boolean flag = false;

        FacturaTemporal factura = (FacturaTemporal) session.createSQLQuery(" SELECT * FROM factura_temporal f  where anulada=false and "
                + "  f.numero=:numero limit 1 ")
                .addEntity(FacturaTemporal.class)
                .setParameter("numero", numero).uniqueResult();

        if (!(factura == null)) {

            flag = true;

        } else {

            flag = false;
        }

        return flag;

    }

    @Override
    public Class getReferencia() {
        return FacturaTemporal.class;
    }

    public void eliminarFacturaTemporal() {

        Connection cn = Conexion.getInsatancia().getConnectionDB();
        try {

            PreparedStatement pst = cn.prepareStatement(" delete from detalle_factura_temporal where codigo>0 ");

            System.out.println("eliminado detalle  " + pst.execute());

            pst = cn.prepareStatement(" delete from factura_temporal where codigo>0 ");
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

    public BigInteger getNumero() {

        String query = " select ifnull(max(numero)+1,0) from factura_temporal  where unidad_de_negocio=:ung ";

        return (BigInteger) session.createSQLQuery(query)
                .setParameter("ung", VariablesGlobales.USUARIO.getUnidadDeNegocio().getCodigo())
                .uniqueResult();

    }

    public static void main(String[] args) {

//        System.out.println("Datos " + getInstancia().getMontoPendiente(ManejoCliente.getInstancia().getCliente(2)));
//        System.out.println("Datos " + getInstancia().existeSecuencia(1000));
//        System.out.println("Datos " + );
//        getInstancia().enviarFacturaPorCorreoEnPdf(new Date("2022/05/20"));
        getInstancia().eliminarFacturaTemporal();
    }

}
